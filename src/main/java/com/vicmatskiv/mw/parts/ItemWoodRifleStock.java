package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemWoodRifleStock extends Item {
   public ItemWoodRifleStock() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_WoodRifleStock");
      this.setTextureName("mw:woodriflestock");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
