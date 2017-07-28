package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemMiniSteelPlate extends Item {
   public ItemMiniSteelPlate() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_MiniSteelPlate");
      this.setTextureName("mw:ministeelplate");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
