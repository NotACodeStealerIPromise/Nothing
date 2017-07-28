package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.EntityProjectile;
import com.vicmatskiv.weaponlib.Explosion;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTargetPoint;
import com.vicmatskiv.weaponlib.config.Projectiles;
import com.vicmatskiv.weaponlib.particle.SpawnParticleMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WeaponSpawnEntity extends EntityProjectile {
   private static final Logger logger = LogManager.getLogger(WeaponSpawnEntity.class);
   private static final String TAG_ENTITY_ITEM = "entityItem";
   private static final String TAG_DAMAGE = "damage";
   private static final String TAG_EXPLOSION_RADIUS = "explosionRadius";
   private float explosionRadius;
   private float damage = 6.0F;
   private Weapon weapon;

   public WeaponSpawnEntity(World world) {
      super(world);
   }

   public WeaponSpawnEntity(Weapon weapon, World world, EntityLivingBase player, float speed, float gravityVelocity, float inaccuracy, float damage, float explosionRadius, Material... damageableBlockMaterials) {
      super(world, player, speed, gravityVelocity, inaccuracy);
      this.weapon = weapon;
      this.damage = damage;
      this.explosionRadius = explosionRadius;
   }

   public void onUpdate() {
      super.onUpdate();
   }

   protected void onImpact(CompatibleRayTraceResult position) {
      if(!CompatibilityProvider.compatibility.world(this).isRemote) {
         if(this.weapon != null) {
            if(this.explosionRadius > 0.0F) {
               Explosion.createServerSideExplosion(this.weapon.getModContext(), CompatibilityProvider.compatibility.world(this), this, position.getHitVec().getXCoord(), position.getHitVec().getYCoord(), position.getHitVec().getZCoord(), this.explosionRadius, false, true);
            } else if(position.getEntityHit() != null) {
               Projectiles projectilesConfig = this.weapon.getModContext().getConfigurationManager().getProjectiles();
               if(this.getThrower() == null || projectilesConfig.isKnockbackOnHit() != null && !projectilesConfig.isKnockbackOnHit().booleanValue()) {
                  position.getEntityHit().attackEntityFrom(CompatibilityProvider.compatibility.genericDamageSource(), this.damage);
               } else {
                  position.getEntityHit().attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), this.damage);
               }

               position.getEntityHit().hurtResistantTime = 0;
               Entity var10000 = position.getEntityHit();
               var10000.prevRotationYaw = (float)((double)var10000.prevRotationYaw - 0.3D);
               logger.debug("Hit entity {}", new Object[]{position.getEntityHit()});
               CompatibleTargetPoint point = new CompatibleTargetPoint(position.getEntityHit().dimension, this.posX, this.posY, this.posZ, 100.0D);
               double magnitude = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ) + 2.0D;
               Float bleedingCoefficient = projectilesConfig.getBleedingOnHit();
               if(bleedingCoefficient != null && bleedingCoefficient.floatValue() > 0.0F) {
                  int count = (int)((float)this.getParticleCount(this.damage) * bleedingCoefficient.floatValue());
                  logger.debug("Generating {} particle(s) per damage {}", new Object[]{Integer.valueOf(count), Float.valueOf(this.damage)});
                  CompatibleChannel var8 = this.weapon.getModContext().getChannel();
                  SpawnParticleMessage.ParticleType var10003 = SpawnParticleMessage.ParticleType.BLOOD;
                  double var10006 = this.motionX / magnitude;
                  double var10005 = position.getEntityHit().posX - var10006;
                  double var10007 = this.motionY / magnitude;
                  var10006 = position.getEntityHit().posY - var10007;
                  double var10008 = this.motionZ / magnitude;
                  var8.sendToAllAround(new SpawnParticleMessage(var10003, count, var10005, var10006, position.getEntityHit().posZ - var10008), point);
               }
            } else if(position.getTypeOfHit() == CompatibleRayTraceResult.Type.BLOCK) {
               this.weapon.onSpawnEntityBlockImpact(CompatibilityProvider.compatibility.world(this), (EntityPlayer)null, this, position);
            }

            this.setDead();
         }
      }
   }

   public void writeSpawnData(ByteBuf buffer) {
      super.writeSpawnData(buffer);
      buffer.writeInt(Item.getIdFromItem(this.weapon));
      buffer.writeFloat(this.damage);
      buffer.writeFloat(this.explosionRadius);
   }

   public void readSpawnData(ByteBuf buffer) {
      super.readSpawnData(buffer);
      this.weapon = (Weapon)Item.getItemById(buffer.readInt());
      this.damage = buffer.readFloat();
      this.explosionRadius = buffer.readFloat();
   }

   public void readEntityFromNBT(NBTTagCompound tagCompound) {
      super.readEntityFromNBT(tagCompound);
      Item item = Item.getItemById(tagCompound.getInteger("entityItem"));
      if(item instanceof Weapon) {
         this.weapon = (Weapon)item;
      }

      this.damage = tagCompound.getFloat("damage");
      this.explosionRadius = tagCompound.getFloat("explosionRadius");
   }

   public void writeEntityToNBT(NBTTagCompound tagCompound) {
      super.writeEntityToNBT(tagCompound);
      tagCompound.setInteger("entityItem", Item.getIdFromItem(this.weapon));
      tagCompound.setFloat("damage", this.damage);
      tagCompound.setFloat("explosionRadius", this.explosionRadius);
   }

   Weapon getWeapon() {
      return this.weapon;
   }

   boolean isDamageableEntity(Entity entity) {
      return false;
   }

   int getParticleCount(float damage) {
      return (int)(-0.11D * (double)(damage - 30.0F) * (double)(damage - 30.0F) + 100.0D);
   }

   public boolean canCollideWithBlock(Block block, CompatibleBlockState metadata) {
      return !CompatibilityProvider.compatibility.isBlockPenetratableByBullets(block) && super.canCollideWithBlock(block, metadata);
   }
}
