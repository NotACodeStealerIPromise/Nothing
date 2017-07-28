package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.FamasG2;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class FamasG2Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("FamasG2").withFireRate(0.75F).withRecoil(2.5F).withZoom(0.9F).withMaxShots(new int[]{Integer.MAX_VALUE, 3, 1}).withShootSound("Famas").withSilencedShootSound("RifleSilencer").withReloadSound("StandardReload").withUnloadSound("Unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(0.8F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.11F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.12F);
      }).withShellCasingForwardOffset(-0.1F).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Bullpup assault rifle", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.Voltaic, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Voltaic.getTextureVariantIndex("voltaic"));
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
         GL11.glTranslatef(-0.355F, 0.9F, 1.45F);
         GL11.glScaled(1.2000000476837158D, 1.5D, 1.5D);
         GL11.glRotatef(-9.0F, 1.0F, 0.0F, 0.0F);
      })).withCompatibleAttachment(AuxiliaryAttachments.FamasCarryHandle, true, (model) -> {
         GL11.glTranslatef(-0.241F, -1.15F, -1.1F);
         GL11.glScaled(0.5199999809265137D, 0.550000011920929D, 0.8500000238418579D);
      }).withCompatibleAttachment(AuxiliaryAttachments.FamasBipod1, true, (model) -> {
         GL11.glTranslatef(-0.05F, -1.2F, -2.5F);
         GL11.glScaled(1.0D, 1.0D, 0.8799999952316284D);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(1.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(1.8F, 0.0F, 1.0F, 0.0F);
      }).withCompatibleAttachment(AuxiliaryAttachments.FamasBipod2, true, (model) -> {
         GL11.glTranslatef(-0.25F, -1.05F, -2.5F);
         GL11.glScaled(1.0D, 1.0D, 0.8799999952316284D);
         GL11.glRotatef(200.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-0.8F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(1.5F, 0.0F, 1.0F, 0.0F);
      }).withCompatibleAttachment(AuxiliaryAttachments.FamasAction, true, (model) -> {
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.2F, -1.11F, -5.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"AK12"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new FamasG2()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-1.8F, -1.1F, 2.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.3F, 0.7F, -0.8F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.3F, 0.7F, -0.4F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glTranslatef(-0.795F, 1.941F, -2.2F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.246F, 0.3F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.33F, 0.28F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.3F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.373F, -1.04F, 2.8F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.FamasAction.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.FamasAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.FamasAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
      }, 300L, 60L), new Transition((renderContext) -> {
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
      }, 400L, 200L), new Transition((renderContext) -> {
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
      }, 400L, 100L), new Transition((renderContext) -> {
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.175F, -0.1F, -0.125F);
      }, 150L, 100L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.225F, -0.475F, -1.749999F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, 0.5F, -0.2F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, 0.9F, -0.2F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.FamasCarryHandle.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.FamasAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.FamasAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.FamasBipod1.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.FamasBipod2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glTranslatef(-0.795F, 1.941F, -2.5F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.246F, 0.3F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.005F, -0.04F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.33F, 0.28F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.3F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.373F, -1.04F, 2.8F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.925F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.85F, 0.575F, -0.5F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.475F, -0.225F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-25.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.475F, -0.225F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.33F, -0.5F, 0.08F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.2F, -0.8F, 0.15F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 1.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 1.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-125.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.125F, -0.575F, 0.075F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.25F, -0.6F, -0.075F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.4F, -0.5F, 0.12F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.4F, -0.5F, 0.12F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 1.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 1.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.4F, -0.5F, 0.12F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.4F, -0.5F, 0.12F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.0F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
