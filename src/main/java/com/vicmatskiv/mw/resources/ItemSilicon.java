package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSilicon extends Item {
   public ItemSilicon() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Silicon");
      this.setTextureName("mw:silicon");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
