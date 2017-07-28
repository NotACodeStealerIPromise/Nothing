package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Contextual;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleAxisAlignedBB;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleIEntityAdditionalSpawnData;
import com.vicmatskiv.weaponlib.compatibility.CompatibleIThrowableEntity;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMathHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTracing;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import io.netty.buffer.ByteBuf;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityBounceable extends Entity implements Contextual, CompatibleIEntityAdditionalSpawnData, CompatibleIThrowableEntity {
   private static final Logger logger = LogManager.getLogger(EntityBounceable.class);
   private static final int VELOCITY_HISTORY_SIZE = 10;
   private static final double STOP_THRESHOLD = 0.001D;
   private static final int MAX_TICKS = 2000;
   protected ModContext modContext;
   private float gravityVelocity;
   private float slowdownFactor = 0.5F;
   private int ticksInAir;
   private EntityLivingBase thrower;
   protected int bounceCount;
   private float initialYaw;
   private float initialPitch;
   private float xRotation;
   private float yRotation;
   private float zRotation;
   private float xRotationChange;
   private float yRotationChange;
   private float zRotationChange;
   private float rotationSlowdownFactor = 0.99F;
   private float maxRotationChange = 20.0F;
   private boolean stopped;
   private Queue velocityHistory = new ArrayDeque(10);

   public EntityBounceable(ModContext modContext, World world, EntityLivingBase thrower, float velocity, float gravityVelocity, float rotationSlowdownFactor) {
      super(world);
      this.modContext = modContext;
      this.thrower = thrower;
      this.gravityVelocity = gravityVelocity;
      this.rotationSlowdownFactor = rotationSlowdownFactor;
      this.setSize(0.3F, 0.3F);
      this.setLocationAndAngles(thrower.posX, thrower.posY + (double)thrower.getEyeHeight(), thrower.posZ, thrower.rotationYaw, thrower.rotationPitch);
      this.posX -= (double)(CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.posY -= 0.10000000149011612D;
      this.posZ -= (double)(CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = 0.4F;
      this.motionX = (double)(-CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
      this.motionZ = (double)(CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
      this.motionY = (double)(-CompatibleMathHelper.sin((this.rotationPitch + 0.0F) / 180.0F * 3.1415927F) * f);
      this.initialYaw = this.rotationYaw;
      this.initialPitch = this.rotationPitch;
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, velocity, 10.0F);
      logger.debug("Throwing with position {}{}{}, rotation pitch {}, velocity {}, {}, {}", new Object[]{Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ), Float.valueOf(this.rotationPitch), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
   }

   public void setThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float inaccuracy) {
      float f2 = CompatibleMathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
      motionX /= (double)f2;
      motionY /= (double)f2;
      motionZ /= (double)f2;
      motionX += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      motionY += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      motionZ += this.rand.nextGaussian() * 0.007499999832361937D * (double)inaccuracy;
      motionX *= (double)velocity;
      motionY *= (double)velocity;
      motionZ *= (double)velocity;
      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
      float f3 = CompatibleMathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
      this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / 3.141592653589793D);
      this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(motionY, (double)f3) * 180.0D / 3.141592653589793D);
   }

   public EntityBounceable(World world) {
      super(world);
      this.setRotations();
   }

   private void setRotations() {
      this.xRotationChange = this.maxRotationChange * (float)this.rand.nextGaussian();
      this.yRotationChange = this.maxRotationChange * (float)this.rand.nextGaussian();
      this.zRotationChange = this.maxRotationChange * (float)this.rand.nextGaussian();
   }

   public EntityLivingBase getThrower() {
      return this.thrower;
   }

   public void setThrower(Entity thrower) {
      this.thrower = (EntityLivingBase)thrower;
   }

   public void onUpdate() {
      if(!CompatibilityProvider.compatibility.world(this).isRemote && this.ticksExisted > 2000) {
         this.setDead();
      } else {
         this.xRotation += this.xRotationChange;
         this.yRotation += this.yRotationChange;
         this.zRotation += this.zRotationChange;
         this.xRotationChange *= this.rotationSlowdownFactor;
         this.yRotationChange *= this.rotationSlowdownFactor;
         this.zRotationChange *= this.rotationSlowdownFactor;
         this.lastTickPosX = this.posX;
         this.lastTickPosY = this.posY;
         this.lastTickPosZ = this.posZ;
         super.onUpdate();
         ++this.ticksInAir;
         if(!this.stopped) {
            CompatibleVec3 vec3 = new CompatibleVec3(this.posX, this.posY, this.posZ);
            CompatibleVec3 vec31 = new CompatibleVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            CompatibleRayTraceResult movingobjectposition = CompatibleRayTracing.rayTraceBlocks(CompatibilityProvider.compatibility.world(this), vec3, vec31, (block, blockMetadata) -> {
               return this.canCollideWithBlock(block, blockMetadata);
            });
            vec3 = new CompatibleVec3(this.posX, this.posY, this.posZ);
            vec31 = new CompatibleVec3(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            if(movingobjectposition != null) {
               vec31 = CompatibleVec3.fromCompatibleVec3(movingobjectposition.getHitVec());
            }

            if(this.thrower != null) {
               Entity motionSquared = null;
               List f2 = CompatibilityProvider.compatibility.getEntitiesWithinAABBExcludingEntity(CompatibilityProvider.compatibility.world(this), this, CompatibilityProvider.compatibility.getBoundingBox(this).addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
               double currentGravityVelocity = 0.0D;
               EntityLivingBase f4 = this.getThrower();
               CompatibleRayTraceResult entityMovingObjectPosition = null;

               for(int j = 0; j < f2.size(); ++j) {
                  Entity entity1 = (Entity)f2.get(j);
                  if(entity1.canBeCollidedWith() && (entity1 != f4 || this.ticksInAir >= 5)) {
                     float f = 0.3F;
                     CompatibleAxisAlignedBB axisalignedbb = CompatibilityProvider.compatibility.expandEntityBoundingBox(entity1, (double)f, (double)f, (double)f);
                     CompatibleRayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec3, vec31);
                     if(movingobjectposition1 != null) {
                        double d1 = vec3.distanceTo(movingobjectposition1.getHitVec());
                        if(d1 < currentGravityVelocity || currentGravityVelocity == 0.0D) {
                           motionSquared = entity1;
                           entityMovingObjectPosition = movingobjectposition1;
                           currentGravityVelocity = d1;
                        }
                     }
                  }
               }

               if(motionSquared != null) {
                  movingobjectposition = new CompatibleRayTraceResult(motionSquared);
                  movingobjectposition.setSideHit(entityMovingObjectPosition.getSideHit());
                  movingobjectposition.setHitVec(entityMovingObjectPosition.getHitVec());
               }
            }

            logger.trace("Ori position to {}, {}, {}, motion {} {} {} ", new Object[]{Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
            if(movingobjectposition != null && (movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK || movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.ENTITY)) {
               logger.trace("Hit {}, vec set to {}, {}, {}", new Object[]{movingobjectposition.getTypeOfHit(), Double.valueOf(movingobjectposition.getHitVec().getXCoord()), Double.valueOf(movingobjectposition.getHitVec().getYCoord()), Double.valueOf(movingobjectposition.getHitVec().getZCoord())});
               logger.trace("Before bouncing {}, side {}, motion set to {}, {}, {}", new Object[]{Integer.valueOf(this.bounceCount), movingobjectposition.getSideHit(), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
               this.posX = movingobjectposition.getHitVec().getXCoord();
               this.posY = movingobjectposition.getHitVec().getYCoord();
               this.posZ = movingobjectposition.getHitVec().getZCoord();
               switch(null.$SwitchMap$com$vicmatskiv$weaponlib$compatibility$CompatibleEnumFacing[movingobjectposition.getSideHit().ordinal()]) {
               case 1:
                  this.motionY = -this.motionY;
                  this.posY += this.motionY;
                  break;
               case 2:
                  this.motionY = -this.motionY;
                  break;
               case 3:
                  this.motionZ = -this.motionZ;
                  this.posZ += this.motionZ;
                  break;
               case 4:
                  this.motionZ = -this.motionZ;
                  break;
               case 5:
                  this.motionX = -this.motionX;
                  this.posX += this.motionX;
                  break;
               case 6:
                  this.motionX = -this.motionX;
               }

               this.setPosition(this.posX, this.posY, this.posZ);
               if(movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.ENTITY) {
                  this.avoidEntityCollisionAfterBounce(movingobjectposition);
               } else if(movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK) {
                  this.avoidBlockCollisionAfterBounce(movingobjectposition);
               }

               logger.trace("After bouncing {}  motion set to {}, {}, {}", new Object[]{Integer.valueOf(this.bounceCount), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
               this.onBounce(movingobjectposition);
               ++this.bounceCount;
               if(this.isDead) {
                  return;
               }
            } else {
               this.posX += this.motionX;
               this.posY += this.motionY;
               this.posZ += this.motionZ;
            }

            this.setPosition(this.posX, this.posY, this.posZ);
            float var17 = CompatibleMathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592653589793D);

            for(this.rotationPitch = (float)(Math.atan2(this.motionY, (double)var17) * 180.0D / 3.141592653589793D); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) {
               ;
            }

            while(this.rotationPitch - this.prevRotationPitch >= 180.0F) {
               this.prevRotationPitch += 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw < -180.0F) {
               this.prevRotationYaw -= 360.0F;
            }

            while(this.rotationYaw - this.prevRotationYaw >= 180.0F) {
               this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var18 = 0.99F;
            float var19 = this.getGravityVelocity();
            if(this.isInWater()) {
               for(int i = 0; i < 4; ++i) {
                  float var20 = 0.25F;
                  CompatibilityProvider.compatibility.spawnParticle(CompatibilityProvider.compatibility.world(this), "bubble", this.posX - this.motionX * (double)var20, this.posY - this.motionY * (double)var20, this.posZ - this.motionZ * (double)var20, this.motionX, this.motionY, this.motionZ);
               }

               var18 = 0.8F;
            }

            if(movingobjectposition != null && (movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK || movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.ENTITY)) {
               var18 = this.slowdownFactor;
               this.rotationSlowdownFactor *= this.slowdownFactor * 1.5F;
            }

            this.motionX *= (double)var18;
            this.motionY *= (double)var18;
            this.motionZ *= (double)var18;
            this.recordVelocityHistory();
            if(!this.velocityHistory.stream().anyMatch((v) -> {
               return v.doubleValue() > 0.001D;
            })) {
               this.motionX = this.motionY = this.motionZ = 0.0D;
               this.stopped = true;
               logger.trace("Stopping {}", new Object[]{this});
               this.onStop();
            } else {
               this.motionY -= (double)var19;
            }

            logger.trace("Set position to {}, {}, {}, motion {} {} {} ", new Object[]{Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
         }
      }
   }

   public void onStop() {
   }

   public void onBounce(CompatibleRayTraceResult movingobjectposition) {
   }

   private void avoidBlockCollisionAfterBounce(CompatibleRayTraceResult movingobjectposition) {
      if(movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK) {
         double dX = Math.signum(this.motionX) * 0.05D;
         double dY = Math.signum(this.motionY) * 0.05D;
         double dZ = Math.signum(this.motionZ) * 0.05D;

         for(int i = 0; i < 10; ++i) {
            double projectedXPos = this.posX + dX * (double)i;
            double projectedYPos = this.posY + dY * (double)i;
            double projectedZPos = this.posZ + dZ * (double)i;
            CompatibleVec3 projectedPos = new CompatibleVec3(projectedXPos, projectedYPos, projectedZPos);
            CompatibleBlockPos blockPos = new CompatibleBlockPos(projectedPos);
            CompatibleAxisAlignedBB projectedEntityBoundingBox = CompatibilityProvider.compatibility.getBoundingBox(this).offset(dX * (double)i, dY * (double)i, dZ * (double)i);
            if(CompatibilityProvider.compatibility.isAirBlock(CompatibilityProvider.compatibility.world(this), blockPos) || !(new CompatibleAxisAlignedBB(blockPos)).intersectsWith(projectedEntityBoundingBox)) {
               this.posX = projectedXPos;
               this.posY = projectedYPos;
               this.posZ = projectedZPos;
               logger.trace("Found non-intercepting post-bounce position on iteration {}", new Object[]{Integer.valueOf(i)});
               break;
            }
         }

      }
   }

   private void avoidEntityCollisionAfterBounce(CompatibleRayTraceResult movingobjectposition) {
      if(movingobjectposition.getEntityHit() != null) {
         this.slowdownFactor = 0.3F;
         double dX = Math.signum(this.motionX) * 0.05D;
         double dY = Math.signum(this.motionY) * 0.05D;
         double dZ = Math.signum(this.motionZ) * 0.05D;
         float f = 0.3F;
         CompatibleAxisAlignedBB axisalignedbb = CompatibilityProvider.compatibility.getBoundingBox(movingobjectposition.getEntityHit()).expand((double)f, (double)f, (double)f);
         CompatibleRayTraceResult intercept = movingobjectposition;

         for(int i = 0; i < 10; ++i) {
            CompatibleVec3 currentPos = new CompatibleVec3(this.posX + dX * (double)i, this.posY + dY * (double)i, this.posZ + dY * (double)i);
            CompatibleVec3 projectedPos = new CompatibleVec3(this.posX + dX * (double)(i + 1), this.posY + dY * (double)(i + 1), this.posZ + dZ * (double)(i + 1));
            intercept = axisalignedbb.calculateIntercept(currentPos, projectedPos);
            if(intercept == null) {
               CompatibleBlockPos blockPos = new CompatibleBlockPos(projectedPos);
               CompatibleBlockState blockState;
               if((blockState = CompatibilityProvider.compatibility.getBlockAtPosition(CompatibilityProvider.compatibility.world(this), blockPos)) != null && !CompatibilityProvider.compatibility.isAirBlock(blockState)) {
                  logger.debug("Found non-intercept position colliding with block {}", new Object[]{blockState});
                  intercept = movingobjectposition;
               } else {
                  this.posX = projectedPos.getXCoord();
                  this.posY = projectedPos.getYCoord();
                  this.posZ = projectedPos.getZCoord();
               }
               break;
            }
         }

         if(intercept != null) {
            logger.debug("Could not find non-intercept position after bounce");
         }

      }
   }

   protected float getGravityVelocity() {
      return this.gravityVelocity;
   }

   protected void entityInit() {
   }

   protected void readEntityFromNBT(NBTTagCompound tagCompound) {
   }

   protected void writeEntityToNBT(NBTTagCompound tagCompound) {
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeInt(this.thrower != null?this.thrower.getEntityId():-1);
      buffer.writeDouble(this.posX);
      buffer.writeDouble(this.posY);
      buffer.writeDouble(this.posZ);
      buffer.writeDouble(this.motionX);
      buffer.writeDouble(this.motionY);
      buffer.writeDouble(this.motionZ);
      buffer.writeFloat(this.gravityVelocity);
      buffer.writeFloat(this.rotationSlowdownFactor);
      buffer.writeFloat(this.initialYaw);
      buffer.writeFloat(this.initialPitch);
   }

   public void readSpawnData(ByteBuf buffer) {
      int entityId = buffer.readInt();
      if(this.thrower == null && entityId >= 0) {
         Entity entity = CompatibilityProvider.compatibility.world(this).getEntityByID(entityId);
         if(entity instanceof EntityLivingBase) {
            this.thrower = (EntityPlayer)entity;
         }
      }

      this.posX = buffer.readDouble();
      this.posY = buffer.readDouble();
      this.posZ = buffer.readDouble();
      this.motionX = buffer.readDouble();
      this.motionY = buffer.readDouble();
      this.motionZ = buffer.readDouble();
      this.gravityVelocity = buffer.readFloat();
      this.rotationSlowdownFactor = buffer.readFloat();
      this.initialYaw = buffer.readFloat();
      this.initialPitch = buffer.readFloat();
      this.setPosition(this.posX, this.posY, this.posZ);
      logger.debug("Restoring with position {}{}{}, rotation pitch {}, velocity {}, {}, {}", new Object[]{Double.valueOf(this.posX), Double.valueOf(this.posY), Double.valueOf(this.posZ), Float.valueOf(this.rotationPitch), Double.valueOf(this.motionX), Double.valueOf(this.motionY), Double.valueOf(this.motionZ)});
   }

   public float getXRotation() {
      return this.xRotation;
   }

   public float getYRotation() {
      return this.yRotation - this.initialYaw - 90.0F;
   }

   public float getZRotation() {
      return this.zRotation;
   }

   public boolean canCollideWithBlock(Block block, CompatibleBlockState metadata) {
      return CompatibilityProvider.compatibility.canCollideCheck(block, metadata, false);
   }

   private void recordVelocityHistory() {
      double velocity = this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ;
      this.velocityHistory.add(Double.valueOf(velocity));
      if(this.velocityHistory.size() > 10) {
         this.velocityHistory.poll();
      }

   }

   public void setContext(ModContext modContext) {
      if(this.modContext == null) {
         this.modContext = modContext;
      }

   }
}
