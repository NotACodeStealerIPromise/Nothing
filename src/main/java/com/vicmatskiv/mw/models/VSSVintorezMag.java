package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VSSVintorezMag extends ModelBase {
   ModelRenderer Mag1;
   ModelRenderer Mag2;
   ModelRenderer Mag3;
   ModelRenderer Mag4;

   public VSSVintorezMag() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Mag1 = new ModelRenderer(this, 0, 0);
      this.Mag1.addBox(0.0F, 0.0F, 0.0F, 4, 9, 5);
      this.Mag1.setRotationPoint(0.5F, -0.5F, 0.5F);
      this.Mag1.setTextureSize(128, 64);
      this.Mag1.mirror = true;
      this.setRotation(this.Mag1, 2.862753F, 0.0F, 0.0F);
      this.Mag2 = new ModelRenderer(this, 0, 0);
      this.Mag2.addBox(0.0F, 0.0F, 0.0F, 3, 9, 2);
      this.Mag2.setRotationPoint(1.0F, -1.85F, -4.0F);
      this.Mag2.setTextureSize(128, 64);
      this.Mag2.mirror = true;
      this.setRotation(this.Mag2, 2.862753F, 0.0F, 0.0F);
      this.Mag3 = new ModelRenderer(this, 0, 0);
      this.Mag3.addBox(0.0F, 0.0F, 0.0F, 4, 2, 2);
      this.Mag3.setRotationPoint(0.5F, -1.85F, -4.1F);
      this.Mag3.setTextureSize(128, 64);
      this.Mag3.mirror = true;
      this.setRotation(this.Mag3, 2.862753F, 0.0F, 0.0F);
      this.Mag4 = new ModelRenderer(this, 0, 0);
      this.Mag4.addBox(0.0F, 0.0F, 0.0F, 5, 2, 5);
      this.Mag4.setRotationPoint(0.0F, -0.45F, 0.5F);
      this.Mag4.setTextureSize(128, 64);
      this.Mag4.mirror = true;
      this.setRotation(this.Mag4, 2.862753F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Mag1.render(f5);
      this.Mag2.render(f5);
      this.Mag3.render(f5);
      this.Mag4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
