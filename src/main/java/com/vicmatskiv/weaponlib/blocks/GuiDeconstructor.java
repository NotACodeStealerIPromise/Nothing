package com.vicmatskiv.weaponlib.blocks;

import com.vicmatskiv.weaponlib.blocks.ContainerDeconstructor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiDeconstructor extends GuiContainer {
   public ContainerDeconstructor container;
   private final String blockName;

   public GuiDeconstructor(InventoryPlayer playerInventory, World parWorld, String parBlockName, int parX, int parY, int parZ) {
      super(new ContainerDeconstructor(playerInventory, parWorld, parX, parY, parZ));
      this.container = (ContainerDeconstructor)this.inventorySlots;
      this.blockName = parBlockName;
   }

   public void actionPerformed(GuiButton button) {
   }

   public void drawScreen(int par1, int par2, float par3) {
      super.drawScreen(par1, par2, par3);
   }

   protected void drawGuiContainerForegroundLayer(int par1, int par2) {
      GL11.glDisable(2896);
      this.fontRendererObj.drawString(this.blockName, this.xSize / 2 - this.fontRendererObj.getStringWidth(this.blockName) / 2 + 1, 5, 4210752);
      this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 6, this.ySize - 96 + 2, 4210752);
      String string = this.container.resultString;
      if(string != null) {
         ContainerDeconstructor.State msgType = this.container.deconstructingState;
         EnumChatFormatting format = EnumChatFormatting.GREEN;
         EnumChatFormatting shadowFormat = EnumChatFormatting.DARK_GRAY;
         if(msgType == ContainerDeconstructor.State.ERROR) {
            format = EnumChatFormatting.WHITE;
            shadowFormat = EnumChatFormatting.DARK_RED;
         }

         this.fontRendererObj.drawString(shadowFormat + string + EnumChatFormatting.RESET, 7, this.ySize - 95 + 2 - this.fontRendererObj.FONT_HEIGHT, 0);
         this.fontRendererObj.drawString(format + string + EnumChatFormatting.RESET, 6, this.ySize - 96 + 2 - this.fontRendererObj.FONT_HEIGHT, 0);
      }

      GL11.glEnable(2896);
   }

   protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.renderEngine.bindTexture(new ResourceLocation("blocksmith:textures/gui/container/deconstructor.png"));
      int k = this.width / 2 - this.xSize / 2;
      int l = this.height / 2 - this.ySize / 2;
      this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
      GL11.glPopMatrix();
   }
}
