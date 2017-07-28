package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.ModelSource;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.StaticModelSourceRenderer;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWeaponRenderer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.Iterator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;
import org.lwjgl.opengl.GL11;

public abstract class CompatibleStaticModelSourceRenderer implements IItemRenderer {
   protected StaticModelSourceRenderer.Builder builder;

   protected CompatibleStaticModelSourceRenderer(StaticModelSourceRenderer.Builder builder) {
      this.builder = builder;
   }

   public boolean handleRenderType(ItemStack item, ItemRenderType type) {
      return true;
   }

   public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
      return true;
   }

   @SideOnly(Side.CLIENT)
   public void renderItem(ItemRenderType type, ItemStack itemStack, Object... data) {
      GL11.glPushMatrix();
      GL11.glScaled(-1.0D, -1.0D, 1.0D);
      EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
      RenderContext renderContext = new RenderContext(this.getModContext(), player, itemStack);
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
      case 1:
         this.builder.getEntityPositioning().accept(itemStack);
         break;
      case 2:
         this.builder.getInventoryPositioning().accept(itemStack);
         break;
      case 3:
         this.builder.getThirdPersonPositioning().accept(player, itemStack);
         break;
      case 4:
         this.builder.getFirstPersonPositioning().accept(player, itemStack);
         CompatibleWeaponRenderer.renderLeftArm(player, renderContext, (p, c) -> {
            this.builder.getFirstPersonLeftHandPositioning().accept(c);
         });
         CompatibleWeaponRenderer.renderRightArm(player, renderContext, (p, c) -> {
            this.builder.getFirstPersonRightHandPositioning().accept(c);
         });
      }

      this.renderModelSource(renderContext, itemStack, type, (Entity)null, 0.0F, 0.0F, -0.4F, 0.0F, 0.0F, 0.08F);
      GL11.glPopMatrix();
   }

   private void renderModelSource(RenderContext renderContext, ItemStack itemStack, ItemRenderType type, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      if(!(itemStack.getItem() instanceof ModelSource)) {
         throw new IllegalArgumentException();
      } else {
         GL11.glPushMatrix();
         ModelSource modelSource = (ModelSource)itemStack.getItem();
         Iterator postRenderer = modelSource.getTexturedModels().iterator();

         while(postRenderer.hasNext()) {
            Tuple texturedModel = (Tuple)postRenderer.next();
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + (String)texturedModel.getV()));
            GL11.glPushMatrix();
            GL11.glPushAttrib(8192);
            ModelBase model = (ModelBase)texturedModel.getU();
            switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[type.ordinal()]) {
            case 1:
               this.builder.getEntityModelPositioning().accept(model, itemStack);
               break;
            case 2:
               this.builder.getInventoryModelPositioning().accept(model, itemStack);
               break;
            case 3:
               this.builder.getThirdPersonModelPositioning().accept(model, itemStack);
               break;
            case 4:
               this.builder.getFirstPersonModelPositioning().accept(model, itemStack);
            }

            model.render(entity, f, f1, f2, f3, f4, f5);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }

         CustomRenderer postRenderer1 = modelSource.getPostRenderer();
         if(postRenderer1 != null) {
            renderContext.setAgeInTicks(-0.4F);
            renderContext.setScale(0.08F);
            renderContext.setCompatibleTransformType(CompatibleTransformType.fromItemRenderType(type));
            renderContext.setPlayerItemInstance(this.getModContext().getPlayerItemInstanceRegistry().getItemInstance(renderContext.getPlayer(), itemStack));
            GL11.glPushMatrix();
            GL11.glPushAttrib(8193);
            postRenderer1.render(renderContext);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
      }
   }

   protected abstract ModContext getModContext();
}
