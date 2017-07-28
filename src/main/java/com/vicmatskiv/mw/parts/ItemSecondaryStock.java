package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSecondaryStock extends Item {
   public ItemSecondaryStock() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SecondaryStock");
      this.setTextureName("mw:secondarystock");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
