package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeAttackAspect;
import com.vicmatskiv.weaponlib.melee.TryAttackMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class TryAttackMessageHandler implements CompatibleMessageHandler {
   private MeleeAttackAspect attackAspect;

   public TryAttackMessageHandler(MeleeAttackAspect attackAspect) {
      this.attackAspect = attackAspect;
   }

   public CompatibleMessage onCompatibleMessage(TryAttackMessage message, CompatibleMessageContext ctx) {
      if(ctx.isServerSide()) {
         EntityPlayer player = ctx.getPlayer();
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
         if(itemStack != null && itemStack.getItem() instanceof ItemMelee) {
            ctx.runInMainThread(() -> {
               this.attackAspect.serverAttack(player, message.getInstance(), message.getEntity(CompatibilityProvider.compatibility.world(player)), message.isHeavyAttack());
            });
         }
      }

      return null;
   }
}
