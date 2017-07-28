package com.vicmatskiv.weaponlib.crafting;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.crafting.RecipeGenerator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecipeManager {
   private static final Logger logger = LogManager.getLogger(RecipeManager.class);
   private RecipeGenerator recipeGenerator = new RecipeGenerator();
   private Map recipes = new HashMap();

   public List createShapedRecipe(Item item, String name, OptionsMetadata optionsMetadata) {
      List recipe = this.recipeGenerator.createShapedRecipe(name, optionsMetadata);
      if(this.recipes.put(item, recipe) != null) {
         logger.warn("Duplicate recipe registered for item {}", new Object[]{item});
      }

      return recipe;
   }

   public List registerShapedRecipe(Item item, Object... recipe) {
      return this.registerShapedRecipe(new ItemStack(item), recipe);
   }

   public List registerShapedRecipe(ItemStack itemStack, Object... recipe) {
      ArrayList recipeAslist = new ArrayList(recipe.length);
      boolean hasOres = false;
      Object[] var5 = recipe;
      int var6 = recipe.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Object option = var5[var7];
         if(option instanceof CompatibleBlocks) {
            option = ((CompatibleBlocks)option).getBlock();
         } else if(option instanceof CompatibleItems) {
            option = ((CompatibleItems)option).getItem();
         } else if(option instanceof String) {
            hasOres = true;
         }

         recipeAslist.add(option);
      }

      if(hasOres) {
         CompatibilityProvider.compatibility.addShapedOreRecipe(itemStack, recipeAslist.toArray());
      } else {
         CompatibilityProvider.compatibility.addShapedRecipe(itemStack, recipeAslist.toArray());
      }

      if(this.recipes.put(itemStack.getItem(), recipeAslist) != null) {
         logger.warn("Duplicate recipe registered for item {}", new Object[]{itemStack.getItem()});
      }

      return recipeAslist;
   }

   public List getRecipe(Item item) {
      return (List)this.recipes.get(item);
   }
}
