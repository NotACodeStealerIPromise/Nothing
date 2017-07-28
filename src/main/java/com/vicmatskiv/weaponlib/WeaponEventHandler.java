package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Contextual;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.RenderingPhase;
import com.vicmatskiv.weaponlib.SafeGlobals;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityJoinWorldEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWeaponEventHandler;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderLivingEvent.Pre;

public class WeaponEventHandler extends CompatibleWeaponEventHandler {
   private SafeGlobals safeGlobals;
   private ModContext modContext;

   public WeaponEventHandler(ModContext modContext, SafeGlobals safeGlobals) {
      this.modContext = modContext;
      this.safeGlobals = safeGlobals;
   }

   public void onCompatibleGuiOpenEvent(GuiOpenEvent event) {
      this.safeGlobals.guiOpen.set(CompatibilityProvider.compatibility.getGui(event) != null);
   }

   public void compatibleZoom(FOVUpdateEvent event) {
      PlayerWeaponInstance instance = this.modContext.getMainHeldWeapon();
      if(instance != null) {
         float fov;
         if(instance.isAttachmentZoomEnabled()) {
            if(this.safeGlobals.renderingPhase.get() == RenderingPhase.RENDER_PERSPECTIVE) {
               fov = instance.getZoom();
            } else {
               fov = CompatibilityProvider.compatibility.isFlying(CompatibilityProvider.compatibility.clientPlayer())?1.1F:1.0F;
            }
         } else {
            fov = CompatibilityProvider.compatibility.isFlying(CompatibilityProvider.compatibility.clientPlayer())?1.1F:1.0F;
         }

         CompatibilityProvider.compatibility.setNewFov(event, fov);
      }

   }

   public void onCompatibleMouse(MouseEvent event) {
      if(CompatibilityProvider.compatibility.getButton(event) == 0 || CompatibilityProvider.compatibility.getButton(event) == 1) {
         PlayerItemInstance instance = this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(CompatibilityProvider.compatibility.clientPlayer());
         if(instance instanceof PlayerWeaponInstance || instance instanceof PlayerMeleeInstance || instance instanceof PlayerGrenadeInstance) {
            event.setCanceled(true);
         }
      }

   }

   public void onCompatibleHandleRenderLivingEvent(Pre event) {
      if(!event.isCanceled() && CompatibilityProvider.compatibility.getEntity(event) instanceof EntityPlayer) {
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(CompatibilityProvider.compatibility.getEntity(event));
         if(itemStack != null && itemStack.getItem() instanceof Weapon) {
            RenderPlayer rp = CompatibilityProvider.compatibility.getRenderer(event);
            if(itemStack != null) {
               PlayerItemInstance instance = this.modContext.getPlayerItemInstanceRegistry().getItemInstance((EntityPlayer)CompatibilityProvider.compatibility.getEntity(event), itemStack);
               if(instance instanceof PlayerWeaponInstance) {
                  PlayerWeaponInstance weaponInstance = (PlayerWeaponInstance)instance;
                  CompatibilityProvider.compatibility.setAimed(rp, weaponInstance.isAimed() || weaponInstance.getState() == WeaponState.FIRING || weaponInstance.getState() == WeaponState.RECOILED || weaponInstance.getState() == WeaponState.PAUSED);
               }
            }
         }

      }
   }

   protected void onCompatibleEntityJoinedWorldEvent(CompatibleEntityJoinWorldEvent compatibleEntityJoinWorldEvent) {
      if(compatibleEntityJoinWorldEvent.getEntity() instanceof Contextual) {
         ((Contextual)compatibleEntityJoinWorldEvent.getEntity()).setContext(this.modContext);
      }

   }

   protected ModContext getModContext() {
      return this.modContext;
   }
}
