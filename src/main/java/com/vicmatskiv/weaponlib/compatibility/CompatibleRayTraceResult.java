package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEnumFacing;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import net.minecraft.entity.Entity;
import net.minecraft.util.MovingObjectPosition;

public class CompatibleRayTraceResult {
   private MovingObjectPosition position;
   private CompatibleBlockPos blockPos;
   private CompatibleVec3 hitVec;

   static CompatibleRayTraceResult fromMovingObjectPosition(MovingObjectPosition position) {
      return position == null?null:new CompatibleRayTraceResult(position);
   }

   private CompatibleRayTraceResult(MovingObjectPosition position) {
      this.position = position;
      this.init();
   }

   public CompatibleRayTraceResult(Entity entity) {
      this.position = new MovingObjectPosition(entity);
      this.init();
   }

   private void init() {
      this.blockPos = new CompatibleBlockPos(this.position.blockX, this.position.blockY, this.position.blockZ);
      this.hitVec = this.position.hitVec != null?new CompatibleVec3(this.position.hitVec):null;
   }

   protected MovingObjectPosition getPosition() {
      return this.position;
   }

   public Entity getEntityHit() {
      return this.position.entityHit;
   }

   public CompatibleRayTraceResult.Type getTypeOfHit() {
      CompatibleRayTraceResult.Type result = null;
      switch(null.$SwitchMap$net$minecraft$util$MovingObjectPosition$MovingObjectType[this.position.typeOfHit.ordinal()]) {
      case 1:
         result = CompatibleRayTraceResult.Type.BLOCK;
         break;
      case 2:
         result = CompatibleRayTraceResult.Type.ENTITY;
         break;
      case 3:
         result = CompatibleRayTraceResult.Type.MISS;
      }

      return result;
   }

   public int getBlockPosX() {
      return this.position.blockX;
   }

   public int getBlockPosY() {
      return this.position.blockY;
   }

   public int getBlockPosZ() {
      return this.position.blockZ;
   }

   public CompatibleEnumFacing getSideHit() {
      return CompatibleEnumFacing.values()[this.position.sideHit];
   }

   public CompatibleBlockPos getBlockPos() {
      return this.blockPos;
   }

   public CompatibleVec3 getHitVec() {
      return this.hitVec;
   }

   public void setSideHit(CompatibleEnumFacing sideHit) {
      this.position.sideHit = sideHit.ordinal();
   }

   public void setHitVec(CompatibleVec3 hitVec) {
      this.position.hitVec = hitVec.getVec();
      this.hitVec = hitVec;
   }

   public String toString() {
      return this.position.toString();
   }

   public static enum Type {
      MISS,
      BLOCK,
      ENTITY;
   }
}
