package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AKMiron1 extends ModelBase {
   ModelRenderer sight1;
   ModelRenderer sight2;
   ModelRenderer sight3;
   ModelRenderer sight4;
   ModelRenderer sight5;
   ModelRenderer sight6;

   public AKMiron1() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.sight1 = new ModelRenderer(this, 0, 0);
      this.sight1.addBox(0.0F, 0.0F, 0.0F, 5, 2, 3);
      this.sight1.setRotationPoint(-0.5F, 5.0F, -2.0F);
      this.sight1.setTextureSize(64, 32);
      this.sight1.mirror = true;
      this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
      this.sight2 = new ModelRenderer(this, 0, 0);
      this.sight2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
      this.sight2.setRotationPoint(-1.5F, 4.0F, -2.5F);
      this.sight2.setTextureSize(64, 32);
      this.sight2.mirror = true;
      this.setRotation(this.sight2, 0.0F, 0.0F, 0.0F);
      this.sight3 = new ModelRenderer(this, 0, 0);
      this.sight3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 4);
      this.sight3.setRotationPoint(4.5F, 4.0F, -2.5F);
      this.sight3.setTextureSize(64, 32);
      this.sight3.mirror = true;
      this.setRotation(this.sight3, 0.0F, 0.0F, 0.0F);
      this.sight4 = new ModelRenderer(this, 0, 0);
      this.sight4.addBox(0.0F, 0.0F, 0.0F, 4, 2, 3);
      this.sight4.setRotationPoint(0.0F, 5.3F, 1.0F);
      this.sight4.setTextureSize(64, 32);
      this.sight4.mirror = true;
      this.setRotation(this.sight4, 0.0F, 0.0F, 0.0F);
      this.sight5 = new ModelRenderer(this, 0, 0);
      this.sight5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.sight5.setRotationPoint(0.0F, 3.3F, 3.0F);
      this.sight5.setTextureSize(64, 32);
      this.sight5.mirror = true;
      this.setRotation(this.sight5, 0.0F, 0.0F, 0.0F);
      this.sight6 = new ModelRenderer(this, 0, 0);
      this.sight6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.sight6.setRotationPoint(3.0F, 3.3F, 3.0F);
      this.sight6.setTextureSize(64, 32);
      this.sight6.mirror = true;
      this.setRotation(this.sight6, 0.0F, 0.0F, 0.0F);
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
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
