package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemWeaponStock extends Item {
   public ItemWeaponStock() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_WeaponStock");
      this.setTextureName("mw:weaponstock");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
