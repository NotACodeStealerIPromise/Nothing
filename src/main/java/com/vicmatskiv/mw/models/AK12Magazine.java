package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AK12Magazine extends ModelBase {
   ModelRenderer Mag1;
   ModelRenderer Mag2;
   ModelRenderer Mag3;
   ModelRenderer Mag4;
   ModelRenderer Mag5;
   ModelRenderer Mag6;

   public AK12Magazine() {
      this.textureWidth = 64;
      this.textureHeight = 32;
      this.Mag1 = new ModelRenderer(this, 0, 0);
      this.Mag1.addBox(0.0F, 0.0F, 0.0F, 4, 7, 6);
      this.Mag1.setRotationPoint(-3.5F, -8.5F, -19.3F);
      this.Mag1.setTextureSize(64, 32);
      this.Mag1.mirror = true;
      this.setRotation(this.Mag1, -0.1115358F, 0.0F, 0.0F);
      this.Mag2 = new ModelRenderer(this, 0, 0);
      this.Mag2.addBox(0.0F, 0.0F, 0.0F, 4, 6, 6);
      this.Mag2.setRotationPoint(-3.5F, -0.9F, -14.1F);
      this.Mag2.setTextureSize(64, 32);
      this.Mag2.mirror = true;
      this.setRotation(this.Mag2, -1.821752F, 0.0F, 0.0F);
      this.Mag3 = new ModelRenderer(this, 0, 0);
      this.Mag3.addBox(0.0F, 0.0F, 0.0F, 4, 6, 7);
      this.Mag3.setRotationPoint(-3.5F, 4.9F, -15.55F);
      this.Mag3.setTextureSize(64, 32);
      this.Mag3.mirror = true;
      this.setRotation(this.Mag3, -1.970466F, 0.0F, 0.0F);
      this.Mag4 = new ModelRenderer(this, 0, 0);
      this.Mag4.addBox(0.0F, 0.0F, 0.0F, 3, 7, 2);
      this.Mag4.setRotationPoint(-3.0F, -8.65F, -20.6F);
      this.Mag4.setTextureSize(64, 32);
      this.Mag4.mirror = true;
      this.setRotation(this.Mag4, -0.1115358F, 0.0F, 0.0F);
      this.Mag5 = new ModelRenderer(this, 0, 0);
      this.Mag5.addBox(0.0F, 0.0F, 0.0F, 3, 2, 6);
      this.Mag5.setRotationPoint(-3.0F, -2.2F, -19.2F);
      this.Mag5.setTextureSize(64, 32);
      this.Mag5.mirror = true;
      this.setRotation(this.Mag5, -1.821752F, 0.0F, 0.0F);
      this.Mag6 = new ModelRenderer(this, 0, 0);
      this.Mag6.addBox(0.0F, 0.0F, 0.0F, 3, 2, 7);
      this.Mag6.setRotationPoint(-3.0F, 2.9F, -20.4F);
      this.Mag6.setTextureSize(64, 32);
      this.Mag6.mirror = true;
      this.setRotation(this.Mag6, -1.970466F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Mag1.render(f5);
      this.Mag2.render(f5);
      this.Mag3.render(f5);
      this.Mag4.render(f5);
      this.Mag5.render(f5);
      this.Mag6.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
