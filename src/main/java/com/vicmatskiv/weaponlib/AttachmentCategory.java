package com.vicmatskiv.weaponlib;

public enum AttachmentCategory {
   SCOPE,
   GRIP,
   SILENCER,
   MAGAZINE,
   BULLET,
   SKIN,
   EXTRA,
   EXTRA2,
   EXTRA3,
   EXTRA4,
   EXTRA5,
   EXTRA6,
   EXTRA7,
   EXTRA8;

   public static final AttachmentCategory[] values;

   public static AttachmentCategory valueOf(int ordinal) {
      return values[ordinal];
   }

   static {
      values = values();
   }
}
