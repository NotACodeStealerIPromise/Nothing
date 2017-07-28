package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.ModContext;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.model.ModelBase;

public class ItemBullet extends ItemAttachment {
   private List compatibleMagazines = new ArrayList();

   public ItemBullet(String modId, AttachmentCategory category, ModelBase model, String textureName, String crosshair, ItemAttachment.ApplyHandler apply, ItemAttachment.ApplyHandler remove) {
      super(modId, category, model, textureName, crosshair, apply, remove);
   }

   public static final class Builder extends AttachmentBuilder {
      private List compatibleMagazines = new ArrayList();

      public Builder() {
         this.withMaxStackSize(64);
      }

      public ItemBullet.Builder withCompatibleMagazine(ItemMagazine magazine) {
         this.compatibleMagazines.add(magazine);
         return this;
      }

      protected ItemAttachment createAttachment(ModContext modContext) {
         ItemBullet bullet = new ItemBullet(this.getModId(), AttachmentCategory.BULLET, this.getModel(), this.getTextureName(), (String)null, (ItemAttachment.ApplyHandler)null, (ItemAttachment.ApplyHandler)null);
         bullet.compatibleMagazines = this.compatibleMagazines;
         return bullet;
      }
   }
}
