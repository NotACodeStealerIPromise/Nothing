package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;

public abstract class CompatibleGui extends Gui {
   @SubscribeEvent
   public final void onRenderHud(Pre event) {
      this.onCompatibleRenderHud(event);
   }

   public abstract void onCompatibleRenderHud(Pre var1);

   @SubscribeEvent
   public final void onRenderCrosshair(Pre event) {
      this.onCompatibleRenderCrosshair(event);
   }

   public abstract void onCompatibleRenderCrosshair(Pre var1);
}
