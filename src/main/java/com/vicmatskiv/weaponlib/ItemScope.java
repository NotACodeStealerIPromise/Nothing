package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.perspective.PerspectiveRenderer;
import java.util.function.BiConsumer;
import org.lwjgl.opengl.GL11;

public class ItemScope extends ItemAttachment {
   private final int DEFAULT_MAX_STACK_SIZE;
   private ModContext modContext;
   private ItemScope.Builder builder;

   private ItemScope(ItemScope.Builder builder) {
      super(builder.getModId(), AttachmentCategory.SCOPE, builder.getModel(), builder.getTextureName(), (String)null, (ItemAttachment.ApplyHandler)null, (ItemAttachment.ApplyHandler)null);
      this.DEFAULT_MAX_STACK_SIZE = 1;
      this.builder = builder;
      this.setMaxStackSize(1);
   }

   public float getMinZoom() {
      return this.builder.minZoom;
   }

   public float getMaxZoom() {
      return this.builder.maxZoom;
   }

   public boolean isOptical() {
      return this.builder.isOpticalZoom;
   }

   // $FF: synthetic method
   ItemScope(ItemScope.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder extends AttachmentBuilder {
      private float minZoom;
      private float maxZoom;
      private boolean isOpticalZoom;
      private BiConsumer viewfinderPositioning;

      public ItemScope.Builder withZoomRange(float minZoom, float maxZoom) {
         this.minZoom = minZoom;
         this.maxZoom = maxZoom;
         return this;
      }

      public ItemScope.Builder withOpticalZoom() {
         this.isOpticalZoom = true;
         return this;
      }

      public ItemScope.Builder withViewfinderPositioning(BiConsumer viewfinderPositioning) {
         this.viewfinderPositioning = viewfinderPositioning;
         return this;
      }

      protected ItemAttachment createAttachment(ModContext modContext) {
         if(this.isOpticalZoom) {
            if(this.viewfinderPositioning == null) {
               this.viewfinderPositioning = (p, s) -> {
                  GL11.glScalef(1.1F, 1.1F, 1.1F);
                  GL11.glTranslatef(0.1F, 0.4F, 0.6F);
               };
            }

            this.withPostRender(new PerspectiveRenderer(this.viewfinderPositioning));
         }

         ItemScope itemScope = new ItemScope(this);
         itemScope.modContext = modContext;
         return itemScope;
      }

      public ItemAttachment build(ModContext modContext) {
         this.apply2 = (a, instance) -> {
            float zoom = this.minZoom + (this.maxZoom - this.minZoom) / 2.0F;
            instance.setZoom(zoom);
         };
         this.remove2 = (a, instance) -> {
            instance.setZoom(1.0F);
         };
         return super.build(modContext);
      }
   }
}
