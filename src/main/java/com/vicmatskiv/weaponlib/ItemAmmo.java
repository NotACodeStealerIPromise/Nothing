package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.Weapon;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemAmmo extends Item {
   private List compatibleWeapons = new ArrayList();

   public void addCompatibleWeapon(Weapon weapon) {
      this.compatibleWeapons.add(weapon);
   }

   public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List info, boolean p_77624_4_) {
      info.add("Compatible guns:");
      this.compatibleWeapons.forEach((weapon) -> {
         info.add(weapon.getName());
      });
   }
}
