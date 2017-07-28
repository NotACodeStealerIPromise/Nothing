package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.MagazineState;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerMagazineInstance;
import com.vicmatskiv.weaponlib.Tags;
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
import net.minecraft.item.ItemStack;

public class MagazineReloadAspect implements Aspect {
   private static final Set allowedUpdateFromStates;
   private static long reloadAnimationDuration;
   private static Predicate reloadAnimationCompleted;
   private ModContext modContext;
   private PermitManager permitManager;
   private StateManager stateManager;
   private Predicate notFull = (instance) -> {
      boolean result = Tags.getAmmo(instance.getItemStack()) < instance.getMagazine().getAmmo();
      return result;
   };

   public MagazineReloadAspect(ModContext modContext) {
      this.modContext = modContext;
   }

   public void setStateManager(StateManager stateManager) {
      if(this.permitManager == null) {
         throw new IllegalStateException("Permit manager not initialized");
      } else {
         this.stateManager = stateManager.in(this).change(MagazineState.READY).to(MagazineState.LOAD).when(this.notFull).withPermit((s, es) -> {
            return new MagazineReloadAspect.LoadPermit(s);
         }, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).withAction((c, f, t, p) -> {
            this.doPermittedLoad(c, (MagazineReloadAspect.LoadPermit)p);
         }).manual().in(this).change(MagazineState.LOAD).to(MagazineState.READY).when(reloadAnimationCompleted).automatic();
      }
   }

   public void setPermitManager(PermitManager permitManager) {
      this.permitManager = permitManager;
      permitManager.registerEvaluator(MagazineReloadAspect.LoadPermit.class, PlayerMagazineInstance.class, (p, c) -> {
         this.evaluateLoad(p, c);
      });
   }

   public void reloadMainHeldItem(EntityPlayer player) {
      PlayerMagazineInstance instance = (PlayerMagazineInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMagazineInstance.class);
      this.stateManager.changeState(this, instance, new MagazineState[]{MagazineState.LOAD});
   }

   void updateMainHeldItem(EntityPlayer player) {
      PlayerMagazineInstance instance = (PlayerMagazineInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMagazineInstance.class);
      if(instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, allowedUpdateFromStates, new MagazineState[0]);
      }

   }

   private void evaluateLoad(MagazineReloadAspect.LoadPermit p, PlayerMagazineInstance magazineInstance) {
      ItemStack magazineStack = magazineInstance.getItemStack();
      Permit.Status status = Permit.Status.DENIED;
      if(magazineStack.getItem() instanceof ItemMagazine) {
         ItemMagazine magazine = (ItemMagazine)magazineStack.getItem();
         List compatibleBullets = magazine.getCompatibleBullets();
         int currentAmmo = Tags.getAmmo(magazineStack);
         ItemStack consumedStack;
         if((consumedStack = CompatibilityProvider.compatibility.tryConsumingCompatibleItem(compatibleBullets, magazine.getAmmo() - currentAmmo, magazineInstance.getPlayer(), new Predicate[]{(i) -> {
            return true;
         }})) != null) {
            Tags.setAmmo(magazineStack, Tags.getAmmo(magazineStack) + CompatibilityProvider.compatibility.getStackSize(consumedStack));
            if(magazine.getReloadSound() != null) {
               CompatibilityProvider.compatibility.playSound(magazineInstance.getPlayer(), magazine.getReloadSound(), 1.0F, 1.0F);
            }

            status = Permit.Status.GRANTED;
         }
      }

      p.setStatus(status);
   }

   private void doPermittedLoad(PlayerMagazineInstance weaponInstance, MagazineReloadAspect.LoadPermit permit) {
      if(permit == null) {
         System.err.println("Permit is null, something went wrong");
      }
   }

   static {
      TypeRegistry.getInstance().register(MagazineReloadAspect.LoadPermit.class);
      allowedUpdateFromStates = new HashSet(Arrays.asList(new MagazineState[]{MagazineState.LOAD_REQUESTED, MagazineState.LOAD}));
      reloadAnimationDuration = 1000L;
      reloadAnimationCompleted = (es) -> {
         return System.currentTimeMillis() >= es.getStateUpdateTimestamp() + reloadAnimationDuration;
      };
   }

   public static class LoadPermit extends Permit {
      public LoadPermit() {
      }

      public LoadPermit(MagazineState state) {
         super(state);
      }
   }
}
