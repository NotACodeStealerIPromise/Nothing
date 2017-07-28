package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRuby extends Item {
   public ItemRuby() {
      this.setMaxStackSize(16);
      this.setUnlocalizedName("mw_Ruby");
      this.setTextureName("mw:ruby");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
