package com.vicmatskiv.weaponlib.animation;

import java.util.Queue;

public interface MultipartPositioning {
   Object getFromState(Class var1);

   Object getToState(Class var1);

   boolean isExpired(Queue var1);

   MultipartPositioning.Positioner getPositioner();

   float getProgress();

   public interface Positioner {
      void position(Object var1, Object var2);

      default void randomize(float rate, float amplitude) {
      }
   }
}
