package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Laser extends ModelBase {
   ModelRenderer laser1;
   ModelRenderer laser2;
   ModelRenderer laser3;
   ModelRenderer laser4;
   ModelRenderer laser5;
   ModelRenderer laser6;
   ModelRenderer laser7;
   ModelRenderer laser8;
   ModelRenderer laser9;
   ModelRenderer laser11;

   public Laser() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.laser1 = new ModelRenderer(this, 0, 0);
      this.laser1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 10);
      this.laser1.setRotationPoint(2.0F, 0.0F, 0.0F);
      this.laser1.setTextureSize(64, 32);
      this.laser1.mirror = true;
      this.setRotation(this.laser1, 0.0F, 0.0F, 0.0F);
      this.laser2 = new ModelRenderer(this, 0, 0);
      this.laser2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 10);
      this.laser2.setRotationPoint(2.5F, -0.3F, 0.0F);
      this.laser2.setTextureSize(64, 32);
      this.laser2.mirror = true;
      this.setRotation(this.laser2, 0.0F, 0.0F, 0.0F);
      this.laser3 = new ModelRenderer(this, 0, 0);
      this.laser3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 10);
      this.laser3.setRotationPoint(4.3F, 0.5F, 0.0F);
      this.laser3.setTextureSize(64, 32);
      this.laser3.mirror = true;
      this.setRotation(this.laser3, 0.0F, 0.0F, 0.0F);
      this.laser4 = new ModelRenderer(this, 0, 0);
      this.laser4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 10);
      this.laser4.setRotationPoint(2.5F, 2.3F, 0.0F);
      this.laser4.setTextureSize(64, 32);
      this.laser4.mirror = true;
      this.setRotation(this.laser4, 0.0F, 0.0F, 0.0F);
      this.laser5 = new ModelRenderer(this, 0, 0);
      this.laser5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.laser5.setRotationPoint(5.3F, 0.5F, 4.0F);
      this.laser5.setTextureSize(64, 32);
      this.laser5.mirror = true;
      this.setRotation(this.laser5, 0.0F, 0.0F, 0.0F);
      this.laser6 = new ModelRenderer(this, 0, 0);
      this.laser6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.laser6.setRotationPoint(2.5F, -1.3F, 4.0F);
      this.laser6.setTextureSize(64, 32);
      this.laser6.mirror = true;
      this.setRotation(this.laser6, 0.0F, 0.0F, 0.0F);
      this.laser7 = new ModelRenderer(this, 0, 0);
      this.laser7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.laser7.setRotationPoint(2.5F, 3.3F, 4.0F);
      this.laser7.setTextureSize(64, 32);
      this.laser7.mirror = true;
      this.setRotation(this.laser7, 0.0F, 0.0F, 0.0F);
      this.laser8 = new ModelRenderer(this, 0, 0);
      this.laser8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.laser8.setRotationPoint(5.8F, 0.5F, 4.0F);
      this.laser8.setTextureSize(64, 32);
      this.laser8.mirror = true;
      this.setRotation(this.laser8, 0.0F, 0.0F, 2.379431F);
      this.laser9 = new ModelRenderer(this, 0, 0);
      this.laser9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.laser9.setRotationPoint(5.8F, 2.5F, 4.0F);
      this.laser9.setTextureSize(64, 32);
      this.laser9.mirror = true;
      this.setRotation(this.laser9, 0.0F, 0.0F, 2.379431F);
      this.laser11 = new ModelRenderer(this, 0, 0);
      this.laser11.addBox(0.0F, 0.0F, 0.0F, 2, 4, 4);
      this.laser11.setRotationPoint(0.0F, -0.5F, 4.0F);
      this.laser11.setTextureSize(64, 32);
      this.laser11.mirror = true;
      this.setRotation(this.laser11, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.laser1.render(f5);
      this.laser2.render(f5);
      this.laser3.render(f5);
      this.laser4.render(f5);
      this.laser5.render(f5);
      this.laser6.render(f5);
      this.laser7.render(f5);
      this.laser8.render(f5);
      this.laser9.render(f5);
      this.laser11.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
