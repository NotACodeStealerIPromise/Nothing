package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.PlayerContext;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.network.UniversalObject;
import com.vicmatskiv.weaponlib.state.ExtendedState;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerItemInstance extends UniversalObject implements ExtendedState, PlayerContext {
   private static final Logger logger = LogManager.getLogger(PlayerItemInstance.class);
   protected ManagedState state;
   protected long stateUpdateTimestamp = System.currentTimeMillis();
   protected long updateId;
   protected EntityPlayer player;
   protected Item item;
   protected int itemInventoryIndex;
   private PlayerItemInstance preparedState;
   private long syncStartTimestamp;

   public PlayerItemInstance() {
   }

   public PlayerItemInstance(int itemInventoryIndex, EntityPlayer player) {
      this.itemInventoryIndex = itemInventoryIndex;
      this.player = player;
      ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
      if(itemStack != null) {
         this.item = itemStack.getItem();
      }

   }

   public PlayerItemInstance(int itemInventoryIndex, EntityPlayer player, ItemStack itemStack) {
      this.itemInventoryIndex = itemInventoryIndex;
      this.player = player;
      if(itemStack != null) {
         this.item = itemStack.getItem();
      }

   }

   public EntityPlayer getPlayer() {
      return this.player;
   }

   public void setPlayer(EntityPlayer player) {
      this.player = player;
   }

   public Item getItem() {
      return this.item;
   }

   public ItemStack getItemStack() {
      return CompatibilityProvider.compatibility.getInventoryItemStack(this.player, this.itemInventoryIndex);
   }

   public int getItemInventoryIndex() {
      return this.itemInventoryIndex;
   }

   protected void setItemInventoryIndex(int itemInventoryIndex) {
      this.itemInventoryIndex = itemInventoryIndex;
   }

   protected PlayerItemInstance getPreparedState() {
      return this.preparedState;
   }

   public void init(ByteBuf buf) {
      super.init(buf);
      this.item = Item.getItemById(buf.readInt());
      this.itemInventoryIndex = buf.readInt();
      this.updateId = buf.readLong();
      this.state = (ManagedState)TypeRegistry.getInstance().fromBytes(buf);
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      buf.writeInt(Item.getIdFromItem(this.item));
      buf.writeInt(this.itemInventoryIndex);
      buf.writeLong(this.updateId);
      TypeRegistry.getInstance().toBytes(this.state, buf);
   }

   public boolean setState(ManagedState state) {
      this.state = state;
      this.stateUpdateTimestamp = System.currentTimeMillis();
      ++this.updateId;
      if(this.preparedState != null) {
         if(this.preparedState.getState().commitPhase() == state) {
            logger.debug("Committing state {} to {}", new Object[]{this.preparedState.getState(), this.preparedState.getState().commitPhase()});
            this.updateWith(this.preparedState, false);
         } else {
            this.rollback();
         }

         this.preparedState = null;
      }

      return false;
   }

   protected void rollback() {
   }

   protected void updateWith(PlayerItemInstance otherState, boolean updateManagedState) {
      if(updateManagedState) {
         this.setState(otherState.getState());
      }

   }

   public ManagedState getState() {
      return this.state;
   }

   public long getStateUpdateTimestamp() {
      return this.stateUpdateTimestamp;
   }

   public long getUpdateId() {
      return this.updateId;
   }

   void markDirty() {
      ++this.updateId;
   }

   public void prepareTransaction(ExtendedState preparedExtendedState) {
      this.setState(preparedExtendedState.getState());
      this.preparedState = (PlayerItemInstance)preparedExtendedState;
   }

   public long getSyncStartTimestamp() {
      return this.syncStartTimestamp;
   }

   public void setSyncStartTimestamp(long syncStartTimestamp) {
      this.syncStartTimestamp = syncStartTimestamp;
   }

   public Class getRequiredPerspectiveType() {
      return null;
   }

   static {
      TypeRegistry.getInstance().register(PlayerItemInstance.class);
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
   }
}
