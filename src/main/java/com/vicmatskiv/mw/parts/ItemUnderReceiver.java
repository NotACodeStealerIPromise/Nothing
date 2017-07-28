package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemUnderReceiver extends Item {
   public ItemUnderReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_UnderReceiver");
      this.setTextureName("mw:underreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
