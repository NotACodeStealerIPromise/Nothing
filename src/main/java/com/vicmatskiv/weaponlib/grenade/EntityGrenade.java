package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.Explosion;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleAxisAlignedBB;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTracing;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import com.vicmatskiv.weaponlib.grenade.AbstractEntityGrenade;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiPredicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityGrenade extends AbstractEntityGrenade {
   private static final Logger logger = LogManager.getLogger(EntityGrenade.class);
   private long explosionTimeout;
   private float explosionStrength;
   private long activationTimestamp;

   private EntityGrenade(ModContext modContext, ItemGrenade itemGrenade, EntityLivingBase thrower, float velocity, float gravityVelocity, float rotationSlowdownFactor) {
      super(modContext, itemGrenade, thrower, velocity, gravityVelocity, rotationSlowdownFactor);
   }

   public EntityGrenade(World world) {
      super(world);
   }

   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeLong(this.activationTimestamp);
      buffer.writeLong(this.explosionTimeout);
      buffer.writeFloat(this.explosionStrength);
   }

   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.activationTimestamp = buffer.readLong();
      this.explosionTimeout = buffer.readLong();
      this.explosionStrength = buffer.readFloat();
   }

   public void onGrenadeUpdate() {
      if(!CompatibilityProvider.compatibility.world(this).isRemote && this.explosionTimeout > 0L && System.currentTimeMillis() > this.activationTimestamp + this.explosionTimeout) {
         this.explode();
      }
   }

   public void onBounce(CompatibleRayTraceResult movingobjectposition) {
      if(this.explosionTimeout == -1L && !CompatibilityProvider.compatibility.world(this).isRemote) {
         this.explode();
      } else {
         super.onBounce(movingobjectposition);
      }

   }

   private void explode() {
      logger.debug("Exploding {}", new Object[]{this});
      Explosion.createServerSideExplosion(this.modContext, CompatibilityProvider.compatibility.world(this), this, this.posX, this.posY, this.posZ, this.explosionStrength, false, true);
      List nearbyEntities = CompatibilityProvider.compatibility.getEntitiesWithinAABBExcludingEntity(CompatibilityProvider.compatibility.world(this), this, CompatibilityProvider.compatibility.getBoundingBox(this).expand(5.0D, 5.0D, 5.0D));
      Float damageCoefficient = this.modContext.getConfigurationManager().getExplosions().getDamage();
      float effectiveRadius = this.itemGrenade.getEffectiveRadius() * damageCoefficient.floatValue();
      float fragmentDamage = this.itemGrenade.getFragmentDamage();
      float configuredFragmentCount = (float)this.itemGrenade.getFragmentCount() * damageCoefficient.floatValue();

      for(int i = 0; (float)i < configuredFragmentCount; ++i) {
         double x = (this.rand.nextDouble() - 0.5D) * 2.0D;
         double y = (this.rand.nextDouble() - 0.5D) * 2.0D;
         double z = (this.rand.nextDouble() - 0.5D) * 2.0D;
         double d2 = x * x + y * y + z * z;
         if(d2 == 0.0D) {
            logger.debug("Ignoring zero distance index {}", new Object[]{Integer.valueOf(i)});
         } else {
            double k = Math.sqrt((double)(effectiveRadius * effectiveRadius) / d2);
            double k2 = 0.1D;
            CompatibleVec3 cvec1 = new CompatibleVec3(this.posX + x * k2, this.posY + y * k2, this.posZ + z * k2);
            CompatibleVec3 cvec10 = new CompatibleVec3(this.posX + x * k2, this.posY + y * k2, this.posZ + z * k2);
            CompatibleVec3 cvec2 = new CompatibleVec3(this.posX + x * k, this.posY + y * k, this.posZ + z * k);
            BiPredicate isCollidable = (block, blockMetadata) -> {
               return CompatibilityProvider.compatibility.canCollideCheck(block, blockMetadata, false);
            };
            CompatibleRayTraceResult rayTraceResult = CompatibleRayTracing.rayTraceBlocks(CompatibilityProvider.compatibility.world(this), cvec1, cvec2, isCollidable);
            if(rayTraceResult != null) {
               cvec2 = CompatibleVec3.fromCompatibleVec3(rayTraceResult.getHitVec());
            }

            Iterator var24 = nearbyEntities.iterator();

            while(var24.hasNext()) {
               Object nearbyEntityObject = var24.next();
               Entity nearbyEntity = (Entity)nearbyEntityObject;
               if(nearbyEntity.canBeCollidedWith()) {
                  float f = 0.5F;
                  CompatibleAxisAlignedBB axisalignedbb = CompatibilityProvider.compatibility.expandEntityBoundingBox(nearbyEntity, (double)f, (double)f, (double)f);
                  CompatibleRayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(cvec10, cvec2);
                  if(movingobjectposition1 != null) {
                     double distanceToEntity = cvec10.distanceTo(movingobjectposition1.getHitVec());
                     float damageDistanceReductionFactor = (float)Math.abs(1.0D - distanceToEntity / (double)effectiveRadius);
                     logger.trace("Hit entity {} at distance {}, damage reduction {}", new Object[]{nearbyEntity, Double.valueOf(distanceToEntity), Float.valueOf(damageDistanceReductionFactor)});
                     nearbyEntity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), Math.max(0.1F, this.rand.nextFloat()) * fragmentDamage * damageDistanceReductionFactor);
                  }
               }
            }
         }
      }

      this.setDead();
   }

   public ItemGrenade getItemGrenade() {
      return this.itemGrenade;
   }

   // $FF: synthetic method
   EntityGrenade(ModContext x0, ItemGrenade x1, EntityLivingBase x2, float x3, float x4, float x5, Object x6) {
      this(x0, x1, x2, x3, x4, x5);
   }

   public static class Builder {
      private long explosionTimeout;
      private float explosionStrength;
      private long activationTimestamp;
      private EntityLivingBase thrower;
      private ItemGrenade itemGrenade;
      private float velocity = 1.0F;
      private float gravityVelocity = 0.06F;
      private float rotationSlowdownFactor = 0.99F;

      public EntityGrenade.Builder withActivationTimestamp(long activationTimestamp) {
         this.activationTimestamp = activationTimestamp;
         return this;
      }

      public EntityGrenade.Builder withExplosionTimeout(long explosionTimeout) {
         this.explosionTimeout = explosionTimeout;
         return this;
      }

      public EntityGrenade.Builder withThrower(EntityLivingBase thrower) {
         this.thrower = thrower;
         return this;
      }

      public EntityGrenade.Builder withExplosionStrength(float explosionStrength) {
         this.explosionStrength = explosionStrength;
         return this;
      }

      public EntityGrenade.Builder withGrenade(ItemGrenade itemGrenade) {
         this.itemGrenade = itemGrenade;
         return this;
      }

      public EntityGrenade.Builder withVelocity(float velocity) {
         this.velocity = velocity;
         return this;
      }

      public EntityGrenade.Builder withGravityVelocity(float gravityVelocity) {
         this.gravityVelocity = gravityVelocity;
         return this;
      }

      public EntityGrenade.Builder withRotationSlowdownFactor(float rotationSlowdownFactor) {
         this.rotationSlowdownFactor = rotationSlowdownFactor;
         return this;
      }

      public EntityGrenade build(ModContext modContext) {
         EntityGrenade entityGrenade = new EntityGrenade(modContext, this.itemGrenade, this.thrower, this.velocity, this.gravityVelocity, this.rotationSlowdownFactor);
         entityGrenade.activationTimestamp = this.activationTimestamp;
         entityGrenade.explosionTimeout = this.explosionTimeout;
         entityGrenade.explosionStrength = this.explosionStrength;
         entityGrenade.itemGrenade = this.itemGrenade;
         return entityGrenade;
      }
   }
}
