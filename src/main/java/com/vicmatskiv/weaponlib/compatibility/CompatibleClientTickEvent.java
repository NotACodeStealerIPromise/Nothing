package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;

public class CompatibleClientTickEvent {
   private ClientTickEvent event;

   public CompatibleClientTickEvent(ClientTickEvent event) {
      this.event = event;
   }

   public CompatibleClientTickEvent.Phase getPhase() {
      return this.event.phase == cpw.mods.fml.common.gameevent.TickEvent.Phase.START?CompatibleClientTickEvent.Phase.START:CompatibleClientTickEvent.Phase.END;
   }

   public static enum Phase {
      START,
      END;
   }
}
