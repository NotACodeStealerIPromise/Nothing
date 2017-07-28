package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class L115Bolt2 extends ModelBase {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;

   public L115Bolt2() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun1 = new ModelRenderer(this, 150, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 1);
      this.gun1.setRotationPoint(1.9F, -16.6F, 6.0F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 1.226894F);
      this.gun2 = new ModelRenderer(this, 150, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1);
      this.gun2.setRotationPoint(-1.0F, -15.5F, 6.0F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.4461411F, 0.2230705F, 1.041002F);
      this.gun3 = new ModelRenderer(this, 150, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.gun3.setRotationPoint(-2.4F, -14.8F, 7.0F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun2.render(f5);
      this.gun3.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
