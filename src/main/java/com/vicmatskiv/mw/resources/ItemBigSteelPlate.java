package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemBigSteelPlate extends Item {
   public ItemBigSteelPlate() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_BigSteelPlate");
      this.setTextureName("mw:bigsteelplate");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
