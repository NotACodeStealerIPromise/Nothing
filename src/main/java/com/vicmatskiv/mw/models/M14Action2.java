package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M14Action2 extends ModelBase {
   ModelRenderer Action1;
   ModelRenderer Action4;
   ModelRenderer Action5;
   ModelRenderer Action8;
   ModelRenderer Action9;
   ModelRenderer Action10;
   ModelRenderer Action11;

   public M14Action2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Action1 = new ModelRenderer(this, 0, 0);
      this.Action1.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action1.setRotationPoint(-2.0F, -15.7F, -37.0F);
      this.Action1.setTextureSize(64, 32);
      this.Action1.mirror = true;
      this.setRotation(this.Action1, 0.0F, 0.0F, 0.0F);
      this.Action4 = new ModelRenderer(this, 0, 0);
      this.Action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action4.setRotationPoint(-3.2F, -14.2F, -37.5F);
      this.Action4.setTextureSize(64, 32);
      this.Action4.mirror = true;
      this.setRotation(this.Action4, 0.0F, 0.0F, 0.0F);
      this.Action5 = new ModelRenderer(this, 0, 0);
      this.Action5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action5.setRotationPoint(-0.8F, -14.2F, -37.5F);
      this.Action5.setTextureSize(64, 32);
      this.Action5.mirror = true;
      this.setRotation(this.Action5, 0.0F, 0.0F, 0.0F);
      this.Action8 = new ModelRenderer(this, 0, 0);
      this.Action8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action8.setRotationPoint(-2.0F, -15.7F, -37.0F);
      this.Action8.setTextureSize(64, 32);
      this.Action8.mirror = true;
      this.setRotation(this.Action8, 0.0F, 0.0F, 0.9294653F);
      this.Action9 = new ModelRenderer(this, 0, 0);
      this.Action9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action9.setRotationPoint(-3.2F, -14.2F, -37.5F);
      this.Action9.setTextureSize(64, 32);
      this.Action9.mirror = true;
      this.setRotation(this.Action9, 0.0F, 0.0F, -1.152537F);
      this.Action10 = new ModelRenderer(this, 0, 0);
      this.Action10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action10.setRotationPoint(-1.0F, -15.7F, -37.0F);
      this.Action10.setTextureSize(64, 32);
      this.Action10.mirror = true;
      this.setRotation(this.Action10, 0.0F, 0.0F, 0.7063936F);
      this.Action11 = new ModelRenderer(this, 0, 0);
      this.Action11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 19);
      this.Action11.setRotationPoint(0.2F, -14.2F, -37.5F);
      this.Action11.setTextureSize(64, 32);
      this.Action11.mirror = true;
      this.setRotation(this.Action11, 0.0F, 0.0F, 2.670354F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Action1.render(f5);
      this.Action4.render(f5);
      this.Action5.render(f5);
      this.Action8.render(f5);
      this.Action9.render(f5);
      this.Action10.render(f5);
      this.Action11.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
