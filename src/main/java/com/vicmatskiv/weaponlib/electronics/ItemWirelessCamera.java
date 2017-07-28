package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.ModelSource;
import com.vicmatskiv.weaponlib.StaticModelSourceRenderer;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItem;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.electronics.EntityWirelessCamera;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemWirelessCamera extends CompatibleItem implements ModelSource {
   public static final long DEFAULT_DURATION = 300000L;
   private ItemWirelessCamera.Builder builder;
   private ModContext modContext;
   private List texturedModels = new ArrayList();

   public ItemWirelessCamera(ItemWirelessCamera.Builder builder, ModContext modContext) {
      this.builder = builder;
      this.modContext = modContext;
      this.maxStackSize = 16;
   }

   protected ItemStack onCompatibleItemRightClick(ItemStack itemStack, World world, EntityPlayer player, boolean mainHand) {
      CompatibilityProvider.compatibility.setStackSize(itemStack, CompatibilityProvider.compatibility.getStackSize(itemStack) - 1);
      if(!world.isRemote) {
         CompatibilityProvider.compatibility.spawnEntity(player, new EntityWirelessCamera(this.modContext, world, player, this, this.builder.duration));
      }

      return itemStack;
   }

   public List getTexturedModels() {
      return this.texturedModels;
   }

   public CustomRenderer getPostRenderer() {
      return null;
   }

   public ModelBase getModel() {
      return (ModelBase)((Tuple)this.texturedModels.get(0)).getU();
   }

   public String getTextureName() {
      return (String)((Tuple)this.texturedModels.get(0)).getV();
   }

   public static class Builder {
      protected String name;
      protected String modId;
      protected ModelBase model;
      protected String textureName;
      protected Consumer entityPositioning;
      protected Consumer inventoryPositioning;
      protected BiConsumer thirdPersonPositioning;
      protected BiConsumer firstPersonPositioning;
      protected BiConsumer firstPersonModelPositioning;
      protected BiConsumer thirdPersonModelPositioning;
      protected BiConsumer inventoryModelPositioning;
      protected BiConsumer entityModelPositioning;
      protected Consumer firstPersonLeftHandPositioning;
      protected Consumer firstPersonRightHandPositioning;
      protected CreativeTabs tab;
      protected AttachmentCategory attachmentCategory;
      private List texturedModels = new ArrayList();
      private int maxStackSize = 1;
      private CraftingComplexity craftingComplexity;
      private Object[] craftingMaterials;
      private int craftingCount = 1;
      private long duration = 300000L;

      public ItemWirelessCamera.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public ItemWirelessCamera.Builder withCreativeTab(CreativeTabs tab) {
         this.tab = tab;
         return this;
      }

      public ItemWirelessCamera.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public ItemWirelessCamera.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public ItemWirelessCamera.Builder withTextureName(String textureName) {
         this.textureName = textureName.toLowerCase();
         return this;
      }

      public ItemWirelessCamera.Builder withMaxStackSize(int maxStackSize) {
         this.maxStackSize = maxStackSize;
         return this;
      }

      public ItemWirelessCamera.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withThirdPersonPositioning(BiConsumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withFirstPersonPositioning(BiConsumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withFirstPersonModelPositioning(BiConsumer firstPersonModelPositioning) {
         this.firstPersonModelPositioning = firstPersonModelPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withEntityModelPositioning(BiConsumer entityModelPositioning) {
         this.entityModelPositioning = entityModelPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withInventoryModelPositioning(BiConsumer inventoryModelPositioning) {
         this.inventoryModelPositioning = inventoryModelPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withThirdPersonModelPositioning(BiConsumer thirdPersonModelPositioning) {
         this.thirdPersonModelPositioning = thirdPersonModelPositioning;
         return this;
      }

      public ItemWirelessCamera.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public ItemWirelessCamera.Builder withModel(ModelBase model, String textureName) {
         this.texturedModels.add(new Tuple(model, textureName.toLowerCase()));
         return this;
      }

      public ItemWirelessCamera.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
         return this.withCrafting(1, craftingComplexity, craftingMaterials);
      }

      public ItemWirelessCamera.Builder withCrafting(int craftingCount, CraftingComplexity craftingComplexity, Object... craftingMaterials) {
         if(craftingComplexity == null) {
            throw new IllegalArgumentException("Crafting complexity not set");
         } else if(craftingMaterials.length < 2) {
            throw new IllegalArgumentException("2 or more materials required for crafting");
         } else if(craftingCount == 0) {
            throw new IllegalArgumentException("Invalid item count");
         } else {
            this.craftingComplexity = craftingComplexity;
            this.craftingMaterials = craftingMaterials;
            this.craftingCount = craftingCount;
            return this;
         }
      }

      public ItemWirelessCamera.Builder withDuration(long duration) {
         this.duration = duration;
         return this;
      }

      public ItemWirelessCamera build(ModContext modContext) {
         ItemWirelessCamera camera = new ItemWirelessCamera(this, modContext);
         camera.setUnlocalizedName(this.modId + "_" + this.name);
         camera.setCreativeTab(this.tab);
         camera.maxStackSize = this.maxStackSize;
         this.texturedModels.forEach((tm) -> {
            camera.texturedModels.add(new Tuple(tm.getU(), addFileExtension((String)tm.getV(), ".png")));
         });
         if(this.model != null) {
            this.texturedModels.add(new Tuple(this.model, addFileExtension(this.textureName, ".png")));
         }

         if(this.model != null || !this.texturedModels.isEmpty()) {
            modContext.registerRenderableItem(this.name, camera, CompatibilityProvider.compatibility.isClientSide()?this.registerRenderer(camera, modContext):null);
         }

         if(this.craftingComplexity != null) {
            OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9).build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
            List shape = modContext.getRecipeManager().createShapedRecipe(camera, this.name, optionsMetadata);
            ItemStack itemStack = new ItemStack(camera);
            CompatibilityProvider.compatibility.setStackSize(itemStack, this.craftingCount);
            if(optionsMetadata.hasOres()) {
               CompatibilityProvider.compatibility.addShapedOreRecipe(itemStack, shape.toArray());
            } else {
               CompatibilityProvider.compatibility.addShapedRecipe(itemStack, shape.toArray());
            }
         }

         return camera;
      }

      static String addFileExtension(String s, String ext) {
         return s != null && !s.endsWith(ext)?s + ext:s;
      }

      protected static String stripFileExtension(String str, String extension) {
         return str.endsWith(extension)?str.substring(0, str.length() - extension.length()):str;
      }

      private Object registerRenderer(ItemWirelessCamera camera, ModContext modContext) {
         return (new StaticModelSourceRenderer.Builder()).withEntityPositioning(this.entityPositioning).withFirstPersonPositioning(this.firstPersonPositioning).withThirdPersonPositioning(this.thirdPersonPositioning).withInventoryPositioning(this.inventoryPositioning).withEntityModelPositioning(this.entityModelPositioning).withFirstPersonModelPositioning(this.firstPersonModelPositioning).withThirdPersonModelPositioning(this.thirdPersonModelPositioning).withInventoryModelPositioning(this.inventoryModelPositioning).withFirstPersonHandPositioning(this.firstPersonLeftHandPositioning, this.firstPersonRightHandPositioning).withModContext(modContext).withModId(this.modId).build();
      }
   }
}
