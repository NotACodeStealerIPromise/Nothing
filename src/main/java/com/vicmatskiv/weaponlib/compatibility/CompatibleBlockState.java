package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.block.Block;

public class CompatibleBlockState {
   private int blockMetadata;
   private Block block;

   CompatibleBlockState(Block block, int blockMetadata) {
      this.block = block;
      this.blockMetadata = blockMetadata;
   }

   static CompatibleBlockState fromBlock(Block block) {
      return block != null?new CompatibleBlockState(block):null;
   }

   private CompatibleBlockState(Block block) {
      this.block = block;
   }

   public int getBlockMetadata() {
      return this.blockMetadata;
   }

   Block getBlock() {
      return this.block;
   }
}
