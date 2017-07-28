package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Part;

public final class DefaultPart implements Part {
   private String name;

   public DefaultPart(String name) {
      this.name = name;
   }

   public String toString() {
      return "[" + this.name + "]";
   }
}
