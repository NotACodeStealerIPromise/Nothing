package com.vicmatskiv.weaponlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DeconstructingAdjustedRecipes {
   public int divideByTwoCounter = 1;
   public int divideByThreeCounter = 2;
   public int divideByFourCounter = 3;
   public int divideByEightCounter = 7;
   public Item theItem = null;
   public int theMetadata = 0;

   public ItemStack[] adjustOutputQuantities(ItemStack[] parOutputItemStackArray, ItemStack parInputItemStack) {
      this.theItem = parInputItemStack.getItem();
      this.theMetadata = this.theItem.getMetadata(0);
      throw new IllegalStateException("Implement me");
   }

   private ItemStack[] outputSingle(Block parBlock) {
      return new ItemStack[]{new ItemStack(Item.getItemFromBlock(parBlock)), null, null, null, null, null, null, null, null};
   }

   private ItemStack[] outputSingle(Item parItem) {
      return new ItemStack[]{new ItemStack(parItem), null, null, null, null, null, null, null, null};
   }

   private ItemStack[] outputSingle(Block parBlock, int parMetadata) {
      return new ItemStack[]{new ItemStack(Item.getItemFromBlock(parBlock), 1, parMetadata), null, null, null, null, null, null, null, null};
   }

   private ItemStack[] outputSingle(Item parItem, int parMetadata) {
      return new ItemStack[]{new ItemStack(parItem, 1, parMetadata), null, null, null, null, null, null, null, null};
   }

   private ItemStack[] outputForWoodenDoor(int parMetadata) {
      return new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.planks), 1, parMetadata), new ItemStack(Item.getItemFromBlock(Blocks.planks), 1, parMetadata), null, null, null, null, null, null, null};
   }

   private ItemStack[] outputForWoodenFence(int parMetadata) {
      ItemStack[] resultItemStackArray = this.initItemStackArray();
      ItemStack planksItemStack = new ItemStack(Item.getItemFromBlock(Blocks.planks), 1, parMetadata);
      if(this.divideByThreeCounter == 2) {
         this.decrementDivideByThreeCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, planksItemStack, new ItemStack(Items.stick, 1, 0), null, null, null, null};
      } else if(this.divideByThreeCounter == 1) {
         this.decrementDivideByThreeCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, null, null, null, null, new ItemStack(Items.stick, 1, 0), planksItemStack};
      } else if(this.divideByThreeCounter == 0) {
         this.decrementDivideByThreeCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, null, null, planksItemStack, planksItemStack, null, null};
      }

      return resultItemStackArray;
   }

   private ItemStack[] outputForStoneSlab() {
      ItemStack[] resultItemStackArray = this.initItemStackArray();
      if((this.theMetadata & 7) == 0) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.stone), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 1) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.sandstone), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 2) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.stone), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 3) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 4) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.brick_block), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 5) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.stonebrick), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 6) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.nether_brick), 1, 0), null, null, null, null, null, null, null, null};
      } else if((this.theMetadata & 7) == 7) {
         resultItemStackArray = new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.quartz_block), 1, 0), null, null, null, null, null, null, null, null};
      }

      return resultItemStackArray;
   }

   private ItemStack[] outputForQuartz() {
      ItemStack[] resultItemStackArray = this.initItemStackArray();
      if(this.theMetadata == 0) {
         resultItemStackArray = new ItemStack[]{null, null, null, new ItemStack(Items.quartz, 1, 0), new ItemStack(Items.quartz, 1, 0), null, new ItemStack(Items.quartz, 1, 0), new ItemStack(Items.quartz, 1, 0), null};
      } else if(this.theMetadata == 1) {
         resultItemStackArray = new ItemStack[]{null, null, null, null, new ItemStack(Item.getItemFromBlock(Blocks.stone_slab), 1, 7), null, null, new ItemStack(Item.getItemFromBlock(Blocks.stone_slab), 1, 7), null};
      } else if(this.theMetadata == 2 || this.theMetadata == 3 || this.theMetadata == 4) {
         if(this.divideByTwoCounter == 1) {
            this.decrementDivideByTwoCounter();
            resultItemStackArray = new ItemStack[]{null, null, null, null, null, null, null, new ItemStack(Item.getItemFromBlock(Blocks.quartz_block), 1, 0), null};
         } else if(this.divideByTwoCounter == 0) {
            this.decrementDivideByTwoCounter();
            resultItemStackArray = new ItemStack[]{null, null, null, null, new ItemStack(Item.getItemFromBlock(Blocks.quartz_block), 1, 0), null, null, null, null};
         }
      }

      return resultItemStackArray;
   }

   private ItemStack[] outputForHardenedClay() {
      if(this.divideByEightCounter != 3) {
         this.decrementDivideByEightCounter();
         return new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.clay), 1, 0), null, null, null, null, null, null, null, null};
      } else {
         System.out.println("Should output a dye");
         this.decrementDivideByEightCounter();
         return new ItemStack[]{new ItemStack(Item.getItemFromBlock(Blocks.clay), 1, 0), new ItemStack(Items.dye, 1, this.convertClayMetaToDyeMeta(this.theMetadata)), null, null, null, null, null, null, null};
      }
   }

   private ItemStack[] outputForStairs(ItemStack parOutputItemStack) {
      ItemStack[] resultItemStackArray = this.initItemStackArray();
      if(this.divideByFourCounter == 0) {
         this.decrementDivideByFourCounter();
         resultItemStackArray = new ItemStack[]{null, null, parOutputItemStack, null, null, null, null, null, null};
      } else if(this.divideByFourCounter == 1) {
         this.decrementDivideByFourCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, null, parOutputItemStack, parOutputItemStack, null, null, null};
      } else if(this.divideByFourCounter == 2) {
         this.decrementDivideByFourCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, null, null, null, null, null, parOutputItemStack};
      } else if(this.divideByFourCounter == 3) {
         this.decrementDivideByFourCounter();
         resultItemStackArray = new ItemStack[]{null, null, null, null, null, null, parOutputItemStack, parOutputItemStack, null};
      }

      return resultItemStackArray;
   }

   private int convertClayMetaToDyeMeta(int parClayMeta) {
      return 15 - parClayMeta;
   }

   private void decrementDivideByTwoCounter() {
      --this.divideByTwoCounter;
      if(this.divideByTwoCounter < 0) {
         this.divideByTwoCounter = 1;
      }

   }

   private void decrementDivideByThreeCounter() {
      --this.divideByThreeCounter;
      if(this.divideByThreeCounter < 0) {
         this.divideByThreeCounter = 2;
      }

   }

   private void decrementDivideByFourCounter() {
      --this.divideByFourCounter;
      if(this.divideByFourCounter < 0) {
         this.divideByFourCounter = 3;
      }

   }

   private void decrementDivideByEightCounter() {
      --this.divideByEightCounter;
      if(this.divideByEightCounter < 0) {
         this.divideByEightCounter = 7;
      }

   }

   private ItemStack[] initItemStackArray() {
      ItemStack[] resultItemStackArray = new ItemStack[9];

      for(int j = 0; j < resultItemStackArray.length; ++j) {
         resultItemStackArray[j] = null;
      }

      return resultItemStackArray;
   }
}
