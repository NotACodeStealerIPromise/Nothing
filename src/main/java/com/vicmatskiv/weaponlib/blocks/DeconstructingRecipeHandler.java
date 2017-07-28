package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.BlockSmith;
import com.vicmatskiv.weaponlib.blocks.DeconstructingAddedRecipes;
import com.vicmatskiv.weaponlib.blocks.DeconstructingAdjustedRecipes;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public final class DeconstructingRecipeHandler {
   public Item theItem = null;
   public DeconstructingAdjustedRecipes deconstructingAdjustedRecipes = new DeconstructingAdjustedRecipes();

   public ItemStack[] getDeconstructResults(ItemStack parItemStack) {
      System.out.println("Looking for deconstructing a recipe for " + parItemStack.getUnlocalizedName());
      this.theItem = parItemStack.getItem();
      if(DeconstructingAddedRecipes.shouldAddRecipe(this.theItem)) {
         return DeconstructingAddedRecipes.getCraftingGrid(this.theItem);
      } else {
         List listAllRecipes = CraftingManager.getInstance().getRecipeList();

         for(int i = 0; i < listAllRecipes.size(); ++i) {
            IRecipe recipe = (IRecipe)listAllRecipes.get(i);
            if(recipe != null) {
               ItemStack recipeKeyItemStack = recipe.getRecipeOutput();
               if(recipeKeyItemStack != null && recipeKeyItemStack.getUnlocalizedName().equals(parItemStack.getUnlocalizedName())) {
                  return this.getCraftingGrid(recipe);
               }
            }
         }

         return null;
      }
   }

   public ItemStack[] getCraftingGrid(IRecipe parRecipe) {
      ItemStack[] resultItemStackArray = new ItemStack[9];

      for(int shapelessArray = 0; shapelessArray < resultItemStackArray.length; ++shapelessArray) {
         resultItemStackArray[shapelessArray] = null;
      }

      int j;
      if(parRecipe instanceof ShapedRecipes) {
         System.out.println("getCraftingGrid for shaped recipe");
         ShapedRecipes var6 = (ShapedRecipes)parRecipe;

         for(j = 0; j < var6.recipeItems.length; ++j) {
            resultItemStackArray[j] = var6.recipeItems[j];
         }
      }

      if(parRecipe instanceof ShapelessRecipes) {
         System.out.println("getCraftingGrid for shapeless recipe");
         ShapelessRecipes var7 = (ShapelessRecipes)parRecipe;

         for(j = 0; j < var7.recipeItems.size(); ++j) {
            resultItemStackArray[j] = (ItemStack)var7.recipeItems.get(j);
         }
      }

      Object o;
      if(parRecipe instanceof ShapedOreRecipe) {
         System.out.println("getCraftingGrid for shaped ore recipe");
         ShapedOreRecipe var8 = (ShapedOreRecipe)parRecipe;

         for(j = 0; j < var8.getInput().length; ++j) {
            if(var8.getInput()[j] instanceof ItemStack) {
               resultItemStackArray[j] = (ItemStack)var8.getInput()[j];
            } else if(var8.getInput()[j] instanceof List) {
               o = ((List)var8.getInput()[j]).get(0);
               if(o instanceof ItemStack) {
                  resultItemStackArray[j] = (ItemStack)o;
               }
            }
         }
      }

      if(parRecipe instanceof ShapelessOreRecipe) {
         ArrayList var9 = ((ShapelessOreRecipe)parRecipe).getInput();
         System.out.println("getCraftingGrid for shapeless ore recipe with input array size = " + var9.size());

         for(j = 0; j < var9.size(); ++j) {
            if(var9.get(j) instanceof ItemStack) {
               resultItemStackArray[j] = (ItemStack)var9.get(j);
            } else if(var9.get(j) instanceof List) {
               o = ((List)var9.get(j)).get(0);
               if(o instanceof ItemStack) {
                  resultItemStackArray[j] = (ItemStack)o;
               } else {
                  System.out.println("But list element is not an ItemStack");
               }
            }
         }
      }

      return BlockSmith.allowPartialDeconstructing?this.deconstructingAdjustedRecipes.adjustOutputQuantities(resultItemStackArray, parRecipe.getRecipeOutput()):resultItemStackArray;
   }
}
