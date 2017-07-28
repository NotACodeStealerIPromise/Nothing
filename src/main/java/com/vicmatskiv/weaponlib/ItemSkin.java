package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.model.FlatModel;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.client.model.ModelBase;
import org.lwjgl.opengl.GL11;

public class ItemSkin extends ItemAttachment {
   private List textureVariants;

   public ItemSkin(String modId, AttachmentCategory category, ModelBase model, String textureName, String crosshair, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
      super(modId, category, model, textureName, crosshair, apply, remove);
   }

   public String getTextureName() {
      return this.textureName;
   }

   public int getTextureVariantIndex(String name) {
      return this.textureVariants.indexOf(name.toLowerCase());
   }

   public String getTextureVariant(int textureIndex) {
      return textureIndex >= 0 && textureIndex < this.textureVariants.size()?(String)this.textureVariants.get(textureIndex):null;
   }

   public static final class Builder extends AttachmentBuilder {
      private List textureVariants = new ArrayList();

      public ItemSkin.Builder withTextureVariant(String... textureVariantNames) {
         String[] var2 = textureVariantNames;
         int var3 = textureVariantNames.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            String s = var2[var4];
            this.textureVariants.add(stripFileExtension(s.toLowerCase(), ".png"));
         }

         return this;
      }

      protected ItemAttachment createAttachment(ModContext modContext) {
         ItemSkin skin = new ItemSkin(this.getModId(), AttachmentCategory.SKIN, this.getModel(), this.getTextureName(), (String)null, (ItemAttachment.ApplyHandler)null, (ItemAttachment.ApplyHandler)null);
         skin.textureVariants = this.textureVariants;
         return skin;
      }

      public ItemAttachment build(ModContext modContext, Class target) {
         this.model = new FlatModel();
         if(this.textureVariants.isEmpty()) {
            this.textureVariants.add(this.getTextureName());
         } else if(this.getTextureName() == null) {
            this.textureName = (String)this.textureVariants.get(0);
         }

         if(this.inventoryPositioning == null) {
            this.withInventoryPositioning((itemStack) -> {
               GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
               GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
               GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
               GL11.glTranslatef(-0.6F, -0.6F, 0.0F);
               GL11.glScaled(15.0D, 15.0D, 15.0D);
            });
         }

         return super.build(modContext, target);
      }
   }
}
