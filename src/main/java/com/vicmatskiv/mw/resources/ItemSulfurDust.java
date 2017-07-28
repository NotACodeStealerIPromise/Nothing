package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemSulfurDust extends Item {
   public ItemSulfurDust() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_SulfurDust");
      this.setTextureName("mw:sulfurdust");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
