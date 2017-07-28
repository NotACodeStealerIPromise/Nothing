package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CommonModContext;
import com.vicmatskiv.weaponlib.EntityShellCasing;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.TryFireMessage;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class WeaponFireAspect implements Aspect {
   private static final float FLASH_X_OFFSET_ZOOMED = 0.0F;
   private static final long ALERT_TIMEOUT = 500L;
   private static Predicate readyToShootAccordingToFireRate = (instance) -> {
      return (float)(System.currentTimeMillis() - instance.getLastFireTimestamp()) >= 50.0F / instance.getWeapon().builder.fireRate;
   };
   private static Predicate readyToShootAccordingToFireMode = (instance) -> {
      return instance.getSeriesShotCount() < instance.getMaxShots();
   };
   private static Predicate hasAmmo = (instance) -> {
      return instance.getAmmo() > 0;
   };
   private static Predicate ejectSpentRoundRequired = (instance) -> {
      return instance.getWeapon().ejectSpentRoundRequired();
   };
   private static Predicate ejectSpentRoundTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() >= instance.getWeapon().builder.pumpTimeoutMilliseconds + instance.getStateUpdateTimestamp();
   };
   private static Predicate alertTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() >= 500L + instance.getStateUpdateTimestamp();
   };
   private static Predicate sprinting = (instance) -> {
      return instance.getPlayer().isSprinting();
   };
   private static final Set allowedFireOrEjectFromStates;
   private static final Set allowedUpdateFromStates;
   private ModContext modContext;
   private StateManager stateManager;

   public WeaponFireAspect(CommonModContext modContext) {
      this.modContext = modContext;
   }

   public void setPermitManager(PermitManager permitManager) {
   }

   public void setStateManager(StateManager stateManager) {
      this.stateManager = stateManager;
      stateManager.in(this).change(WeaponState.READY).to(WeaponState.ALERT).when(hasAmmo.negate()).withAction(this::cannotFire).manual().in(this).change(WeaponState.ALERT).to(WeaponState.READY).when(alertTimeoutExpired).automatic().in(this).change(WeaponState.READY).to(WeaponState.FIRING).when(hasAmmo.and(sprinting.negate()).and(readyToShootAccordingToFireRate)).withAction(this::fire).manual().in(this).change(WeaponState.FIRING).to(WeaponState.RECOILED).automatic().in(this).change(WeaponState.RECOILED).to(WeaponState.PAUSED).automatic().in(this).change(WeaponState.PAUSED).to(WeaponState.EJECT_REQUIRED).when(ejectSpentRoundRequired).manual().in(this).change(WeaponState.EJECT_REQUIRED).to(WeaponState.EJECTING).withAction(this::ejectSpentRound).manual().in(this).change(WeaponState.EJECTING).to(WeaponState.READY).when(ejectSpentRoundTimeoutExpired).automatic().in(this).change(WeaponState.PAUSED).to(WeaponState.FIRING).when(hasAmmo.and(sprinting.negate()).and(readyToShootAccordingToFireMode).and(readyToShootAccordingToFireRate)).withAction(this::fire).manual().in(this).change(WeaponState.PAUSED).to(WeaponState.READY).when(ejectSpentRoundRequired.negate()).withAction(PlayerWeaponInstance::resetCurrentSeries).manual();
   }

   void onFireButtonClick(EntityPlayer player) {
      PlayerWeaponInstance weaponInstance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedFireOrEjectFromStates, new WeaponState[]{WeaponState.FIRING, WeaponState.EJECTING, WeaponState.ALERT});
      }

   }

   void onFireButtonRelease(EntityPlayer player) {
      PlayerWeaponInstance weaponInstance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, new WeaponState[]{WeaponState.EJECT_REQUIRED, WeaponState.READY});
      }

   }

   void onUpdate(EntityPlayer player) {
      PlayerWeaponInstance weaponInstance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedUpdateFromStates, new WeaponState[0]);
      }

   }

   private void cannotFire(PlayerWeaponInstance weaponInstance) {
      if(weaponInstance.getAmmo() == 0) {
         String message;
         if(weaponInstance.getWeapon().getAmmoCapacity() == 0 && this.modContext.getAttachmentAspect().getActiveAttachment(weaponInstance, AttachmentCategory.MAGAZINE) == null) {
            message = CompatibilityProvider.compatibility.getLocalizedString("gui.noMagazine", new Object[0]);
         } else {
            message = CompatibilityProvider.compatibility.getLocalizedString("gui.noAmmo", new Object[0]);
         }

         this.modContext.getStatusMessageCenter().addAlertMessage(message, 3, 250L, 200L);
         CompatibilityProvider.compatibility.playSound(weaponInstance.getPlayer(), this.modContext.getNoAmmoSound(), 1.0F, 1.0F);
      }

   }

   private void fire(PlayerWeaponInstance weaponInstance) {
      EntityPlayer player = weaponInstance.getPlayer();
      Weapon weapon = (Weapon)weaponInstance.getItem();
      Random random = player.getRNG();
      this.modContext.getChannel().getChannel().sendToServer(new TryFireMessage(true));
      boolean silencerOn = this.modContext.getAttachmentAspect().isSilencerOn(weaponInstance);
      CompatibilityProvider.compatibility.playSound(player, silencerOn?weapon.getSilencedShootSound():weapon.getShootSound(), silencerOn?weapon.getSilencedShootSoundVolume():weapon.getShootSoundVolume(), 1.0F);
      player.rotationPitch -= weaponInstance.getRecoil();
      float rotationYawFactor = -1.0F + random.nextFloat() * 2.0F;
      player.rotationYaw += weaponInstance.getRecoil() * rotationYawFactor;
      Boolean muzzleFlash = this.modContext.getConfigurationManager().getProjectiles().isMuzzleEffects();
      if(muzzleFlash == null || muzzleFlash.booleanValue()) {
         if(weapon.builder.flashIntensity > 0.0F) {
            this.modContext.getEffectManager().spawnFlashParticle(player, weapon.builder.flashIntensity, ((Float)weapon.builder.flashScale.get()).floatValue(), weaponInstance.isAimed()?0.0F:CompatibilityProvider.compatibility.getEffectOffsetX() + ((Float)weapon.builder.flashOffsetX.get()).floatValue(), CompatibilityProvider.compatibility.getEffectOffsetY() + ((Float)weapon.builder.flashOffsetY.get()).floatValue());
         }

         this.modContext.getEffectManager().spawnSmokeParticle(player, CompatibilityProvider.compatibility.getEffectOffsetX() + ((Float)weapon.builder.smokeOffsetX.get()).floatValue(), CompatibilityProvider.compatibility.getEffectOffsetY() + ((Float)weapon.builder.smokeOffsetY.get()).floatValue());
      }

      weaponInstance.setSeriesShotCount(weaponInstance.getSeriesShotCount() + 1);
      weaponInstance.setLastFireTimestamp(System.currentTimeMillis());
      weaponInstance.setAmmo(weaponInstance.getAmmo() - 1);
   }

   private void ejectSpentRound(PlayerWeaponInstance weaponInstance) {
      EntityPlayer player = weaponInstance.getPlayer();
      CompatibilityProvider.compatibility.playSound(player, weaponInstance.getWeapon().getEjectSpentRoundSound(), 1.0F, 1.0F);
   }

   void serverFire(EntityPlayer player, ItemStack itemStack) {
      if(itemStack.getItem() instanceof Weapon) {
         Weapon weapon = (Weapon)itemStack.getItem();

         for(int playerWeaponInstance = 0; playerWeaponInstance < weapon.builder.pellets; ++playerWeaponInstance) {
            WeaponSpawnEntity silencerOn = (WeaponSpawnEntity)weapon.builder.spawnEntityWith.apply(weapon, player);
            CompatibilityProvider.compatibility.spawnEntity(player, silencerOn);
         }

         PlayerWeaponInstance var6 = (PlayerWeaponInstance)Tags.getInstance(itemStack, PlayerWeaponInstance.class);
         if(weapon.isShellCasingEjectEnabled()) {
            EntityShellCasing var7 = (EntityShellCasing)weapon.builder.spawnShellWith.apply(var6, player);
            if(var7 != null) {
               CompatibilityProvider.compatibility.spawnEntity(player, var7);
            }
         }

         boolean var8 = this.modContext.getAttachmentAspect().isSilencerOn(var6);
         CompatibilityProvider.compatibility.playSoundToNearExcept(player, var6 != null && var8?weapon.getSilencedShootSound():weapon.getShootSound(), var8?weapon.getSilencedShootSoundVolume():weapon.getShootSoundVolume(), 1.0F);
      }
   }

   static {
      allowedFireOrEjectFromStates = new HashSet(Arrays.asList(new WeaponState[]{WeaponState.READY, WeaponState.PAUSED, WeaponState.EJECT_REQUIRED}));
      allowedUpdateFromStates = new HashSet(Arrays.asList(new WeaponState[]{WeaponState.EJECTING, WeaponState.PAUSED, WeaponState.FIRING, WeaponState.RECOILED, WeaponState.PAUSED, WeaponState.ALERT}));
   }
}
