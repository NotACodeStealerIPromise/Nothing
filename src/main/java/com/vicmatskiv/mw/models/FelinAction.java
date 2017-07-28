package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FelinAction extends ModelBase {
   ModelRenderer CarryHandle146;
   ModelRenderer CarryHandle147;
   ModelRenderer CarryHandle148;
   ModelRenderer CarryHandle149;
   ModelRenderer CarryHandle150;

   public FelinAction() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.CarryHandle146 = new ModelRenderer(this, 0, 0);
      this.CarryHandle146.addBox(0.0F, 0.0F, 0.0F, 4, 4, 2);
      this.CarryHandle146.setRotationPoint(-4.0F, -9.0F, -26.0F);
      this.CarryHandle146.setTextureSize(64, 32);
      this.CarryHandle146.mirror = true;
      this.setRotation(this.CarryHandle146, 0.0F, 0.0F, 0.0F);
      this.CarryHandle147 = new ModelRenderer(this, 0, 0);
      this.CarryHandle147.addBox(0.0F, 0.0F, 0.0F, 4, 2, 4);
      this.CarryHandle147.setRotationPoint(-4.0F, -9.0F, -24.0F);
      this.CarryHandle147.setTextureSize(64, 32);
      this.CarryHandle147.mirror = true;
      this.setRotation(this.CarryHandle147, -1.115358F, 0.0F, 0.0F);
      this.CarryHandle148 = new ModelRenderer(this, 0, 0);
      this.CarryHandle148.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.CarryHandle148.setRotationPoint(-0.2F, -9.5F, -26.0F);
      this.CarryHandle148.setTextureSize(64, 32);
      this.CarryHandle148.mirror = true;
      this.setRotation(this.CarryHandle148, 0.0F, 0.0F, 0.0F);
      this.CarryHandle149 = new ModelRenderer(this, 0, 0);
      this.CarryHandle149.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.CarryHandle149.setRotationPoint(0.0F, -9.5F, -25.0F);
      this.CarryHandle149.setTextureSize(64, 32);
      this.CarryHandle149.mirror = true;
      this.setRotation(this.CarryHandle149, 0.0F, 0.0F, 0.0F);
      this.CarryHandle150 = new ModelRenderer(this, 0, 0);
      this.CarryHandle150.addBox(0.0F, 0.0F, 0.0F, 1, 2, 3);
      this.CarryHandle150.setRotationPoint(1.0F, -9.5F, -24.0F);
      this.CarryHandle150.setTextureSize(64, 32);
      this.CarryHandle150.mirror = true;
      this.setRotation(this.CarryHandle150, 0.0F, 1.933288F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.CarryHandle146.render(f5);
      this.CarryHandle147.render(f5);
      this.CarryHandle148.render(f5);
      this.CarryHandle149.render(f5);
      this.CarryHandle150.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
