package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.StaticModelSourceRenderer;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class AttachmentBuilder {
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
   protected ItemAttachment.ApplyHandler apply;
   protected ItemAttachment.ApplyHandler remove;
   protected ItemAttachment.ApplyHandler2 apply2;
   protected ItemAttachment.ApplyHandler2 remove2;
   private String crosshair;
   private CustomRenderer postRenderer;
   private List texturedModels = new ArrayList();
   private boolean isRenderablePart;
   private int maxStackSize = 1;
   private Function informationProvider;
   private CraftingComplexity craftingComplexity;
   private Object[] craftingMaterials;
   Map compatibleAttachments = new HashMap();
   private int craftingCount = 1;
   private Object[] craftingRecipe;

   public AttachmentBuilder withCategory(AttachmentCategory attachmentCategory) {
      this.attachmentCategory = attachmentCategory;
      return this;
   }

   public AttachmentBuilder withName(String name) {
      this.name = name;
      return this;
   }

   public AttachmentBuilder withCreativeTab(CreativeTabs tab) {
      this.tab = tab;
      return this;
   }

   public AttachmentBuilder withModId(String modId) {
      this.modId = modId;
      return this;
   }

   public AttachmentBuilder withCompatibleAttachment(ItemAttachment attachment, Consumer positioner) {
      this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
      return this;
   }

   public AttachmentBuilder withModel(ModelBase model) {
      this.model = model;
      return this;
   }

   public AttachmentBuilder withTextureName(String textureName) {
      this.textureName = textureName.toLowerCase();
      return this;
   }

   public AttachmentBuilder withMaxStackSize(int maxStackSize) {
      this.maxStackSize = maxStackSize;
      return this;
   }

   public AttachmentBuilder withEntityPositioning(Consumer entityPositioning) {
      this.entityPositioning = entityPositioning;
      return this;
   }

   public AttachmentBuilder withInventoryPositioning(Consumer inventoryPositioning) {
      this.inventoryPositioning = inventoryPositioning;
      return this;
   }

   public AttachmentBuilder withThirdPersonPositioning(BiConsumer thirdPersonPositioning) {
      this.thirdPersonPositioning = thirdPersonPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonPositioning(BiConsumer firstPersonPositioning) {
      this.firstPersonPositioning = firstPersonPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonModelPositioning(BiConsumer firstPersonModelPositioning) {
      this.firstPersonModelPositioning = firstPersonModelPositioning;
      return this;
   }

   public AttachmentBuilder withEntityModelPositioning(BiConsumer entityModelPositioning) {
      this.entityModelPositioning = entityModelPositioning;
      return this;
   }

   public AttachmentBuilder withInventoryModelPositioning(BiConsumer inventoryModelPositioning) {
      this.inventoryModelPositioning = inventoryModelPositioning;
      return this;
   }

   public AttachmentBuilder withThirdPersonModelPositioning(BiConsumer thirdPersonModelPositioning) {
      this.thirdPersonModelPositioning = thirdPersonModelPositioning;
      return this;
   }

   public AttachmentBuilder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
      this.firstPersonLeftHandPositioning = leftHand;
      this.firstPersonRightHandPositioning = rightHand;
      return this;
   }

   public AttachmentBuilder withCrosshair(String crosshair) {
      this.crosshair = crosshair.toLowerCase();
      return this;
   }

   public AttachmentBuilder withPostRender(CustomRenderer postRenderer) {
      this.postRenderer = postRenderer;
      return this;
   }

   public AttachmentBuilder withModel(ModelBase model, String textureName) {
      this.texturedModels.add(new Tuple(model, textureName.toLowerCase()));
      return this;
   }

   public AttachmentBuilder withRenderablePart() {
      this.isRenderablePart = true;
      return this;
   }

   public AttachmentBuilder withApply(ItemAttachment.ApplyHandler apply) {
      this.apply = apply;
      return this;
   }

   public AttachmentBuilder withRemove(ItemAttachment.ApplyHandler remove) {
      this.remove = remove;
      return this;
   }

   public AttachmentBuilder withApply(ItemAttachment.ApplyHandler2 apply) {
      this.apply2 = apply;
      return this;
   }

   public AttachmentBuilder withRemove(ItemAttachment.ApplyHandler2 remove) {
      this.remove2 = remove;
      return this;
   }

   public AttachmentBuilder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
      return this.withCrafting(1, craftingComplexity, craftingMaterials);
   }

   public AttachmentBuilder withInformationProvider(Function informationProvider) {
      this.informationProvider = informationProvider;
      return this;
   }

   public AttachmentBuilder withCrafting(int craftingCount, CraftingComplexity craftingComplexity, Object... craftingMaterials) {
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

   public AttachmentBuilder withCraftingRecipe(Object... craftingRecipe) {
      this.craftingRecipe = craftingRecipe;
      return this;
   }

   protected ItemAttachment createAttachment(ModContext modContext) {
      return new ItemAttachment(this.getModId(), this.attachmentCategory, this.crosshair, this.apply, this.remove);
   }

   public ItemAttachment build(ModContext modContext) {
      ItemAttachment attachment = this.createAttachment(modContext);
      attachment.setUnlocalizedName(this.getModId() + "_" + this.name);
      attachment.setCreativeTab(this.tab);
      attachment.setPostRenderer(this.postRenderer);
      attachment.setName(this.name);
      attachment.apply2 = this.apply2;
      attachment.remove2 = this.remove2;
      attachment.maxStackSize = this.maxStackSize;
      if(attachment.getInformationProvider() == null) {
         attachment.setInformationProvider(this.informationProvider);
      }

      if(this.getTextureName() != null) {
         attachment.setTextureName(this.getModId() + ":" + stripFileExtension(this.getTextureName(), ".png"));
      }

      if(this.isRenderablePart) {
         attachment.setRenderablePart(new Part() {
            public String toString() {
               return AttachmentBuilder.this.name != null?"Part [" + AttachmentBuilder.this.name + "]":super.toString();
            }
         });
      }

      if(this.getModel() != null) {
         attachment.addModel(this.getModel(), addFileExtension(this.getTextureName(), ".png"));
      }

      this.texturedModels.forEach((tm) -> {
         attachment.addModel((ModelBase)tm.getU(), addFileExtension((String)tm.getV(), ".png"));
      });
      this.compatibleAttachments.values().forEach((a) -> {
         attachment.addCompatibleAttachment(a);
      });
      if(this.getModel() != null || !this.texturedModels.isEmpty()) {
         modContext.registerRenderableItem(this.name, attachment, CompatibilityProvider.compatibility.isClientSide()?this.registerRenderer(attachment, modContext):null);
      }

      if(this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
         modContext.getRecipeManager().registerShapedRecipe((Item)attachment, this.craftingRecipe);
      } else if(this.craftingComplexity != null) {
         OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9).build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
         List shape = modContext.getRecipeManager().createShapedRecipe(attachment, this.name, optionsMetadata);
         ItemStack itemStack = new ItemStack(attachment);
         CompatibilityProvider.compatibility.setStackSize(itemStack, this.craftingCount);
         if(optionsMetadata.hasOres()) {
            CompatibilityProvider.compatibility.addShapedOreRecipe(itemStack, shape.toArray());
         } else {
            CompatibilityProvider.compatibility.addShapedRecipe(itemStack, shape.toArray());
         }
      } else if(attachment.getCategory() == AttachmentCategory.GRIP || attachment.getCategory() == AttachmentCategory.SCOPE || attachment.getCategory() == AttachmentCategory.MAGAZINE || attachment.getCategory() == AttachmentCategory.BULLET || attachment.getCategory() == AttachmentCategory.SILENCER || attachment.getCategory() == AttachmentCategory.SKIN) {
         System.err.println("!!!No recipe defined for attachment " + this.name);
      }

      return attachment;
   }

   private Object registerRenderer(ItemAttachment attachment, ModContext modContext) {
      return (new StaticModelSourceRenderer.Builder()).withEntityPositioning(this.entityPositioning).withFirstPersonPositioning(this.firstPersonPositioning).withThirdPersonPositioning(this.thirdPersonPositioning).withInventoryPositioning(this.inventoryPositioning).withEntityModelPositioning(this.entityModelPositioning).withFirstPersonModelPositioning(this.firstPersonModelPositioning).withThirdPersonModelPositioning(this.thirdPersonModelPositioning).withInventoryModelPositioning(this.inventoryModelPositioning).withFirstPersonHandPositioning(this.firstPersonLeftHandPositioning, this.firstPersonRightHandPositioning).withModContext(modContext).withModId(this.getModId()).build();
   }

   static String addFileExtension(String s, String ext) {
      return s != null && !s.endsWith(ext)?s + ext:s;
   }

   protected static String stripFileExtension(String str, String extension) {
      return str.endsWith(extension)?str.substring(0, str.length() - extension.length()):str;
   }

   public ItemAttachment build(ModContext modContext, Class target) {
      return (ItemAttachment)target.cast(this.build(modContext));
   }

   public String getModId() {
      return this.modId;
   }

   public ModelBase getModel() {
      return this.model;
   }

   public String getTextureName() {
      return this.textureName;
   }
}
