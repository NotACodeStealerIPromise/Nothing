package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemCopperIngot extends Item {
   public ItemCopperIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_CopperIngot");
      this.setTextureName("mw:copperingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
