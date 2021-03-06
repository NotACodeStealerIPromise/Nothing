package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class MagnumCase extends ModelBase {
   ModelRenderer gun78;
   ModelRenderer gun79;
   ModelRenderer gun80;
   ModelRenderer gun81;
   ModelRenderer gun90;
   ModelRenderer gun91;
   ModelRenderer gun92;
   ModelRenderer gun93;
   ModelRenderer gun94;
   ModelRenderer gun95;
   ModelRenderer gun96;
   ModelRenderer gun98;
   ModelRenderer gun99;
   ModelRenderer gun100;
   ModelRenderer gun101;

   public MagnumCase() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.gun78 = new ModelRenderer(this, 100, 0);
      this.gun78.addBox(0.0F, 0.0F, 0.0F, 3, 3, 5);
      this.gun78.setRotationPoint(-3.0F, -14.3F, -13.0F);
      this.gun78.setTextureSize(64, 32);
      this.gun78.mirror = true;
      this.setRotation(this.gun78, 0.0F, 0.0F, 0.0F);
      this.gun79 = new ModelRenderer(this, 100, 0);
      this.gun79.addBox(0.0F, 0.0F, 0.0F, 3, 2, 5);
      this.gun79.setRotationPoint(-3.0F, -11.7F, -13.0F);
      this.gun79.setTextureSize(64, 32);
      this.gun79.mirror = true;
      this.setRotation(this.gun79, 0.0F, 0.0F, 0.0F);
      this.gun80 = new ModelRenderer(this, 100, 0);
      this.gun80.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.gun80.setRotationPoint(-3.0F, -10.7F, -8.5F);
      this.gun80.setTextureSize(64, 32);
      this.gun80.mirror = true;
      this.setRotation(this.gun80, 0.0F, 0.0F, 0.0F);
      this.gun81 = new ModelRenderer(this, 100, 0);
      this.gun81.addBox(0.0F, 0.0F, 0.0F, 3, 4, 1);
      this.gun81.setRotationPoint(-3.0F, -14.3F, -8.5F);
      this.gun81.setTextureSize(64, 32);
      this.gun81.mirror = true;
      this.setRotation(this.gun81, 0.0F, 0.0F, 0.0F);
      this.gun90 = new ModelRenderer(this, 100, 0);
      this.gun90.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
      this.gun90.setRotationPoint(0.0F, -14.3F, -13.0F);
      this.gun90.setTextureSize(64, 32);
      this.gun90.mirror = true;
      this.setRotation(this.gun90, 0.0F, 0.0F, 0.7063936F);
      this.gun91 = new ModelRenderer(this, 100, 0);
      this.gun91.addBox(0.0F, 0.0F, 0.0F, 1, 2, 5);
      this.gun91.setRotationPoint(-3.0F, -14.3F, -13.0F);
      this.gun91.setTextureSize(64, 32);
      this.gun91.mirror = true;
      this.setRotation(this.gun91, 0.0F, 0.0F, 0.8922867F);
      this.gun92 = new ModelRenderer(this, 100, 0);
      this.gun92.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.gun92.setRotationPoint(0.0F, -14.3F, -8.5F);
      this.gun92.setTextureSize(64, 32);
      this.gun92.mirror = true;
      this.setRotation(this.gun92, 0.0F, 0.0F, 0.7063936F);
      this.gun93 = new ModelRenderer(this, 100, 0);
      this.gun93.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.gun93.setRotationPoint(-3.0F, -14.3F, -8.5F);
      this.gun93.setTextureSize(64, 32);
      this.gun93.mirror = true;
      this.setRotation(this.gun93, 0.0F, 0.0F, 0.8922867F);
      this.gun94 = new ModelRenderer(this, 100, 0);
      this.gun94.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
      this.gun94.setRotationPoint(-3.0F, -9.7F, -13.0F);
      this.gun94.setTextureSize(64, 32);
      this.gun94.mirror = true;
      this.setRotation(this.gun94, 0.0F, 0.0F, -2.453788F);
      this.gun95 = new ModelRenderer(this, 100, 0);
      this.gun95.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.gun95.setRotationPoint(-3.0F, -9.7F, -8.5F);
      this.gun95.setTextureSize(64, 32);
      this.gun95.mirror = true;
      this.setRotation(this.gun95, 0.0F, 0.0F, -2.453788F);
      this.gun96 = new ModelRenderer(this, 100, 0);
      this.gun96.addBox(0.0F, 0.0F, 0.0F, 1, 2, 5);
      this.gun96.setRotationPoint(0.0F, -9.7F, -13.0F);
      this.gun96.setTextureSize(64, 32);
      this.gun96.mirror = true;
      this.setRotation(this.gun96, 0.0F, 0.0F, -2.305074F);
      this.gun98 = new ModelRenderer(this, 100, 0);
      this.gun98.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.gun98.setRotationPoint(0.0F, -9.7F, -8.5F);
      this.gun98.setTextureSize(64, 32);
      this.gun98.mirror = true;
      this.setRotation(this.gun98, 0.0F, 0.0F, -2.305074F);
      this.gun99 = new ModelRenderer(this, 100, 0);
      this.gun99.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.gun99.setRotationPoint(0.5F, -13.0F, -9.5F);
      this.gun99.setTextureSize(64, 32);
      this.gun99.mirror = true;
      this.setRotation(this.gun99, 0.0F, 0.0F, 0.0F);
      this.gun100 = new ModelRenderer(this, 100, 0);
      this.gun100.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.gun100.setRotationPoint(-4.5F, -13.0F, -9.5F);
      this.gun100.setTextureSize(64, 32);
      this.gun100.mirror = true;
      this.setRotation(this.gun100, 0.0F, 0.0F, 0.0F);
      this.gun101 = new ModelRenderer(this, 100, 0);
      this.gun101.addBox(0.0F, 0.0F, 0.0F, 5, 2, 4);
      this.gun101.setRotationPoint(-4.0F, -13.0F, -13.0F);
      this.gun101.setTextureSize(64, 32);
      this.gun101.mirror = true;
      this.setRotation(this.gun101, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun78.render(f5);
      this.gun79.render(f5);
      this.gun80.render(f5);
      this.gun81.render(f5);
      this.gun90.render(f5);
      this.gun91.render(f5);
      this.gun92.render(f5);
      this.gun93.render(f5);
      this.gun94.render(f5);
      this.gun95.render(f5);
      this.gun96.render(f5);
      this.gun98.render(f5);
      this.gun99.render(f5);
      this.gun100.render(f5);
      this.gun101.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
