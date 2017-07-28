package com.vicmatskiv.weaponlib.tracking;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.compatibility.CompatiblePlayerEntityTrackerProvider;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import com.vicmatskiv.weaponlib.tracking.SyncPlayerEntityTrackerMessage;

public class SyncPlayerEntityTrackerMessageMessageHandler implements CompatibleMessageHandler {
   private ModContext modContext;

   public SyncPlayerEntityTrackerMessageMessageHandler(ModContext modContex) {
      this.modContext = modContex;
   }

   public CompatibleMessage onCompatibleMessage(SyncPlayerEntityTrackerMessage message, CompatibleMessageContext ctx) {
      if(!ctx.isServerSide()) {
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
            CompatiblePlayerEntityTrackerProvider.setTracker(CompatibilityProvider.compatibility.clientPlayer(), (PlayerEntityTracker)message.getTracker().apply(CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer())));
            if(message.getStatusMessage() != null) {
               this.modContext.getStatusMessageCenter().addMessage(message.getStatusMessage(), 1000L);
            }

         });
      }

      return null;
   }
}
