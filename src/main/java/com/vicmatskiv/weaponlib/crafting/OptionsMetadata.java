package com.vicmatskiv.weaponlib.crafting;

import com.vicmatskiv.weaponlib.compatibility.CompatibleBlocks;
import com.vicmatskiv.weaponlib.compatibility.CompatibleItems;
import com.vicmatskiv.weaponlib.crafting.CraftingComplexity;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.function.IntFunction;

public class OptionsMetadata {
   public static final Object EMPTY_OPTION = new Object();
   private OptionsMetadata.OptionMetadata[] metadata;
   private boolean hasOres;

   private OptionsMetadata(OptionsMetadata.OptionMetadata[] metadata, boolean hasOres) {
      this.metadata = metadata;
      this.hasOres = hasOres;
   }

   public OptionsMetadata.OptionMetadata[] getMetadata() {
      return this.metadata;
   }

   public boolean hasOres() {
      return this.hasOres;
   }

   // $FF: synthetic method
   OptionsMetadata(OptionsMetadata.OptionMetadata[] x0, boolean x1, Object x2) {
      this(x0, x1);
   }

   public static class OptionMetadataBuilder {
      LinkedHashMap optionMetadata = new LinkedHashMap();
      private int slotCount;

      public OptionsMetadata.OptionMetadataBuilder withSlotCount(int slotCount) {
         this.slotCount = slotCount;
         return this;
      }

      public OptionsMetadata.OptionMetadataBuilder withOption(int minOccurs, int maxOccurs) {
         OptionsMetadata.OptionMetadata metadata = new OptionsMetadata.OptionMetadata(OptionsMetadata.EMPTY_OPTION, minOccurs, maxOccurs);
         metadata.minOccurs = minOccurs;
         metadata.maxOccurs = maxOccurs;
         this.optionMetadata.put(OptionsMetadata.EMPTY_OPTION, metadata);
         return this;
      }

      public OptionsMetadata.OptionMetadataBuilder withOption(Object option, int minOccurs, int maxOccurs) {
         if(minOccurs > maxOccurs) {
            throw new IllegalArgumentException("Min occurs must be less or equals maxOccurs");
         } else {
            if(option instanceof CompatibleBlocks) {
               option = ((CompatibleBlocks)option).getBlock();
            } else if(option instanceof CompatibleItems) {
               option = ((CompatibleItems)option).getItem();
            } else if(option instanceof String) {
               String metadata = ((String)option).toLowerCase();
               if((metadata.contains("ore") || metadata.contains("ingot") || metadata.contains("dust")) && !metadata.startsWith(":")) {
                  ;
               }
            }

            OptionsMetadata.OptionMetadata metadata1 = new OptionsMetadata.OptionMetadata(option, minOccurs, maxOccurs);
            metadata1.minOccurs = minOccurs;
            metadata1.maxOccurs = maxOccurs;
            this.optionMetadata.put(option, metadata1);
            return this;
         }
      }

      public OptionsMetadata build(CraftingComplexity complexity, Object... options) {
         int complexityIndex = complexity.ordinal() + 1;
         if(options.length * complexityIndex > this.slotCount) {
            throw new IllegalArgumentException("Too many options for complexity level " + complexity);
         } else {
            Object[] var4 = options;
            int var5 = options.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               Object option = var4[var6];
               if(option == null) {
                  throw new IllegalArgumentException("Option cannot be null, make sure to initialize it before generating receipe");
               }

               this.withOption(option, complexityIndex, complexityIndex);
            }

            this.withOption(OptionsMetadata.EMPTY_OPTION, 0, this.slotCount - options.length * complexityIndex);
            return this.build();
         }
      }

      public OptionsMetadata build() {
         if(this.slotCount == 0) {
            throw new IllegalStateException("Slot count not set");
         } else {
            int totalMaxOccurs = 0;
            int totalMinOccurs = 0;
            boolean hasOres = false;
            Iterator metadata = this.optionMetadata.values().iterator();

            while(metadata.hasNext()) {
               OptionsMetadata.OptionMetadata m = (OptionsMetadata.OptionMetadata)metadata.next();
               totalMaxOccurs += m.maxOccurs;
               totalMinOccurs += m.minOccurs;
               if(m.getOption() instanceof String) {
                  hasOres = true;
               }
            }

            if(totalMaxOccurs < this.slotCount) {
               throw new IllegalStateException("Total slot count is less than total max occurs");
            } else if(totalMinOccurs > this.slotCount) {
               throw new IllegalStateException("Total max occurs exceeds the number of slots");
            } else {
               OptionsMetadata.OptionMetadata[] metadata1 = (OptionsMetadata.OptionMetadata[])((OptionsMetadata.OptionMetadata[])this.optionMetadata.entrySet().stream().map((e) -> {
                  return new OptionsMetadata.OptionMetadata(e.getKey(), ((OptionsMetadata.OptionMetadata)e.getValue()).minOccurs, ((OptionsMetadata.OptionMetadata)e.getValue()).maxOccurs);
               }).toArray((size) -> {
                  return new OptionsMetadata.OptionMetadata[size];
               }));
               return new OptionsMetadata(metadata1, hasOres);
            }
         }
      }
   }

   static class OptionMetadata {
      private int minOccurs;
      private int maxOccurs;
      private Object option;

      private OptionMetadata(Object option, int minOccurs, int maxOccurs) {
         this.option = option;
         this.minOccurs = minOccurs;
         this.maxOccurs = maxOccurs;
      }

      protected int getMinOccurs() {
         return this.minOccurs;
      }

      protected int getMaxOccurs() {
         return this.maxOccurs;
      }

      protected Object getOption() {
         return this.option;
      }

      // $FF: synthetic method
      OptionMetadata(Object x0, int x1, int x2, Object x3) {
         this(x0, x1, x2);
      }
   }
}
