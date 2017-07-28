package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemTantalumIngot extends Item {
   public ItemTantalumIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_TantalumIngot");
      this.setTextureName("mw:tantalumingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
