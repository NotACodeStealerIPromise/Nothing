package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.ViewfinderModel;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import com.vicmatskiv.weaponlib.perspective.Perspective;
import java.util.function.BiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class PerspectiveRenderer implements CustomRenderer {
   private static Perspective STATIC_TEXTURE_PERSPECTIVE = new PerspectiveRenderer.StaticTexturePerspective();
   private ViewfinderModel model = new ViewfinderModel();
   private BiConsumer positioning;

   public PerspectiveRenderer(BiConsumer positioning) {
      this.positioning = positioning;
   }

   public void render(RenderContext renderContext) {
      if(renderContext.getCompatibleTransformType() == CompatibleTransformType.FIRST_PERSON_RIGHT_HAND || renderContext.getCompatibleTransformType() == CompatibleTransformType.FIRST_PERSON_LEFT_HAND) {
         if(renderContext.getModContext() != null) {
            ClientModContext clientModContext = (ClientModContext)renderContext.getModContext();
            Perspective perspective = clientModContext.getViewManager().getPerspective(renderContext.getPlayerItemInstance(), false);
            if(perspective == null) {
               perspective = STATIC_TEXTURE_PERSPECTIVE;
            }

            float brightness = perspective.getBrightness(renderContext);
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
            GL11.glBindTexture(3553, perspective.getTexture(renderContext));
            CompatibilityProvider.compatibility.disableLightMap();
            GL11.glEnable(2929);
            GL11.glDisable(2896);
            GL11.glDisable(3008);
            GL11.glDisable(3042);
            GL11.glColor4f(brightness, brightness, brightness, 1.0F);
            this.model.render(renderContext.getPlayer(), renderContext.getLimbSwing(), renderContext.getFlimbSwingAmount(), renderContext.getAgeInTicks(), renderContext.getNetHeadYaw(), renderContext.getHeadPitch(), renderContext.getScale());
            CompatibilityProvider.compatibility.enableLightMap();
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }
      }
   }

   private static class StaticTexturePerspective extends Perspective {
      private Integer textureId;

      private StaticTexturePerspective() {
      }

      public void update(CompatibleRenderTickEvent event) {
      }

      public int getTexture(RenderContext context) {
         if(this.textureId == null) {
            ResourceLocation textureResource = new ResourceLocation("weaponlib:/com/vicmatskiv/weaponlib/resources/dark-screen.png");
            Minecraft.getMinecraft().getTextureManager().bindTexture(textureResource);
            ITextureObject textureObject = Minecraft.getMinecraft().getTextureManager().getTexture(textureResource);
            if(textureObject != null) {
               this.textureId = Integer.valueOf(textureObject.getGlTextureId());
            }
         }

         return this.textureId.intValue();
      }

      public float getBrightness(RenderContext context) {
         return 0.0F;
      }

      // $FF: synthetic method
      StaticTexturePerspective(Object x0) {
         this();
      }
   }
}
