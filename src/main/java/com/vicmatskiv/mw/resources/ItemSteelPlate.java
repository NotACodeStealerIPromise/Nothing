package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSteelPlate extends Item {
   public ItemSteelPlate() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SteelPlate");
      this.setTextureName("mw:steelplate");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
