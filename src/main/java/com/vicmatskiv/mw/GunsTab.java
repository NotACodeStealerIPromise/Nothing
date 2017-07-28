package com.vicmatskiv.mw;

import com.vicmatskiv.mw.Ores;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class GunsTab extends CreativeTabs {
   public GunsTab(int par1, String par2Str) {
      super(par1, par2Str);
   }

   @SideOnly(Side.CLIENT)
   public Item getTabIconItem() {
      return Ores.CopperIngot;
   }
}
