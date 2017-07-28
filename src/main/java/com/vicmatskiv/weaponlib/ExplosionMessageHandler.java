package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Explosion;
import com.vicmatskiv.weaponlib.ExplosionMessage;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ExplosionMessageHandler implements CompatibleMessageHandler {
   private ModContext modContext;

   public ExplosionMessageHandler(ModContext modContext) {
      this.modContext = modContext;
   }

   public CompatibleMessage onCompatibleMessage(ExplosionMessage message, CompatibleMessageContext ctx) {
      if(!ctx.isServerSide()) {
         EntityPlayer player = CompatibilityProvider.compatibility.clientPlayer();
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
            Explosion explosion = new Explosion(this.modContext, CompatibilityProvider.compatibility.world(player), (Entity)null, message.getPosX(), message.getPosY(), message.getPosZ(), message.getStrength(), message.getAffectedBlockPositions());
            explosion.doExplosionB(true);
            player.motionX += (double)message.getMotionX();
            player.motionY += (double)message.getMotionY();
            player.motionZ += (double)message.getMotionZ();
         });
      }

      return null;
   }
}
