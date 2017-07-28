package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AN94action extends ModelBase {
   ModelRenderer action1;
   ModelRenderer action2;
   ModelRenderer action3;
   ModelRenderer action4;
   ModelRenderer action5;
   ModelRenderer action6;

   public AN94action() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.action1 = new ModelRenderer(this, 0, 0);
      this.action1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 16);
      this.action1.setRotationPoint(-3.6F, -13.1F, -33.5F);
      this.action1.setTextureSize(128, 64);
      this.action1.mirror = true;
      this.setRotation(this.action1, 0.0F, 0.0F, -0.7807508F);
      this.action2 = new ModelRenderer(this, 0, 0);
      this.action2.addBox(0.0F, 0.0F, 0.0F, 1, 1, 16);
      this.action2.setRotationPoint(-3.6F, -13.1F, -33.5F);
      this.action2.setTextureSize(128, 64);
      this.action2.mirror = true;
      this.setRotation(this.action2, 0.0F, 0.0F, 0.0F);
      this.action3 = new ModelRenderer(this, 0, 0);
      this.action3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1);
      this.action3.setRotationPoint(-6.0F, -13.3F, -33.5F);
      this.action3.setTextureSize(128, 64);
      this.action3.mirror = true;
      this.setRotation(this.action3, 0.0F, 0.0F, 0.0F);
      this.action4 = new ModelRenderer(this, 0, 0);
      this.action4.addBox(0.0F, 0.0F, 0.0F, 4, 1, 1);
      this.action4.setRotationPoint(-6.0F, -13.7F, -33.5F);
      this.action4.setTextureSize(128, 64);
      this.action4.mirror = true;
      this.setRotation(this.action4, 0.0F, 0.0F, 0.0F);
      this.action5 = new ModelRenderer(this, 0, 0);
      this.action5.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.action5.setRotationPoint(-6.5F, -13.7F, -33.2F);
      this.action5.setTextureSize(128, 64);
      this.action5.mirror = true;
      this.setRotation(this.action5, 0.0F, 0.0F, 0.0F);
      this.action6 = new ModelRenderer(this, 0, 0);
      this.action6.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.action6.setRotationPoint(-6.5F, -13.3F, -33.2F);
      this.action6.setTextureSize(128, 64);
      this.action6.mirror = true;
      this.setRotation(this.action6, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.action1.render(f5);
      this.action2.render(f5);
      this.action3.render(f5);
      this.action4.render(f5);
      this.action5.render(f5);
      this.action6.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
