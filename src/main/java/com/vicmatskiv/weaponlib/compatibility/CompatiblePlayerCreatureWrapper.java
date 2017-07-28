package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.util.Session;
import net.minecraft.world.World;

public class CompatiblePlayerCreatureWrapper extends EntityClientPlayerMP {
   private EntityLivingBase entityLiving;
   private static Session session = new Session("1", "2", "3", "4");

   public CompatiblePlayerCreatureWrapper(Minecraft mc, World world) {
      super(mc, world, session, (NetHandlerPlayClient)null, (StatFileWriter)null);
   }

   public void setEntityLiving(EntityLivingBase entityLiving) {
      this.entityLiving = entityLiving;
      if(entityLiving != null) {
         this.updateCoordinates();
      }

   }

   public EntityLivingBase getEntityLiving() {
      return this.entityLiving;
   }

   public void updateCoordinates() {
      this.posX = this.entityLiving.posX;
      this.posY = this.entityLiving.posY;
      this.posZ = this.entityLiving.posZ;
      this.lastTickPosX = this.entityLiving.lastTickPosX;
      this.lastTickPosY = this.entityLiving.lastTickPosY;
      this.lastTickPosZ = this.entityLiving.lastTickPosZ;
      this.height = this.entityLiving.height;
      this.cameraYaw = this.entityLiving.rotationYaw;
      this.cameraPitch = this.entityLiving.cameraPitch;
      this.rotationYaw = this.entityLiving.rotationYaw;
      this.rotationPitch = this.entityLiving.rotationPitch;
      this.rotationYawHead = this.entityLiving.rotationYawHead;
      this.motionX = this.entityLiving.motionX;
      this.motionY = this.entityLiving.motionY;
      this.motionZ = this.entityLiving.motionZ;
      this.chunkCoordX = this.entityLiving.chunkCoordX;
      this.chunkCoordY = this.entityLiving.chunkCoordY;
      this.chunkCoordZ = this.entityLiving.chunkCoordZ;
      this.addedToChunk = this.entityLiving.addedToChunk;
      this.arrowHitTimer = this.entityLiving.arrowHitTimer;
      this.attackedAtYaw = this.entityLiving.attackedAtYaw;
      this.attackTime = this.entityLiving.attackTime;
      this.dimension = this.entityLiving.dimension;
      this.entityUniqueID = this.entityLiving.getUniqueID();
      this.limbSwing = this.entityLiving.limbSwing;
      this.limbSwingAmount = this.entityLiving.limbSwingAmount;
      this.height = this.entityLiving.height;
      this.moveForward = this.entityLiving.moveForward;
      this.ticksExisted = this.entityLiving.ticksExisted;
   }

   public float getFOVMultiplier() {
      return 0.5F;
   }
}
