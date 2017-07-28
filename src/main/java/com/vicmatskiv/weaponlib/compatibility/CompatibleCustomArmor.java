package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.CustomArmor;
import com.vicmatskiv.weaponlib.compatibility.CompatibleEntityEquipmentSlot;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemArmor.ArmorMaterial;

public class CompatibleCustomArmor extends ItemArmor {
   protected ModelBiped model;
   protected String iconName;
   protected String textureName;
   protected String hudTextureName;
   protected String modId;

   public CompatibleCustomArmor(String modId, ArmorMaterial material, int renderIndex, CompatibleEntityEquipmentSlot compatibleEntityEquipmentSlot, String iconName, String textureName, ModelBiped model, String hudTextureName) {
      super(material, renderIndex, compatibleEntityEquipmentSlot.getSlot());
      this.modId = modId;
      this.model = model;
      this.iconName = iconName;
      this.textureName = textureName;
      this.hudTextureName = hudTextureName;
   }

   @SideOnly(Side.CLIENT)
   public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, int armorSlot) {
      ModelBiped armorModel = null;
      if(itemStack != null) {
         if(itemStack.getItem() instanceof CustomArmor) {
            armorModel = this.model;
         }

         if(armorModel != null) {
            armorModel.bipedHead.showModel = armorSlot == 0;
            armorModel.bipedHeadwear.showModel = armorSlot == 0;
            armorModel.bipedBody.showModel = armorSlot == 1 || armorSlot == 2;
            armorModel.bipedRightArm.showModel = armorSlot == 1;
            armorModel.bipedLeftArm.showModel = armorSlot == 1;
            armorModel.bipedRightLeg.showModel = false;
            armorModel.bipedLeftLeg.showModel = false;
            armorModel.bipedRightLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.bipedLeftLeg.showModel = armorSlot == 2 || armorSlot == 3;
            armorModel.isSneak = entityLiving.isSneaking();
            armorModel.isRiding = entityLiving.isRiding();
            armorModel.isChild = entityLiving.isChild();
            armorModel.heldItemRight = entityLiving.getEquipmentInSlot(0) != null?1:0;
            if(entityLiving instanceof EntityPlayer) {
               RenderPlayer renderPlayer = (RenderPlayer)RenderManager.instance.getEntityRenderObject(entityLiving);
               armorModel.aimedBow = ((EntityPlayer)entityLiving).getItemInUseDuration() > 0 || renderPlayer.modelBipedMain.aimedBow;
            }

            return armorModel;
         }
      }

      return null;
   }

   @SideOnly(Side.CLIENT)
   public void registerIcons(IIconRegister par1IconRegister) {
      this.itemIcon = par1IconRegister.registerIcon(this.modId + ":" + this.iconName);
   }

   public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
      return this.modId + ":textures/models/" + this.textureName + ".png";
   }
}
