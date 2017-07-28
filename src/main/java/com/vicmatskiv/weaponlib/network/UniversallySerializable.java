package com.vicmatskiv.weaponlib.network;

import io.netty.buffer.ByteBuf;

public interface UniversallySerializable {
   void init(ByteBuf var1);

   void serialize(ByteBuf var1);
}
