package com.vicmatskiv.weaponlib.tracking;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import io.netty.buffer.ByteBuf;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SyncPlayerEntityTrackerMessage implements CompatibleMessage {
   private static final Logger logger = LogManager.getLogger(SyncPlayerEntityTrackerMessage.class);
   private Function playerEntityTracker;
   private String statusMessage;

   public SyncPlayerEntityTrackerMessage() {
   }

   public SyncPlayerEntityTrackerMessage(PlayerEntityTracker playerEntityTracker) {
      this(playerEntityTracker, (String)null);
   }

   public SyncPlayerEntityTrackerMessage(PlayerEntityTracker playerEntityTracker, String statusMessage) {
      this.playerEntityTracker = (a) -> {
         return playerEntityTracker;
      };
      this.statusMessage = statusMessage;
   }

   public void fromBytes(ByteBuf buf) {
      try {
         int e = buf.readInt();
         if(e > 0) {
            byte[] bytes = new byte[e];
            buf.readBytes(bytes);
            this.statusMessage = new String(bytes);
         }

         this.playerEntityTracker = (w) -> {
            return PlayerEntityTracker.fromBuf(buf, w);
         };
      } catch (Exception var4) {
         logger.error("Failed to deserialize tracker {}", var4);
      }

   }

   public void toBytes(ByteBuf buf) {
      byte[] statusMessageBytes = this.statusMessage != null?this.statusMessage.getBytes():new byte[0];
      buf.writeInt(statusMessageBytes.length);
      if(statusMessageBytes.length > 0) {
         buf.writeBytes(statusMessageBytes);
      }

      ((PlayerEntityTracker)this.playerEntityTracker.apply((Object)null)).serialize(buf);
   }

   public Function getTracker() {
      return this.playerEntityTracker;
   }

   public String getStatusMessage() {
      return this.statusMessage;
   }
}
