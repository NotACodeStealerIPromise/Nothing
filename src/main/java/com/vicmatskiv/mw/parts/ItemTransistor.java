package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemTransistor extends Item {
   public ItemTransistor() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Transistor");
      this.setTextureName("mw:transistor");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
