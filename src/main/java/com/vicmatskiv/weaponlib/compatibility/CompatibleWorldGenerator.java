package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import cpw.mods.fml.common.IWorldGenerator;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public abstract class CompatibleWorldGenerator implements IWorldGenerator {
   public void generate(Block block, int maxVeinSize, CompatibleBlocks target, World world, Random random, int posX, int posY, int posZ) {
      (new WorldGenMinable(block, 0, maxVeinSize, target.getBlock())).generate(world, random, posX, posY, posZ);
   }
}
