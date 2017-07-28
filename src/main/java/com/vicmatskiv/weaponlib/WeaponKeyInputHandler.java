package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.KeyBindings;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Modifiable;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.Reloadable;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleChannel;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWeaponKeyInputHandler;
import com.vicmatskiv.weaponlib.electronics.PlayerTabletInstance;
import com.vicmatskiv.weaponlib.melee.MeleeState;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import java.util.function.Function;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class WeaponKeyInputHandler extends CompatibleWeaponKeyInputHandler {
   private CompatibleChannel channel;
   private Function entityPlayerSupplier;
   private ModContext modContext;

   public WeaponKeyInputHandler(ModContext modContext, Function entityPlayerSupplier, WeaponAttachmentAspect attachmentAspect, CompatibleChannel channel) {
      this.modContext = modContext;
      this.entityPlayerSupplier = entityPlayerSupplier;
      this.channel = channel;
   }

   public void onCompatibleKeyInput() {
      EntityPlayer player = (EntityPlayer)this.entityPlayerSupplier.apply((Object)null);
      ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(player);
      if(DebugPositioner.isDebugModeEnabled() && KeyBindings.upArrowKey.isPressed()) {
         DebugPositioner.incrementXRotation(5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.downArrowKey.isPressed()) {
         DebugPositioner.incrementXRotation(-5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.leftArrowKey.isPressed()) {
         DebugPositioner.incrementYRotation(5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.rightArrowKey.isPressed()) {
         DebugPositioner.incrementYRotation(-5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.jDebugKey.isPressed()) {
         DebugPositioner.incrementZRotation(5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.kDebugKey.isPressed()) {
         DebugPositioner.incrementZRotation(-5.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.minusDebugKey.isPressed()) {
         DebugPositioner.incrementXPosition(-1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.equalsDebugKey.isPressed()) {
         DebugPositioner.incrementXPosition(1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.lBracketDebugKey.isPressed()) {
         DebugPositioner.incrementYPosition(-1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.rBracketDebugKey.isPressed()) {
         DebugPositioner.incrementYPosition(1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.semicolonDebugKey.isPressed()) {
         DebugPositioner.incrementZPosition(-1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.apostropheDebugKey.isPressed()) {
         DebugPositioner.incrementZPosition(1.0F);
      } else if(DebugPositioner.isDebugModeEnabled() && KeyBindings.deleteDebugKey.isPressed()) {
         DebugPositioner.reset();
      } else if(KeyBindings.reloadKey.isPressed()) {
         if(itemStack != null) {
            Item instance = itemStack.getItem();
            if(instance instanceof Reloadable) {
               ((Reloadable)instance).reloadMainHeldItemForPlayer(player);
            }
         }
      } else {
         PlayerWeaponInstance instance1;
         if(KeyBindings.laserSwitchKey.isPressed()) {
            instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
            if(instance1 != null && (instance1.getState() == WeaponState.READY || instance1.getState() == WeaponState.MODIFYING)) {
               instance1.setLaserOn(!instance1.isLaserOn());
            }
         } else if(KeyBindings.attachmentKey.isPressed()) {
            if(itemStack != null && itemStack.getItem() instanceof Modifiable) {
               ((Modifiable)itemStack.getItem()).toggleClientAttachmentSelectionMode(player);
            }
         } else if(KeyBindings.upArrowKey.isPressed()) {
            instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
            if(instance1 != null && instance1.getState() == WeaponState.MODIFYING) {
               this.modContext.getAttachmentAspect().changeAttachment(AttachmentCategory.SCOPE, instance1);
            }
         } else {
            PlayerTabletInstance playerTabletInstance;
            PlayerItemInstance instance2;
            if(KeyBindings.rightArrowKey.isPressed()) {
               instance2 = this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player);
               if(instance2 instanceof PlayerWeaponInstance && instance2.getState() == WeaponState.MODIFYING) {
                  this.modContext.getAttachmentAspect().changeAttachment(AttachmentCategory.SKIN, (PlayerWeaponInstance)instance2);
               } else if(instance2 instanceof PlayerMeleeInstance && instance2.getState() == MeleeState.MODIFYING) {
                  this.modContext.getMeleeAttachmentAspect().changeAttachment(AttachmentCategory.SKIN, (PlayerMeleeInstance)instance2);
               } else if(instance2 instanceof PlayerTabletInstance) {
                  playerTabletInstance = (PlayerTabletInstance)instance2;
                  playerTabletInstance.nextActiveWatchIndex();
               }
            } else if(KeyBindings.downArrowKey.isPressed()) {
               instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
               if(instance1 != null && instance1.getState() == WeaponState.MODIFYING) {
                  this.modContext.getAttachmentAspect().changeAttachment(AttachmentCategory.GRIP, instance1);
               }
            } else if(KeyBindings.leftArrowKey.isPressed()) {
               instance2 = this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player);
               if(instance2 instanceof PlayerWeaponInstance && instance2.getState() == WeaponState.MODIFYING) {
                  this.modContext.getAttachmentAspect().changeAttachment(AttachmentCategory.SILENCER, (PlayerWeaponInstance)instance2);
               } else if(instance2 instanceof PlayerTabletInstance) {
                  playerTabletInstance = (PlayerTabletInstance)instance2;
                  playerTabletInstance.previousActiveWatchIndex();
               }
            } else if(KeyBindings.fireModeKey.isPressed()) {
               instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
               if(instance1 != null && instance1.getState() == WeaponState.READY) {
                  instance1.getWeapon().changeFireMode(instance1);
               }
            } else if(KeyBindings.addKey.isPressed()) {
               instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
               if(instance1 != null && (instance1.getState() == WeaponState.READY || instance1.getState() == WeaponState.EJECT_REQUIRED)) {
                  instance1.getWeapon().incrementZoom(instance1);
               }
            } else if(KeyBindings.subtractKey.isPressed()) {
               instance1 = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getMainHandItemInstance(player, PlayerWeaponInstance.class);
               if(instance1 != null && (instance1.getState() == WeaponState.READY || instance1.getState() == WeaponState.EJECT_REQUIRED)) {
                  instance1.getWeapon().decrementZoom(instance1);
               }
            }
         }
      }

   }
}
