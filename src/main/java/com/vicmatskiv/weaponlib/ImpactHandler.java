package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

@FunctionalInterface
public interface ImpactHandler {
   void onImpact(World var1, EntityPlayer var2, WeaponSpawnEntity var3, CompatibleRayTraceResult var4);
}
