package com.vicmatskiv.mw.items.melee;

import com.vicmatskiv.mw.CommonProxy;
import com.vicmatskiv.mw.MeleeSkins;
import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.items.melee.MeleeFactory;
import com.vicmatskiv.mw.models.TacKnifeStandard;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeRenderer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.item.Item;
import org.lwjgl.opengl.GL11;

public class TestMeleeFactory implements MeleeFactory {
   public Item createMelee(CommonProxy commonProxy) {
      return (new ItemMelee.Builder()).withModId("mw").withName("TacKnifeStandard").withCreativeTab(ModernWarfareMod.gunsTab).withTextureNames(new String[]{"TacKnifeStandard"}).withAttackDamage(8.5F).withHeavyAttackDamage(17.0F).withPrepareStubTimeout(() -> {
         return Integer.valueOf(100);
      }).withPrepareHeavyStubTimeout(() -> {
         return Integer.valueOf(530);
      }).withAttackCooldownTimeout(() -> {
         return Integer.valueOf(500);
      }).withHeavyAttackCooldownTimeout(() -> {
         return Integer.valueOf(1500);
      }).withAttackSound("swoosh").withHeavyAttackSound("swoosh").withCompatibleSkin(MeleeSkins.CrimsonBlood, "TacKnifeCrimsonBlood").withCompatibleSkin(MeleeSkins.Chrome, "TacKnifeChrome").withCompatibleSkin(MeleeSkins.GodWillsIt, "TacKnifeGodWillsIt").withCompatibleSkin(MeleeSkins.Murasaki, "TacKnifeMurasaki").withCompatibleSkin(MeleeSkins.Evangelion, "TacKnifeEvangelion").withCraftingRecipe(new Object[]{" X ", " X ", " F ", Character.valueOf('X'), CommonProxy.SteelPlate, Character.valueOf('F'), CompatibleItems.STICK}).withRenderer((new MeleeRenderer.Builder()).withModId("mw").withModel(new TacKnifeStandard()).withAnimationDuration(200).withInventoryPositioning((itemStack) -> {
         GL11.glScaled(0.699999988079071D, 0.699999988079071D, 0.699999988079071D);
         GL11.glTranslatef(1.0F, 1.3F, -1.3F);
         GL11.glRotatef(230.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
      }).withThirdPersonPositioning((renderContext) -> {
         GL11.glScaled(0.800000011920929D, 0.800000011920929D, 0.800000011920929D);
         GL11.glTranslatef(-1.2F, -0.45F, 1.0F);
         GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
      }).withFirstPersonPositioning((context) -> {
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-1.0F, -0.5F, -1.0F);
      }).withFirstPersonHandPositioning((context) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.175F, -0.525F, 0.425F);
      }, (context) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-70.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.225F, -0.4F, -0.05F);
      }).withFirstPersonPositioningAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.7F, -0.3F, -1.0F);
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 0.0F, 0.0F, 1.0F);
      }, 80L, 0L), new Transition((renderContext) -> {
         GL11.glTranslatef(-0.7F, -0.3F, -1.0F);
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-30.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-80.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.7F, 0.4F, -1.0F);
      }, 40L, 50L)}).withFirstPersonLeftHandPositioningAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.1F, -0.95F, 0.425F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.1F, -0.95F, 0.425F);
      }, 70L, 0L)}).withFirstPersonRightHandPositioningAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.375F, -0.2F, 0.0F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-50.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.35F, 0.05F, 0.025F);
      }, 70L, 0L)}).withFirstPersonPositioningHeavyAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glTranslatef(-0.7F, -0.3F, -1.0F);
         GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-110.0F, 0.0F, 0.0F, 1.0F);
      }, 180L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.675F, 0.075F, -1.574999F);
      }, 200L, 150L), new Transition((renderContext) -> {
         GL11.glScalef(1.0F, 1.0F, 1.0F);
         GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-25.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-100.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.65F, -2.0F, -1.649999F);
      }, 70L, 70L)}).withFirstPersonLeftHandPositioningHeavyAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(75.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.1F, -0.95F, 0.425F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-140.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.05F, -0.9F, -0.2F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(85.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.05F, -0.75F, 0.15F);
      }, 70L, 0L)}).withFirstPersonRightHandPositioningHeavyAttacking(new Transition[]{new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-95.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.375F, -0.2F, 0.0F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(20.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-45.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.525F, -0.025F, 0.075F);
      }, 70L, 0L), new Transition((renderContext) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-110.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(15.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-35.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.3F, 0.0F, 0.075F);
      }, 70L, 0L)}).withFirstPersonPositioningModifying((renderContext) -> {
         GL11.glRotatef(-20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.5F, -0.5F, -1.5F);
      }).withFirstPersonHandPositioningModifying((context) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-120.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(10.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-0.0F, -1.0F, 1.0F);
      }, (context) -> {
         GL11.glScalef(3.0F, 3.0F, 3.0F);
         GL11.glRotatef(-105.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(-30.0F, 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(0.275F, -0.125F, -0.05F);
      }).build()).build(ModernWarfareMod.MOD_CONTEXT);
   }
}
