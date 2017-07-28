package com.vicmatskiv.weaponlib.compatibility;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartHopper;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayer.EnumStatus;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.CombatTracker;
import net.minecraft.util.DamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings.GameType;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CompatiblePlayerWrapper extends EntityClientPlayerMP {
   private AbstractClientPlayer delegate;

   public CompatiblePlayerWrapper(Minecraft mc, AbstractClientPlayer player) {
      super(mc, player.worldObj, mc.getSession(), (NetHandlerPlayClient)null, (StatFileWriter)null);
      this.delegate = player;
      this.posX = player.posX;
      this.posY = player.posY;
      this.posZ = player.posZ;
      this.lastTickPosX = player.lastTickPosX;
      this.lastTickPosY = player.lastTickPosY;
      this.lastTickPosZ = player.lastTickPosZ;
      this.cameraYaw = player.cameraYaw;
      this.cameraPitch = player.cameraPitch;
      this.motionX = player.motionX;
      this.motionY = player.motionY;
      this.motionZ = player.motionZ;
      this.chunkCoordX = player.chunkCoordX;
      this.chunkCoordY = player.chunkCoordY;
      this.chunkCoordZ = player.chunkCoordZ;
      this.addedToChunk = player.addedToChunk;
      this.arrowHitTimer = player.arrowHitTimer;
      this.attackedAtYaw = player.attackedAtYaw;
      this.attackTime = player.attackTime;
      this.dimension = player.dimension;
      this.entityUniqueID = player.getUniqueID();
      this.limbSwing = player.limbSwing;
      this.limbSwingAmount = player.limbSwingAmount;
      this.height = player.height;
      this.moveForward = player.moveForward;
      this.ticksExisted = player.ticksExisted;
   }

   public boolean func_152122_n() {
      return this.delegate.func_152122_n();
   }

   public boolean func_152123_o() {
      return this.delegate.func_152123_o();
   }

   public ResourceLocation getLocationSkin() {
      return this.delegate.getLocationSkin();
   }

   public ResourceLocation getLocationCape() {
      return this.delegate.getLocationCape();
   }

   public boolean attackEntityFrom(DamageSource p_70097_1_, float p_70097_2_) {
      return this.delegate.attackEntityFrom(p_70097_1_, p_70097_2_);
   }

   public void func_152121_a(Type p_152121_1_, ResourceLocation p_152121_2_) {
      this.delegate.func_152121_a(p_152121_1_, p_152121_2_);
   }

   public void heal(float p_70691_1_) {
      this.delegate.heal(p_70691_1_);
   }

   public void mountEntity(Entity p_70078_1_) {
      this.delegate.mountEntity(p_70078_1_);
   }

   public void onUpdate() {
      this.delegate.onUpdate();
   }

   public void updateEntityActionState() {
   }

   public void sendMotionUpdates() {
   }

   public void onLivingUpdate() {
      this.delegate.onLivingUpdate();
   }

   public EntityItem dropOneItem(boolean p_71040_1_) {
      return this.delegate.dropOneItem(p_71040_1_);
   }

   public void joinEntityItemWithWorld(EntityItem p_71012_1_) {
      this.delegate.joinEntityItemWithWorld(p_71012_1_);
   }

   public void sendChatMessage(String p_71165_1_) {
   }

   public void swingItem() {
      this.delegate.swingItem();
   }

   public int getEntityId() {
      return this.delegate.getEntityId();
   }

   public void setEntityId(int p_145769_1_) {
      this.delegate.setEntityId(p_145769_1_);
   }

   public void respawnPlayer() {
      this.delegate.respawnPlayer();
   }

   public void closeScreen() {
      this.delegate.closeScreen();
   }

   public ItemStack getItemInUse() {
      return this.delegate.getItemInUse();
   }

   public void closeScreenNoPacket() {
   }

   public int getItemInUseCount() {
      return this.delegate.getItemInUseCount();
   }

   public void setPlayerSPHealth(float p_71150_1_) {
   }

   public boolean isUsingItem() {
      return this.delegate.isUsingItem();
   }

   public DataWatcher getDataWatcher() {
      return this.delegate.getDataWatcher();
   }

   public int getItemInUseDuration() {
      return this.delegate.getItemInUseDuration();
   }

   public boolean equals(Object p_equals_1_) {
      return this.delegate.equals(p_equals_1_);
   }

   public void addStat(StatBase p_71064_1_, int p_71064_2_) {
      this.delegate.addStat(p_71064_1_, p_71064_2_);
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   public void stopUsingItem() {
      this.delegate.stopUsingItem();
   }

   public void sendPlayerAbilities() {
      this.delegate.sendPlayerAbilities();
   }

   public boolean canBreatheUnderwater() {
      return this.delegate.canBreatheUnderwater();
   }

   public void onEntityUpdate() {
      this.delegate.onEntityUpdate();
   }

   public void clearItemInUse() {
      this.delegate.clearItemInUse();
   }

   public void func_110322_i() {
   }

   public boolean isBlocking() {
      return this.delegate.isBlocking();
   }

   public void func_142020_c(String p_142020_1_) {
   }

   public String func_142021_k() {
      throw new UnsupportedOperationException();
   }

   public StatFileWriter getStatFileWriter() {
      throw new UnsupportedOperationException();
   }

   public float getFOVMultiplier() {
      return super.getFOVMultiplier();
   }

   public void setPosition(double p_70107_1_, double p_70107_3_, double p_70107_5_) {
      this.posX = p_70107_1_;
      this.posY = p_70107_3_;
      this.posZ = p_70107_5_;
      float f = this.width / 2.0F;
      float f1 = this.height;
      this.boundingBox.setBounds(p_70107_1_ - (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize, p_70107_5_ - (double)f, p_70107_1_ + (double)f, p_70107_3_ - (double)this.yOffset + (double)this.ySize + (double)f1, p_70107_5_ + (double)f);
   }

   public void setAngles(float p_70082_1_, float p_70082_2_) {
      float f2 = this.rotationPitch;
      float f3 = this.rotationYaw;
      this.rotationYaw = (float)((double)this.rotationYaw + (double)p_70082_1_ * 0.15D);
      this.rotationPitch = (float)((double)this.rotationPitch - (double)p_70082_2_ * 0.15D);
      if(this.rotationPitch < -90.0F) {
         this.rotationPitch = -90.0F;
      }

      if(this.rotationPitch > 90.0F) {
         this.rotationPitch = 90.0F;
      }

      this.prevRotationPitch += this.rotationPitch - f2;
      this.prevRotationYaw += this.rotationYaw - f3;
   }

   public void func_146100_a(TileEntity p_146100_1_) {
      this.delegate.func_146100_a(p_146100_1_);
   }

   public boolean isChild() {
      return this.delegate.isChild();
   }

   public void func_146095_a(CommandBlockLogic p_146095_1_) {
      this.delegate.func_146095_a(p_146095_1_);
   }

   public void displayGUIBook(ItemStack p_71048_1_) {
      this.delegate.displayGUIBook(p_71048_1_);
   }

   public void displayGUIChest(IInventory p_71007_1_) {
      this.delegate.displayGUIChest(p_71007_1_);
   }

   public int getMaxInPortalTime() {
      return this.delegate.getMaxInPortalTime();
   }

   public void func_146093_a(TileEntityHopper p_146093_1_) {
      this.delegate.func_146093_a(p_146093_1_);
   }

   public void displayGUIHopperMinecart(EntityMinecartHopper p_96125_1_) {
      this.delegate.displayGUIHopperMinecart(p_96125_1_);
   }

   public int getPortalCooldown() {
      return this.delegate.getPortalCooldown();
   }

   public void displayGUIHorse(EntityHorse p_110298_1_, IInventory p_110298_2_) {
      this.delegate.displayGUIHorse(p_110298_1_, p_110298_2_);
   }

   public void displayGUIWorkbench(int p_71058_1_, int p_71058_2_, int p_71058_3_) {
      this.delegate.displayGUIWorkbench(p_71058_1_, p_71058_2_, p_71058_3_);
   }

   public void displayGUIEnchantment(int p_71002_1_, int p_71002_2_, int p_71002_3_, String p_71002_4_) {
      this.delegate.displayGUIEnchantment(p_71002_1_, p_71002_2_, p_71002_3_, p_71002_4_);
   }

   public void displayGUIAnvil(int p_82244_1_, int p_82244_2_, int p_82244_3_) {
      this.delegate.displayGUIAnvil(p_82244_1_, p_82244_2_, p_82244_3_);
   }

   public Random getRNG() {
      return this.delegate.getRNG();
   }

   public EntityLivingBase getAITarget() {
      return this.delegate.getAITarget();
   }

   public void func_146101_a(TileEntityFurnace p_146101_1_) {
      this.delegate.func_146101_a(p_146101_1_);
   }

   public int func_142015_aE() {
      return this.delegate.func_142015_aE();
   }

   public void func_146098_a(TileEntityBrewingStand p_146098_1_) {
      this.delegate.func_146098_a(p_146098_1_);
   }

   public void setRevengeTarget(EntityLivingBase p_70604_1_) {
      this.delegate.setRevengeTarget(p_70604_1_);
   }

   public void func_146104_a(TileEntityBeacon p_146104_1_) {
      this.delegate.func_146104_a(p_146104_1_);
   }

   public EntityLivingBase getLastAttacker() {
      return this.delegate.getLastAttacker();
   }

   public void func_146102_a(TileEntityDispenser p_146102_1_) {
      this.delegate.func_146102_a(p_146102_1_);
   }

   public int getLastAttackerTime() {
      return this.delegate.getLastAttackerTime();
   }

   public void setLastAttacker(Entity p_130011_1_) {
      this.delegate.setLastAttacker(p_130011_1_);
   }

   public void displayGUIMerchant(IMerchant p_71030_1_, String p_71030_2_) {
      this.delegate.displayGUIMerchant(p_71030_1_, p_71030_2_);
   }

   public void onCriticalHit(Entity p_71009_1_) {
      this.delegate.onCriticalHit(p_71009_1_);
   }

   public int getAge() {
      return this.delegate.getAge();
   }

   public void onEnchantmentCritical(Entity p_71047_1_) {
      this.delegate.onEnchantmentCritical(p_71047_1_);
   }

   public void onItemPickup(Entity p_71001_1_, int p_71001_2_) {
      this.delegate.onItemPickup(p_71001_1_, p_71001_2_);
   }

   public boolean isSneaking() {
      return this.delegate.isSneaking();
   }

   public void handleHealthUpdate(byte p_70103_1_) {
      this.delegate.handleHealthUpdate(p_70103_1_);
   }

   public void addChatComponentMessage(IChatComponent p_146105_1_) {
      this.delegate.addChatComponentMessage(p_146105_1_);
   }

   public void setFire(int p_70015_1_) {
      this.delegate.setFire(p_70015_1_);
   }

   public void extinguish() {
      this.delegate.extinguish();
   }

   public void updateRidden() {
      this.delegate.updateRidden();
   }

   public boolean isOffsetPositionInLiquid(double p_70038_1_, double p_70038_3_, double p_70038_5_) {
      return this.delegate.isOffsetPositionInLiquid(p_70038_1_, p_70038_3_, p_70038_5_);
   }

   public void moveEntity(double p_70091_1_, double p_70091_3_, double p_70091_5_) {
      this.delegate.moveEntity(p_70091_1_, p_70091_3_, p_70091_5_);
   }

   public void preparePlayerToSpawn() {
      this.delegate.preparePlayerToSpawn();
   }

   public void setSprinting(boolean p_70031_1_) {
      this.delegate.setSprinting(p_70031_1_);
   }

   public void setXPStats(float p_71152_1_, int p_71152_2_, int p_71152_3_) {
   }

   public void addChatMessage(IChatComponent p_145747_1_) {
      this.delegate.addChatMessage(p_145747_1_);
   }

   public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
      return this.delegate.canCommandSenderUseCommand(p_70003_1_, p_70003_2_);
   }

   public ChunkCoordinates getPlayerCoordinates() {
      return this.delegate.getPlayerCoordinates();
   }

   public void playSound(String p_85030_1_, float p_85030_2_, float p_85030_3_) {
      this.delegate.playSound(p_85030_1_, p_85030_2_, p_85030_3_);
   }

   public boolean isClientWorld() {
      return this.delegate.isClientWorld();
   }

   public boolean isRidingHorse() {
      return false;
   }

   public float getHorseJumpPower() {
      return 0.0F;
   }

   public void clearActivePotions() {
      this.delegate.clearActivePotions();
   }

   public int getScore() {
      return this.delegate.getScore();
   }

   public void setScore(int p_85040_1_) {
      this.delegate.setScore(p_85040_1_);
   }

   public void addScore(int p_85039_1_) {
      this.delegate.addScore(p_85039_1_);
   }

   public Collection getActivePotionEffects() {
      return this.delegate.getActivePotionEffects();
   }

   public boolean isPotionActive(int p_82165_1_) {
      return this.delegate.isPotionActive(p_82165_1_);
   }

   public void onDeath(DamageSource p_70645_1_) {
      this.delegate.onDeath(p_70645_1_);
   }

   public boolean isPotionActive(Potion p_70644_1_) {
      return this.delegate.isPotionActive(p_70644_1_);
   }

   public PotionEffect getActivePotionEffect(Potion p_70660_1_) {
      return this.delegate.getActivePotionEffect(p_70660_1_);
   }

   public void addPotionEffect(PotionEffect p_70690_1_) {
      this.delegate.addPotionEffect(p_70690_1_);
   }

   public boolean isPotionApplicable(PotionEffect p_70687_1_) {
      return this.delegate.isPotionApplicable(p_70687_1_);
   }

   public boolean isEntityUndead() {
      return this.delegate.isEntityUndead();
   }

   public void removePotionEffectClient(int p_70618_1_) {
      this.delegate.removePotionEffectClient(p_70618_1_);
   }

   public void addToPlayerScore(Entity p_70084_1_, int p_70084_2_) {
      this.delegate.addToPlayerScore(p_70084_1_, p_70084_2_);
   }

   public void removePotionEffect(int p_82170_1_) {
      this.delegate.removePotionEffect(p_82170_1_);
   }

   public EntityItem dropPlayerItemWithRandomChoice(ItemStack p_71019_1_, boolean p_71019_2_) {
      return this.delegate.dropPlayerItemWithRandomChoice(p_71019_1_, p_71019_2_);
   }

   public EntityItem func_146097_a(ItemStack p_146097_1_, boolean p_146097_2_, boolean p_146097_3_) {
      return this.delegate.func_146097_a(p_146097_1_, p_146097_2_, p_146097_3_);
   }

   public void setHealth(float p_70606_1_) {
      if(this.delegate == null) {
         super.setHealth(p_70606_1_);
      } else {
         this.delegate.setHealth(p_70606_1_);
      }

   }

   public float getCurrentPlayerStrVsBlock(Block p_146096_1_, boolean p_146096_2_) {
      return this.delegate.getCurrentPlayerStrVsBlock(p_146096_1_, p_146096_2_);
   }

   public float getBreakSpeed(Block p_146096_1_, boolean p_146096_2_, int meta) {
      return this.delegate.getBreakSpeed(p_146096_1_, p_146096_2_, meta);
   }

   public float getBreakSpeed(Block p_146096_1_, boolean p_146096_2_, int meta, int x, int y, int z) {
      return this.delegate.getBreakSpeed(p_146096_1_, p_146096_2_, meta, x, y, z);
   }

   public boolean canHarvestBlock(Block p_146099_1_) {
      return this.delegate.canHarvestBlock(p_146099_1_);
   }

   public void readEntityFromNBT(NBTTagCompound p_70037_1_) {
      this.delegate.readEntityFromNBT(p_70037_1_);
   }

   public void renderBrokenItemStack(ItemStack p_70669_1_) {
      this.delegate.renderBrokenItemStack(p_70669_1_);
   }

   public void writeEntityToNBT(NBTTagCompound p_70014_1_) {
      this.delegate.writeEntityToNBT(p_70014_1_);
   }

   public AxisAlignedBB getBoundingBox() {
      return this.delegate.getBoundingBox();
   }

   public void knockBack(Entity p_70653_1_, float p_70653_2_, double p_70653_3_, double p_70653_5_) {
      this.delegate.knockBack(p_70653_1_, p_70653_2_, p_70653_3_, p_70653_5_);
   }

   public boolean isWet() {
      return this.delegate.isWet();
   }

   public boolean isInWater() {
      return this.delegate.isInWater();
   }

   public boolean handleWaterMovement() {
      return this.delegate.handleWaterMovement();
   }

   public boolean isOnLadder() {
      return this.delegate.isOnLadder();
   }

   public float getEyeHeight() {
      return this.delegate.getEyeHeight();
   }

   public boolean isEntityAlive() {
      return this.delegate.isEntityAlive();
   }

   public boolean isInsideOfMaterial(Material p_70055_1_) {
      return this.delegate.isInsideOfMaterial(p_70055_1_);
   }

   public void performHurtAnimation() {
      this.delegate.performHurtAnimation();
   }

   public boolean canAttackPlayer(EntityPlayer p_96122_1_) {
      return this.delegate.canAttackPlayer(p_96122_1_);
   }

   public int getTotalArmorValue() {
      return this.delegate.getTotalArmorValue();
   }

   public float getArmorVisibility() {
      return this.delegate.getArmorVisibility();
   }

   public boolean handleLavaMovement() {
      return this.delegate.handleLavaMovement();
   }

   public void moveFlying(float p_70060_1_, float p_70060_2_, float p_70060_3_) {
      this.delegate.moveFlying(p_70060_1_, p_70060_2_, p_70060_3_);
   }

   public int getBrightnessForRender(float p_70070_1_) {
      return this.delegate.getBrightnessForRender(p_70070_1_);
   }

   public float getBrightness(float p_70013_1_) {
      return this.delegate.getBrightness(p_70013_1_);
   }

   public void setWorld(World p_70029_1_) {
      this.delegate.setWorld(p_70029_1_);
   }

   public void setPositionAndRotation(double p_70080_1_, double p_70080_3_, double p_70080_5_, float p_70080_7_, float p_70080_8_) {
      this.delegate.setPositionAndRotation(p_70080_1_, p_70080_3_, p_70080_5_, p_70080_7_, p_70080_8_);
   }

   public boolean interactWith(Entity p_70998_1_) {
      return this.delegate.interactWith(p_70998_1_);
   }

   public CombatTracker func_110142_aN() {
      return this.delegate.func_110142_aN();
   }

   public EntityLivingBase func_94060_bK() {
      return this.delegate.func_94060_bK();
   }

   public void setLocationAndAngles(double p_70012_1_, double p_70012_3_, double p_70012_5_, float p_70012_7_, float p_70012_8_) {
      this.lastTickPosX = this.prevPosX = this.posX = p_70012_1_;
      this.lastTickPosY = this.prevPosY = this.posY = p_70012_3_ + (double)this.yOffset;
      this.lastTickPosZ = this.prevPosZ = this.posZ = p_70012_5_;
      this.rotationYaw = p_70012_7_;
      this.rotationPitch = p_70012_8_;
      this.setPosition(this.posX, this.posY, this.posZ);
   }

   public float getDistanceToEntity(Entity p_70032_1_) {
      return this.delegate.getDistanceToEntity(p_70032_1_);
   }

   public ItemStack getCurrentEquippedItem() {
      return this.delegate.getCurrentEquippedItem();
   }

   public void destroyCurrentEquippedItem() {
      this.delegate.destroyCurrentEquippedItem();
   }

   public double getDistanceSq(double p_70092_1_, double p_70092_3_, double p_70092_5_) {
      return this.delegate.getDistanceSq(p_70092_1_, p_70092_3_, p_70092_5_);
   }

   public double getYOffset() {
      return this.delegate.getYOffset();
   }

   public double getDistance(double p_70011_1_, double p_70011_3_, double p_70011_5_) {
      return this.delegate.getDistance(p_70011_1_, p_70011_3_, p_70011_5_);
   }

   public void attackTargetEntityWithCurrentItem(Entity p_71059_1_) {
      this.delegate.attackTargetEntityWithCurrentItem(p_71059_1_);
   }

   public double getDistanceSqToEntity(Entity p_70068_1_) {
      return this.delegate.getDistanceSqToEntity(p_70068_1_);
   }

   public void onCollideWithPlayer(EntityPlayer p_70100_1_) {
      this.delegate.onCollideWithPlayer(p_70100_1_);
   }

   public void applyEntityCollision(Entity p_70108_1_) {
      this.delegate.applyEntityCollision(p_70108_1_);
   }

   public void addVelocity(double p_70024_1_, double p_70024_3_, double p_70024_5_) {
      this.delegate.addVelocity(p_70024_1_, p_70024_3_, p_70024_5_);
   }

   public IAttributeInstance getEntityAttribute(IAttribute p_110148_1_) {
      return this.delegate == null?super.getEntityAttribute(p_110148_1_):this.delegate.getEntityAttribute(p_110148_1_);
   }

   public BaseAttributeMap getAttributeMap() {
      return this.delegate == null?super.getAttributeMap():this.delegate.getAttributeMap();
   }

   public EnumCreatureAttribute getCreatureAttribute() {
      return this.delegate == null?super.getCreatureAttribute():this.delegate.getCreatureAttribute();
   }

   public boolean isInRangeToRender3d(double p_145770_1_, double p_145770_3_, double p_145770_5_) {
      return this.delegate.isInRangeToRender3d(p_145770_1_, p_145770_3_, p_145770_5_);
   }

   public boolean isInRangeToRenderDist(double p_70112_1_) {
      return this.delegate.isInRangeToRenderDist(p_70112_1_);
   }

   public boolean writeMountToNBT(NBTTagCompound p_98035_1_) {
      return this.delegate.writeMountToNBT(p_98035_1_);
   }

   public void setPositionAndUpdate(double p_70634_1_, double p_70634_3_, double p_70634_5_) {
      this.delegate.setPositionAndUpdate(p_70634_1_, p_70634_3_, p_70634_5_);
   }

   public void dismountEntity(Entity p_110145_1_) {
      this.delegate.dismountEntity(p_110145_1_);
   }

   public boolean writeToNBTOptional(NBTTagCompound p_70039_1_) {
      return this.delegate.writeToNBTOptional(p_70039_1_);
   }

   public void writeToNBT(NBTTagCompound p_70109_1_) {
      this.delegate.writeToNBT(p_70109_1_);
   }

   public void setDead() {
      this.delegate.setDead();
   }

   public boolean isEntityInsideOpaqueBlock() {
      return this.delegate.isEntityInsideOpaqueBlock();
   }

   public GameProfile getGameProfile() {
      return this.delegate.getGameProfile();
   }

   public EnumStatus sleepInBedAt(int p_71018_1_, int p_71018_2_, int p_71018_3_) {
      return this.delegate.sleepInBedAt(p_71018_1_, p_71018_2_, p_71018_3_);
   }

   public void readFromNBT(NBTTagCompound p_70020_1_) {
      this.delegate.readFromNBT(p_70020_1_);
   }

   public void wakeUpPlayer(boolean p_70999_1_, boolean p_70999_2_, boolean p_70999_3_) {
      this.delegate.wakeUpPlayer(p_70999_1_, p_70999_2_, p_70999_3_);
   }

   public void onChunkLoad() {
      this.delegate.onChunkLoad();
   }

   public void setAIMoveSpeed(float p_70659_1_) {
      this.delegate.setAIMoveSpeed(p_70659_1_);
   }

   public boolean attackEntityAsMob(Entity p_70652_1_) {
      return this.delegate.attackEntityAsMob(p_70652_1_);
   }

   public EntityItem dropItem(Item p_145779_1_, int p_145779_2_) {
      return this.delegate.dropItem(p_145779_1_, p_145779_2_);
   }

   public EntityItem func_145778_a(Item p_145778_1_, int p_145778_2_, float p_145778_3_) {
      return this.delegate.func_145778_a(p_145778_1_, p_145778_2_, p_145778_3_);
   }

   public EntityItem entityDropItem(ItemStack p_70099_1_, float p_70099_2_) {
      return this.delegate.entityDropItem(p_70099_1_, p_70099_2_);
   }

   public float getBedOrientationInDegrees() {
      return this.delegate.getBedOrientationInDegrees();
   }

   public float getShadowSize() {
      return this.delegate.getShadowSize();
   }

   public boolean isPlayerSleeping() {
      return this.delegate.isPlayerSleeping();
   }

   public boolean isPlayerFullyAsleep() {
      return this.delegate.isPlayerFullyAsleep();
   }

   public int getSleepTimer() {
      return this.delegate.getSleepTimer();
   }

   public boolean interactFirst(EntityPlayer p_130002_1_) {
      return this.delegate.interactFirst(p_130002_1_);
   }

   public ChunkCoordinates getBedLocation() {
      return this.delegate.getBedLocation();
   }

   public AxisAlignedBB getCollisionBox(Entity p_70114_1_) {
      return this.delegate.getCollisionBox(p_70114_1_);
   }

   public boolean isSpawnForced() {
      return this.delegate.isSpawnForced();
   }

   public void setSpawnChunk(ChunkCoordinates p_71063_1_, boolean p_71063_2_) {
      this.delegate.setSpawnChunk(p_71063_1_, p_71063_2_);
   }

   public void triggerAchievement(StatBase p_71029_1_) {
      this.delegate.triggerAchievement(p_71029_1_);
   }

   public void jump() {
      this.delegate.jump();
   }

   public void moveEntityWithHeading(float p_70612_1_, float p_70612_2_) {
      this.delegate.moveEntityWithHeading(p_70612_1_, p_70612_2_);
   }

   public void updateRiderPosition() {
      this.delegate.updateRiderPosition();
   }

   public float getAIMoveSpeed() {
      return this.delegate.getAIMoveSpeed();
   }

   public void addMovementStat(double p_71000_1_, double p_71000_3_, double p_71000_5_) {
      this.delegate.addMovementStat(p_71000_1_, p_71000_3_, p_71000_5_);
   }

   public double getMountedYOffset() {
      return this.delegate.getMountedYOffset();
   }

   public float getCollisionBorderSize() {
      return this.delegate.getCollisionBorderSize();
   }

   public void setInPortal() {
      this.delegate.setInPortal();
   }

   public void setVelocity(double p_70016_1_, double p_70016_3_, double p_70016_5_) {
      this.delegate.setVelocity(p_70016_1_, p_70016_3_, p_70016_5_);
   }

   public void setPositionAndRotation2(double p_70056_1_, double p_70056_3_, double p_70056_5_, float p_70056_7_, float p_70056_8_, int p_70056_9_) {
      this.delegate.setPositionAndRotation2(p_70056_1_, p_70056_3_, p_70056_5_, p_70056_7_, p_70056_8_, p_70056_9_);
   }

   public boolean isBurning() {
      return this.delegate.isBurning();
   }

   public void onKillEntity(EntityLivingBase p_70074_1_) {
      this.delegate.onKillEntity(p_70074_1_);
   }

   public boolean isRiding() {
      return this.delegate.isRiding();
   }

   public void setJumping(boolean p_70637_1_) {
      this.delegate.setJumping(p_70637_1_);
   }

   public void setSneaking(boolean p_70095_1_) {
      this.delegate.setSneaking(p_70095_1_);
   }

   public void setInWeb() {
      this.delegate.setInWeb();
   }

   public boolean isSprinting() {
      return this.delegate.isSprinting();
   }

   public IIcon getItemIcon(ItemStack p_70620_1_, int p_70620_2_) {
      return this.delegate.getItemIcon(p_70620_1_, p_70620_2_);
   }

   public boolean isInvisible() {
      return this.delegate.isInvisible();
   }

   public boolean canEntityBeSeen(Entity p_70685_1_) {
      return this.delegate.canEntityBeSeen(p_70685_1_);
   }

   public void setInvisible(boolean p_82142_1_) {
      this.delegate.setInvisible(p_82142_1_);
   }

   public boolean isEating() {
      return this.delegate.isEating();
   }

   public void setEating(boolean p_70019_1_) {
      this.delegate.setEating(p_70019_1_);
   }

   public Vec3 getLookVec() {
      return this.delegate.getLookVec();
   }

   public Vec3 getLook(float p_70676_1_) {
      return this.delegate.getLook(p_70676_1_);
   }

   public ItemStack getCurrentArmor(int p_82169_1_) {
      return this.delegate.getCurrentArmor(p_82169_1_);
   }

   public void addExperience(int p_71023_1_) {
      this.delegate.addExperience(p_71023_1_);
   }

   public int getAir() {
      return this.delegate.getAir();
   }

   public void setAir(int p_70050_1_) {
      this.delegate.setAir(p_70050_1_);
   }

   public void addExperienceLevel(int p_82242_1_) {
      this.delegate.addExperienceLevel(p_82242_1_);
   }

   public void onStruckByLightning(EntityLightningBolt p_70077_1_) {
      this.delegate.onStruckByLightning(p_70077_1_);
   }

   public float getSwingProgress(float p_70678_1_) {
      return this.delegate.getSwingProgress(p_70678_1_);
   }

   public int xpBarCap() {
      return this.delegate.xpBarCap();
   }

   public void addExhaustion(float p_71020_1_) {
      this.delegate.addExhaustion(p_71020_1_);
   }

   public FoodStats getFoodStats() {
      return this.delegate.getFoodStats();
   }

   public MovingObjectPosition rayTrace(double p_70614_1_, float p_70614_3_) {
      return this.delegate.rayTrace(p_70614_1_, p_70614_3_);
   }

   public boolean canEat(boolean p_71043_1_) {
      return this.delegate.canEat(p_71043_1_);
   }

   public boolean shouldHeal() {
      return this.delegate.shouldHeal();
   }

   public void setItemInUse(ItemStack p_71008_1_, int p_71008_2_) {
      this.delegate.setItemInUse(p_71008_1_, p_71008_2_);
   }

   public boolean canBeCollidedWith() {
      return this.delegate.canBeCollidedWith();
   }

   public boolean canBePushed() {
      return this.delegate.canBePushed();
   }

   public boolean isCurrentToolAdventureModeExempt(int p_82246_1_, int p_82246_2_, int p_82246_3_) {
      return this.delegate.isCurrentToolAdventureModeExempt(p_82246_1_, p_82246_2_, p_82246_3_);
   }

   public float getRotationYawHead() {
      return this.delegate.getRotationYawHead();
   }

   public void setRotationYawHead(float p_70034_1_) {
      this.delegate.setRotationYawHead(p_70034_1_);
   }

   public Entity[] getParts() {
      return this.delegate.getParts();
   }

   public boolean canPlayerEdit(int p_82247_1_, int p_82247_2_, int p_82247_3_, int p_82247_4_, ItemStack p_82247_5_) {
      return this.delegate.canPlayerEdit(p_82247_1_, p_82247_2_, p_82247_3_, p_82247_4_, p_82247_5_);
   }

   public boolean isOnSameTeam(EntityLivingBase p_142014_1_) {
      return this.delegate.isOnSameTeam(p_142014_1_);
   }

   public boolean isEntityEqual(Entity p_70028_1_) {
      return this.delegate.isEntityEqual(p_70028_1_);
   }

   public boolean isOnTeam(Team p_142012_1_) {
      return this.delegate.isOnTeam(p_142012_1_);
   }

   public void curePotionEffects(ItemStack curativeItem) {
      this.delegate.curePotionEffects(curativeItem);
   }

   public boolean canAttackWithItem() {
      return this.delegate.canAttackWithItem();
   }

   public boolean hitByEntity(Entity p_85031_1_) {
      return this.delegate.hitByEntity(p_85031_1_);
   }

   public String toString() {
      return this.delegate.toString();
   }

   public boolean getAlwaysRenderNameTagForRender() {
      return this.delegate.getAlwaysRenderNameTagForRender();
   }

   public void clonePlayer(EntityPlayer p_71049_1_, boolean p_71049_2_) {
      this.delegate.clonePlayer(p_71049_1_, p_71049_2_);
   }

   public boolean shouldRiderFaceForward(EntityPlayer player) {
      return this.delegate.shouldRiderFaceForward(player);
   }

   public boolean isEntityInvulnerable() {
      return this.delegate.isEntityInvulnerable();
   }

   public void copyLocationAndAnglesFrom(Entity p_82149_1_) {
      this.delegate.copyLocationAndAnglesFrom(p_82149_1_);
   }

   public void func_152111_bt() {
      this.delegate.func_152111_bt();
   }

   public void func_152112_bu() {
      this.delegate.func_152112_bu();
   }

   public void copyDataFrom(Entity p_82141_1_, boolean p_82141_2_) {
      this.delegate.copyDataFrom(p_82141_1_, p_82141_2_);
   }

   public void travelToDimension(int p_71027_1_) {
      this.delegate.travelToDimension(p_71027_1_);
   }

   public void setGameType(GameType p_71033_1_) {
      if(this.delegate != null) {
         this.delegate.setGameType(p_71033_1_);
      }

      super.setGameType(p_71033_1_);
   }

   public String getCommandSenderName() {
      return this.delegate != null?this.delegate.getCommandSenderName():super.getCommandSenderName();
   }

   public World getEntityWorld() {
      return this.delegate != null?this.delegate.getEntityWorld():super.getEntityWorld();
   }

   public InventoryEnderChest getInventoryEnderChest() {
      return this.delegate.getInventoryEnderChest();
   }

   public ItemStack getEquipmentInSlot(int p_71124_1_) {
      return this.delegate.getEquipmentInSlot(p_71124_1_);
   }

   public ItemStack getHeldItem() {
      return this.delegate.getHeldItem();
   }

   public float func_145772_a(Explosion p_145772_1_, World p_145772_2_, int p_145772_3_, int p_145772_4_, int p_145772_5_, Block p_145772_6_) {
      return this.delegate.func_145772_a(p_145772_1_, p_145772_2_, p_145772_3_, p_145772_4_, p_145772_5_, p_145772_6_);
   }

   public void setCurrentItemOrArmor(int p_70062_1_, ItemStack p_70062_2_) {
      this.delegate.setCurrentItemOrArmor(p_70062_1_, p_70062_2_);
   }

   public boolean func_145774_a(Explosion p_145774_1_, World p_145774_2_, int p_145774_3_, int p_145774_4_, int p_145774_5_, Block p_145774_6_, float p_145774_7_) {
      return this.delegate.func_145774_a(p_145774_1_, p_145774_2_, p_145774_3_, p_145774_4_, p_145774_5_, p_145774_6_, p_145774_7_);
   }

   public boolean isInvisibleToPlayer(EntityPlayer p_98034_1_) {
      return this.delegate.isInvisibleToPlayer(p_98034_1_);
   }

   public int getMaxSafePointTries() {
      return this.delegate.getMaxSafePointTries();
   }

   public int getTeleportDirection() {
      return this.delegate.getTeleportDirection();
   }

   public boolean doesEntityNotTriggerPressurePlate() {
      return this.delegate.doesEntityNotTriggerPressurePlate();
   }

   public void addEntityCrashInfo(CrashReportCategory p_85029_1_) {
      this.delegate.addEntityCrashInfo(p_85029_1_);
   }

   public ItemStack[] getLastActiveItems() {
      return this.delegate.getLastActiveItems();
   }

   public boolean getHideCape() {
      return this.delegate.getHideCape();
   }

   public boolean isPushedByWater() {
      return this.delegate.isPushedByWater();
   }

   public Scoreboard getWorldScoreboard() {
      return this.delegate.getWorldScoreboard();
   }

   public Team getTeam() {
      return this.delegate.getTeam();
   }

   public IChatComponent func_145748_c_() {
      return this.delegate.func_145748_c_();
   }

   public void setAbsorptionAmount(float p_110149_1_) {
      this.delegate.setAbsorptionAmount(p_110149_1_);
   }

   public float getAbsorptionAmount() {
      return this.delegate.getAbsorptionAmount();
   }

   public boolean canRenderOnFire() {
      return this.delegate.canRenderOnFire();
   }

   public UUID getUniqueID() {
      return this.delegate.getUniqueID();
   }

   public void func_145781_i(int p_145781_1_) {
      if(this.delegate != null) {
         this.delegate.func_145781_i(p_145781_1_);
      }

   }

   public NBTTagCompound getEntityData() {
      return this.delegate.getEntityData();
   }

   public boolean shouldRiderSit() {
      return this.delegate.shouldRiderSit();
   }

   public ItemStack getPickedResult(MovingObjectPosition target) {
      return this.delegate.getPickedResult(target);
   }

   public void openGui(Object mod, int modGuiId, World world, int x, int y, int z) {
      this.delegate.openGui(mod, modGuiId, world, x, y, z);
   }

   public Vec3 getPosition(float par1) {
      return this.delegate.getPosition(par1);
   }

   public UUID getPersistentID() {
      return this.delegate.getPersistentID();
   }

   public ChunkCoordinates getBedLocation(int dimension) {
      return this.delegate.getBedLocation(dimension);
   }

   public boolean shouldRenderInPass(int pass) {
      return this.delegate.shouldRenderInPass(pass);
   }

   public boolean isCreatureType(EnumCreatureType type, boolean forSpawnCount) {
      return this.delegate.isCreatureType(type, forSpawnCount);
   }

   public boolean isSpawnForced(int dimension) {
      return this.delegate != null?this.delegate.isSpawnForced(dimension):super.isSpawnForced();
   }

   public String registerExtendedProperties(String identifier, IExtendedEntityProperties properties) {
      return this.delegate.registerExtendedProperties(identifier, properties);
   }

   public void setSpawnChunk(ChunkCoordinates chunkCoordinates, boolean forced, int dimension) {
      this.delegate.setSpawnChunk(chunkCoordinates, forced, dimension);
   }

   public float getDefaultEyeHeight() {
      return this.delegate != null?this.delegate.getDefaultEyeHeight():super.getDefaultEyeHeight();
   }

   public IExtendedEntityProperties getExtendedProperties(String identifier) {
      return this.delegate.getExtendedProperties(identifier);
   }

   public String getDisplayName() {
      return this.delegate != null?this.delegate.getDisplayName():super.getDisplayName();
   }

   public boolean canRiderInteract() {
      return this.delegate.canRiderInteract();
   }

   public void refreshDisplayName() {
      if(this.delegate != null) {
         this.delegate.refreshDisplayName();
      }

   }

   public boolean shouldDismountInWater(Entity rider) {
      return this.delegate.shouldDismountInWater(rider);
   }
}
