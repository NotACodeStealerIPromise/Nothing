package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FNFALMag extends ModelBase {
   ModelRenderer gun16;
   ModelRenderer gun17;
   ModelRenderer gun18;
   ModelRenderer gun19;
   ModelRenderer gun20;
   ModelRenderer gun21;
   ModelRenderer gun22;
   ModelRenderer gun23;

   public FNFALMag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun16 = new ModelRenderer(this, 0, 0);
      this.gun16.addBox(0.0F, 0.0F, 0.0F, 3, 14, 7);
      this.gun16.setRotationPoint(1.0F, -13.0F, -3.8F);
      this.gun16.setTextureSize(64, 32);
      this.gun16.mirror = true;
      this.setRotation(this.gun16, 0.0F, 0.0F, 0.0F);
      this.gun17 = new ModelRenderer(this, 0, 0);
      this.gun17.addBox(0.0F, 0.0F, 0.0F, 3, 6, 1);
      this.gun17.setRotationPoint(1.0F, 1.0F, -2.8F);
      this.gun17.setTextureSize(64, 32);
      this.gun17.mirror = true;
      this.setRotation(this.gun17, 1.449966F, 0.0F, 0.0F);
      this.gun18 = new ModelRenderer(this, 0, 0);
      this.gun18.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun18.setRotationPoint(0.9F, -13.0F, 1.2F);
      this.gun18.setTextureSize(64, 32);
      this.gun18.mirror = true;
      this.setRotation(this.gun18, 0.0F, 0.0F, 0.0F);
      this.gun19 = new ModelRenderer(this, 0, 0);
      this.gun19.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun19.setRotationPoint(0.9F, -13.0F, -1.0F);
      this.gun19.setTextureSize(64, 32);
      this.gun19.mirror = true;
      this.setRotation(this.gun19, 0.0F, 0.0F, 0.0F);
      this.gun20 = new ModelRenderer(this, 0, 0);
      this.gun20.addBox(0.0F, 0.0F, 0.0F, 3, 14, 2);
      this.gun20.setRotationPoint(0.9F, -13.0F, -3.3F);
      this.gun20.setTextureSize(64, 32);
      this.gun20.mirror = true;
      this.setRotation(this.gun20, 0.0F, 0.0F, 0.0F);
      this.gun21 = new ModelRenderer(this, 0, 0);
      this.gun21.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun21.setRotationPoint(3.1F, -13.0F, -3.3F);
      this.gun21.setTextureSize(64, 32);
      this.gun21.mirror = true;
      this.setRotation(this.gun21, 0.0F, 0.0F, 0.0F);
      this.gun22 = new ModelRenderer(this, 0, 0);
      this.gun22.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun22.setRotationPoint(3.1F, -13.0F, -1.0F);
      this.gun22.setTextureSize(64, 32);
      this.gun22.mirror = true;
      this.setRotation(this.gun22, 0.0F, 0.0F, 0.0F);
      this.gun23 = new ModelRenderer(this, 0, 0);
      this.gun23.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun23.setRotationPoint(3.1F, -13.0F, 1.2F);
      this.gun23.setTextureSize(64, 32);
      this.gun23.mirror = true;
      this.setRotation(this.gun23, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun16.render(f5);
      this.gun17.render(f5);
      this.gun18.render(f5);
      this.gun19.render(f5);
      this.gun20.render(f5);
      this.gun21.render(f5);
      this.gun22.render(f5);
      this.gun23.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
