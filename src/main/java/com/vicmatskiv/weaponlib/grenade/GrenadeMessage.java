package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import io.netty.buffer.ByteBuf;

public class GrenadeMessage implements CompatibleMessage {
   private PlayerGrenadeInstance instance;
   private long activationTimestamp;

   public GrenadeMessage() {
   }

   public GrenadeMessage(PlayerGrenadeInstance instance, long activationTimestamp) {
      this.instance = instance;
      this.activationTimestamp = activationTimestamp;
   }

   public void fromBytes(ByteBuf buf) {
      this.instance = (PlayerGrenadeInstance)TypeRegistry.getInstance().fromBytes(buf);
      this.activationTimestamp = buf.readLong();
   }

   public void toBytes(ByteBuf buf) {
      TypeRegistry.getInstance().toBytes(this.instance, buf);
      buf.writeLong(this.activationTimestamp);
   }

   public PlayerGrenadeInstance getInstance() {
      return this.instance;
   }

   public long getActivationTimestamp() {
      return this.activationTimestamp;
   }
}
