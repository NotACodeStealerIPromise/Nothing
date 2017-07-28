package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Contextual;
import com.vicmatskiv.weaponlib.EntityBounceable;
import com.vicmatskiv.weaponlib.EntityProjectile;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityJoinWorldEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleServerEventHandler;
import com.vicmatskiv.weaponlib.compatibility.CompatibleStartTrackingEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleStopTrackingEvent;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import com.vicmatskiv.weaponlib.tracking.SyncPlayerEntityTrackerMessage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerEventHandler extends CompatibleServerEventHandler {
   private static final Logger logger = LogManager.getLogger(ServerEventHandler.class);
   private ModContext modContext;
   private String modId;

   public ServerEventHandler(ModContext modContext, String modId) {
      this.modContext = modContext;
      this.modId = modId;
   }

   protected void onCompatibleItemToss(ItemTossEvent itemTossEvent) {
   }

   protected void onCompatibleEntityJoinWorld(CompatibleEntityJoinWorldEvent e) {
      if(e.getEntity() instanceof Contextual) {
         ((Contextual)e.getEntity()).setContext(this.modContext);
      }

      if(e.getEntity() instanceof EntityPlayerMP && !e.getWorld().isRemote) {
         logger.debug("Player {} joined the world", new Object[]{e.getEntity()});
         PlayerEntityTracker tracker = PlayerEntityTracker.getTracker((EntityPlayer)e.getEntity());
         if(tracker != null) {
            this.modContext.getChannel().getChannel().sendTo(new SyncPlayerEntityTrackerMessage(tracker), (EntityPlayerMP)e.getEntity());
         }
      }

   }

   protected void onCompatiblePlayerStartedTracking(CompatibleStartTrackingEvent e) {
      if(!(e.getTarget() instanceof EntityProjectile) && !(e.getTarget() instanceof EntityBounceable)) {
         PlayerEntityTracker tracker = PlayerEntityTracker.getTracker((EntityPlayer)e.getEntity());
         if(tracker != null && tracker.updateTrackableEntity(e.getTarget())) {
            logger.debug("Player {} started tracking {} with uuid {}", new Object[]{e.getPlayer(), e.getTarget(), e.getTarget().getUniqueID()});
            this.modContext.getChannel().getChannel().sendTo(new SyncPlayerEntityTrackerMessage(tracker), (EntityPlayerMP)e.getPlayer());
         }

      }
   }

   protected void onCompatiblePlayerStoppedTracking(CompatibleStopTrackingEvent e) {
      if(!(e.getTarget() instanceof EntityProjectile) && !(e.getTarget() instanceof EntityBounceable)) {
         PlayerEntityTracker tracker = PlayerEntityTracker.getTracker((EntityPlayer)e.getEntity());
         if(tracker != null && tracker.updateTrackableEntity(e.getTarget())) {
            logger.debug("Player {} stopped tracking {}", new Object[]{e.getPlayer(), e.getTarget()});
            this.modContext.getChannel().getChannel().sendTo(new SyncPlayerEntityTrackerMessage(tracker), (EntityPlayerMP)e.getPlayer());
         }

      }
   }

   protected void onCompatibleLivingDeathEvent(LivingDeathEvent e) {
   }

   public String getModId() {
      return this.modId;
   }
}
