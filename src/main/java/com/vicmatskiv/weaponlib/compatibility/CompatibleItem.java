package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public abstract class CompatibleItem extends Item {
   public void onCreated(ItemStack stack, World worldIn, EntityPlayer playerIn) {
      super.onCreated(stack, worldIn, playerIn);
      stack.setTagCompound(new NBTTagCompound());
   }

   public final ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
      return this.onCompatibleItemRightClick(itemStack, world, player, true);
   }

   protected ItemStack onCompatibleItemRightClick(ItemStack itemStack, World world, EntityPlayer player, boolean mainHand) {
      return itemStack;
   }

   public final boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
      return super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
   }

   protected boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world) {
      return false;
   }

   public void registerIcons(IIconRegister register) {
   }
}
