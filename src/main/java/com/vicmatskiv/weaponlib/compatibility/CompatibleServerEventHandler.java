package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityJoinWorldEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleStartTrackingEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleStopTrackingEvent;
import com.vicmatskiv.weaponlib.compatibility.ExtendedPlayerProperties;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.ServerTickEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.StartTracking;
import net.minecraftforge.event.entity.player.PlayerEvent.StopTracking;

public abstract class CompatibleServerEventHandler {
   @SubscribeEvent
   public void onItemToss(ItemTossEvent itemTossEvent) {
      this.onCompatibleItemToss(itemTossEvent);
   }

   protected abstract void onCompatibleItemToss(ItemTossEvent var1);

   @SubscribeEvent
   public void onTick(ServerTickEvent event) {
      if(event.phase == Phase.START) {
         ;
      }

   }

   @SubscribeEvent
   public void onEntityConstructing(EntityConstructing event) {
      if(event.entity instanceof EntityPlayer) {
         ExtendedPlayerProperties.init((EntityPlayer)event.entity);
      }

   }

   @SubscribeEvent
   public void onEntityJoinWorld(EntityJoinWorldEvent e) {
      this.onCompatibleEntityJoinWorld(new CompatibleEntityJoinWorldEvent(e));
   }

   protected abstract void onCompatibleEntityJoinWorld(CompatibleEntityJoinWorldEvent var1);

   @SubscribeEvent
   public void playerStartedTracking(StartTracking e) {
      this.onCompatiblePlayerStartedTracking(new CompatibleStartTrackingEvent(e));
   }

   @SubscribeEvent
   public void playerStoppedTracking(StopTracking e) {
      this.onCompatiblePlayerStoppedTracking(new CompatibleStopTrackingEvent(e));
   }

   protected abstract void onCompatiblePlayerStartedTracking(CompatibleStartTrackingEvent var1);

   public abstract String getModId();

   protected abstract void onCompatiblePlayerStoppedTracking(CompatibleStopTrackingEvent var1);

   protected abstract void onCompatibleLivingDeathEvent(LivingDeathEvent var1);
}
