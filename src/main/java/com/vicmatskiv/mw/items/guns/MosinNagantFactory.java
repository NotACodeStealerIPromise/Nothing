package com.vicmatskiv.mw.items.guns;

import com.vicmatskiv.mw.Attachments;
import com.vicmatskiv.mw.AuxiliaryAttachments;
import com.vicmatskiv.mw.Bullets;
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
import com.vicmatskiv.mw.models.LPscope;
import com.vicmatskiv.mw.models.M14Iron;
import com.vicmatskiv.mw.models.M4Iron1;
import com.vicmatskiv.mw.models.M4Iron2;
import com.vicmatskiv.mw.models.MP5Iron;
import com.vicmatskiv.mw.models.MosinBolt;
import com.vicmatskiv.mw.models.MosinNagant;
import com.vicmatskiv.mw.models.P90iron;
import com.vicmatskiv.mw.models.PSO12;
import com.vicmatskiv.mw.models.ScarIron1;
import com.vicmatskiv.mw.models.ScarIron2;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Arrays;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class MosinNagantFactory implements GunFactory {
   public Item createGun(CommonProxy commonProxy) {
      return (new Weapon.Builder()).withModId("mw").withName("MosinNagantM91-30").withAmmoCapacity(5).withFireRate(0.16F).withEjectRoundRequired().withEjectSpentRoundSound("MosinBoltAction").withRecoil(6.0F).withZoom(0.8F).withMaxShots(new int[]{1}).withShootSound("MosinNagant").withPumpTimeout(1600L).withReloadSound("MosinReload").withReloadingTime(40L).withCrosshair("gun").withCrosshairRunning("Running").withCrosshairZoomed("Sight").withFlashIntensity(1.0F).withFlashScale(() -> {
         return Float.valueOf(0.8F);
      }).withFlashOffsetX(() -> {
         return Float.valueOf(0.1F);
      }).withFlashOffsetY(() -> {
         return Float.valueOf(0.1F);
      }).withCreativeTab(ModernWarfareMod.SnipersTab).withCrafting(CraftingComplexity.MEDIUM, new Object[]{CommonProxy.SteelPlate, CommonProxy.MiniSteelPlate, CommonProxy.MetalComponents, CompatibleBlocks.PLANK}).withInformationProvider((stack) -> {
         return Arrays.asList(new String[]{"Type: Bolt-action rifle", "Damage: 20", "Cartridge:", "7.62x54mm Bullet", "Fire Rate: Bolt Action"});
      }).withCompatibleAttachment(AuxiliaryAttachments.MosinBolt, true, (model) -> {
         if(model instanceof MosinBolt) {
            GL11.glTranslatef(0.0F, 0.0F, 0.0F);
            GL11.glScaled(1.0D, 1.0D, 1.0D);
         }

      }).withCompatibleAttachment(GunSkins.Emerald, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Emerald.getTextureVariantIndex("MosinNagantEmerald"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Forest, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Forest.getTextureVariantIndex("MosinNagantForest"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Diamond, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Diamond.getTextureVariantIndex("Diamond"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(GunSkins.Gold, (ItemAttachment.ApplyHandler2)((a, i) -> {
         i.setActiveTextureIndex(GunSkins.Gold.getTextureVariantIndex("MosinNagantGold"));
      }), (ItemAttachment.ApplyHandler2)((a, i) -> {
      })).withCompatibleAttachment(Attachments.PSO1, (player, stack) -> {
         GL11.glTranslatef(0.349F, -1.1F, -0.2F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }, (model) -> {
         if(model instanceof LPscope) {
            GL11.glTranslatef(-0.209F, -0.485F, 1.27F);
            GL11.glScaled(0.07000000029802322D, 0.07000000029802322D, 0.07000000029802322D);
         } else if(model instanceof PSO12) {
            GL11.glTranslatef(-0.27F, -0.6F, 1.21F);
            GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         }

      }).withCompatibleBullet(Bullets.Bullet762x54, (model) -> {
      }).withCompatibleAttachment(AuxiliaryAttachments.Extra, true, (model) -> {
         if(model instanceof M4Iron1) {
            GL11.glTranslatef(0.17F, -1.42F, 0.43F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M4Iron2) {
            GL11.glTranslatef(0.255F, -0.8F, -2.25F);
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
            GL11.glTranslatef(0.092F, -1.91F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron1) {
            GL11.glTranslatef(-0.22F, -1.94F, -1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof G36CIron2) {
            GL11.glTranslatef(0.158F, -1.23F, -4.2F);
            GL11.glScaled(0.25D, 0.25D, 0.25D);
         } else if(model instanceof ScarIron1) {
            GL11.glTranslatef(0.165F, -1.65F, 1.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof ScarIron2) {
            GL11.glTranslatef(0.25F, -1.55F, -2.0F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof FALIron) {
            GL11.glTranslatef(0.125F, -1.43F, -4.88F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof M14Iron) {
            GL11.glTranslatef(0.129F, -1.63F, -2.08F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         } else if(model instanceof MP5Iron) {
            GL11.glTranslatef(0.215F, -1.54F, 1.2F);
            GL11.glScaled(0.0D, 0.0D, 0.0D);
         }

      }).withTextureNames(new String[]{"MosinNagant", "Electric"}).withRenderer((new WeaponRenderer.Builder()).withModId("mw").withModel(new MosinNagant()).withEntityPositioning((itemStack) -> {
         GL11.glScaled(0.5D, 0.5D, 0.5D);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 4.0F);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.3199999928474426D, 0.3199999928474426D, 0.3199999928474426D);
         GL11.glTranslatef(1.0F, 0.8F, 0.0F);
         GL11.glRotatef(-120.0F, -0.5F, 7.0F, 3.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-1.7F, 0.25F, 1.3F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((renderContext) -> {
         GL11.glTranslatef(0.25F, -0.13F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }).withFirstPersonPositioningRecoiled((renderContext) -> {
         GL11.glTranslatef(0.25F, -0.13F, -0.3F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.3F);
         GL11.glRotatef(-4.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioningZoomingRecoiled((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.3F, 0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.17F, -1.07F, 0.61F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-0.4F, 1.0F, 0.0F, 0.0F);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
            GL11.glTranslatef(1.37F, -1.12F, 2.6F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonCustomPositioning(AuxiliaryAttachments.MosinBolt.getRenderablePart(), (renderContext) -> {
      }).withFirstPersonPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 200L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 50L)}).withFirstPersonCustomPositioningEjectSpentRound(AuxiliaryAttachments.MosinBolt.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 300L), new Transition((renderContext) -> {
      }, 250L, 0L)}).withFirstPersonPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 350L, 250L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 350L, 100L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.3F, -0.3F, -0.15F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(7.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(-0.4F, -0.8F, 1.2F);
      }, 250L, 0L)}).withFirstPersonCustomPositioningReloading(AuxiliaryAttachments.MosinBolt.getRenderablePart(), new Transition[]{new Transition((renderContext) -> {
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glTranslatef(0.0F, 0.0F, 0.5F);
      }, 250L, 50L), new Transition((renderContext) -> {
      }, 250L, 50L)}).withFirstPersonPositioningZooming((renderContext) -> {
         GL11.glTranslatef(0.2F, -0.3F, 0.1F);
         GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
         GL11.glTranslatef(0.17F, -1.07F, 0.6F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         if(Weapon.isActiveAttachment(renderContext.getWeaponInstance(), Attachments.PSO1)) {
            GL11.glTranslatef(1.37F, -1.12F, 2.6F);
         } else {
            GL11.glTranslatef(1.373F, -1.34F, 2.4F);
         }

      }).withFirstPersonPositioningRunning((renderContext) -> {
         GL11.glScaled(0.8999999761581421D, 0.8999999761581421D, 0.8999999761581421D);
         GL11.glRotatef(-20.0F, -4.0F, 1.0F, -2.0F);
         GL11.glTranslatef(0.3F, -0.15F, -0.3F);
      }).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
         GL11.glRotatef(-35.0F, 2.0F, 1.0F, 1.0F);
         GL11.glTranslatef(1.0F, -0.8F, -1.5F);
      }).withFirstPersonHandPositioning((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.2F, 0.0F, 0.8F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningZooming((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.3F, 0.2F, 0.6F);
         GL11.glRotatef(70.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 1.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonHandPositioningModifying((renderContext) -> {
         GL11.glScalef(2.2F, 2.2F, 2.2F);
         GL11.glTranslatef(1.0F, 0.2F, 0.0F);
         GL11.glRotatef(99.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-60.0F, 20.0F, 20.0F, -20.0F);
      }, (renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.2F, 0.0F, 0.8F);
         GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonLeftHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 50L, 200L)}).withFirstPersonRightHandPositioningReloading(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.4F, -0.2F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 1000L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.35F, 0.06F, 0.01F);
         GL11.glRotatef(100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.45F, 0.5F, 0.01F);
         GL11.glRotatef(100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.3F, -0.2F, -0.2F);
         GL11.glRotatef(120.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.35F, 0.06F, 0.01F);
         GL11.glRotatef(100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.4F, -0.2F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonLeftHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L), new Transition((renderContext) -> {
         GL11.glScalef(1.5F, 1.5F, 3.0F);
         GL11.glTranslatef(0.7F, -0.04F, 0.17F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(-50.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
      }, 250L, 50L)}).withFirstPersonRightHandPositioningEjectSpentRound(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.4F, -0.2F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.35F, 0.06F, 0.01F);
         GL11.glRotatef(100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(60.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L), new Transition((renderContext) -> {
         GL11.glScalef(1.8F, 1.8F, 2.5F);
         GL11.glTranslatef(-0.4F, -0.2F, 0.3F);
         GL11.glRotatef(110.0F, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
      }, 350L, 1050L)}).build()).withSpawnEntityDamage(20.0F).withSpawnEntityGravityVelocity(0.0F).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
