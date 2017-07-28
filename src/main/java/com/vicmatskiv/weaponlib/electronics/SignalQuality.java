package com.vicmatskiv.weaponlib.electronics;

import java.util.Random;

public class SignalQuality {
   private int quality;
   private boolean interrupted;
   private static Random random = new Random();

   public SignalQuality(int quality, boolean interrupted) {
      this.quality = quality;
      this.interrupted = interrupted;
   }

   public int getQuality() {
      return this.quality;
   }

   public boolean isInterrupted() {
      return this.interrupted;
   }

   public static SignalQuality getQuality(int currentDistance, int maxDistance) {
      double allowedRange = (double)currentDistance / (double)maxDistance;
      double adjustedAllowedRange = 1.0D - allowedRange * allowedRange * allowedRange;
      if(adjustedAllowedRange > 0.97D) {
         adjustedAllowedRange = 1.0D;
      }

      double r = random.nextDouble();
      int signalQuality = (int)(adjustedAllowedRange * 100.0D);
      return new SignalQuality(signalQuality, r >= adjustedAllowedRange);
   }
}
