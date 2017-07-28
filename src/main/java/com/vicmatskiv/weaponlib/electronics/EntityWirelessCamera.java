package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMathHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleThrowableEntity;
import com.vicmatskiv.weaponlib.electronics.ItemWirelessCamera;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import com.vicmatskiv.weaponlib.tracking.SyncPlayerEntityTrackerMessage;
import com.vicmatskiv.weaponlib.tracking.TrackableEntity;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityWirelessCamera extends CompatibleThrowableEntity {
   private static final Logger logger = LogManager.getLogger(EntityWirelessCamera.class);
   private ModContext modContext;
   private ItemWirelessCamera itemWirelessCamera;
   private long timestamp;
   private long duration;

   public EntityWirelessCamera(ModContext modContext, World world, EntityPlayer player, ItemWirelessCamera itemWirelessCamera, long duration) {
      super(world, player);
      this.timestamp = System.currentTimeMillis();
      this.duration = duration;
      this.modContext = modContext;
      this.itemWirelessCamera = itemWirelessCamera;
      this.setSize(0.25F, 0.25F);
      this.setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);
      double var10001 = this.posX;
      CompatibilityProvider.compatibility.getMathHelper();
      this.posX = var10001 - (double)(CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.posY -= 0.10000000149011612D;
      var10001 = this.posZ;
      CompatibilityProvider.compatibility.getMathHelper();
      this.posZ = var10001 - (double)(CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * 0.16F);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = 0.4F;
      CompatibilityProvider.compatibility.getMathHelper();
      float var9 = -CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F);
      CompatibilityProvider.compatibility.getMathHelper();
      this.motionX = (double)(var9 * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
      CompatibilityProvider.compatibility.getMathHelper();
      var9 = CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F);
      CompatibilityProvider.compatibility.getMathHelper();
      this.motionZ = (double)(var9 * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * f);
      float pitchOffset = 0.0F;
      CompatibilityProvider.compatibility.getMathHelper();
      this.motionY = (double)(-CompatibleMathHelper.sin((this.rotationPitch + pitchOffset) / 180.0F * 3.1415927F) * f);
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, 1.5F, 0.0F);
   }

   public EntityWirelessCamera(World world, EntityLivingBase player) {
      super(world, player);
   }

   public EntityWirelessCamera(World world) {
      super(world);
   }

   protected void onImpact(CompatibleRayTraceResult rayTraceResult) {
      Entity entityHit = rayTraceResult.getEntityHit();
      logger.debug("Player {} hit entity {}", new Object[]{this.getThrower(), rayTraceResult.getEntityHit()});
      boolean hit = false;
      if(entityHit != null && this.getThrower() instanceof EntityPlayer) {
         String displayName = "";
         if(entityHit instanceof EntityPlayer) {
            displayName = CompatibilityProvider.compatibility.getDisplayName((EntityPlayer)entityHit);
         } else if(entityHit instanceof EntityLivingBase) {
            displayName = EntityList.getEntityString(entityHit);
         }

         if(!CompatibilityProvider.compatibility.world(this).isRemote) {
            logger.debug("Server hit entity uuid {}", new Object[]{rayTraceResult.getEntityHit().getPersistentID()});
            PlayerEntityTracker tracker = PlayerEntityTracker.getTracker((EntityPlayer)this.getThrower());
            if(tracker != null) {
               hit = true;
               tracker.addTrackableEntity(new TrackableEntity(entityHit, this.timestamp, this.duration));
               this.modContext.getChannel().getChannel().sendTo(new SyncPlayerEntityTrackerMessage(tracker, "Tracking " + displayName), (EntityPlayerMP)this.getThrower());
            }

            entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.001F);
         }
      }

      if(!CompatibilityProvider.compatibility.world(this).isRemote) {
         if(!hit) {
            this.dropItem(this.itemWirelessCamera, 1);
         }

         this.setDead();
      }

   }

   public void onUpdate() {
      super.onUpdate();
   }

   public void writeSpawnData(ByteBuf buffer) {
      buffer.writeInt(Item.getIdFromItem(this.itemWirelessCamera));
      buffer.writeLong(this.timestamp);
      buffer.writeLong(this.duration);
   }

   public void readSpawnData(ByteBuf buffer) {
      Item item = Item.getItemById(buffer.readInt());
      if(item instanceof ItemWirelessCamera) {
         this.itemWirelessCamera = (ItemWirelessCamera)item;
      }

      this.timestamp = buffer.readLong();
      this.duration = buffer.readLong();
   }

   protected void setCompatibleThrowableHeading(double motionX, double motionY, double motionZ, float velocity, float inaccuracy) {
      CompatibilityProvider.compatibility.getMathHelper();
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
      CompatibilityProvider.compatibility.getMathHelper();
      float f3 = CompatibleMathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
      this.prevRotationYaw = this.rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0D / 3.141592653589793D);
      this.prevRotationPitch = this.rotationPitch = (float)(Math.atan2(motionY, (double)f3) * 180.0D / 3.141592653589793D);
   }

   protected float getInaccuracy() {
      return 0.0F;
   }

   protected float getVelocity() {
      return 0.5F;
   }

   public ItemWirelessCamera getItem() {
      return this.itemWirelessCamera;
   }
}
