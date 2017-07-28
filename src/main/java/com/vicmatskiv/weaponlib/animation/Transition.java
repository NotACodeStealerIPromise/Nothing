package com.vicmatskiv.weaponlib.animation;

import com.vicmatskiv.weaponlib.Part;
import java.util.function.Consumer;

public class Transition {
   private static final Consumer ANCHORED_POSITION = (c) -> {
   };
   private Consumer itemPositioning;
   private long duration;
   private long pause;
   private Part attachedTo;
   private boolean animated;

   public static Consumer anchoredPosition() {
      return ANCHORED_POSITION;
   }

   public Transition(Consumer itemPositioning, Part attachedTo, boolean animated) {
      this(itemPositioning, 0L, 0L);
      this.animated = animated;
   }

   public Transition(Consumer itemPositioning, long duration) {
      this(itemPositioning, duration, 0L);
   }

   public Transition(Consumer itemPositioning, long duration, long pause) {
      this(itemPositioning, duration, pause, (Part)null);
   }

   public Transition(Consumer itemPositioning, long duration, long pause, Part attachedTo) {
      this.itemPositioning = itemPositioning;
      this.duration = duration;
      this.pause = pause;
      this.attachedTo = attachedTo;
   }

   public Consumer getItemPositioning() {
      return this.itemPositioning;
   }

   public long getDuration() {
      return this.duration;
   }

   public long getPause() {
      return this.pause;
   }

   public Part getAttachedTo() {
      return this.attachedTo;
   }

   public boolean isAnimated() {
      return this.animated;
   }
}
