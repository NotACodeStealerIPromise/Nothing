package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Bullet extends ModelBase {
   ModelRenderer bullet;

   public Bullet() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.bullet = new ModelRenderer(this, 0, 0);
      this.bullet.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.bullet.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.bullet.setTextureSize(64, 32);
      this.bullet.mirror = true;
      this.setRotation(this.bullet, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.bullet.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
