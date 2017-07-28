package com.vicmatskiv.weaponlib.particle;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticle;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ExplosionParticleFX extends CompatibleParticle {
   private static final String TEXTURE = "weaponlib:/com/vicmatskiv/weaponlib/resources/explosion-particles.png";
   private int imageIndex;
   private static final int columnCount = 5;
   private static final int rowCount = 5;

   public ExplosionParticleFX(World par1World, double positionX, double positionY, double positionZ, float scale, float motionX, float motionY, float motionZ) {
      this(par1World, positionY, positionY, positionZ, scale, (double)motionX, (double)motionY, (double)motionZ, 0);
   }

   public ExplosionParticleFX(World par1World, double positionX, double positionY, double positionZ, float scale, double motionX, double motionY, double motionZ, int particleMaxAge) {
      super(par1World, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);
      this.motionX = motionX;
      this.motionY = motionY;
      this.motionZ = motionZ;
      if(motionX == 0.0D) {
         motionX = 1.0D;
      }

      this.particleTextureIndexX = 0;
      this.particleTextureIndexY = 0;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleAlpha = 1.0F;
      this.particleScale = scale;
      this.particleMaxAge = particleMaxAge == 0?50 + (int)(this.rand.nextFloat() * 30.0F):particleMaxAge;
      this.imageIndex = this.rand.nextInt(25);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if(this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.motionY += 0.004D;
      CompatibilityProvider.compatibility.moveParticle(this, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.99D;
      this.motionY *= 0.99D;
      this.motionY -= 0.07D;
      this.motionZ *= 0.99D;
      if(CompatibilityProvider.compatibility.isCollided(this)) {
         this.motionX *= 0.699999988079071D;
         this.motionZ *= 0.699999988079071D;
      }

      this.particleAlpha = this.particleAge < 9?1.0F:(float)(1 + 9 / this.particleMaxAge - this.particleAge / this.particleMaxAge);
   }

   public void renderParticle(CompatibleTessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/explosion-particles.png"));
      GL11.glPushMatrix();
      GL11.glPushAttrib(8192);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDepthMask(false);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      RenderHelper.disableStandardItemLighting();
      tessellator.startDrawingParticles();
      float f10 = 0.1F * this.particleScale;
      float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
      float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
      float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
      tessellator.setColorRgba(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha);
      int i = this.getBrightnessForRender(partialTicks);
      int j = i >> 16 & '\uffff';
      int k = i & '\uffff';
      tessellator.setLightMap(j, k);
      float columnWidth = 0.2F;
      float rowHeight = 0.2F;
      int rowIndex = Math.floorDiv(this.imageIndex, 5);
      int columnIndex = this.imageIndex % 5;
      float aU = (float)(columnIndex + 1) * columnWidth;
      float aV = (float)(rowIndex + 1) * rowHeight;
      float bV = (float)rowIndex * rowHeight;
      float cU = (float)columnIndex * columnWidth;
      tessellator.addVertexWithUV((double)(f11 - par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 - par5 * f10 - par7 * f10), aU, aV);
      tessellator.addVertexWithUV((double)(f11 - par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 - par5 * f10 + par7 * f10), aU, bV);
      tessellator.addVertexWithUV((double)(f11 + par3 * f10 + par6 * f10), (double)(f12 + par4 * f10), (double)(f13 + par5 * f10 + par7 * f10), cU, bV);
      tessellator.addVertexWithUV((double)(f11 + par3 * f10 - par6 * f10), (double)(f12 - par4 * f10), (double)(f13 + par5 * f10 - par7 * f10), cU, aV);
      tessellator.draw();
      RenderHelper.enableStandardItemLighting();
      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public int getFXLayer() {
      return 3;
   }
}
