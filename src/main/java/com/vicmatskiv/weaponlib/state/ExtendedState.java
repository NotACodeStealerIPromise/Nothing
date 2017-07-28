package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.state.ManagedState;

public interface ExtendedState {
   boolean setState(ManagedState var1);

   ManagedState getState();

   long getStateUpdateTimestamp();

   void prepareTransaction(ExtendedState var1);
}
