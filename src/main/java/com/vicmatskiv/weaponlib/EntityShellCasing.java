package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.EntityProjectile;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMathHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import io.netty.buffer.ByteBuf;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityShellCasing extends EntityProjectile {
   private static final Logger logger = LogManager.getLogger(EntityShellCasing.class);
   private static final String TAG_ENTITY_ITEM = "entity_item";
   static final float DEFAULT_INACCURACY = 1.0F;
   private Random random = new Random();
   private Weapon weapon;
   private PlayerWeaponInstance weaponInstance;
   private float initialYaw;
   private float initialPitch;
   private float xRotation;
   private float yRotation;
   private float zRotation;
   private float xRotationChange;
   private float yRotationChange;
   private float zRotationChange;
   private float rotationSlowdownFactor = 0.95F;
   private float maxRotationChange = 30.0F;

   public EntityShellCasing(World world) {
      super(world);
      this.setRotations();
   }

   public EntityShellCasing(PlayerWeaponInstance weaponInstance, World world, EntityLivingBase player, float velocity, float gravityVelocity, float inaccuracy) {
      super(world, player, velocity, gravityVelocity, inaccuracy);
      this.weapon = weaponInstance.getWeapon();
      this.weaponInstance = weaponInstance;
   }

   public void setPositionAndDirection() {
      this.setSize(0.001F, 0.001F);
      float forwardOffset = CompatibilityProvider.compatibility.getCompatibleShellCasingForwardOffset() + this.weapon.getShellCasingForwardOffset();
      float sideOffset;
      if(this.weapon.getShellCasingEjectDirection() == Weapon.ShellCasingEjectDirection.RIGHT) {
         sideOffset = this.weaponInstance.isAimed()?this.weapon.getShellCasingSideOffsetAimed():this.weapon.getShellCasingSideOffset();
      } else {
         sideOffset = this.weaponInstance.isAimed()?-0.1F:0.0F;
      }

      float yOffset = this.weapon.getShellCasingVerticalOffset() + (this.thrower.isSneaking()?-0.1F:0.0F);
      this.setLocationAndAngles(this.thrower.posX, this.thrower.posY + (double)this.thrower.getEyeHeight() + (double)yOffset, this.thrower.posZ, this.thrower.rotationYaw, this.thrower.rotationPitch);
      this.posX -= (double)(CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * sideOffset) + (double)(CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * forwardOffset);
      this.posY += (double)(-CompatibleMathHelper.sin(this.rotationPitch / 180.0F * 3.1415927F) * forwardOffset);
      this.posZ -= (double)(CompatibleMathHelper.sin(this.rotationYaw / 180.0F * 3.1415927F) * sideOffset) - (double)(CompatibleMathHelper.cos(this.rotationYaw / 180.0F * 3.1415927F) * CompatibleMathHelper.cos(this.rotationPitch / 180.0F * 3.1415927F) * forwardOffset);
      this.setPosition(this.posX, this.posY, this.posZ);
      float f = this.velocity;
      float adjustedRotationYaw;
      if(this.weapon.getShellCasingEjectDirection() == Weapon.ShellCasingEjectDirection.RIGHT) {
         adjustedRotationYaw = this.rotationYaw + (this.weaponInstance.isAimed()?-10.0F:-30.0F);
      } else {
         adjustedRotationYaw = this.rotationYaw + 0.0F;
      }

      int directionSignum = this.weapon.getShellCasingEjectDirection() == Weapon.ShellCasingEjectDirection.RIGHT?1:-1;
      this.motionX = (double)directionSignum * -((double)(CompatibleMathHelper.cos(adjustedRotationYaw / 180.0F * 3.1415927F) * f));
      this.motionZ = (double)directionSignum * (double)(-CompatibleMathHelper.sin(adjustedRotationYaw / 180.0F * 3.1415927F) * f);
      this.motionY = 0.0D;
      this.initialYaw = this.rotationYaw;
      this.initialPitch = this.rotationPitch;
      this.setThrowableHeading(this.motionX, this.motionY, this.motionZ, this.velocity, this.inaccuracy);
   }

   private void setRotations() {
      this.xRotationChange = this.maxRotationChange * (float)this.random.nextGaussian();
      this.yRotationChange = this.maxRotationChange * (float)this.random.nextGaussian();
      this.zRotationChange = this.maxRotationChange * (float)this.random.nextGaussian();
   }

   public void onUpdate() {
      super.onUpdate();
      this.xRotation += this.xRotationChange;
      this.yRotation += this.yRotationChange;
      this.zRotation += this.zRotationChange;
      this.xRotationChange *= this.rotationSlowdownFactor;
      this.yRotationChange *= this.rotationSlowdownFactor;
      this.zRotationChange *= this.rotationSlowdownFactor;
   }

   protected void onImpact(CompatibleRayTraceResult position) {
      if(!CompatibilityProvider.compatibility.world(this).isRemote) {
         this.setDead();
      }

   }

   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.getIdFromItem(this.weapon));
      buffer.writeFloat(this.initialYaw);
      buffer.writeFloat(this.initialPitch);
   }

   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (Weapon)Item.getItemById(buffer.readInt());
      this.initialYaw = buffer.readFloat();
      this.initialPitch = buffer.readFloat();
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      Item item = Item.getItemById(tagCompound.getInteger("entity_item"));
      if(item instanceof Weapon) {
         this.weapon = (Weapon)item;
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entity_item", Item.getIdFromItem(this.weapon));
   }

   Weapon getWeapon() {
      return this.weapon;
   }

   boolean isDamageableEntity(Entity entity) {
      return false;
   }

   public float getInitialYaw() {
      return this.initialYaw;
   }

   public float getInitialPitch() {
      return this.initialPitch;
   }

   public float getXRotation() {
      return this.initialPitch - this.xRotation;
   }

   public float getYRotation() {
      return this.yRotation - this.initialYaw - 90.0F;
   }

   public float getZRotation() {
      return this.zRotation;
   }

   public void setDead() {
      super.setDead();
      logger.debug("{} despawned", new Object[]{this});
   }
}
