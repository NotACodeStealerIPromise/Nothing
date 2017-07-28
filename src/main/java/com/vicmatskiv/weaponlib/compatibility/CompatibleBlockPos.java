package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;

public class CompatibleBlockPos {
   private int blockPosX;
   private int blockPosY;
   private int blockPosZ;

   public CompatibleBlockPos(int blockPosX, int blockPosY, int blockPosZ) {
      this.blockPosX = blockPosX;
      this.blockPosY = blockPosY;
      this.blockPosZ = blockPosZ;
   }

   public CompatibleBlockPos(CompatibleVec3 pos) {
      this.blockPosX = (int)pos.getVec().xCoord;
      this.blockPosY = (int)pos.getVec().yCoord;
      this.blockPosZ = (int)pos.getVec().zCoord;
   }

   public int getBlockPosX() {
      return this.blockPosX;
   }

   public int getBlockPosY() {
      return this.blockPosY;
   }

   public int getBlockPosZ() {
      return this.blockPosZ;
   }

   public int hashCode() {
      boolean prime = true;
      byte result = 1;
      int result1 = 31 * result + this.blockPosX;
      result1 = 31 * result1 + this.blockPosY;
      result1 = 31 * result1 + this.blockPosZ;
      return result1;
   }

   public boolean equals(Object obj) {
      if(this == obj) {
         return true;
      } else if(obj == null) {
         return false;
      } else if(this.getClass() != obj.getClass()) {
         return false;
      } else {
         CompatibleBlockPos other = (CompatibleBlockPos)obj;
         return this.blockPosX != other.blockPosX?false:(this.blockPosY != other.blockPosY?false:this.blockPosZ == other.blockPosZ);
      }
   }
}
