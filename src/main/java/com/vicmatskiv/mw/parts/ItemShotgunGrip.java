package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemShotgunGrip extends Item {
   public ItemShotgunGrip() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_ShotgunGrip");
      this.setTextureName("mw:shotgungrip");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
