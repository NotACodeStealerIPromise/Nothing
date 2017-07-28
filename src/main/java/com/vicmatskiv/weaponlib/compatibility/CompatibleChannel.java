package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSide;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTargetPoint;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public class CompatibleChannel {
   private SimpleNetworkWrapper channel;

   public CompatibleChannel(SimpleNetworkWrapper channel) {
      this.channel = channel;
   }

   public SimpleNetworkWrapper getChannel() {
      return this.channel;
   }

   public void registerMessage(CompatibleMessageHandler messageHandler, Class requestMessageType, int discriminator, CompatibleSide side) {
      this.channel.registerMessage(messageHandler, requestMessageType, discriminator, side.getSide());
   }

   public void sendToAllAround(CompatibleMessage spawnParticleMessage, CompatibleTargetPoint point) {
      this.channel.sendToAllAround(spawnParticleMessage, point.getTargetPoint());
   }
}
