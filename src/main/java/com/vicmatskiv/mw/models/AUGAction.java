package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AUGAction extends ModelBase {
   ModelRenderer AUG103;
   ModelRenderer AUG137;
   ModelRenderer AUG138;
   ModelRenderer AUG139;

   public AUGAction() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.AUG103 = new ModelRenderer(this, 0, 100);
      this.AUG103.addBox(0.0F, 0.0F, 0.0F, 3, 1, 9);
      this.AUG103.setRotationPoint(-4.5F, -9.5F, 10.3F);
      this.AUG103.setTextureSize(64, 32);
      this.AUG103.mirror = true;
      this.setRotation(this.AUG103, 0.0F, 0.0F, -1.021018F);
      this.AUG137 = new ModelRenderer(this, 0, 0);
      this.AUG137.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.AUG137.setRotationPoint(1.1F, -9.3F, -20.5F);
      this.AUG137.setTextureSize(64, 32);
      this.AUG137.mirror = true;
      this.setRotation(this.AUG137, 0.0F, 0.0F, -0.8870379F);
      this.AUG138 = new ModelRenderer(this, 0, 0);
      this.AUG138.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.AUG138.setRotationPoint(1.55F, -9.83F, -19.5F);
      this.AUG138.setTextureSize(64, 32);
      this.AUG138.mirror = true;
      this.setRotation(this.AUG138, 0.0F, 0.0F, -0.8870379F);
      this.AUG139 = new ModelRenderer(this, 0, 0);
      this.AUG139.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
      this.AUG139.setRotationPoint(1.27F, -9.5F, -20.0F);
      this.AUG139.setTextureSize(64, 32);
      this.AUG139.mirror = true;
      this.setRotation(this.AUG139, 0.0F, 0.0F, -0.8870379F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.AUG103.render(f5);
      this.AUG137.render(f5);
      this.AUG138.render(f5);
      this.AUG139.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
