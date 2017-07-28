package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
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
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP40;
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
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class MP40Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("MP40").withFireRate(0.66F).withRecoil(3.0F).withZoom(0.9F).withShootSound("MP40").withSilencedShootSound("RifleSilencer").withReloadSound("NoBoltReload").withUnloadSound("Unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.5F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.15F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withInaccuracy(4.0F).withCreativeTab(ModernWarfareMod.SMGTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Submachine gun", "Damage: 5", "Caliber: 9mm", "Magazines:", "32rnd 9mm Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("Gold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Sapphire, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Sapphire.getTextureVariantIndex("Sapphire"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.MP40Mag, (Consumer)((model) -> {
      })).withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
         if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.21F, -1.94F, 4.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(0.1F, -1.45F, -3.5F);
            GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
         } else if(model instanceof AKMiron1) {
            GL11.glTranslatef(0.125F, -1.8F, -0.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron2) {
            GL11.glTranslatef(0.13F, -1.55F, -3.05F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
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
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.165F, -1.65F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.Silencer9mm, (model) -> {
         GL11.glTranslatef(0.11F, -1.5F, -5.1F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"MP40"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new MP40()).withEntityPositioning((itemStack) -> {
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
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.7F, 1.1F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.1F, 1.2F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.31F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.26F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.19F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.15F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.MP40Mag, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.1F, -0.7F, -0.6F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.1F, -0.7F, -0.6F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
      }, 250L, 20L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
      }, 250L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
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
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.MP40Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.2F, 0.5F, -0.2F);
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(1.3F, 0.5F, -0.8F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.MP40Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.135F, -1.1F, 1.1F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.31F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.26F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.19F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.15F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.5F, -0.3F, -1.0F);
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
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
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
         GL11.glTranslatef(0.4F, 0.5F, 0.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.4F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.65F, -0.35F, 0.37F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-55.0F, 1.0F, 0.0F, 0.0F);
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
      }, 250L, 50L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
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
      }, 250L, 50L)}).build()).withSpawnEntityDamage(5.0F).withSpawnEntityGravityVelocity(0.028F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
