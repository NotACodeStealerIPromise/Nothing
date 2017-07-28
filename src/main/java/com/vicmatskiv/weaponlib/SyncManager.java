package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.PermitManager;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SyncManager {
   private static final Logger logger = LogManager.getLogger(SyncManager.class);
   private PermitManager permitManager;
   private Map watchables = new LinkedHashMap();
   private long syncTimeout = 10000L;

   public SyncManager(PermitManager permitManager) {
      this.permitManager = permitManager;
      this.permitManager.registerEvaluator(Permit.class, PlayerItemInstance.class, this::syncOnServer);
   }

   private void syncOnServer(Permit permit, PlayerItemInstance instance) {
      logger.debug("Syncing {} in state {} on server", new Object[]{instance, instance.getState()});
      ItemStack itemStack = instance.getItemStack();
      if(itemStack != null) {
         logger.debug("Stored {} in stack {}", new Object[]{instance, itemStack});
         Tags.setInstance(itemStack, instance);
      }

   }

   public void watch(PlayerItemInstance watchableInstance) {
      this.watchables.put(watchableInstance, Long.valueOf(watchableInstance.getUpdateId()));
   }

   public void unwatch(PlayerItemInstance watchableInstance) {
      this.watchables.remove(watchableInstance);
   }

   public void run() {
      List instancesToUpdate = (List)this.watchables.entrySet().stream().filter((e) -> {
         return ((PlayerItemInstance)e.getKey()).getUpdateId() != ((Long)e.getValue()).longValue() && ((PlayerItemInstance)e.getKey()).getSyncStartTimestamp() + this.syncTimeout < System.currentTimeMillis();
      }).map((e) -> {
         return (PlayerItemInstance)e.getKey();
      }).collect(Collectors.toList());
      instancesToUpdate.forEach(this::sync);
   }

   private void sync(PlayerItemInstance watchable) {
      logger.debug("Syncing {} in state {} with update id {} to server", new Object[]{watchable, watchable.getState(), Long.valueOf(watchable.getUpdateId())});
      long updateId = watchable.getUpdateId();
      watchable.setSyncStartTimestamp(System.currentTimeMillis());
      this.permitManager.request(new Permit(watchable.getState()), watchable, (p, e) -> {
         this.watchables.put(watchable, Long.valueOf(updateId));
         watchable.setSyncStartTimestamp(0L);
         logger.debug("Completed syncing {} with update id {}", new Object[]{watchable, Long.valueOf(updateId)});
      });
   }
}
