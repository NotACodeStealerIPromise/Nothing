package com.vicmatskiv.weaponlib.blocks;

import net.minecraft.stats.StatBase;

public class BlockSmith {
   public static StatBase deconstructAny;
   public static StatBase deconstructedItemsStat;
   public static boolean allowPartialDeconstructing;
   public static boolean allowDeconstructUnrealistic;
   public static boolean allowDeconstructEnchantedBooks;
   public static boolean allowHorseArmorCrafting;

   public static enum GUI_ENUM {
      GRINDER,
      COMPACTOR,
      DECONSTRUCTOR,
      TANNING_RACK,
      FORGE;
   }
}
