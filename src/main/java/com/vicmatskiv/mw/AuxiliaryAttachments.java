package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.models.ACRAction;
import com.vicmatskiv.mw.models.ACRAction2;
import com.vicmatskiv.mw.models.AK12IronSight;
import com.vicmatskiv.mw.models.AK12action;
import com.vicmatskiv.mw.models.AK47iron;
import com.vicmatskiv.mw.models.AKMiron1;
import com.vicmatskiv.mw.models.AKMiron2;
import com.vicmatskiv.mw.models.AKRail;
import com.vicmatskiv.mw.models.AKRail2;
import com.vicmatskiv.mw.models.AKRail3;
import com.vicmatskiv.mw.models.AKRail4;
import com.vicmatskiv.mw.models.AKRail5;
import com.vicmatskiv.mw.models.AKS74UIron;
import com.vicmatskiv.mw.models.AKaction;
import com.vicmatskiv.mw.models.AKiron3;
import com.vicmatskiv.mw.models.AKpart;
import com.vicmatskiv.mw.models.AN94action;
import com.vicmatskiv.mw.models.AR15Action;
import com.vicmatskiv.mw.models.AR15CarryHandle;
import com.vicmatskiv.mw.models.AR15Iron;
import com.vicmatskiv.mw.models.AUGAction;
import com.vicmatskiv.mw.models.DeagleTop;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.FNP90Sight;
import com.vicmatskiv.mw.models.FamasAction;
import com.vicmatskiv.mw.models.FamasBipod;
import com.vicmatskiv.mw.models.FamasCarryHandle;
import com.vicmatskiv.mw.models.FelinAction;
import com.vicmatskiv.mw.models.FelinCarryHandle;
import com.vicmatskiv.mw.models.G36Action;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.G36Rail;
import com.vicmatskiv.mw.models.GlockTop;
import com.vicmatskiv.mw.models.Grip2;
import com.vicmatskiv.mw.models.HecateIIBoltAction;
import com.vicmatskiv.mw.models.KSG12Pump;
import com.vicmatskiv.mw.models.L115Bolt2;
import com.vicmatskiv.mw.models.L96Action;
import com.vicmatskiv.mw.models.M14Action;
import com.vicmatskiv.mw.models.M14Action2;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M14Rail;
import com.vicmatskiv.mw.models.M1911Top;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.M9Top;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.MagnumCase;
import com.vicmatskiv.mw.models.MakarovTop;
import com.vicmatskiv.mw.models.MosinBolt;
import com.vicmatskiv.mw.models.P2000Top;
import com.vicmatskiv.mw.models.P225Top;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.R870Pump;
import com.vicmatskiv.mw.models.Reflex2;
import com.vicmatskiv.mw.models.SV98Action;
import com.vicmatskiv.mw.models.ScarAction;
import com.vicmatskiv.mw.models.ScarIron1;
import com.vicmatskiv.mw.models.ScarIron2;
import com.vicmatskiv.mw.models.Suppressor;
import com.vicmatskiv.mw.models.USP45Top;
import com.vicmatskiv.mw.models.VSSVintorezAction;
import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import java.util.function.BiConsumer;
import org.lwjgl.opengl.GL11;

public class AuxiliaryAttachments {
   public static ItemAttachment FNP90Sight;
   public static ItemAttachment AR15Iron;
   public static ItemAttachment Extra;
   public static ItemAttachment ExtraAR;
   public static ItemAttachment GlockTop;
   public static ItemAttachment G18Top;
   public static ItemAttachment M9Top;
   public static ItemAttachment P2000Top;
   public static ItemAttachment DeagleTop;
   public static ItemAttachment Deagle44Top;
   public static ItemAttachment KSGPump;
   public static ItemAttachment L115Bolt1;
   public static ItemAttachment L115Bolt2;
   public static ItemAttachment SV98Action;
   public static ItemAttachment RevolverCase;
   public static ItemAttachment PythonCase;
   public static ItemAttachment R870Pump;
   public static ItemAttachment M1911Top;
   public static ItemAttachment M9SDsuppressor;
   public static ItemAttachment MosinBolt;
   public static ItemAttachment USP45Top;
   public static ItemAttachment MakarovTop;
   public static ItemAttachment AK12IronSight;
   public static ItemAttachment M14Rail;
   public static ItemAttachment P225Top;
   public static ItemAttachment P226Top;
   public static ItemAttachment P30Top;
   public static ItemAttachment MP5KGrip;
   public static ItemAttachment HecateIIBoltAction;
   public static ItemAttachment AR15Action;
   public static ItemAttachment BushmasterACRAction;
   public static ItemAttachment RemingtonACRAction;
   public static ItemAttachment AKIron;
   public static ItemAttachment AKpart;
   public static ItemAttachment AKaction;
   public static ItemAttachment AN94action;
   public static ItemAttachment VSSVintorezAction;
   public static ItemAttachment AK12action;
   public static ItemAttachment AKS74UIron;
   public static ItemAttachment AKRail;
   public static ItemAttachment AUGRail;
   public static ItemAttachment BushmasterACRRail;
   public static ItemAttachment RemingtonACRRail;
   public static ItemAttachment M4Rail;
   public static ItemAttachment ScarAction;
   public static ItemAttachment G36Rail;
   public static ItemAttachment G36Action;
   public static ItemAttachment FamasCarryHandle;
   public static ItemAttachment FamasAction;
   public static ItemAttachment AUGAction;
   public static ItemAttachment FamasBipod1;
   public static ItemAttachment FamasBipod2;
   public static ItemAttachment FelinAction;
   public static ItemAttachment FelinCarryHandle;
   public static ItemAttachment M14Action;
   public static ItemAttachment M14Action2;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      AR15Iron = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new M4Iron1(), "AK12.png").withModel(new M4Iron2(), "AK12.png").withModel(new FALIron(), "AK12.png").withModel(new AR15CarryHandle(), "AK12.png").withInventoryModelPositioning((model, s) -> {
         if(model instanceof AR15CarryHandle) {
            GL11.glTranslatef(-0.6F, 0.0F, 0.2F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.699999988079071D, 0.75D);
         } else {
            GL11.glScalef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AR15CarryHandle) {
            GL11.glTranslatef(0.1F, 0.0F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.5D, 0.699999988079071D, 0.699999988079071D);
         } else {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AR15CarryHandle) {
            GL11.glTranslatef(-1.6F, -0.5F, 1.2F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.30000001192092896D, 0.5D, 0.5D);
         } else {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{" AA", "F F", Character.valueOf('A'), "ingotSteel", Character.valueOf('F'), CommonProxy.SteelPlate}).withName("AR15Iron").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Extra = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA6).withModel(new AKMiron1(), "GunmetalTexture.png").withModel(new AKMiron2(), "GunmetalTexture.png").withModel(new AK47iron(), "GunmetalTexture.png").withModel(new M4Iron1(), "GunmetalTexture.png").withModel(new M4Iron2(), "GunmetalTexture.png").withModel(new P90iron(), "GunmetalTexture.png").withModel(new G36CIron1(), "GunmetalTexture.png").withModel(new G36CIron2(), "GunmetalTexture.png").withModel(new ScarIron1(), "GunmetalTexture.png").withModel(new ScarIron2(), "GunmetalTexture.png").withModel(new FALIron(), "GunmetalTexture.png").withModel(new M14Iron(), "GunmetalTexture.png").withModel(new MP5Iron(), "AK12.png").withName("Extra").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      ExtraAR = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new AR15Iron(), "AK12.png").withModel(new FALIron(), "AK12.png").withModel(new M4Iron1(), "AK12.png").withModel(new M4Iron2(), "AK12.png").withName("ExtraAR").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      GlockTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new GlockTop(), "GlockTop.png").withName("GlockTop").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      G18Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new GlockTop(), "G18Top.png").withName("G18Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M9Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new M9Top(), "M9Top.png").withName("M9Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AK12IronSight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new AK12IronSight(), "GunmetalTexture.png").withName("AK12IronSight").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M9SDsuppressor = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new Suppressor(), "GunmetalTexture.png").withName("M9SDsuppressor").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      P2000Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new P2000Top(), "P2000Top.png").withName("P2000Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      DeagleTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new DeagleTop(), "Deagle.png").withName("DeagleTop").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Deagle44Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new DeagleTop(), "Deagle44.png").withName("Deagle44Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      KSGPump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new KSG12Pump(), "GunmetalTexture.png").withName("KSGPump").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      L115Bolt1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new L96Action(), "L96Action.png").withName("L96Action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      SV98Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new SV98Action(), "SV98Action.png").withName("SV98Action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      L115Bolt2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new L115Bolt2(), "AK12.png").withName("LP115Bolt2").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      MosinBolt = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new MosinBolt(), "NATOMag1.png").withName("MosinBolt").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      RevolverCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new MagnumCase(), "MagnumCase.png").withName("RevolverCase").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      PythonCase = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new MagnumCase(), "PythonCase.png").withName("PythonCase").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      R870Pump = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new R870Pump(), "Remington.png").withName("R870Pump").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M1911Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new M1911Top(), "M1911.png").withName("M1911Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      USP45Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new USP45Top(), "USP45Top.png").withName("USP45Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      MakarovTop = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new MakarovTop(), "MakarovPM.png").withName("MakarovTop").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FNP90Sight = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new FNP90Sight(), "AK12.png").withModel(new Reflex2(), "Reflex2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof FNP90Sight) {
            GL11.glTranslatef(0.1F, -0.8F, 0.2F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof FNP90Sight) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof FNP90Sight) {
            GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof FNP90Sight) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withName("FNP90Sight").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M14Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new M14Rail(), "GunmetalTexture.png").withName("M14Rail").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M14Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new M14Action(), "AK12.png").withName("M14Action").withModId("mw").withTextureName("Dummy.png").withRenderablePart().build(ModernWarfareMod.MOD_CONTEXT);
      M14Action2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new M14Action2(), "AK12.png").withName("M14Action2").withModId("mw").withTextureName("Dummy.png").withRenderablePart().build(ModernWarfareMod.MOD_CONTEXT);
      FamasCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new FamasCarryHandle(), "AK12.png").withName("FamasCarryHandle").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FelinCarryHandle = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new FelinCarryHandle(), "AK12.png").withName("FelinCarryHandle").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      P30Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new P2000Top(), "P30Top.png").withName("P30Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      P225Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new P225Top(), "P225Top.png").withName("P225Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      P226Top = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new P225Top(), "P226Top.png").withName("P226Top").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      MP5KGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new Grip2(), "GunmetalTexture.png").withName("MP5KGrip").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      HecateIIBoltAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new HecateIIBoltAction(), "AK12.png").withName("HecateIIBoltAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AR15Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new AR15Action(), "AK12.png").withName("AR15Action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      BushmasterACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new ACRAction(), "AK12.png").withModel(new ACRAction2(), "AK12.png").withName("BushmasterACRAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      RemingtonACRAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA).withModel(new ACRAction(), "ACR.png").withModel(new ACRAction2(), "AK12.png").withName("RemingtonACRAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new AKiron3(), "AK12.png").withName("AKIron3").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKpart = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new AKpart(), "AK12.png").withName("AKpart").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKS74UIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new AKS74UIron(), "AK12.png").withName("AKS74UIron").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5).withModel(new AKRail(), "AK12.png").withName("AKRail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AUGRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5).withModel(new AKRail(), "AK12.png").withModel(new AKRail2(), "AK12.png").withModel(new AKRail3(), "AK12.png").withModel(new AKRail4(), "AK12.png").withName("AUGRail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      BushmasterACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5).withModel(new AKRail(), "AK12.png").withModel(new AKRail2(), "AK12.png").withModel(new AKRail3(), "AK12.png").withModel(new AKRail4(), "AK12.png").withModel(new AKRail5(), "AK12.png").withName("BushmasterACRRail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      RemingtonACRRail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5).withModel(new AKRail(), "ACR.png").withModel(new AKRail2(), "ACR.png").withModel(new AKRail3(), "ACR.png").withModel(new AKRail4(), "ACR.png").withModel(new AKRail5(), "ACR.png").withName("RemingtonACRRail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      M4Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA5).withModel(new AKRail(), "AK12.png").withModel(new AKRail2(), "AK12.png").withModel(new AKRail3(), "AK12.png").withModel(new AKRail4(), "AK12.png").withModel(new AKRail5(), "AK12.png").withName("M4Rail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      G36Rail = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new G36Rail(), "AK12.png").withName("G36Rail").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKaction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new AKaction(), "AK12.png").withName("AKaction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AN94action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new AN94action(), "AK12.png").withName("AN94action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      VSSVintorezAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new VSSVintorezAction(), "AK12.png").withName("VSSVintorezAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AK12action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new AK12action(), "AK12.png").withName("AK12action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      ScarAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new ScarAction(), "AK12.png").withName("ScarAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      G36Action = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new G36Action(), "AK12.png").withName("G36Action").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FamasAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new FamasAction(), "AK12.png").withName("FamasAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AUGAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new AUGAction(), "AK12.png").withName("AUGAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FelinAction = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA4).withModel(new FelinAction(), "AK12.png").withName("FelinAction").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FamasBipod1 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA2).withModel(new FamasBipod(), "AK12.png").withName("FamasBipod1").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      FamasBipod2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.EXTRA3).withModel(new FamasBipod(), "AK12.png").withName("FamasBipod2").withRenderablePart().withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
   }
}
