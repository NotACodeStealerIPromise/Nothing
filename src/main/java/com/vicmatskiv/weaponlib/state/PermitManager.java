package com.vicmatskiv.weaponlib.state;

import com.vicmatskiv.weaponlib.state.ExtendedState;
import com.vicmatskiv.weaponlib.state.Permit;
import java.util.function.BiConsumer;

public interface PermitManager {
   void request(Permit var1, ExtendedState var2, BiConsumer var3);

   void registerEvaluator(Class var1, Class var2, BiConsumer var3);
}
