package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CompatibleItems {
   public static final CompatibleItems GUNPOWDER;
   public static final CompatibleItems DYE;
   public static final CompatibleItems IRON_INGOT;
   public static final CompatibleItems EMERALD;
   public static final CompatibleItems DIAMOND;
   public static final CompatibleItems GOLD_NUGGET;
   public static final CompatibleItems STRING;
   public static final CompatibleItems COAL;
   public static final CompatibleItems WATER_BUCKET;
   public static final CompatibleItems STICK;
   public static final CompatibleItems FLINT_AND_STEEL;
   private Item item;

   private CompatibleItems(Item item) {
      this.item = item;
   }

   public Item getItem() {
      return this.item;
   }

   // $FF: synthetic method
   CompatibleItems(Item x0, Object x1) {
      this(x0);
   }

   static {
      GUNPOWDER = new CompatibleItems(Items.gunpowder, null) {
      };
      DYE = new CompatibleItems(Items.dye, null) {
      };
      IRON_INGOT = new CompatibleItems(Items.iron_ingot, null) {
      };
      EMERALD = new CompatibleItems(Items.emerald, null) {
      };
      DIAMOND = new CompatibleItems(Items.diamond, null) {
      };
      GOLD_NUGGET = new CompatibleItems(Items.gold_nugget, null) {
      };
      STRING = new CompatibleItems(Items.string, null) {
      };
      COAL = new CompatibleItems(Items.coal, null) {
      };
      WATER_BUCKET = new CompatibleItems(Items.water_bucket, null) {
      };
      STICK = new CompatibleItems(Items.stick, null) {
      };
      FLINT_AND_STEEL = new CompatibleItems(Items.flint_and_steel, null) {
      };
   }
}
