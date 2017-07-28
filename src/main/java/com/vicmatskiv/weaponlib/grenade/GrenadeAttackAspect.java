package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.CommonModContext;
import com.vicmatskiv.weaponlib.Explosion;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.grenade.EntityGrenade;
import com.vicmatskiv.weaponlib.grenade.EntitySmokeGrenade;
import com.vicmatskiv.weaponlib.grenade.GrenadeMessage;
import com.vicmatskiv.weaponlib.grenade.GrenadeState;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GrenadeAttackAspect implements Aspect {
   private static final Logger logger = LogManager.getLogger(GrenadeAttackAspect.class);
   private static final long ALERT_TIMEOUT = 300L;
   private Predicate hasSafetyPin = (instance) -> {
      return instance.getWeapon().hasSafetyPin();
   };
   private static Predicate reequipTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() > instance.getStateUpdateTimestamp() + instance.getWeapon().getReequipTimeout();
   };
   private static Predicate isSmokeGrenade = (instance) -> {
      return instance.getWeapon().isSmokeOnly();
   };
   private static Predicate throwingCompleted = (instance) -> {
      return (double)System.currentTimeMillis() >= (double)instance.getStateUpdateTimestamp() + instance.getWeapon().getTotalThrowingDuration() * 1.1D;
   };
   private static Predicate explosionTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() >= instance.getStateUpdateTimestamp() + (long)instance.getWeapon().getExplosionTimeout();
   };
   private static final Set allowedAttackFromStates;
   private static final Set allowedPinOffFromStates;
   private static final Set allowedUpdateFromStates;
   private static final int SAFETY_IN_ALERT_TIMEOUT = 1000;
   private ModContext modContext;
   private StateManager stateManager;

   public GrenadeAttackAspect(CommonModContext modContext) {
      this.modContext = modContext;
   }

   public void setPermitManager(PermitManager permitManager) {
   }

   public void setStateManager(StateManager stateManager) {
      this.stateManager = stateManager;
      stateManager.in(this).change(GrenadeState.READY).to(GrenadeState.SAFETY_PING_OFF).withAction((i) -> {
         this.takeSafetyPinOff(i);
      }).when(this.hasSafetyPin).manual().in(this).change(GrenadeState.SAFETY_PING_OFF).to(GrenadeState.STRIKER_LEVER_RELEASED).withAction((i) -> {
         this.releaseStrikerLever(i);
      }).manual().in(this).change(GrenadeState.STRIKER_LEVER_RELEASED).to(GrenadeState.EXPLODED_IN_HANDS).withAction((i) -> {
         this.explode(i);
      }).when(explosionTimeoutExpired.and(isSmokeGrenade.negate())).automatic().in(this).change(GrenadeState.READY).to(GrenadeState.THROWING).when(this.hasSafetyPin.negate()).manual().in(this).change(GrenadeState.THROWING).to(GrenadeState.THROWN).withAction((i) -> {
         this.throwIt(i);
      }).when(throwingCompleted).automatic().in(this).change(GrenadeState.STRIKER_LEVER_RELEASED).to(GrenadeState.THROWING).manual().in(this).change(GrenadeState.THROWN).to(GrenadeState.READY).withAction((i) -> {
         this.reequip(i);
      }).when(reequipTimeoutExpired).automatic().in(this).change(GrenadeState.EXPLODED_IN_HANDS).to(GrenadeState.READY).withAction((i) -> {
         this.reequip(i);
      }).when(reequipTimeoutExpired).automatic();
   }

   private void explode(PlayerGrenadeInstance instance) {
      logger.debug("Exploding!");
      this.modContext.getChannel().getChannel().sendToServer(new GrenadeMessage(instance, 0L));
   }

   private void throwIt(PlayerGrenadeInstance instance) {
      logger.debug("Throwing with state " + instance.getState());
      long activationTimestamp;
      if(instance.getWeapon().isSmokeOnly()) {
         activationTimestamp = System.currentTimeMillis();
      } else if(instance.getWeapon().getExplosionTimeout() > 0) {
         activationTimestamp = instance.getActivationTimestamp();
      } else {
         activationTimestamp = -1L;
      }

      CompatibilityProvider.compatibility.playSound(instance.getPlayer(), instance.getWeapon().getThrowSound(), 1.0F, 1.0F);
      this.modContext.getChannel().getChannel().sendToServer(new GrenadeMessage(instance, activationTimestamp));
   }

   private void reequip(PlayerGrenadeInstance instance) {
      logger.debug("Reequipping");
   }

   private void takeSafetyPinOff(PlayerGrenadeInstance instance) {
      CompatibilityProvider.compatibility.playSound(instance.getPlayer(), instance.getWeapon().getSafetyPinOffSound(), 1.0F, 1.0F);
      logger.debug("Taking safety pin off");
   }

   private void releaseStrikerLever(PlayerGrenadeInstance instance) {
      logger.debug("Safety pin is off");
      instance.setActivationTimestamp(System.currentTimeMillis());
   }

   void onAttackButtonClick(EntityPlayer player, boolean throwingFar) {
      PlayerGrenadeInstance grenadeInstance = (PlayerGrenadeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerGrenadeInstance.class);
      if(grenadeInstance != null) {
         grenadeInstance.setThrowingFar(throwingFar);
         this.stateManager.changeStateFromAnyOf(this, grenadeInstance, allowedAttackFromStates, new GrenadeState[]{GrenadeState.SAFETY_PING_OFF, GrenadeState.THROWING});
      }

   }

   void onAttackButtonUp(EntityPlayer player, boolean throwingFar) {
      PlayerGrenadeInstance grenadeInstance = (PlayerGrenadeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerGrenadeInstance.class);
      if(grenadeInstance != null) {
         grenadeInstance.setThrowingFar(throwingFar);
         this.stateManager.changeStateFromAnyOf(this, grenadeInstance, allowedPinOffFromStates, new GrenadeState[]{GrenadeState.STRIKER_LEVER_RELEASED});
      }

   }

   void onUpdate(EntityPlayer player) {
      PlayerGrenadeInstance grenadeInstance = (PlayerGrenadeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerGrenadeInstance.class);
      if(grenadeInstance != null) {
         if(grenadeInstance.getState() == GrenadeState.STRIKER_LEVER_RELEASED && !grenadeInstance.getWeapon().isSmokeOnly() && System.currentTimeMillis() > grenadeInstance.getLastSafetyPinAlertTimestamp() + 1000L) {
            long remainingTimeUntilExplosion = (long)grenadeInstance.getWeapon().getExplosionTimeout() - (System.currentTimeMillis() - grenadeInstance.getActivationTimestamp());
            if(remainingTimeUntilExplosion < 0L) {
               remainingTimeUntilExplosion = 0L;
            }

            String message = CompatibilityProvider.compatibility.getLocalizedString("gui.grenadeExplodes", new Object[]{Integer.valueOf(Math.round((float)remainingTimeUntilExplosion / 1000.0F))});
            this.modContext.getStatusMessageCenter().addAlertMessage(message, 1, 1000L, 0L);
            grenadeInstance.setLastSafetyPinAlertTimestamp(System.currentTimeMillis());
         }

         this.stateManager.changeStateFromAnyOf(this, grenadeInstance, allowedUpdateFromStates, new GrenadeState[0]);
      }

   }

   public void serverThrowGrenade(EntityPlayer player, PlayerGrenadeInstance instance, long activationTimestamp) {
      logger.debug("Throwing grenade");
      boolean isSmokeGrenade = instance.getWeapon().isSmokeOnly();
      if(activationTimestamp == 0L && !isSmokeGrenade) {
         Explosion.createServerSideExplosion(this.modContext, CompatibilityProvider.compatibility.world(player), (Entity)null, player.posX, player.posY, player.posZ, instance.getWeapon().getExplosionStrength(), false, true);
      } else {
         float velocity;
         if(isSmokeGrenade) {
            velocity = instance.isThrowingFar()?instance.getWeapon().getFarVelocity():instance.getWeapon().getVelocity();
            EntitySmokeGrenade entityGrenade = (new EntitySmokeGrenade.Builder()).withThrower(player).withActivationTimestamp(activationTimestamp).withGrenade(instance.getWeapon()).withSmokeAmount(instance.getWeapon().getExplosionStrength()).withActivationDelay(0L).withActiveDuration(instance.getWeapon().getActiveDuration()).withVelocity(velocity).withGravityVelocity(instance.getWeapon().getGravityVelocity()).withRotationSlowdownFactor(instance.getWeapon().getRotationSlowdownFactor()).build(this.modContext);
            logger.debug("Throwing velocity {} ", new Object[]{Float.valueOf(velocity)});
            CompatibilityProvider.compatibility.spawnEntity(player, entityGrenade);
         } else {
            velocity = instance.isThrowingFar()?instance.getWeapon().getFarVelocity():instance.getWeapon().getVelocity();
            EntityGrenade entityGrenade1 = (new EntityGrenade.Builder()).withThrower(player).withActivationTimestamp(activationTimestamp).withGrenade(instance.getWeapon()).withExplosionStrength(instance.getWeapon().getExplosionStrength()).withExplosionTimeout((long)instance.getWeapon().getExplosionTimeout()).withVelocity(velocity).withGravityVelocity(instance.getWeapon().getGravityVelocity()).withRotationSlowdownFactor(instance.getWeapon().getRotationSlowdownFactor()).build(this.modContext);
            logger.debug("Throwing velocity {} ", new Object[]{Float.valueOf(velocity)});
            CompatibilityProvider.compatibility.spawnEntity(player, entityGrenade1);
         }
      }

      CompatibilityProvider.compatibility.consumeInventoryItemFromSlot(player, instance.getItemInventoryIndex());
   }

   int getParticleCount(float damage) {
      return (int)(-0.11D * (double)(damage - 30.0F) * (double)(damage - 30.0F) + 100.0D);
   }

   static {
      allowedAttackFromStates = new HashSet(Arrays.asList(new GrenadeState[]{GrenadeState.READY, GrenadeState.STRIKER_LEVER_RELEASED}));
      allowedPinOffFromStates = new HashSet(Arrays.asList(new GrenadeState[]{GrenadeState.SAFETY_PING_OFF}));
      allowedUpdateFromStates = new HashSet(Arrays.asList(new GrenadeState[]{GrenadeState.STRIKER_LEVER_RELEASED, GrenadeState.THROWING, GrenadeState.THROWN, GrenadeState.EXPLODED_IN_HANDS}));
   }
}
