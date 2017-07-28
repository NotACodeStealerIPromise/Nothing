package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import cpw.mods.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public abstract class CompatibleThrowableEntity extends EntityThrowable implements IEntityAdditionalSpawnData {
   public CompatibleThrowableEntity(World world) {
      super(world);
   }

   public CompatibleThrowableEntity(World par1World, EntityLivingBase player) {
      super(par1World, player);
   }

   public CompatibleThrowableEntity(World world, double x, double y, double z) {
      super(world, x, y, z);
   }

   protected final float func_70182_d() {
      return this.getVelocity();
   }

   protected final void onImpact(MovingObjectPosition position) {
      this.onImpact(CompatibleRayTraceResult.fromMovingObjectPosition(position));
   }

   protected abstract void onImpact(CompatibleRayTraceResult var1);

   public final void setThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float ignoredInaccuracy) {
      this.setCompatibleThrowableHeading(motionX, motionY, motionZ, velocity, this.getInaccuracy());
   }

   protected abstract void setCompatibleThrowableHeading(double var1, double var3, double var5, float var7, float var8);

   protected abstract float getInaccuracy();

   protected abstract float getVelocity();
}
