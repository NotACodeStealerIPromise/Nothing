package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M4Iron2 extends ModelBase {
   ModelRenderer sight4;
   ModelRenderer sight5;
   ModelRenderer sight7;
   ModelRenderer sight9;
   ModelRenderer sight10;
   ModelRenderer sight11;
   ModelRenderer sight12;

   public M4Iron2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.sight4 = new ModelRenderer(this, 0, 0);
      this.sight4.addBox(0.0F, 0.0F, 0.0F, 2, 6, 1);
      this.sight4.setRotationPoint(-2.0F, -3.0F, 0.0F);
      this.sight4.setTextureSize(64, 32);
      this.sight4.mirror = true;
      this.setRotation(this.sight4, 0.0F, 0.0F, 0.0F);
      this.sight5 = new ModelRenderer(this, 0, 0);
      this.sight5.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.sight5.setRotationPoint(-2.0F, -3.0F, 1.0F);
      this.sight5.setTextureSize(64, 32);
      this.sight5.mirror = true;
      this.setRotation(this.sight5, 0.0F, 0.0F, 0.0F);
      this.sight7 = new ModelRenderer(this, 0, 0);
      this.sight7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
      this.sight7.setRotationPoint(-2.0F, -3.0F, 2.0F);
      this.sight7.setTextureSize(64, 32);
      this.sight7.mirror = true;
      this.setRotation(this.sight7, -1.189716F, 0.0F, 0.0F);
      this.sight9 = new ModelRenderer(this, 0, 0);
      this.sight9.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4);
      this.sight9.setRotationPoint(-2.0F, 3.0F, 0.0F);
      this.sight9.setTextureSize(64, 32);
      this.sight9.mirror = true;
      this.setRotation(this.sight9, 0.0F, 0.0F, 0.0F);
      this.sight10 = new ModelRenderer(this, 0, 0);
      this.sight10.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.sight10.setRotationPoint(-2.0F, 1.6F, 2.9F);
      this.sight10.setTextureSize(64, 32);
      this.sight10.mirror = true;
      this.setRotation(this.sight10, 0.0F, 0.0F, 0.0F);
      this.sight11 = new ModelRenderer(this, 0, 0);
      this.sight11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.sight11.setRotationPoint(-1.5F, -1.0F, 0.5F);
      this.sight11.setTextureSize(64, 32);
      this.sight11.mirror = true;
      this.setRotation(this.sight11, 0.0F, 0.0F, 0.0F);
      this.sight12 = new ModelRenderer(this, 0, 0);
      this.sight12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.sight12.setRotationPoint(-1.5F, 2.5F, 0.5F);
      this.sight12.setTextureSize(64, 32);
      this.sight12.mirror = true;
      this.setRotation(this.sight12, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.sight4.render(f5);
      this.sight5.render(f5);
      this.sight7.render(f5);
      this.sight9.render(f5);
      this.sight10.render(f5);
      this.sight11.render(f5);
      this.sight12.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
