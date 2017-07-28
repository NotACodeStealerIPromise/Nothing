package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemCloth extends Item {
   public ItemCloth() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Cloth");
      this.setTextureName("mw:cloth");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
