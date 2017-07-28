package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.models.ACOG;
import com.vicmatskiv.mw.models.AK47iron;
import com.vicmatskiv.mw.models.AKMiron1;
import com.vicmatskiv.mw.models.AKMiron2;
import com.vicmatskiv.mw.models.AR15Iron;
import com.vicmatskiv.mw.models.AUGScope;
import com.vicmatskiv.mw.models.Acog2;
import com.vicmatskiv.mw.models.AngledGrip;
import com.vicmatskiv.mw.models.Bipod;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.Grip2;
import com.vicmatskiv.mw.models.HP;
import com.vicmatskiv.mw.models.Holo2;
import com.vicmatskiv.mw.models.Holographic;
import com.vicmatskiv.mw.models.Holographic2;
import com.vicmatskiv.mw.models.Kobra;
import com.vicmatskiv.mw.models.LP;
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.mw.models.Laser;
import com.vicmatskiv.mw.models.Laser2;
import com.vicmatskiv.mw.models.Leupold;
import com.vicmatskiv.mw.models.LeupoldReticle;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.PSO1;
import com.vicmatskiv.mw.models.PSO12;
import com.vicmatskiv.mw.models.Reflex;
import com.vicmatskiv.mw.models.Reflex2;
import com.vicmatskiv.mw.models.ScarIron1;
import com.vicmatskiv.mw.models.ScarIron2;
import com.vicmatskiv.mw.models.SpecterSight;
import com.vicmatskiv.mw.models.StubbyGrip;
import com.vicmatskiv.mw.models.Suppressor;
import com.vicmatskiv.mw.models.Suppressor300AACBlackout;
import com.vicmatskiv.mw.models.Suppressor45ACP;
import com.vicmatskiv.mw.models.Suppressor556x39;
import com.vicmatskiv.mw.models.Suppressor556x45;
import com.vicmatskiv.mw.models.Suppressor762x39;
import com.vicmatskiv.mw.models.Suppressor762x51;
import com.vicmatskiv.mw.models.UFCG36Scope;
import com.vicmatskiv.mw.models.VGrip;
import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemScope;
import com.vicmatskiv.weaponlib.LaserBeamRenderer;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.function.BiConsumer;
import org.lwjgl.opengl.GL11;

public class Attachments {
   public static ItemAttachment Reflex;
   public static ItemAttachment Holo2;
   public static ItemAttachment Holographic2;
   public static ItemAttachment Kobra;
   public static ItemAttachment ACOG;
   public static ItemAttachment Specter;
   public static ItemAttachment G36Scope;
   public static ItemAttachment AUGScope;
   public static ItemAttachment Scope;
   public static ItemAttachment HP;
   public static ItemAttachment Leupold;
   public static ItemAttachment PSO1;
   public static ItemAttachment Silencer556x45;
   public static ItemAttachment Silencer762x39;
   public static ItemAttachment Silencer556x39;
   public static ItemAttachment Silencer50BMG;
   public static ItemAttachment Silencer9mm;
   public static ItemAttachment Silencer762x54;
   public static ItemAttachment Silencer762x51;
   public static ItemAttachment Silencer45ACP;
   public static ItemAttachment Silencer12Gauge;
   public static ItemAttachment Silencer65x39;
   public static ItemAttachment Silencer57x38;
   public static ItemAttachment Silencer300AACBlackout;
   public static ItemAttachment Silencer357;
   public static ItemAttachment SilencerMP7;
   public static ItemAttachment Laser;
   public static ItemAttachment Laser2;
   public static ItemAttachment Grip2;
   public static ItemAttachment Grip;
   public static ItemAttachment StubbyGrip;
   public static ItemAttachment VGrip;
   public static ItemAttachment Bipod;
   public static ItemAttachment AKMIron;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      Reflex = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Reflex(), "Reflex.png").withModel(new Reflex2(), "Reflex2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(0.1F, -0.8F, 0.2F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.6F, -0.1F, 1.15F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"A  ", "ORX", "AXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("Reflex").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      ACOG = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.1F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glTranslatef(0.12F, 0.37F, 0.45F);
      }).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new ACOG(), "Acog.png").withModel(new Acog2(), "Acog2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof ACOG) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof ACOG) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof ACOG) {
            GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof ACOG) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"FXA", "ORG", "AXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE, Character.valueOf('F'), CommonProxy.CopperWiring}).withName("Acog").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Specter = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.1F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(2.7F, 2.8F, 2.7F);
         GL11.glTranslatef(-0.06F, 0.275F, 0.56F);
      }).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new SpecterSight(), "SpecterSight.png").withModel(new Acog2(), "Acog2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof SpecterSight) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof SpecterSight) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.25D, 0.25D, 0.25D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof SpecterSight) {
            GL11.glTranslatef(-0.6F, 0.0F, 0.85F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof SpecterSight) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Acog2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"FXA", "ORG", "XAX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE, Character.valueOf('F'), CommonProxy.CopperWiring}).withName("Specter").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Holo2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Holographic(), "Holographic.png").withModel(new Holo2(), "Holo3.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(0.1F, -0.8F, 0.2F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"  A", "XRO", "AXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("Holographic").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Holographic2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Holographic2(), "Holographic2.png").withModel(new Holo2(), "Holo3.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(0.1F, -0.8F, 0.2F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.6F, -0.1F, 0.5F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Holo2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"  A", "XRO", "AXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("Holographic2").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Kobra = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Kobra(), "Kobra.png").withModel(new Reflex2(), "Reflex2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(0.4F, -0.8F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.6F, -0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"X  ", "OGX", "ARX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("Kobra").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      G36Scope = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.1F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.5F, 1.5F, 1.5F);
         GL11.glTranslatef(-0.095F, 0.6F, 0.85F);
      }).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new UFCG36Scope(), "AK12.png").withModel(new Reflex2(), "Holo3.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof UFCG36Scope) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof UFCG36Scope) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof UFCG36Scope) {
            GL11.glTranslatef(-0.6F, -0.7F, 1.2F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof UFCG36Scope) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[0]).withName("G36Scope").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AUGScope = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.1F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.65F, 1.65F, 1.6F);
         GL11.glTranslatef(-0.07F, 0.559F, 1.82F);
      }).withModel(new AUGScope(), "AK12.png").withModel(new LPscope(), "HP2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AUGScope) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AUGScope) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof AUGScope) {
            GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof AUGScope) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withName("AUGScope").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Scope = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.06F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.1F, 1.1F, 1.1F);
         GL11.glTranslatef(0.1F, 0.395F, 0.6F);
      }).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withCrosshair("LP").withModel(new LP(), "AK12.png").withModel(new LPscope(), "HP2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof LP) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof LP) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof LP) {
            GL11.glTranslatef(-0.6F, -0.6F, 0.5F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof LP) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"A R", "OGO", "XXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("LPScope").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Leupold = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.04F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(2.3F, 2.3F, 2.3F);
         GL11.glTranslatef(-0.085F, 0.33F, 1.75F);
      }).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withCrosshair("LP").withModel(new Leupold(), "Leupold.png").withModel(new LeupoldReticle(), "Reticle.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Leupold) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof LeupoldReticle) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Leupold) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof LeupoldReticle) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Leupold) {
            GL11.glTranslatef(-0.6F, -0.45F, 0.94F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         } else if(model instanceof LeupoldReticle) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Leupold) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof LeupoldReticle) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"ARR", "OGO", "XXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withName("Leupold").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      PSO1 = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.06F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.05F, 1.05F, 1.05F);
         GL11.glTranslatef(-0.32F, 0.168F, 1.2F);
      }).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withCrosshair("LP").withModel(new PSO1(), "AK12.png").withModel(new PSO12(), "AK12.png").withModel(new LPscope(), "HP2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof PSO1) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof PSO12) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof PSO1) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof PSO12) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof PSO1) {
            GL11.glTranslatef(-0.6F, -0.3F, 0.7F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof PSO12) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof PSO1) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof PSO12) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withName("PSO1").withCraftingRecipe(new Object[]{"ARR", "XXX", "OGO", "  X", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      HP = (new ItemScope.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.02F).withViewfinderPositioning((p, s) -> {
         GL11.glScalef(1.65F, 1.65F, 1.65F);
         GL11.glTranslatef(0.0285F, 0.492F, 0.7F);
      }).withCreativeTab(ModernWarfareMod.AttachmentsTab).withCrosshair("HP").withModel(new HP(), "AK12.png").withModel(new LPscope(), "HP2.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof HP) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof HP) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof HP) {
            GL11.glTranslatef(-0.6F, -0.6F, 0.6F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof HP) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof LPscope) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"ARX", "ORO", "AXX", Character.valueOf('R'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('X'), "ingotSteel", Character.valueOf('O'), CommonProxy.OpticGlass}).withName("HPScope").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer556x45 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor556x45(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x45) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x45) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x45) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x45) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer556x45").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer762x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor762x39(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x39) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x39) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x39) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x39) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer762x39").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer9mm = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "GunmetalTexture.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer9mm").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer45ACP = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor45ACP(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer45ACP").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer762x54 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer762x54").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer762x51 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor762x51(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x51) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x51) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x51) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor762x51) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer762x51").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer50BMG = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "GunmetalTexture.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer50BMG").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer556x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor556x39(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer556x39").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      AKMIron = (new AttachmentBuilder()).withCategory(AttachmentCategory.SCOPE).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new AKMiron1(), "AK12.png").withModel(new AKMiron2(), "AK12.png").withModel(new AK47iron(), "AK12.png").withModel(new M4Iron1(), "AK12.png").withModel(new M4Iron2(), "AK12.png").withModel(new P90iron(), "AK12.png").withModel(new G36CIron1(), "AK12.png").withModel(new G36CIron2(), "AK12.png").withModel(new ScarIron1(), "AK12.png").withModel(new ScarIron2(), "AK12.png").withModel(new FALIron(), "AK12.png").withModel(new M14Iron(), "AK12.png").withModel(new MP5Iron(), "AK12.png").withModel(new MP5Iron(), "AK12.png").withInventoryModelPositioning((model, s) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(-0.6F, -0.7F, 0.65F);
            GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-190.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
         } else {
            GL11.glScalef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(0.1F, -0.8F, 0.4F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         } else {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCraftingRecipe(new Object[]{"AAA", Character.valueOf('A'), "ingotSteel"}).withName("AKMIron").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      SilencerMP7 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("SilencerMP7").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer357 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "GunmetalTexture.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer357").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer57x38 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer57x38").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer12Gauge = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor45ACP(), "GunmetalTexture.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor45ACP) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer12Gauge").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer300AACBlackout = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor300AACBlackout(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor300AACBlackout) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor300AACBlackout) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor300AACBlackout) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor300AACBlackout) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer300AACBlackout").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Silencer65x39 = (new AttachmentBuilder()).withCategory(AttachmentCategory.SILENCER).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Suppressor556x39(), "AK12.png").withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.6F, 0.1F, 0.3F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Suppressor556x39) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{"ingotSteel", CommonProxy.SteelPlate}).withName("Silencer65x39").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Laser = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Laser(), "AK12.png").withPostRender(new LaserBeamRenderer((p, s) -> {
         GL11.glTranslatef(-0.2F, 1.4F, 1.8F);
      })).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Laser) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(!(model instanceof AR15Iron)) {
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Laser) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Laser) {
            GL11.glTranslatef(0.6F, -0.3F, 0.65F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.7999999523162842D, 1.7999999523162842D, 1.7999999523162842D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Laser) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCraftingRecipe(new Object[]{" X ", "ARE", "AXX", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('R'), CommonProxy.LaserPointer, Character.valueOf('E'), CommonProxy.ElectronicCircuitBoard}).withName("Laser").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Laser2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Laser2(), "AK12.png").withPostRender(new LaserBeamRenderer((p, s) -> {
         GL11.glTranslatef(-0.2F, 1.3F, 1.8F);
      })).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Laser2) {
            GL11.glTranslatef(0.5F, -1.3F, -0.1F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Laser2) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Laser2) {
            GL11.glTranslatef(0.6F, -0.3F, 0.65F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.7999999523162842D, 1.7999999523162842D, 1.7999999523162842D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Laser2) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         }

      }).withCraftingRecipe(new Object[]{"AXA", "XRE", "AXX", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate, Character.valueOf('R'), CommonProxy.LaserPointer, Character.valueOf('E'), CommonProxy.ElectronicCircuitBoard}).withName("Laser2").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Grip2 = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Grip2(), "AK12.png").withApply((a, i) -> {
         i.setRecoil(i.getWeapon().getRecoil() * 0.6F);
      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Grip2) {
            GL11.glTranslatef(0.7F, -1.2F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Grip2) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Grip2) {
            GL11.glTranslatef(0.6F, 0.3F, -0.5F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.2999999523162842D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Grip2) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withCraftingRecipe(new Object[]{"AXX", " X ", " X ", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate}).withName("Grip2").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Grip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new AngledGrip(), "AK12.png").withApply((a, weapon, player) -> {
         ((Weapon) weapon).changeRecoil(player, 1.5F);
      }).withRemove((attachment, weapon, player) -> {
         ((Weapon) weapon).changeRecoil(player, 1.0F);
      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AngledGrip) {
            GL11.glTranslatef(0.7F, -1.1F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof AngledGrip) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof AngledGrip) {
            GL11.glTranslatef(0.6F, 0.8F, -0.45F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof AngledGrip) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withCraftingRecipe(new Object[]{"X A", " XX", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate}).withName("AngledGrip").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      StubbyGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new StubbyGrip(), "AK12.png").withApply((a, i) -> {
         i.setRecoil(i.getWeapon().getRecoil() * 0.6F);
      }).withRemove((a, i) -> {
         i.setRecoil(i.getWeapon().getRecoil());
      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof StubbyGrip) {
            GL11.glTranslatef(0.7F, -1.2F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof StubbyGrip) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof StubbyGrip) {
            GL11.glTranslatef(0.6F, 0.5F, -0.5F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof StubbyGrip) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withCraftingRecipe(new Object[]{"AXA", " X ", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate}).withName("StubbyGrip").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      VGrip = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new VGrip(), "AK12.png").withApply((a, i) -> {
         i.setRecoil(i.getWeapon().getRecoil() * 0.6F);
      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof VGrip) {
            GL11.glTranslatef(0.7F, -1.1F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof VGrip) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof VGrip) {
            GL11.glTranslatef(0.6F, 0.3F, -0.5F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(1.2999999523162842D, 1.2999999523162842D, 1.2999999523162842D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof VGrip) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withCraftingRecipe(new Object[]{"XAX", " X ", " X ", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate}).withName("VGrip").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
      Bipod = (new AttachmentBuilder()).withCategory(AttachmentCategory.GRIP).withCreativeTab(ModernWarfareMod.AttachmentsTab).withModel(new Bipod(), "AK12.png").withApply((a, i) -> {
         i.setRecoil(i.getWeapon().getRecoil() * 0.4F);
      }).withFirstPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Bipod) {
            GL11.glTranslatef(0.7F, -1.1F, 0.5F);
            GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof Bipod) {
            GL11.glTranslatef(-0.7F, -0.5F, 0.6F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         }

      }).withInventoryModelPositioning((model, itemStack) -> {
         if(model instanceof Bipod) {
            GL11.glTranslatef(0.6F, -0.05F, -0.5F);
            GL11.glRotatef(-180.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         }

      }).withEntityModelPositioning((model, itemStack) -> {
         if(model instanceof Bipod) {
            GL11.glTranslatef(0.1F, 0.2F, 0.4F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         }

      }).withCraftingRecipe(new Object[]{" X ", "A A", "X X", Character.valueOf('X'), "ingotSteel", Character.valueOf('A'), CommonProxy.MiniSteelPlate}).withName("Bipod").withModId("mw").withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT);
   }
}
