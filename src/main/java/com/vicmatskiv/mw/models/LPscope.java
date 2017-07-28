package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class LPscope extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun4;
   ModelRenderer gun7;
   ModelRenderer gun9;
   ModelRenderer gun10;
   ModelRenderer gun11;
   ModelRenderer gun12;

   public LPscope() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.gun1 = new ModelRenderer(this, 0, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 12, 1, 0);
      this.gun1.setRotationPoint(-5.5F, -10.0F, 0.0F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 0, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 1, 12, 0);
      this.gun4.setRotationPoint(0.0F, -15.5F, 0.0F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun7 = new ModelRenderer(this, 0, 20);
      this.gun7.addBox(0.0F, 0.0F, 0.0F, 1, 5, 0);
      this.gun7.setRotationPoint(0.0F, -3.5F, 0.0F);
      this.gun7.setTextureSize(64, 32);
      this.gun7.mirror = true;
      this.setRotation(this.gun7, 0.0F, 0.0F, 0.0F);
      this.gun9 = new ModelRenderer(this, 0, 20);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 20, 0);
      this.gun9.setRotationPoint(0.0F, -35.5F, 0.0F);
      this.gun9.setTextureSize(64, 32);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 0.0F);
      this.gun10 = new ModelRenderer(this, 0, 20);
      this.gun10.addBox(0.0F, 0.0F, 0.0F, 22, 1, 0);
      this.gun10.setRotationPoint(6.5F, -10.0F, 0.0F);
      this.gun10.setTextureSize(64, 32);
      this.gun10.mirror = true;
      this.setRotation(this.gun10, 0.0F, 0.0F, 0.0F);
      this.gun11 = new ModelRenderer(this, 0, 20);
      this.gun11.addBox(0.0F, 0.0F, 0.0F, 22, 1, 0);
      this.gun11.setRotationPoint(-27.5F, -10.0F, 0.0F);
      this.gun11.setTextureSize(64, 32);
      this.gun11.mirror = true;
      this.setRotation(this.gun11, 0.0F, 0.0F, 0.0F);
      this.gun12 = new ModelRenderer(this, 0, 20);
      this.gun12.addBox(0.0F, 0.0F, 0.0F, 1, 15, 0);
      this.gun12.setRotationPoint(0.0F, 1.5F, 0.0F);
      this.gun12.setTextureSize(64, 32);
      this.gun12.mirror = true;
      this.setRotation(this.gun12, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun4.render(f5);
      this.gun7.render(f5);
      this.gun9.render(f5);
      this.gun10.render(f5);
      this.gun11.render(f5);
      this.gun12.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
