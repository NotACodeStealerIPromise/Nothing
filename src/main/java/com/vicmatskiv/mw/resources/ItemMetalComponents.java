package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemMetalComponents extends Item {
   public ItemMetalComponents() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_MetalComponents");
      this.setTextureName("mw:metalcomponents");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
