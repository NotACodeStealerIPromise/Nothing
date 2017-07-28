package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;

public abstract class CompatibleEntityRenderer extends Render {
   public final void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
      this.doCompatibleRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected abstract void doCompatibleRender(Entity var1, double var2, double var4, double var6, float var8, float var9);
}
