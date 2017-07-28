package com.vicmatskiv.mw;

import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.melee.MeleeSkin;

public class MeleeSkins {
   public static MeleeSkin CrimsonBlood;
   public static MeleeSkin Chrome;
   public static MeleeSkin GodWillsIt;
   public static MeleeSkin Murasaki;
   public static MeleeSkin Evangelion;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      CrimsonBlood = (MeleeSkin)(new MeleeSkin.Builder()).withTextureVariant(new String[]{"TacKnifeCrimsonBlood"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("CrimsonBlood").withCraftingRecipe(new Object[]{"AR", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 0, 0), Character.valueOf('R'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 1, 1)}).build(ModernWarfareMod.MOD_CONTEXT, MeleeSkin.class);
      Chrome = (MeleeSkin)(new MeleeSkin.Builder()).withTextureVariant(new String[]{"TacKnifeChrome"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Chrome").withCraftingRecipe(new Object[]{"AR", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5), Character.valueOf('R'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 11, 11)}).build(ModernWarfareMod.MOD_CONTEXT, MeleeSkin.class);
      GodWillsIt = (MeleeSkin)(new MeleeSkin.Builder()).withTextureVariant(new String[]{"TacKnifeGodWillsIt"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("GodWillsIt").withCraftingRecipe(new Object[]{"AR", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 11, 11), Character.valueOf('R'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 15, 15)}).build(ModernWarfareMod.MOD_CONTEXT, MeleeSkin.class);
      Murasaki = (MeleeSkin)(new MeleeSkin.Builder()).withTextureVariant(new String[]{"TacKnifeMurasaki"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Murasaki").withCraftingRecipe(new Object[]{"AA", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5)}).build(ModernWarfareMod.MOD_CONTEXT, MeleeSkin.class);
      Evangelion = (MeleeSkin)(new MeleeSkin.Builder()).withTextureVariant(new String[]{"TacKnifeEvangelion"}).withModId("mw").withCreativeTab(ModernWarfareMod.AttachmentsTab).withName("Evangelion").withCraftingRecipe(new Object[]{"AR", Character.valueOf('A'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 5, 5), Character.valueOf('R'), CompatibilityProvider.compatibility.createItemStack(CompatibleItems.DYE, 10, 10)}).build(ModernWarfareMod.MOD_CONTEXT, MeleeSkin.class);
   }
}
