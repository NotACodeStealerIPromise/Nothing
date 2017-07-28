package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRevolverGrip extends Item {
   public ItemRevolverGrip() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_RevolverGrip");
      this.setTextureName("mw:revolvergrip");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
