package com.vicmatskiv.mw;

import com.vicmatskiv.mw.Armors;
import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.Bullets;
import com.vicmatskiv.mw.Electronics;
import com.vicmatskiv.mw.Grenades;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Guns;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.MeleeSkins;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.Ores;
import com.vicmatskiv.mw.WorldGeneratorEventHandler;
import com.vicmatskiv.mw.items.ItemLaserPointer;
import com.vicmatskiv.mw.items.melee.KarambitFactory;
import com.vicmatskiv.mw.items.melee.TestMeleeFactory;
import com.vicmatskiv.mw.parts.ItemCapacitor;
import com.vicmatskiv.mw.parts.ItemCopperWiring;
import com.vicmatskiv.mw.parts.ItemDiode;
import com.vicmatskiv.mw.parts.ItemInductor;
import com.vicmatskiv.mw.parts.ItemResistor;
import com.vicmatskiv.mw.parts.ItemTransistor;
import com.vicmatskiv.mw.resources.ItemAluminumPlate;
import com.vicmatskiv.mw.resources.ItemBigSteelPlate;
import com.vicmatskiv.mw.resources.ItemCloth;
import com.vicmatskiv.mw.resources.ItemElectronics;
import com.vicmatskiv.mw.resources.ItemGreenCloth;
import com.vicmatskiv.mw.resources.ItemMetalComponents;
import com.vicmatskiv.mw.resources.ItemMiniSteelPlate;
import com.vicmatskiv.mw.resources.ItemOpticGlass;
import com.vicmatskiv.mw.resources.ItemPiston;
import com.vicmatskiv.mw.resources.ItemPlastic;
import com.vicmatskiv.mw.resources.ItemSteelPlate;
import com.vicmatskiv.mw.resources.ItemTanCloth;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.item.Item;

public class CommonProxy {
   public static Item ElectronicCircuitBoard;
   public static Item CGrip;
   public static Item OpticGlass;
   public static Item Cloth;
   public static Item TanCloth;
   public static Item GreenCloth;
   public static Item Inductor;
   public static Item Transistor;
   public static Item Resistor;
   public static Item Diode;
   public static Item Capacitor;
   public static Item CopperWiring;
   public static Item Piston;
   public static Item LaserPointer;
   public static Item AluminumPlate;
   public static Item SteelPlate;
   public static Item BigSteelPlate;
   public static Item MiniSteelPlate;
   public static Item MetalComponents;
   public static Item Plastic;

   public ModelBiped getArmorModel(int id) {
      return null;
   }

   protected boolean isClient() {
      return false;
   }

   public void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      ModernWarfareMod.MOD_CONTEXT.init(mod, "mw", configurationManager, new CompatibleChannel(ModernWarfareMod.CHANNEL));
      ModernWarfareMod.MOD_CONTEXT.setChangeZoomSound("OpticZoom");
      ModernWarfareMod.MOD_CONTEXT.setChangeFireModeSound("GunFireModeSwitch");
      ModernWarfareMod.MOD_CONTEXT.setNoAmmoSound("dryfire");
      ModernWarfareMod.MOD_CONTEXT.setExplosionSound("grenadeexplosion");
      ElectronicCircuitBoard = new ItemElectronics();
      OpticGlass = new ItemOpticGlass();
      Cloth = new ItemCloth();
      TanCloth = new ItemTanCloth();
      GreenCloth = new ItemGreenCloth();
      Inductor = new ItemInductor();
      Resistor = new ItemResistor();
      Transistor = new ItemTransistor();
      Diode = new ItemDiode();
      Capacitor = new ItemCapacitor();
      CopperWiring = new ItemCopperWiring();
      Piston = new ItemPiston();
      LaserPointer = new ItemLaserPointer();
      Plastic = new ItemPlastic();
      AluminumPlate = new ItemAluminumPlate();
      SteelPlate = new ItemSteelPlate();
      BigSteelPlate = new ItemBigSteelPlate();
      MiniSteelPlate = new ItemMiniSteelPlate();
      MetalComponents = new ItemMetalComponents();
      CompatibilityProvider.compatibility.registerItem("mw", ElectronicCircuitBoard, "Electronics");
      CompatibilityProvider.compatibility.registerItem("mw", OpticGlass, "OpticGlass");
      CompatibilityProvider.compatibility.registerItem("mw", Cloth, "Cloth");
      CompatibilityProvider.compatibility.registerItem("mw", TanCloth, "TanCloth");
      CompatibilityProvider.compatibility.registerItem("mw", GreenCloth, "GreenCloth");
      CompatibilityProvider.compatibility.registerItem("mw", AluminumPlate, "AluminumPlate");
      CompatibilityProvider.compatibility.registerItem("mw", SteelPlate, "SteelPlate");
      CompatibilityProvider.compatibility.registerItem("mw", BigSteelPlate, "BigSteelPlate");
      CompatibilityProvider.compatibility.registerItem("mw", MiniSteelPlate, "MiniSteelPlate");
      CompatibilityProvider.compatibility.registerItem("mw", MetalComponents, "MetalComponents");
      CompatibilityProvider.compatibility.registerItem("mw", Transistor, "Transistor");
      CompatibilityProvider.compatibility.registerItem("mw", Resistor, "Resistor");
      CompatibilityProvider.compatibility.registerItem("mw", Inductor, "Inductor");
      CompatibilityProvider.compatibility.registerItem("mw", Diode, "Diode");
      CompatibilityProvider.compatibility.registerItem("mw", Capacitor, "Capacitor");
      CompatibilityProvider.compatibility.registerItem("mw", CopperWiring, "CopperWiring");
      CompatibilityProvider.compatibility.registerItem("mw", Piston, "Piston");
      CompatibilityProvider.compatibility.registerItem("mw", LaserPointer, "LaserPointer");
      CompatibilityProvider.compatibility.registerItem("mw", Plastic, "plastic");
      Ores.init(mod, configurationManager, event);
      Armors.init(mod, configurationManager, event, this.isClient());
      Attachments.init(mod, configurationManager, event);
      AuxiliaryAttachments.init(mod, configurationManager, event);
      GunSkins.init(mod, configurationManager, event);
      MeleeSkins.init(mod, configurationManager, event);
      Bullets.init(mod, configurationManager, event);
      Magazines.init(mod, configurationManager, event);
      Guns.init(mod, configurationManager, event, this);
      Electronics.init(mod, configurationManager, event);
      Grenades.init(mod, configurationManager, event, this);
      (new TestMeleeFactory()).createMelee(this);
      (new KarambitFactory()).createMelee(this);
      CompatibilityProvider.compatibility.registerWorldGenerator(new WorldGeneratorEventHandler(configurationManager), 0);
   }
}
