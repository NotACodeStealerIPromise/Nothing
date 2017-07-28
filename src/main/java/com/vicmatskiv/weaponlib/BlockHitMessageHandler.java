package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.BlockHitMessage;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;

public class BlockHitMessageHandler implements CompatibleMessageHandler {
   private ModContext modContext;

   public BlockHitMessageHandler(ModContext modContext) {
      this.modContext = modContext;
   }

   public CompatibleMessage onCompatibleMessage(BlockHitMessage message, CompatibleMessageContext ctx) {
      if(!ctx.isServerSide()) {
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
            CompatibilityProvider.compatibility.addBlockHitEffect(message.getPosX(), message.getPosY(), message.getPosZ(), message.getSideHit());
         });
      }

      return null;
   }
}
