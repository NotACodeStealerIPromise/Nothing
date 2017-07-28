package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.network.TypeRegistry;
import com.vicmatskiv.weaponlib.state.ManagedState;
import io.netty.buffer.ByteBuf;

public enum WeaponState implements ManagedState {
   READY(false),
   LOAD_REQUESTED,
   LOAD,
   UNLOAD_PREPARING,
   UNLOAD_REQUESTED,
   UNLOAD,
   FIRING,
   RECOILED,
   PAUSED,
   EJECT_REQUIRED,
   EJECTING,
   MODIFYING_REQUESTED,
   MODIFYING,
   NEXT_ATTACHMENT_REQUESTED,
   NEXT_ATTACHMENT,
   ALERT;

   private static final int DEFAULT_PRIORITY = 0;
   private WeaponState preparingPhase;
   private WeaponState permitRequestedPhase;
   private WeaponState commitPhase;
   private boolean isTransient;
   private int priority;

   private WeaponState() {
      this((WeaponState)null, (WeaponState)null, (WeaponState)null, true);
   }

   private WeaponState(int priority) {
      this(priority, (WeaponState)null, (WeaponState)null, (WeaponState)null, true);
   }

   private WeaponState(boolean isTransient) {
      this((WeaponState)null, (WeaponState)null, (WeaponState)null, isTransient);
   }

   private WeaponState(WeaponState preparingPhase, WeaponState permitRequestedState, WeaponState transactionFinalState, boolean isTransient) {
      this(0, preparingPhase, permitRequestedState, transactionFinalState, isTransient);
   }

   private WeaponState(int priority, WeaponState preparingPhase, WeaponState permitRequestedState, WeaponState transactionFinalState, boolean isTransient) {
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

   public WeaponState preparingPhase() {
      return this.preparingPhase;
   }

   public WeaponState permitRequestedPhase() {
      return this.permitRequestedPhase;
   }

   public WeaponState commitPhase() {
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
      LOAD = new WeaponState("LOAD", 2, (WeaponState)null, LOAD_REQUESTED, (WeaponState)null, true);
      UNLOAD_PREPARING = new WeaponState("UNLOAD_PREPARING", 3);
      UNLOAD_REQUESTED = new WeaponState("UNLOAD_REQUESTED", 4);
      UNLOAD = new WeaponState("UNLOAD", 5, UNLOAD_PREPARING, UNLOAD_REQUESTED, READY, true);
      FIRING = new WeaponState("FIRING", 6, 9);
      RECOILED = new WeaponState("RECOILED", 7, 10);
      PAUSED = new WeaponState("PAUSED", 8, 10);
      EJECT_REQUIRED = new WeaponState("EJECT_REQUIRED", 9);
      EJECTING = new WeaponState("EJECTING", 10);
      MODIFYING_REQUESTED = new WeaponState("MODIFYING_REQUESTED", 11, 1);
      MODIFYING = new WeaponState("MODIFYING", 12, 2, (WeaponState)null, MODIFYING_REQUESTED, (WeaponState)null, false);
      NEXT_ATTACHMENT_REQUESTED = new WeaponState("NEXT_ATTACHMENT_REQUESTED", 13);
      NEXT_ATTACHMENT = new WeaponState("NEXT_ATTACHMENT", 14, 2, (WeaponState)null, NEXT_ATTACHMENT_REQUESTED, (WeaponState)null, false);
      ALERT = new WeaponState("ALERT", 15);
      TypeRegistry.getInstance().register(WeaponState.class);
   }
}
