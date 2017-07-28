package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.relauncher.Side;

public enum CompatibleSide {
   CLIENT,
   SERVER;

   private Side side;

   private CompatibleSide(Side side) {
      this.side = side;
   }

   public static CompatibleSide fromSide(Side side) {
      return side == Side.SERVER?SERVER:CLIENT;
   }

   public Side getSide() {
      return this.side;
   }

   static {
      CLIENT = new CompatibleSide("CLIENT", 0, Side.CLIENT);
      SERVER = new CompatibleSide("SERVER", 1, Side.SERVER);
   }
}
