package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M14Mag extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun11;
   ModelRenderer gun13;

   public M14Mag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun1 = new ModelRenderer(this, 200, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 2, 11, 2);
      this.gun1.setRotationPoint(1.5F, -12.9F, -6.0F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, -0.0743572F, 0.0F, 0.0F);
      this.gun11 = new ModelRenderer(this, 200, 0);
      this.gun11.addBox(0.0F, 0.0F, 0.0F, 3, 11, 2);
      this.gun11.setRotationPoint(1.0F, -13.0F, -7.0F);
      this.gun11.setTextureSize(64, 32);
      this.gun11.mirror = true;
      this.setRotation(this.gun11, -0.0743572F, 0.0F, 0.0F);
      this.gun13 = new ModelRenderer(this, 200, 0);
      this.gun13.addBox(0.0F, 0.0F, 0.0F, 3, 11, 4);
      this.gun13.setRotationPoint(1.0F, -12.8F, -4.5F);
      this.gun13.setTextureSize(64, 32);
      this.gun13.mirror = true;
      this.setRotation(this.gun13, -0.0743572F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun11.render(f5);
      this.gun13.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
