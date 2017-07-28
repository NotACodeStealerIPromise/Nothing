package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class CompatibleEntityJoinWorldEvent {
   private EntityJoinWorldEvent event;

   public CompatibleEntityJoinWorldEvent(EntityJoinWorldEvent event) {
      this.event = event;
   }

   public World getWorld() {
      return this.event.world;
   }

   public Entity getEntity() {
      return this.event.entity;
   }
}