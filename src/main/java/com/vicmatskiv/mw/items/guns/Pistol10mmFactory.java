package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.Pistol10mm;
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

public class Pistol10mmFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("10mmPistol").withFireRate(0.6F).withRecoil(6.0F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("10mm").withSilencedShootSound("silencer").withReloadSound("NoBoltReload").withUnloadSound("Unload").withReloadingTime(40L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.5F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.2F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withInaccuracy(3.0F).withCreativeTab(ModernWarfareMod.FunGunsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MetalComponents}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"--Fallout 4\'s starting pistol--", "", "Type: Pistol", "Damage: 6", "Caliber: 10mm", "Magazines:", "10rnd 10mm Magazine", "Fire Rate: Semi"});
      }).withCompatibleAttachment(GunSkins.Amethyst, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amethyst.getTextureVariantIndex("Amethyst"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.Mag10mm, (Consumer)((model) -> {
         GL11.glTranslatef(0.0F, -0.2F, 0.14F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
         GL11.glRotatef(15.0F, 1.0F, 0.0F, 0.0F);
      })).withTextureNames(new String[]{"Pistol10mm", "Electric"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new Pistol10mm()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(0.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-1.8F, -1.0F, 2.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.5F, -1.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.6F, -1.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.Mag10mm, (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.Mag10mm, (renderContext) -> {
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(-0.3F, -0.4F, -0.5F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.31F, -1.34F, 1.5F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.5F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.38F, -1.115F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.Mag10mm, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.6F, -0.6F, -0.6F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.4F, -0.2F, -0.3F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.6F, -0.6F, -0.6F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.6F, -0.6F, -0.6F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.Mag10mm, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.3F, 0.4F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.Mag10mm, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.3F, 0.4F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(-0.235F, -0.3F, -0.44F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.31F, -1.34F, 1.5F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.5F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.38F, -1.115F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glTranslatef(0.1F, -1.5F, -1.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(-1.0F, 0.1F, 0.0F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.6F, -0.1F, 0.4F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(1.6F, 1.6F, 1.6F);
         GL11.glTranslatef(1.5F, 0.1F, -0.2F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.1F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.9F, 0.8F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 20.0F, 20.0F, -20.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.5F, 0.5F, 0.3F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.5F, 0.5F, 0.3F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L)}).withFirstPersonLeftHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.8F, 0.1F, 0.6F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.8F, 0.1F, 0.6F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.4F, -0.1F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 1.0F, 0.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.34F, 0.48F, 0.3F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
      }).build()).withSpawnEntityDamage(6.0F).withSpawnEntityGravityVelocity(0.016F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
