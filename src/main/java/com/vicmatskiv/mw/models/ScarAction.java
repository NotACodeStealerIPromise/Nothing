package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ScarAction extends ModelBase {
   ModelRenderer gun149;
   ModelRenderer gun150;
   ModelRenderer gun151;
   ModelRenderer gun152;
   ModelRenderer gun116;

   public ScarAction() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.gun149 = new ModelRenderer(this, 100, 50);
      this.gun149.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1);
      this.gun149.setRotationPoint(-5.0F, -19.1F, -32.0F);
      this.gun149.setTextureSize(64, 32);
      this.gun149.mirror = true;
      this.setRotation(this.gun149, 0.0F, 0.0F, 0.0F);
      this.gun150 = new ModelRenderer(this, 100, 50);
      this.gun150.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1);
      this.gun150.setRotationPoint(-5.0F, -18.9F, -32.0F);
      this.gun150.setTextureSize(64, 32);
      this.gun150.mirror = true;
      this.setRotation(this.gun150, 0.0F, 0.0F, 0.0F);
      this.gun151 = new ModelRenderer(this, 100, 50);
      this.gun151.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1);
      this.gun151.setRotationPoint(-5.0F, -19.0F, -32.1F);
      this.gun151.setTextureSize(64, 32);
      this.gun151.mirror = true;
      this.setRotation(this.gun151, 0.0F, 0.0F, 0.0F);
      this.gun152 = new ModelRenderer(this, 100, 50);
      this.gun152.addBox(0.0F, 0.0F, 0.0F, 7, 1, 1);
      this.gun152.setRotationPoint(-5.0F, -19.0F, -31.9F);
      this.gun152.setTextureSize(64, 32);
      this.gun152.mirror = true;
      this.setRotation(this.gun152, 0.0F, 0.0F, 0.0F);
      this.gun116 = new ModelRenderer(this, 100, 50);
      this.gun116.addBox(0.0F, 0.0F, 0.0F, 3, 1, 18);
      this.gun116.setRotationPoint(-3.0F, -19.0F, -32.0F);
      this.gun116.setTextureSize(64, 32);
      this.gun116.mirror = true;
      this.setRotation(this.gun116, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun149.render(f5);
      this.gun150.render(f5);
      this.gun151.render(f5);
      this.gun152.render(f5);
      this.gun116.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
