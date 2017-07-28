package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.ImpactHandler;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Modifiable;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItem;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.melee.MeleeAttachmentAspect;
import com.vicmatskiv.weaponlib.melee.MeleeRenderer;
import com.vicmatskiv.weaponlib.melee.MeleeSkin;
import com.vicmatskiv.weaponlib.melee.MeleeState;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ItemMelee extends CompatibleItem implements PlayerItemInstanceFactory, AttachmentContainer, Modifiable, Updatable {
   private static final Logger logger = LogManager.getLogger(ItemMelee.class);
   static final long MAX_RELOAD_TIMEOUT_TICKS = 60L;
   static final long MAX_UNLOAD_TIMEOUT_TICKS = 60L;
   ItemMelee.Builder builder;
   private ModContext modContext;
   private CompatibleSound attackSound;
   private CompatibleSound silencedShootSound;
   private CompatibleSound heavyAttackSound;
   private CompatibleSound unloadSound;
   private CompatibleSound ejectSpentRoundSound;

   ItemMelee(ItemMelee.Builder builder, ModContext modContext) {
      this.builder = builder;
      this.modContext = modContext;
      this.setMaxStackSize(1);
   }

   public String getName() {
      return this.builder.name;
   }

   public CompatibleSound getShootSound() {
      return this.attackSound;
   }

   public CompatibleSound getSilencedShootSound() {
      return this.silencedShootSound;
   }

   public CompatibleSound getReloadSound() {
      return this.heavyAttackSound;
   }

   public CompatibleSound getUnloadSound() {
      return this.unloadSound;
   }

   public CompatibleSound getEjectSpentRoundSound() {
      return this.ejectSpentRoundSound;
   }

   public boolean onEntitySwing(EntityLivingBase entityLiving, ItemStack itemStack) {
      return true;
   }

   public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean active) {
   }

   Map getCompatibleAttachments() {
      return this.builder.compatibleAttachments;
   }

   public static boolean isActiveAttachment(PlayerMeleeInstance weaponInstance, ItemAttachment attachment) {
      return weaponInstance != null?MeleeAttachmentAspect.isActiveAttachment(attachment, weaponInstance):false;
   }

   public int getMaxItemUseDuration(ItemStack itemStack) {
      return 0;
   }

   public List getActiveAttachments(EntityPlayer player, ItemStack itemStack) {
      return this.modContext.getMeleeAttachmentAspect().getActiveAttachments(player, itemStack);
   }

   public MeleeRenderer getRenderer() {
      return this.builder.renderer;
   }

   List getCompatibleAttachments(Class target) {
      return (List)this.builder.compatibleAttachments.entrySet().stream().filter((e) -> {
         return target.isInstance(e.getKey());
      }).map((e) -> {
         return (ItemAttachment)e.getKey();
      }).collect(Collectors.toList());
   }

   public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean p_77624_4_) {
      if(list != null && this.builder.informationProvider != null) {
         list.addAll((Collection)this.builder.informationProvider.apply(itemStack));
      }

   }

   public void update(EntityPlayer player) {
      this.modContext.getMeleeAttackAspect().onUpdate(player);
      this.modContext.getMeleeAttachmentAspect().onUpdate(player);
   }

   public PlayerMeleeInstance createItemInstance(EntityPlayer player, ItemStack itemStack, int slot) {
      PlayerMeleeInstance instance = new PlayerMeleeInstance(slot, player, itemStack);
      instance.setState(MeleeState.READY);
      Iterator var5 = ((ItemMelee)itemStack.getItem()).getCompatibleAttachments().values().iterator();

      while(var5.hasNext()) {
         CompatibleAttachment compatibleAttachment = (CompatibleAttachment)var5.next();
         ItemAttachment attachment = compatibleAttachment.getAttachment();
         if(compatibleAttachment.isDefault() && attachment.getApply3() != null) {
            attachment.getApply3().apply(attachment, instance);
         }
      }

      return instance;
   }

   public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
      this.modContext.getMeleeAttachmentAspect().toggleClientAttachmentSelectionMode(player);
   }

   public String getTextureName() {
      return (String)this.builder.textureNames.get(0);
   }

   public ItemAttachment.ApplyHandler2 getEquivalentHandler(AttachmentCategory attachmentCategory) {
      return null;
   }

   public void attack(EntityPlayer player, boolean heavy) {
      if(heavy) {
         this.modContext.getMeleeAttackAspect().onAttackButtonClick(player);
      } else {
         this.modContext.getMeleeAttackAspect().onHeavyAttackButtonClick(player);
      }

   }

   public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase player) {
      PlayerItemInstance instance = Tags.getInstance(stack);
      if(instance instanceof PlayerMeleeInstance) {
         logger.debug("Player {} hits {} with {} in state {}", new Object[]{player, target, instance, instance.getState()});
      }

      return true;
   }

   public float getDamage(boolean isHeavyAttack) {
      return isHeavyAttack?this.builder.heavyAttackDamage:this.builder.attackDamage;
   }

   public long getPrepareStubTimeout() {
      return (long)((Integer)this.builder.prepareStubTimeout.get()).intValue();
   }

   public long getPrepareHeavyStubTimeout() {
      return (long)((Integer)this.builder.prepareHeavyStubTimeout.get()).intValue();
   }

   public long getAttackCooldownTimeout() {
      return (long)((Integer)this.builder.attackCooldownTimeout.get()).intValue();
   }

   public long getHeavyAttackCooldownTimeout() {
      return (long)((Integer)this.builder.heavyAttackCooldownTimeout.get()).intValue();
   }

   public CompatibleSound getHeavyAtackSound() {
      return this.heavyAttackSound;
   }

   public CompatibleSound getLightAtackSound() {
      return this.attackSound;
   }

   public Collection getCompatibleAttachments(AttachmentCategory... categories) {
      Collection c = this.builder.compatibleAttachments.values();
      List inputCategoryList = Arrays.asList(categories);
      return (Collection)c.stream().filter((e) -> {
         return inputCategoryList.contains(e);
      }).collect(Collectors.toList());
   }

   public static enum State {
      READY,
      SHOOTING,
      RELOAD_REQUESTED,
      RELOAD_CONFIRMED,
      UNLOAD_STARTED,
      UNLOAD_REQUESTED_FROM_SERVER,
      UNLOAD_CONFIRMED,
      PAUSED,
      MODIFYING,
      EJECT_SPENT_ROUND;
   }

   public static class Builder {
      private static final int DEFAULT_PREPARE_STUB_TIMEOUT = 100;
      private static final int DEFAULT_ATTACK_COOLDOWN_TIMEOUT = 500;
      private static final int DEFAULT_HEAVY_ATTACK_COOLDOWN_TIMEOUT = 1000;
      String name;
      List textureNames = new ArrayList();
      private String attackSound;
      private String heavyAttackSound;
      private CreativeTabs creativeTab;
      private MeleeRenderer renderer;
      private String modId;
      Map compatibleAttachments = new HashMap();
      private Class spawnEntityClass;
      ImpactHandler blockImpactHandler;
      private Function informationProvider;
      private CraftingComplexity craftingComplexity;
      private Object[] craftingMaterials;
      public float attackDamage = 1.0F;
      public float heavyAttackDamage = 2.0F;
      public Supplier prepareStubTimeout = () -> {
         return Integer.valueOf(100);
      };
      public Supplier prepareHeavyStubTimeout = () -> {
         return Integer.valueOf(100);
      };
      public Supplier attackCooldownTimeout = () -> {
         return Integer.valueOf(500);
      };
      public Supplier heavyAttackCooldownTimeout = () -> {
         return Integer.valueOf(1000);
      };
      private Object[] craftingRecipe;

      public ItemMelee.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public ItemMelee.Builder withInformationProvider(Function informationProvider) {
         this.informationProvider = informationProvider;
         return this;
      }

      public ItemMelee.Builder withPrepareStubTimeout(Supplier prepareStubTimeout) {
         this.prepareStubTimeout = prepareStubTimeout;
         return this;
      }

      public ItemMelee.Builder withPrepareHeavyStubTimeout(Supplier prepareHeavyStubTimeout) {
         this.prepareHeavyStubTimeout = prepareHeavyStubTimeout;
         return this;
      }

      public ItemMelee.Builder withAttackCooldownTimeout(Supplier attackCooldownTimeout) {
         this.attackCooldownTimeout = attackCooldownTimeout;
         return this;
      }

      public ItemMelee.Builder withHeavyAttackCooldownTimeout(Supplier heavyAttackCooldownTimeout) {
         this.heavyAttackCooldownTimeout = heavyAttackCooldownTimeout;
         return this;
      }

      public ItemMelee.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public ItemMelee.Builder withAttackDamage(float attackDamage) {
         this.attackDamage = attackDamage;
         return this;
      }

      public ItemMelee.Builder withHeavyAttackDamage(float heavyAttackDamage) {
         this.heavyAttackDamage = heavyAttackDamage;
         return this;
      }

      public ItemMelee.Builder withTextureNames(String... textureNames) {
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

      public ItemMelee.Builder withAttackSound(String attackSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.attackSound = attackSound.toLowerCase();
            return this;
         }
      }

      public ItemMelee.Builder withHeavyAttackSound(String heavyAttackSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.heavyAttackSound = heavyAttackSound.toLowerCase();
            return this;
         }
      }

      public ItemMelee.Builder withCreativeTab(CreativeTabs creativeTab) {
         this.creativeTab = creativeTab;
         return this;
      }

      public ItemMelee.Builder withRenderer(MeleeRenderer renderer) {
         this.renderer = renderer;
         return this;
      }

      public ItemMelee.Builder withCompatibleSkin(MeleeSkin skin, String activeTextureName) {
         this.withCompatibleAttachment(skin, (a, i) -> {
            i.setActiveTextureIndex(skin.getTextureVariantIndex(activeTextureName.toLowerCase()));
         }, (a, i) -> {
         });
         return this;
      }

      public ItemMelee.Builder withCompatibleAttachment(ItemAttachment attachment, ItemAttachment.MeleeWeaponApplyHandler applyHandler, ItemAttachment.MeleeWeaponApplyHandler removeHandler) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, applyHandler, removeHandler));
         return this;
      }

      public ItemMelee.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer positioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, (Consumer)null, false));
         return this;
      }

      public ItemMelee.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, BiConsumer positioning, Consumer modelPositioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault));
         return this;
      }

      public ItemMelee.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, Consumer positioner) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner, isDefault));
         return this;
      }

      public ItemMelee.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
         if(craftingComplexity == null) {
            throw new IllegalArgumentException("Crafting complexity not set");
         } else if(craftingMaterials.length < 2) {
            throw new IllegalArgumentException("2 or more materials required for crafting");
         } else {
            this.craftingComplexity = craftingComplexity;
            this.craftingMaterials = craftingMaterials;
            return this;
         }
      }

      public ItemMelee.Builder withCraftingRecipe(Object... craftingRecipe) {
         this.craftingRecipe = craftingRecipe;
         return this;
      }

      public ItemMelee build(ModContext modContext) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else if(this.name == null) {
            throw new IllegalStateException("Item name not provided");
         } else {
            if(this.heavyAttackSound == null) {
               this.heavyAttackSound = this.attackSound;
            }

            if(this.spawnEntityClass == null) {
               this.spawnEntityClass = WeaponSpawnEntity.class;
            }

            ItemMelee itemMelee = new ItemMelee(this, modContext);
            itemMelee.attackSound = this.attackSound != null?modContext.registerSound(this.attackSound):CompatibleSound.SNOWBALL_THROW;
            itemMelee.heavyAttackSound = this.heavyAttackSound != null?modContext.registerSound(this.heavyAttackSound):CompatibleSound.SNOWBALL_THROW;
            itemMelee.setCreativeTab(this.creativeTab);
            itemMelee.setUnlocalizedName(this.name);
            modContext.registerMeleeWeapon(this.name, itemMelee, this.renderer);
            List shape;
            if(this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
               ItemStack optionsMetadata1 = new ItemStack(itemMelee);
               shape = modContext.getRecipeManager().registerShapedRecipe((Item)itemMelee, this.craftingRecipe);
               boolean hasOres = Arrays.stream(this.craftingRecipe).anyMatch((r) -> {
                  return r instanceof String;
               });
               if(hasOres) {
                  CompatibilityProvider.compatibility.addShapedOreRecipe(optionsMetadata1, shape.toArray());
               } else {
                  CompatibilityProvider.compatibility.addShapedRecipe(optionsMetadata1, shape.toArray());
               }
            } else if(this.craftingComplexity != null) {
               OptionsMetadata optionsMetadata = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9).build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
               shape = modContext.getRecipeManager().createShapedRecipe(itemMelee, itemMelee.getName(), optionsMetadata);
               CompatibilityProvider.compatibility.addShapedRecipe(new ItemStack(itemMelee), shape.toArray());
            } else {
               System.err.println("!!!No recipe defined for melee weapon " + this.name);
            }

            return itemMelee;
         }
      }
   }
}
