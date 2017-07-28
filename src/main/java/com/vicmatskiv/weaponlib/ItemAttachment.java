package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.ModelSource;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItem;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAttachment extends CompatibleItem implements ModelSource {
   private AttachmentCategory category;
   private String crosshair;
   private ItemAttachment.ApplyHandler apply;
   private ItemAttachment.ApplyHandler remove;
   protected ItemAttachment.ApplyHandler2 apply2;
   protected ItemAttachment.ApplyHandler2 remove2;
   protected ItemAttachment.MeleeWeaponApplyHandler apply3;
   protected ItemAttachment.MeleeWeaponApplyHandler remove3;
   private List texturedModels;
   private CustomRenderer postRenderer;
   private CustomRenderer preRenderer;
   private Part renderablePart;
   private String name;
   private Function informationProvider;
   protected int maxStackSize;
   private List attachments;
   private List compatibleWeapons;
   protected String textureName;

   protected ItemAttachment(String modId, AttachmentCategory category, ModelBase model, String textureName, String crosshair, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
      this.texturedModels = new ArrayList();
      this.maxStackSize = 1;
      this.attachments = new ArrayList();
      this.compatibleWeapons = new ArrayList();
      this.category = category;
      this.textureName = textureName.toLowerCase();
      this.crosshair = crosshair != null?modId + ":" + "textures/crosshairs/" + crosshair + ".png":null;
      this.apply = apply;
      this.remove = remove;
   }

   protected ItemAttachment(String modId, AttachmentCategory category, String crosshair, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
      this.texturedModels = new ArrayList();
      this.maxStackSize = 1;
      this.attachments = new ArrayList();
      this.compatibleWeapons = new ArrayList();
      this.category = category;
      this.crosshair = crosshair != null?modId + ":" + "textures/crosshairs/" + crosshair + ".png":null;
      this.apply = apply;
      this.remove = remove;
   }

   public int getItemStackLimit() {
      return this.maxStackSize;
   }

   public Item setTextureName(String name) {
      return this;
   }

   public Part getRenderablePart() {
      return this.renderablePart;
   }

   protected void setRenderablePart(Part renderablePart) {
      this.renderablePart = renderablePart;
   }

   protected Function getInformationProvider() {
      return this.informationProvider;
   }

   protected void setInformationProvider(Function informationProvider) {
      this.informationProvider = informationProvider;
   }

   /** @deprecated */
   @Deprecated
   public ItemAttachment addModel(ModelBase model, String textureName) {
      this.texturedModels.add(new Tuple(model, textureName));
      return this;
   }

   public ItemAttachment(String modId, AttachmentCategory category, String crosshair) {
      this(modId, category, crosshair, (a, w, p) -> {
      }, (a, w, p) -> {
      });
   }

   public ItemAttachment(String modId, AttachmentCategory category, ModelBase attachment, String textureName, String crosshair) {
      this(modId, category, attachment, textureName, crosshair, (a, w, p) -> {
      }, (a, w, p) -> {
      });
   }

   public AttachmentCategory getCategory() {
      return this.category;
   }

   public List getTexturedModels() {
      return this.texturedModels;
   }

   public String getCrosshair() {
      return this.crosshair;
   }

   public ItemAttachment.ApplyHandler getApply() {
      return this.apply;
   }

   public ItemAttachment.ApplyHandler getRemove() {
      return this.remove;
   }

   public void addCompatibleWeapon(Weapon weapon) {
      this.compatibleWeapons.add(weapon);
   }

   public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean p_77624_4_) {
      if(list != null && this.informationProvider != null) {
         list.add(this.informationProvider.apply(itemStack));
      }

   }

   public void setName(String name) {
      this.name = name;
   }

   public void setPostRenderer(CustomRenderer postRenderer) {
      this.postRenderer = postRenderer;
   }

   public CustomRenderer getPostRenderer() {
      return this.postRenderer;
   }

   public CustomRenderer getPreRenderer() {
      return this.preRenderer;
   }

   public void setPreRenderer(CustomRenderer preRenderer) {
      this.preRenderer = preRenderer;
   }

   protected void addCompatibleAttachment(CompatibleAttachment attachment) {
      this.attachments.add(attachment);
   }

   public List getAttachments() {
      return Collections.unmodifiableList(this.attachments);
   }

   public String toString() {
      return this.name != null?"Attachment [" + this.name + "]":super.toString();
   }

   public ItemAttachment.ApplyHandler2 getApply2() {
      return this.apply2;
   }

   protected ItemAttachment.ApplyHandler2 getRemove2() {
      return this.remove2;
   }

   public ItemAttachment.MeleeWeaponApplyHandler getApply3() {
      return this.apply3;
   }

   public ItemAttachment.MeleeWeaponApplyHandler getRemove3() {
      return this.remove3;
   }

   public interface MeleeWeaponApplyHandler {
      void apply(ItemAttachment var1, PlayerMeleeInstance var2);
   }

   public interface ApplyHandler2 {
      void apply(ItemAttachment var1, PlayerWeaponInstance var2);
   }

   public interface ApplyHandler {
      void apply(ItemAttachment var1, Object var2, EntityPlayer var3);
   }
}
