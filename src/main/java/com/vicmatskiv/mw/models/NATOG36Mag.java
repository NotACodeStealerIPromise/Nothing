package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class NATOG36Mag extends ModelBase {
   ModelRenderer gun133;
   ModelRenderer gun134;
   ModelRenderer gun135;
   ModelRenderer gun136;
   ModelRenderer gun137;
   ModelRenderer gun138;
   ModelRenderer gun139;
   ModelRenderer gun140;
   ModelRenderer gun141;
   ModelRenderer gun142;

   public NATOG36Mag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun133 = new ModelRenderer(this, 0, 50);
      this.gun133.addBox(0.0F, 0.0F, 0.0F, 1, 8, 2);
      this.gun133.setRotationPoint(0.8F, -12.4F, -2.8F);
      this.gun133.setTextureSize(64, 32);
      this.gun133.mirror = true;
      this.setRotation(this.gun133, -0.1487144F, 0.0F, 0.0F);
      this.gun134 = new ModelRenderer(this, 0, 50);
      this.gun134.addBox(0.0F, 0.0F, 0.0F, 3, 8, 2);
      this.gun134.setRotationPoint(1.2F, -12.4F, -2.8F);
      this.gun134.setTextureSize(64, 32);
      this.gun134.mirror = true;
      this.setRotation(this.gun134, -0.1487144F, 0.0F, 0.0F);
      this.gun135 = new ModelRenderer(this, 0, 50);
      this.gun135.addBox(0.0F, 0.0F, 0.0F, 3, 8, 3);
      this.gun135.setRotationPoint(1.2F, -11.9F, 0.1F);
      this.gun135.setTextureSize(64, 32);
      this.gun135.mirror = true;
      this.setRotation(this.gun135, -0.1487144F, 0.0F, 0.0F);
      this.gun136 = new ModelRenderer(this, 0, 50);
      this.gun136.addBox(0.0F, 0.0F, 0.0F, 1, 8, 3);
      this.gun136.setRotationPoint(0.8F, -11.9F, 0.1F);
      this.gun136.setTextureSize(64, 32);
      this.gun136.mirror = true;
      this.setRotation(this.gun136, -0.1487144F, 0.0F, 0.0F);
      this.gun137 = new ModelRenderer(this, 0, 50);
      this.gun137.addBox(0.0F, 0.0F, 0.0F, 1, 3, 5);
      this.gun137.setRotationPoint(0.8F, -3.6F, 1.9F);
      this.gun137.setTextureSize(64, 32);
      this.gun137.mirror = true;
      this.setRotation(this.gun137, -1.896109F, 0.0F, 0.0F);
      this.gun138 = new ModelRenderer(this, 0, 50);
      this.gun138.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5);
      this.gun138.setRotationPoint(1.2F, -3.6F, 1.9F);
      this.gun138.setTextureSize(64, 32);
      this.gun138.mirror = true;
      this.setRotation(this.gun138, -1.896109F, 0.0F, 0.0F);
      this.gun139 = new ModelRenderer(this, 0, 50);
      this.gun139.addBox(0.0F, 0.0F, 0.0F, 3, 2, 4);
      this.gun139.setRotationPoint(1.2F, -4.2F, -2.05F);
      this.gun139.setTextureSize(64, 32);
      this.gun139.mirror = true;
      this.setRotation(this.gun139, -1.896109F, 0.0F, 0.0F);
      this.gun140 = new ModelRenderer(this, 0, 50);
      this.gun140.addBox(0.0F, 0.0F, 0.0F, 1, 2, 4);
      this.gun140.setRotationPoint(0.8F, -4.2F, -2.05F);
      this.gun140.setTextureSize(64, 32);
      this.gun140.mirror = true;
      this.setRotation(this.gun140, -1.896109F, 0.0F, 0.0F);
      this.gun141 = new ModelRenderer(this, 0, 50);
      this.gun141.addBox(0.0F, 0.0F, 0.0F, 4, 1, 6);
      this.gun141.setRotationPoint(0.5F, -1.2F, -5.2F);
      this.gun141.setTextureSize(64, 32);
      this.gun141.mirror = true;
      this.setRotation(this.gun141, -0.2974289F, 0.0F, 0.0F);
      this.gun142 = new ModelRenderer(this, 0, 50);
      this.gun142.addBox(0.0F, 0.0F, 0.0F, 3, 12, 3);
      this.gun142.setRotationPoint(1.0F, -12.4F, -2.5F);
      this.gun142.setTextureSize(64, 32);
      this.gun142.mirror = true;
      this.setRotation(this.gun142, -0.1858931F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun133.render(f5);
      this.gun134.render(f5);
      this.gun135.render(f5);
      this.gun136.render(f5);
      this.gun137.render(f5);
      this.gun138.render(f5);
      this.gun139.render(f5);
      this.gun140.render(f5);
      this.gun141.render(f5);
      this.gun142.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
