package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.grenade.AsyncGrenadeState;
import com.vicmatskiv.weaponlib.grenade.GrenadeState;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerGrenadeInstance extends PlayerItemInstance {
   private static final int SERIAL_VERSION = 11;
   private static final Logger logger = LogManager.getLogger(PlayerGrenadeInstance.class);
   private int ammo;
   private long activationTimestamp;
   private Deque filteredStateQueue = new LinkedBlockingDeque();
   private int[] activeAttachmentIds = new int[0];
   private byte[] selectedAttachmentIndexes = new byte[0];
   private long lastSafetyPinAlertTimestamp;
   private boolean throwingFar;

   public PlayerGrenadeInstance() {
   }

   public PlayerGrenadeInstance(int itemInventoryIndex, EntityPlayer player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   public PlayerGrenadeInstance(int itemInventoryIndex, EntityPlayer player) {
      super(itemInventoryIndex, player);
   }

   protected int getSerialVersion() {
      return 11;
   }

   private void addStateToHistory(GrenadeState state) {
      AsyncGrenadeState t;
      while((t = (AsyncGrenadeState)this.filteredStateQueue.peekFirst()) != null && t.getState().getPriority() < state.getPriority()) {
         this.filteredStateQueue.pollFirst();
      }

      long expirationTimeout = 500L;
      this.filteredStateQueue.addFirst(new AsyncGrenadeState(state, this.stateUpdateTimestamp, expirationTimeout));
   }

   public boolean setState(GrenadeState state) {
      boolean result = super.setState(state);
      this.addStateToHistory(state);
      return result;
   }

   public AsyncGrenadeState nextHistoryState() {
      AsyncGrenadeState result = (AsyncGrenadeState)this.filteredStateQueue.pollLast();
      if(result == null) {
         result = new AsyncGrenadeState((GrenadeState)this.getState(), this.stateUpdateTimestamp);
      }

      return result;
   }

   public int getAmmo() {
      return this.ammo;
   }

   protected void setAmmo(int ammo) {
      if(ammo != this.ammo) {
         this.ammo = ammo;
         ++this.updateId;
      }

   }

   public void init(ByteBuf buf) {
      super.init(buf);
      this.throwingFar = buf.readBoolean();
      this.activeAttachmentIds = initIntArray(buf);
      this.selectedAttachmentIndexes = initByteArray(buf);
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      buf.writeBoolean(this.throwingFar);
      serializeIntArray(buf, this.activeAttachmentIds);
      serializeByteArray(buf, this.selectedAttachmentIndexes);
   }

   private static void serializeIntArray(ByteBuf buf, int[] a) {
      buf.writeByte(a.length);

      for(int i = 0; i < a.length; ++i) {
         buf.writeInt(a[i]);
      }

   }

   private static void serializeByteArray(ByteBuf buf, byte[] a) {
      buf.writeByte(a.length);

      for(int i = 0; i < a.length; ++i) {
         buf.writeByte(a[i]);
      }

   }

   private static int[] initIntArray(ByteBuf buf) {
      byte length = buf.readByte();
      int[] a = new int[length];

      for(int i = 0; i < length; ++i) {
         a[i] = buf.readInt();
      }

      return a;
   }

   private static byte[] initByteArray(ByteBuf buf) {
      byte length = buf.readByte();
      byte[] a = new byte[length];

      for(int i = 0; i < length; ++i) {
         a[i] = buf.readByte();
      }

      return a;
   }

   protected void updateWith(PlayerItemInstance otherItemInstance, boolean updateManagedState) {
      super.updateWith(otherItemInstance, updateManagedState);
      PlayerGrenadeInstance otherWeaponInstance = (PlayerGrenadeInstance)otherItemInstance;
      this.setAmmo(otherWeaponInstance.ammo);
      this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
      this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
   }

   public ItemGrenade getWeapon() {
      return (ItemGrenade)this.item;
   }

   public long getActivationTimestamp() {
      return this.activationTimestamp;
   }

   void setActivationTimestamp(long activationTimestamp) {
      this.activationTimestamp = activationTimestamp;
   }

   public int[] getActiveAttachmentIds() {
      if(this.activeAttachmentIds == null || this.activeAttachmentIds.length != AttachmentCategory.values.length) {
         this.activeAttachmentIds = new int[AttachmentCategory.values.length];
         Iterator var1 = this.getWeapon().getCompatibleAttachments().values().iterator();

         while(var1.hasNext()) {
            CompatibleAttachment attachment = (CompatibleAttachment)var1.next();
            if(attachment.isDefault()) {
               this.activeAttachmentIds[attachment.getAttachment().getCategory().ordinal()] = Item.getIdFromItem(attachment.getAttachment());
            }
         }
      }

      return this.activeAttachmentIds;
   }

   void setActiveAttachmentIds(int[] activeAttachmentIds) {
      if(!Arrays.equals(this.activeAttachmentIds, activeAttachmentIds)) {
         this.activeAttachmentIds = activeAttachmentIds;
         ++this.updateId;
      }

   }

   public byte[] getSelectedAttachmentIds() {
      return this.selectedAttachmentIndexes;
   }

   void setSelectedAttachmentIndexes(byte[] selectedAttachmentIndexes) {
      if(!Arrays.equals(this.selectedAttachmentIndexes, selectedAttachmentIndexes)) {
         this.selectedAttachmentIndexes = selectedAttachmentIndexes;
         ++this.updateId;
      }

   }

   public ItemAttachment getAttachmentItemWithCategory(AttachmentCategory category) {
      if(this.activeAttachmentIds != null && this.activeAttachmentIds.length > category.ordinal()) {
         Item scopeItem = Item.getItemById(this.activeAttachmentIds[category.ordinal()]);
         return (ItemAttachment)scopeItem;
      } else {
         return null;
      }
   }

   public List getActiveAttachments(RenderContext renderContext, boolean attached) {
      int[] activeIds = this.getActiveAttachmentIds();
      ArrayList result = new ArrayList();

      for(int i = 0; i < activeIds.length; ++i) {
         if(activeIds[i] != 0) {
            Item item = Item.getItemById(activeIds[i]);
            if(item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = (CompatibleAttachment)this.getWeapon().getCompatibleAttachments().get(item);
               if(compatibleAttachment != null) {
                  result.add(compatibleAttachment);
               }
            }
         }
      }

      return result;
   }

   public String toString() {
      return this.getWeapon().builder.name + "[" + this.getUuid() + "]";
   }

   public long getLastSafetyPinAlertTimestamp() {
      return this.lastSafetyPinAlertTimestamp;
   }

   public void setLastSafetyPinAlertTimestamp(long lastSafetyPinAlertTimestamp) {
      this.lastSafetyPinAlertTimestamp = lastSafetyPinAlertTimestamp;
   }

   public void setThrowingFar(boolean throwingFar) {
      this.throwingFar = throwingFar;
   }

   public boolean isThrowingFar() {
      return this.throwingFar;
   }

   static {
      TypeRegistry.getInstance().register(PlayerGrenadeInstance.class);
   }
}
