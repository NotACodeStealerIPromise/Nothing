package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.animation.MultipartRenderStateManager;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.grenade.RenderableState;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public abstract class CompatibleGrenadeRenderer implements IItemRenderer {
   private GrenadeRenderer.Builder builder;

   protected CompatibleGrenadeRenderer(GrenadeRenderer.Builder builder) {
      this.builder = builder;
   }

   protected abstract ClientModContext getClientModContext();

   protected abstract CompatibleGrenadeRenderer.StateDescriptor getStateDescriptor(EntityPlayer var1, ItemStack var2);

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void renderItem(ItemRenderType type, ItemStack weaponItemStack, Object... data) {
      GL11.glPushMatrix();
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
         CompatibleGrenadeRenderer.StateDescriptor stateDescriptor = this.getStateDescriptor((EntityPlayer)player, weaponItemStack);
         renderContext.setPlayerItemInstance(stateDescriptor.instance);
         MultipartPositioning multipartPositioning = stateDescriptor.stateManager.nextPositioning();
         renderContext.setTransitionProgress(multipartPositioning.getProgress());
         renderContext.setFromState(multipartPositioning.getFromState(RenderableState.class));
         renderContext.setToState(multipartPositioning.getToState(RenderableState.class));
         positioner = multipartPositioning.getPositioner();
         renderContext.capturePartPosition(Part.NONE);
         this.renderLeftArm((EntityPlayer)player, renderContext, positioner);
         positioner.randomize(stateDescriptor.rate, stateDescriptor.amplitude);
         this.renderRightArm((EntityPlayer)player, renderContext, positioner);
         positioner.position(Part.MAIN_ITEM, renderContext);
         if(DebugPositioner.isDebugModeEnabled()) {
            DebugPositioner.position(Part.MAIN_ITEM, renderContext);
         }

         renderContext.capturePartPosition(Part.MAIN_ITEM);
      }

      this.renderItem(weaponItemStack, renderContext, positioner);
      GL11.glPopMatrix();
   }

   protected abstract void renderItem(ItemStack var1, RenderContext var2, MultipartPositioning.Positioner var3);

   private void renderRightArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
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

      renderContext.capturePartPosition(Part.RIGHT_HAND);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedRightArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   private void renderLeftArm(EntityPlayer player, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
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

      renderContext.capturePartPosition(Part.LEFT_HAND);
      GL11.glColor3f(1.0F, 1.0F, 1.0F);
      render.modelBipedMain.onGround = 0.0F;
      render.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, player);
      render.modelBipedMain.bipedLeftArm.render(0.0625F);
      GL11.glPopMatrix();
   }

   public abstract void renderAttachments(MultipartPositioning.Positioner var1, RenderContext var2, List var3);

   protected static class StateDescriptor {
      protected MultipartRenderStateManager stateManager;
      protected float rate;
      protected float amplitude = 0.04F;
      private PlayerGrenadeInstance instance;

      public StateDescriptor(PlayerGrenadeInstance instance, MultipartRenderStateManager stateManager, float rate, float amplitude) {
         this.instance = instance;
         this.stateManager = stateManager;
         this.rate = rate;
         this.amplitude = amplitude;
      }
   }
}
