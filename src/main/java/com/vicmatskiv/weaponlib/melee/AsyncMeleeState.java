package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.melee.MeleeState;

public class AsyncMeleeState {
   private MeleeState state;
   private long timestamp;
   private long duration;
   private boolean isInfinite;

   public AsyncMeleeState(MeleeState state, long timestamp) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = 2147483647L;
      this.isInfinite = true;
   }

   public AsyncMeleeState(MeleeState state, long timestamp, long duration) {
      this.state = state;
      this.timestamp = timestamp;
      this.duration = duration;
   }

   public MeleeState getState() {
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
