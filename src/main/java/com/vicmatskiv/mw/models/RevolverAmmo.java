package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class RevolverAmmo extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;
   ModelRenderer gun4;
   ModelRenderer gun6;
   ModelRenderer gun7;
   ModelRenderer gun8;
   ModelRenderer gun9;

   public RevolverAmmo() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.gun1 = new ModelRenderer(this, 0, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 3, 5, 1);
      this.gun1.setRotationPoint(-3.0F, -14.5F, -8.3F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun2 = new ModelRenderer(this, 0, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 5, 3, 1);
      this.gun2.setRotationPoint(-4.0F, -13.5F, -8.3F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 30, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun3.setRotationPoint(-3.0F, -14.5F, -10.3F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 30, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun4.setRotationPoint(-1.0F, -14.5F, -10.3F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun6 = new ModelRenderer(this, 30, 0);
      this.gun6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun6.setRotationPoint(0.0F, -12.5F, -10.3F);
      this.gun6.setTextureSize(64, 32);
      this.gun6.mirror = true;
      this.setRotation(this.gun6, 0.0F, 0.0F, 0.0F);
      this.gun7 = new ModelRenderer(this, 30, 0);
      this.gun7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun7.setRotationPoint(-1.0F, -10.5F, -10.3F);
      this.gun7.setTextureSize(64, 32);
      this.gun7.mirror = true;
      this.setRotation(this.gun7, 0.0F, 0.0F, 0.0F);
      this.gun8 = new ModelRenderer(this, 30, 0);
      this.gun8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun8.setRotationPoint(-3.0F, -10.5F, -10.3F);
      this.gun8.setTextureSize(64, 32);
      this.gun8.mirror = true;
      this.setRotation(this.gun8, 0.0F, 0.0F, 0.0F);
      this.gun9 = new ModelRenderer(this, 30, 0);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.gun9.setRotationPoint(-4.0F, -12.5F, -10.3F);
      this.gun9.setTextureSize(64, 32);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun2.render(f5);
      this.gun3.render(f5);
      this.gun4.render(f5);
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
