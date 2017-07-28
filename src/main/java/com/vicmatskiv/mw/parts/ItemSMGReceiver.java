package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSMGReceiver extends Item {
   public ItemSMGReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SMGReceiver");
      this.setTextureName("mw:smgreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
