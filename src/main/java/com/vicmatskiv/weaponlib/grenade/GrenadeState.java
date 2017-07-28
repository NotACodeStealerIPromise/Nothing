package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum GrenadeState implements ManagedState {
   READY(false),
   SAFETY_PING_OFF(9),
   STRIKER_LEVER_RELEASED(9),
   THROWING(9),
   THROWN(9),
   EXPLODED_IN_HANDS(9);

   private static final int DEFAULT_PRIORITY = 0;
   private GrenadeState preparingPhase;
   private GrenadeState permitRequestedPhase;
   private GrenadeState commitPhase;
   private boolean isTransient;
   private int priority;

   private GrenadeState() {
      this((GrenadeState)null, (GrenadeState)null, (GrenadeState)null, true);
   }

   private GrenadeState(int priority) {
      this(priority, (GrenadeState)null, (GrenadeState)null, (GrenadeState)null, true);
   }

   private GrenadeState(boolean isTransient) {
      this((GrenadeState)null, (GrenadeState)null, (GrenadeState)null, isTransient);
   }

   private GrenadeState(GrenadeState preparingPhase, GrenadeState permitRequestedState, GrenadeState transactionFinalState, boolean isTransient) {
      this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
   }

   private GrenadeState(int priority, GrenadeState preparingPhase, GrenadeState permitRequestedState, GrenadeState transactionFinalState, boolean isTransient) {
      this.priority = 0;
      this.priority = priority;
      this.preparingPhase = preparingPhase;
      this.permitRequestedPhase = permitRequestedState;
      this.commitPhase = transactionFinalState;
      this.isTransient = false;
   }

   public boolean isTransient() {
      return this.isTransient;
   }

   public GrenadeState preparingPhase() {
      return this.preparingPhase;
   }

   public GrenadeState permitRequestedPhase() {
      return this.permitRequestedPhase;
   }

   public GrenadeState commitPhase() {
      return this.commitPhase;
   }

   public int getPriority() {
      return this.priority;
   }

   public void init(ByteBuf buf) {
   }

   public void serialize(ByteBuf buf) {
   }

   static {
      TypeRegistry.getInstance().register(GrenadeState.class);
   }
}
