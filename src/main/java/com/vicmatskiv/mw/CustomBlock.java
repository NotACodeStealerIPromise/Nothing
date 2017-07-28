package com.vicmatskiv.mw;

import com.vicmatskiv.mw.CustomBlockEntity;
import com.vicmatskiv.mw.ModernWarfareMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CustomBlock extends BlockContainer {
   private String iconName;

   public CustomBlock(Material material, String iconName) {
      super(material);
      this.iconName = iconName;
      this.setCreativeTab(ModernWarfareMod.gunsTab);
      this.setBlockBounds(0.4F, 0.0F, 0.4F, 0.6F, 3.0F, 0.6F);
   }

   public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
      return new CustomBlockEntity();
   }

   public int getRenderType() {
      return -1;
   }

   public boolean isOpaqueCube() {
      return false;
   }

   public boolean renderAsNormalBlock() {
      return false;
   }

   public void registerIcons(IIconRegister iconRegister) {
      this.blockIcon = iconRegister.registerIcon("mw:" + this.iconName);
   }
}
