package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.BlockSmith;
import com.vicmatskiv.weaponlib.blocks.DeconstructingInputQuantity;
import com.vicmatskiv.weaponlib.blocks.DeconstructingRecipeHandler;
import com.vicmatskiv.weaponlib.blocks.InventoryDeconstructResult;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerConstructor extends Container {
   public InventoryCrafting inputInventory = new InventoryCrafting(this, 1, 1);
   public int inputSlotNumber;
   public InventoryDeconstructResult outputInventory = new InventoryDeconstructResult();
   public DeconstructingRecipeHandler deconstructingRecipeHandler;
   private final World worldObj;
   public InventoryPlayer playerInventory;
   public String resultString = "deconstructing.result.ready";
   public ContainerConstructor.State deconstructingState;
   public int x;
   public int y;
   public int z;

   public ContainerConstructor(InventoryPlayer parPlayerInventory, World parWorld, int parX, int parY, int parZ) {
      this.deconstructingState = ContainerConstructor.State.READY;
      this.x = 0;
      this.y = 0;
      this.z = 0;
      this.x = parX;
      this.y = parY;
      this.z = parZ;
      this.worldObj = parWorld;
      this.deconstructingRecipeHandler = new DeconstructingRecipeHandler();

      int hotbarSlotIndex;
      int playerSlotIndexX;
      for(hotbarSlotIndex = 0; hotbarSlotIndex < 3; ++hotbarSlotIndex) {
         for(playerSlotIndexX = 0; playerSlotIndexX < 3; ++playerSlotIndexX) {
            this.addSlotToContainer(new Slot(this.outputInventory, playerSlotIndexX + hotbarSlotIndex * 3, 112 + playerSlotIndexX * 18, 17 + hotbarSlotIndex * 18));
         }
      }

      this.inputSlotNumber = this.addSlotToContainer(new Slot(this.inputInventory, 0, 45, 35)).slotNumber;

      for(hotbarSlotIndex = 0; hotbarSlotIndex < 3; ++hotbarSlotIndex) {
         for(playerSlotIndexX = 0; playerSlotIndexX < 9; ++playerSlotIndexX) {
            this.addSlotToContainer(new Slot(parPlayerInventory, playerSlotIndexX + hotbarSlotIndex * 9 + 9, 8 + playerSlotIndexX * 18, 84 + hotbarSlotIndex * 18));
         }
      }

      for(hotbarSlotIndex = 0; hotbarSlotIndex < 9; ++hotbarSlotIndex) {
         this.addSlotToContainer(new Slot(parPlayerInventory, hotbarSlotIndex, 8 + hotbarSlotIndex * 18, 142));
      }

      this.playerInventory = parPlayerInventory;
   }

   public void onCraftMatrixChanged(IInventory parInventory) {
      if(parInventory == this.inputInventory) {
         if(this.inputInventory.getStackInSlot(0) == null) {
            this.resultString = I18n.format("deconstructing.result.ready", new Object[0]);
            this.deconstructingState = ContainerConstructor.State.READY;
            return;
         }

         int amountRequired = DeconstructingInputQuantity.getStackSizeNeeded(this.inputInventory.getStackInSlot(0));
         System.out.println("Amount required = " + amountRequired);
         if(amountRequired > this.inputInventory.getStackInSlot(0).stackSize) {
            this.resultString = I18n.format("deconstructing.result.needMoreStacks", new Object[]{Integer.valueOf(amountRequired - this.inputInventory.getStackInSlot(0).stackSize)});
            this.deconstructingState = ContainerConstructor.State.ERROR;
            return;
         }

         if(amountRequired <= 0) {
            this.resultString = I18n.format("deconstructing.result.impossible", new Object[0]);
            this.deconstructingState = ContainerConstructor.State.ERROR;
            return;
         }

         ItemStack[] outputItemStackArray = this.deconstructingRecipeHandler.getDeconstructResults(this.inputInventory.getStackInSlot(0));
         if(outputItemStackArray == null) {
            this.resultString = I18n.format("deconstructing.result.impossible", new Object[0]);
            this.deconstructingState = ContainerConstructor.State.ERROR;
            return;
         }

         while(this.inputInventory.getStackInSlot(0) != null && amountRequired > 0 && amountRequired <= this.inputInventory.getStackInSlot(0).stackSize) {
            int i;
            ItemStack outputItemStack;
            if(!this.outputInventory.isEmpty()) {
               for(i = 0; i < this.outputInventory.getSizeInventory(); ++i) {
                  outputItemStack = this.outputInventory.getStackInSlot(i);
                  if(outputItemStack != null && outputItemStackArray[i] != null && !outputItemStack.isItemEqual(outputItemStackArray[i])) {
                     if(!this.playerInventory.addItemStackToInventory(outputItemStack)) {
                        EntityItem currentStack = this.playerInventory.player.entityDropItem(outputItemStack, 0.5F);
                        currentStack.posX = this.playerInventory.player.posX;
                        currentStack.posY = this.playerInventory.player.posY;
                        currentStack.posZ = this.playerInventory.player.posZ;
                     }

                     this.outputInventory.setInventorySlotContents(i, (ItemStack)null);
                  }
               }
            }

            for(i = 0; i < outputItemStackArray.length; ++i) {
               outputItemStack = outputItemStackArray[i];
               ItemStack var10 = this.outputInventory.getStackInSlot(i);
               if(outputItemStack != null) {
                  int metadata = outputItemStack.getItemDamage();
                  if(metadata == 32767) {
                     metadata = 0;
                  }

                  ItemStack newStack = null;
                  if(var10 != null && 1 + var10.stackSize <= outputItemStack.getMaxStackSize()) {
                     newStack = new ItemStack(outputItemStack.getItem(), 1 + var10.stackSize, metadata);
                  } else {
                     if(var10 != null && !this.playerInventory.addItemStackToInventory(var10)) {
                        EntityItem entityItem = this.playerInventory.player.entityDropItem(var10, 0.5F);
                        entityItem.posX = this.playerInventory.player.posX;
                        entityItem.posY = this.playerInventory.player.posY;
                        entityItem.posZ = this.playerInventory.player.posZ;
                     }

                     newStack = new ItemStack(outputItemStack.getItem(), 1, metadata);
                  }

                  this.outputInventory.setInventorySlotContents(i, newStack);
               }
            }

            this.playerInventory.player.addStat(BlockSmith.deconstructedItemsStat, amountRequired);
            this.playerInventory.player.triggerAchievement(BlockSmith.deconstructAny);
            this.inputInventory.decrStackSize(0, amountRequired);
         }
      } else {
         this.resultString = I18n.format("deconstructing.result.impossible", new Object[0]);
         this.deconstructingState = ContainerConstructor.State.ERROR;
      }

   }

   public ItemStack slotClick(int parSlotId, int parMouseButtonId, int parClickMode, EntityPlayer parPlayer) {
      ItemStack clickItemStack = super.slotClick(parSlotId, parMouseButtonId, parClickMode, parPlayer);
      if(this.inventorySlots.size() > parSlotId && parSlotId >= 0 && this.inventorySlots.get(parSlotId) != null && ((Slot)this.inventorySlots.get(parSlotId)).inventory == this.inputInventory) {
         this.onCraftMatrixChanged(this.inputInventory);
      }

      return clickItemStack;
   }

   public void onContainerClosed(EntityPlayer parPlayer) {
      if(this.playerInventory.getItemStack() != null) {
         parPlayer.entityDropItem(this.playerInventory.getItemStack(), 0.5F);
      }

      if(!this.worldObj.isRemote) {
         ItemStack itemStack = this.inputInventory.getStackInSlotOnClosing(0);
         if(itemStack != null) {
            parPlayer.entityDropItem(itemStack, 0.5F);
         }

         for(int i = 0; i < this.outputInventory.getSizeInventory(); ++i) {
            itemStack = this.outputInventory.getStackInSlotOnClosing(i);
            if(itemStack != null) {
               parPlayer.entityDropItem(itemStack, 0.5F);
            }
         }
      }

   }

   public boolean canInteractWith(EntityPlayer player) {
      return true;
   }

   public ItemStack transferStackInSlot(EntityPlayer parPlayer, int parSlotIndex) {
      Slot slot = (Slot)this.inventorySlots.get(parSlotIndex);
      if(slot != null && slot.getHasStack()) {
         if(!slot.inventory.equals(this.inputInventory) && !slot.inventory.equals(this.outputInventory)) {
            if(slot.inventory.equals(this.playerInventory)) {
               System.out.println("Shift-clicked on player inventory slot");
               if(!((Slot)this.inventorySlots.get(this.inputSlotNumber)).getHasStack()) {
                  ((Slot)this.inventorySlots.get(this.inputSlotNumber)).putStack(slot.getStack());
                  slot.putStack((ItemStack)null);
                  slot.onSlotChanged();
               } else {
                  System.out.println("There is already something in the input slot");
               }
            }
         } else {
            if(!this.playerInventory.addItemStackToInventory(slot.getStack())) {
               return null;
            }

            slot.putStack((ItemStack)null);
            slot.onSlotChanged();
         }
      }

      return null;
   }

   public Slot getSlot(int parSlotIndex) {
      if(parSlotIndex >= this.inventorySlots.size()) {
         parSlotIndex = this.inventorySlots.size() - 1;
      }

      return super.getSlot(parSlotIndex);
   }

   public static enum State {
      ERROR,
      READY;
   }
}
