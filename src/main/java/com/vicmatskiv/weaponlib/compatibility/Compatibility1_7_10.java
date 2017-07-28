package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.Compatibility;
import com.vicmatskiv.weaponlib.compatibility.CompatibleAxisAlignedBB;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEnumFacing;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMathHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticle;
import com.vicmatskiv.weaponlib.compatibility.CompatibleParticleManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRenderingRegistry;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentProtection;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.util.StatCollector;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Compatibility1_7_10 implements Compatibility {
   private static final float DEFAULT_SHELL_CASING_FORWARD_OFFSET = 0.0F;
   private static CompatibleMathHelper mathHelper = new CompatibleMathHelper();

   public World world(Entity entity) {
      return entity.worldObj;
   }

   @SideOnly(Side.CLIENT)
   public EntityPlayer clientPlayer() {
      return Minecraft.getMinecraft().thePlayer;
   }

   @SideOnly(Side.CLIENT)
   public void setClientPlayer(EntityPlayer player) {
      Minecraft.getMinecraft().thePlayer = (EntityClientPlayerMP)player;
   }

   public void spawnEntity(EntityPlayer player, Entity entity) {
      player.worldObj.spawnEntityInWorld(entity);
   }

   public void moveParticle(CompatibleParticle particle, double motionX, double motionY, double motionZ) {
      particle.moveEntity(motionX, motionY, motionZ);
   }

   public int getStackSize(ItemStack consumedStack) {
      return consumedStack.stackSize;
   }

   public NBTTagCompound getTagCompound(ItemStack itemStack) {
      return itemStack.stackTagCompound;
   }

   public ItemStack getItemStack(ItemTossEvent event) {
      return event.entityItem.getEntityItem();
   }

   public EntityPlayer getPlayer(ItemTossEvent event) {
      return event.player;
   }

   public ItemStack getHeldItemMainHand(EntityLivingBase player) {
      return player.getHeldItem();
   }

   public boolean consumeInventoryItem(EntityPlayer player, Item item) {
      return player.inventory.consumeInventoryItem(item);
   }

   public void ensureTagCompound(ItemStack itemStack) {
      if(itemStack.stackTagCompound == null) {
         itemStack.stackTagCompound = new NBTTagCompound();
      }

   }

   public void playSound(EntityPlayer player, CompatibleSound sound, float volume, float pitch) {
      if(sound != null) {
         player.playSound(sound.getSound(), volume, pitch);
      }

   }

   public IAttribute getMovementSpeedAttribute() {
      return SharedMonsterAttributes.movementSpeed;
   }

   public void setTagCompound(ItemStack itemStack, NBTTagCompound tagCompound) {
      itemStack.stackTagCompound = tagCompound;
   }

   public boolean isClientSide() {
      return FMLCommonHandler.instance().getSide() == Side.CLIENT;
   }

   public CompatibleMathHelper getMathHelper() {
      return mathHelper;
   }

   public void playSoundToNearExcept(EntityPlayer player, CompatibleSound sound, float volume, float pitch) {
      player.worldObj.playSoundToNearExcept(player, sound.getSound(), volume, pitch);
   }

   @SideOnly(Side.CLIENT)
   public EntityPlayer getClientPlayer() {
      return FMLClientHandler.instance().getClientPlayerEntity();
   }

   @SideOnly(Side.CLIENT)
   public FontRenderer getFontRenderer() {
      return Minecraft.getMinecraft().fontRenderer;
   }

   @SideOnly(Side.CLIENT)
   public ScaledResolution getResolution(Pre event) {
      return event.resolution;
   }

   @SideOnly(Side.CLIENT)
   public ElementType getEventType(Pre event) {
      return event.type;
   }

   @SideOnly(Side.CLIENT)
   public ItemStack getHelmet() {
      return Minecraft.getMinecraft().thePlayer.getEquipmentInSlot(4);
   }

   public CompatibleVec3 getLookVec(EntityPlayer player) {
      return new CompatibleVec3(player.getLookVec());
   }

   @SideOnly(Side.CLIENT)
   public void registerKeyBinding(KeyBinding key) {
      ClientRegistry.registerKeyBinding(key);
   }

   public void registerWithFmlEventBus(Object object) {
      FMLCommonHandler.instance().bus().register(object);
   }

   public void registerWithEventBus(Object object) {
      MinecraftForge.EVENT_BUS.register(object);
   }

   public void registerSound(CompatibleSound sound) {
   }

   public void registerItem(Item item, String name) {
      GameRegistry.registerItem(item, name);
   }

   public void registerItem(String modId, Item item, String name) {
      GameRegistry.registerItem(item, name);
   }

   public void runInMainClientThread(Runnable runnable) {
      runnable.run();
   }

   public void registerModEntity(Class entityClass, String entityName, int id, Object mod, String modId, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates) {
      EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
   }

   public void registerRenderingRegistry(CompatibleRenderingRegistry rendererRegistry) {
   }

   public Object getPrivateValue(Class classToAccess, Object instance, String... fieldNames) {
      return ObfuscationReflectionHelper.getPrivateValue(classToAccess, instance, fieldNames);
   }

   public int getButton(MouseEvent event) {
      return event.button;
   }

   @SideOnly(Side.CLIENT)
   public EntityPlayer getEntity(FOVUpdateEvent event) {
      return event.entity;
   }

   @SideOnly(Side.CLIENT)
   public EntityLivingBase getEntity(net.minecraftforge.client.event.RenderLivingEvent.Pre event) {
      return event.entity;
   }

   @SideOnly(Side.CLIENT)
   public void setNewFov(FOVUpdateEvent event, float fov) {
      event.newfov = fov;
   }

   @SideOnly(Side.CLIENT)
   public RenderPlayer getRenderer(net.minecraftforge.client.event.RenderLivingEvent.Pre event) {
      return (RenderPlayer)event.renderer;
   }

   @SideOnly(Side.CLIENT)
   public GuiScreen getGui(GuiOpenEvent event) {
      return event.gui;
   }

   @SideOnly(Side.CLIENT)
   public void setAimed(RenderPlayer rp, boolean aimed) {
      rp.modelBipedMain.aimedBow = aimed;
   }

   public CompatibleRayTraceResult getObjectMouseOver() {
      return CompatibleRayTraceResult.fromMovingObjectPosition(Minecraft.getMinecraft().objectMouseOver);
   }

   public boolean consumeInventoryItem(InventoryPlayer inventoryPlayer, Item item) {
      return inventoryPlayer.consumeInventoryItem(item);
   }

   public CompatibleBlockState getBlockAtPosition(World world, CompatibleRayTraceResult position) {
      return CompatibleBlockState.fromBlock(world.getBlock(position.getBlockPosX(), position.getBlockPosY(), position.getBlockPosZ()));
   }

   public void destroyBlock(World world, CompatibleRayTraceResult position) {
      world.func_147480_a(position.getBlockPosX(), position.getBlockPosY(), position.getBlockPosZ(), true);
   }

   public ItemStack itemStackForItem(Item item, Predicate condition, EntityPlayer player) {
      ItemStack result = null;

      for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
         if(player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == item && condition.test(player.inventory.mainInventory[i])) {
            result = player.inventory.mainInventory[i];
            break;
         }
      }

      return result;
   }

   public boolean isGlassBlock(CompatibleBlockState blockState) {
      Block block = blockState.getBlock();
      return block == Blocks.glass || block == Blocks.glass_pane || block == Blocks.stained_glass || block == Blocks.stained_glass_pane;
   }

   public float getEffectOffsetX() {
      return 0.0F;
   }

   public float getEffectOffsetY() {
      return 0.0F;
   }

   public float getEffectScaleFactor() {
      return 2.3F;
   }

   public int getCurrentInventoryItemIndex(EntityPlayer player) {
      return player.inventory.currentItem;
   }

   public ItemStack getInventoryItemStack(EntityPlayer player, int inventoryItemIndex) {
      return player.inventory.getStackInSlot(inventoryItemIndex);
   }

   public int getInventorySlot(EntityPlayer player, ItemStack itemStack) {
      int slot = -1;

      for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
         if(player.inventory.mainInventory[i] == itemStack) {
            slot = i;
            break;
         }
      }

      return slot;
   }

   public boolean addItemToPlayerInventory(EntityPlayer player, Item item, int slot) {
      boolean result = false;
      if(slot == -1) {
         player.inventory.addItemStackToInventory(new ItemStack(item));
      } else if(player.inventory.mainInventory[slot] == null) {
         player.inventory.mainInventory[slot] = new ItemStack(item);
      }

      return result;
   }

   public boolean consumeInventoryItemFromSlot(EntityPlayer player, int slot) {
      if(player.inventory.mainInventory[slot] == null) {
         return false;
      } else {
         if(--player.inventory.mainInventory[slot].stackSize <= 0) {
            player.inventory.mainInventory[slot] = null;
         }

         return true;
      }
   }

   public void addShapedRecipe(ItemStack itemStack, Object... materials) {
      GameRegistry.addShapedRecipe(itemStack, materials);
   }

   public void addShapedOreRecipe(ItemStack itemStack, Object... materials) {
      GameRegistry.addRecipe((new ShapedOreRecipe(itemStack, materials)).setMirrored(false));
   }

   public void disableLightMap() {
      Minecraft.getMinecraft().entityRenderer.disableLightmap(0.0D);
   }

   public void enableLightMap() {
      Minecraft.getMinecraft().entityRenderer.enableLightmap(0.0D);
   }

   public void registerBlock(String modId, Block block, String name) {
      GameRegistry.registerBlock(block, name);
   }

   public void registerWorldGenerator(IWorldGenerator generator, int modGenerationWeight) {
      GameRegistry.registerWorldGenerator(generator, modGenerationWeight);
   }

   public ArmorMaterial addArmorMaterial(String name, String textureName, int durability, int[] reductionAmounts, int enchantability, CompatibleSound soundOnEquip, float toughness) {
      return EnumHelper.addArmorMaterial(name, durability, reductionAmounts, enchantability);
   }

   public boolean inventoryHasFreeSlots(EntityPlayer player) {
      boolean result = false;

      for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
         if(player.inventory.mainInventory[i] == null) {
            result = true;
            break;
         }
      }

      return result;
   }

   public void addBlockHitEffect(int x, int y, int z, CompatibleEnumFacing enumFacing) {
      for(int i = 0; i < 6; ++i) {
         Minecraft.getMinecraft().effectRenderer.addBlockHitEffects(x, y, z, enumFacing.ordinal());
      }

   }

   public String getDisplayName(EntityPlayer entity) {
      return entity.getDisplayName();
   }

   public String getPlayerName(EntityPlayer player) {
      return player.getCommandSenderName();
   }

   @SideOnly(Side.CLIENT)
   public RenderGlobal createCompatibleRenderGlobal() {
      return Minecraft.getMinecraft().renderGlobal;
   }

   public CompatibleParticleManager createCompatibleParticleManager(WorldClient world) {
      return new CompatibleParticleManager(world);
   }

   public void setRenderViewEntity(Entity entity) {
      if(entity instanceof EntityLivingBase) {
         Minecraft.getMinecraft().renderViewEntity = (EntityLivingBase)entity;
      }

   }

   public Entity getRenderViewEntity() {
      return Minecraft.getMinecraft().renderViewEntity;
   }

   public CompatibleParticleManager getCompatibleParticleManager() {
      return new CompatibleParticleManager(Minecraft.getMinecraft().effectRenderer);
   }

   public void addChatMessage(Entity clientPlayer, String message) {
      if(clientPlayer instanceof EntityPlayer) {
         ((EntityPlayerSP)clientPlayer).addChatMessage(new ChatComponentText(message));
      }

   }

   public boolean isAirBlock(World world, CompatibleBlockPos blockPos) {
      Block blockHit = world.getBlock(blockPos.getBlockPosX(), blockPos.getBlockPosY(), blockPos.getBlockPosZ());
      return blockHit.getMaterial() == Material.air;
   }

   public void clickBlock(CompatibleBlockPos blockPos, CompatibleEnumFacing sideHit) {
      Minecraft.getMinecraft().playerController.clickBlock(blockPos.getBlockPosX(), blockPos.getBlockPosY(), blockPos.getBlockPosZ(), sideHit.ordinal());
   }

   @SideOnly(Side.CLIENT)
   public void addBreakingParticle(ModContext modContext, double x, double y, double z) {
      double yOffset = 1.0D;
      CompatibleParticle.CompatibleParticleBreaking particle = CompatibleParticle.createParticleBreaking(modContext, this.world(this.clientPlayer()), x, y + yOffset, z);
      Minecraft.getMinecraft().effectRenderer.addEffect(particle);
   }

   @SideOnly(Side.CLIENT)
   public float getAspectRatio(ModContext modContext) {
      return modContext.getAspectRatio();
   }

   private static int itemSlotIndex(Item item, Predicate condition, EntityPlayer player) {
      for(int i = 0; i < player.inventory.mainInventory.length; ++i) {
         if(player.inventory.mainInventory[i] != null && player.inventory.mainInventory[i].getItem() == item && condition.test(player.inventory.mainInventory[i])) {
            return i;
         }
      }

      return -1;
   }

   public ItemStack consumeInventoryItem(Item item, Predicate condition, EntityPlayer player, int maxSize) {
      if(maxSize <= 0) {
         return null;
      } else {
         int i = itemSlotIndex(item, condition, player);
         if(i < 0) {
            return null;
         } else {
            ItemStack stackInSlot = player.inventory.mainInventory[i];
            int consumedStackSize = maxSize >= stackInSlot.stackSize?stackInSlot.stackSize:maxSize;
            ItemStack result = stackInSlot.splitStack(consumedStackSize);
            if(stackInSlot.stackSize <= 0) {
               player.inventory.mainInventory[i] = null;
            }

            return result;
         }
      }
   }

   public ItemStack tryConsumingCompatibleItem(List compatibleParts, int maxSize, EntityPlayer player, Predicate... conditions) {
      ItemStack resultStack = null;
      Predicate[] var6 = conditions;
      int var7 = conditions.length;

      for(int var8 = 0; var8 < var7; ++var8) {
         Predicate condition = var6[var8];
         Iterator var10 = compatibleParts.iterator();

         while(var10.hasNext()) {
            Item item = (Item)var10.next();
            if((resultStack = this.consumeInventoryItem(item, condition, player, maxSize)) != null) {
               break;
            }
         }

         if(resultStack != null) {
            break;
         }
      }

      return resultStack;
   }

   public void setStackSize(ItemStack itemStack, int size) {
      itemStack.stackSize = size;
   }

   public Item findItemByName(String modId, String itemName) {
      return GameRegistry.findItem(modId, itemName);
   }

   public CompatibleRayTraceResult rayTraceBlocks(Entity entity, CompatibleVec3 vec3, CompatibleVec3 vec31) {
      return CompatibleRayTraceResult.fromMovingObjectPosition(entity.worldObj.rayTraceBlocks(vec3.getVec(), vec31.getVec()));
   }

   public CompatibleAxisAlignedBB expandEntityBoundingBox(Entity entity1, double f1, double f2, double f3) {
      return new CompatibleAxisAlignedBB(entity1.boundingBox.expand(f1, f2, f3));
   }

   public CompatibleAxisAlignedBB getBoundingBox(Entity entity) {
      return new CompatibleAxisAlignedBB(entity.boundingBox);
   }

   public List getEntitiesWithinAABBExcludingEntity(World world, Entity entity, CompatibleAxisAlignedBB boundingBox) {
      return world.getEntitiesWithinAABBExcludingEntity(entity, boundingBox.getBoundingBox());
   }

   public void spawnParticle(World world, String particleName, double xCoord, double yCoord, double zCoord, double xSpeed, double ySpeed, double zSpeed) {
      world.spawnParticle(particleName, xCoord, yCoord, zCoord, xSpeed, ySpeed, zSpeed);
   }

   public CompatibleBlockState getBlockAtPosition(World world, CompatibleBlockPos blockPos) {
      return CompatibleBlockState.fromBlock(world.getBlock(blockPos.getBlockPosX(), blockPos.getBlockPosY(), blockPos.getBlockPosZ()));
   }

   public boolean isBlockPenetratableByBullets(Block block) {
      return block == Blocks.air || block == Blocks.tallgrass || block == Blocks.leaves || block == Blocks.leaves2 || block == Blocks.fire || block == Blocks.hay_block || block == Blocks.double_plant || block == Blocks.web || block == Blocks.wheat;
   }

   public boolean canCollideCheck(Block block, CompatibleBlockState metadata, boolean hitIfLiquid) {
      return block.canCollideCheck(metadata.getBlockMetadata(), hitIfLiquid);
   }

   public float getCompatibleShellCasingForwardOffset() {
      return 0.0F;
   }

   public boolean madeFromHardMaterial(CompatibleBlockState blockState) {
      Block block = blockState.getBlock();
      Material material = block.getMaterial();
      return material == Material.rock || material == Material.iron || material == Material.ice || material == Material.wood;
   }

   public void playSoundAtEntity(Entity entity, CompatibleSound explosionSound, float volume, float pitch) {
      entity.worldObj.playSoundAtEntity(entity, explosionSound.getSound(), volume, pitch);
   }

   public double getBlockDensity(World world, CompatibleVec3 vec3, CompatibleAxisAlignedBB boundingBox) {
      return (double)world.getBlockDensity(vec3.getVec(), boundingBox.getBoundingBox());
   }

   public boolean isImmuneToExplosions(Entity entity) {
      return false;
   }

   public boolean isAirBlock(CompatibleBlockState blockState) {
      return blockState.getBlock().getMaterial() == Material.air;
   }

   private Explosion getCompatibleExplosion(com.vicmatskiv.weaponlib.Explosion e) {
      Explosion ce = new Explosion(e.getWorld(), e.getExploder(), e.getExplosionX(), e.getExplosionX(), e.getExplosionZ(), e.getExplosionSize());
      return ce;
   }

   public boolean canDropBlockFromExplosion(CompatibleBlockState blockState, com.vicmatskiv.weaponlib.Explosion explosion) {
      return blockState.getBlock().canDropFromExplosion(this.getCompatibleExplosion(explosion));
   }

   public void onBlockExploded(World world, CompatibleBlockState blockState, CompatibleBlockPos blockpos, com.vicmatskiv.weaponlib.Explosion explosion) {
      blockState.getBlock().onBlockExploded(world, blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ(), this.getCompatibleExplosion(explosion));
   }

   public float getExplosionResistance(World world, CompatibleBlockState blockState, CompatibleBlockPos blockpos, Entity entity, com.vicmatskiv.weaponlib.Explosion explosion) {
      return blockState.getBlock().getExplosionResistance(entity);
   }

   public float getExplosionResistance(World world, Entity exploder, com.vicmatskiv.weaponlib.Explosion explosion, CompatibleBlockPos blockpos, CompatibleBlockState blockState) {
      return exploder.func_145772_a(this.getCompatibleExplosion(explosion), world, blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ(), blockState.getBlock());
   }

   public boolean isSpectator(EntityPlayer entityplayer) {
      return false;
   }

   public boolean isCreative(EntityPlayer entityplayer) {
      return entityplayer.capabilities.isCreativeMode;
   }

   public boolean isFlying(EntityPlayer entityplayer) {
      return entityplayer.capabilities.isFlying;
   }

   public void setBlockToFire(World world, CompatibleBlockPos blockpos1) {
      world.setBlock(blockpos1.getBlockPosX(), blockpos1.getBlockPosY(), blockpos1.getBlockPosZ(), Blocks.fire);
   }

   public DamageSource getDamageSource(com.vicmatskiv.weaponlib.Explosion explosion) {
      return DamageSource.setExplosionSource(this.getCompatibleExplosion(explosion));
   }

   public double getBlastDamageReduction(EntityLivingBase entity, double d10) {
      return EnchantmentProtection.func_92092_a(entity, d10);
   }

   public boolean verifyExplosion(World world, Entity exploder, com.vicmatskiv.weaponlib.Explosion explosion, CompatibleBlockPos blockpos, CompatibleBlockState blockState, float f) {
      return exploder.func_145774_a(this.getCompatibleExplosion(explosion), world, blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ(), blockState.getBlock(), f);
   }

   public CompatibleBlockState getBlockBelow(World world, CompatibleBlockPos blockpos1) {
      return CompatibleBlockState.fromBlock(world.getBlock(blockpos1.getBlockPosX(), blockpos1.getBlockPosY() - 1, blockpos1.getBlockPosZ()));
   }

   public boolean isFullBlock(CompatibleBlockState blockState) {
      return blockState.getBlock().func_149730_j();
   }

   public void dropBlockAsItemWithChance(World world, CompatibleBlockState blockState, CompatibleBlockPos blockpos, float f, int i) {
      int blockMetadata = world.getBlockMetadata(blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ());
      blockState.getBlock().dropBlockAsItemWithChance(world, blockpos.getBlockPosX(), blockpos.getBlockPosY(), blockpos.getBlockPosZ(), blockMetadata, f, i);
   }

   public void playSound(World world, double posX, double posY, double posZ, CompatibleSound sound, float volume, float pitch) {
      if(sound != null) {
         world.playSoundEffect(posX, posY, posZ, sound.getSound(), volume, pitch);
      }

   }

   public boolean isBlockPenetratableByGrenades(Block block) {
      return block == Blocks.air || block == Blocks.tallgrass || block == Blocks.leaves || block == Blocks.leaves2 || block == Blocks.fire || block == Blocks.hay_block || block == Blocks.double_plant || block == Blocks.web || block == Blocks.wheat;
   }

   public DamageSource genericDamageSource() {
      return DamageSource.generic;
   }

   public boolean isCollided(CompatibleParticle particle) {
      return particle.isCollided;
   }

   public ItemStack createItemStack(CompatibleItems compatibleItem, int stackSize, int damage) {
      return new ItemStack(compatibleItem.getItem(), stackSize, damage);
   }

   public void addSmelting(Block block, ItemStack output, float f) {
      GameRegistry.addSmelting(block, output, f);
   }

   public void addSmelting(Item item, ItemStack output, float f) {
      GameRegistry.addSmelting(item, output, f);
   }

   public String getLocalizedString(String format, Object... args) {
      return StatCollector.translateToLocalFormatted(format, args);
   }
}
