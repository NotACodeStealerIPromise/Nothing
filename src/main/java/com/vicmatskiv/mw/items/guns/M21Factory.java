package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.AK47iron;
import com.vicmatskiv.mw.models.AKMiron1;
import com.vicmatskiv.mw.models.AKMiron2;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.LeupoldReticle;
import com.vicmatskiv.mw.models.M14Action;
import com.vicmatskiv.mw.models.M14Action2;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M21;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.ScarIron1;
import com.vicmatskiv.mw.models.ScarIron2;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class M21Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("M21").withFireRate(0.4F).withRecoil(2.0F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("M21").withSilencedShootSound("RifleSilencer").withReloadSound("m14reload").withUnloadSound("Unload").withReloadingTime(45L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.06F);
      }).withCreativeTab(ModernWarfareMod.SnipersTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, CommonProxy.MetalComponents, CompatibleBlocks.PLANK}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Sniper", "Damage: 14", "Caliber: 7.62x51mm NATO", "Magazines:", "21rnd 7.62x51mm NATO Magazine", "Fire Rate: Semi"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("Gold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Sapphire, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Sapphire.getTextureVariantIndex("Sapphire"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Emerald, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Emerald.getTextureVariantIndex("Emerald"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.M14DMRMag, (Consumer)((model) -> {
         GL11.glTranslatef(-0.4F, 0.9F, -2.1F);
         GL11.glScaled(1.399999976158142D, 1.5D, 1.5D);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
      })).withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
         if(model instanceof AKMiron1) {
            GL11.glTranslatef(0.125F, -1.8F, -0.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(-0.215F, -1.58F, -1.34F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof AKMiron2) {
            GL11.glTranslatef(0.129F, -1.63F, -5.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(-0.163F, -1.36F, -8.512F);
            GL11.glScaled(0.36000001430511475D, 0.36000001430511475D, 0.699999988079071D);
         } else if(model instanceof AK47iron) {
            GL11.glTranslatef(0.092F, -1.91F, -0.9F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron1) {
            GL11.glTranslatef(0.155F, -1.74F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.26F, -1.55F, -2.35F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof P90iron) {
            GL11.glTranslatef(0.26F, -1.55F, -2.35F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.165F, -1.36F, -1.4F);
            GL11.glScaled(0.23000000417232513D, 0.23000000417232513D, 0.23000000417232513D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.177F, -1.65F, 1.4F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.Leupold, (player, stack) -> {
         GL11.glTranslatef(-0.157F, -1.22F, -2.85F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
      }, (model) -> {
         if(model instanceof LeupoldReticle) {
            GL11.glTranslatef(0.076F, -0.52F, 4.0251F);
            GL11.glScaled(0.09000000357627869D, 0.09000000357627869D, 0.09000000357627869D);
         }

      }).withCompatibleAttachment(AuxiliaryAttachments.M14Action, true, (model) -> {
         if(model instanceof M14Action) {
            GL11.glTranslatef(0.0F, 0.01F, 0.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withCompatibleAttachment(AuxiliaryAttachments.M14Action2, true, (model) -> {
         if(model instanceof M14Action2) {
            GL11.glTranslatef(0.0F, 0.02F, 0.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withCompatibleAttachment(Attachments.Silencer762x51, (model) -> {
         GL11.glTranslatef(-0.2F, -1.3F, -10.63F);
         GL11.glScaled(1.0D, 1.0D, 1.2000000476837158D);
      }).withTextureNames(new String[]{"M21"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new M21()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.4699999988079071D, 0.4699999988079071D, 0.4699999988079071D);
         GL11.glTranslatef(-2.0F, -1.6F, 2.2F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         GL11.glTranslatef(-0.45F, 0.675F, 0.15F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         GL11.glTranslatef(-0.45F, 0.675F, 0.7F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 0.8F, 0.65F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
            GL11.glTranslatef(-0.008F, 0.26F, 0.07F);
         }

      }).withFirstPersonCustomPositioning(Magazines.M14DMRMag, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.M14DMRMag, (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.M14DMRMag, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.M14Action.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.M14Action2.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.M14Action.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.2F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.M14Action.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.2F);
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.M14Action2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.34F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.M14Action2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.34F);
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.249999F, 0.625F, -0.025F);
      }, 350L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.249999F, 0.625F, -0.025F);
      }, 450L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
      }, 350L, 160L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
      }, 200L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.525F, 1.225F, 0.2F);
      }, 230L, 50L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.249999F, 0.625F, -0.025F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.249999F, 0.625F, -0.025F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.M14DMRMag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.M14DMRMag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.M14Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.M14Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.2F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.M14Action2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.M14Action2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.34F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 0.8F, 0.4F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Leupold)) {
            GL11.glTranslatef(-0.008F, 0.26F, 0.2F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.4F, 0.9F, 0.15F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.0F);
         GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.05F, 0.575F, 0.875F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(4.0F, 4.0F, 7.0F);
         GL11.glTranslatef(0.5F, 0.1F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }).withFirstPersonHandPositioningRunning((renderContext) -> {
         GL11.glScalef(4.0F, 4.0F, 7.0F);
         GL11.glTranslatef(0.5F, 0.1F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.325F, -0.425F, 0.025F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.49F, 0.14F, -0.1F);
         GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(25.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.375F, -0.8F, 0.15F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.525F, -0.7F, 0.1F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.225F, -0.675F, 0.1F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(4.0F, 4.0F, 7.0F);
         GL11.glTranslatef(0.5F, 0.1F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(4.0F, 4.0F, 7.0F);
         GL11.glTranslatef(0.5F, 0.1F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(4.0F, 4.0F, 7.0F);
         GL11.glTranslatef(0.5F, 0.1F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, 0.05F, 0.225F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, -0.1F, 0.225F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, 0.05F, 0.225F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.225F, -0.675F, 0.1F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.525F, -0.7F, 0.1F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.25F, -0.5F, 0.075F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(14.0F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
