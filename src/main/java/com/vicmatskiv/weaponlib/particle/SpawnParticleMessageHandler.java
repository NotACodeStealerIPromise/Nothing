package com.vicmatskiv.weaponlib.particle;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.particle.ExplosionSmokeFX;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessage;

public class SpawnParticleMessageHandler implements CompatibleMessageHandler {
   private ModContext modContext;
   private double yOffset = 1.0D;

   public SpawnParticleMessageHandler(ModContext modContext) {
      this.modContext = modContext;
   }

   public CompatibleMessage onCompatibleMessage(SpawnParticleMessage message, CompatibleMessageContext ctx) {
      if(!ctx.isServerSide()) {
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
            for(int i = 0; i < message.getCount(); ++i) {
               switch(null.$SwitchMap$com$vicmatskiv$weaponlib$particle$SpawnParticleMessage$ParticleType[message.getParticleType().ordinal()]) {
               case 1:
                  CompatibilityProvider.compatibility.addBreakingParticle(this.modContext, message.getPosX(), message.getPosY(), message.getPosZ());
                  break;
               case 2:
                  this.modContext.getEffectManager().spawnExplosionSmoke(message.getPosX(), message.getPosY(), message.getPosZ(), message.getMotionX(), message.getMotionY(), message.getMotionZ(), 0.2F * CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer()).rand.nextFloat(), 300, ExplosionSmokeFX.Behavior.SMOKE_GRENADE);
               }
            }

         });
      }

      return null;
   }
}
