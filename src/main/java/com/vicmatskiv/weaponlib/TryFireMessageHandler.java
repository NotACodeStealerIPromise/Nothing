package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.TryFireMessage;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponFireAspect;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TryFireMessageHandler implements CompatibleMessageHandler {
   private WeaponFireAspect fireManager;

   TryFireMessageHandler(WeaponFireAspect fireManager) {
      this.fireManager = fireManager;
   }

   public CompatibleMessage onCompatibleMessage(TryFireMessage message, CompatibleMessageContext ctx) {
      if(ctx.isServerSide()) {
         EntityPlayer player = ctx.getPlayer();
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
         if(itemStack != null && itemStack.getItem() instanceof Weapon && message.isOn()) {
            ctx.runInMainThread(() -> {
               this.fireManager.serverFire(player, itemStack);
            });
         }
      }

      return null;
   }
}
