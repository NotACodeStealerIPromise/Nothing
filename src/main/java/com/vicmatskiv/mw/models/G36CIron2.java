package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class G36CIron2 extends ModelBase {
   ModelRenderer sight1;
   ModelRenderer sight2;
   ModelRenderer sight3;
   ModelRenderer sight4;
   ModelRenderer sight5;
   ModelRenderer sight6;
   ModelRenderer sight7;

   public G36CIron2() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.sight1 = new ModelRenderer(this, 0, 0);
      this.sight1.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3);
      this.sight1.setRotationPoint(0.0F, 0.0F, 0.0F);
      this.sight1.setTextureSize(128, 64);
      this.sight1.mirror = true;
      this.setRotation(this.sight1, 0.0F, 0.0F, 0.0F);
      this.sight2 = new ModelRenderer(this, 0, 0);
      this.sight2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.sight2.setRotationPoint(0.0F, 1.0F, 0.0F);
      this.sight2.setTextureSize(128, 64);
      this.sight2.mirror = true;
      this.setRotation(this.sight2, 0.0F, 0.0F, -2.193538F);
      this.sight3 = new ModelRenderer(this, 0, 0);
      this.sight3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3);
      this.sight3.setRotationPoint(4.0F, 1.0F, 0.0F);
      this.sight3.setTextureSize(128, 64);
      this.sight3.mirror = true;
      this.setRotation(this.sight3, 0.0F, 0.0F, -2.565324F);
      this.sight4 = new ModelRenderer(this, 0, 0);
      this.sight4.addBox(0.0F, 0.0F, 0.0F, 3, 1, 3);
      this.sight4.setRotationPoint(-1.7F, -1.4F, 0.0F);
      this.sight4.setTextureSize(128, 64);
      this.sight4.mirror = true;
      this.setRotation(this.sight4, 0.0F, 0.0F, -1.07818F);
      this.sight5 = new ModelRenderer(this, 0, 0);
      this.sight5.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3);
      this.sight5.setRotationPoint(5.6F, -1.5F, 0.0F);
      this.sight5.setTextureSize(128, 64);
      this.sight5.mirror = true;
      this.setRotation(this.sight5, 0.0F, 0.0F, 2.639681F);
      this.sight6 = new ModelRenderer(this, 0, 0);
      this.sight6.addBox(0.0F, 0.0F, 0.0F, 4, 1, 3);
      this.sight6.setRotationPoint(0.0F, -4.0F, 0.0F);
      this.sight6.setTextureSize(128, 64);
      this.sight6.mirror = true;
      this.setRotation(this.sight6, 0.0F, 0.0F, 0.0F);
      this.sight7 = new ModelRenderer(this, 0, 0);
      this.sight7.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2);
      this.sight7.setRotationPoint(1.5F, -1.5F, 0.0F);
      this.sight7.setTextureSize(128, 64);
      this.sight7.mirror = true;
      this.setRotation(this.sight7, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.sight1.render(f5);
      this.sight2.render(f5);
      this.sight3.render(f5);
      this.sight4.render(f5);
      this.sight5.render(f5);
      this.sight6.render(f5);
      this.sight7.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
