package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VGrip extends ModelBase {
   ModelRenderer gun2;
   ModelRenderer gun4;
   ModelRenderer gun6;
   ModelRenderer gun11;
   ModelRenderer gun12;
   ModelRenderer gun13;
   ModelRenderer gun14;
   ModelRenderer gun15;
   ModelRenderer gun16;
   ModelRenderer gun17;

   public VGrip() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun2 = new ModelRenderer(this, 0, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
      this.gun2.setRotationPoint(-0.5F, -6.5F, 2.0F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 0, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
      this.gun4.setRotationPoint(-0.5F, -6.5F, -4.0F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun6 = new ModelRenderer(this, 0, 0);
      this.gun6.addBox(0.0F, 0.0F, 0.0F, 2, 9, 3);
      this.gun6.setRotationPoint(0.0F, -3.5F, -2.5F);
      this.gun6.setTextureSize(64, 32);
      this.gun6.mirror = true;
      this.setRotation(this.gun6, 0.0F, 0.0F, 0.0F);
      this.gun11 = new ModelRenderer(this, 0, 0);
      this.gun11.addBox(0.0F, 0.0F, 0.0F, 3, 11, 2);
      this.gun11.setRotationPoint(-0.5F, -4.0F, -2.0F);
      this.gun11.setTextureSize(64, 32);
      this.gun11.mirror = true;
      this.setRotation(this.gun11, 0.0F, 0.0F, 0.0F);
      this.gun12 = new ModelRenderer(this, 0, 0);
      this.gun12.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
      this.gun12.setRotationPoint(0.0F, 3.5F, -2.5F);
      this.gun12.setTextureSize(64, 32);
      this.gun12.mirror = true;
      this.setRotation(this.gun12, 0.0F, 0.0F, 0.0F);
      this.gun13 = new ModelRenderer(this, 0, 0);
      this.gun13.addBox(0.0F, 0.0F, 0.0F, 2, 6, 1);
      this.gun13.setRotationPoint(0.0F, -2.5F, -3.0F);
      this.gun13.setTextureSize(64, 32);
      this.gun13.mirror = true;
      this.setRotation(this.gun13, 0.0F, 0.0F, 0.0F);
      this.gun14 = new ModelRenderer(this, 0, 0);
      this.gun14.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.gun14.setRotationPoint(0.0F, -4.5F, -3.0F);
      this.gun14.setTextureSize(64, 32);
      this.gun14.mirror = true;
      this.setRotation(this.gun14, 0.0F, 0.0F, 0.0F);
      this.gun15 = new ModelRenderer(this, 0, 0);
      this.gun15.addBox(0.0F, 0.0F, 0.0F, 3, 1, 7);
      this.gun15.setRotationPoint(-0.5F, -5.5F, -3.5F);
      this.gun15.setTextureSize(64, 32);
      this.gun15.mirror = true;
      this.setRotation(this.gun15, 0.0F, 0.0F, 0.0F);
      this.gun16 = new ModelRenderer(this, 0, 0);
      this.gun16.addBox(0.0F, 0.0F, 0.0F, 2, 1, 6);
      this.gun16.setRotationPoint(0.0F, -6.5F, -2.0F);
      this.gun16.setTextureSize(64, 32);
      this.gun16.mirror = true;
      this.setRotation(this.gun16, 0.0F, 0.0F, 0.0F);
      this.gun17 = new ModelRenderer(this, 0, 0);
      this.gun17.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.gun17.setRotationPoint(-1.0F, -3.5F, -1.5F);
      this.gun17.setTextureSize(64, 32);
      this.gun17.mirror = true;
      this.setRotation(this.gun17, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun2.render(f5);
      this.gun4.render(f5);
      this.gun6.render(f5);
      this.gun11.render(f5);
      this.gun12.render(f5);
      this.gun13.render(f5);
      this.gun14.render(f5);
      this.gun15.render(f5);
      this.gun16.render(f5);
      this.gun17.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
