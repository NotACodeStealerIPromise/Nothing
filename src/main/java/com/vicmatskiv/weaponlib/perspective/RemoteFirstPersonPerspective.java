package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.RenderingPhase;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticleManager;
import com.vicmatskiv.weaponlib.compatibility.CompatiblePlayerCreatureWrapper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.perspective.Perspective;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class RemoteFirstPersonPerspective extends Perspective {
   private static final Logger logger = LogManager.getLogger(RemoteFirstPersonPerspective.class);
   private long renderEndNanoTime = System.nanoTime();
   protected CompatiblePlayerCreatureWrapper watchablePlayer;

   public RemoteFirstPersonPerspective() {
      this.width = 427;
      this.height = 240;
      WorldClient world = (WorldClient)CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer());
      this.watchablePlayer = new CompatiblePlayerCreatureWrapper(Minecraft.getMinecraft(), world);
   }

   public void update(CompatibleRenderTickEvent event) {
      EntityPlayer origPlayer = CompatibilityProvider.compatibility.clientPlayer();
      if(origPlayer != null) {
         this.updateWatchablePlayer();
         RenderGlobal origRenderGlobal = Minecraft.getMinecraft().renderGlobal;
         CompatibleParticleManager origEffectRenderer = CompatibilityProvider.compatibility.getCompatibleParticleManager();
         Entity origRenderViewEntity = CompatibilityProvider.compatibility.getRenderViewEntity();
         EntityRenderer origEntityRenderer = Minecraft.getMinecraft().entityRenderer;
         int origDisplayWidth = Minecraft.getMinecraft().displayWidth;
         int origDisplayHeight = Minecraft.getMinecraft().displayHeight;
         Minecraft.getMinecraft().displayWidth = this.width;
         Minecraft.getMinecraft().displayHeight = this.height;
         this.framebuffer.bindFramebuffer(true);
         Minecraft.getMinecraft().renderGlobal = this.renderGlobal;
         Minecraft.getMinecraft().effectRenderer = this.effectRenderer.getParticleManager();
         Minecraft.getMinecraft().entityRenderer = this.entityRenderer;
         if(this.watchablePlayer.getEntityLiving() != null && !this.watchablePlayer.getEntityLiving().isDead) {
            CompatibilityProvider.compatibility.setRenderViewEntity(this.watchablePlayer.getEntityLiving());
            CompatibilityProvider.compatibility.setClientPlayer(this.watchablePlayer);
            this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.RENDER_PERSPECTIVE);
            this.entityRenderer.setPrepareTerrain(true);
            this.entityRenderer.updateRenderer();
            long p_78471_2_ = this.renderEndNanoTime + 16666666L;
            this.entityRenderer.renderWorld(event.getRenderTickTime(), p_78471_2_);
            this.modContext.getSafeGlobals().renderingPhase.set(RenderingPhase.NORMAL);
            CompatibilityProvider.compatibility.setRenderViewEntity(origRenderViewEntity);
            CompatibilityProvider.compatibility.setClientPlayer(origPlayer);
         }

         this.renderOverlay();
         Minecraft.getMinecraft().getFramebuffer().bindFramebuffer(true);
         Minecraft.getMinecraft().renderGlobal = origRenderGlobal;
         Minecraft.getMinecraft().effectRenderer = origEffectRenderer.getParticleManager();
         Minecraft.getMinecraft().displayWidth = origDisplayWidth;
         Minecraft.getMinecraft().displayHeight = origDisplayHeight;
         Minecraft.getMinecraft().entityRenderer = origEntityRenderer;
         this.renderEndNanoTime = System.nanoTime();
      }
   }

   protected abstract void updateWatchablePlayer();

   protected void renderOverlay() {
      this.entityRenderer.setupOverlayRendering();
   }
}
