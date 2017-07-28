package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.grenade.GrenadeAttackAspect;
import com.vicmatskiv.weaponlib.grenade.GrenadeMessage;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GrenadeMessageHandler implements CompatibleMessageHandler {
   private GrenadeAttackAspect attackAspect;

   public GrenadeMessageHandler(GrenadeAttackAspect attackAspect) {
      this.attackAspect = attackAspect;
   }

   public CompatibleMessage onCompatibleMessage(GrenadeMessage message, CompatibleMessageContext ctx) {
      if(ctx.isServerSide()) {
         EntityPlayer player = ctx.getPlayer();
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
         if(itemStack != null && itemStack.getItem() instanceof ItemGrenade) {
            ctx.runInMainThread(() -> {
               message.getInstance().setPlayer(player);
               this.attackAspect.serverThrowGrenade(player, message.getInstance(), message.getActivationTimestamp());
            });
         }
      }

      return null;
   }
}
