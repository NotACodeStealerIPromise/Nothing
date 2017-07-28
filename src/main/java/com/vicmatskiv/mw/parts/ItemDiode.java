package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemDiode extends Item {
   public ItemDiode() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Diode");
      this.setTextureName("mw:diode");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
