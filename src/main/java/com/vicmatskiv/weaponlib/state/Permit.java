package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.network.UniversalObject;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public class Permit extends UniversalObject {
   protected ManagedState state;
   protected Permit.Status status;
   protected long timestamp;

   public Permit() {
      this.status = Permit.Status.UNKNOWN;
   }

   public Permit(ManagedState state) {
      this.state = state;
      this.status = Permit.Status.REQUESTED;
      this.timestamp = System.currentTimeMillis();
   }

   public ManagedState getState() {
      return this.state;
   }

   public void setStatus(Permit.Status status) {
      this.status = status;
   }

   public Permit.Status getStatus() {
      return this.status;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public void init(ByteBuf buf) {
      super.init(buf);
      this.timestamp = buf.readLong();
      this.status = Permit.Status.values()[buf.readInt()];
      this.state = (ManagedState)TypeRegistry.getInstance().fromBytes(buf);
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      buf.writeLong(this.timestamp);
      buf.writeInt(this.status.ordinal());
      TypeRegistry.getInstance().toBytes(this.state, buf);
   }

   static {
      TypeRegistry.getInstance().register(Permit.class);
   }

   public static enum Status {
      REQUESTED,
      GRANTED,
      DENIED,
      UNKNOWN;
   }
}
