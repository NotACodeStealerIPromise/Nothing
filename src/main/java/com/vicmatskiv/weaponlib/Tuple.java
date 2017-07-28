package com.vicmatskiv.weaponlib;

public final class Tuple {
   private Object u;
   private Object v;

   public Tuple(Object u, Object v) {
      this.u = u;
      this.v = v;
   }

   public Object getU() {
      return this.u;
   }

   public void setU(Object u) {
      this.u = u;
   }

   public Object getV() {
      return this.v;
   }

   public void setV(Object v) {
      this.v = v;
   }
}
