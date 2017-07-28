package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ItemAttachment;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CompatibleAttachment {
   private ItemAttachment attachment;
   private Consumer modelPositioning;
   private BiConsumer positioning;
   private boolean isDefault;
   private boolean isPermanent;
   private ItemAttachment.ApplyHandler2 applyHandler;
   private ItemAttachment.ApplyHandler2 removeHandler;
   ItemAttachment.MeleeWeaponApplyHandler meleeApplyHandler;
   ItemAttachment.MeleeWeaponApplyHandler meleeRemoveHandler;

   public CompatibleAttachment(ItemAttachment attachment, BiConsumer positioning, Consumer modelPositioning, boolean isDefault, boolean isPermanent) {
      this.attachment = attachment;
      this.positioning = positioning;
      this.modelPositioning = modelPositioning;
      this.isDefault = isDefault;
      this.isPermanent = isPermanent;
   }

   public CompatibleAttachment(ItemAttachment attachment, BiConsumer positioning, Consumer modelPositioning, boolean isDefault) {
      this(attachment, positioning, modelPositioning, isDefault, false);
   }

   public CompatibleAttachment(ItemAttachment attachment, ItemAttachment.ApplyHandler2 applyHandler, ItemAttachment.ApplyHandler2 removeHandler) {
      this.attachment = attachment;
      this.applyHandler = applyHandler;
      this.removeHandler = removeHandler;
   }

   public CompatibleAttachment(ItemAttachment attachment, ItemAttachment.MeleeWeaponApplyHandler meleeApplyHandler, ItemAttachment.MeleeWeaponApplyHandler meleeRemoveHandler) {
      this.attachment = attachment;
      this.meleeApplyHandler = meleeApplyHandler;
      this.meleeRemoveHandler = meleeRemoveHandler;
   }

   public CompatibleAttachment(ItemAttachment attachment, Consumer positioning) {
      this(attachment, (BiConsumer)null, positioning, false);
   }

   public CompatibleAttachment(ItemAttachment attachment, Consumer positioning, boolean isDefault) {
      this.attachment = attachment;
      this.modelPositioning = positioning;
      this.isDefault = isDefault;
   }

   public ItemAttachment getAttachment() {
      return this.attachment;
   }

   public Consumer getModelPositioning() {
      return this.modelPositioning;
   }

   public BiConsumer getPositioning() {
      return this.positioning;
   }

   public boolean isDefault() {
      return this.isDefault;
   }

   public ItemAttachment.ApplyHandler2 getApplyHandler() {
      return this.applyHandler;
   }

   public ItemAttachment.ApplyHandler2 getRemoveHandler() {
      return this.removeHandler;
   }

   public ItemAttachment.MeleeWeaponApplyHandler getMeleeApplyHandler() {
      return this.meleeApplyHandler;
   }

   public ItemAttachment.MeleeWeaponApplyHandler getMeleeRemoveHandler() {
      return this.meleeRemoveHandler;
   }

   public boolean isPermanent() {
      return this.isPermanent;
   }
}
