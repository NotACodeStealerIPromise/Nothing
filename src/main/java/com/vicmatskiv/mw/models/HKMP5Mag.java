package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HKMP5Mag extends ModelBase {
   ModelRenderer Magazine1;
   ModelRenderer Magazine2;
   ModelRenderer Magazine3;
   ModelRenderer Magazine4;
   ModelRenderer Magazine5;
   ModelRenderer Magazine6;
   ModelRenderer Magazine7;

   public HKMP5Mag() {
      this.textureWidth = 128;
      this.textureHeight = 128;
      this.Magazine1 = new ModelRenderer(this, 0, 0);
      this.Magazine1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 12);
      this.Magazine1.setRotationPoint(-3.5F, 1.0F, -24.2F);
      this.Magazine1.setTextureSize(128, 128);
      this.Magazine1.mirror = true;
      this.setRotation(this.Magazine1, 1.375609F, 0.0F, 0.0F);
      this.Magazine2 = new ModelRenderer(this, 0, 0);
      this.Magazine2.addBox(0.0F, 0.0F, 0.0F, 4, 3, 12);
      this.Magazine2.setRotationPoint(-3.5F, 2.0F, -19.3F);
      this.Magazine2.setTextureSize(128, 128);
      this.Magazine2.mirror = true;
      this.setRotation(this.Magazine2, -2.044824F, 0.0F, 0.0F);
      this.Magazine3 = new ModelRenderer(this, 0, 0);
      this.Magazine3.addBox(0.0F, 0.0F, 0.0F, 4, 1, 12);
      this.Magazine3.setRotationPoint(-3.5F, 0.3F, -22.8F);
      this.Magazine3.setTextureSize(128, 128);
      this.Magazine3.mirror = true;
      this.setRotation(this.Magazine3, -2.044824F, 0.0F, 0.0F);
      this.Magazine4 = new ModelRenderer(this, 0, 0);
      this.Magazine4.addBox(0.0F, 0.0F, 0.0F, 4, 3, 12);
      this.Magazine4.setRotationPoint(-3.5F, 1.4F, -22.2F);
      this.Magazine4.setTextureSize(128, 128);
      this.Magazine4.mirror = true;
      this.setRotation(this.Magazine4, 1.375609F, 0.0F, 0.0F);
      this.Magazine5 = new ModelRenderer(this, 0, 0);
      this.Magazine5.addBox(0.0F, 0.0F, 0.0F, 4, 2, 1);
      this.Magazine5.setRotationPoint(-3.5F, 10.75F, -26.4F);
      this.Magazine5.setTextureSize(128, 128);
      this.Magazine5.mirror = true;
      this.setRotation(this.Magazine5, -2.044824F, 0.0F, 0.0F);
      this.Magazine6 = new ModelRenderer(this, 0, 0);
      this.Magazine6.addBox(0.0F, 0.0F, 0.0F, 3, 1, 12);
      this.Magazine6.setRotationPoint(-3.0F, 0.3F, -21.8F);
      this.Magazine6.setTextureSize(128, 128);
      this.Magazine6.mirror = true;
      this.setRotation(this.Magazine6, -2.044824F, 0.0F, 0.0F);
      this.Magazine7 = new ModelRenderer(this, 0, 0);
      this.Magazine7.addBox(0.0F, 0.0F, 0.0F, 3, 2, 12);
      this.Magazine7.setRotationPoint(-3.0F, 1.2F, -23.5F);
      this.Magazine7.setTextureSize(128, 128);
      this.Magazine7.mirror = true;
      this.setRotation(this.Magazine7, 1.375609F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Magazine1.render(f5);
      this.Magazine2.render(f5);
      this.Magazine3.render(f5);
      this.Magazine4.render(f5);
      this.Magazine5.render(f5);
      this.Magazine6.render(f5);
      this.Magazine7.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
