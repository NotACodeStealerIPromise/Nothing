package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.RenderableState;

class ExpirableRenderableState {
   RenderableState state;
   long expiresAt;
   boolean singleUse;

   public ExpirableRenderableState(RenderableState state, long expiresAt, boolean singleUse) {
      this.state = state;
      this.expiresAt = expiresAt;
      this.singleUse = singleUse;
   }
}
