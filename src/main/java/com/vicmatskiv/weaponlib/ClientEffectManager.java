package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.EffectManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import com.vicmatskiv.weaponlib.particle.ExplosionParticleFX;
import com.vicmatskiv.weaponlib.particle.ExplosionSmokeFX;
import com.vicmatskiv.weaponlib.particle.FlashFX;
import com.vicmatskiv.weaponlib.particle.SmokeFX;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

final class ClientEffectManager implements EffectManager {
   public void spawnSmokeParticle(EntityPlayer player, float xOffset, float yOffset) {
      double motionX = CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003D;
      double motionY = CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003D;
      double motionZ = CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003D;
      CompatibleVec3 look = CompatibilityProvider.compatibility.getLookVec(player);
      float distance = 0.3F;
      float scale = 1.0F * CompatibilityProvider.compatibility.getEffectScaleFactor();
      float positionRandomizationFactor = 0.01F;
      double posX = player.posX + look.getVec().xCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) + -look.getVec().zCoord * (double)xOffset;
      double posY = player.posY + look.getVec().yCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) - (double)yOffset;
      double posZ = player.posZ + look.getVec().zCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) + look.getVec().xCoord * (double)xOffset;
      SmokeFX smokeParticle = new SmokeFX(CompatibilityProvider.compatibility.world(player), posX, posY, posZ, scale, (float)motionX, (float)motionY, (float)motionZ);
      Minecraft.getMinecraft().effectRenderer.addEffect(smokeParticle);
   }

   public void spawnFlashParticle(EntityPlayer player, float flashIntensity, float flashScale, float xOffset, float yOffset) {
      float distance = 0.5F;
      float scale = 0.8F * CompatibilityProvider.compatibility.getEffectScaleFactor() * flashScale;
      float positionRandomizationFactor = 0.003F;
      CompatibleVec3 look = CompatibilityProvider.compatibility.getLookVec(player);
      float motionX = (float)CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003F;
      float motionY = (float)CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003F;
      float motionZ = (float)CompatibilityProvider.compatibility.world(player).rand.nextGaussian() * 0.003F;
      double posX = player.posX + look.getVec().xCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) + -look.getVec().zCoord * (double)xOffset;
      double posY = player.posY + look.getVec().yCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) - (double)yOffset;
      double posZ = player.posZ + look.getVec().zCoord * (double)distance + (double)((CompatibilityProvider.compatibility.world(player).rand.nextFloat() * 2.0F - 1.0F) * positionRandomizationFactor) + look.getVec().xCoord * (double)xOffset;
      FlashFX flashParticle = new FlashFX(CompatibilityProvider.compatibility.world(player), posX, posY, posZ, scale, flashIntensity, motionX, motionY, motionZ);
      Minecraft.getMinecraft().effectRenderer.addEffect(flashParticle);
   }

   public void spawnExplosionSmoke(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge, ExplosionSmokeFX.Behavior behavior) {
      World world = CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer());
      ExplosionSmokeFX smokeParticle = new ExplosionSmokeFX(world, posX, posY, posZ, scale, (float)motionX, (float)motionY, (float)motionZ, maxAge, ExplosionSmokeFX.Behavior.SMOKE_GRENADE);
      Minecraft.getMinecraft().effectRenderer.addEffect(smokeParticle);
   }

   public void spawnExplosionParticle(double posX, double posY, double posZ, double motionX, double motionY, double motionZ, float scale, int maxAge) {
      World world = CompatibilityProvider.compatibility.world(CompatibilityProvider.compatibility.clientPlayer());
      ExplosionParticleFX explosionParticle = new ExplosionParticleFX(world, posX, posY, posZ, scale, motionX, motionY, motionZ, maxAge);
      Minecraft.getMinecraft().effectRenderer.addEffect(explosionParticle);
   }
}
