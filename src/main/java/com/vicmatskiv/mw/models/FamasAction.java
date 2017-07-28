package com.vicmatskiv.mw.models;

import com.vicmatskiv.weaponlib.ModelWithAttachments;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class FamasAction extends ModelWithAttachments {
   ModelRenderer Famas280;
   ModelRenderer Famas281;
   ModelRenderer Famas282;
   ModelRenderer Famas283;
   ModelRenderer Famas284;
   ModelRenderer Famas285;
   ModelRenderer Famas286;
   ModelRenderer Famas287;

   public FamasAction() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.Famas280 = new ModelRenderer(this, 0, 0);
      this.Famas280.addBox(0.0F, 0.0F, 0.0F, 1, 1, 6);
      this.Famas280.setRotationPoint(-2.0F, -15.7F, -19.0F);
      this.Famas280.setTextureSize(64, 32);
      this.Famas280.mirror = true;
      this.setRotation(this.Famas280, 0.0F, 0.0F, 0.0F);
      this.Famas281 = new ModelRenderer(this, 0, 0);
      this.Famas281.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.Famas281.setRotationPoint(-2.5F, -17.6F, -19.0F);
      this.Famas281.setTextureSize(64, 32);
      this.Famas281.mirror = true;
      this.setRotation(this.Famas281, -0.2230717F, 0.0F, 0.0F);
      this.Famas282 = new ModelRenderer(this, 0, 0);
      this.Famas282.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Famas282.setRotationPoint(-2.5F, -17.6F, -19.0F);
      this.Famas282.setTextureSize(64, 32);
      this.Famas282.mirror = true;
      this.setRotation(this.Famas282, 1.003822F, 0.0F, 0.0F);
      this.Famas283 = new ModelRenderer(this, 0, 0);
      this.Famas283.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.Famas283.setRotationPoint(-2.5F, -19.3F, -17.9F);
      this.Famas283.setTextureSize(64, 32);
      this.Famas283.mirror = true;
      this.setRotation(this.Famas283, 0.3717861F, 0.0F, 0.0F);
      this.Famas284 = new ModelRenderer(this, 0, 0);
      this.Famas284.addBox(0.0F, 0.0F, 0.0F, 2, 3, 1);
      this.Famas284.setRotationPoint(-2.5F, -17.6F, -19.0F);
      this.Famas284.setTextureSize(64, 32);
      this.Famas284.mirror = true;
      this.setRotation(this.Famas284, 0.1487144F, 0.0F, 0.0F);
      this.Famas285 = new ModelRenderer(this, 0, 0);
      this.Famas285.addBox(0.0F, 0.0F, 0.0F, 1, 5, 1);
      this.Famas285.setRotationPoint(-2.0F, -15.7F, -13.0F);
      this.Famas285.setTextureSize(64, 32);
      this.Famas285.mirror = true;
      this.setRotation(this.Famas285, -1.747395F, 0.0F, 0.0F);
      this.Famas286 = new ModelRenderer(this, 0, 0);
      this.Famas286.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2);
      this.Famas286.setRotationPoint(-2.5F, -15.6F, -18.5F);
      this.Famas286.setTextureSize(64, 32);
      this.Famas286.mirror = true;
      this.setRotation(this.Famas286, 0.0F, 0.0F, 0.0F);
      this.Famas287 = new ModelRenderer(this, 0, 0);
      this.Famas287.addBox(0.0F, 0.0F, 0.0F, 2, 2, 1);
      this.Famas287.setRotationPoint(-2.5F, -15.6F, -16.5F);
      this.Famas287.setTextureSize(64, 32);
      this.Famas287.mirror = true;
      this.setRotation(this.Famas287, -2.156359F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.Famas280.render(f5);
      this.Famas281.render(f5);
      this.Famas282.render(f5);
      this.Famas283.render(f5);
      this.Famas284.render(f5);
      this.Famas285.render(f5);
      this.Famas286.render(f5);
      this.Famas287.render(f5);
   }
}
