package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemGunmetalIngot extends Item {
   public ItemGunmetalIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_GunmetalIngot");
      this.setTextureName("mw:gunmetalingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
