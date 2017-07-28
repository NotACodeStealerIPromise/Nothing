package com.vicmatskiv.mw;

import com.vicmatskiv.mw.Armors;
import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.Guns;
import com.vicmatskiv.mw.Ores;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import net.minecraft.item.ItemStack;

public class RecipeManager {
   public static void init(ModContext modContext) {
      CompatibilityProvider.compatibility.addSmelting(Ores.TitaniumOre, new ItemStack(Ores.TitaniumIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.CopperOre, new ItemStack(Ores.CopperIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.LeadOre, new ItemStack(Ores.LeadIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.TinOre, new ItemStack(Ores.TinIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.BauxiteOre, new ItemStack(Ores.AluminumIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.SiliconOre, new ItemStack(Ores.Silicon), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.TantalumOre, new ItemStack(Ores.TantalumIngot), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.SulfurDust, CompatibilityProvider.compatibility.createItemStack(CompatibleItems.GUNPOWDER, 1, 0), 5.0F);
      CompatibilityProvider.compatibility.addSmelting(Ores.SteelDust, new ItemStack(Ores.SteelIngot), 5.0F);
      modContext.getRecipeManager().registerShapedRecipe(Armors.Marinehelmet, new Object[]{"AAA", "AGA", "X X", Character.valueOf('A'), CommonProxy.TanCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(Armors.Marinechest, new Object[]{"A A", "AAA", "XXX", Character.valueOf('A'), CommonProxy.TanCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(Armors.Marineboots, new Object[]{"A A", "A A", "X X", Character.valueOf('A'), CommonProxy.TanCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(Armors.Spetznazhelmet, new Object[]{"AAA", "AGA", "X X", Character.valueOf('A'), CommonProxy.GreenCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(Armors.Spetznazchest, new Object[]{"A A", "AAA", "XXX", Character.valueOf('A'), CommonProxy.GreenCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(Armors.Spetznazboots, new Object[]{"A A", "A A", "X X", Character.valueOf('A'), CommonProxy.GreenCloth, Character.valueOf('X'), "ingotTitanium"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Cloth, 3), new Object[]{"XAX", "AXA", "XAX", Character.valueOf('X'), CompatibleItems.STRING, Character.valueOf('A'), CompatibleBlocks.WOOL});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.TanCloth, 3), new Object[]{"XAX", "AXA", "XAX", Character.valueOf('X'), CompatibleBlocks.SANDSTONE, Character.valueOf('A'), CommonProxy.Cloth});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.GreenCloth, 3), new Object[]{"XAX", "AXA", "XAX", Character.valueOf('X'), CompatibleBlocks.LEAVES, Character.valueOf('A'), CommonProxy.Cloth});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.ElectronicCircuitBoard), new Object[]{"TAI", "ACA", "RAD", Character.valueOf('I'), CommonProxy.Inductor, Character.valueOf('T'), CommonProxy.Transistor, Character.valueOf('R'), CommonProxy.Resistor, Character.valueOf('D'), CommonProxy.Diode, Character.valueOf('C'), CommonProxy.Capacitor, Character.valueOf('A'), CommonProxy.CopperWiring});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.CGrip), new Object[]{"AAA", "XBX", "  A", Character.valueOf('A'), CommonProxy.SteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('B'), CompatibleBlocks.STONE_BUTTON});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.OpticGlass), new Object[]{"XAX", "AEA", "XAX", Character.valueOf('X'), "ingotTitanium", Character.valueOf('E'), CommonProxy.CopperWiring, Character.valueOf('A'), CompatibleBlocks.GLASS_PANE});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.MiniSteelPlate, 2), new Object[]{"XX", Character.valueOf('X'), "ingotSteel"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.SteelPlate, 2), new Object[]{"XXX", "XXX", Character.valueOf('X'), "ingotSteel"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.BigSteelPlate), new Object[]{"XAX", Character.valueOf('X'), CommonProxy.SteelPlate, Character.valueOf('A'), CommonProxy.MiniSteelPlate});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.MetalComponents), new Object[]{"XA", "AX", Character.valueOf('X'), CommonProxy.SteelPlate, Character.valueOf('A'), "ingotSteel"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(Guns.M9SD), new Object[]{"AX", Character.valueOf('X'), Guns.M9, Character.valueOf('A'), Attachments.Silencer9mm});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.LaserPointer), new Object[]{"XXX", "AAR", "XXX", Character.valueOf('X'), CommonProxy.MiniSteelPlate, Character.valueOf('A'), Ores.Ruby, Character.valueOf('R'), CommonProxy.Capacitor});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Resistor, 3), new Object[]{"A", "X", "A", Character.valueOf('A'), "ingotSteel", Character.valueOf('X'), CompatibleItems.COAL});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Transistor, 3), new Object[]{"AA", "XX", "XX", Character.valueOf('A'), "ingotSteel", Character.valueOf('X'), Ores.Silicon});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Capacitor, 3), new Object[]{"XX", "AA", "RR", Character.valueOf('X'), "ingotAluminium", Character.valueOf('A'), CommonProxy.Plastic, Character.valueOf('R'), "ingotTantalum"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Inductor, 3), new Object[]{" A ", "AXA", " A ", Character.valueOf('X'), CommonProxy.Plastic, Character.valueOf('A'), CommonProxy.CopperWiring});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Diode, 3), new Object[]{"X", "A", "X", Character.valueOf('A'), "ingotSteel", Character.valueOf('X'), Ores.Silicon});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.CopperWiring, 10), new Object[]{"A  ", "A A", "AAA", Character.valueOf('A'), "ingotCopper"});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Plastic, 3), new Object[]{"AX", Character.valueOf('A'), CompatibleItems.COAL, Character.valueOf('X'), CompatibleItems.WATER_BUCKET});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.Piston), new Object[]{"XA ", "AXX", " XE", Character.valueOf('A'), CommonProxy.SteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('E'), CommonProxy.ElectronicCircuitBoard});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(Ores.SteelDust, 4), new Object[]{" X ", "XAX", " X ", Character.valueOf('A'), CompatibleItems.IRON_INGOT, Character.valueOf('X'), CompatibleItems.COAL});
      modContext.getRecipeManager().registerShapedRecipe(new ItemStack(CommonProxy.AluminumPlate, 6), new Object[]{"   ", "XXX", Character.valueOf('X'), "ingotAluminium"});
   }
}
