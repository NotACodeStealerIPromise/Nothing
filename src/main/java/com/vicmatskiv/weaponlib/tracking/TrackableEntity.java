package com.vicmatskiv.weaponlib.tracking;

import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import io.netty.buffer.ByteBuf;
import java.lang.ref.WeakReference;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TrackableEntity {
   private static final Logger logger = LogManager.getLogger(TrackableEntity.class);
   private Supplier entitySupplier;
   private long startTimestamp;
   private UUID uuid;
   private int entityId;
   private long trackingDuration;
   private WeakReference entityRef;
   private String displayName = "";

   private TrackableEntity() {
   }

   public TrackableEntity(Entity entity, long startTimestamp, long trackingDuration) {
      this.uuid = entity.getPersistentID();
      this.entityId = entity.getEntityId();
      this.entitySupplier = () -> {
         return entity;
      };
      this.startTimestamp = startTimestamp;
      this.trackingDuration = trackingDuration;
   }

   public UUID getUuid() {
      if(this.uuid != null) {
         return this.uuid;
      } else {
         Entity entity = this.getEntity();
         return entity != null?entity.getPersistentID():null;
      }
   }

   public void setEntitySupplier(Supplier entitySupplier) {
      this.entitySupplier = entitySupplier;
      this.entityId = -1;
      this.entityRef = null;
   }

   public Entity getEntity() {
      if(this.entityRef == null || this.entityRef.get() == null) {
         Entity entity = (Entity)this.entitySupplier.get();
         if(entity != null) {
            if(entity instanceof EntityPlayer) {
               this.displayName = CompatibilityProvider.compatibility.getDisplayName((EntityPlayer)entity);
            } else if(entity instanceof EntityLivingBase) {
               this.displayName = EntityList.getEntityString(entity);
            }

            this.entityId = entity.getEntityId();
         }

         this.entityRef = new WeakReference(entity);
      }

      return (Entity)this.entityRef.get();
   }

   public static TrackableEntity fromBuf(ByteBuf buf, World world) {
      TrackableEntity te = new TrackableEntity();
      te.init(buf, world);
      return te;
   }

   public void init(ByteBuf buf, World world) {
      this.uuid = new UUID(buf.readLong(), buf.readLong());
      this.entityId = buf.readInt();
      this.startTimestamp = buf.readLong();
      this.trackingDuration = buf.readLong();
      if(world.isRemote) {
         logger.debug("Initializing client entity uuid {}, id {}", new Object[]{this.uuid, Integer.valueOf(this.entityId)});
         this.entitySupplier = () -> {
            return world.getEntityByID(this.entityId);
         };
      } else {
         logger.debug("Initializing server entity uuid {}, id {}", new Object[]{this.uuid, Integer.valueOf(this.entityId)});
         this.entitySupplier = () -> {
            return this.getEntityByUuid(this.uuid, world);
         };
      }

   }

   public void serialize(ByteBuf buf, World world) {
      buf.writeLong(this.uuid.getMostSignificantBits());
      buf.writeLong(this.uuid.getLeastSignificantBits());
      Entity entity = this.getEntity();
      int entityId = -1;
      if(entity != null) {
         entityId = entity.getEntityId();
      }

      logger.debug("Serializing server entity uuid {}, id {}", new Object[]{this.uuid, Integer.valueOf(entityId)});
      buf.writeInt(entityId);
      buf.writeLong(this.startTimestamp);
      buf.writeLong(this.trackingDuration);
   }

   private Entity getEntityByUuid(UUID uuid, World world) {
      return (Entity)world.getLoadedEntityList().stream().filter((e) -> {
         return e.equals(((Entity)e).getPersistentID());
      }).findAny().orElse((Object)null);
   }

   public boolean isExpired() {
      return this.startTimestamp + this.trackingDuration < System.currentTimeMillis();
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public long getTrackingDuration() {
      return this.trackingDuration;
   }

   public long getStartTimestamp() {
      return this.startTimestamp;
   }
}
