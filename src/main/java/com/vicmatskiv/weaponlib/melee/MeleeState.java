package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum MeleeState implements ManagedState {
   READY(false),
   ATTACKING(9),
   ATTACKING_STABBING(9),
   HEAVY_ATTACKING(9),
   HEAVY_ATTACKING_STABBING(9),
   MODIFYING_REQUESTED(1),
   MODIFYING,
   NEXT_ATTACHMENT_REQUESTED,
   NEXT_ATTACHMENT,
   ALERT;

   private static final int DEFAULT_PRIORITY = 0;
   private MeleeState preparingPhase;
   private MeleeState permitRequestedPhase;
   private MeleeState commitPhase;
   private boolean isTransient;
   private int priority;

   private MeleeState() {
      this((MeleeState)null, (MeleeState)null, (MeleeState)null, true);
   }

   private MeleeState(int priority) {
      this(priority, (MeleeState)null, (MeleeState)null, (MeleeState)null, true);
   }

   private MeleeState(boolean isTransient) {
      this((MeleeState)null, (MeleeState)null, (MeleeState)null, isTransient);
   }

   private MeleeState(MeleeState preparingPhase, MeleeState permitRequestedState, MeleeState transactionFinalState, boolean isTransient) {
      this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
   }

   private MeleeState(int priority, MeleeState preparingPhase, MeleeState permitRequestedState, MeleeState transactionFinalState, boolean isTransient) {
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

   public MeleeState preparingPhase() {
      return this.preparingPhase;
   }

   public MeleeState permitRequestedPhase() {
      return this.permitRequestedPhase;
   }

   public MeleeState commitPhase() {
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
      MODIFYING = new MeleeState("MODIFYING", 6, 2, (MeleeState)null, MODIFYING_REQUESTED, (MeleeState)null, false);
      NEXT_ATTACHMENT_REQUESTED = new MeleeState("NEXT_ATTACHMENT_REQUESTED", 7);
      NEXT_ATTACHMENT = new MeleeState("NEXT_ATTACHMENT", 8, 2, (MeleeState)null, NEXT_ATTACHMENT_REQUESTED, (MeleeState)null, false);
      ALERT = new MeleeState("ALERT", 9);
      TypeRegistry.getInstance().register(MeleeState.class);
   }
}
