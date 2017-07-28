package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemGreenCloth extends Item {
   public ItemGreenCloth() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_GreenCloth");
      this.setTextureName("mw:greencloth");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
