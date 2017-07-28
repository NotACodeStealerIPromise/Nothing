package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import net.minecraft.client.settings.KeyBinding;

public class KeyBindings {
   public static KeyBinding reloadKey;
   public static KeyBinding attachmentKey;
   public static KeyBinding upArrowKey;
   public static KeyBinding downArrowKey;
   public static KeyBinding leftArrowKey;
   public static KeyBinding rightArrowKey;
   public static KeyBinding laserSwitchKey;
   public static KeyBinding addKey;
   public static KeyBinding subtractKey;
   public static KeyBinding fireModeKey;
   public static KeyBinding jDebugKey;
   public static KeyBinding kDebugKey;
   public static KeyBinding minusDebugKey;
   public static KeyBinding equalsDebugKey;
   public static KeyBinding lBracketDebugKey;
   public static KeyBinding rBracketDebugKey;
   public static KeyBinding semicolonDebugKey;
   public static KeyBinding apostropheDebugKey;
   public static KeyBinding deleteDebugKey;

   public static void init() {
      reloadKey = new KeyBinding("key.reload", 19, "key.categories.weaponlib");
      laserSwitchKey = new KeyBinding("key.laser", 38, "key.categories.weaponlib");
      attachmentKey = new KeyBinding("key.attachment", 50, "key.categories.weaponlib");
      upArrowKey = new KeyBinding("key.scope", 200, "key.categories.weaponlib");
      downArrowKey = new KeyBinding("key.recoil_fitter", 208, "key.categories.weaponlib");
      leftArrowKey = new KeyBinding("key.silencer", 203, "key.categories.weaponlib");
      rightArrowKey = new KeyBinding("key.texture_change", 205, "key.categories.weaponlib");
      addKey = new KeyBinding("key.add", 23, "key.categories.weaponlib");
      subtractKey = new KeyBinding("key.subtract", 24, "key.categories.weaponlib");
      fireModeKey = new KeyBinding("key.fire_mode", 54, "key.categories.weaponlib");
      CompatibilityProvider.compatibility.registerKeyBinding(reloadKey);
      CompatibilityProvider.compatibility.registerKeyBinding(attachmentKey);
      CompatibilityProvider.compatibility.registerKeyBinding(upArrowKey);
      CompatibilityProvider.compatibility.registerKeyBinding(downArrowKey);
      CompatibilityProvider.compatibility.registerKeyBinding(leftArrowKey);
      CompatibilityProvider.compatibility.registerKeyBinding(rightArrowKey);
      CompatibilityProvider.compatibility.registerKeyBinding(laserSwitchKey);
      CompatibilityProvider.compatibility.registerKeyBinding(addKey);
      CompatibilityProvider.compatibility.registerKeyBinding(subtractKey);
      CompatibilityProvider.compatibility.registerKeyBinding(fireModeKey);
      if(DebugPositioner.isDebugModeEnabled()) {
         bindDebugKeys();
      }

   }

   public static void bindDebugKeys() {
      jDebugKey = new KeyBinding("key.jDebugKey", 36, "key.categories.weaponlib");
      kDebugKey = new KeyBinding("key.klDebugKey", 37, "key.categories.weaponlib");
      minusDebugKey = new KeyBinding("key.minusDebugKey", 12, "key.categories.weaponlib");
      equalsDebugKey = new KeyBinding("key.equalsDebugKey", 13, "key.categories.weaponlib");
      lBracketDebugKey = new KeyBinding("key.lBracketDebugKey", 26, "key.categories.weaponlib");
      rBracketDebugKey = new KeyBinding("key.rBracketDebugKey", 27, "key.categories.weaponlib");
      semicolonDebugKey = new KeyBinding("key.semicolonDebugKey", 39, "key.categories.weaponlib");
      apostropheDebugKey = new KeyBinding("key.apostropheDebugKey", 40, "key.categories.weaponlib");
      deleteDebugKey = new KeyBinding("key.deleteDebugKey", 14, "key.categories.weaponlib");
      CompatibilityProvider.compatibility.registerKeyBinding(jDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(kDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(lBracketDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(rBracketDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(semicolonDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(apostropheDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(minusDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(equalsDebugKey);
      CompatibilityProvider.compatibility.registerKeyBinding(deleteDebugKey);
   }
}
