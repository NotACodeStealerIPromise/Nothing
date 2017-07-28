package com.vicmatskiv.weaponlib;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.vicmatskiv.weaponlib.ExplosionMessage;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleAxisAlignedBB;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockPos;
import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleMathHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import com.vicmatskiv.weaponlib.particle.ExplosionSmokeFX;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class Explosion {
   private ModContext modContext;
   private final boolean isFlaming;
   private final boolean isSmoking;
   private final Random explosionRNG;
   private final World world;
   private final double explosionX;
   private final double explosionY;
   private final double explosionZ;
   private final Entity exploder;
   private final float explosionSize;
   private final List affectedBlockPositions;
   private final Map playerKnockbackMap;
   private final CompatibleVec3 position;

   public static void createServerSideExplosion(ModContext modContext, World world, Entity entity, double posX, double posY, double posZ, float explosionStrength, boolean isFlaming, boolean isSmoking) {
      Float damageCoefficient = modContext.getConfigurationManager().getExplosions().getDamage();
      explosionStrength *= damageCoefficient.floatValue();
      Explosion explosion = new Explosion(modContext, world, entity, posX, posY, posZ, explosionStrength, isFlaming, isSmoking);
      explosion.doExplosionA();
      explosion.doExplosionB(false);
      if(!isSmoking) {
         explosion.clearAffectedBlockPositions();
      }

      Iterator var14 = world.playerEntities.iterator();

      while(var14.hasNext()) {
         Object o = var14.next();
         EntityPlayer player = (EntityPlayer)o;
         if(player.getDistanceSq(posX, posY, posZ) < 4096.0D) {
            modContext.getChannel().getChannel().sendTo(new ExplosionMessage(posX, posY, posZ, explosionStrength, explosion.getAffectedBlockPositions(), (CompatibleVec3)explosion.getPlayerKnockbackMap().get(player)), (EntityPlayerMP)player);
         }
      }

   }

   public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size, List affectedPositions) {
      this(modContext, worldIn, entityIn, x, y, z, size, false, true, affectedPositions);
   }

   public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking, List affectedPositions) {
      this(modContext, worldIn, entityIn, x, y, z, size, flaming, smoking);
      this.affectedBlockPositions.addAll(affectedPositions);
   }

   public Explosion(ModContext modContext, World worldIn, Entity entityIn, double x, double y, double z, float size, boolean flaming, boolean smoking) {
      this.modContext = modContext;
      this.explosionRNG = new Random();
      this.affectedBlockPositions = Lists.newArrayList();
      this.playerKnockbackMap = Maps.newHashMap();
      this.world = worldIn;
      this.exploder = entityIn;
      this.explosionSize = size;
      this.explosionX = x;
      this.explosionY = y;
      this.explosionZ = z;
      this.isFlaming = flaming;
      this.isSmoking = smoking;
      this.position = new CompatibleVec3(this.explosionX, this.explosionY, this.explosionZ);
   }

   public double getExplosionX() {
      return this.explosionX;
   }

   public double getExplosionY() {
      return this.explosionY;
   }

   public double getExplosionZ() {
      return this.explosionZ;
   }

   public World getWorld() {
      return this.world;
   }

   public Entity getExploder() {
      return this.exploder;
   }

   public float getExplosionSize() {
      return this.explosionSize;
   }

   public void doExplosionA() {
      HashSet set = Sets.newHashSet();

      int k1;
      int l1;
      for(int f3 = 0; f3 < 16; ++f3) {
         for(k1 = 0; k1 < 16; ++k1) {
            for(l1 = 0; l1 < 16; ++l1) {
               if(f3 == 0 || f3 == 15 || k1 == 0 || k1 == 15 || l1 == 0 || l1 == 15) {
                  double i2 = (double)((float)f3 / 15.0F * 2.0F - 1.0F);
                  double j2 = (double)((float)k1 / 15.0F * 2.0F - 1.0F);
                  double list = (double)((float)l1 / 15.0F * 2.0F - 1.0F);
                  double k2 = Math.sqrt(i2 * i2 + j2 * j2 + list * list);
                  i2 /= k2;
                  j2 /= k2;
                  list /= k2;
                  float d12 = this.explosionSize * (0.7F + this.world.rand.nextFloat() * 0.6F);
                  double d4 = this.explosionX;
                  double d6 = this.explosionY;

                  for(double d8 = this.explosionZ; d12 > 0.0F; d12 -= 0.22500001F) {
                     CompatibleBlockPos blockpos = new CompatibleBlockPos((int)d4, (int)d6, (int)d8);
                     CompatibleBlockState d13 = CompatibilityProvider.compatibility.getBlockAtPosition(this.world, blockpos);
                     if(!CompatibilityProvider.compatibility.isAirBlock(d13)) {
                        float f2 = this.exploder != null?CompatibilityProvider.compatibility.getExplosionResistance(this.world, this.exploder, this, blockpos, d13):CompatibilityProvider.compatibility.getExplosionResistance(this.world, d13, blockpos, (Entity)null, this);
                        d12 -= (f2 + 0.3F) * 0.3F;
                     }

                     if(d12 > 0.0F && (this.exploder == null || CompatibilityProvider.compatibility.verifyExplosion(this.world, this.exploder, this, blockpos, d13, d12))) {
                        set.add(blockpos);
                     }

                     d4 += i2 * 0.30000001192092896D;
                     d6 += j2 * 0.30000001192092896D;
                     d8 += list * 0.30000001192092896D;
                  }
               }
            }
         }
      }

      this.affectedBlockPositions.addAll(set);
      float var30 = this.explosionSize * 2.0F;
      k1 = CompatibleMathHelper.floor_double(this.explosionX - (double)var30 - 1.0D);
      l1 = CompatibleMathHelper.floor_double(this.explosionX + (double)var30 + 1.0D);
      int var31 = CompatibleMathHelper.floor_double(this.explosionY - (double)var30 - 1.0D);
      int i1 = CompatibleMathHelper.floor_double(this.explosionY + (double)var30 + 1.0D);
      int var32 = CompatibleMathHelper.floor_double(this.explosionZ - (double)var30 - 1.0D);
      int j1 = CompatibleMathHelper.floor_double(this.explosionZ + (double)var30 + 1.0D);
      List var33 = CompatibilityProvider.compatibility.getEntitiesWithinAABBExcludingEntity(this.world, this.exploder, new CompatibleAxisAlignedBB((double)k1, (double)var31, (double)var32, (double)l1, (double)i1, (double)j1));
      CompatibleVec3 vec3d = new CompatibleVec3(this.explosionX, this.explosionY, this.explosionZ);

      for(int var34 = 0; var34 < var33.size(); ++var34) {
         Entity entity = (Entity)var33.get(var34);
         if(!CompatibilityProvider.compatibility.isImmuneToExplosions(entity)) {
            double var35 = entity.getDistance(this.explosionX, this.explosionY, this.explosionZ) / (double)var30;
            if(var35 <= 1.0D) {
               double d5 = entity.posX - this.explosionX;
               double d7 = entity.posY + (double)entity.getEyeHeight() - this.explosionY;
               double d9 = entity.posZ - this.explosionZ;
               double var36 = (double)CompatibleMathHelper.sqrt_double(d5 * d5 + d7 * d7 + d9 * d9);
               if(var36 != 0.0D) {
                  d5 /= var36;
                  d7 /= var36;
                  d9 /= var36;
                  double d14 = CompatibilityProvider.compatibility.getBlockDensity(this.world, vec3d, CompatibilityProvider.compatibility.getBoundingBox(entity));
                  double d10 = (1.0D - var35) * d14;
                  entity.attackEntityFrom(CompatibilityProvider.compatibility.getDamageSource(this), (float)((int)((d10 * d10 + d10) / 2.0D * 7.0D * (double)var30 + 1.0D)));
                  double d11 = 1.0D;
                  if(entity instanceof EntityLivingBase) {
                     d11 = CompatibilityProvider.compatibility.getBlastDamageReduction((EntityLivingBase)entity, d10);
                  }

                  entity.motionX += d5 * d11;
                  entity.motionY += d7 * d11;
                  entity.motionZ += d9 * d11;
                  if(entity instanceof EntityPlayer) {
                     EntityPlayer entityplayer = (EntityPlayer)entity;
                     if(!CompatibilityProvider.compatibility.isSpectator(entityplayer) && (!CompatibilityProvider.compatibility.isCreative(entityplayer) || !entityplayer.capabilities.isFlying)) {
                        this.playerKnockbackMap.put(entityplayer, new CompatibleVec3(d5 * d10, d7 * d10, d9 * d10));
                     }
                  }
               }
            }
         }
      }

   }

   public void doExplosionB(boolean spawnParticles) {
      CompatibilityProvider.compatibility.playSound(this.world, this.explosionX, this.explosionY, this.explosionZ, this.modContext.getExplosionSound(), 4.0F, (1.0F + (this.world.rand.nextFloat() - this.world.rand.nextFloat()) * 0.2F) * 0.7F);
      Iterator i;
      CompatibleBlockPos blockpos1;
      if(this.isSmoking) {
         i = this.affectedBlockPositions.iterator();

         while(i.hasNext()) {
            blockpos1 = (CompatibleBlockPos)i.next();
            CompatibleBlockState blockState = CompatibilityProvider.compatibility.getBlockAtPosition(this.world, blockpos1);
            if(spawnParticles) {
               for(int pY = 0; pY < 3; ++pY) {
                  double d0 = (double)((float)blockpos1.getBlockPosX() + this.world.rand.nextFloat());
                  double d1 = (double)((float)blockpos1.getBlockPosY() + this.world.rand.nextFloat());
                  double d2 = (double)((float)blockpos1.getBlockPosZ() + this.world.rand.nextFloat());
                  double d3 = d0 - this.explosionX;
                  double d4 = d1 - this.explosionY;
                  double d5 = d2 - this.explosionZ;
                  double d6 = (double)CompatibleMathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
                  d3 /= d6;
                  d4 /= d6;
                  d5 /= d6;
                  double d7 = 4.0D / (d6 / (double)this.explosionSize + 0.1D);
                  d7 *= (double)(this.world.rand.nextFloat() * this.world.rand.nextFloat() + 0.3F);
                  d3 *= d7;
                  d4 *= d7;
                  d5 *= d7;
                  this.modContext.getEffectManager().spawnExplosionParticle((d0 + this.explosionX) / 2.0D, (d1 + this.explosionY) / 2.0D, (d2 + this.explosionZ) / 2.0D, d3 / 2.0D, d4 * 2.0D, d5 / 2.0D, 1.5F * this.world.rand.nextFloat(), 15 + (int)(this.world.rand.nextFloat() * 10.0F));
               }
            }

            if(!CompatibilityProvider.compatibility.isAirBlock(blockState)) {
               if(CompatibilityProvider.compatibility.canDropBlockFromExplosion(blockState, this)) {
                  CompatibilityProvider.compatibility.dropBlockAsItemWithChance(this.world, blockState, blockpos1, this.modContext.getConfigurationManager().getExplosions().getDropBlockChance().floatValue() * (1.0F / this.explosionSize), 0);
               }

               CompatibilityProvider.compatibility.onBlockExploded(this.world, blockState, blockpos1, this);
            }
         }

         if(spawnParticles) {
            for(int var22 = 0; var22 < 15; ++var22) {
               double var23 = this.explosionX + this.world.rand.nextGaussian() * 0.8D;
               double var24 = this.explosionY + this.world.rand.nextGaussian() * 0.9D;
               double pZ = this.explosionZ + this.world.rand.nextGaussian() * 0.8D;
               double motionX = this.world.rand.nextGaussian() * 0.001D;
               double motionY = this.world.rand.nextGaussian() * 1.0E-4D;
               double motionZ = this.world.rand.nextGaussian() * 0.001D;
               this.modContext.getEffectManager().spawnExplosionSmoke(var23, var24, pZ, motionX, motionY, motionZ, 1.0F, 250 + (int)(this.world.rand.nextFloat() * 30.0F), ExplosionSmokeFX.Behavior.EXPLOSION);
            }
         }
      }

      if(this.isFlaming) {
         i = this.affectedBlockPositions.iterator();

         while(i.hasNext()) {
            blockpos1 = (CompatibleBlockPos)i.next();
            if(CompatibilityProvider.compatibility.isAirBlock(this.world, blockpos1) && CompatibilityProvider.compatibility.isFullBlock(CompatibilityProvider.compatibility.getBlockBelow(this.world, blockpos1)) && this.explosionRNG.nextInt(3) == 0) {
               CompatibilityProvider.compatibility.setBlockToFire(this.world, blockpos1);
            }
         }
      }

   }

   public Map getPlayerKnockbackMap() {
      return this.playerKnockbackMap;
   }

   public EntityLivingBase getExplosivePlacedBy() {
      return this.exploder == null?null:(this.exploder instanceof EntityTNTPrimed?((EntityTNTPrimed)this.exploder).getTntPlacedBy():(this.exploder instanceof EntityLivingBase?(EntityLivingBase)this.exploder:null));
   }

   public void clearAffectedBlockPositions() {
      this.affectedBlockPositions.clear();
   }

   public List getAffectedBlockPositions() {
      return this.affectedBlockPositions;
   }

   public CompatibleVec3 getPosition() {
      return this.position;
   }
}
