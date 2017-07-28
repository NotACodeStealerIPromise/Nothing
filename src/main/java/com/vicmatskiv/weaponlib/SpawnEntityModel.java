package com.vicmatskiv.weaponlib;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class SpawnEntityModel extends ModelBase {
   ModelRenderer ammunition;

   public SpawnEntityModel() {
      this.textureWidth = 32;
      this.textureHeight = 32;
      this.ammunition = new ModelRenderer(this, 0, 0);
      this.ammunition.addBox(0.0F, 0.0F, 0.0F, 1, 1, 3);
      this.ammunition.setRotationPoint(-0.5F, 22.0F, -1.5F);
      this.ammunition.setTextureSize(32, 32);
      this.ammunition.mirror = true;
      this.setRotation(this.ammunition, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.ammunition.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
