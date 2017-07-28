package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AKiron3 extends ModelBase {
   ModelRenderer Iron1;
   ModelRenderer Iron2;
   ModelRenderer Iron3;
   ModelRenderer Iron4;
   ModelRenderer Iron5;
   ModelRenderer Iron6;
   ModelRenderer Iron7;

   public AKiron3() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Iron1 = new ModelRenderer(this, 0, 0);
      this.Iron1.addBox(0.0F, 0.0F, 0.0F, 2, 8, 1);
      this.Iron1.setRotationPoint(0.0F, -5.0F, 0.0F);
      this.Iron1.setTextureSize(64, 32);
      this.Iron1.mirror = true;
      this.setRotation(this.Iron1, 0.0F, 0.0F, 0.0F);
      this.Iron2 = new ModelRenderer(this, 0, 0);
      this.Iron2.addBox(0.0F, 0.0F, 0.0F, 2, 4, 2);
      this.Iron2.setRotationPoint(0.0F, -5.0F, 1.0F);
      this.Iron2.setTextureSize(64, 32);
      this.Iron2.mirror = true;
      this.setRotation(this.Iron2, 0.0F, 0.0F, 0.0F);
      this.Iron3 = new ModelRenderer(this, 0, 0);
      this.Iron3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 8);
      this.Iron3.setRotationPoint(0.0F, -5.0F, 3.0F);
      this.Iron3.setTextureSize(64, 32);
      this.Iron3.mirror = true;
      this.setRotation(this.Iron3, -1.189716F, 0.0F, 0.0F);
      this.Iron4 = new ModelRenderer(this, 0, 0);
      this.Iron4.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Iron4.setRotationPoint(0.0F, -3.0F, 2.8F);
      this.Iron4.setTextureSize(64, 32);
      this.Iron4.mirror = true;
      this.setRotation(this.Iron4, 0.0F, 0.0F, 0.0F);
      this.Iron5 = new ModelRenderer(this, 0, 0);
      this.Iron5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 5);
      this.Iron5.setRotationPoint(0.0F, 2.0F, 1.0F);
      this.Iron5.setTextureSize(64, 32);
      this.Iron5.mirror = true;
      this.setRotation(this.Iron5, 0.0F, 0.0F, 0.0F);
      this.Iron6 = new ModelRenderer(this, 0, 0);
      this.Iron6.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Iron6.setRotationPoint(0.0F, 0.0F, 4.0F);
      this.Iron6.setTextureSize(64, 32);
      this.Iron6.mirror = true;
      this.setRotation(this.Iron6, 0.0F, 0.0F, 0.0F);
      this.Iron7 = new ModelRenderer(this, 0, 0);
      this.Iron7.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3);
      this.Iron7.setRotationPoint(0.0F, -1.5F, 1.0F);
      this.Iron7.setTextureSize(64, 32);
      this.Iron7.mirror = true;
      this.setRotation(this.Iron7, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Iron1.render(f5);
      this.Iron2.render(f5);
      this.Iron3.render(f5);
      this.Iron4.render(f5);
      this.Iron5.render(f5);
      this.Iron6.render(f5);
      this.Iron7.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
