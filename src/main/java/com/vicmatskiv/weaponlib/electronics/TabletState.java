package com.vicmatskiv.weaponlib.electronics;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum TabletState implements ManagedState {
   READY(false),
   MODIFYING_REQUESTED(1),
   MODIFYING,
   NEXT_ATTACHMENT_REQUESTED,
   NEXT_ATTACHMENT,
   ALERT;

   private static final int DEFAULT_PRIORITY = 0;
   private TabletState preparingPhase;
   private TabletState permitRequestedPhase;
   private TabletState commitPhase;
   private boolean isTransient;
   private int priority;

   private TabletState() {
      this((TabletState)null, (TabletState)null, (TabletState)null, true);
   }

   private TabletState(int priority) {
      this(priority, (TabletState)null, (TabletState)null, (TabletState)null, true);
   }

   private TabletState(boolean isTransient) {
      this((TabletState)null, (TabletState)null, (TabletState)null, isTransient);
   }

   private TabletState(TabletState preparingPhase, TabletState permitRequestedState, TabletState transactionFinalState, boolean isTransient) {
      this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
   }

   private TabletState(int priority, TabletState preparingPhase, TabletState permitRequestedState, TabletState transactionFinalState, boolean isTransient) {
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

   public TabletState preparingPhase() {
      return this.preparingPhase;
   }

   public TabletState permitRequestedPhase() {
      return this.permitRequestedPhase;
   }

   public TabletState commitPhase() {
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
      MODIFYING = new TabletState("MODIFYING", 2, 2, (TabletState)null, MODIFYING_REQUESTED, (TabletState)null, false);
      NEXT_ATTACHMENT_REQUESTED = new TabletState("NEXT_ATTACHMENT_REQUESTED", 3);
      NEXT_ATTACHMENT = new TabletState("NEXT_ATTACHMENT", 4, 2, (TabletState)null, NEXT_ATTACHMENT_REQUESTED, (TabletState)null, false);
      ALERT = new TabletState("ALERT", 5);
      TypeRegistry.getInstance().register(TabletState.class);
   }
}
