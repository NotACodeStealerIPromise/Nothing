package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Acog2 extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;
   ModelRenderer gun4;
   ModelRenderer gun5;
   ModelRenderer gun6;
   ModelRenderer gun7;
   ModelRenderer gun8;
   ModelRenderer gun9;
   ModelRenderer gun10;
   ModelRenderer gun11;
   ModelRenderer gun12;

   public Acog2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.gun1 = new ModelRenderer(this, 0, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 0);
      this.gun1.setRotationPoint(-1.5F, -10.0F, 0.0F);
      this.gun1.setTextureSize(128, 64);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun2 = new ModelRenderer(this, 0, 20);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 8, 1, 0);
      this.gun2.setRotationPoint(2.5F, -10.0F, 0.0F);
      this.gun2.setTextureSize(128, 64);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 0, 20);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 8, 1, 0);
      this.gun3.setRotationPoint(-9.5F, -10.0F, 0.0F);
      this.gun3.setTextureSize(128, 64);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 0, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 1, 7, 0);
      this.gun4.setRotationPoint(0.0F, -9.0F, 0.0F);
      this.gun4.setTextureSize(128, 64);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun5 = new ModelRenderer(this, 0, 0);
      this.gun5.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0);
      this.gun5.setRotationPoint(-1.0F, -7.0F, 0.0F);
      this.gun5.setTextureSize(128, 64);
      this.gun5.mirror = true;
      this.setRotation(this.gun5, 0.0F, 0.0F, 0.0F);
      this.gun6 = new ModelRenderer(this, 0, 0);
      this.gun6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 0);
      this.gun6.setRotationPoint(-1.0F, -4.0F, 0.0F);
      this.gun6.setTextureSize(128, 64);
      this.gun6.mirror = true;
      this.setRotation(this.gun6, 0.0F, 0.0F, 0.0F);
      this.gun7 = new ModelRenderer(this, 0, 20);
      this.gun7.addBox(0.0F, 0.0F, 0.0F, 1, 5, 0);
      this.gun7.setRotationPoint(0.0F, -2.0F, 0.0F);
      this.gun7.setTextureSize(128, 64);
      this.gun7.mirror = true;
      this.setRotation(this.gun7, 0.0F, 0.0F, 0.0F);
      this.gun8 = new ModelRenderer(this, 0, 20);
      this.gun8.addBox(0.0F, 0.0F, 0.0F, 2, 1, 0);
      this.gun8.setRotationPoint(-0.5F, -1.0F, 0.0F);
      this.gun8.setTextureSize(128, 64);
      this.gun8.mirror = true;
      this.setRotation(this.gun8, 0.0F, 0.0F, 0.0F);
      this.gun9 = new ModelRenderer(this, 0, 20);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 20, 0);
      this.gun9.setRotationPoint(0.0F, -30.0F, 0.0F);
      this.gun9.setTextureSize(128, 64);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 0.0F);
      this.gun10 = new ModelRenderer(this, 0, 20);
      this.gun10.addBox(0.0F, 0.0F, 0.0F, 18, 2, 0);
      this.gun10.setRotationPoint(10.5F, -10.5F, 0.0F);
      this.gun10.setTextureSize(128, 64);
      this.gun10.mirror = true;
      this.setRotation(this.gun10, 0.0F, 0.0F, 0.0F);
      this.gun11 = new ModelRenderer(this, 0, 20);
      this.gun11.addBox(0.0F, 0.0F, 0.0F, 18, 2, 0);
      this.gun11.setRotationPoint(-27.5F, -10.5F, 0.0F);
      this.gun11.setTextureSize(128, 64);
      this.gun11.mirror = true;
      this.setRotation(this.gun11, 0.0F, 0.0F, 0.0F);
      this.gun12 = new ModelRenderer(this, 0, 20);
      this.gun12.addBox(0.0F, 0.0F, 0.0F, 2, 15, 0);
      this.gun12.setRotationPoint(-0.5F, 3.0F, 0.0F);
      this.gun12.setTextureSize(128, 64);
      this.gun12.mirror = true;
      this.setRotation(this.gun12, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun2.render(f5);
      this.gun3.render(f5);
      this.gun4.render(f5);
      this.gun5.render(f5);
      this.gun6.render(f5);
      this.gun7.render(f5);
      this.gun8.render(f5);
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