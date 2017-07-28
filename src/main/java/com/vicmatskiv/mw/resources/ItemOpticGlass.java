package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemOpticGlass extends Item {
   public ItemOpticGlass() {
      this.setMaxStackSize(16);
      this.setUnlocalizedName("mw_OpticGlass");
      this.setTextureName("mw:opticglass");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
