package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRevolverReceiver extends Item {
   public ItemRevolverReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_RevolverReceiver");
      this.setTextureName("mw:revolverreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
