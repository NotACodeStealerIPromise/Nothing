package com.vicmatskiv.mw;

import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.Ores;
import com.vicmatskiv.weaponlib.ItemSkin;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;

public class GunSkins {
   public static ItemSkin ElectricSkin;
   public static ItemSkin Voltaic;
   public static ItemSkin LightningStrike;
   public static ItemSkin Fade;
   public static ItemSkin Emerald;
   public static ItemSkin Diamond;
   public static ItemSkin Gold;
   public static ItemSkin Sapphire;
   public static ItemSkin Desert;
   public static ItemSkin Forest;
   public static ItemSkin Amber;
   public static ItemSkin Arctic;
   public static ItemSkin Amethyst;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      ElectricSkin = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Electric"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Electric").withCraftingRecipe(new Object[]{"AF", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 0, 0), Character.valueOf('F'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 12, 12)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Voltaic = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"voltaic"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Voltaic").withCraftingRecipe(new Object[]{"AF", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5), Character.valueOf('F'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 8, 8)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      LightningStrike = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"AWPLightningStrike"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withCraftingRecipe(new Object[]{"AF", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5), Character.valueOf('F'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 9, 9)}).withName("LightningStrike").build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Fade = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"G18Fade"}).withTextureVariant(new String[]{"SMAWRuby"}).withTextureVariant(new String[]{"Ruby"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), Ores.Ruby}).withName("Fade").build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Emerald = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Emerald"}).withTextureVariant(new String[]{"KrissVectorEmerald"}).withTextureVariant(new String[]{"MosinNagantEmerald"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Emerald").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibleItems.EMERALD}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Diamond = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Diamond"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Diamond").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibleItems.DIAMOND}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Gold = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Gold"}).withTextureVariant(new String[]{"KrissVectorGold"}).withTextureVariant(new String[]{"MosinNagantGold"}).withTextureVariant(new String[]{"SMAWGold"}).withTextureVariant(new String[]{"HecateIIGold"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Gold").withCraftingRecipe(new Object[]{"AA", "AA", Character.valueOf('A'), CompatibleItems.GOLD_NUGGET}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Sapphire = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Sapphire"}).withTextureVariant(new String[]{"KrissVectorSapphire"}).withTextureVariant(new String[]{"SMAWSapphire"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Sapphire").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 4, 4)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Desert = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Desert"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Desert").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibleBlocks.SAND}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Forest = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Forest"}).withTextureVariant(new String[]{"MosinNagantForest"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Forest").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibleBlocks.LEAVES}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Amber = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Amber"}).withTextureVariant(new String[]{"M14Amber"}).withTextureVariant(new String[]{"HecateIIAmber"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Amber").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 14, 14)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Arctic = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Arctic"}).withTextureVariant(new String[]{"HecateIIArctic"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Arctic").withCraftingRecipe(new Object[]{"AR", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 0, 0), Character.valueOf('R'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 15, 15)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
      Amethyst = (ItemSkin)(new ItemSkin.Builder()).withTextureVariant(new String[]{"Amethyst"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Amethyst").withCraftingRecipe(new Object[]{"A", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5)}).build(ModernWarfareMod.MOD_CONTEXT, ItemSkin.class);
   }
}
