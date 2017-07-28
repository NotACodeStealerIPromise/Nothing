package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import net.minecraft.util.AxisAlignedBB;

public class CompatibleAxisAlignedBB {
   private AxisAlignedBB boundingBox;

   CompatibleAxisAlignedBB(AxisAlignedBB boundingBox) {
      this.boundingBox = boundingBox;
   }

   public CompatibleAxisAlignedBB(CompatibleBlockPos blockPos) {
      this.boundingBox = AxisAlignedBB.getBoundingBox((double)blockPos.getBlockPosX(), (double)blockPos.getBlockPosY(), (double)blockPos.getBlockPosZ(), (double)(blockPos.getBlockPosX() + 1), (double)(blockPos.getBlockPosY() + 1), (double)(blockPos.getBlockPosZ() + 1));
   }

   public CompatibleAxisAlignedBB(double x1, double y1, double z1, double x2, double y2, double z2) {
      this.boundingBox = AxisAlignedBB.getBoundingBox(x1, y1, z1, x2, y2, z2);
   }

   AxisAlignedBB getBoundingBox() {
      return this.boundingBox;
   }

   public CompatibleRayTraceResult calculateIntercept(CompatibleVec3 vec3, CompatibleVec3 vec31) {
      return CompatibleRayTraceResult.fromMovingObjectPosition(this.boundingBox.calculateIntercept(vec3.getVec(), vec31.getVec()));
   }

   public CompatibleAxisAlignedBB addCoord(double x, double y, double z) {
      return new CompatibleAxisAlignedBB(this.boundingBox.addCoord(x, y, z));
   }

   public CompatibleAxisAlignedBB expand(double x, double y, double z) {
      return new CompatibleAxisAlignedBB(this.boundingBox.expand(x, y, z));
   }

   public CompatibleAxisAlignedBB offset(double x, double y, double z) {
      return new CompatibleAxisAlignedBB(this.boundingBox.offset(x, y, z));
   }

   public boolean intersectsWith(CompatibleAxisAlignedBB other) {
      return this.boundingBox.intersectsWith(other.boundingBox);
   }

   public double getAverageEdgeLength() {
      return this.boundingBox.getAverageEdgeLength();
   }
}
