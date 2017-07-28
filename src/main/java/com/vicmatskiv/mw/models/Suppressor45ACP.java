package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Suppressor45ACP extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;
   ModelRenderer gun4;
   ModelRenderer gun5;
   ModelRenderer gun6;
   ModelRenderer gun7;
   ModelRenderer gun8;
   ModelRenderer gun9;

   public Suppressor45ACP() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun1 = new ModelRenderer(this, 0, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 2, 3, 22);
      this.gun1.setRotationPoint(0.0F, 0.0F, -2.0F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun2 = new ModelRenderer(this, 0, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 22);
      this.gun2.setRotationPoint(1.5F, 1.0F, -2.0F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 0, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 22);
      this.gun3.setRotationPoint(-0.5F, 1.0F, -2.0F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 0, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 22);
      this.gun4.setRotationPoint(0.0F, 3.0F, -2.0F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun5 = new ModelRenderer(this, 0, 0);
      this.gun5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 22);
      this.gun5.setRotationPoint(2.0F, 0.0F, -2.0F);
      this.gun5.setTextureSize(64, 32);
      this.gun5.mirror = true;
      this.setRotation(this.gun5, 0.0F, 0.0F, 1.07818F);
      this.gun6 = new ModelRenderer(this, 0, 0);
      this.gun6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 22);
      this.gun6.setRotationPoint(-0.5F, 2.7F, -2.0F);
      this.gun6.setTextureSize(64, 32);
      this.gun6.mirror = true;
      this.setRotation(this.gun6, 0.0F, 0.0F, 0.0F);
      this.gun7 = new ModelRenderer(this, 0, 0);
      this.gun7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 22);
      this.gun7.setRotationPoint(0.0F, 0.0F, -2.0F);
      this.gun7.setTextureSize(64, 32);
      this.gun7.mirror = true;
      this.setRotation(this.gun7, 0.0F, 0.0F, 0.4833219F);
      this.gun8 = new ModelRenderer(this, 0, 0);
      this.gun8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 22);
      this.gun8.setRotationPoint(-0.5F, 1.0F, -2.0F);
      this.gun8.setTextureSize(64, 32);
      this.gun8.mirror = true;
      this.setRotation(this.gun8, 0.0F, 0.0F, -1.07818F);
      this.gun9 = new ModelRenderer(this, 0, 0);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 22);
      this.gun9.setRotationPoint(2.5F, 1.0F, -2.0F);
      this.gun9.setTextureSize(64, 32);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 2.67686F);
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
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
