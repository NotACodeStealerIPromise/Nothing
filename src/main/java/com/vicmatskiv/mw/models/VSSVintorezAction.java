package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class VSSVintorezAction extends ModelBase {
   ModelRenderer Action1;
   ModelRenderer Action2;
   ModelRenderer Action3;
   ModelRenderer Action4;
   ModelRenderer Action5;
   ModelRenderer Action6;
   ModelRenderer Action7;

   public VSSVintorezAction() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.Action1 = new ModelRenderer(this, 0, 0);
      this.Action1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 12);
      this.Action1.setRotationPoint(-3.68F, -11.2F, -26.5F);
      this.Action1.setTextureSize(128, 64);
      this.Action1.mirror = true;
      this.setRotation(this.Action1, 0.0F, 0.0F, -1.375609F);
      this.Action2 = new ModelRenderer(this, 0, 0);
      this.Action2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Action2.setRotationPoint(-4.5F, -12.2F, -26.5F);
      this.Action2.setTextureSize(128, 64);
      this.Action2.mirror = true;
      this.setRotation(this.Action2, 0.0F, 0.0F, 0.0F);
      this.Action3 = new ModelRenderer(this, 0, 0);
      this.Action3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.Action3.setRotationPoint(-6.0F, -12.2F, -26.3F);
      this.Action3.setTextureSize(128, 64);
      this.Action3.mirror = true;
      this.setRotation(this.Action3, 0.0F, 0.0F, 0.0F);
      this.Action4 = new ModelRenderer(this, 0, 0);
      this.Action4.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action4.setRotationPoint(-4.5F, -12.2F, -26.5F);
      this.Action4.setTextureSize(128, 64);
      this.Action4.mirror = true;
      this.setRotation(this.Action4, 0.0F, -1.375609F, 0.0F);
      this.Action5 = new ModelRenderer(this, 0, 0);
      this.Action5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action5.setRotationPoint(-6.0F, -12.2F, -25.3F);
      this.Action5.setTextureSize(128, 64);
      this.Action5.mirror = true;
      this.setRotation(this.Action5, 0.0F, 1.98792F, 0.0F);
      this.Action6 = new ModelRenderer(this, 0, 0);
      this.Action6.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action6.setRotationPoint(-3.8F, -12.2F, -25.9F);
      this.Action6.setTextureSize(128, 64);
      this.Action6.mirror = true;
      this.setRotation(this.Action6, 0.0F, 0.0F, 0.0F);
      this.Action7 = new ModelRenderer(this, 0, 0);
      this.Action7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.Action7.setRotationPoint(-3.8F, -12.2F, -24.9F);
      this.Action7.setTextureSize(128, 64);
      this.Action7.mirror = true;
      this.setRotation(this.Action7, 0.0F, 2.41661F, 0.0F);
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
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
