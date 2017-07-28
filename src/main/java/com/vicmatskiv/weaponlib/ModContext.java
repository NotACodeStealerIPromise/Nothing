package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentContainer;
import com.vicmatskiv.weaponlib.EffectManager;
import com.vicmatskiv.weaponlib.MagazineReloadAspect;
import com.vicmatskiv.weaponlib.PlayerItemInstanceRegistry;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.StatusMessageCenter;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponFireAspect;
import com.vicmatskiv.weaponlib.WeaponReloadAspect;
import com.vicmatskiv.weaponlib.WeaponRenderer;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleSound;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.RecipeManager;
import com.vicmatskiv.weaponlib.grenade.GrenadeAttackAspect;
import com.vicmatskiv.weaponlib.grenade.GrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeAttachmentAspect;
import com.vicmatskiv.weaponlib.melee.MeleeAttackAspect;
import com.vicmatskiv.weaponlib.melee.MeleeRenderer;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public interface ModContext {
   void init(Object var1, String var2, ConfigurationManager var3, CompatibleChannel var4);

   void registerWeapon(String var1, Weapon var2, WeaponRenderer var3);

   CompatibleChannel getChannel();

   void runSyncTick(Runnable var1);

   void registerRenderableItem(String var1, Item var2, Object var3);

   CompatibleSound registerSound(String var1);

   void runInMainThread(Runnable var1);

   PlayerItemInstanceRegistry getPlayerItemInstanceRegistry();

   WeaponReloadAspect getWeaponReloadAspect();

   WeaponFireAspect getWeaponFireAspect();

   WeaponAttachmentAspect getAttachmentAspect();

   MagazineReloadAspect getMagazineReloadAspect();

   PlayerWeaponInstance getMainHeldWeapon();

   StatusMessageCenter getStatusMessageCenter();

   RecipeManager getRecipeManager();

   CompatibleSound getZoomSound();

   void setChangeZoomSound(String var1);

   CompatibleSound getChangeFireModeSound();

   void setChangeFireModeSound(String var1);

   CompatibleSound getNoAmmoSound();

   void setNoAmmoSound(String var1);

   CompatibleSound getExplosionSound();

   void setExplosionSound(String var1);

   void registerMeleeWeapon(String var1, ItemMelee var2, MeleeRenderer var3);

   void registerGrenadeWeapon(String var1, ItemGrenade var2, GrenadeRenderer var3);

   MeleeAttackAspect getMeleeAttackAspect();

   MeleeAttachmentAspect getMeleeAttachmentAspect();

   AttachmentContainer getGrenadeAttachmentAspect();

   ResourceLocation getNamedResource(String var1);

   float getAspectRatio();

   GrenadeAttackAspect getGrenadeAttackAspect();

   String getModId();

   EffectManager getEffectManager();

   ConfigurationManager getConfigurationManager();
}
