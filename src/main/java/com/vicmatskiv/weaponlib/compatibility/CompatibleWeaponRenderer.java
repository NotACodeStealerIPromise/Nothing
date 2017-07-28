package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.RenderableState;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.animation.MultipartRenderStateManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import com.vicmatskiv.weaponlib.compatibility.Framebuffers;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public abstract class CompatibleWeaponRenderer implements IItemRenderer {
   private static final int INVENTORY_TEXTURE_WIDTH = 256;
   private static final int INVENTORY_TEXTURE_HEIGHT = 256;
   private WeaponRenderer.Builder builder;

   protected CompatibleWeaponRenderer(WeaponRenderer.Builder builder) {
      this.builder = builder;
   }

   protected abstract ClientModContext getClientModContext();

   protected abstract CompatibleWeaponRenderer.StateDescriptor getStateDescriptor(EntityPlayer var1, ItemStack var2);

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void renderItem(ItemRenderType type, ItemStack weaponItemStack, Object... data) {
      GL11.glPushMatrix();
      int originalFramebufferId = -1;
      Framebuffer framebuffer = null;
      Integer inventoryTexture = null;
      boolean inventoryTextureInitializationPhaseOn = false;
      Minecraft mc = Minecraft.getMinecraft();
      ScaledResolution scaledresolution = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
      if(type == ItemRenderType.INVENTORY) {
         inventoryTexture = (Integer)this.getClientModContext().getInventoryTextureMap().get(this);
         if(inventoryTexture == null) {
            inventoryTextureInitializationPhaseOn = true;
            originalFramebufferId = Framebuffers.getCurrentFramebuffer();
            Framebuffers.unbindFramebuffer();
            framebuffer = new Framebuffer(256, 256, true);
            inventoryTexture = Integer.valueOf(framebuffer.framebufferTexture);
            this.getClientModContext().getInventoryTextureMap().put(this, inventoryTexture);
            framebuffer.bindFramebuffer(true);
            this.setupInventoryRendering(256.0D, 256.0D);
            GL11.glScalef(130.0F, 130.0F, 130.0F);
            if(DebugPositioner.isDebugModeEnabled()) {
               DebugPositioner.position(Part.INVENTORY, (RenderContext)null);
            }

            GL11.glScalef(1.0F, 1.0F, 1.0F);
            GL11.glRotatef(25.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(1.449999F, 1.399999F, 0.0F);
         }
      }

      GL11.glScaled(-1.0D, -1.0D, 1.0D);
      Object player;
      if(data.length > 1 && data[1] instanceof EntityPlayer) {
         player = (EntityPlayer)data[1];
      } else {
         player = Minecraft.getMinecraft().thePlayer;
      }

      RenderContext renderContext = new RenderContext(this.getClientModContext(), (EntityPlayer)player, weaponItemStack);
      renderContext.setAgeInTicks(-0.4F);
      renderContext.setScale(0.08F);
      renderContext.setCompatibleTransformType(CompatibleTransformType.fromItemRenderType(type));
      MultipartPositioning.Positioner positioner = null;
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
      case 1:
         this.builder.getEntityPositioning().accept(weaponItemStack);
         break;
      case 2:
         this.builder.getInventoryPositioning().accept(weaponItemStack);
         break;
      case 3:
         this.builder.getThirdPersonPositioning().accept(renderContext);
         break;
      case 4:
         CompatibleWeaponRenderer.StateDescriptor stateDescriptor = this.getStateDescriptor((EntityPlayer)player, weaponItemStack);
         renderContext.setPlayerItemInstance(stateDescriptor.instance);
         MultipartPositioning multipartPositioning = stateDescriptor.stateManager.nextPositioning();
         renderContext.setTransitionProgress(multipartPositioning.getProgress());
         renderContext.setFromState(multipartPositioning.getFromState(RenderableState.class));
         renderContext.setToState(multipartPositioning.getToState(RenderableState.class));
         positioner = multipartPositioning.getPositioner();
         positioner.randomize(stateDescriptor.rate, stateDescriptor.amplitude);
         positioner.position(Part.MAIN_ITEM, renderContext);
         if(DebugPositioner.isDebugModeEnabled()) {
            DebugPositioner.position(Part.MAIN_ITEM, renderContext);
         }

         renderLeftArm((EntityPlayer)player, renderContext, positioner);
         renderRightArm((EntityPlayer)player, renderContext, positioner);
      }

      if(type != ItemRenderType.INVENTORY || inventoryTextureInitializationPhaseOn) {
         this.renderItem(weaponItemStack, renderContext, positioner);
      }

      if(type == ItemRenderType.INVENTORY && inventoryTextureInitializationPhaseOn) {
         framebuffer.unbindFramebuffer();
         framebuffer.framebufferTexture = -1;
         framebuffer.deleteFramebuffer();
         this.restoreInventoryRendering(scaledresolution);
      }

      GL11.glPopMatrix();
      if(originalFramebufferId >= 0) {
         Framebuffers.bindFramebuffer(originalFramebufferId, true, mc.getFramebuffer().framebufferWidth, mc.getFramebuffer().framebufferHeight);
      }

      if(type == ItemRenderType.INVENTORY) {
         this.renderCachedInventoryTexture(inventoryTexture);
      }

   }

   private void setupInventoryRendering(double projectionWidth, double projectionHeight) {
      GL11.glClear(256);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, projectionWidth, projectionHeight, 0.0D, 1000.0D, 3000.0D);
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -2000.0F);
   }

   private void restoreInventoryRendering(ScaledResolution scaledresolution) {
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      GL11.glOrtho(0.0D, scaledresolution.getScaledWidth_double(), scaledresolution.getScaledHeight_double(), 0.0D, 1000.0D, 3000.0D);
      GL11.glMatrixMode(5888);
   }

   private void renderCachedInventoryTexture(Integer inventoryTexture) {
      GL11.glPushMatrix();
      GL11.glPushAttrib(8192);
      GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(-210.0F, 1.0F, 0.0F, 0.0F);
      GL11.glScalef(1.0F, 1.0F, -1.0F);
      GL11.glTranslatef(-0.8F, -0.8F, -1.0F);
      GL11.glScalef(0.006F, 0.006F, 0.006F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glAlphaFunc(516, 0.003921569F);
      GL11.glBindTexture(3553, inventoryTexture.intValue());
      drawTexturedQuadFit(0.0D, 0.0D, 256.0D, 256.0D, 0.0D);
      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public void drawTexturedModalRect(int x, int y, int u, int v, int width, int height) {
      byte zLevel = 50;
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      Tessellator tessellator = Tessellator.instance;
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + height), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + height) * f1));
      tessellator.addVertexWithUV((double)(x + width), (double)(y + height), (double)zLevel, (double)((float)(u + width) * f), (double)((float)(v + height) * f1));
      tessellator.addVertexWithUV((double)(x + width), (double)(y + 0), (double)zLevel, (double)((float)(u + width) * f), (double)((float)(v + 0) * f1));
      tessellator.addVertexWithUV((double)(x + 0), (double)(y + 0), (double)zLevel, (double)((float)(u + 0) * f), (double)((float)(v + 0) * f1));
      tessellator.draw();
   }

   private static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
      CompatibleTessellator tessellator = CompatibleTessellator.getInstance();
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, 1.0F, 0.0F);
      tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, 0.0F, 0.0F);
      tessellator.draw();
   }

   protected abstract void renderItem(ItemStack var1, RenderContext var2, MultipartPositioning.Positioner var3);

   static void renderRightArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      RenderPlayer render = (RenderPlayer)RenderManager.instance.getEntityRenderObject(player);
      Minecraft.getMinecraft().getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
      GL11.glPushMatrix();
      GL11.glScaled(1.0D, 1.0D, 1.0D);
      GL11.glScaled(1.0D, 1.0D, 1.0D);
      GL11.glTranslatef(-0.25F, 0.0F, 0.2F);
      GL11.glRotatef(5.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(25.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
      positioner.position(Part.RIGHT_HAND, renderContext);
      if(DebugPositioner.isDebugModeEnabled()) {
         DebugPositioner.position(Part.RIGHT_HAND, renderContext);
      }

      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedRightArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   static void renderLeftArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      RenderPlayer render = (RenderPlayer)RenderManager.instance.getEntityRenderObject(player);
      Minecraft.getMinecraft().getTextureManager().bindTexture(((AbstractClientPlayer)player).getLocationSkin());
      GL11.glPushMatrix();
      GL11.glScaled(1.0D, 1.0D, 1.0D);
      GL11.glTranslatef(0.0F, -1.0F, 0.0F);
      GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(0.0F, 0.0F, 1.0F, 0.0F);
      GL11.glRotatef(10.0F, 0.0F, 0.0F, 1.0F);
      positioner.position(Part.LEFT_HAND, renderContext);
      if(DebugPositioner.isDebugModeEnabled()) {
         DebugPositioner.position(Part.LEFT_HAND, renderContext);
      }

      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedLeftArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   protected static class StateDescriptor {
      protected MultipartRenderStateManager stateManager;
      protected float rate;
      protected float amplitude = 0.04F;
      private PlayerWeaponInstance instance;

      public StateDescriptor(PlayerWeaponInstance instance, MultipartRenderStateManager stateManager, float rate, float amplitude) {
         this.instance = instance;
         this.stateManager = stateManager;
         this.rate = rate;
         this.amplitude = amplitude;
      }
   }
}
