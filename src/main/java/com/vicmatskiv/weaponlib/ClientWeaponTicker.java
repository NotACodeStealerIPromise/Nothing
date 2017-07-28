package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.SafeGlobals;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import java.util.concurrent.atomic.AtomicBoolean;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Mouse;

class ClientWeaponTicker extends Thread {
   boolean[] buttonsPressed = new boolean[2];
   long[] buttonsPressedTimestamps = new long[2];
   private AtomicBoolean running = new AtomicBoolean(true);
   private ClientModContext clientModContext;

   public ClientWeaponTicker(ClientModContext clientModContext) {
      this.clientModContext = clientModContext;
   }

   void shutdown() {
      this.running.set(false);
   }

   public void run() {
      SafeGlobals safeGlobals = this.clientModContext.getSafeGlobals();
      int currentItemIndex = safeGlobals.currentItemIndex.get();

      while(this.running.get()) {
         try {
            if(Mouse.isCreated()) {
               if(Mouse.isButtonDown(1)) {
                  if(!this.buttonsPressed[1]) {
                     this.buttonsPressed[1] = true;
                     this.buttonsPressedTimestamps[1] = System.currentTimeMillis();
                     if(!safeGlobals.guiOpen.get() && !this.isInteracting()) {
                        this.clientModContext.runSyncTick(this::onRightButtonDown);
                     }
                  }
               } else if(this.buttonsPressed[1]) {
                  this.buttonsPressed[1] = false;
                  this.clientModContext.runSyncTick(this::onRightButtonUp);
               }

               if(Mouse.isButtonDown(0)) {
                  currentItemIndex = safeGlobals.currentItemIndex.get();
                  if(!this.buttonsPressed[0]) {
                     this.buttonsPressed[0] = true;
                  }

                  if(!safeGlobals.guiOpen.get() && !this.isInteracting()) {
                     this.clientModContext.runSyncTick(this::onLeftButtonDown);
                  }
               } else if(this.buttonsPressed[0] || currentItemIndex != safeGlobals.currentItemIndex.get()) {
                  this.buttonsPressed[0] = false;
                  currentItemIndex = safeGlobals.currentItemIndex.get();
                  this.clientModContext.runSyncTick(this::onLeftButtonUp);
               }

               this.clientModContext.runSyncTick(this::onTick);
               Thread.sleep(10L);
            }
         } catch (InterruptedException var4) {
            break;
         }
      }

   }

   private void onLeftButtonUp() {
      EntityPlayer player = CompatibilityProvider.compatibility.getClientPlayer();
      Item item = this.getHeldItemMainHand(player);
      if(item instanceof Weapon) {
         ((Weapon)item).tryStopFire(player);
      } else if(item instanceof ItemGrenade) {
         ((ItemGrenade)item).attackUp(player, true);
      }

   }

   private void onRightButtonUp() {
      EntityPlayer player = CompatibilityProvider.compatibility.getClientPlayer();
      Item item = this.getHeldItemMainHand(player);
      if(item instanceof ItemGrenade) {
         ((ItemGrenade)item).attackUp(player, false);
      }

   }

   private void onLeftButtonDown() {
      EntityPlayer player = CompatibilityProvider.compatibility.getClientPlayer();
      Item item = this.getHeldItemMainHand(player);
      if(item instanceof Weapon) {
         ((Weapon)item).tryFire(player);
      } else if(item instanceof ItemMelee) {
         ((ItemMelee)item).attack(player, false);
      } else if(item instanceof ItemGrenade) {
         ((ItemGrenade)item).attack(player, true);
      }

   }

   private void onRightButtonDown() {
      EntityPlayer player = CompatibilityProvider.compatibility.getClientPlayer();
      Item item = this.getHeldItemMainHand(player);
      if(item instanceof Weapon) {
         ((Weapon)item).toggleAiming();
      } else if(item instanceof ItemMelee) {
         ((ItemMelee)item).attack(player, true);
      } else if(item instanceof ItemGrenade) {
         ((ItemGrenade)item).attack(player, false);
      }

   }

   private void onTick() {
      EntityPlayer player = CompatibilityProvider.compatibility.getClientPlayer();
      Item item = this.getHeldItemMainHand(player);
      if(item instanceof Updatable) {
         ((Updatable)item).update(player);
      }

   }

   private boolean isInteracting() {
      return false;
   }

   private Item getHeldItemMainHand(EntityPlayer player) {
      if(player == null) {
         return null;
      } else {
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
         return itemStack != null?itemStack.getItem():null;
      }
   }
}
