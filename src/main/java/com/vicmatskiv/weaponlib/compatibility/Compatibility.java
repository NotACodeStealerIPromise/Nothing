package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.Explosion;
import com.vicmatskiv.weaponlib.ModContext;
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
import cpw.mods.fml.common.IWorldGenerator;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import net.minecraftforge.event.entity.item.ItemTossEvent;

public interface Compatibility {
   World world(Entity var1);

   EntityPlayer clientPlayer();

   void setClientPlayer(EntityPlayer var1);

   IAttribute getMovementSpeedAttribute();

   NBTTagCompound getTagCompound(ItemStack var1);

   void setTagCompound(ItemStack var1, NBTTagCompound var2);

   ItemStack getItemStack(ItemTossEvent var1);

   EntityPlayer getPlayer(ItemTossEvent var1);

   ItemStack getHeldItemMainHand(EntityLivingBase var1);

   boolean consumeInventoryItem(EntityPlayer var1, Item var2);

   int getCurrentInventoryItemIndex(EntityPlayer var1);

   void ensureTagCompound(ItemStack var1);

   void playSound(EntityPlayer var1, CompatibleSound var2, float var3, float var4);

   void playSoundToNearExcept(EntityPlayer var1, CompatibleSound var2, float var3, float var4);

   boolean isClientSide();

   CompatibleMathHelper getMathHelper();

   EntityPlayer getClientPlayer();

   FontRenderer getFontRenderer();

   ScaledResolution getResolution(Pre var1);

   ElementType getEventType(Pre var1);

   ItemStack getHelmet();

   CompatibleVec3 getLookVec(EntityPlayer var1);

   void registerKeyBinding(KeyBinding var1);

   void registerWithEventBus(Object var1);

   void registerWithFmlEventBus(Object var1);

   void registerSound(CompatibleSound var1);

   void registerItem(Item var1, String var2);

   void registerItem(String var1, Item var2, String var3);

   void runInMainClientThread(Runnable var1);

   void registerModEntity(Class var1, String var2, int var3, Object var4, String var5, int var6, int var7, boolean var8);

   void registerRenderingRegistry(CompatibleRenderingRegistry var1);

   Object getPrivateValue(Class var1, Object var2, String... var3);

   int getButton(MouseEvent var1);

   EntityPlayer getEntity(FOVUpdateEvent var1);

   EntityLivingBase getEntity(net.minecraftforge.client.event.RenderLivingEvent.Pre var1);

   void setNewFov(FOVUpdateEvent var1, float var2);

   RenderPlayer getRenderer(net.minecraftforge.client.event.RenderLivingEvent.Pre var1);

   GuiScreen getGui(GuiOpenEvent var1);

   void setAimed(RenderPlayer var1, boolean var2);

   CompatibleRayTraceResult getObjectMouseOver();

   CompatibleBlockState getBlockAtPosition(World var1, CompatibleRayTraceResult var2);

   void destroyBlock(World var1, CompatibleRayTraceResult var2);

   boolean addItemToPlayerInventory(EntityPlayer var1, Item var2, int var3);

   boolean consumeInventoryItem(InventoryPlayer var1, Item var2);

   ItemStack itemStackForItem(Item var1, Predicate var2, EntityPlayer var3);

   boolean isGlassBlock(CompatibleBlockState var1);

   float getEffectOffsetX();

   float getEffectOffsetY();

   float getEffectScaleFactor();

   void spawnEntity(EntityPlayer var1, Entity var2);

   void moveParticle(CompatibleParticle var1, double var2, double var4, double var6);

   int getStackSize(ItemStack var1);

   ItemStack consumeInventoryItem(Item var1, Predicate var2, EntityPlayer var3, int var4);

   ItemStack getInventoryItemStack(EntityPlayer var1, int var2);

   int getInventorySlot(EntityPlayer var1, ItemStack var2);

   boolean consumeInventoryItemFromSlot(EntityPlayer var1, int var2);

   void addShapedRecipe(ItemStack var1, Object... var2);

   void addShapedOreRecipe(ItemStack var1, Object... var2);

   void disableLightMap();

   void enableLightMap();

   void registerBlock(String var1, Block var2, String var3);

   void registerWorldGenerator(IWorldGenerator var1, int var2);

   ArmorMaterial addArmorMaterial(String var1, String var2, int var3, int[] var4, int var5, CompatibleSound var6, float var7);

   boolean inventoryHasFreeSlots(EntityPlayer var1);

   void addBlockHitEffect(int var1, int var2, int var3, CompatibleEnumFacing var4);

   String getDisplayName(EntityPlayer var1);

   String getPlayerName(EntityPlayer var1);

   void clickBlock(CompatibleBlockPos var1, CompatibleEnumFacing var2);

   boolean isAirBlock(World var1, CompatibleBlockPos var2);

   void addChatMessage(Entity var1, String var2);

   RenderGlobal createCompatibleRenderGlobal();

   CompatibleParticleManager createCompatibleParticleManager(WorldClient var1);

   Entity getRenderViewEntity();

   void setRenderViewEntity(Entity var1);

   CompatibleParticleManager getCompatibleParticleManager();

   void addBreakingParticle(ModContext var1, double var2, double var4, double var6);

   float getAspectRatio(ModContext var1);

   void setStackSize(ItemStack var1, int var2);

   ItemStack tryConsumingCompatibleItem(List var1, int var2, EntityPlayer var3, Predicate... var4);

   Item findItemByName(String var1, String var2);

   CompatibleRayTraceResult rayTraceBlocks(Entity var1, CompatibleVec3 var2, CompatibleVec3 var3);

   CompatibleAxisAlignedBB expandEntityBoundingBox(Entity var1, double var2, double var4, double var6);

   CompatibleAxisAlignedBB getBoundingBox(Entity var1);

   List getEntitiesWithinAABBExcludingEntity(World var1, Entity var2, CompatibleAxisAlignedBB var3);

   void spawnParticle(World var1, String var2, double var3, double var5, double var7, double var9, double var11, double var13);

   CompatibleBlockState getBlockAtPosition(World var1, CompatibleBlockPos var2);

   boolean isBlockPenetratableByBullets(Block var1);

   boolean canCollideCheck(Block var1, CompatibleBlockState var2, boolean var3);

   float getCompatibleShellCasingForwardOffset();

   boolean madeFromHardMaterial(CompatibleBlockState var1);

   void playSoundAtEntity(Entity var1, CompatibleSound var2, float var3, float var4);

   double getBlockDensity(World var1, CompatibleVec3 var2, CompatibleAxisAlignedBB var3);

   boolean isImmuneToExplosions(Entity var1);

   boolean isAirBlock(CompatibleBlockState var1);

   boolean canDropBlockFromExplosion(CompatibleBlockState var1, Explosion var2);

   void onBlockExploded(World var1, CompatibleBlockState var2, CompatibleBlockPos var3, Explosion var4);

   float getExplosionResistance(World var1, CompatibleBlockState var2, CompatibleBlockPos var3, Entity var4, Explosion var5);

   float getExplosionResistance(World var1, Entity var2, Explosion var3, CompatibleBlockPos var4, CompatibleBlockState var5);

   boolean isSpectator(EntityPlayer var1);

   boolean isCreative(EntityPlayer var1);

   void setBlockToFire(World var1, CompatibleBlockPos var2);

   DamageSource getDamageSource(Explosion var1);

   double getBlastDamageReduction(EntityLivingBase var1, double var2);

   boolean verifyExplosion(World var1, Entity var2, Explosion var3, CompatibleBlockPos var4, CompatibleBlockState var5, float var6);

   boolean isFullBlock(CompatibleBlockState var1);

   void dropBlockAsItemWithChance(World var1, CompatibleBlockState var2, CompatibleBlockPos var3, float var4, int var5);

   CompatibleBlockState getBlockBelow(World var1, CompatibleBlockPos var2);

   void playSound(World var1, double var2, double var4, double var6, CompatibleSound var8, float var9, float var10);

   boolean isBlockPenetratableByGrenades(Block var1);

   DamageSource genericDamageSource();

   boolean isCollided(CompatibleParticle var1);

   ItemStack createItemStack(CompatibleItems var1, int var2, int var3);

   void addSmelting(Block var1, ItemStack var2, float var3);

   void addSmelting(Item var1, ItemStack var2, float var3);

   boolean isFlying(EntityPlayer var1);

   String getLocalizedString(String var1, Object... var2);
}
