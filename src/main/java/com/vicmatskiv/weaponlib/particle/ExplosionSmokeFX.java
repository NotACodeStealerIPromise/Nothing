package com.vicmatskiv.weaponlib.particle;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticle;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class ExplosionSmokeFX extends CompatibleParticle {
   private static final String SMOKE_TEXTURE = "weaponlib:/com/vicmatskiv/weaponlib/resources/large-smoke.png";
   private int imageIndex;
   private static final int columnCount = 4;
   private static final int rowCount = 4;
   private static final ExplosionSmokeFX.TriFunction EXPLOSION_SCALE_UPDATE_FUNCTION = (currentScale, ticks, maxTicks) -> {
      if(currentScale.floatValue() > 25.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.0008F);
      } else if(currentScale.floatValue() > 20.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.002F);
      } else if(currentScale.floatValue() > 15.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.004F);
      } else if(currentScale.floatValue() > 10.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.05F);
      } else {
         currentScale = Float.valueOf(currentScale.floatValue() * 3.0F);
      }

      return currentScale;
   };
   private static final ExplosionSmokeFX.TriFunction SMOKE_GRENADE_SCALE_UPDATE_FUNCTION = (currentScale, ticks, maxTicks) -> {
      if(currentScale.floatValue() > 25.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.0008F);
      } else if(currentScale.floatValue() > 20.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.002F);
      } else if(currentScale.floatValue() > 15.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.004F);
      } else if(currentScale.floatValue() > 5.0F) {
         currentScale = Float.valueOf(currentScale.floatValue() * 1.05F);
      } else {
         currentScale = Float.valueOf(currentScale.floatValue() * 2.0F);
      }

      return currentScale;
   };
   private static final ExplosionSmokeFX.TriFunction EXPLOSION_ALPHA_UPDATE_FUNCTION = (currentAlpha, ticks, maxTicks) -> {
      double alphaRadians = 0.7853981633974483D + 3.141592653589793D * (double)((float)ticks.intValue()) / (double)((float)maxTicks.intValue());
      return Float.valueOf(0.3F * (float)Math.sin(alphaRadians > 3.141592653589793D?3.141592653589793D:alphaRadians));
   };
   private static final ExplosionSmokeFX.TriFunction SMOKE_GRENADE_ALPHA_UPDATE_FUNCTION = (currentAlpha, ticks, maxTicks) -> {
      double alphaRadians = 0.7853981633974483D + 3.141592653589793D * (double)((float)ticks.intValue()) / (double)((float)maxTicks.intValue());
      return Float.valueOf(0.3F * (float)Math.sin(alphaRadians > 3.141592653589793D?3.141592653589793D:alphaRadians));
   };
   private ExplosionSmokeFX.Behavior behavior;

   public ExplosionSmokeFX(World par1World, double positionX, double positionY, double positionZ, float scale, float motionX, float motionY, float motionZ, int particleMaxAge, ExplosionSmokeFX.Behavior behavior) {
      super(par1World, positionX, positionY, positionZ, 0.0D, 0.0D, 0.0D);
      this.motionX = (double)motionX;
      this.motionY = (double)motionY;
      this.motionZ = (double)motionZ;
      if(motionX == 0.0F) {
         motionX = 1.0F;
      }

      this.behavior = behavior;
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.particleAlpha = 0.0F;
      this.particleScale *= scale;
      this.particleMaxAge = particleMaxAge == 0?50 + (int)(this.rand.nextFloat() * 30.0F):particleMaxAge;
      this.imageIndex = this.rand.nextInt(16);
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if(this.particleAge++ >= this.particleMaxAge) {
         this.setExpired();
      }

      this.motionY += 1.0E-5D;
      CompatibilityProvider.compatibility.moveParticle(this, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.599999785423279D;
      this.motionY *= 0.9999999785423279D;
      this.motionZ *= 0.599999785423279D;
      this.particleAlpha = ((Float)this.behavior.alphaUpdateFunction.apply(Float.valueOf(this.particleAlpha), Integer.valueOf(this.particleAge), Integer.valueOf(this.particleMaxAge))).floatValue();
      this.particleScale = ((Float)this.behavior.scaleUpdateFunction.apply(Float.valueOf(this.particleScale), Integer.valueOf(this.particleAge), Integer.valueOf(this.particleMaxAge))).floatValue();
      if(this.isCollided()) {
         this.motionX *= 0.699999988079071D;
         this.motionZ *= 0.699999988079071D;
      }

   }

   public void renderParticle(CompatibleTessellator tessellator, float partialTicks, float par3, float par4, float par5, float par6, float par7) {
      Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/large-smoke.png"));
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
      float columnWidth = 0.25F;
      float rowHeight = 0.25F;
      int rowIndex = Math.floorDiv(this.imageIndex, 4);
      int columnIndex = this.imageIndex % 4;
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

   public static enum Behavior {
      EXPLOSION,
      SMOKE_GRENADE;

      private ExplosionSmokeFX.TriFunction scaleUpdateFunction;
      private ExplosionSmokeFX.TriFunction alphaUpdateFunction;

      private Behavior(ExplosionSmokeFX.TriFunction scaleUpdateFunction, ExplosionSmokeFX.TriFunction alphaUpdateFunction) {
         this.scaleUpdateFunction = scaleUpdateFunction;
         this.alphaUpdateFunction = alphaUpdateFunction;
      }

      static {
         EXPLOSION = new ExplosionSmokeFX.Behavior("EXPLOSION", 0, ExplosionSmokeFX.EXPLOSION_SCALE_UPDATE_FUNCTION, ExplosionSmokeFX.EXPLOSION_ALPHA_UPDATE_FUNCTION);
         SMOKE_GRENADE = new ExplosionSmokeFX.Behavior("SMOKE_GRENADE", 1, ExplosionSmokeFX.SMOKE_GRENADE_SCALE_UPDATE_FUNCTION, ExplosionSmokeFX.SMOKE_GRENADE_ALPHA_UPDATE_FUNCTION);
      }
   }

   public interface TriFunction {
      Object apply(Object var1, Object var2, Object var3);
   }
}
