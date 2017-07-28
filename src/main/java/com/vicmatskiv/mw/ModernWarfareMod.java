package com.vicmatskiv.mw;

import com.vicmatskiv.mw.AmmoTab;
import com.vicmatskiv.mw.ArmorTab;
import com.vicmatskiv.mw.AssaultRiflesTab;
import com.vicmatskiv.mw.AttachmentsTab;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.FunGunsTab;
import com.vicmatskiv.mw.GadgetsTab;
import com.vicmatskiv.mw.GrenadesTab;
import com.vicmatskiv.mw.GunsTab;
import com.vicmatskiv.mw.PistolsTab;
import com.vicmatskiv.mw.RecipeManager;
import com.vicmatskiv.mw.SMGTab;
import com.vicmatskiv.mw.ShotgunsTab;
import com.vicmatskiv.mw.SnipersTab;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import java.io.File;
import javax.xml.transform.stream.StreamSource;
import net.minecraft.creativetab.CreativeTabs;

@Mod(
   modid = "mw",
   version = "1.10.3"
)
public class ModernWarfareMod {
   private static final String DEFAULT_CONFIG_RESOURCE = "/mw.cfg";
   private static final String MODERN_WARFARE_CONFIG_FILE_NAME = "ModernWarfare.cfg";
   public static final String MODID = "mw";
   public static final String VERSION = "1.10.3";
   @SidedProxy(
      serverSide = "com.vicmatskiv.weaponlib.CommonModContext",
      clientSide = "com.vicmatskiv.weaponlib.ClientModContext"
   )
   public static ModContext MOD_CONTEXT;
   public static final SimpleNetworkWrapper CHANNEL;
   public static CreativeTabs gunsTab;
   public static CreativeTabs ArmorTab;
   public static CreativeTabs AssaultRiflesTab;
   public static CreativeTabs PistolsTab;
   public static CreativeTabs SMGTab;
   public static CreativeTabs ShotgunsTab;
   public static CreativeTabs SnipersTab;
   public static CreativeTabs AmmoTab;
   public static CreativeTabs AttachmentsTab;
   public static CreativeTabs GrenadesTab;
   public static CreativeTabs GadgetsTab;
   public static CreativeTabs FunGunsTab;
   @SidedProxy(
      serverSide = "com.vicmatskiv.mw.CommonProxy",
      clientSide = "com.vicmatskiv.mw.ClientProxy"
   )
   public static CommonProxy proxy;
   public static boolean oreGenerationEnabled;
   private ConfigurationManager configurationManager;

   @EventHandler
   public void preInit(FMLPreInitializationEvent event) {
      this.initConfigurationManager(event);
      proxy.init(this, this.configurationManager, new CompatibleFmlInitializationEvent(event));
   }

   @EventHandler
   public void postInit(FMLPostInitializationEvent event) {
      if(this.configurationManager != null) {
         this.configurationManager.save();
      }

   }

   private void initConfigurationManager(FMLPreInitializationEvent event) {
      File parentDirectory = event.getSuggestedConfigurationFile().getParentFile();
      File configFile;
      if(parentDirectory != null) {
         configFile = new File(parentDirectory, "ModernWarfare.cfg");
      } else {
         configFile = new File("ModernWarfare.cfg");
      }

      this.configurationManager = (new ConfigurationManager.Builder()).withUserConfiguration(configFile).withDefaultConfiguration(new StreamSource(this.getClass().getResourceAsStream("/mw.cfg"))).build();
   }

   @EventHandler
   public void init(FMLInitializationEvent event) {
      RecipeManager.init(MOD_CONTEXT);
   }

   static {
      CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel("mw");
      gunsTab = new GunsTab(CreativeTabs.getNextID(), "guns_tab");
      ArmorTab = new ArmorTab(CreativeTabs.getNextID(), "ArmorTab");
      AssaultRiflesTab = new AssaultRiflesTab(CreativeTabs.getNextID(), "AssaultRifles_tab");
      PistolsTab = new PistolsTab(CreativeTabs.getNextID(), "Pistols_tab");
      SMGTab = new SMGTab(CreativeTabs.getNextID(), "SMG_tab");
      ShotgunsTab = new ShotgunsTab(CreativeTabs.getNextID(), "ShotgunsTab");
      SnipersTab = new SnipersTab(CreativeTabs.getNextID(), "SnipersTab");
      AmmoTab = new AmmoTab(CreativeTabs.getNextID(), "AmmoTab");
      AttachmentsTab = new AttachmentsTab(CreativeTabs.getNextID(), "AttachmentsTab");
      GrenadesTab = new GrenadesTab(CreativeTabs.getNextID(), "GrenadesTab");
      GadgetsTab = new GadgetsTab(CreativeTabs.getNextID(), "GadgetsTab");
      FunGunsTab = new FunGunsTab(CreativeTabs.getNextID(), "FunGuns_tab");
      oreGenerationEnabled = true;
   }
}
