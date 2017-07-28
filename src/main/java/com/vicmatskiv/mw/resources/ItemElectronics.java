package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemElectronics extends Item {
   public ItemElectronics() {
      this.setMaxStackSize(32);
      this.setUnlocalizedName("mw_Electronics");
      this.setTextureName("mw:electronics");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
