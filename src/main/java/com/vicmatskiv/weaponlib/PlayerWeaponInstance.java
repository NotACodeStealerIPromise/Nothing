package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AsyncWeaponState;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemScope;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.perspective.OpticalScopePerspective;
import io.netty.buffer.ByteBuf;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingDeque;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerWeaponInstance extends PlayerItemInstance {
   private static final int SERIAL_VERSION = 7;
   private static final Logger logger = LogManager.getLogger(PlayerWeaponInstance.class);
   private int ammo;
   private float recoil;
   private int seriesShotCount;
   private long lastFireTimestamp;
   private boolean aimed;
   private int maxShots;
   private float zoom = 1.0F;
   private byte activeTextureIndex;
   private boolean laserOn;
   private Deque filteredStateQueue = new LinkedBlockingDeque();
   private int[] activeAttachmentIds = new int[0];
   private byte[] selectedAttachmentIndexes = new byte[0];

   public PlayerWeaponInstance() {
   }

   public PlayerWeaponInstance(int itemInventoryIndex, EntityPlayer player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   public PlayerWeaponInstance(int itemInventoryIndex, EntityPlayer player) {
      super(itemInventoryIndex, player);
   }

   protected int getSerialVersion() {
      return 7;
   }

   private void addStateToHistory(WeaponState state) {
      AsyncWeaponState t;
      while((t = (AsyncWeaponState)this.filteredStateQueue.peekFirst()) != null && t.getState().getPriority() < state.getPriority()) {
         this.filteredStateQueue.pollFirst();
      }

      long expirationTimeout;
      if(state != WeaponState.FIRING && state != WeaponState.RECOILED && state != WeaponState.PAUSED) {
         expirationTimeout = 2147483647L;
      } else {
         if(this.isAutomaticModeEnabled() && !this.getWeapon().hasRecoilPositioning()) {
            expirationTimeout = (long)(50.0F / this.getFireRate());
         } else {
            expirationTimeout = 500L;
         }

         expirationTimeout = 500L;
      }

      this.filteredStateQueue.addFirst(new AsyncWeaponState(state, this.stateUpdateTimestamp, expirationTimeout));
   }

   public boolean setState(WeaponState state) {
      boolean result = super.setState(state);
      this.addStateToHistory(state);
      return result;
   }

   public AsyncWeaponState nextHistoryState() {
      AsyncWeaponState result = (AsyncWeaponState)this.filteredStateQueue.pollLast();
      if(result == null) {
         result = new AsyncWeaponState((WeaponState)this.getState(), this.stateUpdateTimestamp);
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
      this.activeAttachmentIds = initIntArray(buf);
      this.selectedAttachmentIndexes = initByteArray(buf);
      this.ammo = buf.readInt();
      this.aimed = buf.readBoolean();
      this.recoil = buf.readFloat();
      this.maxShots = buf.readInt();
      this.zoom = buf.readFloat();
      this.activeTextureIndex = buf.readByte();
      this.laserOn = buf.readBoolean();
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
      serializeIntArray(buf, this.activeAttachmentIds);
      serializeByteArray(buf, this.selectedAttachmentIndexes);
      buf.writeInt(this.ammo);
      buf.writeBoolean(this.aimed);
      buf.writeFloat(this.recoil);
      buf.writeInt(this.maxShots);
      buf.writeFloat(this.zoom);
      buf.writeByte(this.activeTextureIndex);
      buf.writeBoolean(this.laserOn);
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
      PlayerWeaponInstance otherWeaponInstance = (PlayerWeaponInstance)otherItemInstance;
      this.setAmmo(otherWeaponInstance.ammo);
      this.setZoom(otherWeaponInstance.zoom);
      this.setRecoil(otherWeaponInstance.recoil);
      this.setSelectedAttachmentIndexes(otherWeaponInstance.selectedAttachmentIndexes);
      this.setActiveAttachmentIds(otherWeaponInstance.activeAttachmentIds);
      this.setActiveTextureIndex(otherWeaponInstance.activeTextureIndex);
      this.setLaserOn(otherWeaponInstance.laserOn);
      this.setMaxShots(otherWeaponInstance.maxShots);
   }

   public Weapon getWeapon() {
      return (Weapon)this.item;
   }

   public float getRecoil() {
      return this.recoil;
   }

   public void setRecoil(float recoil) {
      if(recoil != this.recoil) {
         this.recoil = recoil;
         ++this.updateId;
      }

   }

   public int getMaxShots() {
      return this.maxShots;
   }

   void setMaxShots(int maxShots) {
      if(this.maxShots != maxShots) {
         this.maxShots = maxShots;
         ++this.updateId;
      }

   }

   public int getSeriesShotCount() {
      return this.seriesShotCount;
   }

   public void setSeriesShotCount(int seriesShotCount) {
      this.seriesShotCount = seriesShotCount;
   }

   public long getLastFireTimestamp() {
      return this.lastFireTimestamp;
   }

   public void setLastFireTimestamp(long lastFireTimestamp) {
      this.lastFireTimestamp = lastFireTimestamp;
   }

   public void resetCurrentSeries() {
      this.seriesShotCount = 0;
   }

   public float getFireRate() {
      return this.getWeapon().builder.fireRate;
   }

   public boolean isAutomaticModeEnabled() {
      return this.maxShots > 1;
   }

   public boolean isAimed() {
      return this.aimed;
   }

   public void setAimed(boolean aimed) {
      if(aimed != this.aimed) {
         this.aimed = aimed;
         ++this.updateId;
      }

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

   public boolean isAttachmentZoomEnabled() {
      ItemAttachment scopeItem = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
      return scopeItem instanceof ItemScope;
   }

   public ItemAttachment getAttachmentItemWithCategory(AttachmentCategory category) {
      if(this.activeAttachmentIds != null && this.activeAttachmentIds.length > category.ordinal()) {
         Item activeAttachment = Item.getItemById(this.activeAttachmentIds[category.ordinal()]);
         return activeAttachment instanceof ItemAttachment?(ItemAttachment)activeAttachment:null;
      } else {
         return null;
      }
   }

   public float getZoom() {
      return this.zoom;
   }

   public void setZoom(float zoom) {
      if(this.zoom != zoom) {
         this.zoom = zoom;
         ++this.updateId;
      }

   }

   public boolean isLaserOn() {
      return this.laserOn;
   }

   public void setLaserOn(boolean laserOn) {
      if(this.laserOn != laserOn) {
         this.laserOn = laserOn;
         ++this.updateId;
      }

   }

   public int getActiveTextureIndex() {
      return this.activeTextureIndex;
   }

   public void setActiveTextureIndex(int activeTextureIndex) {
      if(this.activeTextureIndex != activeTextureIndex) {
         if(activeTextureIndex > 127) {
            throw new IllegalArgumentException("activeTextureIndex must be less than 127");
         }

         this.activeTextureIndex = (byte)activeTextureIndex;
         ++this.updateId;
      }

   }

   public Class getRequiredPerspectiveType() {
      Class result = null;
      if(this.isAimed()) {
         ItemAttachment scope = this.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
         if(scope instanceof ItemScope && ((ItemScope)scope).isOptical()) {
            result = OpticalScopePerspective.class;
         }
      }

      return result;
   }

   public String toString() {
      return this.getWeapon().builder.name + "[" + this.getUuid() + "]";
   }

   static {
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
   }
}
