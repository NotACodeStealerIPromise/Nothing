package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class Pin extends ModelBase {
   ModelRenderer Pin1;
   ModelRenderer Pin2;
   ModelRenderer Pin3;
   ModelRenderer Pin4;
   ModelRenderer Pin5;
   ModelRenderer Pin6;
   ModelRenderer Pin7;
   ModelRenderer Pin8;
   ModelRenderer Pin9;
   ModelRenderer Pin10;
   ModelRenderer Pin11;
   ModelRenderer Pin12;
   ModelRenderer Pin13;

   public Pin() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.Pin1 = new ModelRenderer(this, 0, 0);
      this.Pin1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.Pin1.setRotationPoint(-2.5F, -20.0F, 5.8F);
      this.Pin1.setTextureSize(64, 32);
      this.Pin1.mirror = true;
      this.setRotation(this.Pin1, 0.0F, 0.0F, 0.0F);
      this.Pin2 = new ModelRenderer(this, 0, 0);
      this.Pin2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin2.setRotationPoint(1.2F, -20.3F, 5.3F);
      this.Pin2.setTextureSize(64, 32);
      this.Pin2.mirror = true;
      this.setRotation(this.Pin2, 0.0F, 0.0F, 0.0F);
      this.Pin3 = new ModelRenderer(this, 0, 0);
      this.Pin3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin3.setRotationPoint(1.2F, -17.8F, 8.8F);
      this.Pin3.setTextureSize(64, 32);
      this.Pin3.mirror = true;
      this.setRotation(this.Pin3, 0.0F, 0.0F, 0.0F);
      this.Pin4 = new ModelRenderer(this, 0, 0);
      this.Pin4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin4.setRotationPoint(1.2F, -17.8F, 2.8F);
      this.Pin4.setTextureSize(64, 32);
      this.Pin4.mirror = true;
      this.setRotation(this.Pin4, 0.0F, 0.0F, 0.0F);
      this.Pin5 = new ModelRenderer(this, 0, 0);
      this.Pin5.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin5.setRotationPoint(1.2F, -14.3F, 5.3F);
      this.Pin5.setTextureSize(64, 32);
      this.Pin5.mirror = true;
      this.setRotation(this.Pin5, 0.0F, 0.0F, 0.0F);
      this.Pin6 = new ModelRenderer(this, 0, 0);
      this.Pin6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin6.setRotationPoint(1.2F, -17.8F, 9.8F);
      this.Pin6.setTextureSize(64, 32);
      this.Pin6.mirror = true;
      this.setRotation(this.Pin6, -2.528146F, 0.0F, 0.0F);
      this.Pin7 = new ModelRenderer(this, 0, 0);
      this.Pin7.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin7.setRotationPoint(1.2F, -20.3F, 7.3F);
      this.Pin7.setTextureSize(64, 32);
      this.Pin7.mirror = true;
      this.setRotation(this.Pin7, -0.6320364F, 0.0F, 0.0F);
      this.Pin8 = new ModelRenderer(this, 0, 0);
      this.Pin8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin8.setRotationPoint(1.2F, -20.3F, 5.3F);
      this.Pin8.setTextureSize(64, 32);
      this.Pin8.mirror = true;
      this.setRotation(this.Pin8, -1.021276F, 0.0F, 0.0F);
      this.Pin9 = new ModelRenderer(this, 0, 0);
      this.Pin9.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin9.setRotationPoint(1.2F, -17.8F, 2.8F);
      this.Pin9.setTextureSize(64, 32);
      this.Pin9.mirror = true;
      this.setRotation(this.Pin9, 1.041001F, 0.0F, 0.0F);
      this.Pin10 = new ModelRenderer(this, 0, 0);
      this.Pin10.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin10.setRotationPoint(1.2F, -15.8F, 2.8F);
      this.Pin10.setTextureSize(64, 32);
      this.Pin10.mirror = true;
      this.setRotation(this.Pin10, 0.5205006F, 0.0F, 0.0F);
      this.Pin11 = new ModelRenderer(this, 0, 0);
      this.Pin11.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin11.setRotationPoint(1.2F, -13.3F, 5.3F);
      this.Pin11.setTextureSize(64, 32);
      this.Pin11.mirror = true;
      this.setRotation(this.Pin11, 2.639681F, 0.0F, 0.0F);
      this.Pin12 = new ModelRenderer(this, 0, 0);
      this.Pin12.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.Pin12.setRotationPoint(1.2F, -13.3F, 7.3F);
      this.Pin12.setTextureSize(64, 32);
      this.Pin12.mirror = true;
      this.setRotation(this.Pin12, 2.082002F, 0.0F, 0.0F);
      this.Pin13 = new ModelRenderer(this, 0, 0);
      this.Pin13.addBox(0.0F, 0.0F, 0.0F, 1, 1, 2);
      this.Pin13.setRotationPoint(1.2F, -15.8F, 9.8F);
      this.Pin13.setTextureSize(64, 32);
      this.Pin13.mirror = true;
      this.setRotation(this.Pin13, -2.082002F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Pin1.render(f5);
      this.Pin2.render(f5);
      this.Pin3.render(f5);
      this.Pin4.render(f5);
      this.Pin5.render(f5);
      this.Pin6.render(f5);
      this.Pin7.render(f5);
      this.Pin8.render(f5);
      this.Pin9.render(f5);
      this.Pin10.render(f5);
      this.Pin11.render(f5);
      this.Pin12.render(f5);
      this.Pin13.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
