package com.vicmatskiv.weaponlib.network;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageHandler;
import com.vicmatskiv.weaponlib.network.PermitMessage;
import com.vicmatskiv.weaponlib.state.ExtendedState;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.PermitManager;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import net.minecraft.entity.player.EntityPlayerMP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetworkPermitManager implements PermitManager, CompatibleMessageHandler {
   private static final Logger logger = LogManager.getLogger(NetworkPermitManager.class);
   private ModContext modContext;
   private Map permitCallbacks = new HashMap();
   private Map evaluators = new HashMap();

   public NetworkPermitManager(ModContext modContext) {
      this.modContext = modContext;
   }

   public void request(Permit permit, ExtendedState extendedState, BiConsumer callback) {
      this.permitCallbacks.put(permit.getUuid(), callback);
      this.modContext.getChannel().getChannel().sendToServer(new PermitMessage(permit, extendedState));
   }

   public void registerEvaluator(Class permitClass, Class esClass, BiConsumer evaluator) {
      this.evaluators.put(permitClass, (p, c) -> {
         logger.debug("Processing permit {} for instance {}", new Object[]{p, c});
         evaluator.accept(permitClass.cast(p), esClass.cast(c));
      });
   }

   public CompatibleMessage onCompatibleMessage(PermitMessage permitMessage, CompatibleMessageContext ctx) {
      Permit permit = permitMessage.getPermit();
      Object extendedState = permitMessage.getContext();
      if(ctx.isServerSide()) {
         if(extendedState instanceof PlayerContext) {
            ((PlayerContext)extendedState).setPlayer(ctx.getPlayer());
         }

         ctx.runInMainThread(() -> {
            BiConsumer evaluator = (BiConsumer)this.evaluators.get(permit.getClass());
            if(evaluator != null) {
               evaluator.accept(permit, extendedState);
            }

            PermitMessage message = new PermitMessage(permit, extendedState);
            this.modContext.getChannel().getChannel().sendTo(message, (EntityPlayerMP)ctx.getPlayer());
         });
      } else {
         CompatibilityProvider.compatibility.runInMainClientThread(() -> {
            if(extendedState instanceof PlayerContext) {
               ((PlayerContext)extendedState).setPlayer(CompatibilityProvider.compatibility.clientPlayer());
            }

            BiConsumer callback = (BiConsumer)this.permitCallbacks.remove(permit.getUuid());
            if(callback != null) {
               callback.accept(permit, extendedState);
            }

         });
      }

      return null;
   }
}
