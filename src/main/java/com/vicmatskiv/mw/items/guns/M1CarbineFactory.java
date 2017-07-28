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
import com.vicmatskiv.mw.models.M1Carbine;
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
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class M1CarbineFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("M1Carbine").withFireRate(0.3F).withRecoil(2.5F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("M1Carbine").withReloadSound("M1CarbineReload").withUnloadSound("Unload").withReloadingTime(60L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withInaccuracy(1.0F).withCreativeTab(ModernWarfareMod.AssaultRiflesTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel", CompatibleBlocks.PLANK}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Semi-automatic carbine", "Damage: 7", "Caliber: .30 Carbine", "Magazines:", "15rnd .30 Carbine Magazine", "Fire Rate: Semi"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Arctic, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Arctic.getTextureVariantIndex("Arctic"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.M1CarbineMag, (Consumer)((model) -> {
         GL11.glTranslatef(-0.3F, 0.5F, -1.3F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      })).withCompatibleAttachment(Attachments.AKMIron, true, (model) -> {
         if(model instanceof AKMiron1) {
            GL11.glTranslatef(0.125F, -1.8F, -0.5F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(-0.2F, -1.4F, -0.7F);
            GL11.glScaled(0.5D, 0.5D, 0.5D);
         } else if(model instanceof AKMiron2) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(-0.16F, -1.158F, -6.3F);
            GL11.glScaled(0.33000001311302185D, 0.33000001311302185D, 1.2000000476837158D);
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
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.16F, -1.215F, -0.76F);
            GL11.glScaled(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.16F, -1.16F, -3.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.177F, -1.65F, 1.4F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withTextureNames(new String[]{"m1carbine"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new M1Carbine()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.25D, 0.25D, 0.25D);
         GL11.glTranslatef(1.0F, 2.6F, -2.1F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-1.8F, -1.2F, 2.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.5F, -0.2F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-0.5F, -0.7F, 1.3F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.5F, -0.2F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-0.5F, -0.7F, 1.6F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.35F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.315F, -1.21F, 1.8F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.18F, 0.3F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.HP)) {
            GL11.glTranslatef(0.0F, 0.148F, 5.0F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.23F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.23F, 2.9F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.M1CarbineMag, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }, 250L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }, 350L, 150L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.175F, 0.075F, 0.625F);
      }, 400L, 80L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-35.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.175F, 0.075F, 0.625F);
      }, 100L, 200L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.M1CarbineMag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 1.5F, 1.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.M1CarbineMag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 1.5F, 1.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.35F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.315F, -1.21F, 1.7F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.0F, 0.285F, 0.53F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.236F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.236F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.235F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.15F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(0.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-25.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(2.7F, 2.7F, 4.0F);
         GL11.glTranslatef(0.5F, 0.08F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.25F, 0.15F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(2.2F, 2.2F, 2.2F);
         GL11.glRotatef(-75.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.475F, -1.05F, -0.1F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(2.7F, 2.7F, 4.0F);
         GL11.glTranslatef(0.6F, 0.2F, -0.1F);
         GL11.glRotatef(125.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.4F, 0.1F, 0.3F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-130.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.45F, -0.425F, -0.2F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.3F, -0.6F, -0.225F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.7F, 2.7F, 4.0F);
         GL11.glTranslatef(0.5F, 0.08F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.7F, 2.7F, 4.0F);
         GL11.glTranslatef(0.5F, 0.08F, -0.1F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.25F, 0.15F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.25F, 0.15F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.025F, -0.075F, -0.2F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.025F, -0.25F, -0.2F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.3F, -0.6F, -0.225F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.45F, -0.425F, -0.2F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.25F, 0.15F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.25F, 0.15F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-7.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.0F).withSpawnEntityGravityVelocity(0.0118F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
