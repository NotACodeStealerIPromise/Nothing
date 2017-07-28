package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.compatibility.FlatSurfaceModelBox;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ViewfinderModel extends ModelBase {
   private ModelRenderer surfaceRenderer;
   private FlatSurfaceModelBox box;

   public ViewfinderModel() {
      this.textureWidth = 128;
      this.textureHeight = 64;
      this.surfaceRenderer = new ModelRenderer(this, 0, 0);
      this.box = new FlatSurfaceModelBox(this.surfaceRenderer, 0, 0, 0.0F, 0.0F, 0.0F, 3, 3, 0, 0.0F);
      this.surfaceRenderer.cubeList.add(this.box);
      this.surfaceRenderer.mirror = true;
      this.surfaceRenderer.setRotationPoint(0.0F, -10.0F, 0.0F);
      this.surfaceRenderer.setTextureSize(100, 100);
      this.setRotation(this.surfaceRenderer, 0.0F, 0.0F, 0.0F);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      super.render(entity, f, f1, f2, f3, f4, f5);
      this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
      this.surfaceRenderer.render(f5);
   }

   private void setRotation(ModelRenderer model, float x, float y, float z) {
      model.rotateAngleX = x;
      model.rotateAngleY = y;
      model.rotateAngleZ = z;
   }
}
