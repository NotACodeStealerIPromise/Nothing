package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemResistor extends Item {
   public ItemResistor() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Resistor");
      this.setTextureName("mw:resistor");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
