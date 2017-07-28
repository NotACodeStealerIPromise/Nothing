package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.state.PermitManager;
import com.vicmatskiv.weaponlib.state.StateManager;

public interface Aspect {
   void setStateManager(StateManager var1);

   void setPermitManager(PermitManager var1);
}
