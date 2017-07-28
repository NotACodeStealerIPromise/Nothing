package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemInductor extends Item {
   public ItemInductor() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Inductor");
      this.setTextureName("mw:inductor");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
