package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import java.util.Random;
import java.util.function.BiConsumer;
import org.lwjgl.opengl.GL11;

public class LaserBeamRenderer implements CustomRenderer {
   private float xOffset = 0.5F;
   private float yOffset = -1.3F;
   private float zOffset = -1.7F;
   private BiConsumer positioning;

   public LaserBeamRenderer(BiConsumer positioning) {
      this.positioning = positioning;
   }

   public void render(RenderContext renderContext) {
      PlayerItemInstance instance = renderContext.getPlayerItemInstance();
      CompatibleTransformType type = renderContext.getCompatibleTransformType();
      if(instance instanceof PlayerWeaponInstance && ((PlayerWeaponInstance)instance).isLaserOn() && (type == CompatibleTransformType.THIRD_PERSON_LEFT_HAND || type == CompatibleTransformType.THIRD_PERSON_RIGHT_HAND || type == CompatibleTransformType.FIRST_PERSON_LEFT_HAND || type == CompatibleTransformType.FIRST_PERSON_RIGHT_HAND || type == CompatibleTransformType.GROUND)) {
         GL11.glPushMatrix();
         GL11.glPushAttrib(1048575);
         GL11.glDisable(2884);
         GL11.glDisable(2896);
         GL11.glDisable(3553);
         GL11.glEnable(3042);
         GL11.glBlendFunc(770, 771);
         GL11.glColor4f(1.0F, 0.0F, 0.0F, 0.5F);
         GL11.glLineWidth(1.5F);
         GL11.glDepthMask(false);
         if(this.positioning != null) {
            this.positioning.accept(renderContext.getPlayer(), renderContext.getWeapon());
         }

         CompatibleTessellator tessellator = CompatibleTessellator.getInstance();
         tessellator.startDrawingLines();
         long time = System.currentTimeMillis();
         Random random = new Random(time - time % 300L);
         float start = this.zOffset;
         float length = 100.0F;
         float end = 0.0F;

         for(int i = 0; i < 100 && start < length && end < length; ++i) {
            tessellator.addVertex(this.xOffset, this.yOffset, start);
            tessellator.endVertex();
            int ii = 15728880;
            int j = ii >> 16 & '\uffff';
            int k = ii & '\uffff';
            tessellator.setLightMap(j, k);
            end = start - (1.0F + random.nextFloat() * 2.0F);
            if(end > length) {
               end = length;
            }

            tessellator.addVertex(this.xOffset, this.yOffset, end);
            tessellator.endVertex();
            start = end + random.nextFloat() * 0.5F;
         }

         tessellator.draw();
         GL11.glDepthMask(true);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

   }
}
