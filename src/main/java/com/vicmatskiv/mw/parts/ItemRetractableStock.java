package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRetractableStock extends Item {
   public ItemRetractableStock() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_RetractableStock");
      this.setTextureName("mw:retractablestock");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
