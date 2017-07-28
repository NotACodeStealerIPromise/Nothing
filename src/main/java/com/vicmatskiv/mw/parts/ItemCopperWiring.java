package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemCopperWiring extends Item {
   public ItemCopperWiring() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_CopperWiring");
      this.setTextureName("mw:copperwiring");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
