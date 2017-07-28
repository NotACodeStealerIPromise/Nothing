package com.vicmatskiv.weaponlib.compatibility;

import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public enum CompatibleTransformType {
   NONE,
   THIRD_PERSON_LEFT_HAND,
   THIRD_PERSON_RIGHT_HAND,
   FIRST_PERSON_LEFT_HAND,
   FIRST_PERSON_RIGHT_HAND,
   HEAD,
   GUI,
   GROUND,
   FIXED;

   public static CompatibleTransformType fromItemRenderType(ItemRenderType itemRenderType) {
      CompatibleTransformType result = null;
      switch(null.$SwitchMap$net$minecraftforge$client$IItemRenderer$ItemRenderType[itemRenderType.ordinal()]) {
      case 1:
         result = GROUND;
         break;
      case 2:
         result = THIRD_PERSON_RIGHT_HAND;
         break;
      case 3:
         result = FIRST_PERSON_RIGHT_HAND;
         break;
      case 4:
         result = GUI;
      default:
         result = NONE;
      }

      return result;
   }
}
