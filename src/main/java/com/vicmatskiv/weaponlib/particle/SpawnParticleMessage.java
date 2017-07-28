package com.vicmatskiv.weaponlib.particle;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import io.netty.buffer.ByteBuf;

public class SpawnParticleMessage implements CompatibleMessage {
   private double posX;
   private double posY;
   private double posZ;
   private double motionX;
   private double motionY;
   private double motionZ;
   private int count;
   private SpawnParticleMessage.ParticleType particleType;

   public SpawnParticleMessage() {
   }

   public SpawnParticleMessage(SpawnParticleMessage.ParticleType particleType, int count, double posX, double posY, double posZ) {
      this.particleType = particleType;
      this.count = count;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
   }

   public SpawnParticleMessage(SpawnParticleMessage.ParticleType particleType, int count, double posX, double posY, double posZ, double motionX, double motionY, double motionZ) {
      this.particleType = particleType;
      this.count = count;
      this.posX = posX;
      this.posY = posY;
      this.posZ = posZ;
      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
   }

   public void fromBytes(ByteBuf buf) {
      this.particleType = SpawnParticleMessage.ParticleType.values()[buf.readInt()];
      this.count = buf.readInt();
      this.posX = buf.readDouble();
      this.posY = buf.readDouble();
      this.posZ = buf.readDouble();
      if(this.particleType == SpawnParticleMessage.ParticleType.SMOKE_GRENADE_SMOKE) {
         this.motionX = buf.readDouble();
         this.motionY = buf.readDouble();
         this.motionZ = buf.readDouble();
      }

   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.particleType.ordinal());
      buf.writeInt(this.count);
      buf.writeDouble(this.posX);
      buf.writeDouble(this.posY);
      buf.writeDouble(this.posZ);
      if(this.particleType == SpawnParticleMessage.ParticleType.SMOKE_GRENADE_SMOKE) {
         buf.writeDouble(this.motionX);
         buf.writeDouble(this.motionY);
         buf.writeDouble(this.motionZ);
      }

   }

   public SpawnParticleMessage.ParticleType getParticleType() {
      return this.particleType;
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

   public int getCount() {
      return this.count;
   }

   public double getMotionX() {
      return this.motionX;
   }

   public double getMotionY() {
      return this.motionY;
   }

   public double getMotionZ() {
      return this.motionZ;
   }

   public static enum ParticleType {
      BLOOD,
      SHELL,
      SMOKE_GRENADE_SMOKE;
   }
}
