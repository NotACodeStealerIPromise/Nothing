package com.vicmatskiv.weaponlib;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.UncheckedExecutionException;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.SyncManager;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.state.ManagedState;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerItemInstanceRegistry {
   private static final int CACHE_EXPIRATION_TIMEOUT_SECONDS = 5;
   private static final Logger logger = LogManager.getLogger(PlayerItemInstanceRegistry.class);
   private Map registry = new HashMap();
   private SyncManager syncManager;
   private Cache itemStackInstanceCache;

   public PlayerItemInstanceRegistry(SyncManager syncManager) {
      this.syncManager = syncManager;
      this.itemStackInstanceCache = CacheBuilder.newBuilder().weakKeys().maximumSize(1000L).expireAfterAccess(5L, TimeUnit.SECONDS).build();
   }

   public PlayerItemInstance getMainHandItemInstance(EntityPlayer player, Class targetClass) {
      if(player == null) {
         return null;
      } else {
         PlayerItemInstance instance = this.getItemInstance(player, CompatibilityProvider.compatibility.getCurrentInventoryItemIndex(player));
         return targetClass.isInstance(instance)?(PlayerItemInstance)targetClass.cast(instance):null;
      }
   }

   public PlayerItemInstance getMainHandItemInstance(EntityPlayer player) {
      return this.getItemInstance(player, CompatibilityProvider.compatibility.getCurrentInventoryItemIndex(player));
   }

   public PlayerItemInstance getItemInstance(EntityPlayer player, int slot) {
      Map slotInstances = (Map)this.registry.computeIfAbsent(player.getPersistentID(), (p) -> {
         return new HashMap();
      });
      PlayerItemInstance result = (PlayerItemInstance)slotInstances.get(Integer.valueOf(slot));
      if(result == null) {
         result = this.createItemInstance(player, slot);
         if(result != null) {
            slotInstances.put(Integer.valueOf(slot), result);
            this.syncManager.watch(result);
            result.markDirty();
         }
      } else {
         ItemStack slotItemStack = CompatibilityProvider.compatibility.getInventoryItemStack(player, slot);
         if(slotItemStack != null && slotItemStack.getItem() != result.getItem()) {
            this.syncManager.unwatch(result);
            result = this.createItemInstance(player, slot);
            if(result != null) {
               slotInstances.put(Integer.valueOf(slot), result);
               this.syncManager.watch(result);
               result.markDirty();
            }
         }

         if(result != null && result.getItemInventoryIndex() != slot) {
            logger.warn("Invalid instance slot id, correcting...");
            result.setItemInventoryIndex(slot);
         }

         if(result != null && result.getPlayer() != player) {
            logger.warn("Invalid player " + result.getPlayer() + " associated with instance in slot, changing to {}", new Object[]{player});
            result.setPlayer(player);
         }
      }

      return result;
   }

   private boolean isSameItem(PlayerItemInstance instance1, PlayerItemInstance instance2) {
      return Item.getIdFromItem(instance1.getItem()) == Item.getIdFromItem(instance2.getItem());
   }

   public boolean update(ManagedState newManagedState, PlayerItemInstance extendedStateToMerge) {
      Map slotContexts = (Map)this.registry.get(extendedStateToMerge.getPlayer().getUniqueID());
      boolean result = false;
      if(slotContexts != null) {
         PlayerItemInstance currentState = (PlayerItemInstance)slotContexts.get(Integer.valueOf(extendedStateToMerge.getItemInventoryIndex()));
         if(currentState != null && this.isSameItem(currentState, extendedStateToMerge)) {
            extendedStateToMerge.setState(newManagedState);
            if(newManagedState.commitPhase() != null) {
               currentState.prepareTransaction(extendedStateToMerge);
            } else {
               currentState.updateWith(extendedStateToMerge, true);
            }

            result = true;
         }
      }

      return result;
   }

   private PlayerItemInstance createItemInstance(EntityPlayer player, int slot) {
      ItemStack itemStack = CompatibilityProvider.compatibility.getInventoryItemStack(player, slot);
      PlayerItemInstance result = null;
      if(itemStack != null && itemStack.getItem() instanceof PlayerItemInstanceFactory) {
         logger.debug("Creating instance for slot {} from stack {}", new Object[]{Integer.valueOf(slot), itemStack});

         try {
            result = Tags.getInstance(itemStack);
         } catch (RuntimeException var6) {
            logger.debug("Failed to deserialize instance from {}", new Object[]{itemStack});
         }

         if(result == null) {
            result = ((PlayerItemInstanceFactory)itemStack.getItem()).createItemInstance(player, itemStack, slot);
         }

         result.setItemInventoryIndex(slot);
         result.setPlayer(player);
      }

      return result;
   }

   public PlayerItemInstance getItemInstance(EntityPlayer player, ItemStack itemStack) {
      Optional result = Optional.empty();

      try {
         result = (Optional)this.itemStackInstanceCache.get(itemStack, () -> {
            logger.debug("ItemStack {} not found in cache, initializing...", new Object[]{itemStack});
            PlayerItemInstance instance = null;
            int slot = -1;
            if(CompatibilityProvider.compatibility.clientPlayer() == player) {
               slot = CompatibilityProvider.compatibility.getInventorySlot(player, itemStack);
            }

            if(slot >= 0) {
               instance = this.getItemInstance(player, slot);
               logger.debug("Resolved item stack instance {} in slot {}", new Object[]{instance, Integer.valueOf(slot)});
            } else {
               try {
                  instance = Tags.getInstance(itemStack);
               } catch (RuntimeException var6) {
                  logger.error("Failed to deserialize instance from stack {}: {}", new Object[]{itemStack, var6.toString()});
               }
            }

            return Optional.ofNullable(instance);
         });
      } catch (ExecutionException | UncheckedExecutionException var5) {
         logger.error("Failed to initialize cache instance from {}", new Object[]{itemStack, var5.getCause()});
      }

      return (PlayerItemInstance)result.orElse((Object)null);
   }

   public void update(EntityPlayer player) {
      if(player != null) {
         Map slotContexts = (Map)this.registry.get(player.getPersistentID());
         if(slotContexts != null) {
            Iterator it = slotContexts.entrySet().iterator();

            while(true) {
               Entry e;
               ItemStack slotStack;
               do {
                  if(!it.hasNext()) {
                     return;
                  }

                  e = (Entry)it.next();
                  slotStack = CompatibilityProvider.compatibility.getInventoryItemStack(player, ((Integer)e.getKey()).intValue());
               } while(slotStack != null && slotStack.getItem() == ((PlayerItemInstance)e.getValue()).getItem());

               logger.debug("Removing {} from slot {}", new Object[]{e.getValue(), e.getKey()});
               this.syncManager.unwatch((PlayerItemInstance)e.getValue());
               it.remove();
            }
         }
      }
   }
}
