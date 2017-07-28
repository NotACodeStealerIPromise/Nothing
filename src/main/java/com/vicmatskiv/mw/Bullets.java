package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.models.Bullet44;
import com.vicmatskiv.mw.models.BulletBig;
import com.vicmatskiv.mw.models.SMAWRocket;
import com.vicmatskiv.mw.models.ShotgunShell;
import com.vicmatskiv.weaponlib.ItemBullet;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import org.lwjgl.opengl.GL11;

public class Bullets {
   public static ItemBullet ShotgunShell;
   public static ItemBullet Magnum44Ammo;
   public static ItemBullet Bullet762x39;
   public static ItemBullet Bullet556x39;
   public static ItemBullet Bullet556x45;
   public static ItemBullet Bullet762x54;
   public static ItemBullet Bullet762x51;
   public static ItemBullet Bullet45ACP;
   public static ItemBullet Bullet9mm;
   public static ItemBullet Bullet357;
   public static ItemBullet BMG50;
   public static ItemBullet Bullet57x28;
   public static ItemBullet Bullet46x30;
   public static ItemBullet Carbine30;
   public static ItemBullet Bullet65x39;
   public static ItemBullet Bullet10x24;
   public static ItemBullet Bullet10mm;
   public static ItemBullet Bullet300Blackout;
   public static ItemBullet SMAWRocket;
   public static ItemBullet Bullet50;
   public static ItemBullet Bullet9x39mm;
   public static ItemBullet BulletSpringfield3006;
   public static ItemBullet Bullet545x39;

   public static void init(Object mod, ConfigurationManager configurationManager, CompatibleFmlInitializationEvent event) {
      ShotgunShell = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("ShotgunShell").withModel(new ShotgunShell(), "ShotgunShell.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.2F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet9x39mm = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet9x39mm").withModel(new BulletBig(), "Gold.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.2F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      SMAWRocket = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("SMAWRocket").withMaxStackSize(1).withModel(new SMAWRocket(), "SMAW.png").withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.2F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.0D, 0.0D, 0.0D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.0D, 0.0D, 0.0D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, 0.8F, -2.1F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(0.6000000238418579D, 0.6000000238418579D, 0.6000000238418579D);
      }).withTextureName("Dummy.png").withCraftingRecipe(new Object[]{"AXX", Character.valueOf('X'), CommonProxy.SteelPlate, Character.valueOf('A'), CompatibleBlocks.TNT}).build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet10x24 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.FunGunsTab).withName("Bullet10x24").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.2F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.9F, -0.29F, 0.8F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Magnum44Ammo = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Magnum44Ammo").withModel(new Bullet44(), "Bullet44.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Carbine30 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Carbine30").withModel(new Bullet44(), "Bullet44.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.6000000238418579D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.5D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.7999999523162842D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      BulletSpringfield3006 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("BulletSpringfield3006").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.6000000238418579D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.5D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.7999999523162842D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet357 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet357").withModel(new Bullet44(), "Bullet44.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.6000000238418579D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.5D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.7999999523162842D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet50 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet50AE").withModel(new Bullet44(), "Bullet44.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.6000000238418579D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.5D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.7999999523162842D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet9mm = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet9mm").withModel(new Bullet44(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet10mm = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet10mm").withModel(new Bullet44(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet45ACP = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet45ACP").withModel(new Bullet44(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.30000001192092896D, 0.30000001192092896D, 0.30000001192092896D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.600000023841858D, 1.600000023841858D, 1.600000023841858D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet762x39 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet762x39").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.6000000238418579D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.399999976158142D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet46x30 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet46x30").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.6000000238418579D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.399999976158142D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet57x28 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet57x28").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.6000000238418579D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.399999976158142D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet556x39 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet556x39").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.30000001192092896D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.44999998807907104D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.2999999523162842D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet545x39 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet545x39").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.30000001192092896D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.44999998807907104D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.2999999523162842D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet556x45 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet556x45").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.4000000059604645D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.5D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.399999976158142D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet762x54 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet762x54").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.5D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.6000000238418579D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.5D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet762x51 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet762x51").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.44999998807907104D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.550000011920929D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.4500000476837158D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet300Blackout = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet300Blackout").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.44999998807907104D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.550000011920929D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.4500000476837158D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      BMG50 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("BMG50").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.MEDIUM, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.4000000059604645D, 0.699999988079071D, 0.4000000059604645D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.5D, 0.699999988079071D, 0.5D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.399999976158142D, 1.7999999523162842D, 1.399999976158142D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
      Bullet65x39 = (ItemBullet)(new ItemBullet.Builder()).withModId("mw").withCreativeTab(ModernWarfareMod.AmmoTab).withName("Bullet65x39").withModel(new BulletBig(), "Bullet.png").withCrafting(8, CraftingComplexity.LOW, new Object[]{"ingotCopper", CompatibleItems.GUNPOWDER}).withFirstPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(0.1F, -1.0F, 0.4F);
         GL11.glRotatef(30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glScaled(0.44999998807907104D, 0.44999998807907104D, 0.44999998807907104D);
      }).withThirdPersonPositioning((player, itemStack) -> {
         GL11.glTranslatef(-0.7F, -0.29F, 0.7F);
         GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(80.0F, 1.0F, 0.0F, 0.0F);
         GL11.glScaled(0.550000011920929D, 0.550000011920929D, 0.550000011920929D);
      }).withInventoryPositioning((itemStack) -> {
         GL11.glTranslatef(-0.8F, -0.45F, 0.4F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(2.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glScaled(1.4500000476837158D, 1.4500000476837158D, 1.4500000476837158D);
      }).withTextureName("Dummy.png").build(ModernWarfareMod.MOD_CONTEXT, ItemBullet.class);
   }
}
