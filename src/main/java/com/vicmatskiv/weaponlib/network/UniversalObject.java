package com.vicmatskiv.weaponlib.network;

import com.vicmatskiv.weaponlib.network.UniversallySerializable;
import io.netty.buffer.ByteBuf;
import java.util.UUID;

public abstract class UniversalObject implements UniversallySerializable {
   private UUID uuid = UUID.randomUUID();

   protected int getSerialVersion() {
      return 0;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public void init(ByteBuf buf) {
      if(this.getSerialVersion() != buf.readInt()) {
         throw new IndexOutOfBoundsException("Serial version mismatch");
      } else {
         this.uuid = new UUID(buf.readLong(), buf.readLong());
      }
   }

   public void serialize(ByteBuf buf) {
      buf.writeInt(this.getSerialVersion());
      buf.writeLong(this.uuid.getMostSignificantBits());
      buf.writeLong(this.uuid.getLeastSignificantBits());
   }
}
