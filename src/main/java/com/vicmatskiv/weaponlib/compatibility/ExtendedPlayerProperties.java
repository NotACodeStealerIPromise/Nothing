package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import io.netty.buffer.ByteBuf;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExtendedPlayerProperties implements IExtendedEntityProperties {
   private static final Logger logger = LogManager.getLogger(ExtendedPlayerProperties.class);
   private static final String TAG_TRACKER = "tracker";
   private static final String EXTENDED_PROPERTY_NAME = ExtendedPlayerProperties.class.getName();
   private boolean initialized;
   private World world;
   private ExtendedPlayerProperties.Container entityTrackerContainer = new ExtendedPlayerProperties.Container();

   public static final ExtendedPlayerProperties getProperties(EntityPlayer player) {
      if(player == null) {
         return null;
      } else {
         ExtendedPlayerProperties properties = (ExtendedPlayerProperties)player.getExtendedProperties(EXTENDED_PROPERTY_NAME);
         if(properties != null && !properties.initialized) {
            properties.init(player, CompatibilityProvider.compatibility.world(player));
         }

         return properties;
      }
   }

   public static final void init(EntityPlayer player) {
      ExtendedPlayerProperties properties = new ExtendedPlayerProperties();
      properties.init(player, CompatibilityProvider.compatibility.world(player));
      player.registerExtendedProperties(EXTENDED_PROPERTY_NAME, properties);
   }

   public static final void set(EntityPlayer player, ExtendedPlayerProperties properties) {
      ExtendedPlayerProperties existingProperties = getProperties(player);
      if(existingProperties != null) {
         existingProperties.copyFrom(properties);
      } else {
         player.registerExtendedProperties(EXTENDED_PROPERTY_NAME, properties);
      }

   }

   public static final ExtendedPlayerProperties fromBuf(ByteBuf buf) {
      return new ExtendedPlayerProperties();
   }

   private void copyFrom(ExtendedPlayerProperties properties) {
      this.entityTrackerContainer = properties.entityTrackerContainer;
   }

   public void init(Entity entity, World world) {
      this.world = world;
      this.initialized = true;
      this.entityTrackerContainer.initializer = (w) -> {
         return new PlayerEntityTracker(world);
      };
   }

   public void saveNBTData(NBTTagCompound playerTagCompound) {
      if(this.initialized) {
         NBTTagCompound tagCompound = new NBTTagCompound();
         PlayerEntityTracker playerEntityTracker = (PlayerEntityTracker)this.entityTrackerContainer.get(this.world);
         if(playerEntityTracker != null) {
            tagCompound.setByteArray("tracker", playerEntityTracker.toByteArray());
         }

         playerTagCompound.setTag(EXTENDED_PROPERTY_NAME, tagCompound);
      }
   }

   public void loadNBTData(NBTTagCompound playerTagCompound) {
      NBTTagCompound tagCompound = playerTagCompound.getCompoundTag(EXTENDED_PROPERTY_NAME);
      if(tagCompound != null) {
         byte[] bytes = tagCompound.getByteArray("tracker");
         if(bytes != null) {
            this.entityTrackerContainer.initializer = (w) -> {
               return PlayerEntityTracker.fromByteArray(bytes, w);
            };
         }
      }

   }

   public PlayerEntityTracker getTracker() {
      return !this.initialized?null:(PlayerEntityTracker)this.entityTrackerContainer.get(this.world);
   }

   public void serialize(ByteBuf buf) {
      if(this.initialized) {
         PlayerEntityTracker et = (PlayerEntityTracker)this.entityTrackerContainer.get(this.world);
         if(et != null) {
            et.serialize(buf);
         }

      }
   }

   public void setTracker(PlayerEntityTracker tracker) {
      this.entityTrackerContainer = new ExtendedPlayerProperties.Container((w) -> {
         return tracker;
      });
   }

   private static class Container {
      Function initializer;
      Object resolved;

      Container() {
      }

      Container(Function initializer) {
         this.initializer = initializer;
      }

      public Object get(Object i) {
         if(this.initializer == null) {
            return null;
         } else {
            if(this.resolved == null) {
               this.resolved = this.initializer.apply(i);
            }

            return this.resolved;
         }
      }
   }
}
