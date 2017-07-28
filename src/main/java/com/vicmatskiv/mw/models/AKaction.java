package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AKaction extends ModelBase {
   ModelRenderer Action1;
   ModelRenderer Action2;
   ModelRenderer Action3;
   ModelRenderer Action4;
   ModelRenderer Action5;
   ModelRenderer Action6;
   ModelRenderer Action7;
   ModelRenderer Action8;
   ModelRenderer Action9;
   ModelRenderer Action10;
   ModelRenderer Action11;
   ModelRenderer Action12;

   public AKaction() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Action1 = new ModelRenderer(this, 0, 0);
      this.Action1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 13);
      this.Action1.setRotationPoint(-2.5F, -15.0F, -33.5F);
      this.Action1.setTextureSize(128, 64);
      this.Action1.mirror = true;
      this.setRotation(this.Action1, 0.0F, 0.0F, 0.1858931F);
      this.Action2 = new ModelRenderer(this, 0, 0);
      this.Action2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 7);
      this.Action2.setRotationPoint(-3.5F, -13.0F, -33.5F);
      this.Action2.setTextureSize(128, 64);
      this.Action2.mirror = true;
      this.setRotation(this.Action2, 0.0F, 0.0F, 0.0F);
      this.Action3 = new ModelRenderer(this, 0, 0);
      this.Action3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 6);
      this.Action3.setRotationPoint(-3.2F, -13.0F, -26.5F);
      this.Action3.setTextureSize(128, 64);
      this.Action3.mirror = true;
      this.setRotation(this.Action3, 0.0F, 0.0F, 0.0F);
      this.Action4 = new ModelRenderer(this, 0, 0);
      this.Action4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 11);
      this.Action4.setRotationPoint(-3.0F, -13.0F, -20.5F);
      this.Action4.setTextureSize(128, 64);
      this.Action4.mirror = true;
      this.setRotation(this.Action4, 0.0F, 0.0F, 0.0F);
      this.Action5 = new ModelRenderer(this, 0, 0);
      this.Action5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.Action5.setRotationPoint(-3.5F, -13.0F, -26.5F);
      this.Action5.setTextureSize(128, 64);
      this.Action5.mirror = true;
      this.setRotation(this.Action5, 0.0F, 0.1858931F, 0.0F);
      this.Action6 = new ModelRenderer(this, 0, 0);
      this.Action6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Action6.setRotationPoint(-7.0F, -12.2F, -33.5F);
      this.Action6.setTextureSize(128, 64);
      this.Action6.mirror = true;
      this.setRotation(this.Action6, 0.0F, 0.0F, 0.0F);
      this.Action7 = new ModelRenderer(this, 0, 0);
      this.Action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
      this.Action7.setRotationPoint(-3.5F, -13.0F, -33.5F);
      this.Action7.setTextureSize(128, 64);
      this.Action7.mirror = true;
      this.setRotation(this.Action7, 0.0F, 0.0F, -0.5205006F);
      this.Action8 = new ModelRenderer(this, 0, 0);
      this.Action8.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6);
      this.Action8.setRotationPoint(-3.2F, -13.0F, -26.5F);
      this.Action8.setTextureSize(128, 64);
      this.Action8.mirror = true;
      this.setRotation(this.Action8, 0.0F, 0.0F, -0.8551081F);
      this.Action9 = new ModelRenderer(this, 0, 0);
      this.Action9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 13);
      this.Action9.setRotationPoint(-2.9F, -13.6F, -33.5F);
      this.Action9.setTextureSize(128, 64);
      this.Action9.mirror = true;
      this.setRotation(this.Action9, 0.0F, 0.0F, -0.5576792F);
      this.Action10 = new ModelRenderer(this, 0, 0);
      this.Action10.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action10.setRotationPoint(-7.0F, -12.8F, -33.5F);
      this.Action10.setTextureSize(128, 64);
      this.Action10.mirror = true;
      this.setRotation(this.Action10, 0.0F, 0.0F, 0.0F);
      this.Action11 = new ModelRenderer(this, 0, 0);
      this.Action11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action11.setRotationPoint(-6.0F, -12.8F, -33.5F);
      this.Action11.setTextureSize(128, 64);
      this.Action11.mirror = true;
      this.setRotation(this.Action11, 0.0F, 0.0F, 0.0F);
      this.Action12 = new ModelRenderer(this, 0, 0);
      this.Action12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action12.setRotationPoint(-5.0F, -12.8F, -33.5F);
      this.Action12.setTextureSize(128, 64);
      this.Action12.mirror = true;
      this.setRotation(this.Action12, 0.0F, 0.0F, 0.7435722F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Action1.render(f5);
      this.Action2.render(f5);
      this.Action3.render(f5);
      this.Action4.render(f5);
      this.Action5.render(f5);
      this.Action6.render(f5);
      this.Action7.render(f5);
      this.Action8.render(f5);
      this.Action9.render(f5);
      this.Action10.render(f5);
      this.Action11.render(f5);
      this.Action12.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
