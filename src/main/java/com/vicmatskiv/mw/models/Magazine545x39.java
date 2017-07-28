package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Magazine545x39 extends ModelBase {
   ModelRenderer Magazine1;
   ModelRenderer Magazine4;
   ModelRenderer Magazine5;
   ModelRenderer Magazine7;

   public Magazine545x39() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.Magazine1 = new ModelRenderer(this, 0, 0);
      this.Magazine1.addBox(0.0F, 0.0F, 0.0F, 4, 11, 7);
      this.Magazine1.setRotationPoint(-3.5F, -8.0F, -25.0F);
      this.Magazine1.setTextureSize(64, 32);
      this.Magazine1.mirror = true;
      this.setRotation(this.Magazine1, -0.1858931F, 0.0F, 0.0F);
      this.Magazine4 = new ModelRenderer(this, 0, 0);
      this.Magazine4.addBox(0.0F, 0.0F, 0.0F, 4, 7, 12);
      this.Magazine4.setRotationPoint(-3.5F, 4.0F, -20.1F);
      this.Magazine4.setTextureSize(64, 32);
      this.Magazine4.mirror = true;
      this.setRotation(this.Magazine4, -2.082002F, 0.0F, 0.0F);
      this.Magazine5 = new ModelRenderer(this, 0, 0);
      this.Magazine5.addBox(0.0F, 0.0F, 0.0F, 3, 11, 2);
      this.Magazine5.setRotationPoint(-3.0F, -8.3F, -26.6F);
      this.Magazine5.setTextureSize(64, 32);
      this.Magazine5.mirror = true;
      this.setRotation(this.Magazine5, -0.1858931F, 0.0F, 0.0F);
      this.Magazine7 = new ModelRenderer(this, 0, 0);
      this.Magazine7.addBox(0.0F, 0.0F, 0.0F, 3, 2, 11);
      this.Magazine7.setRotationPoint(-3.0F, 1.6F, -26.5F);
      this.Magazine7.setTextureSize(64, 32);
      this.Magazine7.mirror = true;
      this.setRotation(this.Magazine7, -2.082002F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Magazine1.render(f5);
      this.Magazine4.render(f5);
      this.Magazine5.render(f5);
      this.Magazine7.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
