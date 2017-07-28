package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FNP90Sight extends ModelBase {
   ModelRenderer Sight1;
   ModelRenderer Sight2;
   ModelRenderer Sight3;
   ModelRenderer Sight4;
   ModelRenderer Sight5;
   ModelRenderer Sight6;
   ModelRenderer Sight7;
   ModelRenderer Sight8;
   ModelRenderer Sight9;
   ModelRenderer Sight10;
   ModelRenderer Sight11;

   public FNP90Sight() {
      this.textureWidth = 128;
      this.textureHeight = 32;
      this.Sight1 = new ModelRenderer(this, 0, 0);
      this.Sight1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 14);
      this.Sight1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.Sight1.setTextureSize(128, 32);
      this.Sight1.mirror = true;
      this.setRotation(this.Sight1, 0.0F, 0.0F, 0.0F);
      this.Sight2 = new ModelRenderer(this, 0, 0);
      this.Sight2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 14);
      this.Sight2.setRotationPoint(0.0F, -2.0F, 0.0F);
      this.Sight2.setTextureSize(128, 32);
      this.Sight2.mirror = true;
      this.setRotation(this.Sight2, 0.0F, 0.0F, 0.0F);
      this.Sight3 = new ModelRenderer(this, 0, 0);
      this.Sight3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 14);
      this.Sight3.setRotationPoint(4.0F, -2.0F, 0.0F);
      this.Sight3.setTextureSize(128, 32);
      this.Sight3.mirror = true;
      this.setRotation(this.Sight3, 0.0F, 0.0F, 0.0F);
      this.Sight4 = new ModelRenderer(this, 0, 0);
      this.Sight4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 8);
      this.Sight4.setRotationPoint(4.0F, -4.5F, 3.0F);
      this.Sight4.setTextureSize(128, 32);
      this.Sight4.mirror = true;
      this.setRotation(this.Sight4, 0.0F, 0.0F, 0.0F);
      this.Sight5 = new ModelRenderer(this, 0, 0);
      this.Sight5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 8);
      this.Sight5.setRotationPoint(0.0F, -4.5F, 3.0F);
      this.Sight5.setTextureSize(128, 32);
      this.Sight5.mirror = true;
      this.setRotation(this.Sight5, 0.0F, 0.0F, 0.0F);
      this.Sight6 = new ModelRenderer(this, 0, 0);
      this.Sight6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 8);
      this.Sight6.setRotationPoint(0.5F, -5.0F, 3.0F);
      this.Sight6.setTextureSize(128, 32);
      this.Sight6.mirror = true;
      this.setRotation(this.Sight6, 0.0F, 0.0F, 0.0F);
      this.Sight7 = new ModelRenderer(this, 0, 0);
      this.Sight7.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.Sight7.setRotationPoint(0.0F, -4.5F, 3.0F);
      this.Sight7.setTextureSize(128, 32);
      this.Sight7.mirror = true;
      this.setRotation(this.Sight7, -0.4089647F, 0.0F, 0.0F);
      this.Sight8 = new ModelRenderer(this, 0, 0);
      this.Sight8.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.Sight8.setRotationPoint(4.0F, -4.5F, 3.0F);
      this.Sight8.setTextureSize(128, 32);
      this.Sight8.mirror = true;
      this.setRotation(this.Sight8, -0.4089647F, 0.0F, 0.0F);
      this.Sight9 = new ModelRenderer(this, 0, 0);
      this.Sight9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.Sight9.setRotationPoint(4.0F, -3.0F, 11.0F);
      this.Sight9.setTextureSize(128, 32);
      this.Sight9.mirror = true;
      this.setRotation(this.Sight9, -0.669215F, 0.0F, 0.0F);
      this.Sight10 = new ModelRenderer(this, 0, 0);
      this.Sight10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.Sight10.setRotationPoint(0.0F, -3.0F, 11.0F);
      this.Sight10.setTextureSize(128, 32);
      this.Sight10.mirror = true;
      this.setRotation(this.Sight10, -0.669215F, 0.0F, 0.0F);
      this.Sight11 = new ModelRenderer(this, 0, 0);
      this.Sight11.addBox(0.0F, 0.0F, 0.0F, 3, 2, 11);
      this.Sight11.setRotationPoint(1.0F, -1.8F, 2.0F);
      this.Sight11.setTextureSize(128, 32);
      this.Sight11.mirror = true;
      this.setRotation(this.Sight11, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Sight1.render(f5);
      this.Sight2.render(f5);
      this.Sight3.render(f5);
      this.Sight4.render(f5);
      this.Sight5.render(f5);
      this.Sight6.render(f5);
      this.Sight7.render(f5);
      this.Sight8.render(f5);
      this.Sight9.render(f5);
      this.Sight10.render(f5);
      this.Sight11.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
