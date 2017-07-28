package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M14Action extends ModelBase {
   ModelRenderer Action2;
   ModelRenderer Action3;
   ModelRenderer Action6;
   ModelRenderer Action7;
   ModelRenderer Action12;
   ModelRenderer Action13;
   ModelRenderer Action14;
   ModelRenderer Action15;
   ModelRenderer Action16;
   ModelRenderer Action17;

   public M14Action() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Action2 = new ModelRenderer(this, 0, 0);
      this.Action2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.Action2.setRotationPoint(-4.2F, -14.6F, -37.0F);
      this.Action2.setTextureSize(64, 32);
      this.Action2.mirror = true;
      this.setRotation(this.Action2, 0.0F, 0.0F, 0.0F);
      this.Action3 = new ModelRenderer(this, 0, 0);
      this.Action3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.Action3.setRotationPoint(-4.2F, -15.6F, -39.0F);
      this.Action3.setTextureSize(64, 32);
      this.Action3.mirror = true;
      this.setRotation(this.Action3, 0.0F, 0.0F, 0.0F);
      this.Action6 = new ModelRenderer(this, 0, 0);
      this.Action6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 18);
      this.Action6.setRotationPoint(-3.3F, -14.9F, -57.0F);
      this.Action6.setTextureSize(64, 32);
      this.Action6.mirror = true;
      this.setRotation(this.Action6, 0.0F, 0.0F, 0.0F);
      this.Action7 = new ModelRenderer(this, 0, 0);
      this.Action7.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1);
      this.Action7.setRotationPoint(-3.3F, -14.9F, -57.0F);
      this.Action7.setTextureSize(64, 32);
      this.Action7.mirror = true;
      this.setRotation(this.Action7, 0.0F, 1.487144F, 0.0F);
      this.Action12 = new ModelRenderer(this, 0, 0);
      this.Action12.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Action12.setRotationPoint(-4.2F, -15.6F, -37.0F);
      this.Action12.setTextureSize(64, 32);
      this.Action12.mirror = true;
      this.setRotation(this.Action12, -0.6320364F, 0.0F, 0.0F);
      this.Action13 = new ModelRenderer(this, 0, 0);
      this.Action13.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.Action13.setRotationPoint(-4.2F, -14.6F, -34.0F);
      this.Action13.setTextureSize(64, 32);
      this.Action13.mirror = true;
      this.setRotation(this.Action13, -1.710216F, 0.0F, 0.0F);
      this.Action14 = new ModelRenderer(this, 0, 0);
      this.Action14.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Action14.setRotationPoint(-6.0F, -14.6F, -34.0F);
      this.Action14.setTextureSize(64, 32);
      this.Action14.mirror = true;
      this.setRotation(this.Action14, 0.0F, 0.0F, 0.0F);
      this.Action15 = new ModelRenderer(this, 0, 0);
      this.Action15.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action15.setRotationPoint(-6.0F, -14.2F, -34.0F);
      this.Action15.setTextureSize(64, 32);
      this.Action15.mirror = true;
      this.setRotation(this.Action15, 0.0F, 0.0F, 0.0F);
      this.Action16 = new ModelRenderer(this, 0, 0);
      this.Action16.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action16.setRotationPoint(-5.7F, -14.2F, -34.0F);
      this.Action16.setTextureSize(64, 32);
      this.Action16.mirror = true;
      this.setRotation(this.Action16, 0.0F, 0.0F, 0.0F);
      this.Action17 = new ModelRenderer(this, 0, 0);
      this.Action17.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Action17.setRotationPoint(-4.7F, -13.2F, -34.0F);
      this.Action17.setTextureSize(64, 32);
      this.Action17.mirror = true;
      this.setRotation(this.Action17, 0.0F, 0.0F, -1.821752F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Action2.render(f5);
      this.Action3.render(f5);
      this.Action6.render(f5);
      this.Action7.render(f5);
      this.Action12.render(f5);
      this.Action13.render(f5);
      this.Action14.render(f5);
      this.Action15.render(f5);
      this.Action16.render(f5);
      this.Action17.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
