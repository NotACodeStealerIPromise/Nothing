package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;

public abstract class CompatibleWeaponKeyInputHandler {
   @SubscribeEvent
   public final void onKeyInput(KeyInputEvent event) {
      this.onCompatibleKeyInput();
   }

   protected abstract void onCompatibleKeyInput();
}
