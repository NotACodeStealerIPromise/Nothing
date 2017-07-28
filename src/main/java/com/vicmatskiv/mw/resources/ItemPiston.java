package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemPiston extends Item {
   public ItemPiston() {
      this.setMaxStackSize(16);
      this.setUnlocalizedName("mw_Piston");
      this.setTextureName("mw:piston");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
