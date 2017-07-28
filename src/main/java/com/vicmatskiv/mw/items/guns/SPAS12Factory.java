package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.Bullets;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.SPAS12;
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

public class SPAS12Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("SPAS-12").withAmmoCapacity(8).withMaxBulletsPerReload(8).withFireRate(0.2F).withRecoil(9.0F).withZoom(0.9F).withMaxShots(new int[]{1}).withPumpTimeout(1100L).withShootSound("Spas12").withSilencedShootSound("ShotgunSilenced").withReloadSound("ShotgunReload").withReloadingTime(15L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withInaccuracy(10.0F).withPellets(10).withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withCreativeTab(ModernWarfareMod.ShotgunsTab).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Combat shotgun", "Damage per Pellet: 5", "Pellets per Shot: 10", "Ammo: 12 Gauge Shotgun Shell", "Fire Rate: Semi"});
      }).withCompatibleBullet(Bullets.ShotgunShell, (model) -> {
      }).withCompatibleAttachment(Attachments.Silencer12Gauge, (model) -> {
         GL11.glTranslatef(0.107F, -1.34F, -5.9F);
         GL11.glScaled(1.2000000476837158D, 1.2000000476837158D, 1.2000000476837158D);
      }).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("Amber"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Arctic, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Arctic.getTextureVariantIndex("Arctic"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withTextureNames(new String[]{"SPAS12", "Electric"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new SPAS12()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(1.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-1.8F, 0.5F, 1.5F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.6F, -0.4F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.55F, -0.42F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(-0.2F, -0.313F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.055F, -0.95F, 1.4F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(1.37F, -1.4F, 3.4F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.2F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-45.0F, 1.0F, 0.0F, 2.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.2F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-45.0F, 1.0F, 0.0F, 2.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 250L, 50L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(-0.2F, -0.313F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.055F, -0.95F, 1.4F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(1.37F, -1.4F, 3.4F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.5F, -0.35F, -1.0F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.0F, -0.8F, -1.0F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.7F, -0.2F, 0.15F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
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
         GL11.glTranslatef(0.4F, 0.5F, 0.8F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.4F, 0.5F, 0.8F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 50L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.9F, 0.0F, 0.15F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-5.0F, 0.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.15F, 0.0F, 1.0F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }).build()).withSpawnEntityDamage(5.0F).withSpawnEntityGravityVelocity(0.8F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
