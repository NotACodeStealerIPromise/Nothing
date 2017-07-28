package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.ACRAction;
import com.vicmatskiv.mw.models.ACRAction2;
import com.vicmatskiv.mw.models.AK47iron;
import com.vicmatskiv.mw.models.AKMiron1;
import com.vicmatskiv.mw.models.AKMiron2;
import com.vicmatskiv.mw.models.AKRail;
import com.vicmatskiv.mw.models.AKRail2;
import com.vicmatskiv.mw.models.AKRail3;
import com.vicmatskiv.mw.models.AKRail4;
import com.vicmatskiv.mw.models.AKRail5;
import com.vicmatskiv.mw.models.Acog2;
import com.vicmatskiv.mw.models.BushmasterACR;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.Holo2;
import com.vicmatskiv.mw.models.Holographic;
import com.vicmatskiv.mw.models.Holographic2;
import com.vicmatskiv.mw.models.Kobra;
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.Reflex;
import com.vicmatskiv.mw.models.Reflex2;
import com.vicmatskiv.mw.models.ScarIron1;
import com.vicmatskiv.mw.models.ScarIron2;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class ACRFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("ACRTac").withFireRate(0.7F).withRecoil(3.5F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("acr").withSilencedShootSound("AR15Silenced").withReloadSound("NoBoltReload").withUnloadSound("m4unload").withReloadingTime(30L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.15F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Assault rifle", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine", "20rnd 5.56x45mm NATO Magazine", "40rnd 5.56x45mm NATO Magazine", "30rnd 5.56x45mm NATO PMAG Magazine", "100rnd 5.56x45mm NATO Beta-C Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Fade, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Fade.getTextureVariantIndex("Ruby"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("Amber"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Arctic, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Arctic.getTextureVariantIndex("Arctic"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Emerald, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Emerald.getTextureVariantIndex("Emerald"));
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
      })).withCompatibleAttachment(Magazines.NATOMag1, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.2000000476837158D);
      })).withCompatibleAttachment(Magazines.NATOMag2, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.2000000476837158D);
      })).withCompatibleAttachment(Magazines.NATO20rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.2000000476837158D);
      })).withCompatibleAttachment(Magazines.NATO40rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.3F, -1.31F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.2000000476837158D);
      })).withCompatibleAttachment(Magazines.NATODrum100, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.5F, -1.31F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.2000000476837158D);
      })).withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
         if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.165F, -1.65F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.24F, -1.56F, -1.7F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron1) {
            GL11.glTranslatef(0.14F, -1.566F, 0.3F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron2) {
            GL11.glTranslatef(0.13F, -1.39F, -2.8F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AK47iron) {
            GL11.glTranslatef(-0.22F, -2.08F, -3.7F);
            GL11.glScaled(0.6499999761581421D, 1.100000023841858D, 0.20000000298023224D);
         } else if(model instanceof M4Iron1) {
            GL11.glTranslatef(-0.162F, -1.665F, -0.3F);
            GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.26F, -1.8F, -2.35F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof P90iron) {
            GL11.glTranslatef(0.26F, -1.55F, -2.35F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(-0.173F, -1.7F, -3.72F);
            GL11.glScaled(0.5D, 0.5D, 0.8500000238418579D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
         GL11.glTranslatef(-0.288F, -1.625F, -0.5F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.237F, -0.26F, 0.46F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
         GL11.glTranslatef(-0.182F, -1.32F, -0.6F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.15F, -1.035F, 1.513F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
         GL11.glTranslatef(-0.312F, -1.6F, -0.65F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.272F, 0.67F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.Reflex, (model) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.07F, -1.45F, -0.8F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.12F, -1.7F, -0.94F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Kobra, (model) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.055F, -1.49F, -0.8F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.12F, -1.69F, -1.4F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Holo2, (model) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.05F, -1.48F, -0.8F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.12F, -1.78F, -0.8F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.Holographic2, (model) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.05F, -1.48F, -0.8F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.12F, -1.78F, -0.8F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.Grip2, (model) -> {
         GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Grip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.3F, -2.95F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.VGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.4F, -2.7F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(-0.2F, -0.35F, -3.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
         GL11.glTranslatef(0.08F, -1.15F, -3.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Laser, (p, s) -> {
         GL11.glTranslatef(0.08F, -1.15F, -3.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.19F, -1.205F, -6.8F);
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
      }).withCompatibleAttachment(AuxiliaryAttachments.BushmasterACRRail, true, (model) -> {
         if(model instanceof AKRail) {
            GL11.glTranslatef(0.19F, -1.15F, -3.9F);
            GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.800000011920929D);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         } else if(model instanceof AKRail2) {
            GL11.glTranslatef(-0.43F, -0.93F, -3.92F);
            GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.800000011920929D);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
         } else if(model instanceof AKRail3) {
            GL11.glTranslatef(-0.03F, -0.77F, -3.94F);
            GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.75D);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         } else if(model instanceof AKRail4) {
            GL11.glTranslatef(-0.226F, -1.52F, -3.9F);
            GL11.glScaled(0.7599999904632568D, 0.8700000047683716D, 0.800000011920929D);
         } else if(model instanceof AKRail5) {
            GL11.glTranslatef(-0.226F, -1.52F, -2.0F);
            GL11.glScaled(0.7599999904632568D, 0.8700000047683716D, 0.800000011920929D);
         }

      }).withCompatibleAttachment(AuxiliaryAttachments.BushmasterACRAction, true, (model) -> {
         if(model instanceof ACRAction) {
            GL11.glTranslatef(-0.06F, -1.4F, -2.9F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.5D);
         } else if(model instanceof ACRAction2) {
            ;
         }

      }).withTextureNames(new String[]{"AK12"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new BushmasterACR()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-1.7F, -0.8F, 1.9F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.4F, 0.825F, -0.15F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glTranslatef(-0.4F, 0.825F, 0.2F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.905F, 2.2F, -2.3F);
         GL11.glRotatef(-0.8F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.0F, 0.08F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(0.0F, 0.01F, 0.25F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.085F, 0.45F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.035F, 0.25F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(-0.01F, 0.02F, 0.4F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.06F, 0.2F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.059F, 0.2F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.01F, 0.4F);
         }

         GL11.glTranslatef(1.373F, -1.34F, 2.4F);
      }).withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO20rnd, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATO20rnd.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATO20rnd.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO40rnd, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATO40rnd.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATO40rnd.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATOMag2, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag2.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag2.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATODrum100, (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.BushmasterACRRail.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.BushmasterACRRail.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.BushmasterACRRail.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.BushmasterACRAction.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.BushmasterACRAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.9F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.BushmasterACRAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.9F);
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.274999F, 0.5F, 0.325F);
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.274999F, 0.5F, 0.325F);
      }, 250L, 20L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.274999F, 0.5F, 0.325F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.274999F, 0.5F, 0.325F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATO40rnd, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATO20rnd, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATO40rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATO20rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag2, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOMag2, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.2F, 0.2F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.BushmasterACRRail.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.BushmasterACRRail.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.BushmasterACRAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.BushmasterACRAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.905F, 2.2F, -2.5F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.0F, 0.08F, 0.6F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(0.0F, 0.01F, 0.4F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.085F, 0.6F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.035F, 0.25F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(-0.01F, 0.02F, 0.4F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.06F, 0.2F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.059F, 0.2F);
         } else if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.01F, 0.4F);
         }

         GL11.glTranslatef(1.373F, -1.34F, 2.4F);
      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.3F, 0.95F, -0.55F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.875F, 0.625F, 0.3F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.48F, 0.08F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.375F, -0.8F, 0.15F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.225F, -0.725F, 0.575F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.125F, -0.75F, 0.5F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.125F, -0.75F, 0.5F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.225F, -0.725F, 0.575F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.13F, 0.26F, 0.45F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.0F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
