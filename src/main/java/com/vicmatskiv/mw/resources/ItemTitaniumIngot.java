package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemTitaniumIngot extends Item {
   public ItemTitaniumIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_TitaniumIngot");
      this.setTextureName("mw:titaniumingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
