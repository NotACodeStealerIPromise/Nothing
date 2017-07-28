package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ACRAction extends ModelBase {
   ModelRenderer ACRAction1;
   ModelRenderer ACRAction2;
   ModelRenderer ACRAction3;
   ModelRenderer ACRAction4;

   public ACRAction() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.ACRAction1 = new ModelRenderer(this, 0, 0);
      this.ACRAction1.addBox(0.0F, 0.0F, 0.0F, 5, 1, 1);
      this.ACRAction1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.ACRAction1.setTextureSize(128, 64);
      this.ACRAction1.mirror = true;
      this.setRotation(this.ACRAction1, 0.0F, 0.0F, 0.0F);
      this.ACRAction2 = new ModelRenderer(this, 0, 0);
      this.ACRAction2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 2);
      this.ACRAction2.setRotationPoint(0.0F, 0.0F, 0.5F);
      this.ACRAction2.setTextureSize(128, 64);
      this.ACRAction2.mirror = true;
      this.setRotation(this.ACRAction2, 0.0F, 0.0F, 0.0F);
      this.ACRAction3 = new ModelRenderer(this, 0, 0);
      this.ACRAction3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.ACRAction3.setRotationPoint(2.6F, 0.0F, 0.5F);
      this.ACRAction3.setTextureSize(128, 64);
      this.ACRAction3.mirror = true;
      this.setRotation(this.ACRAction3, 0.0F, 0.0F, 0.0F);
      this.ACRAction4 = new ModelRenderer(this, 0, 0);
      this.ACRAction4.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.ACRAction4.setRotationPoint(5.0F, 0.0F, 1.0F);
      this.ACRAction4.setTextureSize(128, 64);
      this.ACRAction4.mirror = true;
      this.setRotation(this.ACRAction4, 0.0F, -2.342252F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.ACRAction1.render(f5);
      this.ACRAction2.render(f5);
      this.ACRAction3.render(f5);
      this.ACRAction4.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
