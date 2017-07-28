package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Laser2 extends ModelBase {
   ModelRenderer laser1;
   ModelRenderer laser2;
   ModelRenderer laser3;
   ModelRenderer laser5;
   ModelRenderer laser6;
   ModelRenderer laser7;
   ModelRenderer laser8;
   ModelRenderer laser9;
   ModelRenderer laser10;
   ModelRenderer laser11;
   ModelRenderer laser12;
   ModelRenderer laser13;

   public Laser2() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.laser1 = new ModelRenderer(this, 0, 0);
      this.laser1.addBox(0.0F, 0.0F, 0.0F, 3, 5, 10);
      this.laser1.setRotationPoint(2.0F, -1.0F, 0.0F);
      this.laser1.setTextureSize(64, 32);
      this.laser1.mirror = true;
      this.setRotation(this.laser1, 0.0F, 0.0F, 0.0F);
      this.laser2 = new ModelRenderer(this, 0, 0);
      this.laser2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4);
      this.laser2.setRotationPoint(0.0F, -0.5F, 4.0F);
      this.laser2.setTextureSize(64, 32);
      this.laser2.mirror = true;
      this.setRotation(this.laser2, 0.0F, 0.0F, 0.0F);
      this.laser3 = new ModelRenderer(this, 0, 0);
      this.laser3.addBox(0.0F, 0.0F, 0.0F, 2, 6, 10);
      this.laser3.setRotationPoint(2.5F, -1.5F, 0.0F);
      this.laser3.setTextureSize(64, 32);
      this.laser3.mirror = true;
      this.setRotation(this.laser3, 0.0F, 0.0F, 0.0F);
      this.laser5 = new ModelRenderer(this, 0, 0);
      this.laser5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.laser5.setRotationPoint(4.0F, -1.5F, 3.0F);
      this.laser5.setTextureSize(64, 32);
      this.laser5.mirror = true;
      this.setRotation(this.laser5, 0.0F, 0.0F, 0.0F);
      this.laser6 = new ModelRenderer(this, 0, 0);
      this.laser6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.laser6.setRotationPoint(4.3F, 1.0F, 7.0F);
      this.laser6.setTextureSize(64, 32);
      this.laser6.mirror = true;
      this.setRotation(this.laser6, 0.0F, 0.0F, 0.0F);
      this.laser7 = new ModelRenderer(this, 0, 0);
      this.laser7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.laser7.setRotationPoint(4.3F, -0.5F, 4.0F);
      this.laser7.setTextureSize(64, 32);
      this.laser7.mirror = true;
      this.setRotation(this.laser7, 0.0F, 0.0F, 0.0F);
      this.laser8 = new ModelRenderer(this, 0, 0);
      this.laser8.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12);
      this.laser8.setRotationPoint(2.5F, -1.0F, -1.5F);
      this.laser8.setTextureSize(64, 32);
      this.laser8.mirror = true;
      this.setRotation(this.laser8, 0.0F, 0.0F, 0.0F);
      this.laser9 = new ModelRenderer(this, 0, 0);
      this.laser9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.laser9.setRotationPoint(3.0F, 0.3F, -1.5F);
      this.laser9.setTextureSize(64, 32);
      this.laser9.mirror = true;
      this.setRotation(this.laser9, 0.0F, 0.0F, 0.0F);
      this.laser10 = new ModelRenderer(this, 0, 0);
      this.laser10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.laser10.setRotationPoint(3.0F, -1.3F, -1.5F);
      this.laser10.setTextureSize(64, 32);
      this.laser10.mirror = true;
      this.setRotation(this.laser10, 0.0F, 0.0F, 0.0F);
      this.laser11 = new ModelRenderer(this, 0, 0);
      this.laser11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.laser11.setRotationPoint(2.2F, -0.5F, -1.5F);
      this.laser11.setTextureSize(64, 32);
      this.laser11.mirror = true;
      this.setRotation(this.laser11, 0.0F, 0.0F, 0.0F);
      this.laser12 = new ModelRenderer(this, 0, 0);
      this.laser12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.laser12.setRotationPoint(3.8F, -0.5F, -1.5F);
      this.laser12.setTextureSize(64, 32);
      this.laser12.mirror = true;
      this.setRotation(this.laser12, 0.0F, 0.0F, 0.0F);
      this.laser13 = new ModelRenderer(this, 0, 0);
      this.laser13.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.laser13.setRotationPoint(4.5F, 2.5F, -1.0F);
      this.laser13.setTextureSize(64, 32);
      this.laser13.mirror = true;
      this.setRotation(this.laser13, 0.0F, 0.0F, 2.528146F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.laser1.render(f5);
      this.laser2.render(f5);
      this.laser3.render(f5);
      this.laser5.render(f5);
      this.laser6.render(f5);
      this.laser7.render(f5);
      this.laser8.render(f5);
      this.laser9.render(f5);
      this.laser10.render(f5);
      this.laser11.render(f5);
      this.laser12.render(f5);
      this.laser13.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
