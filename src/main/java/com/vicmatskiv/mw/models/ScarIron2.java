package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ScarIron2 extends ModelBase {
   ModelRenderer sight1;
   ModelRenderer sight2;
   ModelRenderer sight3;
   ModelRenderer sight4;
   ModelRenderer sight5;
   ModelRenderer sight6;
   ModelRenderer sight7;
   ModelRenderer sight8;
   ModelRenderer sight9;
   ModelRenderer sight10;
   ModelRenderer sight11;
   ModelRenderer sight12;
   ModelRenderer sight13;

   public ScarIron2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.sight1 = new ModelRenderer(this, 0, 0);
      this.sight1.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2);
      this.sight1.setRotationPoint(0.0F, -6.0F, 0.0F);
      this.sight1.setTextureSize(64, 32);
      this.sight1.mirror = true;
      this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
      this.sight2 = new ModelRenderer(this, 0, 0);
      this.sight2.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.sight2.setRotationPoint(-1.5F, -4.0F, 0.0F);
      this.sight2.setTextureSize(64, 32);
      this.sight2.mirror = true;
      this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
      this.sight3 = new ModelRenderer(this, 0, 0);
      this.sight3.addBox(0.0F, 0.0F, 0.0F, 1, 5, 2);
      this.sight3.setRotationPoint(-3.0F, -6.0F, 0.0F);
      this.sight3.setTextureSize(64, 32);
      this.sight3.mirror = true;
      this.setRotation(this.sight3, 0.0F, 0.0F, 0.0F);
      this.sight4 = new ModelRenderer(this, 0, 0);
      this.sight4.addBox(0.0F, 0.0F, 0.0F, 2, 8, 2);
      this.sight4.setRotationPoint(-2.0F, -3.0F, 0.0F);
      this.sight4.setTextureSize(64, 32);
      this.sight4.mirror = true;
      this.setRotation(this.sight4, 0.0F, 0.0F, 0.0F);
      this.sight5 = new ModelRenderer(this, 0, 0);
      this.sight5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.sight5.setRotationPoint(-3.0F, 2.0F, 0.0F);
      this.sight5.setTextureSize(64, 32);
      this.sight5.mirror = true;
      this.setRotation(this.sight5, 0.0F, 0.0F, 0.0F);
      this.sight6 = new ModelRenderer(this, 0, 0);
      this.sight6.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2);
      this.sight6.setRotationPoint(0.0F, 2.0F, 0.0F);
      this.sight6.setTextureSize(64, 32);
      this.sight6.mirror = true;
      this.setRotation(this.sight6, 0.0F, 0.0F, 0.0F);
      this.sight7 = new ModelRenderer(this, 0, 0);
      this.sight7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.sight7.setRotationPoint(1.0F, -1.0F, 0.0F);
      this.sight7.setTextureSize(64, 32);
      this.sight7.mirror = true;
      this.setRotation(this.sight7, 0.0F, 0.0F, 2.156359F);
      this.sight8 = new ModelRenderer(this, 0, 0);
      this.sight8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.sight8.setRotationPoint(-3.0F, -1.0F, 0.0F);
      this.sight8.setTextureSize(64, 32);
      this.sight8.mirror = true;
      this.setRotation(this.sight8, 0.0F, 0.0F, -0.5576792F);
      this.sight9 = new ModelRenderer(this, 0, 0);
      this.sight9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.sight9.setRotationPoint(1.0F, 2.0F, 0.0F);
      this.sight9.setTextureSize(64, 32);
      this.sight9.mirror = true;
      this.setRotation(this.sight9, 0.0F, 0.0F, 2.639681F);
      this.sight10 = new ModelRenderer(this, 0, 0);
      this.sight10.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.sight10.setRotationPoint(-3.0F, 2.0F, 0.0F);
      this.sight10.setTextureSize(64, 32);
      this.sight10.mirror = true;
      this.setRotation(this.sight10, 0.0F, 0.0F, -1.115358F);
      this.sight11 = new ModelRenderer(this, 0, 0);
      this.sight11.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.sight11.setRotationPoint(-2.0F, -7.0F, 0.0F);
      this.sight11.setTextureSize(64, 32);
      this.sight11.mirror = true;
      this.setRotation(this.sight11, 0.0F, 0.0F, 0.0F);
      this.sight12 = new ModelRenderer(this, 0, 0);
      this.sight12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.sight12.setRotationPoint(-2.5F, -6.5F, 0.0F);
      this.sight12.setTextureSize(64, 32);
      this.sight12.mirror = true;
      this.setRotation(this.sight12, 0.0F, 0.0F, 0.0F);
      this.sight13 = new ModelRenderer(this, 0, 0);
      this.sight13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.sight13.setRotationPoint(-0.5F, -6.5F, 0.0F);
      this.sight13.setTextureSize(64, 32);
      this.sight13.mirror = true;
      this.setRotation(this.sight13, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.sight1.render(f5);
      this.sight2.render(f5);
      this.sight3.render(f5);
      this.sight4.render(f5);
      this.sight5.render(f5);
      this.sight6.render(f5);
      this.sight7.render(f5);
      this.sight8.render(f5);
      this.sight9.render(f5);
      this.sight10.render(f5);
      this.sight11.render(f5);
      this.sight12.render(f5);
      this.sight13.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}