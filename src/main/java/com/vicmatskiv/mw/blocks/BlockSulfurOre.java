package com.vicmatskiv.mw.blocks;

import com.vicmatskiv.mw.ModernWarfareMod;
import com.vicmatskiv.mw.Ores;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public class BlockSulfurOre extends Block {
   private static final String name = "titaniumore";

   public BlockSulfurOre() {
      super(Material.rock);
      this.setBlockName("mw_SulfurOre");
      this.setBlockTextureName("mw:sulfurore");
      this.setHardness(4.0F);
      this.setResistance(600000.0F);
      this.setStepSound(soundTypeStone);
      this.setHarvestLevel("pickaxe", 2);
      this.setCreativeTab(ModernWarfareMod.gunsTab);
   }

   public Item getItemDropped(int meta, Random rand, int fortune) {
      return Ores.SulfurDust;
   }
}
