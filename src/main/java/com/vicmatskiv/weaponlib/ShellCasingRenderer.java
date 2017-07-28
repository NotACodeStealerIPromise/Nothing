package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.EntityShellCasing;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ShellCasingRenderer extends CompatibleEntityRenderer {
   public void doCompatibleRender(Entity entity, double x, double y, double z, float yaw, float tick) {
      EntityShellCasing entityShellCasing = (EntityShellCasing)entity;
      Weapon weapon = entityShellCasing.getWeapon();
      if(weapon != null) {
         ModelBase model = weapon.getShellCasingModel();
         if(model != null) {
            String shellCasingTextureName = weapon.getShellCasingTextureName();
            ResourceLocation textureLocation = shellCasingTextureName != null?new ResourceLocation(shellCasingTextureName):null;
            if(model != null) {
               GL11.glPushMatrix();
               if(textureLocation != null) {
                  this.bindTexture(textureLocation);
               }

               GL11.glTranslated(x, y, z);
               float fov = Minecraft.getMinecraft().gameSettings.fovSetting;
               float scale = (fov * 0.001F - 0.02F) * 0.6F;
               GL11.glScalef(scale, scale, scale);
               GL11.glRotatef(entityShellCasing.getXRotation(), 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(entityShellCasing.getYRotation(), 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(entityShellCasing.getZRotation(), 0.0F, 0.0F, 1.0F);
               GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
               model.render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
               GL11.glPopMatrix();
            }
         }

      }
   }

   protected ResourceLocation getEntityTexture(Entity entity) {
      WeaponSpawnEntity weaponSpawnEntity = (WeaponSpawnEntity)entity;
      return new ResourceLocation(weaponSpawnEntity.getWeapon().getAmmoModelTextureName());
   }
}
