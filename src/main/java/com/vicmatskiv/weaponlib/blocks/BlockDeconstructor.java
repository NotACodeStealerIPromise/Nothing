package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.BlockSmith;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class BlockDeconstructor extends Block {
   private Object mod;
   private int modGuidId;

   public BlockDeconstructor(Object mod) {
      super(Material.rock);
      this.mod = mod;
      this.setBlockName("deconstructor");
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   public boolean onBlockActivated(World parWorld, int x, int y, int z, EntityPlayer parPlayer, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
      if(!parWorld.isRemote) {
         System.out.println("BlockDeconstructor onBlockActivated");
         parPlayer.openGui(this.mod, BlockSmith.GUI_ENUM.DECONSTRUCTOR.ordinal(), parWorld, x, y, z);
      }

      return true;
   }
}
