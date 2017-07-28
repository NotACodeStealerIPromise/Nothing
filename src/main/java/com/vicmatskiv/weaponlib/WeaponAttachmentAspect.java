package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
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

public final class WeaponAttachmentAspect implements Aspect {
   private static final Logger logger = LogManager.getLogger(WeaponAttachmentAspect.class);
   private ModContext modContext;
   private PermitManager permitManager;
   private StateManager stateManager;
   private long clickSpammingTimeout = 100L;
   private Predicate clickSpammingPreventer = (es) -> {
      return System.currentTimeMillis() >= es.getStateUpdateTimestamp() + this.clickSpammingTimeout;
   };
   private Collection allowedUpdateFromStates;

   WeaponAttachmentAspect(ModContext modContext) {
      this.allowedUpdateFromStates = Arrays.asList(new WeaponState[]{WeaponState.MODIFYING_REQUESTED});
      this.modContext = modContext;
   }

   public void setStateManager(StateManager stateManager) {
      if(this.permitManager == null) {
         throw new IllegalStateException("Permit manager not initialized");
      } else {
         this.stateManager = stateManager.in(this).change(WeaponState.READY).to(WeaponState.MODIFYING).when(this.clickSpammingPreventer).withPermit((s, es) -> {
            return new WeaponAttachmentAspect.EnterAttachmentModePermit(s);
         }, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).manual().in(this).change(WeaponState.MODIFYING).to(WeaponState.READY).when(this.clickSpammingPreventer).withAction((instance) -> {
            this.permitManager.request(new WeaponAttachmentAspect.ExitAttachmentModePermit(WeaponState.READY), instance, (p, e) -> {
            });
         }).manual().in(this).change(WeaponState.MODIFYING).to(WeaponState.NEXT_ATTACHMENT).when(this.clickSpammingPreventer).withPermit((BiFunction)null, this.modContext.getPlayerItemInstanceRegistry()::update, this.permitManager).manual().in(this).change(WeaponState.NEXT_ATTACHMENT).to(WeaponState.MODIFYING).automatic();
      }
   }

   public void setPermitManager(PermitManager permitManager) {
      this.permitManager = permitManager;
      permitManager.registerEvaluator(WeaponAttachmentAspect.EnterAttachmentModePermit.class, PlayerWeaponInstance.class, this::enterAttachmentSelectionMode);
      permitManager.registerEvaluator(WeaponAttachmentAspect.ExitAttachmentModePermit.class, PlayerWeaponInstance.class, this::exitAttachmentSelectionMode);
      permitManager.registerEvaluator(WeaponAttachmentAspect.ChangeAttachmentPermit.class, PlayerWeaponInstance.class, this::changeAttachment);
   }

   public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
      PlayerWeaponInstance weaponInstance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, new WeaponState[]{WeaponState.MODIFYING, WeaponState.READY});
      }

   }

   void updateMainHeldItem(EntityPlayer player) {
      PlayerWeaponInstance instance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
      if(instance != null) {
         this.stateManager.changeStateFromAnyOf(this, instance, this.allowedUpdateFromStates, new WeaponState[0]);
      }

   }

   private void enterAttachmentSelectionMode(WeaponAttachmentAspect.EnterAttachmentModePermit permit, PlayerWeaponInstance weaponInstance) {
      logger.debug("Entering attachment mode");
      byte[] selectedAttachmentIndexes = new byte[AttachmentCategory.values.length];
      Arrays.fill(selectedAttachmentIndexes, (byte)-1);
      weaponInstance.setSelectedAttachmentIndexes(selectedAttachmentIndexes);
      permit.setStatus(Permit.Status.GRANTED);
   }

   private void exitAttachmentSelectionMode(WeaponAttachmentAspect.ExitAttachmentModePermit permit, PlayerWeaponInstance weaponInstance) {
      logger.debug("Exiting attachment mode");
      weaponInstance.setSelectedAttachmentIndexes(new byte[0]);
      permit.setStatus(Permit.Status.GRANTED);
   }

   List getActiveAttachments(EntityPlayer player, ItemStack itemStack) {
      CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
      ArrayList activeAttachments = new ArrayList();
      PlayerItemInstance itemInstance = this.modContext.getPlayerItemInstanceRegistry().getItemInstance(player, itemStack);
      int[] activeAttachmentsIds;
      if(!(itemInstance instanceof PlayerWeaponInstance)) {
         activeAttachmentsIds = new int[AttachmentCategory.values.length];
         Iterator weapon = ((Weapon)itemStack.getItem()).getCompatibleAttachments().values().iterator();

         while(weapon.hasNext()) {
            CompatibleAttachment attachment = (CompatibleAttachment)weapon.next();
            if(attachment.isDefault()) {
               activeAttachmentsIds[attachment.getAttachment().getCategory().ordinal()] = Item.getIdFromItem(attachment.getAttachment());
            }
         }
      } else {
         activeAttachmentsIds = ((PlayerWeaponInstance)itemInstance).getActiveAttachmentIds();
      }

      Weapon var13 = (Weapon)itemStack.getItem();
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

   void changeAttachment(AttachmentCategory attachmentCategory, PlayerWeaponInstance weaponInstance) {
      if(weaponInstance != null) {
         this.stateManager.changeState(this, weaponInstance, new WeaponAttachmentAspect.ChangeAttachmentPermit(attachmentCategory), new WeaponState[]{WeaponState.NEXT_ATTACHMENT});
      }

   }

   private void changeAttachment(WeaponAttachmentAspect.ChangeAttachmentPermit permit, PlayerWeaponInstance weaponInstance) {
      AttachmentCategory attachmentCategory = permit.attachmentCategory;
      int[] originalActiveAttachmentIds = weaponInstance.getActiveAttachmentIds();
      int[] activeAttachmentIds = Arrays.copyOf(originalActiveAttachmentIds, originalActiveAttachmentIds.length);
      int activeAttachmentIdForThisCategory = activeAttachmentIds[attachmentCategory.ordinal()];
      ItemAttachment currentAttachment = null;
      if(activeAttachmentIdForThisCategory > 0) {
         currentAttachment = (ItemAttachment)Item.getItemById(activeAttachmentIdForThisCategory);
      }

      if(currentAttachment != null) {
         CompatibleAttachment lookupResult = (CompatibleAttachment)weaponInstance.getWeapon().getCompatibleAttachments().get(currentAttachment);
         if(lookupResult.isPermanent()) {
            return;
         }
      }

      WeaponAttachmentAspect.AttachmentLookupResult lookupResult1 = this.next(attachmentCategory, currentAttachment, weaponInstance);
      if(currentAttachment != null) {
         if(currentAttachment.getRemove() != null) {
            currentAttachment.getRemove().apply(currentAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
         }

         if(currentAttachment.getRemove2() != null) {
            currentAttachment.getRemove2().apply(currentAttachment, weaponInstance);
         }
      }

      if(lookupResult1.index >= 0) {
         ItemStack handler = weaponInstance.getPlayer().inventory.getStackInSlot(lookupResult1.index);
         ItemAttachment nextAttachment = (ItemAttachment)handler.getItem();
         if(nextAttachment.getApply() != null) {
            nextAttachment.getApply().apply(nextAttachment, weaponInstance.getWeapon(), weaponInstance.getPlayer());
         } else if(nextAttachment.getApply2() != null) {
            nextAttachment.getApply2().apply(nextAttachment, weaponInstance);
         } else if(lookupResult1.compatibleAttachment.getApplyHandler() != null) {
            lookupResult1.compatibleAttachment.getApplyHandler().apply(nextAttachment, weaponInstance);
         } else {
            ItemAttachment.ApplyHandler2 handler1 = weaponInstance.getWeapon().getEquivalentHandler(attachmentCategory);
            if(handler1 != null) {
               handler1.apply((ItemAttachment)null, weaponInstance);
            }
         }

         CompatibilityProvider.compatibility.consumeInventoryItemFromSlot(weaponInstance.getPlayer(), lookupResult1.index);
         activeAttachmentIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
      } else {
         activeAttachmentIds[attachmentCategory.ordinal()] = -1;
         ItemAttachment.ApplyHandler2 handler2 = weaponInstance.getWeapon().getEquivalentHandler(attachmentCategory);
         if(handler2 != null) {
            handler2.apply((ItemAttachment)null, weaponInstance);
         }
      }

      if(currentAttachment != null) {
         CompatibilityProvider.compatibility.addItemToPlayerInventory(weaponInstance.getPlayer(), currentAttachment, lookupResult1.index);
      }

      weaponInstance.setActiveAttachmentIds(activeAttachmentIds);
   }

   private WeaponAttachmentAspect.AttachmentLookupResult next(AttachmentCategory category, Item currentAttachment, PlayerWeaponInstance weaponInstance) {
      WeaponAttachmentAspect.AttachmentLookupResult result = new WeaponAttachmentAspect.AttachmentLookupResult();
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

   void addAttachment(ItemAttachment attachment, PlayerWeaponInstance weaponInstance) {
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

   ItemAttachment removeAttachment(AttachmentCategory attachmentCategory, PlayerWeaponInstance weaponInstance) {
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

   static ItemAttachment getActiveAttachment(AttachmentCategory category, PlayerWeaponInstance weaponInstance) {
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

   static boolean isActiveAttachment(ItemAttachment attachment, PlayerWeaponInstance weaponInstance) {
      int[] activeAttachmentIds = weaponInstance.getActiveAttachmentIds();
      return Arrays.stream(activeAttachmentIds).anyMatch((attachmentId) -> {
         return attachment == Item.getItemById(attachmentId);
      });
   }

   boolean isSilencerOn(PlayerWeaponInstance weaponInstance) {
      int[] activeAttachmentsIds = weaponInstance.getActiveAttachmentIds();
      int activeAttachmentIdForThisCategory = activeAttachmentsIds[AttachmentCategory.SILENCER.ordinal()];
      return activeAttachmentIdForThisCategory > 0;
   }

   ItemAttachment getActiveAttachment(PlayerWeaponInstance weaponInstance, AttachmentCategory category) {
      return weaponInstance.getAttachmentItemWithCategory(category);
   }

   static {
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.EnterAttachmentModePermit.class);
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.ExitAttachmentModePermit.class);
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.ChangeAttachmentPermit.class);
   }

   public static class ChangeAttachmentPermit extends Permit {
      AttachmentCategory attachmentCategory;

      public ChangeAttachmentPermit() {
      }

      public ChangeAttachmentPermit(AttachmentCategory attachmentCategory) {
         super(WeaponState.NEXT_ATTACHMENT);
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

      public ExitAttachmentModePermit(WeaponState state) {
         super(state);
      }
   }

   public static class EnterAttachmentModePermit extends Permit {
      public EnterAttachmentModePermit() {
      }

      public EnterAttachmentModePermit(WeaponState state) {
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
