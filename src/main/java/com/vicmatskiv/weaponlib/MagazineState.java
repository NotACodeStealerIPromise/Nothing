package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum MagazineState implements ManagedState {
   READY(false),
   LOAD_REQUESTED,
   LOAD;

   private static final int DEFAULT_PRIORITY = 0;
   private MagazineState preparingPhase;
   private MagazineState permitRequestedPhase;
   private MagazineState commitPhase;
   private boolean isTransient;
   private int priority;

   private MagazineState() {
      this((MagazineState)null, (MagazineState)null, (MagazineState)null, true);
   }

   private MagazineState(int priority) {
      this(priority, (MagazineState)null, (MagazineState)null, (MagazineState)null, true);
   }

   private MagazineState(boolean isTransient) {
      this((MagazineState)null, (MagazineState)null, (MagazineState)null, isTransient);
   }

   private MagazineState(MagazineState preparingPhase, MagazineState permitRequestedState, MagazineState transactionFinalState, boolean isTransient) {
      this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
   }

   private MagazineState(int priority, MagazineState preparingPhase, MagazineState permitRequestedState, MagazineState transactionFinalState, boolean isTransient) {
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

   public MagazineState preparingPhase() {
      return this.preparingPhase;
   }

   public MagazineState permitRequestedPhase() {
      return this.permitRequestedPhase;
   }

   public MagazineState commitPhase() {
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
      LOAD = new MagazineState("LOAD", 2, (MagazineState)null, LOAD_REQUESTED, (MagazineState)null, true);
      TypeRegistry.getInstance().register(MagazineState.class);
   }
}
