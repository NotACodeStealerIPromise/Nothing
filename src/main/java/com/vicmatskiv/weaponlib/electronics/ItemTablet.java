package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.AttachmentBuilder;
import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerItemInstanceFactory;
import com.vicmatskiv.weaponlib.Updatable;
import com.vicmatskiv.weaponlib.electronics.PlayerTabletInstance;
import com.vicmatskiv.weaponlib.electronics.TabletState;
import com.vicmatskiv.weaponlib.perspective.PerspectiveRenderer;
import java.util.function.BiConsumer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class ItemTablet extends ItemAttachment implements PlayerItemInstanceFactory, Updatable {
   private final int DEFAULT_MAX_STACK_SIZE;
   private ModContext modContext;
   private ItemTablet.Builder builder;

   private ItemTablet(ItemTablet.Builder builder) {
      super(builder.getModId(), AttachmentCategory.SCOPE, builder.getModel(), builder.getTextureName(), (String)null, (ItemAttachment.ApplyHandler)null, (ItemAttachment.ApplyHandler)null);
      this.DEFAULT_MAX_STACK_SIZE = 1;
      this.builder = builder;
      this.setMaxStackSize(1);
   }

   public float getMinZoom() {
      return this.builder.minZoom;
   }

   public float getMaxZoom() {
      return this.builder.maxZoom;
   }

   public boolean isOptical() {
      return this.builder.isOpticalZoom;
   }

   public void update(EntityPlayer player) {
   }

   public PlayerTabletInstance createItemInstance(EntityPlayer player, ItemStack stack, int slot) {
      PlayerTabletInstance instance = new PlayerTabletInstance(slot, player, stack);
      instance.setState(TabletState.READY);
      return instance;
   }

   // $FF: synthetic method
   ItemTablet(ItemTablet.Builder x0, Object x1) {
      this(x0);
   }

   public static final class Builder extends AttachmentBuilder {
      private float minZoom;
      private float maxZoom;
      private boolean isOpticalZoom;
      private BiConsumer viewfinderPositioning;

      public ItemTablet.Builder withZoomRange(float minZoom, float maxZoom) {
         this.minZoom = minZoom;
         this.maxZoom = maxZoom;
         return this;
      }

      public ItemTablet.Builder withOpticalZoom() {
         this.isOpticalZoom = true;
         return this;
      }

      public ItemTablet.Builder withViewfinderPositioning(BiConsumer viewfinderPositioning) {
         this.viewfinderPositioning = viewfinderPositioning;
         return this;
      }

      protected ItemAttachment createAttachment(ModContext modContext) {
         if(this.isOpticalZoom) {
            if(this.viewfinderPositioning == null) {
               this.viewfinderPositioning = (p, s) -> {
                  GL11.glScalef(3.0F, 3.0F, 3.0F);
                  GL11.glTranslatef(0.1F, 0.5F, 0.1F);
               };
            }

            this.withPostRender(new PerspectiveRenderer(this.viewfinderPositioning));
         }

         ItemTablet itemTablet = new ItemTablet(this);
         itemTablet.modContext = modContext;
         return itemTablet;
      }

      public ItemAttachment build(ModContext modContext) {
         this.apply2 = (a, instance) -> {
            float zoom = this.minZoom + (this.maxZoom - this.minZoom) / 2.0F;
            instance.setZoom(zoom);
         };
         this.remove2 = (a, instance) -> {
            instance.setZoom(1.0F);
         };
         return super.build(modContext);
      }
   }
}
