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
import com.vicmatskiv.mw.models.Scar;
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

public class ScarFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("Scar").withFireRate(0.7F).withRecoil(3.5F).withZoom(0.9F).withMaxShots(new int[]{Integer.MAX_VALUE, 3, 1}).withShootSound("Scar").withSilencedShootSound("AR15Silenced").withReloadSound("StandardReload").withUnloadSound("Unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.12F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.15F);
      }).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Assault rifle", "Damage: 7.2", "Nickname: Scar-L", "Caliber: 5.56x45mm NATO", "Magazines:", "30rnd 5.56x45mm NATO Magazine", "30rnd 5.56x45mm NATO PMAG Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Sapphire, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Sapphire.getTextureVariantIndex("Sapphire"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.NATOMag1, (Consumer)((model) -> {
         GL11.glTranslatef(-0.342F, 0.5F, -1.43F);
         GL11.glScaled(1.100000023841858D, 1.5D, 1.5D);
      })).withCompatibleAttachment(Magazines.NATO40rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.342F, 0.5F, -1.43F);
         GL11.glScaled(1.100000023841858D, 1.5D, 1.5D);
      })).withCompatibleAttachment(Magazines.NATO20rnd, (Consumer)((model) -> {
         GL11.glTranslatef(-0.342F, 0.5F, -1.43F);
         GL11.glScaled(1.100000023841858D, 1.5D, 1.5D);
      })).withCompatibleAttachment(Magazines.NATOMag2, (Consumer)((model) -> {
         GL11.glTranslatef(-0.342F, 0.5F, -1.43F);
         GL11.glScaled(1.100000023841858D, 1.5D, 1.5D);
      })).withCompatibleAttachment(Magazines.NATODrum100, (Consumer)((model) -> {
         GL11.glTranslatef(-0.342F, 0.8F, -1.43F);
         GL11.glScaled(1.100000023841858D, 1.399999976158142D, 1.5D);
      })).withCompatibleAttachment(AuxiliaryAttachments.ScarAction, true, (model) -> {
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
            GL11.glTranslatef(-0.163F, -1.85F, 0.2F);
            GL11.glScaled(0.3499999940395355D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(-0.085F, -1.74F, -4.55F);
            GL11.glScaled(0.550000011920929D, 0.6399999856948853D, 0.6000000238418579D);
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
         GL11.glTranslatef(-0.321F, -1.8F, -0.7F);
         GL11.glScaled(0.8500000238418579D, 0.8500000238418579D, 0.8500000238418579D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.237F, -0.26F, 0.46F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Specter, (player, stack) -> {
         GL11.glTranslatef(-0.2F, -1.45F, -0.7F);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.15F, -1.035F, 1.513F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Scope, (player, stack) -> {
         GL11.glTranslatef(-0.386F, -1.79F, -0.6F);
         GL11.glScaled(1.1100000143051147D, 1.1100000143051147D, 1.1100000143051147D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.272F, 0.67F);
            GL11.glScaled(0.05000000074505806D, 0.05000000074505806D, 0.05000000074505806D);
         }

      }).withCompatibleAttachment(Attachments.HP, (player, stack) -> {
         GL11.glTranslatef(-0.386F, -1.79F, -1.0F);
         GL11.glScaled(1.1100000143051147D, 1.1100000143051147D, 1.1100000143051147D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(0.237F, -0.235F, 1.16F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Reflex, (model) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(-0.06F, -1.6F, -0.9F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Holo2, (model) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(-0.041F, -1.63F, -0.8F);
            GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Holographic2, (model) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(-0.041F, -1.63F, -1.1F);
            GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Kobra, (model) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(-0.041F, -1.63F, -0.6F);
            GL11.glScaled(0.6700000166893005D, 0.6700000166893005D, 0.6700000166893005D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(-0.125F, -1.94F, -1.2F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         }

      }).withCompatibleAttachment(Attachments.Grip2, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Grip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.VGrip, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -3.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(-0.2F, -0.41F, -4.0F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
         GL11.glTranslatef(0.0F, -1.25F, -4.0F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Laser, (p, s) -> {
         GL11.glTranslatef(0.0F, -1.25F, -4.0F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(-0.2F, -1.32F, -7.2F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"Scar"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new Scar()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 2.0F, -1.2F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-2.0F, -1.2F, 2.2F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.375F, 1.399999F, -0.725F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(-0.375F, 1.399999F, -0.5F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.349999F, -0.5F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(-0.005F, 0.115F, 0.85F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(-0.0F, 0.06F, 0.4F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(-0.003F, 0.19F, 0.3F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.19F, -0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.047F, -0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
         }

      }).withFirstPersonCustomPositioning(Magazines.NATOMag1, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO20rnd, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATO40rnd, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATOMag2, (renderContext) -> {
      }).withFirstPersonCustomPositioning(Magazines.NATODrum100, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.ScarAction.getRenderablePart(), (renderContext) -> {
         if(renderContext.getWeaponInstance().getAmmo() == 0) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.ScarAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.2F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.ScarAction.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 1.2F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag1.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATOMag2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATOMag2.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATO20rnd.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATO20rnd.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATO40rnd.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATO40rnd.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.NATODrum100.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.275F, 0.2F, -0.1F);
      }, 300L, 60L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.275F, 0.2F, -0.1F);
      }, 400L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.325F, 0.325F, 0.0F);
      }, 400L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.325F, 0.325F, 0.0F);
      }, 150L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.325F, 0.325F, 0.0F);
      }, 60L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.275F, 0.2F, -0.1F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.275F, 0.2F, -0.1F);
      }, 200L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.NATOMag1, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.01F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.ScarAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 500L, 1000L), new Transition((renderContext) -> {
      }, 500L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.ScarAction.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
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
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.NATO20rnd, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, 1.7F, 0.1F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
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
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.35F, 1.349999F, -0.6F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.AKMIron)) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(-0.005F, 0.115F, 0.85F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Specter)) {
            GL11.glTranslatef(-0.0F, 0.06F, 0.4F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(-0.003F, 0.19F, 0.3F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.19F, -0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.047F, -0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(0.0F, 0.045F, -0.21F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.375F, 1.574999F, -0.725F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.075F, 0.925F, 0.15F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.5F, 3.5F, 5.5F);
         GL11.glTranslatef(0.48F, 0.0F, -0.05F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.7F, 3.7F, 4.2F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.45F, -0.53F, 0.09F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(3.1F, 3.1F, 3.1F);
         GL11.glTranslatef(0.9F, 0.4F, -0.4F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.7F, 3.7F, 4.2F);
         GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.45F, -0.53F, 0.09F);
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
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.025F, -0.725F, -0.3F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.05F, -0.7F, 0.025F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 2.5F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.025F, -0.725F, -0.3F);
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
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
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
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.2F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
