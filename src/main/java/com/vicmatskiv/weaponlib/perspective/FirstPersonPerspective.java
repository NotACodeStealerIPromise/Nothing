package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.RenderingPhase;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.perspective.Perspective;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;

public class FirstPersonPerspective extends Perspective {
   private long renderEndNanoTime = System.nanoTime();

   public FirstPersonPerspective() {
      this.width = Minecraft.getMinecraft().displayWidth;
      this.height = Minecraft.getMinecraft().displayHeight;
   }

   public void update(CompatibleRenderTickEvent event) {
      this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.RENDER_PERSPECTIVE);
      long p_78471_2_ = this.renderEndNanoTime + 16666666L;
      int origDisplayWidth = Minecraft.getMinecraft().displayWidth;
      int origDisplayHeight = Minecraft.getMinecraft().displayHeight;
      EntityRenderer origEntityRenderer = Minecraft.getMinecraft().entityRenderer;
      this.framebuffer.bindFramebuffer(true);
      Minecraft.getMinecraft().displayWidth = this.width;
      Minecraft.getMinecraft().displayHeight = this.height;
      Minecraft.getMinecraft().entityRenderer = this.entityRenderer;
      this.entityRenderer.setPrepareTerrain(false);
      this.entityRenderer.updateRenderer();
      this.entityRenderer.renderWorld(event.getRenderTickTime(), p_78471_2_);
      Minecraft.getMinecraft().entityRenderer = origEntityRenderer;
      Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
      Minecraft.getMinecraft().displayWidth = origDisplayWidth;
      Minecraft.getMinecraft().displayHeight = origDisplayHeight;
      this.renderEndNanoTime = System.nanoTime();
      this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.NORMAL);
   }
}
