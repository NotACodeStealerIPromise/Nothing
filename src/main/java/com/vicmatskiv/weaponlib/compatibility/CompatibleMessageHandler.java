package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public interface CompatibleMessageHandler extends IMessageHandler {
   default CompatibleMessage onMessage(CompatibleMessage message, MessageContext ctx) {
      return this.onCompatibleMessage(message, new CompatibleMessageContext(ctx));
   }

   CompatibleMessage onCompatibleMessage(CompatibleMessage var1, CompatibleMessageContext var2);
}
