package com.vicmatskiv.weaponlib.blocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DeconstructingAddedRecipes {
   public static boolean shouldAddRecipe(Item parItem) {
      return parItem == Items.enchanted_book || parItem == Items.iron_horse_armor || parItem == Items.golden_horse_armor || parItem == Items.diamond_horse_armor;
   }

   public static ItemStack[] getCraftingGrid(Item parItem) {
      ItemStack[] resultItemStackArray = new ItemStack[9];

      for(int j = 0; j < resultItemStackArray.length; ++j) {
         resultItemStackArray[j] = null;
      }

      if(parItem == Items.enchanted_book) {
         resultItemStackArray = new ItemStack[]{null, new ItemStack(Items.leather, 1, 0), null, new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), new ItemStack(Items.paper, 1, 0), null, null, null};
      } else {
         if(parItem == Items.iron_horse_armor) {
            return new ItemStack[]{null, null, new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 15), new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.iron_ingot, 1, 0), new ItemStack(Items.iron_ingot, 1, 0)};
         }

         if(parItem == Items.golden_horse_armor) {
            return new ItemStack[]{null, null, new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 12), new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.gold_ingot, 1, 0), new ItemStack(Items.gold_ingot, 1, 0)};
         }

         if(parItem == Items.diamond_horse_armor) {
            return new ItemStack[]{null, null, new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Item.getItemFromBlock(Blocks.wool), 1, 11), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0), new ItemStack(Items.diamond, 1, 0)};
         }
      }

      return resultItemStackArray;
   }
}
