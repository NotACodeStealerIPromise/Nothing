package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemPistolUnderReceiver extends Item {
   public ItemPistolUnderReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_PistolUnderReceiver");
      this.setTextureName("mw:pistolunderreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
