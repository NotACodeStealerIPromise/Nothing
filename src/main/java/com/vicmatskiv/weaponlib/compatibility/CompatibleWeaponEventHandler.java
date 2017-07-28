package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityJoinWorldEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public abstract class CompatibleWeaponEventHandler {
   @SubscribeEvent
   public final void onGuiOpenEvent(GuiOpenEvent event) {
      this.onCompatibleGuiOpenEvent(event);
   }

   protected abstract void onCompatibleGuiOpenEvent(GuiOpenEvent var1);

   @SubscribeEvent
   public final void zoom(FOVUpdateEvent event) {
      this.compatibleZoom(event);
   }

   protected abstract void compatibleZoom(FOVUpdateEvent var1);

   @SubscribeEvent
   public final void onMouse(MouseEvent event) {
      this.onCompatibleMouse(event);
   }

   protected abstract void onCompatibleMouse(MouseEvent var1);

   @SubscribeEvent
   public final void handleRenderLivingEvent(Pre event) {
      this.onCompatibleHandleRenderLivingEvent(event);
   }

   protected abstract void onCompatibleHandleRenderLivingEvent(Pre var1);

   @SubscribeEvent
   public void onTextureStitchEvent(net.minecraftforge.client.event.TextureStitchEvent.Pre event) {
      event.map.registerIcon(this.getModContext().getNamedResource("particle/blood").toString());
   }

   @SideOnly(Side.CLIENT)
   @SubscribeEvent
   public final void onEntityJoinWorldEvent(EntityJoinWorldEvent event) {
      this.onCompatibleEntityJoinedWorldEvent(new CompatibleEntityJoinWorldEvent(event));
   }

   protected abstract void onCompatibleEntityJoinedWorldEvent(CompatibleEntityJoinWorldEvent var1);

   protected abstract ModContext getModContext();
}
