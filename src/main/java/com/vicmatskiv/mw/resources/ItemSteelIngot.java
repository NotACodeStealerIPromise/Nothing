package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSteelIngot extends Item {
   public ItemSteelIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SteelIngot");
      this.setTextureName("mw:steelingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
