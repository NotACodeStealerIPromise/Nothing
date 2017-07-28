package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HecateIIBoltAction extends ModelBase {
   ModelRenderer Shape1;
   ModelRenderer Shape11;
   ModelRenderer Shape12;
   ModelRenderer Shape22;
   ModelRenderer Shape2;
   ModelRenderer Shape33;
   ModelRenderer Shape3;
   ModelRenderer Shape4;

   public HecateIIBoltAction() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Shape1 = new ModelRenderer(this, 0, 0);
      this.Shape1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape1.setRotationPoint(2.0F, -16.5F, 2.2F);
      this.Shape1.setTextureSize(64, 32);
      this.Shape1.mirror = true;
      this.setRotation(this.Shape1, 0.0F, 0.0F, 0.0F);
      this.Shape11 = new ModelRenderer(this, 0, 0);
      this.Shape11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape11.setRotationPoint(2.0F, -15.5F, 2.2F);
      this.Shape11.setTextureSize(64, 32);
      this.Shape11.mirror = true;
      this.setRotation(this.Shape11, 0.0F, 0.0F, 0.0F);
      this.Shape12 = new ModelRenderer(this, 0, 0);
      this.Shape12.addBox(0.0F, 0.0F, 0.0F, 2, 1, 8);
      this.Shape12.setRotationPoint(1.5F, -16.0F, 2.2F);
      this.Shape12.setTextureSize(64, 32);
      this.Shape12.mirror = true;
      this.setRotation(this.Shape12, 0.0F, 0.0F, 0.0F);
      this.Shape22 = new ModelRenderer(this, 0, 0);
      this.Shape22.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape22.setRotationPoint(1.7F, -16.3F, 2.2F);
      this.Shape22.setTextureSize(64, 32);
      this.Shape22.mirror = true;
      this.setRotation(this.Shape22, 0.0F, 0.0F, 0.0F);
      this.Shape2 = new ModelRenderer(this, 0, 0);
      this.Shape2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape2.setRotationPoint(2.3F, -16.3F, 2.2F);
      this.Shape2.setTextureSize(64, 32);
      this.Shape2.mirror = true;
      this.setRotation(this.Shape2, 0.0F, 0.0F, 0.0F);
      this.Shape33 = new ModelRenderer(this, 0, 0);
      this.Shape33.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape33.setRotationPoint(2.3F, -15.7F, 2.2F);
      this.Shape33.setTextureSize(64, 32);
      this.Shape33.mirror = true;
      this.setRotation(this.Shape33, 0.0F, 0.0F, 0.0F);
      this.Shape3 = new ModelRenderer(this, 0, 0);
      this.Shape3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8);
      this.Shape3.setRotationPoint(1.7F, -15.7F, 2.2F);
      this.Shape3.setTextureSize(64, 32);
      this.Shape3.mirror = true;
      this.setRotation(this.Shape3, 0.0F, 0.0F, 0.0F);
      this.Shape4 = new ModelRenderer(this, 0, 0);
      this.Shape4.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1);
      this.Shape4.setRotationPoint(1.5F, -16.0F, 5.0F);
      this.Shape4.setTextureSize(64, 32);
      this.Shape4.mirror = true;
      this.setRotation(this.Shape4, 0.0F, 0.0F, 1.003822F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Shape1.render(f5);
      this.Shape11.render(f5);
      this.Shape12.render(f5);
      this.Shape22.render(f5);
      this.Shape2.render(f5);
      this.Shape33.render(f5);
      this.Shape3.render(f5);
      this.Shape4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
