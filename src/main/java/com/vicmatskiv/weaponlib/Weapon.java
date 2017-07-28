package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.BlockHitMessage;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.EntityShellCasing;
import com.vicmatskiv.weaponlib.ImpactHandler;
import com.vicmatskiv.weaponlib.ItemAmmo;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemBullet;
import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.ItemScope;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Modifiable;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Reloadable;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItem;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTargetPoint;
import com.vicmatskiv.weaponlib.config.Gun;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.model.Shell;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Weapon extends CompatibleItem implements PlayerItemInstanceFactory, AttachmentContainer, Reloadable, Modifiable, Updatable {
   private static final Logger logger = LogManager.getLogger(Weapon.class);
   private static final long DEFAULT_RELOADING_TIMEOUT_TICKS = 10L;
   private static final long DEFAULT_UNLOADING_TIMEOUT_TICKS = 10L;
   static final long MAX_RELOAD_TIMEOUT_TICKS = 60L;
   static final long MAX_UNLOAD_TIMEOUT_TICKS = 60L;
   public static final float DEFAULT_SHELL_CASING_FORWARD_OFFSET = 0.1F;
   public static final float DEFAULT_SHELL_CASING_VERTICAL_OFFSET = 0.0F;
   public static final float DEFAULT_SHELL_CASING_SIDE_OFFSET = 0.15F;
   public static final float DEFAULT_SHELL_CASING_SIDE_OFFSET_AIMED = 0.05F;
   private static final float DEFAULT_FIRE_RATE = 0.5F;
   private static final float DEFAULT_SILENCED_SHOOT_SOUND_VOLUME = 0.7F;
   private static final float DEFAULT_SHOOT_SOUND_VOLUME = 10.0F;
   Weapon.Builder builder;
   private ModContext modContext;
   private CompatibleSound shootSound;
   private CompatibleSound silencedShootSound;
   private CompatibleSound reloadSound;
   private CompatibleSound unloadSound;
   private CompatibleSound ejectSpentRoundSound;

   Weapon(Weapon.Builder builder, ModContext modContext) {
      this.builder = builder;
      this.modContext = modContext;
      this.setMaxStackSize(1);
   }

   public String getName() {
      return this.builder.name;
   }

   public CompatibleSound getShootSound() {
      return this.shootSound;
   }

   public CompatibleSound getSilencedShootSound() {
      return this.silencedShootSound;
   }

   public CompatibleSound getReloadSound() {
      return this.reloadSound;
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

   void toggleAiming() {
      PlayerWeaponInstance mainHandHeldWeaponInstance = this.modContext.getMainHeldWeapon();
      if(mainHandHeldWeaponInstance != null && (mainHandHeldWeaponInstance.getState() == WeaponState.READY || mainHandHeldWeaponInstance.getState() == WeaponState.EJECT_REQUIRED)) {
         mainHandHeldWeaponInstance.setAimed(!mainHandHeldWeaponInstance.isAimed());
      }

   }

   public void onUpdate(ItemStack itemStack, World world, Entity entity, int p_77663_4_, boolean active) {
   }

   public void changeRecoil(EntityPlayer player, float factor) {
      PlayerWeaponInstance instance = this.modContext.getMainHeldWeapon();
      if(instance != null) {
         float recoil = instance.getWeapon().builder.recoil * factor;
         logger.debug("Changing recoil to {} for instance {}", new Object[]{Float.valueOf(recoil), instance});
         instance.setRecoil(recoil);
      }

   }

   public Map getCompatibleAttachments() {
      return this.builder.compatibleAttachments;
   }

   public Collection getCompatibleAttachments(AttachmentCategory... categories) {
      Collection c = this.builder.compatibleAttachments.values();
      List inputCategoryList = Arrays.asList(categories);
      return (Collection)c.stream().filter((e) -> {
         return inputCategoryList.contains(e.getAttachment().getCategory());
      }).collect(Collectors.toList());
   }

   String getCrosshair(PlayerWeaponInstance weaponInstance) {
      if(weaponInstance.isAimed()) {
         String crosshair = null;
         ItemAttachment scopeAttachment = WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.SCOPE, weaponInstance);
         if(scopeAttachment != null) {
            crosshair = scopeAttachment.getCrosshair();
         }

         if(crosshair == null) {
            crosshair = this.builder.crosshairZoomed;
         }

         return crosshair;
      } else {
         return weaponInstance.getPlayer().isSprinting()?this.builder.crosshairRunning:this.builder.crosshair;
      }
   }

   public static boolean isActiveAttachment(PlayerWeaponInstance weaponInstance, ItemAttachment attachment) {
      return weaponInstance != null?WeaponAttachmentAspect.isActiveAttachment(attachment, weaponInstance):false;
   }

   public int getMaxItemUseDuration(ItemStack itemStack) {
      return 0;
   }

   int getCurrentAmmo(EntityPlayer player) {
      PlayerWeaponInstance state = this.modContext.getMainHeldWeapon();
      return state.getAmmo();
   }

   int getAmmoCapacity() {
      return this.builder.ammoCapacity;
   }

   int getMaxBulletsPerReload() {
      return this.builder.maxBulletsPerReload;
   }

   ModelBase getAmmoModel() {
      return this.builder.ammoModel;
   }

   String getAmmoModelTextureName() {
      return this.builder.ammoModelTextureName;
   }

   ModelBase getShellCasingModel() {
      return this.builder.shellCasingModel;
   }

   String getShellCasingTextureName() {
      return this.builder.shellCasingModelTextureName;
   }

   void onSpawnEntityBlockImpact(World world, EntityPlayer player, WeaponSpawnEntity entity, CompatibleRayTraceResult position) {
      if(this.builder.blockImpactHandler != null) {
         this.builder.blockImpactHandler.onImpact(world, player, entity, position);
      }

   }

   public List getActiveAttachments(EntityPlayer player, ItemStack itemStack) {
      return this.modContext.getAttachmentAspect().getActiveAttachments(player, itemStack);
   }

   long getUnloadTimeoutTicks() {
      return this.builder.unloadingTimeout;
   }

   boolean ejectSpentRoundRequired() {
      return this.builder.ejectSpentRoundRequired;
   }

   List getCompatibleMagazines() {
      return (List)this.builder.compatibleAttachments.keySet().stream().filter((a) -> {
         return a instanceof ItemMagazine;
      }).map((a) -> {
         return (ItemMagazine)a;
      }).collect(Collectors.toList());
   }

   public WeaponRenderer getRenderer() {
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

   public void reloadMainHeldItemForPlayer(EntityPlayer player) {
      this.modContext.getWeaponReloadAspect().reloadMainHeldItem(player);
   }

   public void update(EntityPlayer player) {
      this.modContext.getWeaponReloadAspect().updateMainHeldItem(player);
      this.modContext.getWeaponFireAspect().onUpdate(player);
      this.modContext.getAttachmentAspect().updateMainHeldItem(player);
   }

   public void tryFire(EntityPlayer player) {
      this.modContext.getWeaponFireAspect().onFireButtonClick(player);
   }

   public void tryStopFire(EntityPlayer player) {
      this.modContext.getWeaponFireAspect().onFireButtonRelease(player);
   }

   public PlayerWeaponInstance createItemInstance(EntityPlayer player, ItemStack itemStack, int slot) {
      PlayerWeaponInstance instance = new PlayerWeaponInstance(slot, player, itemStack);
      instance.setState(WeaponState.READY);
      instance.setRecoil(this.builder.recoil);
      instance.setMaxShots(((Integer)this.builder.maxShots.get(0)).intValue());
      Iterator var5 = ((Weapon)itemStack.getItem()).getCompatibleAttachments().values().iterator();

      while(var5.hasNext()) {
         CompatibleAttachment compatibleAttachment = (CompatibleAttachment)var5.next();
         ItemAttachment attachment = compatibleAttachment.getAttachment();
         if(compatibleAttachment.isDefault() && attachment.getApply2() != null) {
            attachment.apply2.apply(attachment, instance);
         }
      }

      return instance;
   }

   public void toggleClientAttachmentSelectionMode(EntityPlayer player) {
      this.modContext.getAttachmentAspect().toggleClientAttachmentSelectionMode(player);
   }

   public boolean onDroppedByPlayer(ItemStack itemStack, EntityPlayer player) {
      PlayerWeaponInstance instance = (PlayerWeaponInstance)Tags.getInstance(itemStack);
      return instance == null || instance.getState() == WeaponState.READY;
   }

   void changeFireMode(PlayerWeaponInstance instance) {
      Iterator it = this.builder.maxShots.iterator();

      while(it.hasNext() && instance.getMaxShots() != ((Integer)it.next()).intValue()) {
         ;
      }

      int result;
      if(it.hasNext()) {
         result = ((Integer)it.next()).intValue();
      } else {
         result = ((Integer)this.builder.maxShots.get(0)).intValue();
      }

      instance.setMaxShots(result);
      String message;
      if(result == 1) {
         message = CompatibilityProvider.compatibility.getLocalizedString("gui.firearmMode.semi", new Object[0]);
      } else if(result == Integer.MAX_VALUE) {
         message = CompatibilityProvider.compatibility.getLocalizedString("gui.firearmMode.auto", new Object[0]);
      } else {
         message = CompatibilityProvider.compatibility.getLocalizedString("gui.firearmMode.burst", new Object[0]);
      }

      logger.debug("Changed fire mode of {} to {}", new Object[]{instance, Integer.valueOf(result)});
      this.modContext.getStatusMessageCenter().addMessage(CompatibilityProvider.compatibility.getLocalizedString("gui.firearmMode", new Object[]{message}), 1000L);
      CompatibilityProvider.compatibility.playSound(instance.getPlayer(), this.modContext.getChangeFireModeSound(), 1.0F, 1.0F);
   }

   public long getTotalReloadingDuration() {
      return this.builder.renderer.getTotalReloadingDuration();
   }

   public long getTotalUnloadingDuration() {
      return this.builder.renderer.getTotalUnloadingDuration();
   }

   public boolean hasRecoilPositioning() {
      return this.builder.renderer.hasRecoilPositioning();
   }

   void incrementZoom(PlayerWeaponInstance instance) {
      ItemAttachment scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
      if(scopeItem instanceof ItemScope && ((ItemScope)scopeItem).isOptical()) {
         float minZoom = ((ItemScope)scopeItem).getMinZoom();
         float maxZoom = ((ItemScope)scopeItem).getMaxZoom();
         float increment = (minZoom - maxZoom) / 20.0F;
         float zoom = instance.getZoom();
         if(zoom > maxZoom) {
            zoom = Math.max(zoom - increment, maxZoom);
         }

         instance.setZoom(zoom);
         float ratio = (minZoom - zoom) / (minZoom - maxZoom);
         this.modContext.getStatusMessageCenter().addMessage(CompatibilityProvider.compatibility.getLocalizedString("gui.currentZoom", new Object[]{Integer.valueOf(Math.round(ratio * 100.0F))}), 800L);
         CompatibilityProvider.compatibility.playSound(instance.getPlayer(), this.modContext.getZoomSound(), 1.0F, 1.0F);
         logger.debug("Changed optical zoom to {}", new Object[]{Float.valueOf(instance.getZoom())});
      } else {
         logger.debug("Cannot change non-optical zoom");
      }

   }

   void decrementZoom(PlayerWeaponInstance instance) {
      ItemAttachment scopeItem = instance.getAttachmentItemWithCategory(AttachmentCategory.SCOPE);
      if(scopeItem instanceof ItemScope && ((ItemScope)scopeItem).isOptical()) {
         float minZoom = ((ItemScope)scopeItem).getMinZoom();
         float maxZoom = ((ItemScope)scopeItem).getMaxZoom();
         float increment = (minZoom - maxZoom) / 20.0F;
         float zoom = instance.getZoom();
         if(zoom < minZoom) {
            zoom = Math.min(zoom + increment, minZoom);
         }

         instance.setZoom(zoom);
         float ratio = (minZoom - zoom) / (minZoom - maxZoom);
         this.modContext.getStatusMessageCenter().addMessage(CompatibilityProvider.compatibility.getLocalizedString("gui.currentZoom", new Object[]{Integer.valueOf(Math.round(ratio * 100.0F))}), 800L);
         CompatibilityProvider.compatibility.playSound(instance.getPlayer(), this.modContext.getZoomSound(), 1.0F, 1.0F);
         logger.debug("Changed optical zoom to {}", new Object[]{Float.valueOf(zoom)});
      } else {
         logger.debug("Cannot change non-optical zoom");
      }

   }

   public ItemAttachment.ApplyHandler2 getEquivalentHandler(AttachmentCategory attachmentCategory) {
      ItemAttachment.ApplyHandler2 handler = (a, i) -> {
      };
      switch(null.$SwitchMap$com$vicmatskiv$weaponlib$AttachmentCategory[attachmentCategory.ordinal()]) {
      case 1:
         handler = (a, i) -> {
         };
         break;
      case 2:
         handler = (a, i) -> {
            i.setRecoil(this.builder.recoil);
         };
      }

      return handler;
   }

   public String getTextureName() {
      return (String)this.builder.textureNames.get(0);
   }

   public float getRecoil() {
      return this.builder.recoil;
   }

   public ModContext getModContext() {
      return this.modContext;
   }

   public float getShellCasingVerticalOffset() {
      return this.builder.shellCasingVerticalOffset;
   }

   public float getShellCasingForwardOffset() {
      return this.builder.shellCasingForwardOffset;
   }

   public float getShellCasingSideOffset() {
      return this.builder.shellCasingSideOffset;
   }

   public float getShellCasingSideOffsetAimed() {
      return this.builder.shellCasingSideOffsetAimed;
   }

   public boolean isShellCasingEjectEnabled() {
      return this.builder.shellCasingEjectEnabled;
   }

   public Weapon.ShellCasingEjectDirection getShellCasingEjectDirection() {
      return this.builder.shellCasingEjectDirection;
   }

   public float getSilencedShootSoundVolume() {
      return this.builder.silencedShootSoundVolume;
   }

   public float getShootSoundVolume() {
      return this.builder.shootSoundVolume;
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
      private static final float DEFAULT_SPAWN_ENTITY_SPEED = 10.0F;
      private static final float DEFAULT_INACCURACY = 1.0F;
      private static final String DEFAULT_SHELL_CASING_TEXTURE_NAME = "weaponlib:/com/vicmatskiv/weaponlib/resources/shell.png";
      private static final float DEFAULT_SHELL_CASING_VELOCITY = 0.1F;
      private static final float DEFAULT_SHELL_CASING_GRAVITY_VELOCITY = 0.05F;
      private static final float DEFAULT_SHELL_CASING_INACCURACY = 20.0F;
      String name;
      List textureNames = new ArrayList();
      int ammoCapacity = 0;
      float recoil = 1.0F;
      private String shootSound;
      private String silencedShootSound;
      private String reloadSound;
      private String unloadSound;
      private String ejectSpentRoundSound;
      private String exceededMaxShotsSound;
      ItemAmmo ammo;
      float fireRate = 0.5F;
      private CreativeTabs creativeTab;
      private WeaponRenderer renderer;
      List maxShots = new ArrayList();
      String crosshair;
      String crosshairRunning;
      String crosshairZoomed;
      BiFunction spawnEntityWith;
      BiFunction spawnShellWith;
      private float spawnEntityDamage;
      private float spawnEntityExplosionRadius;
      private float spawnEntityGravityVelocity;
      long reloadingTimeout = 10L;
      private String modId;
      boolean crosshairFullScreen = false;
      boolean crosshairZoomedFullScreen = false;
      Map compatibleAttachments = new HashMap();
      ModelBase ammoModel;
      String ammoModelTextureName;
      ModelBase shellCasingModel;
      String shellCasingModelTextureName;
      private float spawnEntitySpeed = 10.0F;
      private Class spawnEntityClass;
      ImpactHandler blockImpactHandler;
      long pumpTimeoutMilliseconds;
      private float inaccuracy = 1.0F;
      int pellets = 1;
      float flashIntensity = 0.7F;
      Supplier flashScale = () -> {
         return Float.valueOf(1.0F);
      };
      Supplier flashOffsetX = () -> {
         return Float.valueOf(0.0F);
      };
      Supplier flashOffsetY = () -> {
         return Float.valueOf(0.0F);
      };
      Supplier smokeOffsetX = () -> {
         return Float.valueOf(0.0F);
      };
      Supplier smokeOffsetY = () -> {
         return Float.valueOf(0.0F);
      };
      long unloadingTimeout = 10L;
      private boolean ejectSpentRoundRequired;
      public int maxBulletsPerReload;
      private Function informationProvider;
      private CraftingComplexity craftingComplexity;
      private Object[] craftingMaterials;
      private float shellCasingForwardOffset = 0.1F;
      private float shellCasingVerticalOffset = 0.0F;
      private float shellCasingSideOffset = 0.15F;
      private float shellCasingSideOffsetAimed = 0.05F;
      public boolean shellCasingEjectEnabled = true;
      private Weapon.ShellCasingEjectDirection shellCasingEjectDirection;
      private float silencedShootSoundVolume;
      private float shootSoundVolume;
      private Object[] craftingRecipe;

      public Builder() {
         this.shellCasingEjectDirection = Weapon.ShellCasingEjectDirection.RIGHT;
         this.silencedShootSoundVolume = 0.7F;
         this.shootSoundVolume = 10.0F;
      }

      public Weapon.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public Weapon.Builder withEjectRoundRequired() {
         this.ejectSpentRoundRequired = true;
         return this;
      }

      public Weapon.Builder withInformationProvider(Function informationProvider) {
         this.informationProvider = informationProvider;
         return this;
      }

      public Weapon.Builder withReloadingTime(long reloadingTime) {
         this.reloadingTimeout = reloadingTime;
         return this;
      }

      public Weapon.Builder withUnloadingTime(long unloadingTime) {
         this.unloadingTimeout = unloadingTime;
         return this;
      }

      public Weapon.Builder withName(String name) {
         this.name = name;
         return this;
      }

      public Weapon.Builder withAmmoCapacity(int ammoCapacity) {
         this.ammoCapacity = ammoCapacity;
         return this;
      }

      public Weapon.Builder withMaxBulletsPerReload(int maxBulletsPerReload) {
         this.maxBulletsPerReload = maxBulletsPerReload;
         return this;
      }

      public Weapon.Builder withRecoil(float recoil) {
         this.recoil = recoil;
         return this;
      }

      /** @deprecated */
      @Deprecated
      public Weapon.Builder withZoom(float zoom) {
         return this;
      }

      public Weapon.Builder withAmmo(ItemAmmo ammo) {
         this.ammo = ammo;
         return this;
      }

      public Weapon.Builder withMaxShots(int... maxShots) {
         int[] var2 = maxShots;
         int var3 = maxShots.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            int m = var2[var4];
            this.maxShots.add(Integer.valueOf(m));
         }

         return this;
      }

      public Weapon.Builder withFireRate(float fireRate) {
         if(fireRate < 1.0F && fireRate > 0.0F) {
            this.fireRate = fireRate;
            return this;
         } else {
            throw new IllegalArgumentException("Invalid fire rate " + fireRate);
         }
      }

      public Weapon.Builder withTextureNames(String... textureNames) {
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

      public Weapon.Builder withCrosshair(String crosshair) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.crosshair = this.modId + ":" + "textures/crosshairs/" + crosshair.toLowerCase() + ".png";
            return this;
         }
      }

      public Weapon.Builder withCrosshair(String crosshair, boolean fullScreen) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.crosshair = this.modId + ":" + "textures/crosshairs/" + crosshair.toLowerCase() + ".png";
            this.crosshairFullScreen = fullScreen;
            return this;
         }
      }

      public Weapon.Builder withCrosshairRunning(String crosshairRunning) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.crosshairRunning = this.modId + ":" + "textures/crosshairs/" + crosshairRunning.toLowerCase() + ".png";
            return this;
         }
      }

      public Weapon.Builder withCrosshairZoomed(String crosshairZoomed) {
         return this.withCrosshairZoomed(crosshairZoomed, true);
      }

      public Weapon.Builder withCrosshairZoomed(String crosshairZoomed, boolean fullScreen) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.crosshairZoomed = this.modId + ":" + "textures/crosshairs/" + crosshairZoomed.toLowerCase() + ".png";
            this.crosshairZoomedFullScreen = fullScreen;
            return this;
         }
      }

      public Weapon.Builder withShootSound(String shootSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.shootSound = shootSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withEjectSpentRoundSound(String ejectSpentRoundSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.ejectSpentRoundSound = ejectSpentRoundSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withSilencedShootSound(String silencedShootSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.silencedShootSound = silencedShootSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withReloadSound(String reloadSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.reloadSound = reloadSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withUnloadSound(String unloadSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.unloadSound = unloadSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withShootSoundVolume(float volume) {
         this.shootSoundVolume = volume;
         return this;
      }

      public Weapon.Builder withSilenceShootSoundVolume(float volume) {
         this.silencedShootSoundVolume = volume;
         return this;
      }

      public Weapon.Builder withExceededMaxShotsSound(String shootSound) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            this.exceededMaxShotsSound = shootSound.toLowerCase();
            return this;
         }
      }

      public Weapon.Builder withCreativeTab(CreativeTabs creativeTab) {
         this.creativeTab = creativeTab;
         return this;
      }

      public Weapon.Builder withSpawnEntityDamage(float spawnEntityDamage) {
         this.spawnEntityDamage = spawnEntityDamage;
         return this;
      }

      public Weapon.Builder withSpawnEntitySpeed(float spawnEntitySpeed) {
         this.spawnEntitySpeed = spawnEntitySpeed;
         return this;
      }

      public Weapon.Builder withSpawnEntityExplosionRadius(float spawnEntityExplosionRadius) {
         this.spawnEntityExplosionRadius = spawnEntityExplosionRadius;
         return this;
      }

      public Weapon.Builder withSpawnEntityGravityVelocity(float spawnEntityGravityVelocity) {
         this.spawnEntityGravityVelocity = spawnEntityGravityVelocity;
         return this;
      }

      public Weapon.Builder withInaccuracy(float inaccuracy) {
         this.inaccuracy = inaccuracy;
         return this;
      }

      public Weapon.Builder withRenderer(WeaponRenderer renderer) {
         this.renderer = renderer;
         return this;
      }

      public Weapon.Builder withCompatibleBullet(ItemBullet bullet, Consumer positioner) {
         this.compatibleAttachments.put(bullet, new CompatibleAttachment(bullet, positioner));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, ItemAttachment.ApplyHandler2 applyHandler, ItemAttachment.ApplyHandler2 removeHandler) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, applyHandler, removeHandler));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer positioning, Consumer modelPositioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, false));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, BiConsumer positioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, (Consumer)null, false));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, Consumer positioner) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, BiConsumer positioning, Consumer modelPositioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, boolean isPermanent, BiConsumer positioning, Consumer modelPositioning) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioning, modelPositioning, isDefault, isPermanent));
         return this;
      }

      public Weapon.Builder withCompatibleAttachment(ItemAttachment attachment, boolean isDefault, Consumer positioner) {
         this.compatibleAttachments.put(attachment, new CompatibleAttachment(attachment, positioner, isDefault));
         return this;
      }

      public Weapon.Builder withSpawnEntityModel(ModelBase ammoModel) {
         this.ammoModel = ammoModel;
         return this;
      }

      public Weapon.Builder withSpawnEntityModelTexture(String ammoModelTextureName) {
         this.ammoModelTextureName = this.modId + ":" + "textures/models/" + ammoModelTextureName.toLowerCase() + ".png";
         return this;
      }

      public Weapon.Builder withSpawnEntityBlockImpactHandler(ImpactHandler impactHandler) {
         this.blockImpactHandler = impactHandler;
         return this;
      }

      public Weapon.Builder withShellCasingEjectEnabled(boolean shellCasingEjectEnabled) {
         this.shellCasingEjectEnabled = shellCasingEjectEnabled;
         return this;
      }

      public Weapon.Builder withShellCasingModel(ModelBase shellCasingModel) {
         this.shellCasingModel = shellCasingModel;
         return this;
      }

      public Weapon.Builder withShellCasingModelTexture(String shellModelTextureName) {
         this.shellCasingModelTextureName = this.modId + ":" + "textures/models/" + shellModelTextureName.toLowerCase() + ".png";
         return this;
      }

      public Weapon.Builder withShellCasingForwardOffset(float shellCasingForwardOffset) {
         this.shellCasingForwardOffset = shellCasingForwardOffset;
         return this;
      }

      public Weapon.Builder withShellCasingVerticalOffset(float shellCasingVerticalOffset) {
         this.shellCasingVerticalOffset = shellCasingVerticalOffset;
         return this;
      }

      public Weapon.Builder withShellCasingSideOffset(float shellCasingSideOffset) {
         this.shellCasingSideOffset = shellCasingSideOffset;
         return this;
      }

      public Weapon.Builder withShellCasingSideOffsetAimed(float shellCasingSideOffsetAimed) {
         this.shellCasingSideOffsetAimed = shellCasingSideOffsetAimed;
         return this;
      }

      public Weapon.Builder withShellCasingEjectDirection(Weapon.ShellCasingEjectDirection shellCasingEjectDirection) {
         this.shellCasingEjectDirection = shellCasingEjectDirection;
         return this;
      }

      public Weapon.Builder withPumpTimeout(long pumpTimeoutMilliseconds) {
         this.pumpTimeoutMilliseconds = pumpTimeoutMilliseconds;
         return this;
      }

      public Weapon.Builder withPellets(int pellets) {
         if(pellets < 1) {
            throw new IllegalArgumentException("Pellet count must be >= 1");
         } else {
            this.pellets = pellets;
            return this;
         }
      }

      public Weapon.Builder withFlashIntensity(float flashIntensity) {
         if(flashIntensity >= 0.0F && flashIntensity <= 1.0F) {
            this.flashIntensity = flashIntensity;
            return this;
         } else {
            throw new IllegalArgumentException("Invalid flash intencity");
         }
      }

      public Weapon.Builder withFlashScale(Supplier flashScale) {
         this.flashScale = flashScale;
         return this;
      }

      public Weapon.Builder withFlashOffsetX(Supplier flashOffsetX) {
         this.flashOffsetX = flashOffsetX;
         return this;
      }

      public Weapon.Builder withFlashOffsetY(Supplier flashOffsetY) {
         this.flashOffsetY = flashOffsetY;
         return this;
      }

      public Weapon.Builder withSmokeOffsetX(Supplier smokeOffsetX) {
         this.smokeOffsetX = smokeOffsetX;
         return this;
      }

      public Weapon.Builder withSmokeOffsetY(Supplier smokeOffsetY) {
         this.smokeOffsetY = smokeOffsetY;
         return this;
      }

      public Weapon.Builder withCrafting(CraftingComplexity craftingComplexity, Object... craftingMaterials) {
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

      public Weapon.Builder withCraftingRecipe(Object... craftingRecipe) {
         this.craftingRecipe = craftingRecipe;
         return this;
      }

      public Weapon build(ModContext modContext) {
         if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else if(this.name == null) {
            throw new IllegalStateException("Weapon name not provided");
         } else {
            if(this.shootSound == null) {
               this.shootSound = this.name;
            }

            if(this.silencedShootSound == null) {
               this.silencedShootSound = this.shootSound;
            }

            if(this.reloadSound == null) {
               this.reloadSound = "reload";
            }

            if(this.unloadSound == null) {
               this.unloadSound = "unload";
            }

            if(this.spawnEntityClass == null) {
               this.spawnEntityClass = WeaponSpawnEntity.class;
            }

            if(this.spawnEntityWith == null) {
               this.spawnEntityWith = (weapon, player) -> {
                  WeaponSpawnEntity bullet = new WeaponSpawnEntity(weapon, CompatibilityProvider.compatibility.world(player), player, this.spawnEntitySpeed, this.spawnEntityGravityVelocity, this.inaccuracy, this.spawnEntityDamage, this.spawnEntityExplosionRadius, new Material[0]);
                  bullet.setPositionAndDirection();
                  return bullet;
               };
            }

            if(this.shellCasingModel == null) {
               this.shellCasingModel = new Shell();
            }

            if(this.shellCasingModelTextureName == null) {
               this.shellCasingModelTextureName = "weaponlib:/com/vicmatskiv/weaponlib/resources/shell.png";
            }

            if(this.spawnShellWith == null) {
               this.spawnShellWith = (weaponInstance, player) -> {
                  EntityShellCasing shell = new EntityShellCasing(weaponInstance, CompatibilityProvider.compatibility.world(player), player, 0.1F, 0.05F, 20.0F);
                  shell.setPositionAndDirection();
                  return shell;
               };
            }

            if(this.crosshairRunning == null) {
               this.crosshairRunning = this.crosshair;
            }

            if(this.crosshairZoomed == null) {
               this.crosshairZoomed = this.crosshair;
            }

            if(this.blockImpactHandler == null) {
               this.blockImpactHandler = (world, player, entity, position) -> {
                  CompatibleBlockState blockState = CompatibilityProvider.compatibility.getBlockAtPosition(world, position);
                  Boolean canDestroyGlassBlocks = modContext.getConfigurationManager().getProjectiles().isDestroyGlassBlocks();
                  if(canDestroyGlassBlocks != null && canDestroyGlassBlocks.booleanValue() && CompatibilityProvider.compatibility.isGlassBlock(blockState)) {
                     CompatibilityProvider.compatibility.destroyBlock(world, position);
                  } else {
                     CompatibleTargetPoint point = new CompatibleTargetPoint(entity.dimension, (double)position.getBlockPosX(), (double)position.getBlockPosY(), (double)position.getBlockPosZ(), 100.0D);
                     modContext.getChannel().sendToAllAround(new BlockHitMessage(position.getBlockPosX(), position.getBlockPosY(), position.getBlockPosZ(), position.getSideHit()), point);
                  }

               };
            }

            if(this.maxBulletsPerReload == 0) {
               this.maxBulletsPerReload = this.ammoCapacity;
            }

            if(this.maxShots.isEmpty()) {
               this.maxShots.add(Integer.valueOf(Integer.MAX_VALUE));
            }

            Weapon weapon = new Weapon(this, modContext);
            weapon.shootSound = modContext.registerSound(this.shootSound);
            weapon.reloadSound = modContext.registerSound(this.reloadSound);
            weapon.unloadSound = modContext.registerSound(this.unloadSound);
            weapon.silencedShootSound = modContext.registerSound(this.silencedShootSound);
            if(this.ejectSpentRoundSound != null) {
               weapon.ejectSpentRoundSound = modContext.registerSound(this.ejectSpentRoundSound);
            }

            weapon.setCreativeTab(this.creativeTab);
            weapon.setUnlocalizedName(this.name);
            if(this.ammo != null) {
               this.ammo.addCompatibleWeapon(weapon);
            }

            Iterator gunConfig = this.compatibleAttachments.keySet().iterator();

            while(gunConfig.hasNext()) {
               ItemAttachment optionsMetadata = (ItemAttachment)gunConfig.next();
               optionsMetadata.addCompatibleWeapon(weapon);
            }

            Gun gunConfig1 = modContext.getConfigurationManager().getGun(this.name);
            if(gunConfig1 == null || gunConfig1.isEnabled()) {
               modContext.registerWeapon(this.name, weapon, this.renderer);
               List shape;
               if(this.craftingRecipe != null && this.craftingRecipe.length >= 2) {
                  ItemStack optionsMetadata2 = new ItemStack(weapon);
                  shape = modContext.getRecipeManager().registerShapedRecipe((Item)weapon, this.craftingRecipe);
                  boolean hasOres = Arrays.stream(this.craftingRecipe).anyMatch((r) -> {
                     return r instanceof String;
                  });
                  if(hasOres) {
                     CompatibilityProvider.compatibility.addShapedOreRecipe(optionsMetadata2, shape.toArray());
                  } else {
                     CompatibilityProvider.compatibility.addShapedRecipe(optionsMetadata2, shape.toArray());
                  }
               } else if(this.craftingComplexity != null) {
                  OptionsMetadata optionsMetadata1 = (new OptionsMetadata.OptionMetadataBuilder()).withSlotCount(9).build(this.craftingComplexity, Arrays.copyOf(this.craftingMaterials, this.craftingMaterials.length));
                  shape = modContext.getRecipeManager().createShapedRecipe(weapon, weapon.getName(), optionsMetadata1);
                  if(optionsMetadata1.hasOres()) {
                     CompatibilityProvider.compatibility.addShapedOreRecipe(new ItemStack(weapon), shape.toArray());
                  } else {
                     CompatibilityProvider.compatibility.addShapedRecipe(new ItemStack(weapon), shape.toArray());
                  }
               } else {
                  System.err.println("!!!No recipe defined for weapon " + this.name);
               }
            }

            return weapon;
         }
      }
   }

   public static enum ShellCasingEjectDirection {
      LEFT,
      RIGHT;
   }
}
