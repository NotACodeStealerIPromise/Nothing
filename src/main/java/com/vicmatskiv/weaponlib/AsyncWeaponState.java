package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.WeaponState;

public class AsyncWeaponState {
   private WeaponState state;
   private long timestamp;
   private long duration;
   private boolean isInfinite;

   public AsyncWeaponState(WeaponState state, long timestamp) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = 2147483647L;
      this.isInfinite = true;
   }

   public AsyncWeaponState(WeaponState state, long timestamp, long duration) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = duration;
   }

   public WeaponState getState() {
      return this.state;
   }

   public long getTimestamp() {
      return this.timestamp;
   }

   public long getDuration() {
      return this.duration;
   }

   public boolean isInfinite() {
      return this.isInfinite;
   }
}
