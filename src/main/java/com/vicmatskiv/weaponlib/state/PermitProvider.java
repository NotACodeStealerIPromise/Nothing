package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.state.ManagedState;
import com.vicmatskiv.weaponlib.state.Permit;

public interface PermitProvider {
   void set(Permit var1, ManagedState var2, Object var3);

   Permit get(ManagedState var1, Object var2);
}
