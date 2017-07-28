package com.vicmatskiv.weaponlib.network;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.network.UniversallySerializable;
import com.vicmatskiv.weaponlib.state.Permit;
import io.netty.buffer.ByteBuf;

public class PermitMessage implements CompatibleMessage {
   private Permit permit;
   private Object context;

   public PermitMessage() {
   }

   public PermitMessage(Permit permit, Object context) {
      this.permit = permit;
      this.context = context;
   }

   public Permit getPermit() {
      return this.permit;
   }

   public Object getContext() {
      return this.context;
   }

   public void fromBytes(ByteBuf buf) {
      TypeRegistry typeRegistry = TypeRegistry.getInstance();
      this.context = typeRegistry.fromBytes(buf);
      this.permit = (Permit)typeRegistry.fromBytes(buf);
   }

   public void toBytes(ByteBuf buf) {
      TypeRegistry typeRegistry = TypeRegistry.getInstance();
      typeRegistry.toBytes((UniversallySerializable)this.context, buf);
      typeRegistry.toBytes(this.permit, buf);
   }
}
