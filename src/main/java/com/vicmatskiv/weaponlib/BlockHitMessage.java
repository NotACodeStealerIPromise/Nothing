package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.compatibility.CompatibleEnumFacing;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import io.netty.buffer.ByteBuf;

public class BlockHitMessage implements CompatibleMessage {
   private int posX;
   private int posY;
   private int posZ;
   private int enumFacing;

   public BlockHitMessage() {
   }

   public BlockHitMessage(int posX, int posY, int posZ, CompatibleEnumFacing enumFacing) {
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.enumFacing = enumFacing.ordinal();
   }

   public void fromBytes(ByteBuf buf) {
      this.posX = buf.readInt();
      this.posY = buf.readInt();
      this.posZ = buf.readInt();
      this.enumFacing = buf.readInt();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.posX);
      buf.writeInt(this.posY);
      buf.writeInt(this.posZ);
      buf.writeInt(this.enumFacing);
   }

   public int getPosX() {
      return this.posX;
   }

   public int getPosY() {
      return this.posY;
   }

   public int getPosZ() {
      return this.posZ;
   }

   public CompatibleEnumFacing getSideHit() {
      return CompatibleEnumFacing.values()[this.enumFacing];
   }
}
