package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.BlockSmith;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;

public class DeconstructingInputQuantity {
   public static int getStackSizeNeeded(ItemStack parItemStack) {
      Item theItem = parItemStack.getItem();
      if(theItem == Items.enchanted_book) {
         return BlockSmith.allowDeconstructEnchantedBooks?1:0;
      } else {
         List crafts = CraftingManager.getInstance().getRecipeList();

         for(int i = 0; i < crafts.size(); ++i) {
            IRecipe recipe = (IRecipe)crafts.get(i);
            if(recipe != null) {
               ItemStack outputItemStack = recipe.getRecipeOutput();
               if(outputItemStack != null && outputItemStack.getUnlocalizedName().equals(parItemStack.getUnlocalizedName())) {
                  System.out.println("getStackSizeNeeded() found matching recipe");
                  return adjustQuantity(theItem, outputItemStack.stackSize);
               }
            }
         }

         System.out.println("No matching recipe found!");
         return 0;
      }
   }

   public static int adjustQuantity(Item theItem, int parDefaultQuantity) {
      if(BlockSmith.allowDeconstructUnrealistic || theItem != Items.paper && theItem != Items.melon_seeds && theItem != Items.pumpkin_seeds && theItem != Items.bread && theItem != Items.cake) {
         if(!BlockSmith.allowHorseArmorCrafting && (theItem == Items.saddle || theItem == Items.iron_horse_armor || theItem == Items.golden_horse_armor || theItem == Items.diamond_horse_armor)) {
            System.out.println("Trying to deconstruct horse armor or saddle item when not allowed");
            return 0;
         } else if(!BlockSmith.allowPartialDeconstructing) {
            System.out.println("Don\'t look for partial deconstruct recipe when not allowed");
            return parDefaultQuantity;
         } else {
            return theItem != Items.iron_door && theItem != Items.paper && theItem != Items.stick && theItem != Item.getItemFromBlock(Blocks.ladder) && theItem != Items.enchanted_book && theItem != Item.getItemFromBlock(Blocks.nether_brick_fence) && theItem != Items.sign && theItem != Items.glass_bottle && theItem != Item.getItemFromBlock(Blocks.cobblestone_wall) && theItem != Item.getItemFromBlock(Blocks.quartz_block) && theItem != Item.getItemFromBlock(Blocks.stained_hardened_clay) && theItem != Item.getItemFromBlock(Blocks.oak_stairs) && theItem != Item.getItemFromBlock(Blocks.spruce_stairs) && theItem != Item.getItemFromBlock(Blocks.birch_stairs) && theItem != Item.getItemFromBlock(Blocks.jungle_stairs) && theItem != Item.getItemFromBlock(Blocks.acacia_stairs) && theItem != Item.getItemFromBlock(Blocks.dark_oak_stairs) && theItem != Item.getItemFromBlock(Blocks.stone_stairs) && theItem != Item.getItemFromBlock(Blocks.sandstone_stairs) && theItem != Item.getItemFromBlock(Blocks.nether_brick_stairs) && theItem != Item.getItemFromBlock(Blocks.quartz_stairs) && theItem != Item.getItemFromBlock(Blocks.stone_brick_stairs) && theItem != Item.getItemFromBlock(Blocks.brick_stairs)?(theItem != Items.paper && theItem != Item.getItemFromBlock(Blocks.wooden_slab) && theItem != Item.getItemFromBlock(Blocks.stone_slab)?(theItem != Item.getItemFromBlock(Blocks.iron_bars) && theItem != Item.getItemFromBlock(Blocks.rail) && theItem != Item.getItemFromBlock(Blocks.golden_rail) && theItem != Item.getItemFromBlock(Blocks.activator_rail) && theItem != Item.getItemFromBlock(Blocks.detector_rail) && theItem != Item.getItemFromBlock(Blocks.glass_pane) && theItem != Item.getItemFromBlock(Blocks.stained_glass_pane)?parDefaultQuantity:8):2):1;
         }
      } else {
         System.out.println("Trying to deconstruct unrealistic item when not allowed");
         return 0;
      }
   }
}
