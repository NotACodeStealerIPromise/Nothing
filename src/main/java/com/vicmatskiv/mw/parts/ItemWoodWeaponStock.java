package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemWoodWeaponStock extends Item {
   public ItemWoodWeaponStock() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_WoodWeaponStock");
      this.setTextureName("mw:woodweaponstock");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
