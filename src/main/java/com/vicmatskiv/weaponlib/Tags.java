package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.item.ItemStack;

public final class Tags {
   private static final String AMMO_TAG = "Ammo";
   private static final String DEFAULT_TIMER_TAG = "DefaultTimer";
   private static final String INSTANCE_TAG = "Instance";

   static int getAmmo(ItemStack itemStack) {
      return itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null?CompatibilityProvider.compatibility.getTagCompound(itemStack).getInteger("Ammo"):0;
   }

   static void setAmmo(ItemStack itemStack, int ammo) {
      if(itemStack != null) {
         CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
         CompatibilityProvider.compatibility.getTagCompound(itemStack).setInteger("Ammo", ammo);
      }
   }

   public static long getDefaultTimer(ItemStack itemStack) {
      return itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null?CompatibilityProvider.compatibility.getTagCompound(itemStack).getLong("DefaultTimer"):0L;
   }

   static void setDefaultTimer(ItemStack itemStack, long ammo) {
      if(itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null) {
         CompatibilityProvider.compatibility.getTagCompound(itemStack).setLong("DefaultTimer", ammo);
      }
   }

   public static PlayerItemInstance getInstance(ItemStack itemStack) {
      if(itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null) {
         byte[] bytes = CompatibilityProvider.compatibility.getTagCompound(itemStack).getByteArray("Instance");
         return bytes != null && bytes.length > 0?(PlayerItemInstance)TypeRegistry.getInstance().fromBytes(Unpooled.wrappedBuffer(bytes)):null;
      } else {
         return null;
      }
   }

   public static PlayerItemInstance getInstance(ItemStack itemStack, Class targetClass) {
      if(itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null) {
         byte[] bytes = CompatibilityProvider.compatibility.getTagCompound(itemStack).getByteArray("Instance");
         if(bytes != null && bytes.length > 0) {
            try {
               return (PlayerItemInstance)targetClass.cast(TypeRegistry.getInstance().fromBytes(Unpooled.wrappedBuffer(bytes)));
            } catch (RuntimeException var4) {
               return null;
            }
         } else {
            return null;
         }
      } else {
         return null;
      }
   }

   static void setInstance(ItemStack itemStack, PlayerItemInstance instance) {
      if(itemStack != null) {
         CompatibilityProvider.compatibility.ensureTagCompound(itemStack);
         ByteBuf buf = Unpooled.buffer();
         if(instance != null) {
            TypeRegistry.getInstance().toBytes(instance, buf);
            CompatibilityProvider.compatibility.getTagCompound(itemStack).setByteArray("Instance", buf.array());
         } else {
            CompatibilityProvider.compatibility.getTagCompound(itemStack).removeTag("Instance");
         }

      }
   }

   public static byte[] getInstanceBytes(ItemStack itemStack) {
      return itemStack != null && CompatibilityProvider.compatibility.getTagCompound(itemStack) != null?CompatibilityProvider.compatibility.getTagCompound(itemStack).getByteArray("Instance"):null;
   }
}
