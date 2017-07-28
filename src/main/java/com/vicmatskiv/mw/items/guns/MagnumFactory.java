package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.Bullets;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.Magnum;
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

public class MagnumFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("Magnum").withAmmoCapacity(6).withFireRate(0.2F).withRecoil(5.0F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("Magnum").withReloadSound("MagnumReload").withReloadingTime(25L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.5F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.2F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withInaccuracy(4.0F).withCreativeTab(ModernWarfareMod.PistolsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Revolver", "Damage: 5.5", "Ammo: .44 Bullet", "Fire Rate: Semi"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("Gold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(AuxiliaryAttachments.RevolverCase, true, (model) -> {
      }).withCompatibleBullet(Bullets.Magnum44Ammo, (model) -> {
      }).withTextureNames(new String[]{"Magnum", "MagnumGold"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new Magnum()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(-0.9F, 0.6F, 0.5F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
         GL11.glTranslatef(-2.0F, -1.1F, 2.2F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.5F, -1.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.5F, -1.0F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.RevolverCase.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.RevolverCase.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(-0.4F, -0.3F, -0.6F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.23F, -1.19F, 1.3F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(-0.01F, 0.44F, 0.6F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.37F, -1.226F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.RevolverCase.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.1F, -0.1F, -0.9F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.1F, -0.1F, -0.9F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }, 250L, 100L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.RevolverCase.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.6F, 0.8F, -1.3F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.6F, 0.8F, -1.3F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         GL11.glTranslatef(-1.1F, -0.76F, 1.5F);
      }, 250L, 50L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(-0.4F, -0.195F, -0.6F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.23F, -1.19F, 1.5F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(-0.01F, 0.44F, 0.6F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.37F, -1.226F, 3.2F);
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
         GL11.glTranslatef(0.65F, 0.3F, 0.4F);
         GL11.glRotatef(130.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(70.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glTranslatef(0.65F, 0.3F, 0.4F);
         GL11.glRotatef(130.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(70.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glScalef(3.3F, 3.3F, 3.3F);
         GL11.glTranslatef(-0.13F, 0.38F, 0.52F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 200L)}).withFirstPersonHandPositioningZooming((renderContext) -> {
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
      }).build()).withSpawnEntityDamage(5.5F).withSpawnEntityGravityVelocity(0.016F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
