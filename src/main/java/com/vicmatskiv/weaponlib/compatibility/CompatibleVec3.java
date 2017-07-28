package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.util.Vec3;

public class CompatibleVec3 {
   private Vec3 vec;

   public CompatibleVec3(Vec3 vec) {
      this.vec = vec;
   }

   public CompatibleVec3(double posX, double posY, double posZ) {
      this.vec = Vec3.createVectorHelper(posX, posY, posZ);
   }

   public static CompatibleVec3 fromCompatibleVec3(CompatibleVec3 otherVec) {
      return otherVec == null?null:new CompatibleVec3(Vec3.createVectorHelper(otherVec.vec.xCoord, otherVec.vec.yCoord, otherVec.vec.zCoord));
   }

   public Vec3 getVec() {
      return this.vec;
   }

   public double getXCoord() {
      return this.vec.xCoord;
   }

   public double getYCoord() {
      return this.vec.yCoord;
   }

   public double getZCoord() {
      return this.vec.zCoord;
   }

   public double distanceTo(CompatibleVec3 otherVec) {
      return this.vec.distanceTo(otherVec.vec);
   }
}
