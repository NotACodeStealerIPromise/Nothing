package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticleManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWorldRenderer;
import com.vicmatskiv.weaponlib.perspective.PerspectiveManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.shader.Framebuffer;

public abstract class Perspective {
   protected ClientModContext modContext;
   protected Framebuffer framebuffer;
   protected int width;
   protected int height;
   protected CompatibleWorldRenderer entityRenderer;
   protected RenderGlobal renderGlobal;
   protected CompatibleParticleManager effectRenderer;

   public void activate(ClientModContext modContext, PerspectiveManager manager) {
      this.modContext = modContext;
      if(this.framebuffer == null) {
         this.framebuffer = new Framebuffer(this.width, this.height, true);
         this.framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

      this.entityRenderer = manager.getEntityRenderer();
      this.effectRenderer = manager.getEffectRenderer();
      this.renderGlobal = manager.getRenderGlobal();
   }

   public void deactivate(ClientModContext modContext) {
      this.framebuffer.framebufferClear();
   }

   public float getBrightness(RenderContext context) {
      return 1.0F;
   }

   public int getTexture(RenderContext context) {
      return this.framebuffer != null?this.framebuffer.framebufferTexture:-1;
   }

   public Framebuffer getFramebuffer() {
      return this.framebuffer;
   }

   public abstract void update(CompatibleRenderTickEvent var1);
}
