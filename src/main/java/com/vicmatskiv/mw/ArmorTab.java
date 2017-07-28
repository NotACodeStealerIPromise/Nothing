package com.vicmatskiv.mw;

import com.vicmatskiv.mw.Armors;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ArmorTab extends CreativeTabs {
   public ArmorTab(int par1, String par2Str) {
      super(par1, par2Str);
   }

   @SideOnly(Side.CLIENT)
   public Item getTabIconItem() {
      return Armors.Marinechest;
   }
}
