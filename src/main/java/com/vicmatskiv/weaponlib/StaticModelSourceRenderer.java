package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleStaticModelSourceRenderer;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;

public class StaticModelSourceRenderer extends CompatibleStaticModelSourceRenderer {
   private StaticModelSourceRenderer(StaticModelSourceRenderer.Builder builder) {
      super(builder);
   }

   protected ModContext getModContext() {
      return this.builder.modContext;
   }

   // $FF: synthetic method
   StaticModelSourceRenderer(StaticModelSourceRenderer.Builder x0, Object x1) {
      this(x0);
   }

   public static class Builder {
      private Consumer entityPositioning;
      private Consumer inventoryPositioning;
      private BiConsumer thirdPersonPositioning;
      private BiConsumer firstPersonPositioning;
      private BiConsumer firstPersonModelPositioning;
      private BiConsumer thirdPersonModelPositioning;
      private BiConsumer inventoryModelPositioning;
      private BiConsumer entityModelPositioning;
      private Consumer firstPersonLeftHandPositioning;
      private Consumer firstPersonRightHandPositioning;
      private String modId;
      private ModContext modContext;

      public StaticModelSourceRenderer.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public StaticModelSourceRenderer.Builder withModContext(ModContext modContext) {
         this.modContext = modContext;
         return this;
      }

      public StaticModelSourceRenderer.Builder withFirstPersonPositioning(BiConsumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public StaticModelSourceRenderer.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withThirdPersonPositioning(BiConsumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withFirstPersonModelPositioning(BiConsumer firstPersonModelPositioning) {
         this.firstPersonModelPositioning = firstPersonModelPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withEntityModelPositioning(BiConsumer entityModelPositioning) {
         this.entityModelPositioning = entityModelPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withInventoryModelPositioning(BiConsumer inventoryModelPositioning) {
         this.inventoryModelPositioning = inventoryModelPositioning;
         return this;
      }

      public StaticModelSourceRenderer.Builder withThirdPersonModelPositioning(BiConsumer thirdPersonModelPositioning) {
         this.thirdPersonModelPositioning = thirdPersonModelPositioning;
         return this;
      }

      public StaticModelSourceRenderer build() {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            if(this.inventoryPositioning == null) {
               this.inventoryPositioning = (itemStack) -> {
                  GL11.glTranslatef(0.0F, 0.12F, 0.0F);
               };
            }

            if(this.entityPositioning == null) {
               this.entityPositioning = (itemStack) -> {
               };
            }

            if(this.firstPersonPositioning == null) {
               this.firstPersonPositioning = (player, itemStack) -> {
               };
            }

            if(this.thirdPersonPositioning == null) {
               this.thirdPersonPositioning = (player, itemStack) -> {
               };
            }

            if(this.inventoryModelPositioning == null) {
               this.inventoryModelPositioning = (m, i) -> {
               };
            }

            if(this.entityModelPositioning == null) {
               this.entityModelPositioning = (m, i) -> {
               };
            }

            if(this.firstPersonModelPositioning == null) {
               this.firstPersonModelPositioning = (m, i) -> {
               };
            }

            if(this.thirdPersonModelPositioning == null) {
               this.thirdPersonModelPositioning = (m, i) -> {
               };
            }

            if(this.firstPersonLeftHandPositioning == null) {
               this.firstPersonLeftHandPositioning = (c) -> {
                  GL11.glScalef(0.0F, 0.0F, 0.0F);
               };
            }

            if(this.firstPersonRightHandPositioning == null) {
               this.firstPersonRightHandPositioning = (c) -> {
                  GL11.glScalef(0.0F, 0.0F, 0.0F);
               };
            }

            return new StaticModelSourceRenderer(this);
         }
      }

      public Consumer getEntityPositioning() {
         return this.entityPositioning;
      }

      public Consumer getInventoryPositioning() {
         return this.inventoryPositioning;
      }

      public BiConsumer getThirdPersonPositioning() {
         return this.thirdPersonPositioning;
      }

      public BiConsumer getFirstPersonPositioning() {
         return this.firstPersonPositioning;
      }

      public BiConsumer getFirstPersonModelPositioning() {
         return this.firstPersonModelPositioning;
      }

      public BiConsumer getThirdPersonModelPositioning() {
         return this.thirdPersonModelPositioning;
      }

      public BiConsumer getInventoryModelPositioning() {
         return this.inventoryModelPositioning;
      }

      public BiConsumer getEntityModelPositioning() {
         return this.entityModelPositioning;
      }

      public Consumer getFirstPersonLeftHandPositioning() {
         return this.firstPersonLeftHandPositioning;
      }

      public Consumer getFirstPersonRightHandPositioning() {
         return this.firstPersonRightHandPositioning;
      }

      public String getModId() {
         return this.modId;
      }
   }
}
