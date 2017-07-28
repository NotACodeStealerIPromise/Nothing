package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AK12Mag extends ModelBase {
   ModelRenderer Mag1;
   ModelRenderer Mag2;
   ModelRenderer Mag3;
   ModelRenderer Mag4;
   ModelRenderer Mag5;
   ModelRenderer Mag6;

   public AK12Mag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.Mag1 = new ModelRenderer(this, 0, 0);
      this.Mag1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 7);
      this.Mag1.setRotationPoint(1.5F, 0.1F, -4.2F);
      this.Mag1.setTextureSize(64, 32);
      this.Mag1.mirror = true;
      this.setRotation(this.Mag1, -1.970466F, 0.0F, 0.0F);
      this.Mag2 = new ModelRenderer(this, 0, 0);
      this.Mag2.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2);
      this.Mag2.setRotationPoint(1.5F, -12.7F, -3.3F);
      this.Mag2.setTextureSize(64, 32);
      this.Mag2.mirror = true;
      this.setRotation(this.Mag2, -0.1858931F, 0.0F, 0.0F);
      this.Mag3 = new ModelRenderer(this, 0, 0);
      this.Mag3.addBox(0.0F, 0.0F, 0.0F, 3, 5, 7);
      this.Mag3.setRotationPoint(1.0F, -5.0F, 1.4F);
      this.Mag3.setTextureSize(64, 32);
      this.Mag3.mirror = true;
      this.setRotation(this.Mag3, -1.858931F, 0.0F, 0.0F);
      this.Mag4 = new ModelRenderer(this, 0, 0);
      this.Mag4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 7);
      this.Mag4.setRotationPoint(1.5F, -6.0F, -2.5F);
      this.Mag4.setTextureSize(64, 32);
      this.Mag4.mirror = true;
      this.setRotation(this.Mag4, -1.858931F, 0.0F, 0.0F);
      this.Mag5 = new ModelRenderer(this, 0, 0);
      this.Mag5.addBox(0.0F, 0.0F, 0.0F, 3, 7, 5);
      this.Mag5.setRotationPoint(1.0F, -12.7F, -2.2F);
      this.Mag5.setTextureSize(64, 32);
      this.Mag5.mirror = true;
      this.setRotation(this.Mag5, -0.1858931F, 0.0F, 0.0F);
      this.Mag6 = new ModelRenderer(this, 0, 0);
      this.Mag6.addBox(0.0F, 0.0F, 0.0F, 3, 5, 7);
      this.Mag6.setRotationPoint(1.0F, 1.6F, -0.5F);
      this.Mag6.setTextureSize(64, 32);
      this.Mag6.mirror = true;
      this.setRotation(this.Mag6, -1.970466F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Mag1.render(f5);
      this.Mag2.render(f5);
      this.Mag3.render(f5);
      this.Mag4.render(f5);
      this.Mag5.render(f5);
      this.Mag6.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
