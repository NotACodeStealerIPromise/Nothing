package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;

public class CompatibleRenderTickEvent {
   private RenderTickEvent event;

   public CompatibleRenderTickEvent(RenderTickEvent event) {
      this.event = event;
   }

   public CompatibleRenderTickEvent.Phase getPhase() {
      return this.event.phase == cpw.mods.fml.common.gameevent.TickEvent.Phase.START?CompatibleRenderTickEvent.Phase.START:CompatibleRenderTickEvent.Phase.END;
   }

   public float getRenderTickTime() {
      return this.event.renderTickTime;
   }

   public static enum Phase {
      START,
      END;
   }
}
