package com.vicmatskiv.mw.resources;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemLeadIngot extends Item {
   public ItemLeadIngot() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_LeadIngot");
      this.setTextureName("mw:leadingot");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
