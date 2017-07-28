package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemAluminumIngot extends Item {
   public ItemAluminumIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_AluminumIngot");
      this.setTextureName("mw:aluminumingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
