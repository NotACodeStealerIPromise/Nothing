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
import com.vicmatskiv.mw.models.AKRail;
import com.vicmatskiv.mw.models.AKRail2;
import com.vicmatskiv.mw.models.AKRail3;
import com.vicmatskiv.mw.models.AKRail4;
import com.vicmatskiv.mw.models.Acog2;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.L96;
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.mw.models.M14Iron;
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
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class L115A3Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("L115A3").withFireRate(0.16F).withEjectRoundRequired().withEjectSpentRoundSound("L96BoltAction").withRecoil(4.0F).withZoom(0.8F).withMaxShots(new int[]{1}).withShootSound("L96").withPumpTimeout(1000L).withSilencedShootSound("RifleSilencer").withReloadSound("BoltActionReload").withUnloadSound("l96unload").withReloadingTime(40L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.07F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.06F);
      }).withShellCasingEjectEnabled(false).withCreativeTab(ModernWarfareMod.SnipersTab).withCrafting(CraftingComplexity.HIGH, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, CommonProxy.BigSteelPlate}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Sniper rifle", "Damage: 27", "Caliber: 7.62x51mm", "Magazines:", "8rnd 7.62x51mm Magazine", "Fire Rate: Bolt Action"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.LightningStrike, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.LightningStrike.getTextureVariantIndex("AWPLightningStrike"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("Gold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.L115Mag, (Consumer)((model) -> {
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
         GL11.glTranslatef(-0.27F, 0.6F, -0.97F);
      })).withCompatibleAttachment(AuxiliaryAttachments.L115Bolt1, true, (model) -> {
      }).withCompatibleAttachment(AuxiliaryAttachments.L115Bolt2, true, (model) -> {
         GL11.glTranslatef(-0.35F, 0.35F, -1.56F);
         GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
      }).withCompatibleAttachment(AuxiliaryAttachments.AUGRail, true, (model) -> {
         if(model instanceof AKRail) {
            GL11.glTranslatef(-0.22F, -1.36F, -3.15F);
            GL11.glScaled(0.699999988079071D, 0.800000011920929D, 0.8999999761581421D);
         } else if(model instanceof AKRail2) {
            GL11.glTranslatef(-0.42F, -0.6F, -5.3F);
            GL11.glScaled(1.0D, 1.0D, 0.699999988079071D);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
         } else if(model instanceof AKRail3) {
            GL11.glTranslatef(-0.03F, -0.52F, -3.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
            GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
         } else if(model instanceof AKRail4) {
            GL11.glTranslatef(0.2F, -0.9F, -5.3F);
            GL11.glScaled(1.0D, 1.0D, 0.699999988079071D);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         }

      }).withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(-0.165F, -1.5F, -1.15F);
            GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.262F, -0.8F, -2.25F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof P90iron) {
            GL11.glTranslatef(0.26F, -1.55F, -2.35F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron1) {
            GL11.glTranslatef(0.125F, -1.8F, -0.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron2) {
            GL11.glTranslatef(0.13F, -1.55F, -3.05F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AK47iron) {
            GL11.glTranslatef(-0.22F, -1.75F, -3.07F);
            GL11.glScaled(0.6000000238418579D, 0.699999988079071D, 0.10000000149011612D);
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.165F, -1.65F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(-0.17F, -1.545F, -3.1F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.HP, (player, stack) -> {
         GL11.glTranslatef(-0.36F, -1.43F, -1.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.235F, 1.16F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
         GL11.glTranslatef(-0.36F, -1.43F, -1.0F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.272F, 0.67F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
         GL11.glTranslatef(-0.335F, -1.45F, -1.3F);
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.237F, -0.26F, 0.46F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
         GL11.glTranslatef(-0.19F, -1.15F, -1.5F);
         GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.15F, -1.035F, 1.513F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(-0.2F, -0.3F, -5.85F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser, (model) -> {
         GL11.glTranslatef(0.06F, -0.9F, -4.8F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser2, (model) -> {
         GL11.glTranslatef(0.06F, -0.9F, -4.8F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"L115"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new L96()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.2800000011920929D, 0.2800000011920929D, 0.2800000011920929D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
         GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.45F, 0.875F, -0.1F);
      }).withFirstPersonCustomPositioning(Magazines.L115Mag, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.AUGRail.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.45F, 0.875F, 0.2F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.0F, 0.2F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.145F, 0.15F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.145F, 0.25F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.0F, 0.13F, 0.75F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(0.0F, 0.055F, 0.6F);
         }

      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.L115Bolt1.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.L115Bolt2.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 120L, 0L)}).withFirstPersonCustomPositioningEjectSpentRound(AuxiliaryAttachments.L115Bolt1.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningEjectSpentRound(AuxiliaryAttachments.L115Bolt2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.0F);
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.8F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningEjectSpentRound(AuxiliaryAttachments.AUGRail.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 300L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningEjectSpentRound(Magazines.L115Mag.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
      }, 300L, 60L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
      }, 300L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 350L, 120L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 200L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.475F, 0.9F, -0.25F);
      }, 120L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.75F, 0.725F, 0.025F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.L115Mag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.L115Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.AUGRail.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.AUGRail.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.L115Bolt1.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.L115Bolt2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.0F);
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.8F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.8F, 0.7F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.L115Bolt1.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.L115Bolt2.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.0F, -0.125F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.145F, 0.35F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.145F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.0F, 0.13F, 1.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(0.0F, 0.055F, 0.8F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.425F, 1.025F, -0.375F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.05F, 0.675F, 0.575F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.05F, -0.275F, -0.35F);
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
         GL11.glTranslatef(0.5F, -0.8F, 0.1F);
      }, (renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 3.5F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.675F, 0.7F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 3.5F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.125F, -0.8F, 0.25F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.175F, -0.4F, -0.075F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.095F, -0.31F, -0.02F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.15F, -0.515F, -0.125F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.095F, -0.31F, -0.02F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.175F, -0.4F, -0.075F);
      }, 350L, 1050L)}).withFirstPersonLeftHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 7.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.275F, -0.34F);
      }, 250L, 50L)}).withFirstPersonRightHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.175F, -0.4F, -0.075F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.095F, -0.31F, -0.02F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-115.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.15F, -0.515F, -0.125F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.095F, -0.31F, -0.02F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.175F, -0.4F, -0.075F);
      }, 350L, 1050L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 3.5F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.125F, -0.8F, 0.25F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 3.5F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.675F, 0.7F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 4.0F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.44F, -0.5F, 0.08F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(27.0F).withSpawnEntityGravityVelocity(0.0F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
