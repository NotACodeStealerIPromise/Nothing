package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Grenade extends ModelBase {
   ModelRenderer Grenade1;
   ModelRenderer Grenade2;

   public Grenade() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Grenade1 = new ModelRenderer(this, 0, 0);
      this.Grenade1.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5);
      this.Grenade1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Grenade1.setTextureSize(64, 32);
      this.Grenade1.mirror = true;
      this.setRotation(this.Grenade1, 0.0F, 0.0F, 0.0F);
      this.Grenade2 = new ModelRenderer(this, 0, 0);
      this.Grenade2.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Grenade2.setRotationPoint(0.5F, 0.5F, -1.0F);
      this.Grenade2.setTextureSize(64, 32);
      this.Grenade2.mirror = true;
      this.setRotation(this.Grenade2, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Grenade1.render(f5);
      this.Grenade2.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
