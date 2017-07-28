package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Reflex2 extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;

   public Reflex2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.gun1 = new ModelRenderer(this, 0, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 0);
      this.gun1.setRotationPoint(0.0F, -10.0F, 0.0F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun2 = new ModelRenderer(this, 0, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0);
      this.gun2.setRotationPoint(-1.0F, -8.0F, 0.0F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 0, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 5, 1, 0);
      this.gun3.setRotationPoint(-2.0F, -6.0F, 0.0F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
