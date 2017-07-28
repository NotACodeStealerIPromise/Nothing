package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.HecateII;
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

public class HecateIIFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("HecateII").withFireRate(0.16F).withEjectRoundRequired().withEjectSpentRoundSound("L96BoltAction").withRecoil(4.0F).withZoom(0.8F).withMaxShots(new int[]{1}).withShootSound("HecateII").withPumpTimeout(2000L).withSilencedShootSound("RifleSilenced").withReloadSound("BoltActionReload").withUnloadSound("Unload").withReloadingTime(30L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCreativeTab(ModernWarfareMod.SnipersTab).withShellCasingEjectEnabled(false).withCrafting(CraftingComplexity.HIGH, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, CommonProxy.BigSteelPlate}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Sniper rifle", "Damage: 35", "Caliber: .50 BMG", "Magazines:", "7rnd .50 BMG Magazine", "Fire Rate: Bolt Action"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("HecateIIAmber"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Arctic, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Arctic.getTextureVariantIndex("HecateIIArctic"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("HecateIIGold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.HecateIIMag, (Consumer)((model) -> {
         GL11.glTranslatef(0.0F, -0.25F, 0.0F);
         GL11.glScaled(1.2000000476837158D, 0.699999988079071D, 1.0D);
      })).withCompatibleAttachment(AuxiliaryAttachments.HecateIIBoltAction, true, (model) -> {
         GL11.glTranslatef(-0.01F, 0.02F, 0.03F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.HP, true, true, (player, stack) -> {
         GL11.glTranslatef(0.06F, -1.37F, 0.3F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.235F, 1.16F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Bipod, true, (model) -> {
         GL11.glTranslatef(0.135F, -0.55F, -1.2F);
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
      }).withCompatibleAttachment(Attachments.Silencer50BMG, (model) -> {
         GL11.glTranslatef(0.107F, -1.45F, -6.95F);
         GL11.glScaled(1.100000023841858D, 1.100000023841858D, 1.2999999523162842D);
      }).withTextureNames(new String[]{"HecateII"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new HecateII()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 1.5F, -0.7F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-1.6F, 0.4F, 1.3F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.33F, -0.23F, -0.27F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.8F);
      }).withFirstPersonCustomPositioning(Magazines.HecateIIMag, (renderContext) -> {
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.33F, -0.23F, -0.27F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.0F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.3F, 0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.17F, -0.99F, 0.45F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.16F, 0.15F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.16F, 0.15F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(1.38F, -1.19F, 2.9F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.HecateIIBoltAction.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 150L, 0L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 150L, 0L)}).withFirstPersonCustomPositioningEjectSpentRound(AuxiliaryAttachments.HecateIIBoltAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 300L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningEjectSpentRound(Magazines.HecateIIMag.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 300L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.5F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 350L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.5F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 350L, 250L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 150L, 0L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.28F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 150L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.5F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.5F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.HecateIIMag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, 0.5F, -0.2F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(1.3F, 0.5F, -0.8F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.HecateIIMag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.HecateIIBoltAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.3F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.HecateIIBoltAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.3F, 0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.17F, -0.99F, 0.4F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.16F, 0.15F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.16F, 0.15F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(1.38F, -1.19F, 2.9F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.5F, -0.3F, -1.0F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.0F, -0.8F, -1.5F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(2.2F, 2.2F, 2.2F);
         GL11.glTranslatef(1.0F, 0.2F, 0.2F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.7F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.7F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.55F, -0.25F, 0.5F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.67F, -0.15F, 0.6F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.67F, -0.15F, 0.6F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.55F, -0.25F, 0.6F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.29F, 0.032F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L)}).withFirstPersonLeftHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.04F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonRightHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.55F, -0.25F, 0.5F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.67F, -0.15F, 0.6F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.55F, -0.25F, 0.6F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.29F, 0.032F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.2F, 0.37F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.7F, 0.0F, 0.37F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-40.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.06F, 0.95F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(35.0F).withSpawnEntityGravityVelocity(0.0F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
