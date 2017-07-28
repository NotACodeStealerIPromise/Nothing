package com.vicmatskiv.weaponlib.compatibility;

import com.google.gson.JsonSyntaxException;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EffectRenderer;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ClippingHelperImpl;
import net.minecraft.client.renderer.culling.Frustrum;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderLinkHelper;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MouseFilter;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Post;
import net.minecraftforge.client.event.GuiScreenEvent.DrawScreenEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;

@SideOnly(Side.CLIENT)
public class CompatibleWorldRenderer extends EntityRenderer implements IResourceManagerReloadListener {
   private static final Logger logger = LogManager.getLogger();
   private static final float MAX_ZOOM = 0.01F;
   private static final ResourceLocation locationRainPng = new ResourceLocation("textures/environment/rain.png");
   private static final ResourceLocation locationSnowPng = new ResourceLocation("textures/environment/snow.png");
   public static boolean anaglyphEnable;
   public static int anaglyphField;
   private Minecraft mc;
   private float farPlaneDistance;
   public final ItemRenderer itemRenderer;
   private final MapItemRenderer theMapItemRenderer;
   private int rendererUpdateCount;
   private Entity pointedEntity;
   private MouseFilter mouseFilterXAxis = new MouseFilter();
   private MouseFilter mouseFilterYAxis = new MouseFilter();
   private MouseFilter mouseFilterDummy1 = new MouseFilter();
   private MouseFilter mouseFilterDummy2 = new MouseFilter();
   private MouseFilter mouseFilterDummy3 = new MouseFilter();
   private MouseFilter mouseFilterDummy4 = new MouseFilter();
   private float thirdPersonDistance = 4.0F;
   private float thirdPersonDistanceTemp = 4.0F;
   private float debugCamYaw;
   private float prevDebugCamYaw;
   private float debugCamPitch;
   private float prevDebugCamPitch;
   private float smoothCamYaw;
   private float smoothCamPitch;
   private float smoothCamFilterX;
   private float smoothCamFilterY;
   private float smoothCamPartialTicks;
   private float debugCamFOV;
   private float prevDebugCamFOV;
   private float camRoll;
   private float prevCamRoll;
   private final DynamicTexture lightmapTexture;
   private final int[] lightmapColors;
   private final ResourceLocation locationLightMap;
   private float fovModifierHand;
   private float fovModifierHandPrev;
   private float fovMultiplierTemp;
   private float bossColorModifier;
   private float bossColorModifierPrev;
   private boolean cloudFog;
   private final IResourceManager resourceManager;
   public ShaderGroup theShaderGroup;
   private static final ResourceLocation[] shaderResourceLocations = new ResourceLocation[]{new ResourceLocation("shaders/post/notch.json"), new ResourceLocation("shaders/post/fxaa.json"), new ResourceLocation("shaders/post/art.json"), new ResourceLocation("shaders/post/bumpy.json"), new ResourceLocation("shaders/post/blobs2.json"), new ResourceLocation("shaders/post/pencil.json"), new ResourceLocation("shaders/post/color_convolve.json"), new ResourceLocation("shaders/post/deconverge.json"), new ResourceLocation("shaders/post/flip.json"), new ResourceLocation("shaders/post/invert.json"), new ResourceLocation("shaders/post/ntsc.json"), new ResourceLocation("shaders/post/outline.json"), new ResourceLocation("shaders/post/phosphor.json"), new ResourceLocation("shaders/post/scan_pincushion.json"), new ResourceLocation("shaders/post/sobel.json"), new ResourceLocation("shaders/post/bits.json"), new ResourceLocation("shaders/post/desaturate.json"), new ResourceLocation("shaders/post/green.json"), new ResourceLocation("shaders/post/blur.json"), new ResourceLocation("shaders/post/wobble.json"), new ResourceLocation("shaders/post/blobs.json"), new ResourceLocation("shaders/post/antialias.json")};
   public static final int shaderCount;
   private int shaderIndex;
   private double cameraZoom;
   private double cameraYaw;
   private double cameraPitch;
   private long prevFrameTime;
   private long renderEndNanoTime;
   private boolean lightmapUpdateNeeded;
   float torchFlickerX;
   float torchFlickerDX;
   float torchFlickerY;
   float torchFlickerDY;
   private Random random;
   private int rainSoundCounter;
   float[] rainXCoords;
   float[] rainYCoords;
   FloatBuffer fogColorBuffer;
   float fogColorRed;
   float fogColorGreen;
   float fogColorBlue;
   private float fogColor2;
   private float fogColor1;
   public int debugViewDirection;
   private static final String __OBFID = "CL_00000947";

   public CompatibleWorldRenderer(Minecraft p_i45076_1_, IResourceManager p_i45076_2_) {
      super(p_i45076_1_, p_i45076_2_);
      this.shaderIndex = shaderCount;
      this.cameraZoom = 1.0D;
      this.prevFrameTime = Minecraft.getSystemTime();
      this.random = new Random();
      this.fogColorBuffer = GLAllocation.createDirectFloatBuffer(16);
      this.mc = p_i45076_1_;
      this.resourceManager = p_i45076_2_;
      this.theMapItemRenderer = new MapItemRenderer(p_i45076_1_.getTextureManager());
      this.itemRenderer = new ItemRenderer(p_i45076_1_);
      this.lightmapTexture = new DynamicTexture(16, 16);
      this.locationLightMap = p_i45076_1_.getTextureManager().getDynamicTextureLocation("lightMap", this.lightmapTexture);
      this.lightmapColors = this.lightmapTexture.getTextureData();
      this.theShaderGroup = null;
   }

   public boolean isShaderActive() {
      return OpenGlHelper.shadersSupported && this.theShaderGroup != null;
   }

   public void deactivateShader() {
      if(this.theShaderGroup != null) {
         this.theShaderGroup.deleteShaderGroup();
      }

      this.theShaderGroup = null;
      this.shaderIndex = shaderCount;
   }

   public void activateNextShader() {
      if(OpenGlHelper.shadersSupported) {
         if(this.theShaderGroup != null) {
            this.theShaderGroup.deleteShaderGroup();
         }

         this.shaderIndex = (this.shaderIndex + 1) % (shaderResourceLocations.length + 1);
         if(this.shaderIndex != shaderCount) {
            try {
               logger.info("Selecting effect " + shaderResourceLocations[this.shaderIndex]);
               this.theShaderGroup = new ShaderGroup(this.mc.getTextureManager(), this.resourceManager, this.mc.getFramebuffer(), shaderResourceLocations[this.shaderIndex]);
               this.theShaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
            } catch (IOException var2) {
               logger.warn("Failed to load shader: " + shaderResourceLocations[this.shaderIndex], var2);
               this.shaderIndex = shaderCount;
            } catch (JsonSyntaxException var3) {
               logger.warn("Failed to load shader: " + shaderResourceLocations[this.shaderIndex], var3);
               this.shaderIndex = shaderCount;
            }
         } else {
            this.theShaderGroup = null;
            logger.info("No effect selected");
         }
      }

   }

   public void onResourceManagerReload(IResourceManager p_110549_1_) {
      if(this.theShaderGroup != null) {
         this.theShaderGroup.deleteShaderGroup();
      }

      if(this.shaderIndex != shaderCount) {
         try {
            this.theShaderGroup = new ShaderGroup(this.mc.getTextureManager(), p_110549_1_, this.mc.getFramebuffer(), shaderResourceLocations[this.shaderIndex]);
            this.theShaderGroup.createBindFramebuffers(this.mc.displayWidth, this.mc.displayHeight);
         } catch (IOException var3) {
            logger.warn("Failed to load shader: " + shaderResourceLocations[this.shaderIndex], var3);
            this.shaderIndex = shaderCount;
         }
      }

   }

   public void updateRenderer() {
      if(OpenGlHelper.shadersSupported && ShaderLinkHelper.getStaticShaderLinkHelper() == null) {
         ShaderLinkHelper.setNewStaticShaderLinkHelper();
      }

      this.updateFovModifierHand();
      this.updateTorchFlicker();
      this.fogColor2 = this.fogColor1;
      this.thirdPersonDistanceTemp = this.thirdPersonDistance;
      this.prevDebugCamYaw = this.debugCamYaw;
      this.prevDebugCamPitch = this.debugCamPitch;
      this.prevDebugCamFOV = this.debugCamFOV;
      this.prevCamRoll = this.camRoll;
      float f;
      float f1;
      if(this.mc.gameSettings.smoothCamera) {
         f = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         f1 = f * f * f * 8.0F;
         this.smoothCamFilterX = this.mouseFilterXAxis.smooth(this.smoothCamYaw, 0.05F * f1);
         this.smoothCamFilterY = this.mouseFilterYAxis.smooth(this.smoothCamPitch, 0.05F * f1);
         this.smoothCamPartialTicks = 0.0F;
         this.smoothCamYaw = 0.0F;
         this.smoothCamPitch = 0.0F;
      }

      if(this.mc.renderViewEntity == null) {
         this.mc.renderViewEntity = this.mc.thePlayer;
      }

      f = this.mc.theWorld.getLightBrightness(MathHelper.floor_double(this.mc.renderViewEntity.posX), MathHelper.floor_double(this.mc.renderViewEntity.posY), MathHelper.floor_double(this.mc.renderViewEntity.posZ));
      f1 = (float)this.mc.gameSettings.renderDistanceChunks / 16.0F;
      float f2 = f * (1.0F - f1) + f1;
      this.fogColor1 += (f2 - this.fogColor1) * 0.1F;
      ++this.rendererUpdateCount;
      this.itemRenderer.updateEquippedItem();
      this.addRainParticles();
      this.bossColorModifierPrev = this.bossColorModifier;
      if(BossStatus.hasColorModifier) {
         this.bossColorModifier += 0.05F;
         if(this.bossColorModifier > 1.0F) {
            this.bossColorModifier = 1.0F;
         }

         BossStatus.hasColorModifier = false;
      } else if(this.bossColorModifier > 0.0F) {
         this.bossColorModifier -= 0.0125F;
      }

   }

   public ShaderGroup getShaderGroup() {
      return this.theShaderGroup;
   }

   public void updateShaderGroupSize(int p_147704_1_, int p_147704_2_) {
      if(OpenGlHelper.shadersSupported && this.theShaderGroup != null) {
         this.theShaderGroup.createBindFramebuffers(p_147704_1_, p_147704_2_);
      }

   }

   public void getMouseOver(float p_78473_1_) {
      if(this.mc.renderViewEntity != null && this.mc.theWorld != null) {
         this.mc.pointedEntity = null;
         double d0 = (double)this.mc.playerController.getBlockReachDistance();
         this.mc.objectMouseOver = this.mc.renderViewEntity.rayTrace(d0, p_78473_1_);
         double d1 = d0;
         Vec3 vec3 = this.mc.renderViewEntity.getPosition(p_78473_1_);
         if(this.mc.playerController.extendedReach()) {
            d0 = 6.0D;
            d1 = 6.0D;
         } else {
            if(d0 > 3.0D) {
               d1 = 3.0D;
            }

            d0 = d1;
         }

         if(this.mc.objectMouseOver != null) {
            d1 = this.mc.objectMouseOver.hitVec.distanceTo(vec3);
         }

         Vec3 vec31 = this.mc.renderViewEntity.getLook(p_78473_1_);
         Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
         this.pointedEntity = null;
         Vec3 vec33 = null;
         float f1 = 1.0F;
         List list = this.mc.theWorld.getEntitiesWithinAABBExcludingEntity(this.mc.renderViewEntity, this.mc.renderViewEntity.boundingBox.addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand((double)f1, (double)f1, (double)f1));
         double d2 = d1;

         for(int i = 0; i < list.size(); ++i) {
            Entity entity = (Entity)list.get(i);
            if(entity.canBeCollidedWith()) {
               float f2 = entity.getCollisionBorderSize();
               AxisAlignedBB axisalignedbb = entity.boundingBox.expand((double)f2, (double)f2, (double)f2);
               MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
               if(axisalignedbb.isVecInside(vec3)) {
                  if(0.0D < d2 || d2 == 0.0D) {
                     this.pointedEntity = entity;
                     vec33 = movingobjectposition == null?vec3:movingobjectposition.hitVec;
                     d2 = 0.0D;
                  }
               } else if(movingobjectposition != null) {
                  double d3 = vec3.distanceTo(movingobjectposition.hitVec);
                  if(d3 < d2 || d2 == 0.0D) {
                     if(entity == this.mc.renderViewEntity.ridingEntity && !entity.canRiderInteract()) {
                        if(d2 == 0.0D) {
                           this.pointedEntity = entity;
                           vec33 = movingobjectposition.hitVec;
                        }
                     } else {
                        this.pointedEntity = entity;
                        vec33 = movingobjectposition.hitVec;
                        d2 = d3;
                     }
                  }
               }
            }
         }

         if(this.pointedEntity != null && (d2 < d1 || this.mc.objectMouseOver == null)) {
            this.mc.objectMouseOver = new MovingObjectPosition(this.pointedEntity, vec33);
            if(this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame) {
               this.mc.pointedEntity = this.pointedEntity;
            }
         }
      }

   }

   private void updateFovModifierHand() {
      if(this.mc.renderViewEntity instanceof EntityPlayerSP) {
         EntityPlayerSP entityplayersp = (EntityPlayerSP)this.mc.renderViewEntity;
         this.fovMultiplierTemp = entityplayersp.getFOVMultiplier();
      } else {
         this.fovMultiplierTemp = this.mc.thePlayer.getFOVMultiplier();
      }

      this.fovModifierHandPrev = this.fovModifierHand;
      this.fovModifierHand += (this.fovMultiplierTemp - this.fovModifierHand) * 0.5F;
      if(this.fovModifierHand > 1.5F) {
         this.fovModifierHand = 1.5F;
      }

      if(this.fovModifierHand < 0.01F) {
         this.fovModifierHand = 0.01F;
      }

   }

   private float getFOVModifier(float p_78481_1_, boolean p_78481_2_) {
      if(this.debugViewDirection > 0) {
         return 90.0F;
      } else {
         EntityLivingBase entityplayer = this.mc.renderViewEntity;
         float f1 = 70.0F;
         if(p_78481_2_) {
            f1 = this.mc.gameSettings.fovSetting;
            f1 *= this.fovModifierHandPrev + (this.fovModifierHand - this.fovModifierHandPrev) * p_78481_1_;
         }

         if(entityplayer.getHealth() <= 0.0F) {
            float block = (float)entityplayer.deathTime + p_78481_1_;
            f1 /= (1.0F - 500.0F / (block + 500.0F)) * 2.0F + 1.0F;
         }

         Block block1 = ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, entityplayer, p_78481_1_);
         if(block1.getMaterial() == Material.water) {
            f1 = f1 * 60.0F / 70.0F;
         }

         return f1 + this.prevDebugCamFOV + (this.debugCamFOV - this.prevDebugCamFOV) * p_78481_1_;
      }
   }

   private void hurtCameraEffect(float p_78482_1_) {
      EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
      float f1 = (float)entitylivingbase.hurtTime - p_78482_1_;
      float f2;
      if(entitylivingbase.getHealth() <= 0.0F) {
         f2 = (float)entitylivingbase.deathTime + p_78482_1_;
         GL11.glRotatef(40.0F - 8000.0F / (f2 + 200.0F), 0.0F, 0.0F, 1.0F);
      }

      if(f1 >= 0.0F) {
         f1 /= (float)entitylivingbase.maxHurtTime;
         f1 = MathHelper.sin(f1 * f1 * f1 * f1 * 3.1415927F);
         f2 = entitylivingbase.attackedAtYaw;
         GL11.glRotatef(-f2, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-f1 * 14.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(f2, 0.0F, 1.0F, 0.0F);
      }

   }

   private void setupViewBobbing(float p_78475_1_) {
      if(this.mc.renderViewEntity instanceof EntityPlayer) {
         EntityPlayer entityplayer = (EntityPlayer)this.mc.renderViewEntity;
         float f1 = entityplayer.distanceWalkedModified - entityplayer.prevDistanceWalkedModified;
         float f2 = -(entityplayer.distanceWalkedModified + f1 * p_78475_1_);
         float f3 = entityplayer.prevCameraYaw + (entityplayer.cameraYaw - entityplayer.prevCameraYaw) * p_78475_1_;
         float f4 = entityplayer.prevCameraPitch + (entityplayer.cameraPitch - entityplayer.prevCameraPitch) * p_78475_1_;
         GL11.glTranslatef(MathHelper.sin(f2 * 3.1415927F) * f3 * 0.5F, -Math.abs(MathHelper.cos(f2 * 3.1415927F) * f3), 0.0F);
         GL11.glRotatef(MathHelper.sin(f2 * 3.1415927F) * f3 * 3.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(Math.abs(MathHelper.cos(f2 * 3.1415927F - 0.2F) * f3) * 5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(f4, 1.0F, 0.0F, 0.0F);
      }

   }

   private void orientCamera(float p_78467_1_) {
      EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
      float f1 = entitylivingbase.yOffset - 1.62F;
      double d0 = entitylivingbase.prevPosX + (entitylivingbase.posX - entitylivingbase.prevPosX) * (double)p_78467_1_;
      double d1 = entitylivingbase.prevPosY + (entitylivingbase.posY - entitylivingbase.prevPosY) * (double)p_78467_1_ - (double)f1;
      double d2 = entitylivingbase.prevPosZ + (entitylivingbase.posZ - entitylivingbase.prevPosZ) * (double)p_78467_1_;
      GL11.glRotatef(this.prevCamRoll + (this.camRoll - this.prevCamRoll) * p_78467_1_, 0.0F, 0.0F, 1.0F);
      if(entitylivingbase.isPlayerSleeping()) {
         f1 = (float)((double)f1 + 1.0D);
         GL11.glTranslatef(0.0F, 0.3F, 0.0F);
         if(!this.mc.gameSettings.debugCamEnable) {
            ForgeHooksClient.orientBedCamera(this.mc, entitylivingbase);
            GL11.glRotatef(entitylivingbase.prevRotationYaw + (entitylivingbase.rotationYaw - entitylivingbase.prevRotationYaw) * p_78467_1_ + 180.0F, 0.0F, -1.0F, 0.0F);
            GL11.glRotatef(entitylivingbase.prevRotationPitch + (entitylivingbase.rotationPitch - entitylivingbase.prevRotationPitch) * p_78467_1_, -1.0F, 0.0F, 0.0F);
         }
      } else if(this.mc.gameSettings.thirdPersonView > 0) {
         double d7 = (double)(this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * p_78467_1_);
         float f2;
         float f6;
         if(this.mc.gameSettings.debugCamEnable) {
            f6 = this.prevDebugCamYaw + (this.debugCamYaw - this.prevDebugCamYaw) * p_78467_1_;
            f2 = this.prevDebugCamPitch + (this.debugCamPitch - this.prevDebugCamPitch) * p_78467_1_;
            GL11.glTranslatef(0.0F, 0.0F, (float)(-d7));
            GL11.glRotatef(f2, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(f6, 0.0F, 1.0F, 0.0F);
         } else {
            f6 = entitylivingbase.rotationYaw;
            f2 = entitylivingbase.rotationPitch;
            if(this.mc.gameSettings.thirdPersonView == 2) {
               f2 += 180.0F;
            }

            double d3 = (double)(-MathHelper.sin(f6 / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F)) * d7;
            double d4 = (double)(MathHelper.cos(f6 / 180.0F * 3.1415927F) * MathHelper.cos(f2 / 180.0F * 3.1415927F)) * d7;
            double d5 = (double)(-MathHelper.sin(f2 / 180.0F * 3.1415927F)) * d7;

            for(int k = 0; k < 8; ++k) {
               float f3 = (float)((k & 1) * 2 - 1);
               float f4 = (float)((k >> 1 & 1) * 2 - 1);
               float f5 = (float)((k >> 2 & 1) * 2 - 1);
               f3 *= 0.1F;
               f4 *= 0.1F;
               f5 *= 0.1F;
               MovingObjectPosition movingobjectposition = this.mc.theWorld.rayTraceBlocks(Vec3.createVectorHelper(d0 + (double)f3, d1 + (double)f4, d2 + (double)f5), Vec3.createVectorHelper(d0 - d3 + (double)f3 + (double)f5, d1 - d5 + (double)f4, d2 - d4 + (double)f5));
               if(movingobjectposition != null) {
                  double d6 = movingobjectposition.hitVec.distanceTo(Vec3.createVectorHelper(d0, d1, d2));
                  if(d6 < d7) {
                     d7 = d6;
                  }
               }
            }

            if(this.mc.gameSettings.thirdPersonView == 2) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            GL11.glRotatef(entitylivingbase.rotationPitch - f2, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(entitylivingbase.rotationYaw - f6, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.0F, (float)(-d7));
            GL11.glRotatef(f6 - entitylivingbase.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(f2 - entitylivingbase.rotationPitch, 1.0F, 0.0F, 0.0F);
         }
      } else {
         GL11.glTranslatef(0.0F, 0.0F, -0.1F);
      }

      if(!this.mc.gameSettings.debugCamEnable) {
         GL11.glRotatef(entitylivingbase.prevRotationPitch + (entitylivingbase.rotationPitch - entitylivingbase.prevRotationPitch) * p_78467_1_, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(entitylivingbase.prevRotationYaw + (entitylivingbase.rotationYaw - entitylivingbase.prevRotationYaw) * p_78467_1_ + 180.0F, 0.0F, 1.0F, 0.0F);
      }

      GL11.glTranslatef(0.0F, f1, 0.0F);
      d0 = entitylivingbase.prevPosX + (entitylivingbase.posX - entitylivingbase.prevPosX) * (double)p_78467_1_;
      d1 = entitylivingbase.prevPosY + (entitylivingbase.posY - entitylivingbase.prevPosY) * (double)p_78467_1_ - (double)f1;
      d2 = entitylivingbase.prevPosZ + (entitylivingbase.posZ - entitylivingbase.prevPosZ) * (double)p_78467_1_;
      this.cloudFog = this.mc.renderGlobal.hasCloudFog(d0, d1, d2, p_78467_1_);
   }

   private void setupCameraTransform(float p_78479_1_, int p_78479_2_) {
      this.farPlaneDistance = (float)(this.mc.gameSettings.renderDistanceChunks * 16);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      float f1 = 0.07F;
      if(this.mc.gameSettings.anaglyph) {
         GL11.glTranslatef((float)(-(p_78479_2_ * 2 - 1)) * f1, 0.0F, 0.0F);
      }

      if(this.cameraZoom != 1.0D) {
         GL11.glTranslatef((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
         GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
      }

      Project.gluPerspective(this.getFOVModifier(p_78479_1_, true), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
      float f2;
      if(this.mc.playerController.enableEverythingIsScrewedUpMode()) {
         f2 = 0.6666667F;
         GL11.glScalef(1.0F, f2, 1.0F);
      }

      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      if(this.mc.gameSettings.anaglyph) {
         GL11.glTranslatef((float)(p_78479_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
      }

      this.hurtCameraEffect(p_78479_1_);
      if(this.mc.gameSettings.viewBobbing) {
         this.setupViewBobbing(p_78479_1_);
      }

      f2 = this.mc.thePlayer.prevTimeInPortal + (this.mc.thePlayer.timeInPortal - this.mc.thePlayer.prevTimeInPortal) * p_78479_1_;
      if(f2 > 0.0F) {
         byte j = 20;
         if(this.mc.thePlayer.isPotionActive(Potion.confusion)) {
            j = 7;
         }

         float f3 = 5.0F / (f2 * f2 + 5.0F) - f2 * 0.04F;
         f3 *= f3;
         GL11.glRotatef(((float)this.rendererUpdateCount + p_78479_1_) * (float)j, 0.0F, 1.0F, 1.0F);
         GL11.glScalef(1.0F / f3, 1.0F, 1.0F);
         GL11.glRotatef(-((float)this.rendererUpdateCount + p_78479_1_) * (float)j, 0.0F, 1.0F, 1.0F);
      }

      this.orientCamera(p_78479_1_);
      if(this.debugViewDirection > 0) {
         int j1 = this.debugViewDirection - 1;
         if(j1 == 1) {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
         }

         if(j1 == 2) {
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
         }

         if(j1 == 3) {
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
         }

         if(j1 == 4) {
            GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         }

         if(j1 == 5) {
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         }
      }

   }

   private void renderHand(float p_78476_1_, int p_78476_2_) {
      if(this.debugViewDirection <= 0) {
         GL11.glMatrixMode(5889);
         GL11.glLoadIdentity();
         float f1 = 0.07F;
         if(this.mc.gameSettings.anaglyph) {
            GL11.glTranslatef((float)(-(p_78476_2_ * 2 - 1)) * f1, 0.0F, 0.0F);
         }

         if(this.cameraZoom != 1.0D) {
            GL11.glTranslatef((float)this.cameraYaw, (float)(-this.cameraPitch), 0.0F);
            GL11.glScaled(this.cameraZoom, this.cameraZoom, 1.0D);
         }

         Project.gluPerspective(this.getFOVModifier(p_78476_1_, false), (float)this.mc.displayWidth / (float)this.mc.displayHeight, 0.05F, this.farPlaneDistance * 2.0F);
         if(this.mc.playerController.enableEverythingIsScrewedUpMode()) {
            float f2 = 0.6666667F;
            GL11.glScalef(1.0F, f2, 1.0F);
         }

         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         if(this.mc.gameSettings.anaglyph) {
            GL11.glTranslatef((float)(p_78476_2_ * 2 - 1) * 0.1F, 0.0F, 0.0F);
         }

         GL11.glPushMatrix();
         this.hurtCameraEffect(p_78476_1_);
         if(this.mc.gameSettings.viewBobbing) {
            this.setupViewBobbing(p_78476_1_);
         }

         if(this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.isPlayerSleeping() && !this.mc.gameSettings.hideGUI && !this.mc.playerController.enableEverythingIsScrewedUpMode()) {
            this.enableLightmap((double)p_78476_1_);
            this.itemRenderer.renderItemInFirstPerson(p_78476_1_);
            this.disableLightmap((double)p_78476_1_);
         }

         GL11.glPopMatrix();
         if(this.mc.gameSettings.thirdPersonView == 0 && !this.mc.renderViewEntity.isPlayerSleeping()) {
            this.itemRenderer.renderOverlays(p_78476_1_);
            this.hurtCameraEffect(p_78476_1_);
         }

         if(this.mc.gameSettings.viewBobbing) {
            this.setupViewBobbing(p_78476_1_);
         }
      }

   }

   public void disableLightmap(double p_78483_1_) {
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glDisable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   public void enableLightmap(double p_78463_1_) {
      OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GL11.glMatrixMode(5890);
      GL11.glLoadIdentity();
      float f = 0.00390625F;
      GL11.glScalef(f, f, f);
      GL11.glTranslatef(8.0F, 8.0F, 8.0F);
      GL11.glMatrixMode(5888);
      this.mc.getTextureManager().bindTexture(this.locationLightMap);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glTexParameteri(3553, 10242, 10496);
      GL11.glTexParameteri(3553, 10243, 10496);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3553);
      OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   private void updateTorchFlicker() {
      this.torchFlickerDX = (float)((double)this.torchFlickerDX + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.torchFlickerDY = (float)((double)this.torchFlickerDY + (Math.random() - Math.random()) * Math.random() * Math.random());
      this.torchFlickerDX = (float)((double)this.torchFlickerDX * 0.9D);
      this.torchFlickerDY = (float)((double)this.torchFlickerDY * 0.9D);
      this.torchFlickerX += (this.torchFlickerDX - this.torchFlickerX) * 1.0F;
      this.torchFlickerY += (this.torchFlickerDY - this.torchFlickerY) * 1.0F;
      this.lightmapUpdateNeeded = true;
   }

   void updateLightmap(float p_78472_1_) {
      WorldClient worldclient = this.mc.theWorld;
      if(worldclient != null) {
         for(int i = 0; i < 256; ++i) {
            float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
            float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
            float f3 = worldclient.provider.lightBrightnessTable[i % 16] * (this.torchFlickerX * 0.1F + 1.5F);
            if(worldclient.lastLightningBolt > 0) {
               f2 = worldclient.provider.lightBrightnessTable[i / 16];
            }

            float f4 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
            float f5 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
            float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
            float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
            float f8 = f4 + f3;
            float f9 = f5 + f6;
            float f10 = f2 + f7;
            f8 = f8 * 0.96F + 0.03F;
            f9 = f9 * 0.96F + 0.03F;
            f10 = f10 * 0.96F + 0.03F;
            float f11;
            if(this.bossColorModifier > 0.0F) {
               f11 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * p_78472_1_;
               f8 = f8 * (1.0F - f11) + f8 * 0.7F * f11;
               f9 = f9 * (1.0F - f11) + f9 * 0.6F * f11;
               f10 = f10 * (1.0F - f11) + f10 * 0.6F * f11;
            }

            if(worldclient.provider.dimensionId == 1) {
               f8 = 0.22F + f3 * 0.75F;
               f9 = 0.28F + f6 * 0.75F;
               f10 = 0.25F + f7 * 0.75F;
            }

            float f12;
            if(this.mc.thePlayer.isPotionActive(Potion.nightVision)) {
               f11 = this.getNightVisionBrightness(this.mc.thePlayer, p_78472_1_);
               f12 = 1.0F / f8;
               if(f12 > 1.0F / f9) {
                  f12 = 1.0F / f9;
               }

               if(f12 > 1.0F / f10) {
                  f12 = 1.0F / f10;
               }

               f8 = f8 * (1.0F - f11) + f8 * f12 * f11;
               f9 = f9 * (1.0F - f11) + f9 * f12 * f11;
               f10 = f10 * (1.0F - f11) + f10 * f12 * f11;
            }

            if(f8 > 1.0F) {
               f8 = 1.0F;
            }

            if(f9 > 1.0F) {
               f9 = 1.0F;
            }

            if(f10 > 1.0F) {
               f10 = 1.0F;
            }

            f11 = this.mc.gameSettings.gammaSetting;
            f12 = 1.0F - f8;
            float f13 = 1.0F - f9;
            float f14 = 1.0F - f10;
            f12 = 1.0F - f12 * f12 * f12 * f12;
            f13 = 1.0F - f13 * f13 * f13 * f13;
            f14 = 1.0F - f14 * f14 * f14 * f14;
            f8 = f8 * (1.0F - f11) + f12 * f11;
            f9 = f9 * (1.0F - f11) + f13 * f11;
            f10 = f10 * (1.0F - f11) + f14 * f11;
            f8 = f8 * 0.96F + 0.03F;
            f9 = f9 * 0.96F + 0.03F;
            f10 = f10 * 0.96F + 0.03F;
            if(f8 > 1.0F) {
               f8 = 1.0F;
            }

            if(f9 > 1.0F) {
               f9 = 1.0F;
            }

            if(f10 > 1.0F) {
               f10 = 1.0F;
            }

            if(f8 < 0.0F) {
               f8 = 0.0F;
            }

            if(f9 < 0.0F) {
               f9 = 0.0F;
            }

            if(f10 < 0.0F) {
               f10 = 0.0F;
            }

            short short1 = 255;
            int j = (int)(f8 * 255.0F);
            int k = (int)(f9 * 255.0F);
            int l = (int)(f10 * 255.0F);
            this.lightmapColors[i] = short1 << 24 | j << 16 | k << 8 | l;
         }

         this.lightmapTexture.updateDynamicTexture();
         this.lightmapUpdateNeeded = false;
      }

   }

   private float getNightVisionBrightness(EntityPlayer p_82830_1_, float p_82830_2_) {
      int i = p_82830_1_.getActivePotionEffect(Potion.nightVision).getDuration();
      return i > 200?1.0F:0.7F + MathHelper.sin(((float)i - p_82830_2_) * 3.1415927F * 0.2F) * 0.3F;
   }

   public void updateCameraAndRender(float p_78480_1_) {
      this.mc.mcProfiler.startSection("lightTex");
      if(this.lightmapUpdateNeeded) {
         this.updateLightmap(p_78480_1_);
      }

      this.mc.mcProfiler.endSection();
      boolean flag = Display.isActive();
      if(flag || !this.mc.gameSettings.pauseOnLostFocus || this.mc.gameSettings.touchscreen && Mouse.isButtonDown(1)) {
         this.prevFrameTime = Minecraft.getSystemTime();
      } else if(Minecraft.getSystemTime() - this.prevFrameTime > 500L) {
         this.mc.displayInGameMenu();
      }

      this.mc.mcProfiler.startSection("mouse");
      if(this.mc.inGameHasFocus && flag) {
         this.mc.mouseHelper.mouseXYChange();
         float scaledresolution = this.mc.gameSettings.mouseSensitivity * 0.6F + 0.2F;
         float i = scaledresolution * scaledresolution * scaledresolution * 8.0F;
         float j = (float)this.mc.mouseHelper.deltaX * i;
         float k = (float)this.mc.mouseHelper.deltaY * i;
         byte l = 1;
         if(this.mc.gameSettings.invertMouse) {
            l = -1;
         }

         if(this.mc.gameSettings.smoothCamera) {
            this.smoothCamYaw += j;
            this.smoothCamPitch += k;
            float i1 = p_78480_1_ - this.smoothCamPartialTicks;
            this.smoothCamPartialTicks = p_78480_1_;
            j = this.smoothCamFilterX * i1;
            k = this.smoothCamFilterY * i1;
            this.mc.thePlayer.setAngles(j, k * (float)l);
         } else {
            this.mc.thePlayer.setAngles(j, k * (float)l);
         }
      }

      this.mc.mcProfiler.endSection();
      if(!this.mc.skipRenderWorld) {
         anaglyphEnable = this.mc.gameSettings.anaglyph;
         final ScaledResolution scaledresolution1 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
         int i1 = scaledresolution1.getScaledWidth();
         int j1 = scaledresolution1.getScaledHeight();
         final int k1 = Mouse.getX() * i1 / this.mc.displayWidth;
         final int l1 = j1 - Mouse.getY() * j1 / this.mc.displayHeight - 1;
         int i11 = this.mc.gameSettings.limitFramerate;
         if(this.mc.theWorld != null) {
            this.mc.mcProfiler.startSection("level");
            if(this.mc.isFramerateLimitBelowMax()) {
               this.renderWorld(p_78480_1_, this.renderEndNanoTime + (long)(1000000000 / i11));
            } else {
               this.renderWorld(p_78480_1_, 0L);
            }

            if(OpenGlHelper.shadersSupported) {
               if(this.theShaderGroup != null) {
                  GL11.glMatrixMode(5890);
                  GL11.glPushMatrix();
                  GL11.glLoadIdentity();
                  this.theShaderGroup.loadShaderGroup(p_78480_1_);
                  GL11.glPopMatrix();
               }

               this.mc.getFramebuffer().bindFramebuffer(true);
            }

            this.renderEndNanoTime = System.nanoTime();
            this.mc.mcProfiler.endStartSection("gui");
            if(!this.mc.gameSettings.hideGUI || this.mc.currentScreen != null) {
               GL11.glAlphaFunc(516, 0.1F);
               this.mc.ingameGUI.renderGameOverlay(p_78480_1_, this.mc.currentScreen != null, k1, l1);
            }

            this.mc.mcProfiler.endSection();
         } else {
            GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
            GL11.glMatrixMode(5889);
            GL11.glLoadIdentity();
            GL11.glMatrixMode(5888);
            GL11.glLoadIdentity();
            this.setupOverlayRendering();
            this.renderEndNanoTime = System.nanoTime();
         }

         if(this.mc.currentScreen != null) {
            GL11.glClear(256);

            try {
               if(!MinecraftForge.EVENT_BUS.post(new Pre(this.mc.currentScreen, k1, l1, p_78480_1_))) {
                  this.mc.currentScreen.drawScreen(k1, l1, p_78480_1_);
               }

               MinecraftForge.EVENT_BUS.post(new Post(this.mc.currentScreen, k1, l1, p_78480_1_));
            } catch (Throwable var12) {
               CrashReport crashreport = CrashReport.makeCrashReport(var12, "Rendering screen");
               CrashReportCategory crashreportcategory = crashreport.makeCategory("Screen render details");
               crashreportcategory.addCrashSectionCallable("Screen name", new Callable() {
                  private static final String __OBFID = "CL_00000948";

                  public String call() {
                     return CompatibleWorldRenderer.this.mc.currentScreen.getClass().getCanonicalName();
                  }
               });
               crashreportcategory.addCrashSectionCallable("Mouse location", new Callable() {
                  private static final String __OBFID = "CL_00000950";

                  public String call() {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d)", new Object[]{Integer.valueOf(k1), Integer.valueOf(l1), Integer.valueOf(Mouse.getX()), Integer.valueOf(Mouse.getY())});
                  }
               });
               crashreportcategory.addCrashSectionCallable("Screen size", new Callable() {
                  private static final String __OBFID = "CL_00000951";

                  public String call() {
                     return String.format("Scaled: (%d, %d). Absolute: (%d, %d). Scale factor of %d", new Object[]{Integer.valueOf(scaledresolution1.getScaledWidth()), Integer.valueOf(scaledresolution1.getScaledHeight()), Integer.valueOf(CompatibleWorldRenderer.this.mc.displayWidth), Integer.valueOf(CompatibleWorldRenderer.this.mc.displayHeight), Integer.valueOf(scaledresolution1.getScaleFactor())});
                  }
               });
               throw new ReportedException(crashreport);
            }
         }
      }

   }

   public void func_152430_c(float p_152430_1_) {
      this.setupOverlayRendering();
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      int i = scaledresolution.getScaledWidth();
      int j = scaledresolution.getScaledHeight();
      this.mc.ingameGUI.func_152126_a((float)i, (float)j);
   }

   public void renderWorld(float p_78471_1_, long p_78471_2_) {
      this.mc.mcProfiler.startSection("lightTex");
      if(this.lightmapUpdateNeeded) {
         this.updateLightmap(p_78471_1_);
      }

      GL11.glEnable(2884);
      GL11.glEnable(2929);
      GL11.glEnable(3008);
      GL11.glAlphaFunc(516, 0.5F);
      if(this.mc.renderViewEntity == null) {
         this.mc.renderViewEntity = this.mc.thePlayer;
      }

      this.mc.mcProfiler.endStartSection("pick");
      this.getMouseOver(p_78471_1_);
      EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
      RenderGlobal renderglobal = this.mc.renderGlobal;
      EffectRenderer effectrenderer = this.mc.effectRenderer;
      double d0 = entitylivingbase.lastTickPosX + (entitylivingbase.posX - entitylivingbase.lastTickPosX) * (double)p_78471_1_;
      double d1 = entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)p_78471_1_;
      double d2 = entitylivingbase.lastTickPosZ + (entitylivingbase.posZ - entitylivingbase.lastTickPosZ) * (double)p_78471_1_;
      this.mc.mcProfiler.endStartSection("center");

      for(int j = 0; j < 2; ++j) {
         if(this.mc.gameSettings.anaglyph) {
            anaglyphField = j;
            if(anaglyphField == 0) {
               GL11.glColorMask(false, true, true, false);
            } else {
               GL11.glColorMask(true, false, false, false);
            }
         }

         this.mc.mcProfiler.endStartSection("clear");
         boolean width = true;
         int height = 100 * this.mc.displayHeight / this.mc.displayWidth;
         GL11.glViewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
         this.updateFogColor(p_78471_1_);
         GL11.glClear(16640);
         GL11.glEnable(2884);
         this.mc.mcProfiler.endStartSection("camera");
         this.setupCameraTransform(p_78471_1_, j);
         ActiveRenderInfo.updateRenderInfo(this.mc.thePlayer, this.mc.gameSettings.thirdPersonView == 2);
         this.mc.mcProfiler.endStartSection("frustrum");
         ClippingHelperImpl.getInstance();
         if(this.mc.gameSettings.renderDistanceChunks >= 4) {
            this.setupFog(-1, p_78471_1_);
            this.mc.mcProfiler.endStartSection("sky");
            renderglobal.renderSky(p_78471_1_);
         }

         GL11.glEnable(2912);
         this.setupFog(1, p_78471_1_);
         if(this.mc.gameSettings.ambientOcclusion != 0) {
            GL11.glShadeModel(7425);
         }

         this.mc.mcProfiler.endStartSection("culling");
         Frustrum frustrum = new Frustrum();
         frustrum.setPosition(d0, d1, d2);
         this.mc.renderGlobal.clipRenderersByFrustum(frustrum, p_78471_1_);
         if(j == 0) {
            this.mc.mcProfiler.endStartSection("updatechunks");

            while(!this.mc.renderGlobal.updateRenderers(entitylivingbase, false) && p_78471_2_ != 0L) {
               long entityplayer = p_78471_2_ - System.nanoTime();
               if(entityplayer < 0L || entityplayer > 1000000000L) {
                  break;
               }
            }
         }

         if(entitylivingbase.posY < 128.0D) {
            this.renderCloudsCheck(renderglobal, p_78471_1_);
         }

         this.mc.mcProfiler.endStartSection("prepareterrain");
         this.setupFog(0, p_78471_1_);
         GL11.glEnable(2912);
         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         RenderHelper.disableStandardItemLighting();
         this.mc.mcProfiler.endStartSection("terrain");
         GL11.glMatrixMode(5888);
         GL11.glPushMatrix();
         renderglobal.sortAndRender(entitylivingbase, 0, (double)p_78471_1_);
         GL11.glShadeModel(7424);
         GL11.glAlphaFunc(516, 0.1F);
         EntityPlayer var19;
         if(this.debugViewDirection == 0) {
            GL11.glMatrixMode(5888);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            RenderHelper.enableStandardItemLighting();
            this.mc.mcProfiler.endStartSection("entities");
            ForgeHooksClient.setRenderPass(0);
            renderglobal.renderEntities(entitylivingbase, frustrum, p_78471_1_);
            ForgeHooksClient.setRenderPass(0);
            RenderHelper.disableStandardItemLighting();
            this.disableLightmap((double)p_78471_1_);
            GL11.glMatrixMode(5888);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            if(this.mc.objectMouseOver != null && entitylivingbase.isInsideOfMaterial(Material.water) && entitylivingbase instanceof EntityPlayer && !this.mc.gameSettings.hideGUI) {
               var19 = (EntityPlayer)entitylivingbase;
               GL11.glDisable(3008);
               this.mc.mcProfiler.endStartSection("outline");
               if(!ForgeHooksClient.onDrawBlockHighlight(renderglobal, var19, this.mc.objectMouseOver, 0, var19.inventory.getCurrentItem(), p_78471_1_)) {
                  renderglobal.drawSelectionBox(var19, this.mc.objectMouseOver, 0, p_78471_1_);
               }

               GL11.glEnable(3008);
            }
         }

         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         if(this.cameraZoom == 1.0D && entitylivingbase instanceof EntityPlayer && !this.mc.gameSettings.hideGUI && this.mc.objectMouseOver != null && !entitylivingbase.isInsideOfMaterial(Material.water)) {
            var19 = (EntityPlayer)entitylivingbase;
            GL11.glDisable(3008);
            this.mc.mcProfiler.endStartSection("outline");
            if(!ForgeHooksClient.onDrawBlockHighlight(renderglobal, var19, this.mc.objectMouseOver, 0, var19.inventory.getCurrentItem(), p_78471_1_)) {
               renderglobal.drawSelectionBox(var19, this.mc.objectMouseOver, 0, p_78471_1_);
            }

            GL11.glEnable(3008);
         }

         this.mc.mcProfiler.endStartSection("destroyProgress");
         GL11.glEnable(3042);
         OpenGlHelper.glBlendFunc(770, 1, 1, 0);
         renderglobal.drawBlockDamageTexture(Tessellator.instance, entitylivingbase, p_78471_1_);
         GL11.glDisable(3042);
         if(this.debugViewDirection == 0) {
            this.enableLightmap((double)p_78471_1_);
            this.mc.mcProfiler.endStartSection("litParticles");
            effectrenderer.renderLitParticles(entitylivingbase, p_78471_1_);
            RenderHelper.disableStandardItemLighting();
            this.setupFog(0, p_78471_1_);
            this.mc.mcProfiler.endStartSection("particles");
            effectrenderer.renderParticles(entitylivingbase, p_78471_1_);
            this.disableLightmap((double)p_78471_1_);
         }

         GL11.glDepthMask(false);
         GL11.glEnable(2884);
         this.mc.mcProfiler.endStartSection("weather");
         this.renderRainSnow(p_78471_1_);
         GL11.glDepthMask(true);
         GL11.glDisable(3042);
         GL11.glEnable(2884);
         OpenGlHelper.glBlendFunc(770, 771, 1, 0);
         GL11.glAlphaFunc(516, 0.1F);
         this.setupFog(0, p_78471_1_);
         GL11.glEnable(3042);
         GL11.glDepthMask(false);
         this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
         if(this.mc.gameSettings.fancyGraphics) {
            this.mc.mcProfiler.endStartSection("water");
            if(this.mc.gameSettings.ambientOcclusion != 0) {
               GL11.glShadeModel(7425);
            }

            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            if(this.mc.gameSettings.anaglyph) {
               if(anaglyphField == 0) {
                  GL11.glColorMask(false, true, true, true);
               } else {
                  GL11.glColorMask(true, false, false, true);
               }

               renderglobal.sortAndRender(entitylivingbase, 1, (double)p_78471_1_);
            } else {
               renderglobal.sortAndRender(entitylivingbase, 1, (double)p_78471_1_);
            }

            GL11.glDisable(3042);
            GL11.glShadeModel(7424);
         } else {
            this.mc.mcProfiler.endStartSection("water");
            renderglobal.sortAndRender(entitylivingbase, 1, (double)p_78471_1_);
         }

         if(this.debugViewDirection == 0) {
            RenderHelper.enableStandardItemLighting();
            this.mc.mcProfiler.endStartSection("entities");
            ForgeHooksClient.setRenderPass(1);
            renderglobal.renderEntities(entitylivingbase, frustrum, p_78471_1_);
            ForgeHooksClient.setRenderPass(-1);
            RenderHelper.disableStandardItemLighting();
         }

         GL11.glDepthMask(true);
         GL11.glEnable(2884);
         GL11.glDisable(3042);
         GL11.glDisable(2912);
         if(entitylivingbase.posY >= 128.0D) {
            this.mc.mcProfiler.endStartSection("aboveClouds");
            this.renderCloudsCheck(renderglobal, p_78471_1_);
         }

         this.mc.mcProfiler.endStartSection("FRenderLast");
         ForgeHooksClient.dispatchRenderLast(renderglobal, p_78471_1_);
         this.mc.mcProfiler.endStartSection("hand");
         if(!this.mc.gameSettings.anaglyph) {
            this.mc.mcProfiler.endSection();
            return;
         }
      }

      GL11.glColorMask(true, true, true, false);
      this.mc.mcProfiler.endSection();
   }

   private void renderCloudsCheck(RenderGlobal p_82829_1_, float p_82829_2_) {
      if(this.mc.gameSettings.shouldRenderClouds()) {
         this.mc.mcProfiler.endStartSection("clouds");
         GL11.glPushMatrix();
         this.setupFog(0, p_82829_2_);
         GL11.glEnable(2912);
         p_82829_1_.renderClouds(p_82829_2_);
         GL11.glDisable(2912);
         this.setupFog(1, p_82829_2_);
         GL11.glPopMatrix();
      }

   }

   private void addRainParticles() {
      float f = this.mc.theWorld.getRainStrength(1.0F);
      if(!this.mc.gameSettings.fancyGraphics) {
         f /= 2.0F;
      }

      if(f != 0.0F) {
         this.random.setSeed((long)this.rendererUpdateCount * 312987231L);
         EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
         WorldClient worldclient = this.mc.theWorld;
         int i = MathHelper.floor_double(entitylivingbase.posX);
         int j = MathHelper.floor_double(entitylivingbase.posY);
         int k = MathHelper.floor_double(entitylivingbase.posZ);
         byte b0 = 10;
         double d0 = 0.0D;
         double d1 = 0.0D;
         double d2 = 0.0D;
         int l = 0;
         int i1 = (int)(100.0F * f * f);
         if(this.mc.gameSettings.particleSetting == 1) {
            i1 >>= 1;
         } else if(this.mc.gameSettings.particleSetting == 2) {
            i1 = 0;
         }

         for(int j1 = 0; j1 < i1; ++j1) {
            int k1 = i + this.random.nextInt(b0) - this.random.nextInt(b0);
            int l1 = k + this.random.nextInt(b0) - this.random.nextInt(b0);
            int i2 = worldclient.getPrecipitationHeight(k1, l1);
            Block block = worldclient.getBlock(k1, i2 - 1, l1);
            BiomeGenBase biomegenbase = worldclient.getBiomeGenForCoords(k1, l1);
            if(i2 <= j + b0 && i2 >= j - b0 && biomegenbase.canSpawnLightningBolt() && biomegenbase.getFloatTemperature(k1, i2, l1) >= 0.15F) {
               float f1 = this.random.nextFloat();
               float f2 = this.random.nextFloat();
               if(block.getMaterial() == Material.lava) {
                  this.mc.effectRenderer.addEffect(new EntitySmokeFX(worldclient, (double)((float)k1 + f1), (double)((float)i2 + 0.1F) - block.getBlockBoundsMinY(), (double)((float)l1 + f2), 0.0D, 0.0D, 0.0D));
               } else if(block.getMaterial() != Material.air) {
                  ++l;
                  if(this.random.nextInt(l) == 0) {
                     d0 = (double)((float)k1 + f1);
                     d1 = (double)((float)i2 + 0.1F) - block.getBlockBoundsMinY();
                     d2 = (double)((float)l1 + f2);
                  }

                  this.mc.effectRenderer.addEffect(new EntityRainFX(worldclient, (double)((float)k1 + f1), (double)((float)i2 + 0.1F) - block.getBlockBoundsMinY(), (double)((float)l1 + f2)));
               }
            }
         }

         if(l > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
            this.rainSoundCounter = 0;
            if(d1 > entitylivingbase.posY + 1.0D && worldclient.getPrecipitationHeight(MathHelper.floor_double(entitylivingbase.posX), MathHelper.floor_double(entitylivingbase.posZ)) > MathHelper.floor_double(entitylivingbase.posY)) {
               this.mc.theWorld.playSound(d0, d1, d2, "ambient.weather.rain", 0.1F, 0.5F, false);
            } else {
               this.mc.theWorld.playSound(d0, d1, d2, "ambient.weather.rain", 0.2F, 1.0F, false);
            }
         }
      }

   }

   protected void renderRainSnow(float p_78474_1_) {
      IRenderHandler renderer = null;
      if((renderer = this.mc.theWorld.provider.getWeatherRenderer()) != null) {
         renderer.render(p_78474_1_, this.mc.theWorld, this.mc);
      } else {
         float f1 = this.mc.theWorld.getRainStrength(p_78474_1_);
         if(f1 > 0.0F) {
            this.enableLightmap((double)p_78474_1_);
            if(this.rainXCoords == null) {
               this.rainXCoords = new float[1024];
               this.rainYCoords = new float[1024];

               for(int entitylivingbase = 0; entitylivingbase < 32; ++entitylivingbase) {
                  for(int worldclient = 0; worldclient < 32; ++worldclient) {
                     float k2 = (float)(worldclient - 16);
                     float l2 = (float)(entitylivingbase - 16);
                     float i3 = MathHelper.sqrt_float(k2 * k2 + l2 * l2);
                     this.rainXCoords[entitylivingbase << 5 | worldclient] = -l2 / i3;
                     this.rainYCoords[entitylivingbase << 5 | worldclient] = k2 / i3;
                  }
               }
            }

            EntityLivingBase var42 = this.mc.renderViewEntity;
            WorldClient var43 = this.mc.theWorld;
            int var44 = MathHelper.floor_double(var42.posX);
            int var45 = MathHelper.floor_double(var42.posY);
            int var46 = MathHelper.floor_double(var42.posZ);
            Tessellator tessellator = Tessellator.instance;
            GL11.glDisable(2884);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glAlphaFunc(516, 0.1F);
            double d0 = var42.lastTickPosX + (var42.posX - var42.lastTickPosX) * (double)p_78474_1_;
            double d1 = var42.lastTickPosY + (var42.posY - var42.lastTickPosY) * (double)p_78474_1_;
            double d2 = var42.lastTickPosZ + (var42.posZ - var42.lastTickPosZ) * (double)p_78474_1_;
            int k = MathHelper.floor_double(d1);
            byte b0 = 5;
            if(this.mc.gameSettings.fancyGraphics) {
               b0 = 10;
            }

            boolean flag = false;
            byte b1 = -1;
            float f5 = (float)this.rendererUpdateCount + p_78474_1_;
            if(this.mc.gameSettings.fancyGraphics) {
               b0 = 10;
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            flag = false;

            for(int l = var46 - b0; l <= var46 + b0; ++l) {
               for(int i1 = var44 - b0; i1 <= var44 + b0; ++i1) {
                  int j1 = (l - var46 + 16) * 32 + i1 - var44 + 16;
                  float f6 = this.rainXCoords[j1] * 0.5F;
                  float f7 = this.rainYCoords[j1] * 0.5F;
                  BiomeGenBase biomegenbase = var43.getBiomeGenForCoords(i1, l);
                  if(biomegenbase.canSpawnLightningBolt() || biomegenbase.getEnableSnow()) {
                     int k1 = var43.getPrecipitationHeight(i1, l);
                     int l1 = var45 - b0;
                     int i2 = var45 + b0;
                     if(l1 < k1) {
                        l1 = k1;
                     }

                     if(i2 < k1) {
                        i2 = k1;
                     }

                     float f8 = 1.0F;
                     int j2 = k1;
                     if(k1 < k) {
                        j2 = k;
                     }

                     if(l1 != i2) {
                        this.random.setSeed((long)(i1 * i1 * 3121 + i1 * 45238971 ^ l * l * 418711 + l * 13761));
                        float f9 = biomegenbase.getFloatTemperature(i1, l1, l);
                        float f10;
                        double d4;
                        if(var43.getWorldChunkManager().getTemperatureAtHeight(f9, k1) >= 0.15F) {
                           if(b1 != 0) {
                              if(b1 >= 0) {
                                 tessellator.draw();
                              }

                              b1 = 0;
                              this.mc.getTextureManager().bindTexture(locationRainPng);
                              tessellator.startDrawingQuads();
                           }

                           f10 = ((float)(this.rendererUpdateCount + i1 * i1 * 3121 + i1 * 45238971 + l * l * 418711 + l * 13761 & 31) + p_78474_1_) / 32.0F * (3.0F + this.random.nextFloat());
                           double f16 = (double)((float)i1 + 0.5F) - var42.posX;
                           d4 = (double)((float)l + 0.5F) - var42.posZ;
                           float d5 = MathHelper.sqrt_double(f16 * f16 + d4 * d4) / (float)b0;
                           float f13 = 1.0F;
                           tessellator.setBrightness(var43.getLightBrightnessForSkyBlocks(i1, j2, l, 0));
                           tessellator.setColorRGBA_F(f13, f13, f13, ((1.0F - d5 * d5) * 0.5F + 0.5F) * f1);
                           tessellator.setTranslation(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                           tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8), (double)((float)l1 * f8 / 4.0F + f10 * f8));
                           tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8), (double)((float)l1 * f8 / 4.0F + f10 * f8));
                           tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8), (double)((float)i2 * f8 / 4.0F + f10 * f8));
                           tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8), (double)((float)i2 * f8 / 4.0F + f10 * f8));
                           tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                        } else {
                           if(b1 != 1) {
                              if(b1 >= 0) {
                                 tessellator.draw();
                              }

                              b1 = 1;
                              this.mc.getTextureManager().bindTexture(locationSnowPng);
                              tessellator.startDrawingQuads();
                           }

                           f10 = ((float)(this.rendererUpdateCount & 511) + p_78474_1_) / 512.0F;
                           float var47 = this.random.nextFloat() + f5 * 0.01F * (float)this.random.nextGaussian();
                           float f11 = this.random.nextFloat() + f5 * (float)this.random.nextGaussian() * 0.001F;
                           d4 = (double)((float)i1 + 0.5F) - var42.posX;
                           double var48 = (double)((float)l + 0.5F) - var42.posZ;
                           float f14 = MathHelper.sqrt_double(d4 * d4 + var48 * var48) / (float)b0;
                           float f15 = 1.0F;
                           tessellator.setBrightness((var43.getLightBrightnessForSkyBlocks(i1, j2, l, 0) * 3 + 15728880) / 4);
                           tessellator.setColorRGBA_F(f15, f15, f15, ((1.0F - f14 * f14) * 0.3F + 0.5F) * f1);
                           tessellator.setTranslation(-d0 * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                           tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)l1, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + var47), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
                           tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)l1, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + var47), (double)((float)l1 * f8 / 4.0F + f10 * f8 + f11));
                           tessellator.addVertexWithUV((double)((float)i1 + f6) + 0.5D, (double)i2, (double)((float)l + f7) + 0.5D, (double)(1.0F * f8 + var47), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
                           tessellator.addVertexWithUV((double)((float)i1 - f6) + 0.5D, (double)i2, (double)((float)l - f7) + 0.5D, (double)(0.0F * f8 + var47), (double)((float)i2 * f8 / 4.0F + f10 * f8 + f11));
                           tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                        }
                     }
                  }
               }
            }

            if(b1 >= 0) {
               tessellator.draw();
            }

            GL11.glEnable(2884);
            GL11.glDisable(3042);
            GL11.glAlphaFunc(516, 0.1F);
            this.disableLightmap((double)p_78474_1_);
         }

      }
   }

   public void setupOverlayRendering() {
      ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
      GL11.glClear(256);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
   }

   private void updateFogColor(float p_78466_1_) {
      WorldClient worldclient = this.mc.theWorld;
      EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
      float f1 = 0.25F + 0.75F * (float)this.mc.gameSettings.renderDistanceChunks / 16.0F;
      f1 = 1.0F - (float)Math.pow((double)f1, 0.25D);
      Vec3 vec3 = worldclient.getSkyColor(this.mc.renderViewEntity, p_78466_1_);
      float f2 = (float)vec3.xCoord;
      float f3 = (float)vec3.yCoord;
      float f4 = (float)vec3.zCoord;
      Vec3 vec31 = worldclient.getFogColor(p_78466_1_);
      this.fogColorRed = (float)vec31.xCoord;
      this.fogColorGreen = (float)vec31.yCoord;
      this.fogColorBlue = (float)vec31.zCoord;
      float f5;
      if(this.mc.gameSettings.renderDistanceChunks >= 4) {
         Vec3 f8 = MathHelper.sin(worldclient.getCelestialAngleRadians(p_78466_1_)) > 0.0F?Vec3.createVectorHelper(-1.0D, 0.0D, 0.0D):Vec3.createVectorHelper(1.0D, 0.0D, 0.0D);
         f5 = (float)entitylivingbase.getLook(p_78466_1_).dotProduct(f8);
         if(f5 < 0.0F) {
            f5 = 0.0F;
         }

         if(f5 > 0.0F) {
            float[] f9 = worldclient.provider.calcSunriseSunsetColors(worldclient.getCelestialAngle(p_78466_1_), p_78466_1_);
            if(f9 != null) {
               f5 *= f9[3];
               this.fogColorRed = this.fogColorRed * (1.0F - f5) + f9[0] * f5;
               this.fogColorGreen = this.fogColorGreen * (1.0F - f5) + f9[1] * f5;
               this.fogColorBlue = this.fogColorBlue * (1.0F - f5) + f9[2] * f5;
            }
         }
      }

      this.fogColorRed += (f2 - this.fogColorRed) * f1;
      this.fogColorGreen += (f3 - this.fogColorGreen) * f1;
      this.fogColorBlue += (f4 - this.fogColorBlue) * f1;
      float f81 = worldclient.getRainStrength(p_78466_1_);
      float f91;
      if(f81 > 0.0F) {
         f5 = 1.0F - f81 * 0.5F;
         f91 = 1.0F - f81 * 0.4F;
         this.fogColorRed *= f5;
         this.fogColorGreen *= f5;
         this.fogColorBlue *= f91;
      }

      f5 = worldclient.getWeightedThunderStrength(p_78466_1_);
      if(f5 > 0.0F) {
         f91 = 1.0F - f5 * 0.5F;
         this.fogColorRed *= f91;
         this.fogColorGreen *= f91;
         this.fogColorBlue *= f91;
      }

      Block block = ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, entitylivingbase, p_78466_1_);
      float f10;
      if(this.cloudFog) {
         Vec3 d0 = worldclient.getCloudColour(p_78466_1_);
         this.fogColorRed = (float)d0.xCoord;
         this.fogColorGreen = (float)d0.yCoord;
         this.fogColorBlue = (float)d0.zCoord;
      } else if(block.getMaterial() == Material.water) {
         f10 = (float)EnchantmentHelper.getRespiration(entitylivingbase) * 0.2F;
         this.fogColorRed = 0.02F + f10;
         this.fogColorGreen = 0.02F + f10;
         this.fogColorBlue = 0.2F + f10;
      } else if(block.getMaterial() == Material.lava) {
         this.fogColorRed = 0.6F;
         this.fogColorGreen = 0.1F;
         this.fogColorBlue = 0.0F;
      }

      f10 = this.fogColor2 + (this.fogColor1 - this.fogColor2) * p_78466_1_;
      this.fogColorRed *= f10;
      this.fogColorGreen *= f10;
      this.fogColorBlue *= f10;
      double d01 = (entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)p_78466_1_) * worldclient.provider.getVoidFogYFactor();
      if(entitylivingbase.isPotionActive(Potion.blindness)) {
         int f11 = entitylivingbase.getActivePotionEffect(Potion.blindness).getDuration();
         if(f11 < 20) {
            d01 *= (double)(1.0F - (float)f11 / 20.0F);
         } else {
            d01 = 0.0D;
         }
      }

      if(d01 < 1.0D) {
         if(d01 < 0.0D) {
            d01 = 0.0D;
         }

         d01 *= d01;
         this.fogColorRed = (float)((double)this.fogColorRed * d01);
         this.fogColorGreen = (float)((double)this.fogColorGreen * d01);
         this.fogColorBlue = (float)((double)this.fogColorBlue * d01);
      }

      float f111;
      if(this.bossColorModifier > 0.0F) {
         f111 = this.bossColorModifierPrev + (this.bossColorModifier - this.bossColorModifierPrev) * p_78466_1_;
         this.fogColorRed = this.fogColorRed * (1.0F - f111) + this.fogColorRed * 0.7F * f111;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f111) + this.fogColorGreen * 0.6F * f111;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f111) + this.fogColorBlue * 0.6F * f111;
      }

      float f6;
      if(entitylivingbase.isPotionActive(Potion.nightVision)) {
         f111 = this.getNightVisionBrightness(this.mc.thePlayer, p_78466_1_);
         f6 = 1.0F / this.fogColorRed;
         if(f6 > 1.0F / this.fogColorGreen) {
            f6 = 1.0F / this.fogColorGreen;
         }

         if(f6 > 1.0F / this.fogColorBlue) {
            f6 = 1.0F / this.fogColorBlue;
         }

         this.fogColorRed = this.fogColorRed * (1.0F - f111) + this.fogColorRed * f6 * f111;
         this.fogColorGreen = this.fogColorGreen * (1.0F - f111) + this.fogColorGreen * f6 * f111;
         this.fogColorBlue = this.fogColorBlue * (1.0F - f111) + this.fogColorBlue * f6 * f111;
      }

      if(this.mc.gameSettings.anaglyph) {
         f111 = (this.fogColorRed * 30.0F + this.fogColorGreen * 59.0F + this.fogColorBlue * 11.0F) / 100.0F;
         f6 = (this.fogColorRed * 30.0F + this.fogColorGreen * 70.0F) / 100.0F;
         float event = (this.fogColorRed * 30.0F + this.fogColorBlue * 70.0F) / 100.0F;
         this.fogColorRed = f111;
         this.fogColorGreen = f6;
         this.fogColorBlue = event;
      }

      FogColors event1 = new FogColors(this, entitylivingbase, block, (double)p_78466_1_, this.fogColorRed, this.fogColorGreen, this.fogColorBlue);
      MinecraftForge.EVENT_BUS.post(event1);
      this.fogColorRed = event1.red;
      this.fogColorBlue = event1.blue;
      this.fogColorGreen = event1.green;
      GL11.glClearColor(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 0.0F);
   }

   private void setupFog(int p_78468_1_, float p_78468_2_) {
      EntityLivingBase entitylivingbase = this.mc.renderViewEntity;
      boolean flag = false;
      if(entitylivingbase instanceof EntityPlayer) {
         flag = ((EntityPlayer)entitylivingbase).capabilities.isCreativeMode;
      }

      if(p_78468_1_ == 999) {
         GL11.glFog(2918, this.setFogColorBuffer(0.0F, 0.0F, 0.0F, 1.0F));
         GL11.glFogi(2917, 9729);
         GL11.glFogf(2915, 0.0F);
         GL11.glFogf(2916, 8.0F);
         if(GLContext.getCapabilities().GL_NV_fog_distance) {
            GL11.glFogi('蕚', '蕛');
         }

         GL11.glFogf(2915, 0.0F);
      } else {
         GL11.glFog(2918, this.setFogColorBuffer(this.fogColorRed, this.fogColorGreen, this.fogColorBlue, 1.0F));
         GL11.glNormal3f(0.0F, -1.0F, 0.0F);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         Block block = ActiveRenderInfo.getBlockAtEntityViewpoint(this.mc.theWorld, entitylivingbase, p_78468_2_);
         FogDensity event = new FogDensity(this, entitylivingbase, block, (double)p_78468_2_, 0.1F);
         if(MinecraftForge.EVENT_BUS.post(event)) {
            GL11.glFogf(2914, event.density);
         } else {
            float f1;
            if(entitylivingbase.isPotionActive(Potion.blindness)) {
               f1 = 5.0F;
               int d0 = entitylivingbase.getActivePotionEffect(Potion.blindness).getDuration();
               if(d0 < 20) {
                  f1 = 5.0F + (this.farPlaneDistance - 5.0F) * (1.0F - (float)d0 / 20.0F);
               }

               GL11.glFogi(2917, 9729);
               if(p_78468_1_ < 0) {
                  GL11.glFogf(2915, 0.0F);
                  GL11.glFogf(2916, f1 * 0.8F);
               } else {
                  GL11.glFogf(2915, f1 * 0.25F);
                  GL11.glFogf(2916, f1);
               }

               if(GLContext.getCapabilities().GL_NV_fog_distance) {
                  GL11.glFogi('蕚', '蕛');
               }
            } else if(this.cloudFog) {
               GL11.glFogi(2917, 2048);
               GL11.glFogf(2914, 0.1F);
            } else if(block.getMaterial() == Material.water) {
               GL11.glFogi(2917, 2048);
               if(entitylivingbase.isPotionActive(Potion.waterBreathing)) {
                  GL11.glFogf(2914, 0.05F);
               } else {
                  GL11.glFogf(2914, 0.1F - (float)EnchantmentHelper.getRespiration(entitylivingbase) * 0.03F);
               }
            } else if(block.getMaterial() == Material.lava) {
               GL11.glFogi(2917, 2048);
               GL11.glFogf(2914, 2.0F);
            } else {
               f1 = this.farPlaneDistance;
               if(this.mc.theWorld.provider.getWorldHasVoidParticles() && !flag) {
                  double d01 = (double)((entitylivingbase.getBrightnessForRender(p_78468_2_) & 15728640) >> 20) / 16.0D + (entitylivingbase.lastTickPosY + (entitylivingbase.posY - entitylivingbase.lastTickPosY) * (double)p_78468_2_ + 4.0D) / 32.0D;
                  if(d01 < 1.0D) {
                     if(d01 < 0.0D) {
                        d01 = 0.0D;
                     }

                     d01 *= d01;
                     float f2 = 100.0F * (float)d01;
                     if(f2 < 5.0F) {
                        f2 = 5.0F;
                     }

                     if(f1 > f2) {
                        f1 = f2;
                     }
                  }
               }

               GL11.glFogi(2917, 9729);
               if(p_78468_1_ < 0) {
                  GL11.glFogf(2915, 0.0F);
                  GL11.glFogf(2916, f1);
               } else {
                  GL11.glFogf(2915, f1 * 0.75F);
                  GL11.glFogf(2916, f1);
               }

               if(GLContext.getCapabilities().GL_NV_fog_distance) {
                  GL11.glFogi('蕚', '蕛');
               }

               if(this.mc.theWorld.provider.doesXZShowFog((int)entitylivingbase.posX, (int)entitylivingbase.posZ)) {
                  GL11.glFogf(2915, f1 * 0.05F);
                  GL11.glFogf(2916, Math.min(f1, 192.0F) * 0.5F);
               }

               MinecraftForge.EVENT_BUS.post(new RenderFogEvent(this, entitylivingbase, block, (double)p_78468_2_, p_78468_1_, f1));
            }
         }

         GL11.glEnable(2903);
         GL11.glColorMaterial(1028, 4608);
      }

   }

   private FloatBuffer setFogColorBuffer(float p_78469_1_, float p_78469_2_, float p_78469_3_, float p_78469_4_) {
      this.fogColorBuffer.clear();
      this.fogColorBuffer.put(p_78469_1_).put(p_78469_2_).put(p_78469_3_).put(p_78469_4_);
      this.fogColorBuffer.flip();
      return this.fogColorBuffer;
   }

   public MapItemRenderer getMapItemRenderer() {
      return this.theMapItemRenderer;
   }

   public void setPrepareTerrain(boolean b) {
   }

   static {
      shaderCount = shaderResourceLocations.length;
   }
}
