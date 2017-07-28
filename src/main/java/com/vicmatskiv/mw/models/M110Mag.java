package com.vicmatskiv.mw.models;

import com.vicmatskiv.weaponlib.ModelWithAttachments;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class M110Mag extends ModelWithAttachments {
   ModelRenderer gun1;
   ModelRenderer gun2;
   ModelRenderer gun3;
   ModelRenderer gun4;
   ModelRenderer gun5;
   ModelRenderer gun6;
   ModelRenderer gun7;
   ModelRenderer gun8;
   ModelRenderer gun9;
   ModelRenderer gun10;

   public M110Mag() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun1 = new ModelRenderer(this, 200, 0);
      this.gun1.addBox(0.0F, 0.0F, 0.0F, 3, 14, 7);
      this.gun1.setRotationPoint(1.0F, -12.0F, -4.5F);
      this.gun1.setTextureSize(64, 32);
      this.gun1.mirror = true;
      this.setRotation(this.gun1, 0.0F, 0.0F, 0.0F);
      this.gun2 = new ModelRenderer(this, 200, 0);
      this.gun2.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun2.setRotationPoint(0.8F, -12.0F, -0.5F);
      this.gun2.setTextureSize(64, 32);
      this.gun2.mirror = true;
      this.setRotation(this.gun2, 0.0F, 0.0F, 0.0F);
      this.gun3 = new ModelRenderer(this, 200, 0);
      this.gun3.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun3.setRotationPoint(3.2F, -12.0F, -0.5F);
      this.gun3.setTextureSize(64, 32);
      this.gun3.mirror = true;
      this.setRotation(this.gun3, 0.0F, 0.0F, 0.0F);
      this.gun4 = new ModelRenderer(this, 200, 0);
      this.gun4.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun4.setRotationPoint(3.2F, -12.0F, -3.0F);
      this.gun4.setTextureSize(64, 32);
      this.gun4.mirror = true;
      this.setRotation(this.gun4, 0.0F, 0.0F, 0.0F);
      this.gun5 = new ModelRenderer(this, 200, 0);
      this.gun5.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1);
      this.gun5.setRotationPoint(3.2F, -12.0F, -4.5F);
      this.gun5.setTextureSize(64, 32);
      this.gun5.mirror = true;
      this.setRotation(this.gun5, 0.0F, 0.0F, 0.0F);
      this.gun6 = new ModelRenderer(this, 200, 0);
      this.gun6.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1);
      this.gun6.setRotationPoint(0.8F, -12.0F, -4.5F);
      this.gun6.setTextureSize(64, 32);
      this.gun6.mirror = true;
      this.setRotation(this.gun6, 0.0F, 0.0F, 0.0F);
      this.gun7 = new ModelRenderer(this, 200, 0);
      this.gun7.addBox(0.0F, 0.0F, 0.0F, 1, 14, 2);
      this.gun7.setRotationPoint(0.8F, -12.0F, -3.0F);
      this.gun7.setTextureSize(64, 32);
      this.gun7.mirror = true;
      this.setRotation(this.gun7, 0.0F, 0.0F, 0.0F);
      this.gun8 = new ModelRenderer(this, 200, 0);
      this.gun8.addBox(0.0F, 0.0F, 0.0F, 3, 14, 1);
      this.gun8.setRotationPoint(0.8F, -12.0F, 2.0F);
      this.gun8.setTextureSize(64, 32);
      this.gun8.mirror = true;
      this.setRotation(this.gun8, 0.0F, 0.0F, 0.0F);
      this.gun9 = new ModelRenderer(this, 200, 0);
      this.gun9.addBox(0.0F, 0.0F, 0.0F, 1, 14, 1);
      this.gun9.setRotationPoint(3.2F, -12.0F, 2.0F);
      this.gun9.setTextureSize(64, 32);
      this.gun9.mirror = true;
      this.setRotation(this.gun9, 0.0F, 0.0F, 0.0F);
      this.gun10 = new ModelRenderer(this, 200, 0);
      this.gun10.addBox(0.0F, 0.0F, 0.0F, 3, 7, 1);
      this.gun10.setRotationPoint(1.0F, 2.0F, -4.5F);
      this.gun10.setTextureSize(64, 32);
      this.gun10.mirror = true;
      this.setRotation(this.gun10, 1.449966F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun1.render(f5);
      this.gun2.render(f5);
      this.gun3.render(f5);
      this.gun4.render(f5);
      this.gun5.render(f5);
      this.gun6.render(f5);
      this.gun7.render(f5);
      this.gun8.render(f5);
      this.gun9.render(f5);
      this.gun10.render(f5);
   }
}
