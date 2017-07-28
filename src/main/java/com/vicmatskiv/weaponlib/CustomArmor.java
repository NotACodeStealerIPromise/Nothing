package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleCustomArmor;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityEquipmentSlot;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.IntPredicate;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class CustomArmor extends CompatibleCustomArmor {
   private static final String ACTIVE_ATTACHMENT_TAG = "ActiveAttachments";
   private Map compatibleAttachments;

   private CustomArmor(String modId, ArmorMaterial material, int renderIndex, CompatibleEntityEquipmentSlot armorType, String iconName, String textureName, ModelBiped model, String hudTextureName) {
      super(modId, material, renderIndex, armorType, iconName.toLowerCase(), textureName, model, hudTextureName);
      this.compatibleAttachments = new HashMap();
   }

   public String getHudTexture() {
      return this.modId + ":" + "textures/hud/" + this.hudTextureName + ".png";
   }

   public void changeAttachment(AttachmentCategory attachmentCategory, ItemStack itemStack, EntityPlayer player) {
      CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
      int[] activeAttachmentsIds = this.ensureActiveAttachments(itemStack);
      int activeAttachmentIdForThisCategory = activeAttachmentsIds[attachmentCategory.ordinal()];
      ItemAttachment item = null;
      if(activeAttachmentIdForThisCategory > 0) {
         item = (ItemAttachment)Item.getItemById(activeAttachmentIdForThisCategory);
         if(item != null && item.getRemove() != null) {
            item.getRemove().apply(item, this, player);
         }
      }

      ItemAttachment nextAttachment = this.nextCompatibleAttachment(attachmentCategory, item, player);
      if(nextAttachment != null && nextAttachment.getApply() != null) {
         nextAttachment.getApply().apply(nextAttachment, this, player);
      }

      activeAttachmentsIds[attachmentCategory.ordinal()] = Item.getIdFromItem(nextAttachment);
      CompatibilityProvider.compatibility.getTagCompound(itemStack).setIntArray("ActiveAttachments", activeAttachmentsIds);
   }

   private ItemAttachment nextCompatibleAttachment(AttachmentCategory category, Item currentAttachment, EntityPlayer player) {
      ItemAttachment nextAttachment = null;
      boolean foundCurrent = false;

      for(int i = 0; i < 36; ++i) {
         ItemStack itemStack = player.inventory.getStackInSlot(i);
         if(itemStack != null && itemStack.getItem() instanceof ItemAttachment) {
            ItemAttachment compatibleAttachment = (ItemAttachment)itemStack.getItem();
            if(compatibleAttachment.getCategory() == category) {
               if(foundCurrent || currentAttachment == null) {
                  nextAttachment = compatibleAttachment;
                  break;
               }

               if(currentAttachment == compatibleAttachment) {
                  foundCurrent = true;
               }
            }
         }
      }

      return nextAttachment;
   }

   public ItemAttachment getActiveAttachment(ItemStack itemStack, AttachmentCategory category) {
      CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
      ItemAttachment itemAttachment = null;
      int[] activeAttachmentsIds = this.ensureActiveAttachments(itemStack);
      int[] var5 = activeAttachmentsIds;
      int var6 = activeAttachmentsIds.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int activeIndex = var5[var7];
         if(activeIndex != 0) {
            Item item = Item.getItemById(activeIndex);
            if(item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = (CompatibleAttachment)this.compatibleAttachments.get(item);
               if(compatibleAttachment != null && category == compatibleAttachment.getAttachment().getCategory()) {
                  itemAttachment = compatibleAttachment.getAttachment();
                  break;
               }
            }
         }
      }

      return itemAttachment;
   }

   public List getActiveAttachments(ItemStack itemStack) {
      CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
      ArrayList activeAttachments = new ArrayList();
      int[] activeAttachmentsIds = this.ensureActiveAttachments(itemStack);
      int[] var4 = activeAttachmentsIds;
      int var5 = activeAttachmentsIds.length;

      for(int var6 = 0; var6 < var5; ++var6) {
         int activeIndex = var4[var6];
         if(activeIndex != 0) {
            Item item = Item.getItemById(activeIndex);
            if(item instanceof ItemAttachment) {
               CompatibleAttachment compatibleAttachment = (CompatibleAttachment)this.compatibleAttachments.get(item);
               if(compatibleAttachment != null) {
                  activeAttachments.add(compatibleAttachment);
               }
            }
         }
      }

      return activeAttachments;
   }

   private int[] ensureActiveAttachments(ItemStack itemStack) {
      int[] activeAttachmentsIds = CompatibilityProvider.compatibility.getTagCompound(itemStack).getIntArray("ActiveAttachments");
      if(activeAttachmentsIds == null || activeAttachmentsIds.length != AttachmentCategory.values.length) {
         activeAttachmentsIds = new int[AttachmentCategory.values.length];
         CompatibilityProvider.compatibility.getTagCompound(itemStack).setIntArray("ActiveAttachments", activeAttachmentsIds);
         Iterator var3 = this.compatibleAttachments.values().iterator();

         while(var3.hasNext()) {
            CompatibleAttachment attachment = (CompatibleAttachment)var3.next();
            if(attachment.isDefault()) {
               activeAttachmentsIds[attachment.getAttachment().getCategory().ordinal()] = Item.getIdFromItem(attachment.getAttachment());
            }
         }
      }

      return activeAttachmentsIds;
   }

   public static boolean isActiveAttachment(ItemStack itemStack, ItemAttachment attachment) {
      CustomArmor armor = (CustomArmor)itemStack.getItem();
      int[] activeAttachmentsIds = armor.ensureActiveAttachments(itemStack);
      return Arrays.stream(activeAttachmentsIds).anyMatch((attachmentId) -> {
         return attachment == Item.getItemById(attachmentId);
      });
   }

   // $FF: synthetic method
   CustomArmor(String x0, ArmorMaterial x1, int x2, CompatibleEntityEquipmentSlot x3, String x4, String x5, ModelBiped x6, String x7, Object x8) {
      this(x0, x1, x2, x3, x4, x5, x6, x7);
   }

   public static class Builder {
      private String modId;
      private String textureName;
      private String iconName;
      private ArmorMaterial material;
      private String unlocalizedName;
      private ModelBiped bootsModel;
      private ModelBiped chestModel;
      private String modelClass;
      private String hudTextureName;
      private Map compatibleAttachments = new HashMap();
      private CreativeTabs creativeTab;

      public CustomArmor.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public CustomArmor.Builder withCreativeTab(CreativeTabs creativeTab) {
         this.creativeTab = creativeTab;
         return this;
      }

      public CustomArmor.Builder withTextureName(String textureName) {
         this.textureName = textureName.toLowerCase();
         return this;
      }

      public CustomArmor.Builder withMaterial(ArmorMaterial material) {
         this.material = material;
         return this;
      }

      public CustomArmor.Builder withUnlocalizedName(String unlocalizedName) {
         this.unlocalizedName = unlocalizedName;
         return this;
      }

      public CustomArmor.Builder withModelClass(String modelClass) {
         this.modelClass = modelClass;
         return this;
      }

      public CustomArmor.Builder withHudTextureName(String hudTextureName) {
         this.hudTextureName = hudTextureName.toLowerCase();
         return this;
      }

      public CustomArmor.Builder withCompatibleAttachment(AttachmentCategory category, ModelBase attachmentModel, String textureName, Consumer positioner) {
         ItemAttachment item = new ItemAttachment(this.modId, category, attachmentModel, textureName, (String)null);
         this.compatibleAttachments.put(item, new CompatibleAttachment(item, positioner));
         return this;
      }

      public void build(boolean isClient) {
         if(isClient) {
            try {
               this.chestModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var9) {
               throw new IllegalStateException("Missing chest model", var9);
            }

            try {
               this.bootsModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var8) {
               throw new IllegalStateException("Missing boots model", var8);
            }
         }

         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            String unlocalizedHelmetName = this.unlocalizedName + "_helmet";
            CustomArmor armorHelmet = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.HEAD, unlocalizedHelmetName, this.textureName, this.chestModel, this.hudTextureName);
            if(this.creativeTab != null) {
               armorHelmet.setCreativeTab(this.creativeTab);
            }

            armorHelmet.setUnlocalizedName(unlocalizedHelmetName);
            CompatibilityProvider.compatibility.registerItem(armorHelmet, unlocalizedHelmetName.toLowerCase());
            String unlocalizedChestName = this.unlocalizedName + "_chest";
            CustomArmor armorChest = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.CHEST, unlocalizedChestName, this.textureName, this.chestModel, this.hudTextureName);
            if(this.creativeTab != null) {
               armorChest.setCreativeTab(this.creativeTab);
            }

            armorChest.setUnlocalizedName(unlocalizedChestName);
            CompatibilityProvider.compatibility.registerItem(armorChest, unlocalizedChestName.toLowerCase());
            String unlocalizedBootsName = this.unlocalizedName + "_boots";
            CustomArmor armorBoots = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.FEET, unlocalizedBootsName, this.textureName, this.bootsModel, this.hudTextureName);
            if(armorBoots != null) {
               armorBoots.setCreativeTab(this.creativeTab);
            }

            armorBoots.setUnlocalizedName(unlocalizedBootsName);
            CompatibilityProvider.compatibility.registerItem(armorBoots, unlocalizedBootsName.toLowerCase());
         }
      }

      public CustomArmor buildHelmet(boolean isClient) {
         if(isClient) {
            if(this.chestModel == null) {
               try {
                  this.chestModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
               } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var5) {
                  throw new IllegalStateException("Missing chest model", var5);
               }
            }

            if(this.bootsModel == null) {
               try {
                  this.bootsModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
               } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var4) {
                  throw new IllegalStateException("Missing boots model", var4);
               }
            }
         }

         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            String unlocalizedHelmetName = this.unlocalizedName + "_helmet";
            CustomArmor armorHelmet = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.HEAD, unlocalizedHelmetName, this.textureName, this.chestModel, this.hudTextureName);
            armorHelmet.setUnlocalizedName(unlocalizedHelmetName);
            CompatibilityProvider.compatibility.registerItem(armorHelmet, unlocalizedHelmetName.toLowerCase());
            if(this.creativeTab != null) {
               armorHelmet.setCreativeTab(this.creativeTab);
            }

            return armorHelmet;
         }
      }

      public CustomArmor buildChest(boolean isClient) {
         if(isClient && this.chestModel == null) {
            try {
               this.chestModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var4) {
               throw new IllegalStateException("Missing chest model", var4);
            }
         }

         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            String unlocalizedChestName = this.unlocalizedName + "_chest";
            CustomArmor armorChest = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.CHEST, unlocalizedChestName, this.textureName, this.chestModel, this.hudTextureName);
            if(this.creativeTab != null) {
               armorChest.setCreativeTab(this.creativeTab);
            }

            armorChest.setUnlocalizedName(unlocalizedChestName);
            CompatibilityProvider.compatibility.registerItem(armorChest, unlocalizedChestName.toLowerCase());
            return armorChest;
         }
      }

      public CustomArmor buildBoots(boolean isClient) {
         if(isClient && this.bootsModel == null) {
            try {
               this.bootsModel = (ModelBiped)Class.forName(this.modelClass).newInstance();
            } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var4) {
               throw new IllegalStateException("Missing boots model", var4);
            }
         }

         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            String unlocalizedBootsName = this.unlocalizedName + "_boots";
            CustomArmor armorBoots = new CustomArmor(this.modId, this.material, 4, CompatibleEntityEquipmentSlot.FEET, unlocalizedBootsName, this.textureName, this.bootsModel, this.hudTextureName);
            if(this.creativeTab != null) {
               armorBoots.setCreativeTab(this.creativeTab);
            }

            armorBoots.setUnlocalizedName(unlocalizedBootsName);
            CompatibilityProvider.compatibility.registerItem(armorBoots, unlocalizedBootsName.toLowerCase());
            return armorBoots;
         }
      }
   }
}
