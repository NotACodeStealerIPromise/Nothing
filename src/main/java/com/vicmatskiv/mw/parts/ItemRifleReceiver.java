package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRifleReceiver extends Item {
   public ItemRifleReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_RifleReceiver");
      this.setTextureName("mw:riflereceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
