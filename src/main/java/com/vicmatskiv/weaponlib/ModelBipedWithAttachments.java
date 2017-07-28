package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.compatibility.CompatibleModelBiped;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class ModelBipedWithAttachments extends CompatibleModelBiped {
   public ModelBipedWithAttachments(ModelBiped delegate) {
      super(delegate);
   }

   public int hashCode() {
      return this.delegate.hashCode();
   }

   public void setLivingAnimations(EntityLivingBase p_78086_1_, float p_78086_2_, float p_78086_3_, float p_78086_4_) {
      this.delegate.setLivingAnimations(p_78086_1_, p_78086_2_, p_78086_3_, p_78086_4_);
   }

   public ModelRenderer getRandomModelBox(Random p_85181_1_) {
      return this.delegate.getRandomModelBox(p_85181_1_);
   }

   public TextureOffset getTextureOffset(String p_78084_1_) {
      return this.delegate.getTextureOffset(p_78084_1_);
   }

   public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      this.delegate.render(entity, f, f1, f2, f3, f4, f5);
   }

   public boolean equals(Object obj) {
      return this.delegate.equals(obj);
   }

   public void setRotationAngles(float p_78087_1_, float p_78087_2_, float p_78087_3_, float p_78087_4_, float p_78087_5_, float p_78087_6_, Entity p_78087_7_) {
      this.delegate.setRotationAngles(p_78087_1_, p_78087_2_, p_78087_3_, p_78087_4_, p_78087_5_, p_78087_6_, p_78087_7_);
   }

   public String toString() {
      return this.delegate.toString();
   }

   public void renderAttachments(String modId, List attachments, Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
      Iterator var10 = attachments.iterator();

      while(true) {
         CompatibleAttachment compatibleAttachment;
         do {
            if(!var10.hasNext()) {
               return;
            }

            compatibleAttachment = (CompatibleAttachment)var10.next();
         } while(compatibleAttachment == null);

         Iterator var12 = compatibleAttachment.getAttachment().getTexturedModels().iterator();

         while(var12.hasNext()) {
            Tuple texturedModel = (Tuple)var12.next();
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(modId + ":textures/models/" + (String)texturedModel.getV()));
            GL11.glPushMatrix();
            if(compatibleAttachment.getModelPositioning() != null) {
               compatibleAttachment.getModelPositioning().accept(texturedModel.getU());
            }

            ((ModelBase)texturedModel.getU()).render(entity, f, f1, f2, f3, f4, f5);
            GL11.glPopMatrix();
         }
      }
   }
}
