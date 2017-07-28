package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemScarReceiver extends Item {
   public ItemScarReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_ScarReceiver");
      this.setTextureName("mw:scarreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
