package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.perspective.WirelessCameraPerspective;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerTabletInstance extends PlayerItemInstance {
   private static final int SERIAL_VERSION = 1;
   private static final Logger logger = LogManager.getLogger(PlayerTabletInstance.class);
   private int activeWatchIndex;

   public PlayerTabletInstance() {
   }

   public PlayerTabletInstance(int itemInventoryIndex, EntityPlayer player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   public PlayerTabletInstance(int itemInventoryIndex, EntityPlayer player) {
      super(itemInventoryIndex, player);
   }

   public Class getRequiredPerspectiveType() {
      return WirelessCameraPerspective.class;
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      buf.writeInt(this.activeWatchIndex);
   }

   public void setActiveWatchIndex(int activeWatchIndex) {
      if(this.activeWatchIndex != activeWatchIndex) {
         logger.debug("Changing active watch index to {}", new Object[]{Integer.valueOf(activeWatchIndex)});
         this.activeWatchIndex = activeWatchIndex;
         ++this.updateId;
      }

   }

   public int getActiveWatchIndex() {
      return this.activeWatchIndex;
   }

   public void init(ByteBuf buf) {
      super.init(buf);
      this.activeWatchIndex = buf.readInt();
   }

   protected int getSerialVersion() {
      return 1;
   }

   public void nextActiveWatchIndex() {
      PlayerEntityTracker tracker = PlayerEntityTracker.getTracker(this.player);
      if(tracker != null) {
         if(this.activeWatchIndex >= tracker.getTrackableEntitites().size() - 1) {
            this.setActiveWatchIndex(0);
         } else {
            this.setActiveWatchIndex(this.activeWatchIndex + 1);
         }
      }

   }

   public void previousActiveWatchIndex() {
      PlayerEntityTracker tracker = PlayerEntityTracker.getTracker(this.player);
      if(tracker != null) {
         if(this.activeWatchIndex == 0) {
            this.setActiveWatchIndex(tracker.getTrackableEntitites().size() - 1);
         } else {
            this.setActiveWatchIndex(this.activeWatchIndex - 1);
         }
      }

   }

   public String toString() {
      return "Tablet [" + this.getUuid() + "]";
   }

   static {
      TypeRegistry.getInstance().register(PlayerTabletInstance.class);
   }
}
