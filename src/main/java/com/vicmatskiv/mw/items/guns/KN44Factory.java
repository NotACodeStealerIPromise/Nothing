package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.GunSkins;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.guns.GunFactory;
import com.vicmatskiv.mw.models.AK47iron;
import com.vicmatskiv.mw.models.AKMiron1;
import com.vicmatskiv.mw.models.AKMiron2;
import com.vicmatskiv.mw.models.FALIron;
import com.vicmatskiv.mw.models.G36CIron1;
import com.vicmatskiv.mw.models.G36CIron2;
import com.vicmatskiv.mw.models.KN44;
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.PSO12;
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

public class KN44Factory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("KN44").withFireRate(0.6F).withRecoil(2.3F).withZoom(0.9F).withMaxShots(new int[]{Integer.MAX_VALUE, 3, 1}).withShootSound("KN44").withSilencedShootSound("MP5Silenced").withReloadSound("StandardReload").withUnloadSound("Unload").withReloadingTime(43L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.12F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.12F);
      }).withCreativeTab(ModernWarfareMod.FunGunsTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, "ingotSteel"}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Assault Rifle", "Damage: 7.7", "Caliber: 5.56x39mm", "Magazines:", "30rnd 5.56x39mm Magazine (Type 2)", "45rnd 5.56x39mm Magazine", "Fire Rate: Auto"});
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
      })).withCompatibleAttachment(GunSkins.Desert, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Desert.getTextureVariantIndex("Desert"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("Forest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
         if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.17F, -1.155F, -0.1F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(-0.16F, -1.15F, -3.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof AKMiron1) {
            GL11.glTranslatef(-0.157F, -1.425F, -1.57F);
            GL11.glScaled(0.23999999463558197D, 0.23999999463558197D, 0.23999999463558197D);
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
            GL11.glTranslatef(-0.15F, -1.32F, -3.253F);
            GL11.glScaled(0.25D, 0.25D, 0.6000000238418579D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(-0.1F, -1.235F, -0.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withCompatibleAttachment(Attachments.Grip2, (model) -> {
         GL11.glTranslatef(-0.17F, -0.4F, -2.4F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.VGrip, (model) -> {
         GL11.glTranslatef(-0.17F, -0.4F, -2.4F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Bipod, (model) -> {
         GL11.glTranslatef(-0.17F, -0.4F, -2.4F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.StubbyGrip, (model) -> {
         GL11.glTranslatef(-0.17F, -0.4F, -2.4F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withCompatibleAttachment(Attachments.Silencer556x39, (model) -> {
         GL11.glTranslatef(-0.2F, -1.08F, -5.4F);
         GL11.glScaled(1.0D, 1.0D, 1.0D);
      }).withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
         GL11.glTranslatef(0.08F, -1.0F, -0.75F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(-0.209F, -0.485F, 1.27F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         } else if(model instanceof PSO12) {
            GL11.glTranslatef(-0.27F, -0.6F, 1.21F);
            GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         }

      }).withTextureNames(new String[]{"KN44"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new KN44()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.36000001430511475D, 0.36000001430511475D, 0.36000001430511475D);
         GL11.glTranslatef(1.0F, 2.0F, -1.4F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-1.6F, -0.8F, 1.7F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 1.15F);
         GL11.glRotatef(-2.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.345F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.315F, -1.17F, 1.95F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.5F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
            GL11.glTranslatef(1.333F, -1.25F, 2.15F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.7F, -0.5F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 500L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.7F, -0.5F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 250L, 20L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
      }, 350L, 60L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
      }, 100L, 0L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
      }, 100L, 0L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.65F, 0.25F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glTranslatef(-0.4F, -0.7F, 0.9F);
         GL11.glRotatef(15.0F, 0.0F, 0.0F, 1.0F);
      }, 250L, 0L)}).withFirstPersonPositioningUnloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.7F, -0.5F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.7F, -0.5F, -0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-10.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-15.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 0.9F);
      }, 150L, 50L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.0F, -0.345F, -0.2F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glTranslatef(0.315F, -1.17F, 1.9F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
            GL11.glTranslatef(1.333F, -1.25F, 2.15F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.949999988079071D, 0.949999988079071D, 0.949999988079071D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.3F, -0.35F, -0.1F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.3F, -0.8F, -0.2F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(2.4F, 2.4F, 3.7F);
         GL11.glTranslatef(0.5F, 0.08F, 0.0F);
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
         GL11.glTranslatef(1.0F, 0.3F, -0.3F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.3F, 0.8F, 0.3F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.0F, 2.0F, 2.5F);
         GL11.glTranslatef(0.3F, 0.6F, 0.3F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-85.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(2.4F, 2.4F, 3.7F);
         GL11.glTranslatef(0.5F, 0.08F, 0.0F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.4F, 2.4F, 3.7F);
         GL11.glTranslatef(0.5F, 0.08F, 0.0F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.4F, 2.4F, 3.7F);
         GL11.glTranslatef(0.5F, 0.08F, 0.0F);
         GL11.glRotatef(115.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(30.0F, 1.0F, 1.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.4F, 2.4F, 3.7F);
         GL11.glTranslatef(0.5F, 0.08F, 0.0F);
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
         GL11.glTranslatef(-0.18F, 0.2F, -0.65F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.2F, 0.2F, -0.65F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.2F, 0.2F, -0.65F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(75.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.18F, 0.2F, -0.65F);
         GL11.glRotatef(95.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
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
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(2.5F, 2.5F, 3.0F);
         GL11.glTranslatef(-0.15F, 0.3F, 0.5F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).build()).withSpawnEntityDamage(7.7F).withSpawnEntityGravityVelocity(0.028F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
