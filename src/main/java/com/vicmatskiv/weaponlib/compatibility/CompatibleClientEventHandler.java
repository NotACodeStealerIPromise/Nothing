package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleClientTickEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class CompatibleClientEventHandler {
   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public final void onClientTick(ClientTickEvent event) {
      this.onCompatibleClientTick(new CompatibleClientTickEvent(event));
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public final void onRenderTickEvent(RenderTickEvent event) {
      this.onCompatibleRenderTickEvent(new CompatibleRenderTickEvent(event));
   }

   protected abstract void onCompatibleRenderTickEvent(CompatibleRenderTickEvent var1);

   protected abstract void onCompatibleClientTick(CompatibleClientTickEvent var1);

   protected abstract ModContext getModContext();
}
