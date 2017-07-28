package com.vicmatskiv.weaponlib;

import com.google.common.collect.Lists;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExplosionMessage implements CompatibleMessage {
   private double posX;
   private double posY;
   private double posZ;
   private float strength;
   private List affectedBlockPositions;
   private float motionX;
   private float motionY;
   private float motionZ;

   public ExplosionMessage() {
   }

   public ExplosionMessage(double xIn, double yIn, double zIn, float strengthIn, List affectedBlockPositionsIn, CompatibleVec3 motion) {
      this.posX = xIn;
      this.posY = yIn;
      this.posZ = zIn;
      this.strength = strengthIn;
      this.affectedBlockPositions = Lists.newArrayList(affectedBlockPositionsIn);
      if(motion != null) {
         this.motionX = (float)motion.getXCoord();
         this.motionY = (float)motion.getYCoord();
         this.motionZ = (float)motion.getZCoord();
      }

   }

   public double getPosX() {
      return this.posX;
   }

   public double getPosY() {
      return this.posY;
   }

   public double getPosZ() {
      return this.posZ;
   }

   public float getMotionX() {
      return this.motionX;
   }

   public float getMotionY() {
      return this.motionY;
   }

   public float getMotionZ() {
      return this.motionZ;
   }

   public float getStrength() {
      return this.strength;
   }

   public List getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public void fromBytes(ByteBuf buf) {
      this.posX = (double)buf.readFloat();
      this.posY = (double)buf.readFloat();
      this.posZ = (double)buf.readFloat();
      this.strength = buf.readFloat();
      int i = buf.readInt();
      this.affectedBlockPositions = new ArrayList(i);
      int j = (int)this.posX;
      int k = (int)this.posY;
      int l = (int)this.posZ;

      for(int i1 = 0; i1 < i; ++i1) {
         int j1 = buf.readByte() + j;
         int k1 = buf.readByte() + k;
         int l1 = buf.readByte() + l;
         this.affectedBlockPositions.add(new CompatibleBlockPos(j1, k1, l1));
      }

      this.motionX = buf.readFloat();
      this.motionY = buf.readFloat();
      this.motionZ = buf.readFloat();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeFloat((float)this.posX);
      buf.writeFloat((float)this.posY);
      buf.writeFloat((float)this.posZ);
      buf.writeFloat(this.strength);
      buf.writeInt(this.affectedBlockPositions.size());
      int i = (int)this.posX;
      int j = (int)this.posY;
      int k = (int)this.posZ;
      Iterator var5 = this.affectedBlockPositions.iterator();

      while(var5.hasNext()) {
         CompatibleBlockPos blockpos = (CompatibleBlockPos)var5.next();
         int l = blockpos.getBlockPosX() - i;
         int i1 = blockpos.getBlockPosY() - j;
         int j1 = blockpos.getBlockPosZ() - k;
         buf.writeByte(l);
         buf.writeByte(i1);
         buf.writeByte(j1);
      }

      buf.writeFloat(this.motionX);
      buf.writeFloat(this.motionY);
      buf.writeFloat(this.motionZ);
   }
}
