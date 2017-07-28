package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.CommonModContext;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTargetPoint;
import com.vicmatskiv.weaponlib.melee.MeleeState;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.melee.TryAttackMessage;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessage;
import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MeleeAttackAspect implements Aspect {
   private static final Logger logger = LogManager.getLogger(MeleeAttackAspect.class);
   private static final long STUB_DURATION = 250L;
   private static final long HEAVY_STUB_DURATION = 250L;
   private static final long ALERT_TIMEOUT = 300L;
   private static Predicate attackTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() > instance.getStateUpdateTimestamp() + 250L;
   };
   private static Predicate heavyAttackTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() > instance.getStateUpdateTimestamp() + 250L;
   };
   private static Predicate attackCooldownTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() > instance.getLastAttackTimestamp() + instance.getWeapon().getAttackCooldownTimeout();
   };
   private static Predicate heavyAttackCooldownTimeoutExpired = (instance) -> {
      return System.currentTimeMillis() > instance.getLastAttackTimestamp() + instance.getWeapon().getHeavyAttackCooldownTimeout();
   };
   private static Predicate readyToStab = (instance) -> {
      return System.currentTimeMillis() > instance.getStateUpdateTimestamp() + instance.getWeapon().getPrepareStubTimeout();
   };
   private static Predicate readyToHeavyStab = (instance) -> {
      return System.currentTimeMillis() > instance.getStateUpdateTimestamp() + instance.getWeapon().getPrepareHeavyStubTimeout();
   };
   private static final Set allowedAttackFromStates;
   private static final Set allowedUpdateFromStates;
   private ModContext modContext;
   private StateManager stateManager;

   public MeleeAttackAspect(CommonModContext modContext) {
      this.modContext = modContext;
   }

   public void setPermitManager(PermitManager permitManager) {
   }

   public void setStateManager(StateManager stateManager) {
      this.stateManager = stateManager;
      stateManager.in(this).change(MeleeState.READY).to(MeleeState.ATTACKING).when(attackCooldownTimeoutExpired).manual().in(this).change(MeleeState.ATTACKING).to(MeleeState.ATTACKING_STABBING).withAction((i) -> {
         this.attack(i, false);
      }).when(readyToStab).automatic().in(this).change(MeleeState.ATTACKING_STABBING).to(MeleeState.READY).withAction((i) -> {
         i.setLastAttackTimestamp(System.currentTimeMillis());
      }).when(attackTimeoutExpired).automatic().in(this).change(MeleeState.READY).to(MeleeState.HEAVY_ATTACKING).when(heavyAttackCooldownTimeoutExpired).manual().in(this).change(MeleeState.HEAVY_ATTACKING).to(MeleeState.HEAVY_ATTACKING_STABBING).withAction((i) -> {
         this.attack(i, true);
      }).when(readyToHeavyStab).automatic().in(this).change(MeleeState.HEAVY_ATTACKING_STABBING).to(MeleeState.READY).withAction((i) -> {
         i.setLastAttackTimestamp(System.currentTimeMillis());
      }).when(heavyAttackTimeoutExpired).automatic();
   }

   void onAttackButtonClick(EntityPlayer player) {
      PlayerMeleeInstance weaponInstance = (PlayerMeleeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMeleeInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedAttackFromStates, new MeleeState[]{MeleeState.ATTACKING, MeleeState.ALERT});
      }

   }

   void onHeavyAttackButtonClick(EntityPlayer player) {
      PlayerMeleeInstance weaponInstance = (PlayerMeleeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMeleeInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedAttackFromStates, new MeleeState[]{MeleeState.HEAVY_ATTACKING, MeleeState.ALERT});
      }

   }

   void onUpdate(EntityPlayer player) {
      PlayerMeleeInstance weaponInstance = (PlayerMeleeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMeleeInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeStateFromAnyOf(this, weaponInstance, allowedUpdateFromStates, new MeleeState[0]);
      }

   }

   private void cannotAttack(PlayerMeleeInstance meleeInstance) {
      this.modContext.getStatusMessageCenter().addAlertMessage(CompatibilityProvider.compatibility.getLocalizedString("gui.coolingDown", new Object[0]), 2, 200L, 100L);
      CompatibilityProvider.compatibility.playSound(meleeInstance.getPlayer(), this.modContext.getNoAmmoSound(), 1.0F, 1.0F);
   }

   private void attack(PlayerMeleeInstance meleeInstance, boolean isHeavyAttack) {
      CompatibleRayTraceResult objectMouseOver = CompatibilityProvider.compatibility.getObjectMouseOver();
      if(objectMouseOver != null) {
         EntityPlayer player = CompatibilityProvider.compatibility.clientPlayer();
         World world = CompatibilityProvider.compatibility.world(player);
         CompatibilityProvider.compatibility.playSound(player, isHeavyAttack?meleeInstance.getWeapon().getHeavyAtackSound():meleeInstance.getWeapon().getLightAtackSound(), 1.0F, 1.0F);
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$compatibility$CompatibleRayTraceResult$Type[objectMouseOver.getTypeOfHit().ordinal()]) {
         case 1:
            this.attackEntity(objectMouseOver.getEntityHit(), player, meleeInstance, isHeavyAttack);
            break;
         case 2:
            if(!CompatibilityProvider.compatibility.isAirBlock(world, objectMouseOver.getBlockPos())) {
               CompatibilityProvider.compatibility.clickBlock(objectMouseOver.getBlockPos(), objectMouseOver.getSideHit());
            }
         }
      }

   }

   private void attackEntity(Entity entity, EntityPlayer player, PlayerMeleeInstance instance, boolean isHeavyAttack) {
      this.modContext.getChannel().getChannel().sendToServer(new TryAttackMessage(instance, entity, isHeavyAttack));
      entity.attackEntityFrom(DamageSource.causePlayerDamage(player), instance.getWeapon().getDamage(isHeavyAttack));
   }

   public void serverAttack(EntityPlayer player, PlayerMeleeInstance instance, Entity entity, boolean isHeavyAttack) {
      logger.debug("Player {} hits {} with {} in state {} with damage {}", new Object[]{player, entity, instance, instance.getState(), Float.valueOf(instance.getWeapon().getDamage(isHeavyAttack))});
      float damage = instance.getWeapon().getDamage(isHeavyAttack);
      entity.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
      CompatibleTargetPoint point = new CompatibleTargetPoint(entity.dimension, entity.posX, entity.posY, entity.posZ, 100.0D);
      double motionX = entity.posX - player.posX;
      double motionY = entity.posY - player.posY;
      double motionZ = entity.posZ - player.posZ;
      int count = this.getParticleCount(damage);
      logger.debug("Generating {} particle(s) per damage {}", new Object[]{Integer.valueOf(count), Float.valueOf(damage)});
      this.modContext.getChannel().sendToAllAround(new SpawnParticleMessage(SpawnParticleMessage.ParticleType.BLOOD, count, entity.posX - motionX / 2.0D, entity.posY - motionY / 2.0D, entity.posZ - motionZ / 2.0D), point);
   }

   int getParticleCount(float damage) {
      return (int)(-0.11D * (double)(damage - 30.0F) * (double)(damage - 30.0F) + 100.0D);
   }

   static {
      allowedAttackFromStates = new HashSet(Arrays.asList(new MeleeState[]{MeleeState.READY}));
      allowedUpdateFromStates = new HashSet(Arrays.asList(new MeleeState[]{MeleeState.ATTACKING, MeleeState.HEAVY_ATTACKING, MeleeState.ATTACKING_STABBING, MeleeState.HEAVY_ATTACKING_STABBING, MeleeState.ALERT}));
   }
}
