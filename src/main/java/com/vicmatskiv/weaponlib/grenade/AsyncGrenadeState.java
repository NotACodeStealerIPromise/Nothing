package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.grenade.GrenadeState;

public class AsyncGrenadeState {
   private GrenadeState state;
   private long timestamp;
   private long duration;
   private boolean isInfinite;

   public AsyncGrenadeState(GrenadeState state, long timestamp) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = 2147483647L;
      this.isInfinite = true;
   }

   public AsyncGrenadeState(GrenadeState state, long timestamp, long duration) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = duration;
   }

   public GrenadeState getState() {
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
