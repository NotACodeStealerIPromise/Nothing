package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemP90Receiver extends Item {
   public ItemP90Receiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_P90Receiver");
      this.setTextureName("mw:p90receiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
