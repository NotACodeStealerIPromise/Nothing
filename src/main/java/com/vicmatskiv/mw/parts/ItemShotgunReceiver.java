package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemShotgunReceiver extends Item {
   public ItemShotgunReceiver() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_ShotgunReceiver");
      this.setTextureName("mw:shotgunreceiver");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
