package com.vicmatskiv.weaponlib.crafting;

import com.vicmatskiv.weaponlib.crafting.OptionsMetadata;
import com.vicmatskiv.weaponlib.crafting.SequenceGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

final class RecipeGenerator {
   private static final int SLOT_COUNT = 9;
   private SequenceGenerator sequenceGenerator = new SequenceGenerator(9);

   public List createShapedRecipe(String name, OptionsMetadata metadata) {
      List sequence = this.sequenceGenerator.generate(name, this.createSeed(name, metadata), metadata);
      LinkedHashMap encodingMap = new LinkedHashMap();
      char startFrom = 65;
      OptionsMetadata.OptionMetadata[] output = metadata.getMetadata();
      int builder = output.length;

      int i;
      for(i = 0; i < builder; ++i) {
         OptionsMetadata.OptionMetadata optionMetadata = output[i];
         char t;
         if(optionMetadata.getOption() == OptionsMetadata.EMPTY_OPTION) {
            t = 32;
         } else {
            t = startFrom++;
         }

         encodingMap.put(optionMetadata.getOption(), Character.valueOf(t));
      }

      ArrayList var11 = new ArrayList();
      StringBuilder var12 = new StringBuilder();
      i = 0;
      Iterator var13 = sequence.iterator();

      while(var13.hasNext()) {
         Object var14 = var13.next();
         var12.append(encodingMap.get(var14));
         ++i;
         if(i % 3 == 0) {
            var11.add(var12.toString());
            var12.setLength(0);
         }
      }

      encodingMap.entrySet().stream().filter((e) -> {
         return e.getKey() != OptionsMetadata.EMPTY_OPTION;
      }).forEach((e) -> {
         var11.add(e.getValue());
         var11.add(e.getKey());
      });
      return var11;
   }

   private byte[] createSeed(String name, OptionsMetadata metadata) {
      return (name + metadata.getMetadata().length).getBytes();
   }
}
