package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemPlastic extends Item {
   public ItemPlastic() {
      this.setMaxStackSize(16);
      this.setUnlocalizedName("mw_Plastic");
      this.setTextureName("mw:plastic");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
