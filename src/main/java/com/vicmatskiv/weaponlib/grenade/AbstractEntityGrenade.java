package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.EntityBounceable;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class AbstractEntityGrenade extends EntityBounceable {
   private static final String TAG_ENTITY_ITEM = "entity_item";
   protected ItemGrenade itemGrenade;

   protected AbstractEntityGrenade(ModContext modContext, ItemGrenade itemGrenade, EntityLivingBase thrower, float velocity, float gravityVelocity, float rotationSlowdownFactor) {
      super(modContext, CompatibilityProvider.compatibility.world(thrower), thrower, velocity, gravityVelocity, rotationSlowdownFactor);
      this.itemGrenade = itemGrenade;
   }

   public AbstractEntityGrenade(World world) {
      super(world);
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      Item item = Item.getItemById(tagCompound.getInteger("entity_item"));
      if(item instanceof ItemGrenade) {
         this.itemGrenade = (ItemGrenade)item;
      }

   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entity_item", Item.getIdFromItem(this.itemGrenade));
   }

   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.getIdFromItem(this.itemGrenade));
   }

   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      Item item = Item.getItemById(buffer.readInt());
      if(item instanceof ItemGrenade) {
         this.itemGrenade = (ItemGrenade)item;
      }

   }

   public void onUpdate() {
      super.onUpdate();
      this.onGrenadeUpdate();
   }

   protected abstract void onGrenadeUpdate();

   public void onBounce(CompatibleRayTraceResult movingobjectposition) {
      if(movingobjectposition.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK && this.itemGrenade != null) {
         CompatibleSound bounceHardSound = this.itemGrenade.getBounceHardSound();
         if(bounceHardSound != null) {
            CompatibleBlockState bounceSoftSound = CompatibilityProvider.compatibility.getBlockAtPosition(CompatibilityProvider.compatibility.world(this), movingobjectposition);
            if(CompatibilityProvider.compatibility.madeFromHardMaterial(bounceSoftSound)) {
               CompatibilityProvider.compatibility.playSoundAtEntity(this, bounceHardSound, 2.0F / ((float)this.bounceCount + 1.0F), 1.0F);
            }
         }

         CompatibleSound bounceSoftSound1 = this.itemGrenade.getBounceSoftSound();
         if(bounceSoftSound1 != null) {
            CompatibleBlockState blockState = CompatibilityProvider.compatibility.getBlockAtPosition(CompatibilityProvider.compatibility.world(this), movingobjectposition);
            if(!CompatibilityProvider.compatibility.madeFromHardMaterial(blockState)) {
               CompatibilityProvider.compatibility.playSoundAtEntity(this, bounceSoftSound1, 1.0F / ((float)this.bounceCount + 1.0F), 1.0F);
            }
         }
      }

   }

   public ItemGrenade getItemGrenade() {
      return this.itemGrenade;
   }

   public boolean canCollideWithBlock(Block block, CompatibleBlockState metadata) {
      return !CompatibilityProvider.compatibility.isBlockPenetratableByGrenades(block) && super.canCollideWithBlock(block, metadata);
   }
}
