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
import com.vicmatskiv.mw.models.Acog2;
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
import com.vicmatskiv.mw.models.SPAR16;
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

public class M27Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("HKM27IAR").withFireRate(0.7F).withRecoil(3.4F).withZoom(0.9F).withMaxShots(new int[]{Integer.MAX_VALUE, 3, 1}).withShootSound("hk416a5").withSilencedShootSound("ar15silenced").withReloadSound("m4reload").withUnloadSound("m4unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Assault rifle/Designated marksmen rifle", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine", "20rnd 5.56x45mm NATO Magazine", "40rnd 5.56x45mm NATO Magazine", "30rnd 5.56x45mm NATO PMAG Magazine", "100rnd 5.56x45mm NATO Beta-C Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("Gold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.NATOMag1, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.149999976158142D);
      })).withCompatibleAttachment(Magazines.NATO40rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.149999976158142D);
      })).withCompatibleAttachment(Magazines.NATO20rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.149999976158142D);
      })).withCompatibleAttachment(Magazines.NATOMag2, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.5F, -1.25F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.149999976158142D);
      })).withCompatibleAttachment(Magazines.NATODrum100, (Consumer)((model) -> {
         GL11.glTranslatef(-0.35F, 0.69F, -1.25F);
         GL11.glScaled(1.149999976158142D, 1.2000000476837158D, 1.149999976158142D);
      })).withCompatibleAttachment(AuxiliaryAttachments.AR15Action, true, (model) -> {
         GL11.glTranslatef(-0.175F, -1.28F, -0.67F);
         GL11.glScaled(0.699999988079071D, 0.4000000059604645D, 0.699999988079071D);
      }).withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(0.162F, -1.75F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(-0.055F, -1.35F, -4.05F);
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
            GL11.glTranslatef(0.092F, -1.91F, -0.9F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
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
            GL11.glTranslatef(-0.085F, -1.42F, -3.54F);
            GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(-0.185F, -1.5F, -4.05F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(-0.16F, -1.5F, -0.3F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.255F, -1.55F, -2.25F);
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
            GL11.glTranslatef(0.092F, -1.91F, -0.9F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.22F, -1.94F, 0.13F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.205F, -1.9F, -3.15F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(-0.15F, -1.48F, -0.2F);
            GL11.glScaled(0.25D, 0.3499999940395355D, 0.30000001192092896D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(0.127F, -1.77F, -2.22F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
         GL11.glTranslatef(-0.265F, -1.41F, -0.3F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.237F, -0.26F, 0.46F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
         GL11.glTranslatef(-0.17F, -1.14F, -0.45F);
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.15F, -1.035F, 1.513F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
         GL11.glTranslatef(-0.3F, -1.38F, -0.3F);
         GL11.glScaled(0.75D, 0.75D, 0.75D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.272F, 0.67F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.HP, (player, stack) -> {
         GL11.glTranslatef(-0.3F, -1.38F, -0.5F);
         GL11.glScaled(0.75D, 0.75D, 0.75D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.235F, 1.16F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Reflex, (model) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.072F, -1.25F, -0.9F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.125F, -1.53F, -0.9F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Holo2, (model) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
            GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.12F, -1.545F, -0.5F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.Holographic2, (model) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
            GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.12F, -1.55F, -0.5F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.Kobra, (model) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.053F, -1.28F, -0.6F);
            GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.12F, -1.53F, -1.05F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Grip2, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Grip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.35F, -2.9F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.VGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -2.5F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
         GL11.glTranslatef(0.05F, -1.18F, -3.2F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }).withCompatibleAttachment(Attachments.Laser, (p, s) -> {
         GL11.glTranslatef(0.05F, -1.18F, -3.2F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.2F, -1.235F, -6.4F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"AK12"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new SPAR16()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-1.7F, -0.8F, 1.9F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.225F, 0.95F, -0.3F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.225F, 0.95F, -0.1F);
         GL11.glRotatef(-1.5F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.0F, -0.4F);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.001F, -0.005F, 0.67F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(-0.003F, -0.065F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.001F, 0.02F, 0.43F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.001F, 0.02F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, -0.02F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, -0.02F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, -0.015F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, -0.015F, 0.1F);
         }

      }).withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO20rnd, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO40rnd, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATOMag2, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATODrum100, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.AR15Action.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.725F, 1.1F, -0.15F);
      }, 300L, 60L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.725F, 1.1F, -0.15F);
      }, 400L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.15F, 0.9F, -0.175F);
      }, 400L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.15F, 0.9F, -0.175F);
      }, 150L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.15F, 0.9F, -0.175F);
      }, 60L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.725F, 1.1F, -0.15F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.725F, 1.1F, -0.15F);
      }, 200L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.AR15Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.AR15Action.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.4F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATO40rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATO20rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATO40rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATO20rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag2, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATOMag2, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATODrum100, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.0F, -0.525F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.001F, -0.005F, 0.75F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(-0.003F, -0.065F, 0.55F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.001F, 0.02F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.001F, 0.02F, 0.25F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, -0.02F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, -0.02F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, -0.015F, 0.1F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, -0.015F, 0.1F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.2F, 1.0F, -0.1F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.75F, 0.675F, 0.25F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.48F, 0.08F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(2.2F, 2.2F, 2.2F);
         GL11.glTranslatef(1.0F, 0.4F, -0.4F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, -0.1F, 0.6F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.48F, 0.08F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.48F, 0.08F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glTranslatef(0.48F, 0.08F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-215.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.6F, -0.25F, -0.125F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-230.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.675F, -0.25F, -0.2F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-215.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(130.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.6F, -0.25F, -0.125F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(65.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.1F, -0.55F, 0.475F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 5.0F);
         GL11.glRotatef(-45.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, -0.1F, 0.6F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.0F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
