package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemBullet;
import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeaponReloadAspect implements Aspect {
   private static final Logger logger = LogManager.getLogger(WeaponReloadAspect.class);
   private static final long ALERT_TIMEOUT = 500L;
   private static final Set allowedUpdateFromStates;
   private static Predicate sprinting;
   private static Predicate supportsDirectBulletLoad;
   private static Predicate magazineAttached;
   private static Predicate reloadAnimationCompleted;
   private static Predicate unloadAnimationCompleted;
   private Predicate inventoryHasFreeSlots = (weaponInstance) -> {
      return CompatibilityProvider.compatibility.inventoryHasFreeSlots(weaponInstance.getPlayer());
   };
   private static Predicate alertTimeoutExpired;
   private Predicate magazineNotEmpty = (magazineStack) -> {
      return Tags.getAmmo(magazineStack) > 0;
   };
   private ModContext modContext;
   private PermitManager permitManager;
   private StateManager stateManager;

   public WeaponReloadAspect(ModContext modContext) {
      this.modContext = modContext;
   }

   public void setStateManager(StateManager stateManager) {
      if(this.permitManager == null) {
         throw new IllegalStateException("Permit manager not initialized");
      } else {
         this.stateManager = stateManager.in(this).change(WeaponState.READY).to(WeaponState.LOAD).when(supportsDirectBulletLoad.or(magazineAttached.negate())).withPermit((s, es) -> {
            return new WeaponReloadAspect.LoadPermit(s);
         }, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).withAction((c, f, t, p) -> {
            this.completeClientLoad(c, (WeaponReloadAspect.LoadPermit)p);
         }).manual().in(this).change(WeaponState.LOAD).to(WeaponState.READY).when(reloadAnimationCompleted).automatic().in(this).prepare((c, f, t) -> {
            this.prepareUnload(c);
         }, unloadAnimationCompleted).change(WeaponState.READY).to(WeaponState.UNLOAD).when(magazineAttached.and(this.inventoryHasFreeSlots)).withPermit((s, c) -> {
            return new WeaponReloadAspect.UnloadPermit(s);
         }, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).withAction((c, f, t, p) -> {
            this.completeClientUnload(c, (WeaponReloadAspect.UnloadPermit)p);
         }).manual().in(this).change(WeaponState.UNLOAD).to(WeaponState.READY).automatic().in(this).change(WeaponState.READY).to(WeaponState.ALERT).when(this.inventoryHasFreeSlots.negate()).withAction(this::inventoryFullAlert).manual().in(this).change(WeaponState.ALERT).to(WeaponState.READY).when(alertTimeoutExpired).automatic();
      }
   }

   public void setPermitManager(PermitManager permitManager) {
      this.permitManager = permitManager;
      permitManager.registerEvaluator(WeaponReloadAspect.LoadPermit.class, PlayerWeaponInstance.class, (p, c) -> {
         this.processLoadPermit(p, c);
      });
      permitManager.registerEvaluator(WeaponReloadAspect.UnloadPermit.class, PlayerWeaponInstance.class, (p, c) -> {
         this.processUnloadPermit(p, c);
      });
   }

   public void reloadMainHeldItem(EntityPlayer player) {
      PlayerWeaponInstance instance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(instance != null) {
         this.stateManager.changeState(this, instance, new WeaponState[]{WeaponState.LOAD, WeaponState.UNLOAD, WeaponState.ALERT});
      }

   }

   void updateMainHeldItem(EntityPlayer player) {
      PlayerWeaponInstance instance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates, new WeaponState[0]);
      }

   }

   private void processLoadPermit(WeaponReloadAspect.LoadPermit p, PlayerWeaponInstance weaponInstance) {
      logger.debug("Processing load permit on server for {}", new Object[]{weaponInstance});
      ItemStack weaponItemStack = weaponInstance.getItemStack();
      if(weaponItemStack != null) {
         Permit.Status status = Permit.Status.GRANTED;
         Weapon weapon = (Weapon)weaponInstance.getItem();
         EntityPlayer player = weaponInstance.getPlayer();
         if(CompatibilityProvider.compatibility.getTagCompound(weaponItemStack) == null) {
            CompatibilityProvider.compatibility.setTagCompound(weaponItemStack, new NBTTagCompound());
         }

         List compatibleMagazines = weapon.getCompatibleMagazines();
         List compatibleBullets = weapon.getCompatibleAttachments(ItemBullet.class);
         if(!compatibleMagazines.isEmpty()) {
            ItemAttachment ammo = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
            int ammo1 = Tags.getAmmo(weaponItemStack);
            if(ammo == null) {
               ammo1 = 0;
               ItemStack magazineItemStack = CompatibilityProvider.compatibility.tryConsumingCompatibleItem(compatibleMagazines, 1, player, new Predicate[]{this.magazineNotEmpty, (magazineStack) -> {
                  return true;
               }});
               if(magazineItemStack != null) {
                  ammo1 = Tags.getAmmo(magazineItemStack);
                  Tags.setAmmo(weaponItemStack, ammo1);
                  logger.debug("Setting server side ammo for {} to {}", new Object[]{weaponInstance, Integer.valueOf(ammo1)});
                  this.modContext.getAttachmentAspect().addAttachment((ItemAttachment)magazineItemStack.getItem(), weaponInstance);
                  CompatibilityProvider.compatibility.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
               } else {
                  status = Permit.Status.DENIED;
               }
            }

            weaponInstance.setAmmo(ammo1);
         } else {
            ItemStack consumedStack;
            if(!compatibleBullets.isEmpty() && (consumedStack = CompatibilityProvider.compatibility.tryConsumingCompatibleItem(compatibleBullets, Math.min(weapon.getMaxBulletsPerReload(), weapon.getAmmoCapacity() - weaponInstance.getAmmo()), player, new Predicate[]{(i) -> {
               return true;
            }})) != null) {
               int ammo2 = weaponInstance.getAmmo() + CompatibilityProvider.compatibility.getStackSize(consumedStack);
               Tags.setAmmo(weaponItemStack, ammo2);
               weaponInstance.setAmmo(ammo2);
               CompatibilityProvider.compatibility.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
            } else if(CompatibilityProvider.compatibility.consumeInventoryItem((InventoryPlayer)player.inventory, weapon.builder.ammo)) {
               Tags.setAmmo(weaponItemStack, weapon.builder.ammoCapacity);
               weaponInstance.setAmmo(weapon.builder.ammoCapacity);
               CompatibilityProvider.compatibility.playSoundToNearExcept(player, weapon.getReloadSound(), 1.0F, 1.0F);
            } else {
               logger.debug("No suitable ammo found for {}. Permit denied.", new Object[]{weaponInstance});
               status = Permit.Status.DENIED;
            }
         }

         p.setStatus(status);
      }
   }

   private void prepareUnload(PlayerWeaponInstance weaponInstance) {
      CompatibilityProvider.compatibility.playSound(weaponInstance.getPlayer(), weaponInstance.getWeapon().getUnloadSound(), 1.0F, 1.0F);
   }

   private void processUnloadPermit(WeaponReloadAspect.UnloadPermit p, PlayerWeaponInstance weaponInstance) {
      logger.debug("Processing unload permit on server for {}", new Object[]{weaponInstance});
      ItemStack weaponItemStack = weaponInstance.getItemStack();
      EntityPlayer player = weaponInstance.getPlayer();
      Weapon weapon = (Weapon)weaponItemStack.getItem();
      if(CompatibilityProvider.compatibility.getTagCompound(weaponItemStack) != null) {
         ItemAttachment attachment = this.modContext.getAttachmentAspect().removeAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
         if(attachment instanceof ItemMagazine) {
            ItemStack attachmentItemStack = ((ItemMagazine)attachment).createItemStack();
            Tags.setAmmo(attachmentItemStack, weaponInstance.getAmmo());
            if(!player.inventory.addItemStackToInventory(attachmentItemStack)) {
               logger.error("Cannot add attachment " + attachment + " for " + weaponInstance + "back to the inventory");
            }
         }

         Tags.setAmmo(weaponItemStack, 0);
         weaponInstance.setAmmo(0);
         CompatibilityProvider.compatibility.playSoundToNearExcept(player, weapon.getUnloadSound(), 1.0F, 1.0F);
         p.setStatus(Permit.Status.GRANTED);
      } else {
         p.setStatus(Permit.Status.DENIED);
      }

      p.setStatus(Permit.Status.GRANTED);
   }

   private void completeClientLoad(PlayerWeaponInstance weaponInstance, WeaponReloadAspect.LoadPermit permit) {
      if(permit == null) {
         logger.error("Permit is null, something went wrong");
      } else {
         if(permit.getStatus() == Permit.Status.GRANTED) {
            CompatibilityProvider.compatibility.playSound(weaponInstance.getPlayer(), weaponInstance.getWeapon().getReloadSound(), 1.0F, 1.0F);
         }

      }
   }

   private void completeClientUnload(PlayerWeaponInstance weaponInstance, WeaponReloadAspect.UnloadPermit p) {
   }

   public void inventoryFullAlert(PlayerWeaponInstance weaponInstance) {
      this.modContext.getStatusMessageCenter().addAlertMessage(CompatibilityProvider.compatibility.getLocalizedString("gui.inventoryFull", new Object[0]), 3, 250L, 200L);
   }

   static {
      TypeRegistry.getInstance().register(WeaponReloadAspect.UnloadPermit.class);
      TypeRegistry.getInstance().register(WeaponReloadAspect.LoadPermit.class);
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
      allowedUpdateFromStates = new HashSet(Arrays.asList(new WeaponState[]{WeaponState.LOAD_REQUESTED, WeaponState.LOAD, WeaponState.UNLOAD_PREPARING, WeaponState.UNLOAD_REQUESTED, WeaponState.UNLOAD, WeaponState.ALERT}));
      sprinting = (instance) -> {
         return instance.getPlayer().isSprinting();
      };
      supportsDirectBulletLoad = (weaponInstance) -> {
         return weaponInstance.getWeapon().getAmmoCapacity() > 0;
      };
      magazineAttached = (weaponInstance) -> {
         return WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance) != null;
      };
      reloadAnimationCompleted = (weaponInstance) -> {
         return (double)System.currentTimeMillis() >= (double)weaponInstance.getStateUpdateTimestamp() + Math.max((double)weaponInstance.getWeapon().builder.reloadingTimeout, (double)weaponInstance.getWeapon().getTotalReloadingDuration() * 1.1D);
      };
      unloadAnimationCompleted = (weaponInstance) -> {
         return (double)System.currentTimeMillis() >= (double)weaponInstance.getStateUpdateTimestamp() + (double)weaponInstance.getWeapon().getTotalUnloadingDuration() * 1.1D;
      };
      alertTimeoutExpired = (instance) -> {
         return System.currentTimeMillis() >= 500L + instance.getStateUpdateTimestamp();
      };
   }

   public static class LoadPermit extends Permit {
      public LoadPermit() {
      }

      public LoadPermit(WeaponState state) {
         super(state);
      }
   }

   public static class UnloadPermit extends Permit {
      public UnloadPermit() {
      }

      public UnloadPermit(WeaponState state) {
         super(state);
      }
   }
}
