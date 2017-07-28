package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityRenderer;
import com.vicmatskiv.weaponlib.grenade.AbstractEntityGrenade;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class EntityGrenadeRenderer extends CompatibleEntityRenderer {
   public void doCompatibleRender(Entity entity, double x, double y, double z, float yaw, float tick) {
      AbstractEntityGrenade entityGrenade = (AbstractEntityGrenade)entity;
      ItemGrenade itemGrenade = entityGrenade.getItemGrenade();
      if(itemGrenade != null) {
         GrenadeRenderer renderer = itemGrenade.getRenderer();
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(renderer.getClientModContext().getModId(), "textures/models/" + itemGrenade.getTextureName()));
         ModelBase model = renderer.getModel();
         GL11.glPushMatrix();
         GL11.glTranslated(x, y, z);
         float rotationOffsetX = ((Float)renderer.getXRotationCenterOffset().get()).floatValue();
         float rotationOffsetY = ((Float)renderer.getYRotationCenterOffset().get()).floatValue();
         float rotationOffsetZ = ((Float)renderer.getZRotationCenterOffset().get()).floatValue();
         GL11.glTranslatef(rotationOffsetX, rotationOffsetY, rotationOffsetZ);
         GL11.glRotatef(entityGrenade.getXRotation(), 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(entityGrenade.getYRotation(), 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(entityGrenade.getZRotation(), 0.0F, 0.0F, 1.0F);
         GL11.glTranslatef(-rotationOffsetX, -rotationOffsetY, -rotationOffsetZ);
         renderer.getThrownEntityPositioning().run();
         model.render(entity, 0.0F, 0.3F, 0.0F, 0.0F, 0.0F, 0.08F);
         GL11.glPopMatrix();
      }
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      return null;
   }
}
