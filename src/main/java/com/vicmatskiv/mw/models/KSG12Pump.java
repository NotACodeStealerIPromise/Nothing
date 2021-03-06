package com.vicmatskiv.mw.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class KSG12Pump extends ModelBase {
   ModelRenderer gun38;
   ModelRenderer gun40;
   ModelRenderer gun51;
   ModelRenderer gun56;
   ModelRenderer gun84;
   ModelRenderer gun85;
   ModelRenderer gun86;
   ModelRenderer gun87;
   ModelRenderer gun88;
   ModelRenderer gun89;
   ModelRenderer gun90;
   ModelRenderer gun91;
   ModelRenderer gun92;
   ModelRenderer gun106;
   ModelRenderer gun107;
   ModelRenderer gun108;
   ModelRenderer gun109;
   ModelRenderer gun110;
   ModelRenderer gun112;
   ModelRenderer gun113;
   ModelRenderer gun114;

   public KSG12Pump() {
      this.textureWidth = 256;
      this.textureHeight = 128;
      this.gun38 = new ModelRenderer(this, 0, 50);
      this.gun38.addBox(0.0F, 0.0F, 0.0F, 4, 2, 25);
      this.gun38.setRotationPoint(0.5F, -12.5F, -21.0F);
      this.gun38.setTextureSize(64, 32);
      this.gun38.mirror = true;
      this.setRotation(this.gun38, 0.0F, 0.0F, 0.0F);
      this.gun40 = new ModelRenderer(this, 0, 50);
      this.gun40.addBox(0.0F, 0.0F, 0.0F, 6, 2, 25);
      this.gun40.setRotationPoint(-0.5F, -13.0F, -21.0F);
      this.gun40.setTextureSize(64, 32);
      this.gun40.mirror = true;
      this.setRotation(this.gun40, 0.0F, 0.0F, 0.0F);
      this.gun51 = new ModelRenderer(this, 0, 50);
      this.gun51.addBox(0.0F, 0.0F, 0.0F, 4, 2, 12);
      this.gun51.setRotationPoint(0.5F, -16.0F, -21.0F);
      this.gun51.setTextureSize(64, 32);
      this.gun51.mirror = true;
      this.setRotation(this.gun51, 0.0F, 0.0F, 0.0F);
      this.gun56 = new ModelRenderer(this, 0, 50);
      this.gun56.addBox(0.0F, 0.0F, 0.0F, 2, 1, 12);
      this.gun56.setRotationPoint(1.5F, -16.5F, -21.0F);
      this.gun56.setTextureSize(64, 32);
      this.gun56.mirror = true;
      this.setRotation(this.gun56, 0.0F, 0.0F, 0.0F);
      this.gun84 = new ModelRenderer(this, 0, 50);
      this.gun84.addBox(0.0F, 0.0F, 0.0F, 2, 1, 14);
      this.gun84.setRotationPoint(1.5F, -11.3F, -21.0F);
      this.gun84.setTextureSize(64, 32);
      this.gun84.mirror = true;
      this.setRotation(this.gun84, 0.0F, 0.0F, 0.0F);
      this.gun85 = new ModelRenderer(this, 0, 50);
      this.gun85.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun85.setRotationPoint(1.5F, -11.0F, -21.0F);
      this.gun85.setTextureSize(64, 32);
      this.gun85.mirror = true;
      this.setRotation(this.gun85, 0.0F, 0.0F, 0.0F);
      this.gun86 = new ModelRenderer(this, 0, 50);
      this.gun86.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun86.setRotationPoint(1.5F, -11.0F, -19.0F);
      this.gun86.setTextureSize(64, 32);
      this.gun86.mirror = true;
      this.setRotation(this.gun86, 0.0F, 0.0F, 0.0F);
      this.gun87 = new ModelRenderer(this, 0, 50);
      this.gun87.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun87.setRotationPoint(1.5F, -11.0F, -17.0F);
      this.gun87.setTextureSize(64, 32);
      this.gun87.mirror = true;
      this.setRotation(this.gun87, 0.0F, 0.0F, 0.0F);
      this.gun88 = new ModelRenderer(this, 0, 50);
      this.gun88.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun88.setRotationPoint(1.5F, -11.0F, -15.0F);
      this.gun88.setTextureSize(64, 32);
      this.gun88.mirror = true;
      this.setRotation(this.gun88, 0.0F, 0.0F, 0.0F);
      this.gun89 = new ModelRenderer(this, 0, 50);
      this.gun89.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun89.setRotationPoint(1.5F, -11.0F, -13.0F);
      this.gun89.setTextureSize(64, 32);
      this.gun89.mirror = true;
      this.setRotation(this.gun89, 0.0F, 0.0F, 0.0F);
      this.gun90 = new ModelRenderer(this, 0, 50);
      this.gun90.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun90.setRotationPoint(1.5F, -11.0F, -11.0F);
      this.gun90.setTextureSize(64, 32);
      this.gun90.mirror = true;
      this.setRotation(this.gun90, 0.0F, 0.0F, 0.0F);
      this.gun91 = new ModelRenderer(this, 0, 50);
      this.gun91.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun91.setRotationPoint(1.5F, -11.0F, -9.0F);
      this.gun91.setTextureSize(64, 32);
      this.gun91.mirror = true;
      this.setRotation(this.gun91, 0.0F, 0.0F, 0.0F);
      this.gun92 = new ModelRenderer(this, 0, 50);
      this.gun92.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1);
      this.gun92.setRotationPoint(1.5F, -11.0F, -7.0F);
      this.gun92.setTextureSize(64, 32);
      this.gun92.mirror = true;
      this.setRotation(this.gun92, 0.0F, 0.0F, 0.0F);
      this.gun106 = new ModelRenderer(this, 0, 50);
      this.gun106.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.gun106.setRotationPoint(3.5F, -16.5F, -21.0F);
      this.gun106.setTextureSize(64, 32);
      this.gun106.mirror = true;
      this.setRotation(this.gun106, 0.0F, 0.0F, 0.4089647F);
      this.gun107 = new ModelRenderer(this, 0, 50);
      this.gun107.addBox(0.0F, 0.0F, 0.0F, 1, 1, 12);
      this.gun107.setRotationPoint(1.5F, -16.5F, -21.0F);
      this.gun107.setTextureSize(64, 32);
      this.gun107.mirror = true;
      this.setRotation(this.gun107, 0.0F, 0.0F, 1.07818F);
      this.gun108 = new ModelRenderer(this, 0, 50);
      this.gun108.addBox(0.0F, 0.0F, 0.0F, 3, 1, 12);
      this.gun108.setRotationPoint(4.5F, -16.0F, -21.0F);
      this.gun108.setTextureSize(64, 32);
      this.gun108.mirror = true;
      this.setRotation(this.gun108, 0.0F, 0.0F, 1.375609F);
      this.gun109 = new ModelRenderer(this, 0, 50);
      this.gun109.addBox(0.0F, 0.0F, 0.0F, 1, 3, 12);
      this.gun109.setRotationPoint(0.5F, -16.0F, -21.0F);
      this.gun109.setTextureSize(64, 32);
      this.gun109.mirror = true;
      this.setRotation(this.gun109, 0.0F, 0.0F, 0.2602503F);
      this.gun110 = new ModelRenderer(this, 0, 50);
      this.gun110.addBox(0.0F, 0.0F, 0.0F, 1, 1, 25);
      this.gun110.setRotationPoint(3.9F, -14.0F, -21.0F);
      this.gun110.setTextureSize(64, 32);
      this.gun110.mirror = true;
      this.setRotation(this.gun110, 0.0F, 0.0F, 0.0F);
      this.gun112 = new ModelRenderer(this, 0, 50);
      this.gun112.addBox(0.0F, 0.0F, 0.0F, 1, 1, 25);
      this.gun112.setRotationPoint(4.9F, -14.0F, -21.0F);
      this.gun112.setTextureSize(64, 32);
      this.gun112.mirror = true;
      this.setRotation(this.gun112, 0.0F, 0.0F, 1.003822F);
      this.gun113 = new ModelRenderer(this, 0, 50);
      this.gun113.addBox(0.0F, 0.0F, 0.0F, 1, 1, 25);
      this.gun113.setRotationPoint(0.0F, -14.0F, -21.0F);
      this.gun113.setTextureSize(64, 32);
      this.gun113.mirror = true;
      this.setRotation(this.gun113, 0.0F, 0.0F, 0.0F);
      this.gun114 = new ModelRenderer(this, 0, 50);
      this.gun114.addBox(0.0F, 0.0F, 0.0F, 1, 1, 25);
      this.gun114.setRotationPoint(0.0F, -14.0F, -21.0F);
      this.gun114.setTextureSize(64, 32);
      this.gun114.mirror = true;
      this.setRotation(this.gun114, 0.0F, 0.0F, 0.4461433F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.gun38.render(f5);
      this.gun40.render(f5);
      this.gun51.render(f5);
      this.gun56.render(f5);
      this.gun84.render(f5);
      this.gun85.render(f5);
      this.gun86.render(f5);
      this.gun87.render(f5);
      this.gun88.render(f5);
      this.gun89.render(f5);
      this.gun90.render(f5);
      this.gun91.render(f5);
      this.gun92.render(f5);
      this.gun106.render(f5);
      this.gun107.render(f5);
      this.gun108.render(f5);
      this.gun109.render(f5);
      this.gun110.render(f5);
      this.gun112.render(f5);
      this.gun113.render(f5);
      this.gun114.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
