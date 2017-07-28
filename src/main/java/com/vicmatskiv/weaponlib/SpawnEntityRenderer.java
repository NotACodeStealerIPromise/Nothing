package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponSpawnEntity;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class SpawnEntityRenderer extends CompatibleEntityRenderer {
   public void doCompatibleRender(Entity entity, double x, double y, double z, float yaw, float tick) {
      WeaponSpawnEntity weaponSpawnEntity = (WeaponSpawnEntity)entity;
      Weapon weapon = weaponSpawnEntity.getWeapon();
      if(weapon != null) {
         Object model = null;
         if(model != null) {
            String ammoModelTextureName = weapon.getAmmoModelTextureName();
            ResourceLocation textureLocation = ammoModelTextureName != null?new ResourceLocation(ammoModelTextureName):null;
            if(model != null) {
               GL11.glPushMatrix();
               if(textureLocation != null) {
                  this.bindTexture(textureLocation);
               }

               GL11.glTranslated(x, y, z);
               ((ModelBase)model).render(entity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
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
