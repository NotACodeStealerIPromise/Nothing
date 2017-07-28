package com.vicmatskiv.weaponlib.blocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class InventoryDeconstructResult implements IInventory {
   private final ItemStack[] stackResult = new ItemStack[9];

   public int getSizeInventory() {
      return 9;
   }

   public ItemStack getStackInSlot(int par1) {
      return this.stackResult[par1];
   }

   public ItemStack decrStackSize(int par1, int par2) {
      if(this.stackResult[par1] != null) {
         ItemStack itemstack = this.stackResult[par1];
         this.stackResult[par1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public ItemStack getStackInSlotOnClosing(int par1) {
      if(this.stackResult[par1] != null) {
         ItemStack itemstack = this.stackResult[par1];
         this.stackResult[par1] = null;
         return itemstack;
      } else {
         return null;
      }
   }

   public void setInventorySlotContents(int par1, ItemStack par2ItemStack) {
      this.stackResult[par1] = par2ItemStack;
   }

   public int getInventoryStackLimit() {
      return 1;
   }

   public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer) {
      return true;
   }

   public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack) {
      return true;
   }

   public boolean isEmpty() {
      for(int i = 0; i < this.stackResult.length; ++i) {
         if(this.stackResult[i] != null) {
            return false;
         }
      }

      return true;
   }

   public void markDirty() {
   }

   public String getInventoryName() {
      return null;
   }

   public boolean hasCustomInventoryName() {
      return false;
   }

   public void openInventory() {
   }

   public void closeInventory() {
   }
}
