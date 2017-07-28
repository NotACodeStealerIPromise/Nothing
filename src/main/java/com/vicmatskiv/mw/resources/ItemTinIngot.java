package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemTinIngot extends Item {
   public ItemTinIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_TinIngot");
      this.setTextureName("mw:tiningot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
