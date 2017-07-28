package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemTanCloth extends Item {
   public ItemTanCloth() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_TanCloth");
      this.setTextureName("mw:tancloth");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
