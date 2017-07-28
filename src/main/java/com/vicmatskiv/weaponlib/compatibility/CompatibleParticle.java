package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBreakingFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public abstract class CompatibleParticle extends EntityFX {
   public static CompatibleParticle.CompatibleParticleBreaking createParticleBreaking(ModContext modContext, World worldIn, double posXIn, double posYIn, double posZIn) {
      return new CompatibleParticle.CompatibleParticleBreaking(modContext, worldIn, posXIn, posYIn, posZIn);
   }

   public CompatibleParticle(World par1World, double positionX, double positionY, double positionZ, double motionX, double motionY, double motionZ) {
      super(par1World, positionX, positionY, positionZ, motionX, motionY, motionZ);
   }

   protected void setExpired() {
      this.setDead();
   }

   protected boolean isCollided() {
      return this.onGround;
   }

   @SideOnly(Side.CLIENT)
   public final void renderParticle(Tessellator tessellator, float p_70539_2_, float p_70539_3_, float p_70539_4_, float p_70539_5_, float p_70539_6_, float p_70539_7_) {
      this.renderParticle(CompatibleTessellator.getInstance(), p_70539_2_, p_70539_3_, p_70539_4_, p_70539_5_, p_70539_6_, p_70539_7_);
   }

   public abstract void renderParticle(CompatibleTessellator var1, float var2, float var3, float var4, float var5, float var6, float var7);

   public static class CompatibleParticleBreaking extends EntityBreakingFX {
      public static final String TEXTURE_BLOOD_PARTICLES = "particle/blood";

      protected CompatibleParticleBreaking(ModContext modContext, World worldIn, double posXIn, double posYIn, double posZIn) {
         super(worldIn, posXIn, posYIn, posZIn, Items.snowball);
         TextureAtlasSprite sprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(modContext.getNamedResource("particle/blood").toString());
         this.setParticleIcon(sprite);
      }
   }
}
