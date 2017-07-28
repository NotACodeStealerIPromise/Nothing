package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.grenade.AbstractEntityGrenade;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessage;
import io.netty.buffer.ByteBuf;
import java.util.Iterator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntitySmokeGrenade extends AbstractEntityGrenade {
   private long activationDelay;
   private long activationTimestamp;
   private float smokeAmount;
   private long activeDuration;

   private EntitySmokeGrenade(ModContext modContext, ItemGrenade itemGrenade, EntityLivingBase thrower, float velocity, float gravityVelocity, float rotationSlowdownFactor) {
      super(modContext, itemGrenade, thrower, velocity, gravityVelocity, rotationSlowdownFactor);
   }

   public EntitySmokeGrenade(World world) {
      super(world);
   }

   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeLong(this.activationTimestamp);
      buffer.writeLong(this.activationDelay);
      buffer.writeLong(this.activeDuration);
      buffer.writeFloat(this.smokeAmount);
   }

   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.activationTimestamp = buffer.readLong();
      this.activationDelay = buffer.readLong();
      this.activeDuration = buffer.readLong();
      this.smokeAmount = buffer.readFloat();
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setLong("activationTimestamp", this.activationTimestamp);
      tagCompound.setLong("activationDelay", this.activationDelay);
      tagCompound.setLong("activeDuration", this.activeDuration);
      tagCompound.setFloat("smokeAmount", this.smokeAmount);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      this.activationTimestamp = tagCompound.getLong("activationTimestamp");
      this.activationDelay = tagCompound.getLong("activationDelay");
      this.activeDuration = tagCompound.getLong("activeDuration");
      this.smokeAmount = tagCompound.getFloat("smokeAmount");
   }

   public void onGrenadeUpdate() {
      if(this.modContext != null) {
         long timeRemaining = this.activationTimestamp + this.activationDelay + this.activeDuration - System.currentTimeMillis();
         if(this.activationDelay != -1L) {
            if(timeRemaining < 0L) {
               this.setDead();
            } else if(!CompatibilityProvider.compatibility.world(this).isRemote && timeRemaining <= this.activeDuration) {
               double f = 0.4D + Math.sin(3.141592653589793D * (1.0D - (double)timeRemaining / (double)this.activeDuration)) * 0.3D;
               if(this.rand.nextDouble() <= f) {
                  Iterator var5 = CompatibilityProvider.compatibility.world(this).playerEntities.iterator();

                  while(var5.hasNext()) {
                     Object o = var5.next();
                     EntityPlayer player = (EntityPlayer)o;
                     if(player.getDistanceSq(this.posX, this.posY, this.posZ) < 4096.0D) {
                        SpawnParticleMessage.ParticleType particleType = SpawnParticleMessage.ParticleType.SMOKE_GRENADE_SMOKE;
                        double movement = this.bounceCount > 0?0.007D:0.001D;
                        this.modContext.getChannel().getChannel().sendTo(new SpawnParticleMessage(particleType, 1, this.posX + this.rand.nextGaussian() / 7.0D, this.posY + this.rand.nextGaussian() / 10.0D, this.posZ + this.rand.nextGaussian() / 7.0D, this.rand.nextGaussian() * movement, this.rand.nextGaussian() * movement, this.rand.nextGaussian() * movement), (EntityPlayerMP)player);
                     }
                  }
               }
            }
         }

      }
   }

   public void onBounce(CompatibleRayTraceResult movingobjectposition) {
      if(this.activationDelay == -1L) {
         this.activationDelay = 0L;
         this.activationTimestamp = System.currentTimeMillis();
      } else {
         super.onBounce(movingobjectposition);
      }

   }

   public void onStop() {
      World world = CompatibilityProvider.compatibility.world(this);
      if(!world.isRemote && this.itemGrenade != null) {
         CompatibilityProvider.compatibility.playSound(CompatibilityProvider.compatibility.world(this), this.posX, this.posY, this.posZ, this.itemGrenade.getStopAfterThrowingSound(), 2.0F, (1.0F + (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F) * 0.7F);
      }

   }

   // $FF: synthetic method
   EntitySmokeGrenade(ModContext x0, ItemGrenade x1, EntityLivingBase x2, float x3, float x4, float x5, Object x6) {
      this(x0, x1, x2, x3, x4, x5);
   }

   public static class Builder {
      private long activationDelay;
      private long activationTimestamp;
      private long activeDuration;
      private float smokeAmount;
      private EntityLivingBase thrower;
      private ItemGrenade itemGrenade;
      private float velocity = 1.0F;
      private float gravityVelocity = 0.06F;
      private float rotationSlowdownFactor = 0.99F;

      public EntitySmokeGrenade.Builder withActivationTimestamp(long activationTimestamp) {
         this.activationTimestamp = activationTimestamp;
         return this;
      }

      public EntitySmokeGrenade.Builder withActivationDelay(long activationDelay) {
         this.activationDelay = activationDelay;
         return this;
      }

      public EntitySmokeGrenade.Builder withThrower(EntityLivingBase thrower) {
         this.thrower = thrower;
         return this;
      }

      public EntitySmokeGrenade.Builder withSmokeAmount(float smokeAmount) {
         this.smokeAmount = smokeAmount;
         return this;
      }

      public EntitySmokeGrenade.Builder withGrenade(ItemGrenade itemGrenade) {
         this.itemGrenade = itemGrenade;
         return this;
      }

      public EntitySmokeGrenade.Builder withVelocity(float velocity) {
         this.velocity = velocity;
         return this;
      }

      public EntitySmokeGrenade.Builder withGravityVelocity(float gravityVelocity) {
         this.gravityVelocity = gravityVelocity;
         return this;
      }

      public EntitySmokeGrenade.Builder withRotationSlowdownFactor(float rotationSlowdownFactor) {
         this.rotationSlowdownFactor = rotationSlowdownFactor;
         return this;
      }

      public EntitySmokeGrenade.Builder withActiveDuration(long activeDuration) {
         this.activeDuration = activeDuration;
         return this;
      }

      public EntitySmokeGrenade build(ModContext modContext) {
         EntitySmokeGrenade entityGrenade = new EntitySmokeGrenade(modContext, this.itemGrenade, this.thrower, this.velocity, this.gravityVelocity, this.rotationSlowdownFactor);
         entityGrenade.activationTimestamp = this.activationTimestamp;
         entityGrenade.activationDelay = this.activationDelay;
         entityGrenade.smokeAmount = this.smokeAmount;
         entityGrenade.activeDuration = this.activeDuration;
         return entityGrenade;
      }
   }
}
