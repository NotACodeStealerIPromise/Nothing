package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.particle.ExplosionSmokeFX;
import net.minecraft.entity.player.EntityPlayer;

public interface EffectManager {
   void spawnSmokeParticle(EntityPlayer var1, float var2, float var3);

   void spawnFlashParticle(EntityPlayer var1, float var2, float var3, float var4, float var5);

   void spawnExplosionSmoke(double var1, double var3, double var5, double var7, double var9, double var11, float var13, int var14, ExplosionSmokeFX.Behavior var15);

   void spawnExplosionParticle(double var1, double var3, double var5, double var7, double var9, double var11, float var13, int var14);
}
