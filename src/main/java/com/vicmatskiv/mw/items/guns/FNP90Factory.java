package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.Magazines;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.FNP90;
import com.vicmatskiv.mw.models.FNP90Sight;
import com.vicmatskiv.mw.models.Reflex2;
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

public class FNP90Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("FNP90").withFireRate(0.75F).withRecoil(2.8F).withZoom(0.9F).withShootSound("P90").withSilencedShootSound("RifleSilencer").withReloadSound("P90Reload").withUnloadSound("Unload").withReloadingTime(70L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.6F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.29F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.3F);
      }).withCreativeTab(ModernWarfareMod.SMGTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Personal defense weapon", "Damage: 6.5", "Caliber: 5.7x28mm", "Magazines:", "50rnd 5.7x28mm Magazine", "Fire Rate: Auto"});
      }).withCompatibleAttachment(GunSkins.ElectricSkin, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.ElectricSkin.getTextureVariantIndex("Electric"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Amber, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amber.getTextureVariantIndex("Amber"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Fade, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Fade.getTextureVariantIndex("Ruby"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Emerald, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Emerald.getTextureVariantIndex("Emerald"));
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
      })).withCompatibleAttachment(GunSkins.Amethyst, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Amethyst.getTextureVariantIndex("Amethyst"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Magazines.FNP90Mag, (Consumer)((model) -> {
      })).withCompatibleAttachment(AuxiliaryAttachments.FNP90Sight, true, (model) -> {
         if(model instanceof FNP90Sight) {
            GL11.glTranslatef(0.045F, -1.7F, -0.3F);
            GL11.glScaled(0.75D, 0.8999999761581421D, 0.8999999761581421D);
         } else if(model instanceof Reflex2) {
            GL11.glTranslatef(0.19F, -1.76F, 0.0F);
            GL11.glScaled(0.20000000298023224D, 0.20000000298023224D, 0.20000000298023224D);
         }

      }).withCompatibleAttachment(Attachments.Laser, (p, s) -> {
         GL11.glTranslatef(0.34F, -1.7F, -0.1F);
         GL11.glScaled(0.6499999761581421D, 0.6499999761581421D, 0.6499999761581421D);
      }).withCompatibleAttachment(Attachments.Silencer57x38, (model) -> {
         GL11.glTranslatef(0.107F, -1.2F, -2.14F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withTextureNames(new String[]{"P90", "Electric"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new FNP90()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3499999940395355D, 0.3499999940395355D, 0.3499999940395355D);
         GL11.glTranslatef(2.0F, 0.5F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glTranslatef(-1.8F, 0.7F, 1.5F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.3F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.45F);
         GL11.glRotatef(-3.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(-0.3F, -0.32F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.263F, -0.9F, 1.9F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.15F, 0.45F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.4F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.19F, 2.5F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(Magazines.FNP90Mag, (renderContext) -> {
      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.1F, -0.2F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.1F, -0.2F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }, 250L, 20L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.1F, -0.2F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }, 250L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.1F, -0.2F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.1F, -0.1F, -0.2F);
         GL11.glRotatef(40.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(-0.5F, -0.6F, 0.3F);
      }, 150L, 50L)}).withFirstPersonCustomPositioningUnloading(Magazines.FNP90Mag, new Transition[]{new Transition((renderContext) -> {
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.15F, 0.8F);
         GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L)}).withFirstPersonCustomPositioningReloading(Magazines.FNP90Mag, new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.15F, 0.6F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.2F);
         GL11.glRotatef(10.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
      }, 250L, 1000L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(-0.3F, -0.32F, -0.4F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.263F, -0.9F, 1.8F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.ACOG)) {
            GL11.glTranslatef(0.005F, 0.15F, 0.45F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Scope)) {
            GL11.glTranslatef(0.0F, 0.4F, 0.5F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Reflex)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holo2)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Holographic2)) {
            GL11.glTranslatef(0.0F, 0.15F, 0.2F);
         }

         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.Kobra)) {
            GL11.glTranslatef(1.373F, -1.19F, 2.5F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(1.0F, -0.75F, -1.2F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.0F, -0.8F, -2.0F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(1.7F, 1.7F, 3.0F);
         GL11.glTranslatef(0.8F, -0.35F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
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
         GL11.glTranslatef(0.9F, -0.4F, 0.8F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.8F, -0.4F, 0.8F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.8F, -0.4F, 0.8F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-40.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
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
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.8F, -0.4F, 0.8F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-30.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.9F, -0.4F, 0.8F);
         GL11.glRotatef(80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(40.0F, 0.0F, 0.0F, 1.0F);
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
      }, 250L, 50L)}).build()).withSpawnEntityDamage(6.5F).withSpawnEntityGravityVelocity(0.028F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
