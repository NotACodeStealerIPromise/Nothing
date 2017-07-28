package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemBarrel extends Item {
   public ItemBarrel() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_Barrel");
      this.setTextureName("mw:barrel");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
