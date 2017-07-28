package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.SafeGlobals;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleClientEventHandler;
import com.vicmatskiv.weaponlib.compatibility.CompatibleClientTickEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderTickEvent;
import com.vicmatskiv.weaponlib.perspective.Perspective;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientEventHandler extends CompatibleClientEventHandler {
   private static final UUID SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER_UUID = UUID.fromString("8efa8469-0256-4f8e-bdd9-3e7b23970663");
   private static final AttributeModifier SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER;
   private static final Logger logger;
   private Lock mainLoopLock = new ReentrantLock();
   private SafeGlobals safeGlobals;
   private Queue runInClientThreadQueue;
   private long renderEndNanoTime;
   private ClientModContext modContext;

   public ClientEventHandler(ClientModContext modContext, Lock mainLoopLock, SafeGlobals safeGlobals, Queue runInClientThreadQueue) {
      this.modContext = modContext;
      this.mainLoopLock = mainLoopLock;
      this.safeGlobals = safeGlobals;
      this.runInClientThreadQueue = runInClientThreadQueue;
      this.renderEndNanoTime = System.nanoTime();
   }

   public void onCompatibleClientTick(CompatibleClientTickEvent event) {
      if(event.getPhase() == CompatibleClientTickEvent.Phase.START) {
         this.mainLoopLock.lock();
      } else if(event.getPhase() == CompatibleClientTickEvent.Phase.END) {
         this.update();
         this.modContext.getSyncManager().run();
         PlayerEntityTracker tracker = PlayerEntityTracker.getTracker(CompatibilityProvider.compatibility.clientPlayer());
         if(tracker != null) {
            tracker.update();
         }

         this.mainLoopLock.unlock();
         this.processRunInClientThreadQueue();
         this.safeGlobals.objectMouseOver.set(CompatibilityProvider.compatibility.getObjectMouseOver());
         if(CompatibilityProvider.compatibility.clientPlayer() != null) {
            this.safeGlobals.currentItemIndex.set(CompatibilityProvider.compatibility.clientPlayer().inventory.currentItem);
         }
      }

   }

   private void update() {
      EntityPlayer player = CompatibilityProvider.compatibility.clientPlayer();
      this.modContext.getPlayerItemInstanceRegistry().update(player);
      PlayerWeaponInstance mainHandHeldWeaponInstance = this.modContext.getMainHeldWeapon();
      if(mainHandHeldWeaponInstance != null) {
         if(player.isSprinting()) {
            mainHandHeldWeaponInstance.setAimed(false);
         }

         if(mainHandHeldWeaponInstance.isAimed()) {
            this.slowPlayerDown(player);
         } else {
            this.restorePlayerSpeed(player);
         }
      } else if(player != null) {
         this.restorePlayerSpeed(player);
      }

   }

   private void restorePlayerSpeed(EntityPlayer entityPlayer) {
      if(entityPlayer.getEntityAttribute(CompatibilityProvider.compatibility.getMovementSpeedAttribute()).getModifier(SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER.getID()) != null) {
         entityPlayer.getEntityAttribute(CompatibilityProvider.compatibility.getMovementSpeedAttribute()).removeModifier(SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER);
      }

   }

   private void slowPlayerDown(EntityPlayer entityPlayer) {
      if(entityPlayer.getEntityAttribute(CompatibilityProvider.compatibility.getMovementSpeedAttribute()).getModifier(SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER.getID()) == null) {
         entityPlayer.getEntityAttribute(CompatibilityProvider.compatibility.getMovementSpeedAttribute()).applyModifier(SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER);
      }

   }

   private void processRunInClientThreadQueue() {
      Runnable r;
      while((r = (Runnable)this.runInClientThreadQueue.poll()) != null) {
         r.run();
      }

   }

   protected void onCompatibleRenderTickEvent(CompatibleRenderTickEvent event) {
      if(event.getPhase() == CompatibleRenderTickEvent.Phase.START && CompatibilityProvider.compatibility.clientPlayer() != null) {
         PlayerItemInstance instance = this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(CompatibilityProvider.compatibility.clientPlayer());
         if(instance != null) {
            Perspective view = this.modContext.getViewManager().getPerspective(instance, true);
            if(view != null) {
               view.update(event);
            }
         }
      } else if(event.getPhase() == CompatibleRenderTickEvent.Phase.END) {
         this.safeGlobals.renderingPhase.set((Object)null);
      }

   }

   protected ModContext getModContext() {
      return this.modContext;
   }

   static {
      SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER = (new AttributeModifier(SLOW_DOWN_WHILE_ZOOMING_ATTRIBUTE_MODIFIER_UUID, "Slow Down While Zooming", -0.5D, 2)).setSaved(false);
      logger = LogManager.getLogger(ClientEventHandler.class);
   }
}
