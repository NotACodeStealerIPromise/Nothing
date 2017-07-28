package com.vicmatskiv.mw.models;

import com.vicmatskiv.weaponlib.ModelWithAttachments;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class AK12action extends ModelWithAttachments {
   ModelRenderer gun74;
   ModelRenderer gun75;
   ModelRenderer gun76;
   ModelRenderer gun77;
   ModelRenderer gun87;
   ModelRenderer gun90;

   public AK12action() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.gun74 = new ModelRenderer(this, 0, 0);
      this.gun74.addBox(0.0F, 0.0F, 0.0F, 1, 1, 11);
      this.gun74.setRotationPoint(-0.6F, -11.5F, -27.0F);
      this.gun74.setTextureSize(64, 32);
      this.gun74.mirror = true;
      this.setRotation(this.gun74, 0.0F, 0.0F, 0.0F);
      this.gun75 = new ModelRenderer(this, 0, 0);
      this.gun75.addBox(0.0F, 0.0F, 0.0F, 3, 1, 11);
      this.gun75.setRotationPoint(-3.4F, -11.5F, -27.0F);
      this.gun75.setTextureSize(64, 32);
      this.gun75.mirror = true;
      this.setRotation(this.gun75, 0.0F, 0.0F, 0.0F);
      this.gun76 = new ModelRenderer(this, 0, 0);
      this.gun76.addBox(0.0F, 0.0F, 0.0F, 3, 1, 11);
      this.gun76.setRotationPoint(-3.4F, -11.5F, -27.0F);
      this.gun76.setTextureSize(64, 32);
      this.gun76.mirror = true;
      this.setRotation(this.gun76, 0.0F, 0.0F, -1.33843F);
      this.gun77 = new ModelRenderer(this, 0, 0);
      this.gun77.addBox(0.0F, 0.0F, 0.0F, 1, 3, 11);
      this.gun77.setRotationPoint(0.4F, -11.5F, -27.0F);
      this.gun77.setTextureSize(64, 32);
      this.gun77.mirror = true;
      this.setRotation(this.gun77, 0.0F, 0.0F, 2.899932F);
      this.gun87 = new ModelRenderer(this, 0, 0);
      this.gun87.addBox(0.0F, 0.0F, 0.0F, 3, 1, 8);
      this.gun87.setRotationPoint(-3.0F, -13.8F, -35.0F);
      this.gun87.setTextureSize(64, 32);
      this.gun87.mirror = true;
      this.setRotation(this.gun87, 0.0F, 0.0F, 0.0F);
      this.gun90 = new ModelRenderer(this, 0, 0);
      this.gun90.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun90.setRotationPoint(-0.3F, -13.9F, -34.8F);
      this.gun90.setTextureSize(64, 32);
      this.gun90.mirror = true;
      this.setRotation(this.gun90, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun74.render(f5);
      this.gun75.render(f5);
      this.gun76.render(f5);
      this.gun77.render(f5);
      this.gun87.render(f5);
      this.gun90.render(f5);
   }
}
