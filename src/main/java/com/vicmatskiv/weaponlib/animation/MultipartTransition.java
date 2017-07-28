package com.vicmatskiv.weaponlib.animation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class MultipartTransition {
   private static final Consumer ANCHORED_POSITION = (c) -> {
   };
   private Map multipartPositionFunctions;
   private Map attachedTo;
   private long duration;
   private long pause;

   public static Consumer anchoredPosition() {
      return ANCHORED_POSITION;
   }

   public MultipartTransition(Object part, Consumer positionFunction, long duration, long pause) {
      this.multipartPositionFunctions = new HashMap();
      this.attachedTo = new HashMap();
      this.duration = duration;
      this.pause = pause;
      this.multipartPositionFunctions.put(part, positionFunction);
   }

   public MultipartTransition(Object part, Consumer positionFunction, long duration) {
      this(part, positionFunction, duration, 0L);
   }

   public MultipartTransition(long duration, long pause) {
      this.multipartPositionFunctions = new HashMap();
      this.attachedTo = new HashMap();
      this.duration = duration;
      this.pause = pause;
   }

   public MultipartTransition(long duration) {
      this(duration, 0L);
   }

   public MultipartTransition withPartPositionFunction(Object part, Consumer positionFunction) {
      return this.withPartPositionFunction(part, (Object)null, positionFunction);
   }

   public MultipartTransition withPartPositionFunction(Object part, Object attachedTo, Consumer positionFunction) {
      this.multipartPositionFunctions.put(part, positionFunction);
      this.attachedTo.put(part, attachedTo);
      return this;
   }

   public void position(Object part, Object context) {
      Consumer positionFunction = (Consumer)this.multipartPositionFunctions.get(part);
      if(positionFunction == null) {
         throw new IllegalArgumentException("Don\'t know anything about part " + part);
      } else {
         positionFunction.accept(context);
      }
   }

   public Consumer getPositioning(Object part) {
      return (Consumer)this.multipartPositionFunctions.get(part);
   }

   public long getDuration() {
      return this.duration;
   }

   public long getPause() {
      return this.pause;
   }

   public Object getAttachedTo(Object part) {
      return this.attachedTo.get(part);
   }
}
