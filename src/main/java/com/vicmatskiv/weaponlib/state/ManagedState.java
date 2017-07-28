package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.network.UniversallySerializable;

public interface ManagedState extends UniversallySerializable {
   default ManagedState preparingPhase() {
      return null;
   }

   default ManagedState permitRequestedPhase() {
      return null;
   }

   default ManagedState commitPhase() {
      return null;
   }

   default boolean isTransient() {
      return false;
   }

   int ordinal();

   default boolean matches(ManagedState mainState) {
      return mainState == this || mainState == this.preparingPhase() || mainState == this.permitRequestedPhase();
   }
}
