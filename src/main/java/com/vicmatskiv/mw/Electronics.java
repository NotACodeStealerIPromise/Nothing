package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.models.HP;
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.electronics.ItemTablet;
import com.vicmatskiv.weaponlib.electronics.ItemWirelessCamera;
import com.vicmatskiv.weaponlib.model.CameraModel;
import com.vicmatskiv.weaponlib.model.TabletModel;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;

public class Electronics {
   public static ItemAttachment Tablet;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      Tablet = (new ItemTablet.Builder()).withOpticalZoom().withZoomRange(0.22F, 0.02F).withViewfinderPositioning((p, s) -> {
         float scale = 5.9F;
         GL11.glScalef(scale, scale / CompatibilityProvider.compatibility.getAspectRatio(ModernWarfareMod.MOD_CONTEXT), scale);
         GL11.glTranslatef(-0.12F, 0.56F, 0.01F);
      }).withCreativeTab(ModernWarfareMod.GadgetsTab).withCrosshair("HP").withModel(new TabletModel(), "IPad.png").withFirstPersonPositioning((player, itemStack) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.68F, -1.35F, 0.7F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof TabletModel) {
            GL11.glTranslatef(-0.8F, -0.5F, 0.8F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
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

      }).withFirstPersonHandPositioning((c) -> {
         GL11.glRotatef(190.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.05F, -1.7F, 0.05F);
         GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.100000023841858D);
      }, (c) -> {
         GL11.glRotatef(190.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.1F, -0.45F, 0.4F);
         GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.100000023841858D);
      }).withName("tablet").withModId("mw").withTextureName("Dummy.png").withCraftingRecipe(new Object[]{"XXX", "XGX", "XEX", Character.valueOf('X'), "ingotSteel", Character.valueOf('E'), CommonProxy.ElectronicCircuitBoard, Character.valueOf('G'), CompatibleBlocks.GLASS_PANE}).build(ModernWarfareMod.MOD_CONTEXT);
      (new ItemWirelessCamera.Builder()).withModId("mw").withName("wcam").withCreativeTab(ModernWarfareMod.GadgetsTab).withModel(new CameraModel(), "AK12").withCrafting(CraftingComplexity.LOW, new Object[]{CommonProxy.SteelPlate, CommonProxy.ElectronicCircuitBoard}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glRotatef(55.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.1F, -1.6F, 1.0F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withThirdPersonModelPositioning((model, itemStack) -> {
         if(model instanceof CameraModel) {
            GL11.glTranslatef(-0.9F, -0.8F, 0.5F);
            GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
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

      }).withFirstPersonHandPositioning((c) -> {
         GL11.glScalef(0.0F, 0.0F, 0.0F);
      }, (c) -> {
         GL11.glScalef(0.0F, 0.0F, 0.0F);
      }).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
