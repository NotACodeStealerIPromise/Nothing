package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MP40Mag extends ModelBase {
   ModelRenderer gun102;
   ModelRenderer gun103;

   public MP40Mag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun102 = new ModelRenderer(this, 0, 0);
      this.gun102.addBox(0.0F, 0.0F, 0.0F, 2, 20, 4);
      this.gun102.setRotationPoint(1.2F, -9.0F, -19.5F);
      this.gun102.setTextureSize(64, 32);
      this.gun102.mirror = true;
      this.setRotation(this.gun102, 0.0F, 0.0F, 0.0F);
      this.gun103 = new ModelRenderer(this, 0, 0);
      this.gun103.addBox(0.0F, 0.0F, 0.0F, 2, 20, 4);
      this.gun103.setRotationPoint(1.8F, -9.0F, -19.5F);
      this.gun103.setTextureSize(64, 32);
      this.gun103.mirror = true;
      this.setRotation(this.gun103, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun102.render(f5);
      this.gun103.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
