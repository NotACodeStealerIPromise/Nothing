package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.G36;
import com.vicmatskiv.mw.models.Holo2;
import com.vicmatskiv.mw.models.Holographic;
import com.vicmatskiv.mw.models.Holographic2;
import com.vicmatskiv.mw.models.Kobra;
import com.vicmatskiv.mw.models.Reflex;
import com.vicmatskiv.mw.models.Reflex2;
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

public class MG36Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("MG36").withFireRate(0.7F).withRecoil(2.5F).withZoom(0.8F).withShootSound("G36").withSilencedShootSound("RifleSilencer").withReloadSound("StandardReload").withUnloadSound("Unload").withReloadingTime(45L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.05F);
      }).withCreativeTab(ModernWarfareMod.ShotgunsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Light machine gun", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "100rnd 5.56x45mm NATO Beta-C Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.Emerald, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Emerald.getTextureVariantIndex("Emerald"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("Amber"));
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
      })).withCompatibleAttachment(Attachments.G36Scope, true, (player, stack) -> {
         GL11.glTranslatef(-0.138F, -2.2F, -1.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.800000011920929D);
      }, (model) -> {
         if(model instanceof Reflex2) {
            GL11.glTranslatef(0.035F, -0.065F, 1.28F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Reflex, (model) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.073F, -2.12F, -0.7F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.125F, -2.395F, -0.95F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Kobra, (model) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.05F, -2.13F, -0.25F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.125F, -2.415F, -0.95F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Holo2, (model) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.05F, -2.13F, -0.25F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.125F, -2.415F, -0.95F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Holographic2, (model) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.05F, -2.13F, -0.4F);
            GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.125F, -2.418F, -0.5F);
            GL11.glScaled(0.03999999910593033D, 0.03999999910593033D, 0.03999999910593033D);
         }

      }).withCompatibleAttachment(Attachments.Bipod, true, true, (player, stack) -> {
         GL11.glTranslatef(-0.2F, -0.4F, -5.4F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }, (model) -> {
      }).withCompatibleAttachment(Magazines.NATOG36Mag, (Consumer)((model) -> {
         GL11.glTranslatef(-0.37F, 0.8F, -1.7F);
         GL11.glScaled(1.25D, 1.7000000476837158D, 1.7000000476837158D);
      })).withCompatibleAttachment(Magazines.NATODrum100, (Consumer)((model) -> {
         GL11.glTranslatef(-0.37F, 0.8F, -1.7F);
         GL11.glScaled(1.25D, 1.600000023841858D, 1.7000000476837158D);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
      })).withCompatibleAttachment(AuxiliaryAttachments.G36Action, true, (model) -> {
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.2F, -1.45F, -8.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"AK12"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new G36()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-1.8F, -1.1F, 2.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glTranslatef(-0.225F, 0.725F, 0.0F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glTranslatef(-0.225F, 0.725F, 0.4F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.14F, -0.1F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.08F, 1.65F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.G36Scope)) {
            GL11.glTranslatef(-0.005F, -0.05F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.13F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.15F, -0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.24F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(1.373F, -1.205F, 2.3F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.G36Action.getRenderablePart(), (renderContext) -> {
         if(renderContext.getWeaponInstance().getAmmo() == 0) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.G36Action.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.5F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.G36Action.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.5F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOG36Mag.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOG36Mag.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATOG36Mag, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATODrum100, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
      }, 250L, 20L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 1.5F);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.3F, 0.85F, 0.3F);
      }, 500L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 1.5F);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.3F, 0.85F, 0.3F);
      }, 70L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.875F, 0.35F, -0.1F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.G36Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.G36Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.5F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOG36Mag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 1.5F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOG36Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 1.5F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.5F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.5F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.14F, -0.1F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.08F, 1.6F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.G36Scope)) {
            GL11.glTranslatef(-0.005F, -0.05F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.13F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.15F, -0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.24F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(1.373F, -1.205F, 2.3F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.45F, 1.174999F, -0.125F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(1.5D, 1.5D, 1.5D);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.249999F, 0.8F, 0.7F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 5.5F);
         GL11.glTranslatef(0.5F, 0.01F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 3.5F);
         GL11.glRotatef(110.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(205.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.775F, -0.025F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.625F, -0.575F, -0.175F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.8F, -0.075F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.175F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-55.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.15F, -0.45F, -0.075F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, -0.8F, -0.075F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.625F, -0.575F, -0.175F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.0F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
