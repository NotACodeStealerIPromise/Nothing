package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticleManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWorldRenderer;
import com.vicmatskiv.weaponlib.perspective.Perspective;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PerspectiveManager {
   private static final Logger logger = LogManager.getLogger(PerspectiveManager.class);
   private Perspective currentPerspective;
   private ClientModContext clientModContext;
   private CompatibleWorldRenderer entityRenderer;
   private RenderGlobal renderGlobal;
   private CompatibleParticleManager effectRenderer;

   public PerspectiveManager(ClientModContext clientModContext) {
      this.clientModContext = clientModContext;
   }

   public Perspective getPerspective(PlayerItemInstance currentInstance, boolean init) {
      if(currentInstance != null && (this.currentPerspective != null || init)) {
         Class perspectiveClass = currentInstance.getRequiredPerspectiveType();
         if(perspectiveClass != null) {
            if(this.currentPerspective == null) {
               this.currentPerspective = this.createActivePerspective(perspectiveClass);
            } else if(!perspectiveClass.isInstance(this.currentPerspective)) {
               this.currentPerspective.deactivate(this.clientModContext);
               this.currentPerspective = this.createActivePerspective(perspectiveClass);
            }
         } else if(this.currentPerspective != null) {
            if(init) {
               this.currentPerspective.deactivate(this.clientModContext);
            }

            this.currentPerspective = null;
         }

         return this.currentPerspective;
      } else {
         return null;
      }
   }

   private Perspective createActivePerspective(Class perspectiveClass) {
      Perspective result = null;

      try {
         result = (Perspective)perspectiveClass.newInstance();
         result.activate(this.clientModContext, this);
      } catch (IllegalAccessException | InstantiationException var4) {
         logger.error("Failed to create view of {} - {}", new Object[]{perspectiveClass, var4, var4});
      }

      return result;
   }

   CompatibleWorldRenderer getEntityRenderer() {
      if(this.entityRenderer == null) {
         this.entityRenderer = new CompatibleWorldRenderer(Minecraft.getMinecraft(), Minecraft.getMinecraft().getResourceManager());
      }

      return this.entityRenderer;
   }

   RenderGlobal getRenderGlobal() {
      if(this.renderGlobal == null) {
         this.renderGlobal = CompatibilityProvider.compatibility.createCompatibleRenderGlobal();
         WorldClient world = (WorldClient)CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer());
         this.renderGlobal.setWorldAndLoadRenderers(world);
      }

      return this.renderGlobal;
   }

   CompatibleParticleManager getEffectRenderer() {
      if(this.effectRenderer == null) {
         WorldClient world = (WorldClient)CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer());
         this.effectRenderer = CompatibilityProvider.compatibility.createCompatibleParticleManager(world);
      }

      return this.effectRenderer;
   }
}
