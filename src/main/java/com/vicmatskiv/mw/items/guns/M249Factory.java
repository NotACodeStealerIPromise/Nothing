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
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M249;
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

public class M249Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("M249").withFireRate(0.6F).withRecoil(2.5F).withZoom(0.9F).withShootSound("M249").withSilencedShootSound("RifleSilencer").withReloadSound("M249Reload").withUnloadSound("Unload").withReloadingTime(110L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCreativeTab(ModernWarfareMod.ShotgunsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, CommonProxy.MetalComponents, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Light machine gun", "Damage: 7", "Caliber: 5.56x45mm NATO", "Magazines:", "200rnd 5.56x45mm NATO Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Fade, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Fade.getTextureVariantIndex("Ruby"));
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
      })).withCompatibleAttachment(Magazines.M249Mag, (Consumer)((model) -> {
         GL11.glTranslatef(0.0F, -0.1F, 0.0F);
      })).withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(0.16F, -1.75F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.26F, -1.4F, -2.05F);
            GL11.glScaled(0.6000000238418579D, 0.44999998807907104D, 0.6000000238418579D);
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
            GL11.glTranslatef(0.168F, -1.55F, 1.2F);
            GL11.glScaled(0.15000000596046448D, 0.15000000596046448D, 0.15000000596046448D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.205F, -1.9F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.165F, -1.65F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(0.163F, -1.53F, -2.05F);
            GL11.glScaled(0.4099999964237213D, 0.4300000071525574D, 0.5D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.15F, -1.7F, 1.2F);
            GL11.glScaled(0.30000001192092896D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.ACOG, (player, stack) -> {
         GL11.glTranslatef(0.055F, -1.6F, 0.7F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }, (model) -> {
         if(model instanceof Acog2) {
            GL11.glTranslatef(0.237F, -0.26F, 0.46F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Reflex, (model) -> {
         if(model instanceof Reflex) {
            GL11.glTranslatef(0.25F, -1.43F, 0.0F);
            GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(0.195F, -1.68F, 0.0F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Kobra, (model) -> {
         if(model instanceof Kobra) {
            GL11.glTranslatef(0.27F, -1.48F, 0.0F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(0.202F, -1.675F, -0.45F);
            GL11.glScaled(0.10000000149011612D, 0.10000000149011612D, 0.10000000149011612D);
         }

      }).withCompatibleAttachment(Attachments.Holo2, (model) -> {
         if(model instanceof Holographic) {
            GL11.glTranslatef(0.264F, -1.48F, 0.2F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(0.202F, -1.72F, 0.3F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Holographic2, (model) -> {
         if(model instanceof Holographic2) {
            GL11.glTranslatef(0.264F, -1.48F, 0.2F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof Holo2) {
            GL11.glTranslatef(0.202F, -1.72F, 0.3F);
            GL11.glScaled(0.05999999865889549D, 0.05999999865889549D, 0.05999999865889549D);
         }

      }).withCompatibleAttachment(Attachments.Grip2, (model) -> {
         GL11.glTranslatef(0.135F, -0.54F, -1.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
         GL11.glTranslatef(0.135F, -0.54F, -1.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Grip, (model) -> {
         GL11.glTranslatef(0.135F, -0.57F, -1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }).withCompatibleAttachment(Attachments.VGrip, (model) -> {
         GL11.glTranslatef(0.135F, -0.52F, -1.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(0.135F, -0.54F, -1.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Laser2, (p, s) -> {
         GL11.glTranslatef(0.32F, -1.14F, -1.2F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }).withCompatibleAttachment(Attachments.Laser, (p, s) -> {
         GL11.glTranslatef(0.32F, -1.14F, -1.2F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }).withCompatibleAttachment(Attachments.Silencer556x45, (model) -> {
         GL11.glTranslatef(0.107F, -1.43F, -4.1F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"GunmetalTexture", "Electric"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new M249()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-1.8F, 0.3F, 1.5F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.45F, -0.47F, -0.25F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.9F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.45F, -0.47F, -0.25F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 1.1F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.034F, -0.325F, -0.17F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.08F, 1.04F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(-0.007F, 0.21F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.19F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(-0.02F, 0.18F, 0.8F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(-0.01F, 0.2F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(1.36F, -1.14F, 2.5F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.M249Mag, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.4F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.4F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 20L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.15F, 0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 2000L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.4F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.4F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.M249Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, 0.5F, -0.2F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(1.3F, 0.5F, -0.8F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.M249Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.034F, -0.325F, -0.17F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.08F, 1.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(-0.007F, 0.21F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.19F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(-0.02F, 0.18F, 0.8F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(-0.01F, 0.2F, 0.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(1.36F, -1.14F, 2.5F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.5F, -0.35F, -1.0F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.0F, -0.8F, -1.5F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.35F, 0.37F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, -0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(2.2F, 2.2F, 2.2F);
         GL11.glTranslatef(1.0F, 0.2F, 0.2F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.8F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.8F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.5F, 0.1F, 0.6F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
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
         GL11.glTranslatef(-0.15F, -0.1F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
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
