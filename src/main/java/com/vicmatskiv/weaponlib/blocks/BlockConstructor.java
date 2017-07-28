package com.vicmatskiv.weaponlib.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockConstructor extends Block {
   private Object mod;
   private int modGuidId;
   static final int ID = 1000000;

   public BlockConstructor(Object mod) {
      super(Material.rock);
      this.mod = mod;
      this.setBlockName("bench");
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean onBlockActivated(World parWorld, int x, int y, int z, EntityPlayer parPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if(!parWorld.isRemote) {
         System.out.println("BlockBench onBlockActivated");
         parPlayer.openGui(this.mod, 1000000, parWorld, x, y, z);
      }

      return true;
   }
}
