package com.vicmatskiv.weaponlib.tracking;

import com.vicmatskiv.weaponlib.compatibility.CompatiblePlayerEntityTrackerProvider;
import com.vicmatskiv.weaponlib.tracking.TrackableEntity;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PlayerEntityTracker {
   private static final Logger logger = LogManager.getLogger(PlayerEntityTracker.class);
   private World world;
   private Map trackableEntities = new LinkedHashMap();

   public static final PlayerEntityTracker getTracker(EntityPlayer player) {
      return CompatiblePlayerEntityTrackerProvider.getTracker(player);
   }

   public PlayerEntityTracker(World world) {
      this.world = world;
   }

   public PlayerEntityTracker() {
   }

   void init(World world) {
      this.world = world;
   }

   public void addTrackableEntity(TrackableEntity te) {
      this.update();
      this.trackableEntities.put(te.getEntity().getPersistentID(), te);
   }

   public boolean updateTrackableEntity(Entity entity) {
      this.update();
      TrackableEntity te = (TrackableEntity)this.trackableEntities.get(entity.getPersistentID());
      if(te != null) {
         te.setEntitySupplier(() -> {
            return entity;
         });
         return true;
      } else {
         return false;
      }
   }

   public Collection getTrackableEntitites() {
      return Collections.unmodifiableCollection(this.trackableEntities.values());
   }

   public void update() {
      Iterator it = this.trackableEntities.values().iterator();

      while(it.hasNext()) {
         TrackableEntity te = (TrackableEntity)it.next();
         if(te.isExpired()) {
            it.remove();
         }
      }

   }

   public TrackableEntity getTrackableEntity(int index) {
      Collection values = this.trackableEntities.values();
      int i = 0;
      TrackableEntity result = null;

      for(Iterator it = values.iterator(); it.hasNext(); ++i) {
         TrackableEntity te = (TrackableEntity)it.next();
         if(i == index) {
            result = te;
            break;
         }
      }

      return result;
   }

   public void serialize(ByteBuf buf) {
      this.update();
      buf.writeInt(this.trackableEntities.size());
      Iterator var2 = this.trackableEntities.values().iterator();

      while(var2.hasNext()) {
         TrackableEntity te = (TrackableEntity)var2.next();
         te.serialize(buf, this.world);
      }

   }

   private void init(ByteBuf buf) {
      int trackableEntitiesSize = buf.readInt();

      for(int i = 0; i < trackableEntitiesSize; ++i) {
         try {
            TrackableEntity e = TrackableEntity.fromBuf(buf, this.world);
            this.trackableEntities.put(e.getUuid(), e);
         } catch (RuntimeException var5) {
            logger.error("Failed to deserialize trackable entity {}", new Object[]{var5.toString(), var5});
         }
      }

   }

   public byte[] toByteArray() {
      ByteBuf buf = Unpooled.buffer();
      this.serialize(buf);
      return buf.array();
   }

   public static PlayerEntityTracker fromByteArray(byte[] bytes, World world) {
      ByteBuf buf = Unpooled.wrappedBuffer(bytes);
      PlayerEntityTracker tracker = new PlayerEntityTracker(world);
      if(bytes != null && bytes.length > 0) {
         tracker.init(buf);
      } else {
         logger.warn("Cannot deserialize tracker from empty byte array");
      }

      return tracker;
   }

   public static PlayerEntityTracker fromBuf(ByteBuf buf, World world) {
      PlayerEntityTracker tracker = new PlayerEntityTracker(world);
      tracker.init(buf);
      return tracker;
   }
}
