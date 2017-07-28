package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.PlayerItemInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface PlayerItemInstanceFactory {
   PlayerItemInstance createItemInstance(EntityPlayer var1, ItemStack var2, int var3);
}
