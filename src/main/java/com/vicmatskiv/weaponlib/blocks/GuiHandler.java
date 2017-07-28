package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.BlockSmith;
import com.vicmatskiv.weaponlib.blocks.ContainerConstructor;
import com.vicmatskiv.weaponlib.blocks.ContainerDeconstructor;
import com.vicmatskiv.weaponlib.blocks.GuiConstructor;
import com.vicmatskiv.weaponlib.blocks.GuiDeconstructor;
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      if(tileEntity != null) {
         throw new UnsupportedOperationException("Implement me");
      } else {
         return ID == BlockSmith.GUI_ENUM.DECONSTRUCTOR.ordinal()?new ContainerDeconstructor(player.inventory, world, x, y, z):(ID == 1000000?new ContainerConstructor(player.inventory, world, x, y, z):null);
      }
   }

   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      if(tileEntity != null) {
         throw new UnsupportedOperationException("Implement me");
      } else {
         return ID == BlockSmith.GUI_ENUM.DECONSTRUCTOR.ordinal()?new GuiDeconstructor(player.inventory, world, I18n.format("tile.deconstructor.name", new Object[0]), x, y, z):(ID == 1000000?new GuiConstructor(player.inventory, world, I18n.format("tile.constructor.name", new Object[0]), x, y, z):null);
      }
   }
}
