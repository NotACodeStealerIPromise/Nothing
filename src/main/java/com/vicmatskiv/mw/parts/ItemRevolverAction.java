package com.vicmatskiv.mw.parts;

import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.item.Item;

public class ItemRevolverAction extends Item {
   public ItemRevolverAction() {
      this.setMaxStackSize(64);
      this.setUnlocalizedName("mw_RevolverAction");
      this.setTextureName("mw:revolveraction");
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }
}
