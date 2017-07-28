package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class G36Action extends ModelBase {
   ModelRenderer gun93;
   ModelRenderer gun376;
   ModelRenderer gun377;
   ModelRenderer gun378;
   ModelRenderer gun379;
   ModelRenderer gun380;
   ModelRenderer gun381;

   public G36Action() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.gun93 = new ModelRenderer(this, 0, 0);
      this.gun93.addBox(0.0F, 0.0F, 0.0F, 1, 2, 13);
      this.gun93.setRotationPoint(-3.3F, -15.7F, -28.0F);
      this.gun93.setTextureSize(64, 32);
      this.gun93.mirror = true;
      this.setRotation(this.gun93, 0.0F, 0.0F, 0.0F);
      this.gun376 = new ModelRenderer(this, 0, 0);
      this.gun376.addBox(0.0F, 0.0F, 0.0F, 2, 2, 12);
      this.gun376.setRotationPoint(-2.5F, -20.0F, -27.0F);
      this.gun376.setTextureSize(64, 32);
      this.gun376.mirror = true;
      this.setRotation(this.gun376, 0.0F, 0.0F, 0.0F);
      this.gun377 = new ModelRenderer(this, 0, 0);
      this.gun377.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5);
      this.gun377.setRotationPoint(-2.5F, -19.7F, -32.0F);
      this.gun377.setTextureSize(64, 32);
      this.gun377.mirror = true;
      this.setRotation(this.gun377, 0.0F, 0.0F, 0.0F);
      this.gun378 = new ModelRenderer(this, 0, 0);
      this.gun378.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun378.setRotationPoint(-2.5F, -20.0F, -27.0F);
      this.gun378.setTextureSize(64, 32);
      this.gun378.mirror = true;
      this.setRotation(this.gun378, -0.8551081F, 0.0F, 0.0F);
      this.gun379 = new ModelRenderer(this, 0, 0);
      this.gun379.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
      this.gun379.setRotationPoint(-2.3F, -19.8F, -38.8F);
      this.gun379.setTextureSize(64, 32);
      this.gun379.mirror = true;
      this.setRotation(this.gun379, -0.0261799F, 0.0F, 0.0F);
      this.gun380 = new ModelRenderer(this, 0, 0);
      this.gun380.addBox(0.0F, 0.0F, 0.0F, 1, 1, 7);
      this.gun380.setRotationPoint(-1.7F, -19.8F, -38.8F);
      this.gun380.setTextureSize(64, 32);
      this.gun380.mirror = true;
      this.setRotation(this.gun380, -0.0261799F, 0.0F, 0.0F);
      this.gun381 = new ModelRenderer(this, 0, 0);
      this.gun381.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.gun381.setRotationPoint(-2.5F, -19.1F, -34.0F);
      this.gun381.setTextureSize(64, 32);
      this.gun381.mirror = true;
      this.setRotation(this.gun381, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun93.render(f5);
      this.gun376.render(f5);
      this.gun377.render(f5);
      this.gun378.render(f5);
      this.gun379.render(f5);
      this.gun380.render(f5);
      this.gun381.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
