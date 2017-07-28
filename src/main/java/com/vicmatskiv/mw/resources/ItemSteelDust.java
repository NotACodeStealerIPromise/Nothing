package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSteelDust extends Item {
   public ItemSteelDust() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SteelDust");
      this.setTextureName("mw:steeldust");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
