package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ClientEffectManager;
import com.vicmatskiv.weaponlib.ClientEventHandler;
import com.vicmatskiv.weaponlib.ClientWeaponTicker;
import com.vicmatskiv.weaponlib.CommonModContext;
import com.vicmatskiv.weaponlib.CustomGui;
import com.vicmatskiv.weaponlib.EffectManager;
import com.vicmatskiv.weaponlib.EntityShellCasing;
import com.vicmatskiv.weaponlib.KeyBindings;
import com.vicmatskiv.weaponlib.PlayerItemInstanceRegistry;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.SafeGlobals;
import com.vicmatskiv.weaponlib.ShellCasingRenderer;
import com.vicmatskiv.weaponlib.SpawnEntityRenderer;
import com.vicmatskiv.weaponlib.StatusMessageCenter;
import com.vicmatskiv.weaponlib.SyncManager;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponEventHandler;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.WeaponResourcePack;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.command.DebugCommand;
import com.vicmatskiv.weaponlib.command.MainCommand;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMessageContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderingRegistry;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.electronics.EntityWirelessCamera;
import com.vicmatskiv.weaponlib.electronics.WirelessCameraRenderer;
import com.vicmatskiv.weaponlib.grenade.EntityGrenade;
import com.vicmatskiv.weaponlib.grenade.EntityGrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.EntitySmokeGrenade;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeRenderer;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.perspective.PerspectiveManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.ClientCommandHandler;

public class ClientModContext extends CommonModContext {
   private ClientEventHandler clientEventHandler;
   private Lock mainLoopLock = new ReentrantLock();
   private Queue runInClientThreadQueue = new LinkedBlockingQueue();
   private CompatibleRenderingRegistry rendererRegistry;
   private SafeGlobals safeGlobals = new SafeGlobals();
   private StatusMessageCenter statusMessageCenter;
   private PerspectiveManager viewManager;
   private float aspectRatio;
   private Framebuffer inventoryFramebuffer;
   private Map inventoryTextureMap;
   private EffectManager effectManager;

   public void init(Object mod, String modId, ConfigurationManager configurationManager, CompatibleChannel channel) {
      super.init(mod, modId, configurationManager, channel);
      this.aspectRatio = (float)Minecraft.getMinecraft().displayWidth / (float)Minecraft.getMinecraft().displayHeight;
      ClientCommandHandler.instance.registerCommand(new DebugCommand(modId));
      ClientCommandHandler.instance.registerCommand(new MainCommand(modId, this));
      this.statusMessageCenter = new StatusMessageCenter();
      this.rendererRegistry = new CompatibleRenderingRegistry(modId);
      List defaultResourcePacks = (List)CompatibilityProvider.compatibility.getPrivateValue(Minecraft.class, Minecraft.getMinecraft(), new String[]{"defaultResourcePacks", "field_110449_ao"});
      defaultResourcePacks.add(new WeaponResourcePack());
      CompatibilityProvider.compatibility.registerWithEventBus(new CustomGui(Minecraft.getMinecraft(), this, this.weaponAttachmentAspect));
      CompatibilityProvider.compatibility.registerWithEventBus(new WeaponEventHandler(this, this.safeGlobals));
      KeyBindings.init();
      ClientWeaponTicker clientWeaponTicker = new ClientWeaponTicker(this);
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
         clientWeaponTicker.shutdown();
      }));
      clientWeaponTicker.start();
      this.clientEventHandler = new ClientEventHandler(this, this.mainLoopLock, this.safeGlobals, this.runInClientThreadQueue);
      CompatibilityProvider.compatibility.registerWithFmlEventBus(this.clientEventHandler);
      CompatibilityProvider.compatibility.registerRenderingRegistry(this.rendererRegistry);
      this.rendererRegistry.registerEntityRenderingHandler(WeaponSpawnEntity.class, new SpawnEntityRenderer());
      this.rendererRegistry.registerEntityRenderingHandler(EntityWirelessCamera.class, new WirelessCameraRenderer(modId));
      this.rendererRegistry.registerEntityRenderingHandler(EntityShellCasing.class, new ShellCasingRenderer());
      this.rendererRegistry.registerEntityRenderingHandler(EntityGrenade.class, new EntityGrenadeRenderer());
      this.rendererRegistry.registerEntityRenderingHandler(EntitySmokeGrenade.class, new EntityGrenadeRenderer());
      this.viewManager = new PerspectiveManager(this);
      this.inventoryTextureMap = new HashMap();
      this.effectManager = new ClientEffectManager();
   }

   public void registerServerSideOnly() {
   }

   public PerspectiveManager getViewManager() {
      return this.viewManager;
   }

   public SafeGlobals getSafeGlobals() {
      return this.safeGlobals;
   }

   public void registerWeapon(String name, Weapon weapon, WeaponRenderer renderer) {
      super.registerWeapon(name, weapon, renderer);
      this.rendererRegistry.register(weapon, weapon.getName(), weapon.getRenderer());
      renderer.setClientModContext(this);
   }

   public void registerRenderableItem(String name, Item item, Object renderer) {
      super.registerRenderableItem(name, item, renderer);
      this.rendererRegistry.register(item, name, renderer);
   }

   protected EntityPlayer getPlayer(CompatibleMessageContext ctx) {
      return CompatibilityProvider.compatibility.clientPlayer();
   }

   public void runSyncTick(Runnable runnable) {
      this.mainLoopLock.lock();

      try {
         runnable.run();
      } finally {
         this.mainLoopLock.unlock();
      }

   }

   public void runInMainThread(Runnable runnable) {
      this.runInClientThreadQueue.add(runnable);
   }

   public PlayerItemInstanceRegistry getPlayerItemInstanceRegistry() {
      return this.playerItemInstanceRegistry;
   }

   protected SyncManager getSyncManager() {
      return this.syncManager;
   }

   public PlayerWeaponInstance getMainHeldWeapon() {
      return (PlayerWeaponInstance)this.getPlayerItemInstanceRegistry().getMainHandItemInstance(CompatibilityProvider.compatibility.clientPlayer(), PlayerWeaponInstance.class);
   }

   public StatusMessageCenter getStatusMessageCenter() {
      return this.statusMessageCenter;
   }

   public PlayerMeleeInstance getMainHeldMeleeWeapon() {
      return (PlayerMeleeInstance)this.getPlayerItemInstanceRegistry().getMainHandItemInstance(CompatibilityProvider.compatibility.clientPlayer(), PlayerMeleeInstance.class);
   }

   public void registerMeleeWeapon(String name, ItemMelee itemMelee, MeleeRenderer renderer) {
      super.registerMeleeWeapon(name, itemMelee, renderer);
      this.rendererRegistry.register(itemMelee, itemMelee.getName(), itemMelee.getRenderer());
      renderer.setClientModContext(this);
   }

   public void registerGrenadeWeapon(String name, ItemGrenade itemGrenade, GrenadeRenderer renderer) {
      super.registerGrenadeWeapon(name, itemGrenade, renderer);
      this.rendererRegistry.register(itemGrenade, itemGrenade.getName(), itemGrenade.getRenderer());
      renderer.setClientModContext(this);
   }

   public float getAspectRatio() {
      return this.aspectRatio;
   }

   public Framebuffer getInventoryFramebuffer() {
      if(this.inventoryFramebuffer == null) {
         this.inventoryFramebuffer = new Framebuffer(256, 256, true);
         this.inventoryFramebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      }

      return this.inventoryFramebuffer;
   }

   public Map getInventoryTextureMap() {
      return this.inventoryTextureMap;
   }

   public String getModId() {
      return this.modId;
   }

   public EffectManager getEffectManager() {
      return this.effectManager;
   }
}
