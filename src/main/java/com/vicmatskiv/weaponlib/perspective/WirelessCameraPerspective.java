package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import com.vicmatskiv.weaponlib.electronics.PlayerTabletInstance;
import com.vicmatskiv.weaponlib.electronics.SignalQuality;
import com.vicmatskiv.weaponlib.perspective.RemoteFirstPersonPerspective;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import com.vicmatskiv.weaponlib.tracking.TrackableEntity;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class WirelessCameraPerspective extends RemoteFirstPersonPerspective {
   private static final Logger logger = LogManager.getLogger(WirelessCameraPerspective.class);
   private static final String STATIC_TEXTURE = "weaponlib:/com/vicmatskiv/weaponlib/resources/static.png";
   static final String DARK_SCREEN_TEXTURE = "weaponlib:/com/vicmatskiv/weaponlib/resources/dark-screen.png";
   private static final int STATIC_IMAGES_PER_ROW = 8;
   private int tickCounter;
   private int activeWatchIndex;
   private int badSignalTickCounter;
   private int imageIndex;
   private Random random = new Random();
   private int totalTrackableEntities;
   private String displayName;
   private Float batteryLevel;

   protected void updateWatchablePlayer() {
      EntityPlayer entityPlayer = CompatibilityProvider.compatibility.clientPlayer();
      PlayerItemInstance instance = this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(entityPlayer);
      if(instance instanceof PlayerTabletInstance) {
         PlayerTabletInstance tabletInstance = (PlayerTabletInstance)instance;
         PlayerEntityTracker playerEntityTracker = PlayerEntityTracker.getTracker(entityPlayer);
         if(playerEntityTracker != null) {
            this.activeWatchIndex = tabletInstance.getActiveWatchIndex();
            this.totalTrackableEntities = playerEntityTracker.getTrackableEntitites().size();
            TrackableEntity te = this.totalTrackableEntities > 0?playerEntityTracker.getTrackableEntity(this.activeWatchIndex):null;
            Object watchableEntity = null;
            if(te == null) {
               this.displayName = "";
               this.batteryLevel = null;
            } else {
               this.displayName = te.getDisplayName();
               watchableEntity = te.getEntity();
               this.batteryLevel = Float.valueOf(1.0F - (float)(System.currentTimeMillis() - te.getStartTimestamp()) / (float)te.getTrackingDuration());
               if(this.batteryLevel.floatValue() > 1.0F) {
                  this.batteryLevel = Float.valueOf(1.0F);
               } else if(this.batteryLevel.floatValue() < 0.0F) {
                  this.batteryLevel = Float.valueOf(0.0F);
               }
            }

            Entity realEntity = watchableEntity == null?null:CompatibilityProvider.compatibility.world((Entity)watchableEntity).getEntityByID(((Entity)watchableEntity).getEntityId());
            if(realEntity != null && realEntity != watchableEntity) {
               watchableEntity = (EntityLivingBase)realEntity;
            }

            if(this.tickCounter++ % 50 == 0) {
               logger.trace("Using entity tracker {}", new Object[]{playerEntityTracker});
               if(watchableEntity != null) {
                  logger.debug("Watching {} with uuid {}, distance: {}  ", new Object[]{watchableEntity, ((Entity)watchableEntity).getUniqueID(), Double.valueOf(Math.sqrt(Math.pow(((Entity)watchableEntity).posX - CompatibilityProvider.compatibility.getClientPlayer().posX, 2.0D) + Math.pow(((Entity)watchableEntity).posZ - CompatibilityProvider.compatibility.getClientPlayer().posZ, 2.0D)))});
               }
            }

            if(watchableEntity == null || watchableEntity instanceof EntityLivingBase) {
               this.watchablePlayer.setEntityLiving((EntityLivingBase)watchableEntity);
            }

         }
      }
   }

   protected void renderOverlay() {
      super.renderOverlay();
      byte maxDistance = 120;
      int displayCameraIndex = this.activeWatchIndex + 1;
      String message = "Cam " + displayCameraIndex + "/" + this.totalTrackableEntities + ": " + this.displayName;
      EntityLivingBase watchableEntity = this.watchablePlayer.getEntityLiving();
      int color = 16776960;
      if(watchableEntity != null) {
         EntityPlayer fontRender = CompatibilityProvider.compatibility.clientPlayer();
         double scale = Math.pow(watchableEntity.posX - fontRender.posX, 2.0D) + Math.pow(watchableEntity.posY - fontRender.posY, 2.0D) + Math.pow(watchableEntity.posZ - fontRender.posZ, 2.0D);
         SignalQuality quality = SignalQuality.getQuality((int)Math.sqrt(scale), maxDistance);
         if(watchableEntity.isDead || quality.isInterrupted() || this.badSignalTickCounter > 0 && this.badSignalTickCounter < 5 || watchableEntity.isDead) {
            if(this.badSignalTickCounter == 0) {
               this.framebuffer.framebufferClear();
               this.framebuffer.bindFramebuffer(true);
            }

            color = 16776960;
            message = "Cam " + displayCameraIndex + "/" + this.totalTrackableEntities + ": no signal";
            this.drawStatic();
            ++this.badSignalTickCounter;
         }

         if(this.badSignalTickCounter == 5) {
            this.badSignalTickCounter = 0;
         }
      } else if(this.totalTrackableEntities == 0) {
         this.framebuffer.framebufferClear();
         this.framebuffer.bindFramebuffer(true);
         Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/dark-screen.png"));
         drawTexturedQuadFit(0.0D, 0.0D, (double)this.width, (double)this.height, 0.0D);
         color = 16711680;
         message = "No Cameras Available";
      } else {
         this.framebuffer.framebufferClear();
         this.framebuffer.bindFramebuffer(true);
         message = "Cam " + displayCameraIndex + "/" + this.totalTrackableEntities + ": " + this.displayName;
         this.drawStatic();
      }

      FontRenderer var10 = CompatibilityProvider.compatibility.getFontRenderer();
      float var11 = 2.0F;
      GL11.glScalef(var11, var11, var11);
      var10.drawString(message, (int)(40.0F / var11), (int)((float)(this.height - 30) / var11), color, false);
      if(this.totalTrackableEntities > 0 && this.batteryLevel != null) {
         var10.drawString("Battery: " + (int)(this.batteryLevel.floatValue() * 100.0F) + "%", (int)(((float)this.width - 150.0F) / var11), (int)((float)(this.height - 30) / var11), color, false);
      }

   }

   public void drawStatic() {
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/static.png"));
      this.imageIndex = this.random.nextInt(8);
      float uWidth = 0.125F;
      float aU = (float)(this.imageIndex + 1) * uWidth;
      float aV = 1.0F;
      float bU = (float)(this.imageIndex + 1) * uWidth;
      float bV = 0.0F;
      float cU = (float)this.imageIndex * uWidth;
      float cV = 0.0F;
      float dU = (float)this.imageIndex * uWidth;
      float dV = 1.0F;
      double x = 0.0D;
      double y = 0.0D;
      double width = (double)this.width;
      double height = (double)this.height;
      double zLevel = 0.0D;
      CompatibleTessellator tessellator = CompatibleTessellator.getInstance();
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, aU, aV);
      tessellator.addVertexWithUV(x + width, y + height, zLevel, bU, bV);
      tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, cU, cV);
      tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, dU, dV);
      tessellator.draw();
   }

   private static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
      CompatibleTessellator tessellator = CompatibleTessellator.getInstance();
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, 1.0F, 0.0F);
      tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, 0.0F, 0.0F);
      tessellator.draw();
   }
}
