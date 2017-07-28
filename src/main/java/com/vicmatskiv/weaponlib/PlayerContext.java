package com.vicmatskiv.weaponlib;

import net.minecraft.entity.player.EntityPlayer;

public interface PlayerContext {
   EntityPlayer getPlayer();

   void setPlayer(EntityPlayer var1);
}
