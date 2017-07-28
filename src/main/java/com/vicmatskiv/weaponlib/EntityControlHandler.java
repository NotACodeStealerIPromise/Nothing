package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.EntityControlMessage;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;

public class EntityControlHandler implements CompatibleMessageHandler {
   private ModContext modContext;

   public EntityControlHandler(ModContext modContext) {
      this.modContext = modContext;
   }

   public CompatibleMessage onCompatibleMessage(EntityControlMessage message, CompatibleMessageContext ctx) {
      if(!ctx.isServerSide()) {
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
         });
      }

      return null;
   }
}
