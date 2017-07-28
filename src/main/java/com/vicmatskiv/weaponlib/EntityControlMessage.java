package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import io.netty.buffer.ByteBuf;

public class EntityControlMessage implements CompatibleMessage {
   private int action;

   public EntityControlMessage() {
   }

   public EntityControlMessage(EntityControlMessage.EntityAction action) {
      this.action = action.ordinal();
   }

   public void fromBytes(ByteBuf buf) {
      this.action = buf.readInt();
   }

   public void toBytes(ByteBuf buf) {
      buf.writeInt(this.action);
   }

   public EntityControlMessage.EntityAction getAction() {
      return EntityControlMessage.EntityAction.values()[this.action];
   }

   public static enum EntityAction {
      STOP;
   }
}
