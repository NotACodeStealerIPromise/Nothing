package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.AUG;
import com.vicmatskiv.mw.models.LPscope;
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

public class AUGFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("AUG").withFireRate(0.7F).withRecoil(3.5F).withZoom(0.9F).withMaxShots(new int[]{Integer.MAX_VALUE, 3, 1}).withShootSound("aug").withSilencedShootSound("AR15Silenced").withReloadSound("StandardReload").withUnloadSound("Unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.11F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.08F);
      }).withShellCasingForwardOffset(-0.1F).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Assault rifle", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine", "Fire Rate: Auto"});
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
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(AuxiliaryAttachments.AUGAction, true, (model) -> {
      }).withCompatibleAttachment(Attachments.AUGScope, true, true, (player, stack) -> {
         GL11.glTranslatef(-0.165F, -1.4F, -1.05F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.08F, -0.12F, 2.92F);
            GL11.glScaled(0.09000000357627869D, 0.10000000149011612D, 0.07999999821186066D);
         }

      }).withCompatibleAttachment(Magazines.NATOMag1, (Consumer)((model) -> {
         GL11.glTranslatef(-0.335F, 0.9F, 1.2F);
         GL11.glScaled(1.0D, 1.5D, 1.5D);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
      })).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.2F, -1.0F, -5.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"AUG"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new AUG()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-1.5F, -0.9F, 1.7F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.3F, 0.4F, -0.6F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glTranslatef(-0.3F, 0.4F, -0.2F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.05F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AUGScope)) {
            GL11.glTranslatef(0.39F, 0.77F, -0.225F);
         } else {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.AUGAction.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.AUGAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.AUGAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
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
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.AUGAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.AUGAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AUGScope)) {
            GL11.glTranslatef(0.39F, 0.77F, -0.225F);
         } else {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.3F, 0.5F, -1.05F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(2.0D, 2.0D, 2.0D);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.9F, 0.35F, -0.45F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, -0.525F, 0.45F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }).withFirstPersonHandPositioningRunning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, -0.525F, 0.45F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.4F, -0.5F, 0.12F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, -0.525F, 0.45F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.5F, 0.12F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(55.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.075F, -0.525F, 0.45F);
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
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.025F, -0.65F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.15F, -0.725F, -0.05F);
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
