package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.M1911;
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

public class M1911Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("M1911").withFireRate(0.5F).withRecoil(5.0F).withZoom(0.9F).withMaxShots(new int[]{1}).withShootSound("M1911").withSilencedShootSound("silencer").withReloadSound("PistolReload").withUnloadSound("Unload").withReloadingTime(50L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.5F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.2F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withInaccuracy(3.0F).withCreativeTab(ModernWarfareMod.PistolsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Pistol", "Damage: 5.5", "Caliber: .45 ACP", "Magazines:", "10rnd .45 ACP Magazine", "Fire Rate: Semi"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("Amber"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amethyst, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amethyst.getTextureVariantIndex("Amethyst"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(AuxiliaryAttachments.M1911Top, true, (model) -> {
      }).withCompatibleAttachment(Magazines.ColtM1911Mag, (Consumer)((model) -> {
         GL11.glTranslatef(0.0F, 0.2F, 0.12F);
      })).withCompatibleAttachment(Attachments.Silencer45ACP, (model) -> {
         GL11.glTranslatef(-0.23F, -1.14F, -4.92F);
         GL11.glScaled(1.5D, 1.5D, 1.5D);
      }).withTextureNames(new String[]{"M1911"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new M1911()).withEntityPositioning((itemStack) -> {
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
      }).withFirstPersonPositioningCustomRecoiled(AuxiliaryAttachments.M1911Top.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }).withFirstPersonPositioningCustomZoomingRecoiled(AuxiliaryAttachments.M1911Top.getRenderablePart(), (renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }).withFirstPersonPositioningCustomRecoiled(Magazines.ColtM1911Mag, (renderContext) -> {
      }).withFirstPersonPositioningCustomZoomingRecoiled(Magazines.ColtM1911Mag, (renderContext) -> {
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(-0.155F, -0.32F, -0.36F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-1.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.31F, -1.31F, 1.5F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.5F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.38F, -1.115F, 3.2F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.ColtM1911Mag, (renderContext) -> {
      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.M1911Top.getRenderablePart(), (renderContext) -> {
         if(renderContext.getWeaponInstance().getAmmo() == 0) {
            GL11.glTranslatef(0.0F, 0.0F, 0.5F);
         }

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
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.4F, -0.2F, -0.3F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(1.0F, -1.2F, 0.0F);
      }, 50L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
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
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.ColtM1911Mag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.3F, 0.4F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.ColtM1911Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.05F, 1.3F, 0.4F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonCustomPositioningUnloading(AuxiliaryAttachments.M1911Top.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.M1911Top.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(-0.155F, -0.32F, -0.36F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.31F, -1.31F, 1.5F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.5F, 0.7F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(1.38F, -1.17F, 3.3F);
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
         GL11.glTranslatef(-1.0F, 0.1F, -0.2F);
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
      }).build()).withSpawnEntityDamage(5.5F).withSpawnEntityGravityVelocity(0.02F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
