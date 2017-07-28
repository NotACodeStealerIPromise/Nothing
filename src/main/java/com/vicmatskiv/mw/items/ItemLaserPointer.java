package com.vicmatskiv.mw.items;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemLaserPointer extends Item {
   public ItemLaserPointer() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_LaserPointer");
      this.setTextureName("mw:laserpointer");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
