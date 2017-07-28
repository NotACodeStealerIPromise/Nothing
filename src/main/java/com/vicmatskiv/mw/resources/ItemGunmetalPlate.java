package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemGunmetalPlate extends Item {
   public ItemGunmetalPlate() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_GunmetalPlate");
      this.setTextureName("mw:gunmetalplate");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
