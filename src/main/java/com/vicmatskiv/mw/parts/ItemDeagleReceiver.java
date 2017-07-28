package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemDeagleReceiver extends Item {
   public ItemDeagleReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_DeagleReceiver");
      this.setTextureName("mw:deaglereceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
