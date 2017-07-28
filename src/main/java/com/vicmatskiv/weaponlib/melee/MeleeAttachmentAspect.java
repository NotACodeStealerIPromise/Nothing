package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeState;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.Aspect;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;
import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class MeleeAttachmentAspect implements Aspect {
   private static final Logger logger = LogManager.getLogger(MeleeAttachmentAspect.class);
   private ModContext modContext;
   private PermitManager permitManager;
   private StateManager stateManager;
   private long clickSpammingTimeout = 100L;
   private Predicate clickSpammingPreventer = (es) -> {
      return System.currentTimeMillis() >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout;
   };
   private Collection allowedUpdateFromStates;

   public MeleeAttachmentAspect(ModContext modContext) {
      this.allowedUpdateFromStates = Arrays.asList(new MeleeState[]{MeleeState.MODIFYING_REQUESTED});
      this.modContext = modContext;
   }

   public void setStateManager(StateManager stateManager) {
      if(this.permitManager == null) {
         throw new IllegalStateException("Permit manager not initialized");
      } else {
         this.stateManager = stateManager.in(this).change(MeleeState.READY).to(MeleeState.MODIFYING).when(this.clickSpammingPreventer).withPermit((s, es) -> {
            return new MeleeAttachmentAspect.EnterAttachmentModePermit(s);
         }, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).manual().in(this).change(MeleeState.MODIFYING).to(MeleeState.READY).when(this.clickSpammingPreventer).withAction((instance) -> {
            this.permitManager.request(new MeleeAttachmentAspect.ExitAttachmentModePermit(MeleeState.READY), instance, (p, e) -> {
            });
         }).manual().in(this).change(MeleeState.MODIFYING).to(MeleeState.NEXT_ATTACHMENT).when(this.clickSpammingPreventer).withPermit((BiFunction)null, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).manual().in(this).change(MeleeState.NEXT_ATTACHMENT).to(MeleeState.MODIFYING).automatic();
      }
   }

   public void setPermitManager(PermitManager permitManager) {
      this.permitManager = permitManager;
      permitManager.registerEvaluator(MeleeAttachmentAspect.EnterAttachmentModePermit.class, PlayerMeleeInstance.class, this::enterAttachmentSelectionMode);
      permitManager.registerEvaluator(MeleeAttachmentAspect.ExitAttachmentModePermit.class, PlayerMeleeInstance.class, this::exitAttachmentSelectionMode);
      permitManager.registerEvaluator(MeleeAttachmentAspect.ChangeAttachmentPermit.class, PlayerMeleeInstance.class, this::changeAttachment);
   }

   public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
      PlayerMeleeInstance weaponInstance = (PlayerMeleeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMeleeInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, new MeleeState[]{MeleeState.MODIFYING, MeleeState.READY});
      }

   }

   public void onUpdate(EntityPlayer player) {
      PlayerMeleeInstance instance = (PlayerMeleeInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerMeleeInstance.class);
      if(instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, this.allowedUpdateFromStates, new MeleeState[0]);
      }

   }

   private void enterAttachmentSelectionMode(MeleeAttachmentAspect.EnterAttachmentModePermit permit, PlayerMeleeInstance weaponInstance) {
      logger.debug("Entering attachment mode");
      byte[] selectedAttachmentIndexes = new byte[AttachmentCategory.values.length];
      Arrays.fill(selectedAttachmentIndexes, (byte)-1);
      weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
      permit.setStatus(Permit.Status.GRANTED);
   }

   private void exitAttachmentSelectionMode(MeleeAttachmentAspect.ExitAttachmentModePermit permit, PlayerMeleeInstance weaponInstance) {
      logger.debug("Exiting attachment mode");
      weaponInstance.setSelectedAttachmentIndexes(new byte[0]);
      permit.setStatus(Permit.Status.GRANTED);
   }

   List getActiveAttachments(EntityPlayer player, ItemStack itemStack) {
      CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
      ArrayList activeAttachments = new ArrayList();
      PlayerItemInstance itemInstance = this.modContext.getPlayerItemInstanceRegistry().getItemInstance(player, itemStack);
      int[] activeAttachmentsIds;
      if(!(itemInstance instanceof PlayerMeleeInstance)) {
         activeAttachmentsIds = new int[AttachmentCategory.values.length];
         Iterator weapon = ((ItemMelee)itemStack.getItem()).getCompatibleAttachments().values().iterator();

         while(weapon.hasNext()) {
            CompatibleAttachment attachment = (CompatibleAttachment)weapon.next();
            if(attachment.isDefault()) {
               activeAttachmentsIds[attachment.getAttachment().getCategory().ordinal()] = Item.getIdFromItem(attachment.getAttachment());
            }
         }
      } else {
         activeAttachmentsIds = ((PlayerMeleeInstance)itemInstance).getActiveAttachmentIds();
      }

      ItemMelee var13 = (ItemMelee)itemStack.getItem();
      int[] var14 = activeAttachmentsIds;
      int var8 = activeAttachmentsIds.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         int activeIndex = var14[var9];
         if(activeIndex != 0) {
            Item item = Item.getItemById(activeIndex);
            if(item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = (CompatibleAttachment)var13.getCompatibleAttachments().get(item);
               if(compatibleAttachment != null) {
                  activeAttachments.add(compatibleAttachment);
               }
            }
         }
      }

      return activeAttachments;
   }

   public void changeAttachment(AttachmentCategory attachmentCategory, PlayerMeleeInstance weaponInstance) {
      if(weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, new MeleeAttachmentAspect.ChangeAttachmentPermit(attachmentCategory), new MeleeState[]{MeleeState.NEXT_ATTACHMENT});
      }

   }

   private void changeAttachment(MeleeAttachmentAspect.ChangeAttachmentPermit permit, PlayerMeleeInstance weaponInstance) {
      AttachmentCategory attachmentCategory = permit.attachmentCategory;
      int[] originalActiveAttachmentIds = weaponInstance.getActiveAttachmentIds();
      int[] activeAttachmentIds = Arrays.copyOf(originalActiveAttachmentIds, originalActiveAttachmentIds.length);
      int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
      ItemAttachment currentAttachment = null;
      if(activeAttachmentIdForThisCategory > 0) {
         currentAttachment = (ItemAttachment)Item.getItemById(activeAttachmentIdForThisCategory);
      }

      MeleeAttachmentAspect.AttachmentLookupResult lookupResult = this.next(attachmentCategory, currentAttachment, weaponInstance);
      if(currentAttachment != null) {
         if(currentAttachment.getRemove() != null) {
            currentAttachment.getRemove().apply(currentAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
         }

         if(currentAttachment.getRemove3() != null) {
            currentAttachment.getRemove3().apply(currentAttachment, weaponInstance);
         }
      }

      if(lookupResult.index >= 0) {
         ItemStack slotItemStack = weaponInstance.getPlayer().inventory.getStackInSlot(lookupResult.index);
         ItemAttachment nextAttachment = (ItemAttachment)slotItemStack.getItem();
         if(nextAttachment.getApply() != null) {
            nextAttachment.getApply().apply(nextAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
         } else if(nextAttachment.getApply3() != null) {
            nextAttachment.getApply3().apply(nextAttachment, weaponInstance);
         } else if(lookupResult.compatibleAttachment.getMeleeApplyHandler() != null) {
            lookupResult.compatibleAttachment.getMeleeApplyHandler().apply(nextAttachment, weaponInstance);
         }

         CompatibilityProvider.compatibility.consumeInventoryItemFromSlot(weaponInstance.getPlayer(), lookupResult.index);
         activeAttachmentIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
      } else {
         activeAttachmentIds[attachmentCategory.ordinal()] = -1;
      }

      if(currentAttachment != null) {
         CompatibilityProvider.compatibility.addItemToPlayerInventory(weaponInstance.getPlayer(), currentAttachment, lookupResult.index);
      }

      weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
   }

   private MeleeAttachmentAspect.AttachmentLookupResult next(AttachmentCategory category, Item currentAttachment, PlayerMeleeInstance weaponInstance) {
      MeleeAttachmentAspect.AttachmentLookupResult result = new MeleeAttachmentAspect.AttachmentLookupResult();
      byte[] originallySelectedAttachmentIndexes = weaponInstance.getSelectedAttachmentIds();
      if(originallySelectedAttachmentIndexes != null && originallySelectedAttachmentIndexes.length == AttachmentCategory.values.length) {
         byte[] selectedAttachmentIndexes = Arrays.copyOf(originallySelectedAttachmentIndexes, originallySelectedAttachmentIndexes.length);
         byte activeIndex = selectedAttachmentIndexes[category.ordinal()];
         result.index = -1;
         int offset = activeIndex + 1;

         for(int i = 0; i < 37; ++i) {
            int currentIndex = i + offset;
            if(currentIndex >= 36) {
               currentIndex -= 37;
            }

            logger.debug("Searching for an attachment in slot {}", new Object[]{Integer.valueOf(currentIndex)});
            if(currentIndex == -1) {
               result.index = -1;
               break;
            }

            ItemStack slotItemStack = weaponInstance.getPlayer().inventory.getStackInSlot(currentIndex);
            if(slotItemStack != null && slotItemStack.getItem() instanceof ItemAttachment) {
               ItemAttachment attachmentItemFromInventory = (ItemAttachment)slotItemStack.getItem();
               CompatibleAttachment compatibleAttachment;
               if(attachmentItemFromInventory.getCategory() == category && (compatibleAttachment = (CompatibleAttachment)weaponInstance.getWeapon().getCompatibleAttachments().get(attachmentItemFromInventory)) != null && attachmentItemFromInventory != currentAttachment) {
                  result.index = currentIndex;
                  result.compatibleAttachment = compatibleAttachment;
                  break;
               }
            }
         }

         selectedAttachmentIndexes[category.ordinal()] = (byte)result.index;
         weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
         return result;
      } else {
         return result;
      }
   }

   void addAttachment(ItemAttachment attachment, PlayerMeleeInstance weaponInstance) {
      int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
      int activeAttachmentIdForThisCategory = activeAttachmentsIds[attachment.getCategory().ordinal()];
      ItemAttachment currentAttachment = null;
      if(activeAttachmentIdForThisCategory > 0) {
         currentAttachment = (ItemAttachment)Item.getItemById(activeAttachmentIdForThisCategory);
      }

      if(currentAttachment == null) {
         if(attachment != null && attachment.getApply() != null) {
            attachment.getApply().apply(attachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
         }

         activeAttachmentsIds[attachment.getCategory().ordinal()] = Item.getIdFromItem(attachment);
      } else {
         System.err.println("Attachment of category " + attachment.getCategory() + " installed, remove it first");
      }

   }

   ItemAttachment removeAttachment(AttachmentCategory attachmentCategory, PlayerMeleeInstance weaponInstance) {
      int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
      int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
      ItemAttachment currentAttachment = null;
      if(activeAttachmentIdForThisCategory > 0) {
         currentAttachment = (ItemAttachment)Item.getItemById(activeAttachmentIdForThisCategory);
      }

      if(currentAttachment != null && currentAttachment.getRemove() != null) {
         currentAttachment.getRemove().apply(currentAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
      }

      if(currentAttachment != null) {
         activeAttachmentIds[attachmentCategory.ordinal()] = -1;
         weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
      }

      return currentAttachment;
   }

   static ItemAttachment getActiveAttachment(AttachmentCategory category, PlayerMeleeInstance weaponInstance) {
      ItemAttachment itemAttachment = null;
      int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
      int[] var4 = activeAttachmentIds;
      int var5 = activeAttachmentIds.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         int activeIndex = var4[var6];
         if(activeIndex != 0) {
            Item item = Item.getItemById(activeIndex);
            if(item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = (CompatibleAttachment)weaponInstance.getWeapon().getCompatibleAttachments().get(item);
               if(compatibleAttachment != null && category == compatibleAttachment.getAttachment().getCategory()) {
                  itemAttachment = compatibleAttachment.getAttachment();
                  break;
               }
            }
         }
      }

      return itemAttachment;
   }

   static boolean isActiveAttachment(ItemAttachment attachment, PlayerMeleeInstance weaponInstance) {
      int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
      return Arrays.stream(activeAttachmentIds).anyMatch((attachmentId) -> {
         return attachment == Item.getItemById(attachmentId);
      });
   }

   ItemAttachment getActiveAttachment(PlayerMeleeInstance weaponInstance, AttachmentCategory category) {
      return weaponInstance.getAttachmentItemWithCategory(category);
   }

   static {
      TypeRegistry.getInstance().register(MeleeAttachmentAspect.EnterAttachmentModePermit.class);
      TypeRegistry.getInstance().register(MeleeAttachmentAspect.ExitAttachmentModePermit.class);
      TypeRegistry.getInstance().register(MeleeAttachmentAspect.ChangeAttachmentPermit.class);
   }

   public static class ChangeAttachmentPermit extends Permit {
      AttachmentCategory attachmentCategory;

      public ChangeAttachmentPermit() {
      }

      public ChangeAttachmentPermit(AttachmentCategory attachmentCategory) {
         super(MeleeState.NEXT_ATTACHMENT);
         this.attachmentCategory = attachmentCategory;
      }

      public void init(ByteBuf buf) {
         super.init(buf);
         this.attachmentCategory = AttachmentCategory.values()[buf.readInt()];
      }

      public void serialize(ByteBuf buf) {
         super.serialize(buf);
         buf.writeInt(this.attachmentCategory.ordinal());
      }
   }

   public static class ExitAttachmentModePermit extends Permit {
      public ExitAttachmentModePermit() {
      }

      public ExitAttachmentModePermit(MeleeState state) {
         super(state);
      }
   }

   public static class EnterAttachmentModePermit extends Permit {
      public EnterAttachmentModePermit() {
      }

      public EnterAttachmentModePermit(MeleeState state) {
         super(state);
      }
   }

   private static class AttachmentLookupResult {
      CompatibleAttachment compatibleAttachment;
      int index;

      private AttachmentLookupResult() {
         this.index = -1;
      }

      // $FF: synthetic method
      AttachmentLookupResult(Object x0) {
         this();
      }
   }
}
