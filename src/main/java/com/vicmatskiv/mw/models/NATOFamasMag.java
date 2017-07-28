package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NATOFamasMag extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun3;
   ModelRenderer gun4;
   ModelRenderer gun8;
   ModelRenderer gun9;
   ModelRenderer gun11;
   ModelRenderer gun13;
   ModelRenderer gun14;

   public NATOFamasMag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun1 = new ModelRenderer(this, 200, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 3, 14, 6);
      this.gun1.setRotationPoint(1.0F, -12.0F, -4.5F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 200, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun3.setRotationPoint(0.8F, -12.0F, -0.5F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 200, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun4.setRotationPoint(3.2F, -12.0F, -0.5F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun8 = new ModelRenderer(this, 200, 0);
      this.gun8.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun8.setRotationPoint(3.2F, -12.0F, -3.0F);
      this.gun8.setTextureSize(64, 32);
      this.gun8.mirror = true;
      this.setRotation(this.gun8, 0.0F, 0.0F, 0.0F);
      this.gun9 = new ModelRenderer(this, 200, 0);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1);
      this.gun9.setRotationPoint(3.2F, -12.0F, -4.5F);
      this.gun9.setTextureSize(64, 32);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 0.0F);
      this.gun11 = new ModelRenderer(this, 200, 0);
      this.gun11.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1);
      this.gun11.setRotationPoint(0.8F, -12.0F, -4.5F);
      this.gun11.setTextureSize(64, 32);
      this.gun11.mirror = true;
      this.setRotation(this.gun11, 0.0F, 0.0F, 0.0F);
      this.gun13 = new ModelRenderer(this, 200, 0);
      this.gun13.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun13.setRotationPoint(0.8F, -12.0F, -3.0F);
      this.gun13.setTextureSize(64, 32);
      this.gun13.mirror = true;
      this.setRotation(this.gun13, 0.0F, 0.0F, 0.0F);
      this.gun14 = new ModelRenderer(this, 200, 0);
      this.gun14.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.gun14.setRotationPoint(1.0F, 2.0F, -4.5F);
      this.gun14.setTextureSize(64, 32);
      this.gun14.mirror = true;
      this.setRotation(this.gun14, 1.412787F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun3.render(f5);
      this.gun4.render(f5);
      this.gun8.render(f5);
      this.gun9.render(f5);
      this.gun11.render(f5);
      this.gun13.render(f5);
      this.gun14.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
