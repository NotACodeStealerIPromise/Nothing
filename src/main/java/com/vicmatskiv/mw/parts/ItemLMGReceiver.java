package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemLMGReceiver extends Item {
   public ItemLMGReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_LMGReceiver");
      this.setTextureName("mw:lmgreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
