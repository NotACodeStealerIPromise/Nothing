package com.vicmatskiv.mw;

import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.weaponlib.CustomArmor;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class Armors {
   public static Item Marinechest;
   public static Item Marineboots;
   public static Item Marinehelmet;
   public static Item Spetznazchest;
   public static Item Spetznazboots;
   public static Item Spetznazhelmet;
   public static Item Swatchest;
   public static Item Swatboots;
   public static Item Swathelmet;
   public static Item GasSwatchest;
   public static Item GasSwatboots;
   public static Item GasSwathelmet;
   public static Item Tacticalchest;
   public static Item Tacticalboots;
   public static Item Tacticalhelmet;
   static ArmorMaterial Marine;
   static ArmorMaterial Tactical;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event, boolean isClient) {
      CustomArmor.Builder marineArmorBuilder = (new CustomArmor.Builder()).withModId("mw").withMaterial(Marine).withUnlocalizedName("Marine").withTextureName("Marine").withModelClass("com.vicmatskiv.mw.models.Marine").withHudTextureName("Marine").withCreativeTab(ModernWarfareMod.ArmorTab);
      Marinehelmet = marineArmorBuilder.buildHelmet(isClient);
      Marinechest = marineArmorBuilder.buildChest(isClient);
      Marineboots = marineArmorBuilder.buildBoots(isClient);
      CustomArmor.Builder spetznazArmorBuilder = (new CustomArmor.Builder()).withModId("mw").withMaterial(Marine).withCreativeTab(ModernWarfareMod.ArmorTab).withUnlocalizedName("Spetznaz").withTextureName("Spetznaz").withModelClass("com.vicmatskiv.mw.models.Marine").withHudTextureName("Marine").withCreativeTab(ModernWarfareMod.ArmorTab);
      Spetznazhelmet = spetznazArmorBuilder.buildHelmet(isClient);
      Spetznazchest = spetznazArmorBuilder.buildChest(isClient);
      Spetznazboots = spetznazArmorBuilder.buildBoots(isClient);
      CustomArmor.Builder swatArmorBuilder = (new CustomArmor.Builder()).withModId("mw").withMaterial(Marine).withUnlocalizedName("Swat").withTextureName("Swat").withModelClass("com.vicmatskiv.mw.models.Swat").withHudTextureName("Marine").withCreativeTab(ModernWarfareMod.ArmorTab);
      Swathelmet = swatArmorBuilder.buildHelmet(isClient);
      Swatchest = swatArmorBuilder.buildChest(isClient);
      Swatboots = swatArmorBuilder.buildBoots(isClient);
      CustomArmor.Builder tacticalArmorBuilder = (new CustomArmor.Builder()).withModId("mw").withMaterial(Tactical).withUnlocalizedName("Tactical").withTextureName("Tactical").withModelClass("com.vicmatskiv.mw.models.Tactical").withHudTextureName("Tactical").withCreativeTab(ModernWarfareMod.ArmorTab);
      Tacticalhelmet = tacticalArmorBuilder.buildHelmet(isClient);
      Tacticalchest = tacticalArmorBuilder.buildChest(isClient);
      Tacticalboots = tacticalArmorBuilder.buildBoots(isClient);
   }

   static {
      Marine = CompatibilityProvider.compatibility.addArmorMaterial("Marine", "Marine", 40, new int[]{3, 5, 4, 3}, 15, (CompatibleSound)null, 0.0F);
      Tactical = CompatibilityProvider.compatibility.addArmorMaterial("Tactical", "Tactical", 40, new int[]{2, 4, 3, 2}, 15, (CompatibleSound)null, 0.0F);
   }
}
