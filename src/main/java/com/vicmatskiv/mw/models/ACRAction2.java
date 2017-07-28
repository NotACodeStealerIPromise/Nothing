package com.vicmatskiv.mw.models;

import com.vicmatskiv.weaponlib.ModelWithAttachments;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ACRAction2 extends ModelWithAttachments {
   ModelRenderer ACR96;
   ModelRenderer ACR97;
   ModelRenderer ACR139;
   ModelRenderer ACR140;

   public ACRAction2() {
      this.textureWidth = 512;
      this.textureHeight = 256;
      this.ACR96 = new ModelRenderer(this, 100, 0);
      this.ACR96.addBox(0.0F, 0.0F, 0.0F, 1, 2, 10);
      this.ACR96.setRotationPoint(-3.6F, -14.5F, -22.0F);
      this.ACR96.setTextureSize(64, 32);
      this.ACR96.mirror = true;
      this.setRotation(this.ACR96, 0.0F, 0.0F, 0.0F);
      this.ACR97 = new ModelRenderer(this, 100, 0);
      this.ACR97.addBox(0.0F, 0.0F, 0.0F, 1, 2, 10);
      this.ACR97.setRotationPoint(-3.5F, -15.5F, -22.0F);
      this.ACR97.setTextureSize(64, 32);
      this.ACR97.mirror = true;
      this.setRotation(this.ACR97, 0.0F, 0.0F, 0.0F);
      this.ACR139 = new ModelRenderer(this, 100, 0);
      this.ACR139.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14);
      this.ACR139.setRotationPoint(-3.1F, -16.7F, -36.0F);
      this.ACR139.setTextureSize(64, 32);
      this.ACR139.mirror = true;
      this.setRotation(this.ACR139, 0.0F, 0.0F, -0.9294653F);
      this.ACR140 = new ModelRenderer(this, 100, 0);
      this.ACR140.addBox(0.0F, 0.0F, 0.0F, 1, 1, 14);
      this.ACR140.setRotationPoint(0.2F, -16.6F, -36.0F);
      this.ACR140.setTextureSize(64, 32);
      this.ACR140.mirror = true;
      this.setRotation(this.ACR140, 0.0F, 0.0F, 2.513274F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.ACR96.render(f5);
      this.ACR97.render(f5);
      this.ACR139.render(f5);
      this.ACR140.render(f5);
   }
}
