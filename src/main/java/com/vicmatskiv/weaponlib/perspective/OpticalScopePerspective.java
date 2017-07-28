package com.vicmatskiv.weaponlib.perspective;

import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.RenderableState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.perspective.FirstPersonPerspective;

public class OpticalScopePerspective extends FirstPersonPerspective {
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 400;

   public OpticalScopePerspective() {
      this.width = 400;
      this.height = 400;
   }

   public float getBrightness(RenderContext renderContext) {
      float brightness = 0.0F;
      PlayerWeaponInstance instance = renderContext.getWeaponInstance();
      if(instance == null) {
         return 0.0F;
      } else {
         boolean aimed = instance != null && instance.isAimed();
         float progress = Math.min(1.0F, renderContext.getTransitionProgress());
         if(isAimingState((RenderableState)renderContext.getFromState()) && isAimingState((RenderableState)renderContext.getToState())) {
            brightness = 1.0F;
         } else if(progress > 0.0F && aimed && isAimingState((RenderableState)renderContext.getToState())) {
            brightness = progress;
         } else if(isAimingState((RenderableState)renderContext.getFromState()) && progress > 0.0F && !aimed) {
            brightness = Math.max(1.0F - progress, 0.0F);
         }

         return brightness;
      }
   }

   private static boolean isAimingState(RenderableState renderableState) {
      return renderableState == RenderableState.ZOOMING || renderableState == RenderableState.ZOOMING_RECOILED || renderableState == RenderableState.ZOOMING_SHOOTING;
   }

   public void update(CompatibleRenderTickEvent event) {
      PlayerWeaponInstance instance = this.modContext.getMainHeldWeapon();
      if(instance != null && instance.isAimed()) {
         super.update(event);
      }

   }
}
