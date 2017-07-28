package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemCapacitor extends Item {
   public ItemCapacitor() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Capacitor");
      this.setTextureName("mw:capacitor");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
