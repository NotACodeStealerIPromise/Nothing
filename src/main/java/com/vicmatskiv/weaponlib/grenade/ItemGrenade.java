package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItem;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.GrenadeState;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemGrenade extends CompatibleItem implements PlayerItemInstanceFactory, AttachmentContainer, Updatable {
   public static final int DEFAULT_FUSE_TIMEOUT = 3000;
   public static final float DEFAULT_EXPLOSION_STRENTH = 2.0F;
   public static final int EXPLODE_ON_IMPACT = -1;
   public static final float DEFAULT_GRAVITY_VELOCITY = 0.06F;
   public static final float DEFAULT_FAR_VELOCITY = 1.3F;
   public static final float DEFAULT_VELOCITY = 1.0F;
   public static final float DEFAULT_ROTATION_SLOWDOWN_FACTOR = 0.99F;
   public static final float DEFAULT_EFFECTIVE_RADIUS = 20.0F;
   public static final float DEFAULT_FRAGMENT_DAMAGE = 15.0F;
   public static final int DEFAULT_FRAGMENT_COUNT = 100;
   ItemGrenade.Builder builder;
   private ModContext modContext;
   private CompatibleSound bounceHardSound;
   private CompatibleSound bounceSoftSound;
   private CompatibleSound explosionSound;
   private CompatibleSound safetyPinOffSound;
   private CompatibleSound throwSound;
   private CompatibleSound stopAfterThrowingSound;

   public ItemGrenade(ItemGrenade.Builder builder, ModContext modContext) {
      this.builder = builder;
      this.modContext = modContext;
      this.maxStackSize = 16;
   }

   public GrenadeRenderer getRenderer() {
      return this.builder.renderer;
   }

   public String getTextureName() {
      return (String)this.builder.textureNames.get(0);
   }

   public boolean hasSafetyPin() {
      return this.builder.explosionTimeout > 0;
   }

   public List getActiveAttachments(EntityPlayer player, ItemStack itemStack) {
      return new ArrayList(this.builder.compatibleAttachments.values());
   }

   Map getCompatibleAttachments() {
      return this.builder.compatibleAttachments;
   }

   public String getName() {
      return this.builder.name;
   }

   public PlayerGrenadeInstance createItemInstance(EntityPlayer player, ItemStack itemStack, int slot) {
      PlayerGrenadeInstance instance = new PlayerGrenadeInstance(slot, player, itemStack);
      instance.setState(GrenadeState.READY);
      return instance;
   }

   public void attack(EntityPlayer player, boolean throwingFar) {
      this.modContext.getGrenadeAttackAspect().onAttackButtonClick(player, throwingFar);
   }

   public void attackUp(EntityPlayer player, boolean throwingFar) {
      this.modContext.getGrenadeAttackAspect().onAttackButtonUp(player, throwingFar);
   }

   public void update(EntityPlayer player) {
      this.modContext.getGrenadeAttackAspect().onUpdate(player);
   }

   public float getExplosionStrength() {
      return this.builder.explosionStrength;
   }

   public int getExplosionTimeout() {
      return this.builder.explosionTimeout;
   }

   public long getThrowTimeout() {
      return 200L;
   }

   public long getTotalTakeSafetyPinOffDuration() {
      return this.builder.renderer.getTotalTakingSafetyPinOffDuration();
   }

   public long getReequipTimeout() {
      return 800L;
   }

   public double getTotalThrowingDuration() {
      return (double)this.builder.renderer.getTotalThrowingDuration();
   }

   public float getVelocity() {
      return ((Float)this.builder.velocity.get()).floatValue();
   }

   public float getFarVelocity() {
      return ((Float)this.builder.farVelocity.get()).floatValue();
   }

   public float getGravityVelocity() {
      return ((Float)this.builder.gravityVelocity.get()).floatValue();
   }

   public float getRotationSlowdownFactor() {
      return ((Float)this.builder.rotationSlowdownFactor.get()).floatValue();
   }

   public CompatibleSound getBounceHardSound() {
      return this.bounceHardSound;
   }

   public CompatibleSound getBounceSoftSound() {
      return this.bounceSoftSound;
   }

   public CompatibleSound getExplosionSound() {
      return this.explosionSound;
   }

   public CompatibleSound getSafetyPinOffSound() {
      return this.safetyPinOffSound;
   }

   public CompatibleSound getThrowSound() {
      return this.throwSound;
   }

   public CompatibleSound getStopAfterThrowingSound() {
      return this.stopAfterThrowingSound;
   }

   public float getEffectiveRadius() {
      return this.builder.effectiveRadius;
   }

   public float getFragmentDamage() {
      return this.builder.fragmentDamage;
   }

   public int getFragmentCount() {
      return this.builder.fragmentCount;
   }

   public boolean isSmokeOnly() {
      return this.builder.smokeOnly;
   }

   public long getActiveDuration() {
      return this.builder.activeDuration;
   }

   public Collection getCompatibleAttachments(AttachmentCategory... categories) {
      Collection c = this.builder.compatibleAttachments.values();
      List inputCategoryList = Arrays.asList(categories);
      return (Collection)c.stream().filter((e) -> {
         return inputCategoryList.contains(e);
      }).collect(Collectors.toList());
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
      protected Map compatibleAttachments = new HashMap();
      private Supplier velocity = () -> {
         return Float.valueOf(1.0F);
      };
      private Supplier farVelocity = () -> {
         return Float.valueOf(1.3F);
      };
      private Supplier gravityVelocity = () -> {
         return Float.valueOf(0.06F);
      };
      private int maxStackSize = 1;
      private int explosionTimeout = 3000;
      private float explosionStrength = 2.0F;
      protected CreativeTabs tab;
      private CraftingComplexity craftingComplexity;
      private Object[] craftingMaterials;
      private int craftingCount = 1;
      private GrenadeRenderer renderer;
      List textureNames = new ArrayList();
      private Supplier rotationSlowdownFactor = () -> {
         return Float.valueOf(0.99F);
      };
      private String bounceHardSound;
      private String bounceSoftSound;
      private String explosionSound;
      private String safetyPinOffSound;
      private String stopAfterThrowingSound;
      private String throwSound;
      private float effectiveRadius = 20.0F;
      private float fragmentDamage = 15.0F;
      private int fragmentCount = 100;
      private boolean smokeOnly;
      private long activeDuration;
      private Object[] craftingRecipe;

      public ItemGrenade.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public ItemGrenade.Builder withCreativeTab(CreativeTabs tab) {
         this.tab = tab;
         return this;
      }

      public ItemGrenade.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public ItemGrenade.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public ItemGrenade.Builder withVelocity(Supplier velocity) {
         this.velocity = velocity;
         return this;
      }

      public ItemGrenade.Builder withFarVelocity(Supplier farVelocity) {
         this.farVelocity = farVelocity;
         return this;
      }

      public ItemGrenade.Builder withGravityVelocity(Supplier gravityVelocity) {
         this.gravityVelocity = gravityVelocity;
         return this;
      }

      public ItemGrenade.Builder withRotationSlowdownFactor(Supplier rotationSlowdownFactor) {
         this.rotationSlowdownFactor = rotationSlowdownFactor;
         return this;
      }

      public ItemGrenade.Builder withExplosionStrength(float explosionStrength) {
         this.explosionStrength = explosionStrength;
         return this;
      }

      public ItemGrenade.Builder withExplosionTimeout(int explosionTimeout) {
         this.explosionTimeout = explosionTimeout;
         return this;
      }

      public ItemGrenade.Builder withExplosionOnImpact() {
         this.explosionTimeout = -1;
         return this;
      }

      public ItemGrenade.Builder withSmokeOnly() {
         this.smokeOnly = true;
         return this;
      }

      public ItemGrenade.Builder withTextureNames(String... textureNames) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            String[] var2 = textureNames;
            int var3 = textureNames.length;

            for(int var4 = 0; var4 < var3; ++var4) {
               String textureName = var2[var4];
               this.textureNames.add(textureName.toLowerCase() + ".png");
            }

            return this;
         }
      }

      public ItemGrenade.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer positioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, (Consumer)null, true));
         return this;
      }

      public ItemGrenade.Builder withMaxStackSize(int maxStackSize) {
         this.maxStackSize = maxStackSize;
         return this;
      }

      public ItemGrenade.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public ItemGrenade.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public ItemGrenade.Builder withThirdPersonPositioning(BiConsumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public ItemGrenade.Builder withFirstPersonPositioning(BiConsumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public ItemGrenade.Builder withFirstPersonModelPositioning(BiConsumer firstPersonModelPositioning) {
         this.firstPersonModelPositioning = firstPersonModelPositioning;
         return this;
      }

      public ItemGrenade.Builder withEntityModelPositioning(BiConsumer entityModelPositioning) {
         this.entityModelPositioning = entityModelPositioning;
         return this;
      }

      public ItemGrenade.Builder withInventoryModelPositioning(BiConsumer inventoryModelPositioning) {
         this.inventoryModelPositioning = inventoryModelPositioning;
         return this;
      }

      public ItemGrenade.Builder withThirdPersonModelPositioning(BiConsumer thirdPersonModelPositioning) {
         this.thirdPersonModelPositioning = thirdPersonModelPositioning;
         return this;
      }

      public ItemGrenade.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public ItemGrenade.Builder withRenderer(GrenadeRenderer renderer) {
         this.renderer = renderer;
         return this;
      }

      public ItemGrenade.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
         return this.withCrafting(1, craftingComplexity, craftingMaterials);
      }

      public ItemGrenade.Builder withCrafting(int craftingCount, CraftingComplexity craftingComplexity, Object... craftingMaterials) {
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

      public ItemGrenade.Builder withCraftingRecipe(Object... craftingRecipe) {
         this.craftingRecipe = craftingRecipe;
         return this;
      }

      public ItemGrenade.Builder withBounceHardSound(String sound) {
         this.bounceHardSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withBounceSoftSound(String sound) {
         this.bounceSoftSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withExplosionSound(String sound) {
         this.explosionSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withSafetyPinOffSound(String sound) {
         this.safetyPinOffSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withThrowSound(String sound) {
         this.throwSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withStopAfterThrowingSond(String sound) {
         this.stopAfterThrowingSound = sound != null?sound.toLowerCase():null;
         return this;
      }

      public ItemGrenade.Builder withEffectiveRadius(float effectiveRadius) {
         this.effectiveRadius = effectiveRadius;
         return this;
      }

      public ItemGrenade.Builder withFragmentDamage(float fragmentDamage) {
         this.fragmentDamage = fragmentDamage;
         return this;
      }

      public ItemGrenade.Builder withFragmentCount(int fragmentCount) {
         this.fragmentCount = fragmentCount;
         return this;
      }

      public ItemGrenade.Builder withActiveDuration(long duration) {
         this.activeDuration = duration;
         return this;
      }

      public ItemGrenade build(ModContext modContext) {
         ItemGrenade grenade = new ItemGrenade(this, modContext);
         grenade.setUnlocalizedName(this.modId + "_" + this.name);
         grenade.setCreativeTab(this.tab);
         grenade.maxStackSize = this.maxStackSize;
         if(this.bounceHardSound != null) {
            grenade.bounceHardSound = modContext.registerSound(this.bounceHardSound);
         }

         if(this.bounceSoftSound != null) {
            grenade.bounceSoftSound = modContext.registerSound(this.bounceSoftSound);
         }

         if(this.explosionSound != null) {
            grenade.explosionSound = modContext.registerSound(this.explosionSound);
         }

         if(this.safetyPinOffSound != null) {
            grenade.safetyPinOffSound = modContext.registerSound(this.safetyPinOffSound);
         }

         if(this.throwSound != null) {
            grenade.throwSound = modContext.registerSound(this.throwSound);
         }

         if(this.stopAfterThrowingSound != null) {
            grenade.stopAfterThrowingSound = modContext.registerSound(this.stopAfterThrowingSound);
         }

         modContext.registerGrenadeWeapon(this.name, grenade, this.renderer);
         List shape;
         if(this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
            ItemStack optionsMetadata1 = new ItemStack(grenade);
            shape = modContext.getRecipeManager().registerShapedRecipe((Item)grenade, this.craftingRecipe);
            boolean itemStack1 = Arrays.stream(this.craftingRecipe).anyMatch((r) -> {
               return r instanceof String;
            });
            if(itemStack1) {
               CompatibilityProvider.compatibility.addShapedOreRecipe(optionsMetadata1, shape.toArray());
            } else {
               CompatibilityProvider.compatibility.addShapedRecipe(optionsMetadata1, shape.toArray());
            }
         } else if(this.craftingComplexity != null) {
            OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9).build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
            shape = modContext.getRecipeManager().createShapedRecipe(grenade, this.name, optionsMetadata);
            ItemStack itemStack = new ItemStack(grenade);
            CompatibilityProvider.compatibility.setStackSize(itemStack, this.craftingCount);
            if(optionsMetadata.hasOres()) {
               CompatibilityProvider.compatibility.addShapedOreRecipe(itemStack, shape.toArray());
            } else {
               CompatibilityProvider.compatibility.addShapedRecipe(itemStack, shape.toArray());
            }
         } else {
            System.err.println("!!!No recipe defined for grenade " + this.name);
         }

         return grenade;
      }

      static String addFileExtension(String s, String ext) {
         return s != null && !s.endsWith(ext)?s + ext:s;
      }

      protected static String stripFileExtension(String str, String extension) {
         return str.endsWith(extension)?str.substring(0, str.length() - extension.length()):str;
      }
   }
}
