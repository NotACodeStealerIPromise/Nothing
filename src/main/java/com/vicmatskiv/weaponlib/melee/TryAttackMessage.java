package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.compatibility.CompatibleMessage;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.network.TypeRegistry;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class TryAttackMessage implements CompatibleMessage {
   private int entityId;
   private boolean isHeavyAttack;
   private PlayerMeleeInstance instance;

   public TryAttackMessage() {
   }

   public TryAttackMessage(PlayerMeleeInstance instance, Entity entity, boolean isHeavyAttack) {
      this.instance = instance;
      this.entityId = entity.getEntityId();
      this.isHeavyAttack = isHeavyAttack;
   }

   public void fromBytes(ByteBuf buf) {
      this.instance = (PlayerMeleeInstance)TypeRegistry.getInstance().fromBytes(buf);
      this.entityId = buf.readInt();
      this.isHeavyAttack = buf.readBoolean();
   }

   public void toBytes(ByteBuf buf) {
      TypeRegistry.getInstance().toBytes(this.instance, buf);
      buf.writeInt(this.entityId);
      buf.writeBoolean(this.isHeavyAttack);
   }

   public Entity getEntity(World world) {
      return world.getEntityByID(this.entityId);
   }

   public boolean isHeavyAttack() {
      return this.isHeavyAttack;
   }

   public PlayerMeleeInstance getInstance() {
      return this.instance;
   }
}
