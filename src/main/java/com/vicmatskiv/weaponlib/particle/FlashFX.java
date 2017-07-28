package com.vicmatskiv.weaponlib.particle;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticle;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class FlashFX extends CompatibleParticle {
   private static final float FLASH_ALPHA_FACTOR = 0.8F;
   private static final double FLASH_SCALE_FACTOR = 1.1D;
   private static final String FLASH_TEXTURE = "weaponlib:/com/vicmatskiv/weaponlib/resources/flashes.png";
   private int imageIndex;
   private static final int imagesPerRow = 4;

   public FlashFX(World par1World, double positionX, double positionY, double positionZ, float scale, float alpha, float motionX, float motionY, float motionZ) {
      super(par1World, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);
      this.motionX = (double)motionX;
      this.motionY = (double)motionY;
      this.motionZ = (double)motionZ;
      if(motionX == 0.0F) {
         motionX = 0.01F;
      }

      if(motionZ == 0.0F) {
         motionZ = 0.01F;
      }

      if(motionY == 0.0F) {
         motionY = 0.01F;
      }

      this.particleTextureIndexX = 0;
      this.particleTextureIndexY = 0;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleAlpha = alpha;
      this.particleScale *= 1.4F;
      this.particleScale *= scale;
      this.particleMaxAge = 2;
      this.imageIndex = this.rand.nextInt() % 4;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if(this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      CompatibilityProvider.compatibility.moveParticle(this, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9999999785423279D;
      this.motionY *= 0.9999999785423279D;
      this.motionZ *= 0.9999999785423279D;
      this.particleAlpha *= 0.8F;
      this.particleScale = (float)((double)this.particleScale * 1.1D);
      if(this.isCollided()) {
         this.motionX *= 0.699999988079071D;
         this.motionZ *= 0.699999988079071D;
      }

   }

   public void renderParticle(CompatibleTessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/flashes.png"));
      GL11.glPushMatrix();
      GL11.glPushAttrib(8192);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      tessellator.startDrawingParticles();
      int i = this.getBrightnessForRender(partialTicks);
      int j = i >> 16 & '\uffff';
      int k = i & '\uffff';
      tessellator.setLightMap(j, k);
      RenderHelper.disableStandardItemLighting();
      float f10 = 0.1F * this.particleScale;
      float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
      float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
      float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
      tessellator.setColorRgba(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
      float uWidth = 0.25F;
      float aU = (float)(this.imageIndex + 1) * uWidth;
      float aV = 1.0F;
      float bU = (float)(this.imageIndex + 1) * uWidth;
      float bV = 0.0F;
      float cU = (float)this.imageIndex * uWidth;
      float cV = 0.0F;
      float dU = (float)this.imageIndex * uWidth;
      float dV = 1.0F;
      tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), aU, aV);
      tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), bU, bV);
      tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), cU, cV);
      tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), dU, dV);
      tessellator.draw();
      RenderHelper.enableStandardItemLighting();
      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public int getFXLayer() {
      return 3;
   }
}
