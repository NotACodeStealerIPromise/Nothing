package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemWeaponReceiver extends Item {
   public ItemWeaponReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_WeaponReceiver");
      this.setTextureName("mw:weaponreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
