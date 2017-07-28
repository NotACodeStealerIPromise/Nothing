package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemBullet;
import com.vicmatskiv.weaponlib.MagazineState;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.PlayerMagazineInstance;
import com.vicmatskiv.weaponlib.Reloadable;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemMagazine extends ItemAttachment implements PlayerItemInstanceFactory, Reloadable, Updatable, Part {
   private static final long DEFAULT_RELOADING_TIMEOUT_TICKS = 25L;
   private final int DEFAULT_MAX_STACK_SIZE;
   private int ammo;
   private long reloadingTimeout;
   private List compatibleBullets;
   private CompatibleSound reloadSound;
   private ModContext modContext;

   ItemMagazine(String modId, ModelBase model, String textureName, int ammo) {
      this(modId, model, textureName, ammo, (ItemAttachment.ApplyHandler)null, (ItemAttachment.ApplyHandler)null);
   }

   ItemMagazine(String modId, ModelBase model, String textureName, int ammo, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
      super(modId, AttachmentCategory.MAGAZINE, model, textureName, (String)null, apply, remove);
      this.DEFAULT_MAX_STACK_SIZE = 1;
      this.ammo = ammo;
      this.setMaxStackSize(1);
   }

   ItemStack createItemStack() {
      ItemStack attachmentItemStack = new ItemStack(this);
      this.ensureItemStack(attachmentItemStack, this.ammo);
      return attachmentItemStack;
   }

   private void ensureItemStack(ItemStack itemStack, int initialAmmo) {
      if(CompatibilityProvider.compatibility.getTagCompound(itemStack) == null) {
         CompatibilityProvider.compatibility.setTagCompound(itemStack, new NBTTagCompound());
         Tags.setAmmo(itemStack, initialAmmo);
      }

   }

   public void onCreated(ItemStack stack, World p_77622_2_, EntityPlayer p_77622_3_) {
      this.ensureItemStack(stack, 0);
      super.onCreated(stack, p_77622_2_, p_77622_3_);
   }

   public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world) {
      return super.onItemUseFirst(stack, player, world);
   }

   public void onUpdate(ItemStack stack, World world, Entity entity, int p_77663_4_, boolean p_77663_5_) {
      this.ensureItemStack(stack, this.ammo);
      super.onUpdate(stack, world, entity, p_77663_4_, p_77663_5_);
   }

   List getCompatibleBullets() {
      return this.compatibleBullets;
   }

   int getAmmo() {
      return this.ammo;
   }

   public CompatibleSound getReloadSound() {
      return this.reloadSound;
   }

   public long getReloadTimeout() {
      return this.reloadingTimeout;
   }

   public Part getRenderablePart() {
      return this;
   }

   public PlayerMagazineInstance createItemInstance(EntityPlayer player, ItemStack itemStack, int slot) {
      PlayerMagazineInstance instance = new PlayerMagazineInstance(slot, player, itemStack);
      instance.setState(MagazineState.READY);
      return instance;
   }

   public void update(EntityPlayer player) {
      this.modContext.getMagazineReloadAspect().updateMainHeldItem(player);
   }

   public void reloadMainHeldItemForPlayer(EntityPlayer player) {
      this.modContext.getMagazineReloadAspect().reloadMainHeldItem(player);
   }

   public static final class Builder extends AttachmentBuilder {
      private int ammo;
      private long reloadingTimeout = 25L;
      private Set compatibleBullets = new HashSet();
      private String reloadSound;

      public ItemMagazine.Builder withAmmo(int ammo) {
         this.ammo = ammo;
         return this;
      }

      public ItemMagazine.Builder withReloadingTimeout(int reloadingTimeout) {
         this.reloadingTimeout = (long)reloadingTimeout;
         return this;
      }

      public ItemMagazine.Builder withReloadSound(String reloadSound) {
         this.reloadSound = reloadSound;
         return this;
      }

      public ItemMagazine.Builder withCompatibleBullet(ItemBullet compatibleBullet) {
         this.compatibleBullets.add(compatibleBullet);
         return this;
      }

      protected ItemAttachment createAttachment(ModContext modContext) {
         ItemMagazine magazine = new ItemMagazine(this.getModId(), this.getModel(), this.getTextureName(), this.ammo);
         magazine.reloadingTimeout = this.reloadingTimeout;
         magazine.compatibleBullets = new ArrayList(this.compatibleBullets);
         if(this.reloadSound != null) {
            magazine.reloadSound = modContext.registerSound(this.reloadSound);
         }

         magazine.modContext = modContext;
         this.withInformationProvider((stack) -> {
            return "Ammo: " + Tags.getAmmo(stack) + "/" + this.ammo;
         });
         return magazine;
      }
   }
}
