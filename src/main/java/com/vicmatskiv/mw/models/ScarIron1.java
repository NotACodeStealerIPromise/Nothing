package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ScarIron1 extends ModelBase {
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
   ModelRenderer sight14;
   ModelRenderer sight15;
   ModelRenderer sight16;
   ModelRenderer sight17;
   ModelRenderer sight18;

   public ScarIron1() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.sight1 = new ModelRenderer(this, 0, 20);
      this.sight1.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
      this.sight1.setRotationPoint(-0.5F, -2.0F, 0.0F);
      this.sight1.setTextureSize(64, 32);
      this.sight1.mirror = true;
      this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
      this.sight2 = new ModelRenderer(this, 0, 20);
      this.sight2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.sight2.setRotationPoint(0.0F, -6.0F, 0.0F);
      this.sight2.setTextureSize(64, 32);
      this.sight2.mirror = true;
      this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
      this.sight3 = new ModelRenderer(this, 0, 20);
      this.sight3.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.sight3.setRotationPoint(-0.5F, -5.5F, 0.0F);
      this.sight3.setTextureSize(64, 32);
      this.sight3.mirror = true;
      this.setRotation(this.sight3, 0.0F, 0.0F, 0.0F);
      this.sight4 = new ModelRenderer(this, 0, 20);
      this.sight4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.sight4.setRotationPoint(2.5F, -5.5F, 0.0F);
      this.sight4.setTextureSize(64, 32);
      this.sight4.mirror = true;
      this.setRotation(this.sight4, 0.0F, 0.0F, 0.0F);
      this.sight5 = new ModelRenderer(this, 0, 0);
      this.sight5.addBox(0.0F, 0.0F, 0.0F, 6, 1, 3);
      this.sight5.setRotationPoint(-1.5F, 0.0F, -1.0F);
      this.sight5.setTextureSize(64, 32);
      this.sight5.mirror = true;
      this.setRotation(this.sight5, 0.0F, 0.0F, 0.0F);
      this.sight6 = new ModelRenderer(this, 0, 20);
      this.sight6.addBox(0.0F, 0.0F, 0.0F, 3, 2, 3);
      this.sight6.setRotationPoint(0.0F, -1.5F, -1.0F);
      this.sight6.setTextureSize(64, 32);
      this.sight6.mirror = true;
      this.setRotation(this.sight6, 0.0F, 0.0F, 0.0F);
      this.sight7 = new ModelRenderer(this, 0, 0);
      this.sight7.addBox(0.0F, 0.0F, 0.0F, 6, 4, 3);
      this.sight7.setRotationPoint(-1.5F, 1.0F, -1.0F);
      this.sight7.setTextureSize(64, 32);
      this.sight7.mirror = true;
      this.setRotation(this.sight7, 0.0F, 0.0F, 0.0F);
      this.sight8 = new ModelRenderer(this, 0, 0);
      this.sight8.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
      this.sight8.setRotationPoint(-0.5F, 2.0F, -1.0F);
      this.sight8.setTextureSize(64, 32);
      this.sight8.mirror = true;
      this.setRotation(this.sight8, 0.0F, 0.0F, 0.0F);
      this.sight9 = new ModelRenderer(this, 0, 0);
      this.sight9.addBox(0.0F, 0.0F, 0.0F, 1, 6, 2);
      this.sight9.setRotationPoint(3.6F, -5.0F, -0.5F);
      this.sight9.setTextureSize(64, 32);
      this.sight9.mirror = true;
      this.setRotation(this.sight9, 0.0F, 0.0F, 0.0F);
      this.sight10 = new ModelRenderer(this, 0, 0);
      this.sight10.addBox(0.0F, 0.0F, 0.0F, 1, 6, 2);
      this.sight10.setRotationPoint(-1.6F, -5.0F, -0.5F);
      this.sight10.setTextureSize(64, 32);
      this.sight10.mirror = true;
      this.setRotation(this.sight10, 0.0F, 0.0F, 0.0F);
      this.sight11 = new ModelRenderer(this, 0, 0);
      this.sight11.addBox(0.0F, 0.0F, 0.0F, 10, 2, 13);
      this.sight11.setRotationPoint(-3.5F, 5.0F, -8.0F);
      this.sight11.setTextureSize(64, 32);
      this.sight11.mirror = true;
      this.setRotation(this.sight11, 0.0F, 0.0F, 0.0F);
      this.sight12 = new ModelRenderer(this, 0, 0);
      this.sight12.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
      this.sight12.setRotationPoint(-0.5F, 4.0F, 2.0F);
      this.sight12.setTextureSize(64, 32);
      this.sight12.mirror = true;
      this.setRotation(this.sight12, -0.2602503F, 0.0F, 0.0F);
      this.sight13 = new ModelRenderer(this, 0, 0);
      this.sight13.addBox(0.0F, 0.0F, 0.0F, 6, 7, 3);
      this.sight13.setRotationPoint(-1.5F, 3.0F, -1.0F);
      this.sight13.setTextureSize(64, 32);
      this.sight13.mirror = true;
      this.setRotation(this.sight13, -1.375609F, 0.0F, 0.0F);
      this.sight14 = new ModelRenderer(this, 0, 0);
      this.sight14.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.sight14.setRotationPoint(4.5F, 4.0F, -5.0F);
      this.sight14.setTextureSize(64, 32);
      this.sight14.mirror = true;
      this.setRotation(this.sight14, 0.0F, 0.0F, 0.0F);
      this.sight15 = new ModelRenderer(this, 0, 0);
      this.sight15.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3);
      this.sight15.setRotationPoint(-4.5F, 4.0F, -5.0F);
      this.sight15.setTextureSize(64, 32);
      this.sight15.mirror = true;
      this.setRotation(this.sight15, 0.0F, 0.0F, 0.0F);
      this.sight16 = new ModelRenderer(this, 0, 0);
      this.sight16.addBox(0.0F, 0.0F, 0.0F, 10, 2, 3);
      this.sight16.setRotationPoint(-3.5F, 3.0F, -1.0F);
      this.sight16.setTextureSize(64, 32);
      this.sight16.mirror = true;
      this.setRotation(this.sight16, 0.0F, 0.0F, 0.0F);
      this.sight17 = new ModelRenderer(this, 0, 0);
      this.sight17.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.sight17.setRotationPoint(-5.5F, 2.5F, 0.5F);
      this.sight17.setTextureSize(64, 32);
      this.sight17.mirror = true;
      this.setRotation(this.sight17, -0.7807508F, 0.0F, 0.0F);
      this.sight18 = new ModelRenderer(this, 0, 0);
      this.sight18.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
      this.sight18.setRotationPoint(6.5F, 2.5F, 0.5F);
      this.sight18.setTextureSize(64, 32);
      this.sight18.mirror = true;
      this.setRotation(this.sight18, -0.7807508F, 0.0F, 0.0F);
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
      this.sight14.render(f5);
      this.sight15.render(f5);
      this.sight16.render(f5);
      this.sight17.render(f5);
      this.sight18.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
