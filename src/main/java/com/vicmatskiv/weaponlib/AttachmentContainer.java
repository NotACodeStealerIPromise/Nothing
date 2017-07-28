package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import java.util.Collection;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public interface AttachmentContainer {
   List getActiveAttachments(EntityPlayer var1, ItemStack var2);

   Collection getCompatibleAttachments(AttachmentCategory... var1);
}
