package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.Compatibility;

public final class CompatibilityProvider {
   public static Compatibility compatibility = initCompatibility();

   private static Compatibility initCompatibility() {
      String compatibilityClassName = Compatibility.class.getName() + "1_7_10";

      try {
         return (Compatibility)Class.forName(compatibilityClassName).newInstance();
      } catch (IllegalAccessException | ClassNotFoundException | InstantiationException var2) {
         throw new IllegalStateException("Cannot find compatible implementation class " + compatibilityClassName);
      }
   }
}
