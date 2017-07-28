package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Bullet44 extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape3;
   ModelRenderer Shape4;
   ModelRenderer Shape5;
   ModelRenderer Shape6;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape13;
   ModelRenderer Shape14;
   ModelRenderer Shape15;
   ModelRenderer Shape16;
   ModelRenderer Shape17;
   ModelRenderer Shape18;
   ModelRenderer Shape19;

   public Bullet44() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 3, 9, 3);
      this.Shape1.setRotationPoint(0.0F, -5.0F, 0.0F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape3.setRotationPoint(0.5F, -5.0F, -0.2F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 2, 9, 1);
      this.Shape4.setRotationPoint(0.5F, -5.0F, 2.2F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 0.0F);
      this.Shape5 = new ModelRenderer(this, 0, 0);
      this.Shape5.addBox(0.0F, 0.0F, 0.0F, 1, 9, 2);
      this.Shape5.setRotationPoint(-0.2F, -5.0F, 0.5F);
      this.Shape5.setTextureSize(64, 32);
      this.Shape5.mirror = true;
      this.setRotation(this.Shape5, 0.0F, 0.0F, 0.0F);
      this.Shape6 = new ModelRenderer(this, 0, 0);
      this.Shape6.addBox(0.0F, 0.0F, 0.0F, 1, 9, 2);
      this.Shape6.setRotationPoint(2.2F, -5.0F, 0.5F);
      this.Shape6.setTextureSize(64, 32);
      this.Shape6.mirror = true;
      this.setRotation(this.Shape6, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.Shape11.setRotationPoint(0.0F, 3.5F, -0.4F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Shape12.setRotationPoint(0.0F, 3.5F, 2.4F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape13 = new ModelRenderer(this, 0, 0);
      this.Shape13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape13.setRotationPoint(2.4F, 3.5F, 0.0F);
      this.Shape13.setTextureSize(64, 32);
      this.Shape13.mirror = true;
      this.setRotation(this.Shape13, 0.0F, 0.0F, 0.0F);
      this.Shape14 = new ModelRenderer(this, 0, 0);
      this.Shape14.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Shape14.setRotationPoint(-0.4F, 3.5F, 0.0F);
      this.Shape14.setTextureSize(64, 32);
      this.Shape14.mirror = true;
      this.setRotation(this.Shape14, 0.0F, 0.0F, 0.0F);
      this.Shape15 = new ModelRenderer(this, 20, 0);
      this.Shape15.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.Shape15.setRotationPoint(0.5F, -7.0F, 0.5F);
      this.Shape15.setTextureSize(64, 32);
      this.Shape15.mirror = true;
      this.setRotation(this.Shape15, 0.0F, 0.0F, 0.0F);
      this.Shape16 = new ModelRenderer(this, 20, 0);
      this.Shape16.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Shape16.setRotationPoint(2.5F, -7.0F, 0.5F);
      this.Shape16.setTextureSize(64, 32);
      this.Shape16.mirror = true;
      this.setRotation(this.Shape16, 0.0F, 0.0F, 1.301251F);
      this.Shape17 = new ModelRenderer(this, 20, 0);
      this.Shape17.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Shape17.setRotationPoint(0.5F, -7.0F, 0.5F);
      this.Shape17.setTextureSize(64, 32);
      this.Shape17.mirror = true;
      this.setRotation(this.Shape17, -0.2602503F, 0.0F, 0.0F);
      this.Shape18 = new ModelRenderer(this, 20, 0);
      this.Shape18.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.Shape18.setRotationPoint(0.5F, -7.0F, 0.5F);
      this.Shape18.setTextureSize(64, 32);
      this.Shape18.mirror = true;
      this.setRotation(this.Shape18, 0.0F, 0.0F, 0.2602503F);
      this.Shape19 = new ModelRenderer(this, 20, 0);
      this.Shape19.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Shape19.setRotationPoint(0.5F, -7.0F, 2.5F);
      this.Shape19.setTextureSize(64, 32);
      this.Shape19.mirror = true;
      this.setRotation(this.Shape19, -1.33843F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
      this.Shape5.render(f5);
      this.Shape6.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape13.render(f5);
      this.Shape14.render(f5);
      this.Shape15.render(f5);
      this.Shape16.render(f5);
      this.Shape17.render(f5);
      this.Shape18.render(f5);
      this.Shape19.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
