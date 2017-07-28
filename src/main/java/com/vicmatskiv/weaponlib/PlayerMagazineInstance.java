package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class PlayerMagazineInstance extends PlayerItemInstance {
   public PlayerMagazineInstance() {
   }

   public PlayerMagazineInstance(int itemInventoryIndex, EntityPlayer player, ItemStack itemStack) {
      super(itemInventoryIndex, player, itemStack);
   }

   public PlayerMagazineInstance(int itemInventoryIndex, EntityPlayer player) {
      super(itemInventoryIndex, player);
   }

   public void init(ByteBuf buf) {
      super.init(buf);
   }

   public void serialize(ByteBuf buf) {
      super.serialize(buf);
   }

   protected void updateWith(PlayerItemInstance otherItemInstance, boolean updateManagedState) {
      super.updateWith(otherItemInstance, updateManagedState);
   }

   public ItemMagazine getMagazine() {
      return (ItemMagazine)this.item;
   }

   static {
      TypeRegistry.getInstance().register(PlayerMagazineInstance.class);
   }
}
