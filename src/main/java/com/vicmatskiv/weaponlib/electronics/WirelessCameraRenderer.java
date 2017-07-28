package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityRenderer;
import com.vicmatskiv.weaponlib.electronics.EntityWirelessCamera;
import com.vicmatskiv.weaponlib.electronics.ItemWirelessCamera;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class WirelessCameraRenderer extends CompatibleEntityRenderer {
   private String modId;

   public WirelessCameraRenderer(String modId) {
      this.modId = modId;
   }

   public void doCompatibleRender(Entity entity, double x, double y, double z, float yaw, float tick) {
      EntityWirelessCamera weaponSpawnEntity = (EntityWirelessCamera)entity;
      ItemWirelessCamera camera = weaponSpawnEntity.getItem();
      if(camera != null) {
         ModelBase model = camera.getModel();
         if(model != null) {
            String textureName = camera.getTextureName();
            ResourceLocation textureLocation = textureName != null?new ResourceLocation(this.modId + ":textures/models/" + textureName):null;
            if(model != null) {
               GL11.glPushMatrix();
               GL11.glTranslatef(0.0F, 0.0F, 0.0F);
               if(textureLocation != null) {
                  this.bindTexture(textureLocation);
               }

               GL11.glTranslated(x, y, z);
               model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
               GL11.glPopMatrix();
            }
         }

      }
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      EntityWirelessCamera camera = (EntityWirelessCamera)entity;
      ItemWirelessCamera item = camera.getItem();
      return item != null?new ResourceLocation(this.modId + ":textures/models/" + item.getTextureName()):null;
   }
}
