package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.BlockHitMessage;
import com.vicmatskiv.weaponlib.BlockHitMessageHandler;
import com.vicmatskiv.weaponlib.EffectManager;
import com.vicmatskiv.weaponlib.EntityShellCasing;
import com.vicmatskiv.weaponlib.ExplosionMessage;
import com.vicmatskiv.weaponlib.ExplosionMessageHandler;
import com.vicmatskiv.weaponlib.MagazineReloadAspect;
import com.vicmatskiv.weaponlib.MagazineState;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerItemInstanceRegistry;
import com.vicmatskiv.weaponlib.PlayerMagazineInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.ServerEventHandler;
import com.vicmatskiv.weaponlib.StatusMessageCenter;
import com.vicmatskiv.weaponlib.SyncManager;
import com.vicmatskiv.weaponlib.TryFireMessage;
import com.vicmatskiv.weaponlib.TryFireMessageHandler;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponFireAspect;
import com.vicmatskiv.weaponlib.WeaponKeyInputHandler;
import com.vicmatskiv.weaponlib.WeaponReloadAspect;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatiblePlayerEntityTrackerProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSide;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.RecipeManager;
import com.vicmatskiv.weaponlib.electronics.EntityWirelessCamera;
import com.vicmatskiv.weaponlib.electronics.PlayerTabletInstance;
import com.vicmatskiv.weaponlib.electronics.TabletState;
import com.vicmatskiv.weaponlib.grenade.EntityGrenade;
import com.vicmatskiv.weaponlib.grenade.EntitySmokeGrenade;
import com.vicmatskiv.weaponlib.grenade.GrenadeAttackAspect;
import com.vicmatskiv.weaponlib.grenade.GrenadeMessage;
import com.vicmatskiv.weaponlib.grenade.GrenadeMessageHandler;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeAttachmentAspect;
import com.vicmatskiv.weaponlib.melee.MeleeAttackAspect;
import com.vicmatskiv.weaponlib.melee.MeleeRenderer;
import com.vicmatskiv.weaponlib.melee.MeleeState;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.melee.TryAttackMessage;
import com.vicmatskiv.weaponlib.melee.TryAttackMessageHandler;
import com.vicmatskiv.weaponlib.network.NetworkPermitManager;
import com.vicmatskiv.weaponlib.network.PermitMessage;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessage;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessageHandler;
import com.vicmatskiv.weaponlib.state.Permit;
import com.vicmatskiv.weaponlib.state.StateManager;
import com.vicmatskiv.weaponlib.tracking.SyncPlayerEntityTrackerMessage;
import com.vicmatskiv.weaponlib.tracking.SyncPlayerEntityTrackerMessageMessageHandler;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CommonModContext implements ModContext {
   protected String modId;
   protected CompatibleChannel channel;
   protected WeaponReloadAspect weaponReloadAspect;
   protected WeaponAttachmentAspect weaponAttachmentAspect;
   protected WeaponFireAspect weaponFireAspect;
   protected MeleeAttachmentAspect meleeAttachmentAspect;
   protected MeleeAttackAspect meleeAttackAspect;
   protected SyncManager syncManager;
   protected MagazineReloadAspect magazineReloadAspect;
   protected NetworkPermitManager permitManager;
   protected PlayerItemInstanceRegistry playerItemInstanceRegistry;
   private Map registeredSounds = new HashMap();
   private RecipeManager recipeManager;
   private CompatibleSound changeZoomSound;
   private CompatibleSound changeFireModeSound;
   private CompatibleSound noAmmoSound;
   private int modEntityID = 256;
   private GrenadeAttackAspect grenadeAttackAspect;
   private CompatibleSound explosionSound;
   protected ConfigurationManager configurationManager;

   public void init(Object mod, String modId, ConfigurationManager configurationManager, CompatibleChannel channel) {
      this.channel = channel;
      this.modId = modId;
      this.configurationManager = configurationManager;
      this.weaponReloadAspect = new WeaponReloadAspect(this);
      this.magazineReloadAspect = new MagazineReloadAspect(this);
      this.weaponFireAspect = new WeaponFireAspect(this);
      this.weaponAttachmentAspect = new WeaponAttachmentAspect(this);
      this.meleeAttackAspect = new MeleeAttackAspect(this);
      this.meleeAttachmentAspect = new MeleeAttachmentAspect(this);
      this.grenadeAttackAspect = new GrenadeAttackAspect(this);
      StateManager grenadeStateManager = new StateManager((s1, s2) -> {
         return s1 == s2;
      });
      this.grenadeAttackAspect.setStateManager(grenadeStateManager);
      this.permitManager = new NetworkPermitManager(this);
      this.syncManager = new SyncManager(this.permitManager);
      this.playerItemInstanceRegistry = new PlayerItemInstanceRegistry(this.syncManager);
      StateManager weaponStateManager = new StateManager((s1, s2) -> {
         return s1 == s2;
      });
      this.weaponReloadAspect.setPermitManager(this.permitManager);
      this.weaponReloadAspect.setStateManager(weaponStateManager);
      this.weaponFireAspect.setPermitManager(this.permitManager);
      this.weaponFireAspect.setStateManager(weaponStateManager);
      this.weaponAttachmentAspect.setPermitManager(this.permitManager);
      this.weaponAttachmentAspect.setStateManager(weaponStateManager);
      StateManager meleeStateManager = new StateManager((s1, s2) -> {
         return s1 == s2;
      });
      this.meleeAttackAspect.setStateManager(meleeStateManager);
      this.meleeAttachmentAspect.setPermitManager(this.permitManager);
      this.meleeAttachmentAspect.setStateManager(meleeStateManager);
      StateManager magazineStateManager = new StateManager((s1, s2) -> {
         return s1 == s2;
      });
      this.magazineReloadAspect.setPermitManager(this.permitManager);
      this.magazineReloadAspect.setStateManager(magazineStateManager);
      this.recipeManager = new RecipeManager();
      channel.registerMessage(new TryFireMessageHandler(this.weaponFireAspect), TryFireMessage.class, 11, CompatibleSide.SERVER);
      channel.registerMessage(this.permitManager, PermitMessage.class, 14, CompatibleSide.SERVER);
      channel.registerMessage(this.permitManager, PermitMessage.class, 15, CompatibleSide.CLIENT);
      channel.registerMessage(new TryAttackMessageHandler(this.meleeAttackAspect), TryAttackMessage.class, 16, CompatibleSide.SERVER);
      channel.registerMessage(new SyncPlayerEntityTrackerMessageMessageHandler(this), SyncPlayerEntityTrackerMessage.class, 17, CompatibleSide.CLIENT);
      channel.registerMessage(new SpawnParticleMessageHandler(this), SpawnParticleMessage.class, 18, CompatibleSide.CLIENT);
      channel.registerMessage(new BlockHitMessageHandler(this), BlockHitMessage.class, 19, CompatibleSide.CLIENT);
      channel.registerMessage(new GrenadeMessageHandler(this.grenadeAttackAspect), GrenadeMessage.class, 20, CompatibleSide.SERVER);
      channel.registerMessage(new ExplosionMessageHandler(this), ExplosionMessage.class, 21, CompatibleSide.CLIENT);
      ServerEventHandler serverHandler = new ServerEventHandler(this, modId);
      CompatibilityProvider.compatibility.registerWithFmlEventBus(serverHandler);
      CompatibilityProvider.compatibility.registerWithEventBus(serverHandler);
      CompatibilityProvider.compatibility.registerWithFmlEventBus(new WeaponKeyInputHandler(this, (ctx) -> {
         return this.getPlayer(ctx);
      }, this.weaponAttachmentAspect, channel));
      CompatiblePlayerEntityTrackerProvider.register(this);
      CompatibilityProvider.compatibility.registerModEntity(WeaponSpawnEntity.class, "Ammo" + this.modEntityID, this.modEntityID++, mod, modId, 64, 3, true);
      CompatibilityProvider.compatibility.registerModEntity(EntityWirelessCamera.class, "wcam" + this.modEntityID, this.modEntityID++, mod, modId, 200, 3, true);
      CompatibilityProvider.compatibility.registerModEntity(EntityShellCasing.class, "ShellCasing" + this.modEntityID, this.modEntityID++, mod, modId, 64, 500, true);
      CompatibilityProvider.compatibility.registerModEntity(EntityGrenade.class, "Grenade" + this.modEntityID, this.modEntityID++, mod, modId, 64, 10000, false);
      CompatibilityProvider.compatibility.registerModEntity(EntitySmokeGrenade.class, "SmokeGrenade" + this.modEntityID, this.modEntityID++, mod, modId, 64, 10000, false);
   }

   public void registerServerSideOnly() {
   }

   public CompatibleSound registerSound(String sound) {
      ResourceLocation soundResourceLocation = new ResourceLocation(this.modId, sound);
      return this.registerSound(soundResourceLocation);
   }

   protected CompatibleSound registerSound(ResourceLocation soundResourceLocation) {
      CompatibleSound result = (CompatibleSound)this.registeredSounds.get(soundResourceLocation);
      if(result == null) {
         result = new CompatibleSound(soundResourceLocation);
         this.registeredSounds.put(soundResourceLocation, result);
         CompatibilityProvider.compatibility.registerSound(result);
      }

      return result;
   }

   public void registerWeapon(String name, Weapon weapon, WeaponRenderer renderer) {
      CompatibilityProvider.compatibility.registerItem(weapon, name);
   }

   private EntityPlayer getServerPlayer(CompatibleMessageContext ctx) {
      return ctx != null?ctx.getPlayer():null;
   }

   protected EntityPlayer getPlayer(CompatibleMessageContext ctx) {
      return this.getServerPlayer(ctx);
   }

   public CompatibleChannel getChannel() {
      return this.channel;
   }

   public void runSyncTick(Runnable runnable) {
      throw new UnsupportedOperationException();
   }

   public void runInMainThread(Runnable runnable) {
      throw new UnsupportedOperationException();
   }

   public void registerRenderableItem(String name, Item item, Object renderer) {
      CompatibilityProvider.compatibility.registerItem(item, name);
   }

   public PlayerItemInstanceRegistry getPlayerItemInstanceRegistry() {
      return this.playerItemInstanceRegistry;
   }

   public WeaponReloadAspect getWeaponReloadAspect() {
      return this.weaponReloadAspect;
   }

   public WeaponFireAspect getWeaponFireAspect() {
      return this.weaponFireAspect;
   }

   public WeaponAttachmentAspect getAttachmentAspect() {
      return this.weaponAttachmentAspect;
   }

   public MagazineReloadAspect getMagazineReloadAspect() {
      return this.magazineReloadAspect;
   }

   public MeleeAttackAspect getMeleeAttackAspect() {
      return this.meleeAttackAspect;
   }

   public MeleeAttachmentAspect getMeleeAttachmentAspect() {
      return this.meleeAttachmentAspect;
   }

   public PlayerWeaponInstance getMainHeldWeapon() {
      throw new IllegalStateException();
   }

   public StatusMessageCenter getStatusMessageCenter() {
      throw new IllegalStateException();
   }

   public RecipeManager getRecipeManager() {
      return this.recipeManager;
   }

   public void setChangeZoomSound(String sound) {
      this.changeZoomSound = this.registerSound(sound.toLowerCase());
   }

   public CompatibleSound getZoomSound() {
      return this.changeZoomSound;
   }

   public CompatibleSound getChangeFireModeSound() {
      return this.changeFireModeSound;
   }

   public void setChangeFireModeSound(String sound) {
      this.changeFireModeSound = this.registerSound(sound.toLowerCase());
   }

   public void setNoAmmoSound(String sound) {
      this.noAmmoSound = this.registerSound(sound.toLowerCase());
   }

   public CompatibleSound getNoAmmoSound() {
      return this.noAmmoSound;
   }

   public void setExplosionSound(String sound) {
      this.explosionSound = this.registerSound(sound.toLowerCase());
   }

   public CompatibleSound getExplosionSound() {
      return this.explosionSound;
   }

   public void registerMeleeWeapon(String name, ItemMelee itemMelee, MeleeRenderer renderer) {
      CompatibilityProvider.compatibility.registerItem(itemMelee, name);
   }

   public void registerGrenadeWeapon(String name, ItemGrenade itemMelee, GrenadeRenderer renderer) {
      CompatibilityProvider.compatibility.registerItem(itemMelee, name);
   }

   public ResourceLocation getNamedResource(String name) {
      return new ResourceLocation(this.modId, name);
   }

   public float getAspectRatio() {
      return 1.0F;
   }

   public AttachmentContainer getGrenadeAttachmentAspect() {
      throw new UnsupportedOperationException("Not implemented");
   }

   public GrenadeAttackAspect getGrenadeAttackAspect() {
      return this.grenadeAttackAspect;
   }

   public String getModId() {
      return this.modId;
   }

   public EffectManager getEffectManager() {
      throw new IllegalStateException();
   }

   public ConfigurationManager getConfigurationManager() {
      return this.configurationManager;
   }

   static {
      TypeRegistry.getInstance().register(MagazineReloadAspect.LoadPermit.class);
      TypeRegistry.getInstance().register(MagazineState.class);
      TypeRegistry.getInstance().register(PlayerItemInstance.class);
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
      TypeRegistry.getInstance().register(PlayerMagazineInstance.class);
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
      TypeRegistry.getInstance().register(Permit.class);
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.EnterAttachmentModePermit.class);
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.ExitAttachmentModePermit.class);
      TypeRegistry.getInstance().register(WeaponAttachmentAspect.ChangeAttachmentPermit.class);
      TypeRegistry.getInstance().register(WeaponReloadAspect.UnloadPermit.class);
      TypeRegistry.getInstance().register(MagazineReloadAspect.LoadPermit.class);
      TypeRegistry.getInstance().register(PlayerWeaponInstance.class);
      TypeRegistry.getInstance().register(WeaponState.class);
      TypeRegistry.getInstance().register(PlayerMeleeInstance.class);
      TypeRegistry.getInstance().register(PlayerGrenadeInstance.class);
      TypeRegistry.getInstance().register(PlayerTabletInstance.class);
      TypeRegistry.getInstance().register(MeleeState.class);
      TypeRegistry.getInstance().register(TabletState.class);
   }
}
