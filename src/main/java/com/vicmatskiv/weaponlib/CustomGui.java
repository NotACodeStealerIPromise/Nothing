package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AttachmentCategory;
import com.vicmatskiv.weaponlib.CustomArmor;
import com.vicmatskiv.weaponlib.ItemMagazine;
import com.vicmatskiv.weaponlib.KeyBindings;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.StatusMessageCenter;
import com.vicmatskiv.weaponlib.Tags;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponAttachmentAspect;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleGui;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTessellator;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.electronics.ItemWirelessCamera;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderGameOverlayEvent.Pre;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CustomGui extends CompatibleGui {
   private static final int STATUS_BAR_BOTTOM_OFFSET = 15;
   private static final int STATUS_BAR_TOP_OFFSET = 10;
   private Minecraft mc;
   private WeaponAttachmentAspect attachmentAspect;
   private ModContext modContext;
   private ConfigurationManager.StatusBarPosition statusBarPosition;

   public CustomGui(Minecraft mc, ModContext modContext, WeaponAttachmentAspect attachmentAspect) {
      this.mc = mc;
      this.modContext = modContext;
      this.attachmentAspect = attachmentAspect;
      this.statusBarPosition = modContext.getConfigurationManager().getStatusBarPosition();
   }

   public void onCompatibleRenderHud(Pre event) {
      if(CompatibilityProvider.compatibility.getEventType(event) == ElementType.HELMET) {
         ItemStack helmet = CompatibilityProvider.compatibility.getHelmet();
         if(helmet != null && this.mc.gameSettings.thirdPersonView == 0 && helmet.getItem() instanceof CustomArmor) {
            String hudTexture = ((CustomArmor)helmet.getItem()).getHudTexture();
            if(hudTexture != null) {
               ScaledResolution scaledResolution = CompatibilityProvider.compatibility.getResolution(event);
               int width = scaledResolution.getScaledWidth();
               int height = scaledResolution.getScaledHeight();
               GL11.glPushAttrib(8192);
               GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(2896);
               GL11.glBlendFunc(770, 771);
               GL11.glEnable(3042);
               this.mc.renderEngine.bindTexture(new ResourceLocation(hudTexture));
               drawTexturedQuadFit(0.0D, 0.0D, (double)width, (double)height, 0.0D);
               GL11.glPopAttrib();
               event.setCanceled(true);
            }
         }
      }

   }

   public void onCompatibleRenderCrosshair(Pre event) {
      if(CompatibilityProvider.compatibility.getEventType(event) == ElementType.CROSSHAIRS) {
         ItemStack itemStack = CompatibilityProvider.compatibility.getHeldItemMainHand(CompatibilityProvider.compatibility.clientPlayer());
         if(itemStack != null) {
            PlayerWeaponInstance weaponInstance = this.modContext.getMainHeldWeapon();
            int color;
            int stringWidth1;
            if(weaponInstance != null) {
               Weapon scaledResolution = (Weapon)itemStack.getItem();
               String width = scaledResolution != null?scaledResolution.getCrosshair(weaponInstance):null;
               if(width != null) {
                  ScaledResolution height = CompatibilityProvider.compatibility.getResolution(event);
                  int fontRender = height.getScaledWidth();
                  color = height.getScaledHeight();
                  FontRenderer message = CompatibilityProvider.compatibility.getFontRenderer();
                  this.mc.entityRenderer.setupOverlayRendering();
                  int messageText = 16777215;
                  this.mc.renderEngine.bindTexture(new ResourceLocation(width));
                  GL11.glPushAttrib(8192);
                  GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                  GL11.glDisable(2896);
                  GL11.glBlendFunc(770, 771);
                  GL11.glEnable(3042);
                  String y;
                  if(this.isInModifyingState(weaponInstance)) {
                     String x = CompatibilityProvider.compatibility.getLocalizedString("gui.attachmentMode.changeScope", new Object[]{Keyboard.getKeyName(KeyBindings.upArrowKey.getKeyCode())});
                     message.drawStringWithShadow(x, fontRender / 2 - 40, 60, messageText);
                     y = CompatibilityProvider.compatibility.getLocalizedString("gui.attachmentMode.changeBarrelRig", new Object[]{Keyboard.getKeyName(KeyBindings.leftArrowKey.getKeyCode())});
                     message.drawStringWithShadow(y, 10, color / 2 - 10, messageText);
                     String stringWidth = CompatibilityProvider.compatibility.getLocalizedString("gui.attachmentMode.changeCamo", new Object[]{Keyboard.getKeyName(KeyBindings.rightArrowKey.getKeyCode())});
                     message.drawStringWithShadow(stringWidth, fontRender / 2 + 60, color / 2 - 20, messageText);
                     String y1 = CompatibilityProvider.compatibility.getLocalizedString("gui.attachmentMode.changeUnderBarrelRig", new Object[]{Keyboard.getKeyName(KeyBindings.downArrowKey.getKeyCode())});
                     message.drawStringWithShadow(y1, 10, color - 40, messageText);
                  } else {
                     StatusMessageCenter.Message x1 = this.modContext.getStatusMessageCenter().nextMessage();
                     if(x1 != null) {
                        y = x1.getMessage();
                        if(x1.isAlert()) {
                           messageText = 16776960;
                        }
                     } else {
                        y = this.getDefaultWeaponMessage(weaponInstance);
                     }

                     stringWidth1 = this.getStatusBarXPosition(fontRender, y, message);
                     int y3 = this.getStatusBarYPosition(color);
                     message.drawStringWithShadow(y, stringWidth1, y3, messageText);
                  }

                  GL11.glPopAttrib();
                  event.setCanceled(true);
               }
            } else {
               ScaledResolution scaledResolution1;
               int width1;
               int height1;
               FontRenderer fontRender1;
               StatusMessageCenter.Message message1;
               String messageText1;
               int x2;
               int y2;
               if(itemStack.getItem() instanceof ItemMagazine) {
                  scaledResolution1 = CompatibilityProvider.compatibility.getResolution(event);
                  width1 = scaledResolution1.getScaledWidth();
                  height1 = scaledResolution1.getScaledHeight();
                  fontRender1 = CompatibilityProvider.compatibility.getFontRenderer();
                  this.mc.entityRenderer.setupOverlayRendering();
                  color = 16777215;
                  message1 = this.modContext.getStatusMessageCenter().nextMessage();
                  if(message1 != null) {
                     messageText1 = message1.getMessage();
                     if(message1.isAlert()) {
                        color = 16711680;
                     }
                  } else {
                     messageText1 = this.getDefaultMagazineMessage(itemStack);
                  }

                  x2 = this.getStatusBarXPosition(width1, messageText1, fontRender1);
                  y2 = this.getStatusBarYPosition(height1);
                  fontRender1.drawStringWithShadow(messageText1, x2, y2, color);
                  event.setCanceled(true);
               } else if(itemStack.getItem() instanceof ItemWirelessCamera) {
                  scaledResolution1 = CompatibilityProvider.compatibility.getResolution(event);
                  width1 = scaledResolution1.getScaledWidth();
                  height1 = scaledResolution1.getScaledHeight();
                  fontRender1 = CompatibilityProvider.compatibility.getFontRenderer();
                  this.mc.entityRenderer.setupOverlayRendering();
                  color = 16777215;
                  message1 = this.modContext.getStatusMessageCenter().nextMessage();
                  if(message1 != null) {
                     messageText1 = message1.getMessage();
                     if(message1.isAlert()) {
                        color = 16711680;
                     }

                     x2 = this.getStatusBarXPosition(width1, messageText1, fontRender1);
                     y2 = this.getStatusBarYPosition(height1);
                     stringWidth1 = fontRender1.getStringWidth(messageText1);
                     if(stringWidth1 > 80) {
                        x2 = width1 - stringWidth1 - 5;
                     }

                     fontRender1.drawStringWithShadow(messageText1, x2, y2, color);
                     event.setCanceled(true);
                  }
               } else if(itemStack.getItem() instanceof ItemGrenade) {
                  scaledResolution1 = CompatibilityProvider.compatibility.getResolution(event);
                  width1 = scaledResolution1.getScaledWidth();
                  height1 = scaledResolution1.getScaledHeight();
                  fontRender1 = CompatibilityProvider.compatibility.getFontRenderer();
                  this.mc.entityRenderer.setupOverlayRendering();
                  color = 16777215;
                  message1 = this.modContext.getStatusMessageCenter().nextMessage();
                  if(message1 != null) {
                     messageText1 = message1.getMessage();
                     if(message1.isAlert()) {
                        color = 16776960;
                     }

                     x2 = this.getStatusBarXPosition(width1, messageText1, fontRender1);
                     y2 = this.getStatusBarYPosition(height1);
                     stringWidth1 = fontRender1.getStringWidth(messageText1);
                     if(stringWidth1 > 80) {
                        x2 = width1 - stringWidth1 - 5;
                     }

                     fontRender1.drawStringWithShadow(messageText1, x2, y2, color);
                     event.setCanceled(true);
                  }
               }
            }

         }
      }
   }

   private int getStatusBarXPosition(int width, String text, FontRenderer fontRender) {
      int x;
      if(this.statusBarPosition != ConfigurationManager.StatusBarPosition.BOTTOM_RIGHT && this.statusBarPosition != ConfigurationManager.StatusBarPosition.TOP_RIGHT) {
         x = 10;
      } else {
         x = width - 80;
         int stringWidth = fontRender.getStringWidth(text);
         if(stringWidth > 80) {
            x = width - stringWidth - 5;
         }
      }

      return x;
   }

   private int getStatusBarYPosition(int height) {
      int yPos;
      switch(null.$SwitchMap$com$vicmatskiv$weaponlib$config$ConfigurationManager$StatusBarPosition[this.statusBarPosition.ordinal()]) {
      case 1:
      case 2:
         yPos = 10;
         break;
      case 3:
      case 4:
         yPos = height - 15;
         break;
      default:
         yPos = 10;
      }

      return yPos;
   }

   private String getDefaultMagazineMessage(ItemStack itemStack) {
      ItemMagazine magazine = (ItemMagazine)itemStack.getItem();
      String ammoCounterMessage = CompatibilityProvider.compatibility.getLocalizedString("gui.ammoCounter", new Object[]{Tags.getAmmo(itemStack) + "/" + magazine.getAmmo()});
      return ammoCounterMessage;
   }

   private String getDefaultWeaponMessage(PlayerWeaponInstance weaponInstance) {
      WeaponAttachmentAspect var10000 = this.attachmentAspect;
      ItemMagazine magazine = (ItemMagazine)WeaponAttachmentAspect.getActiveAttachment(AttachmentCategory.MAGAZINE, weaponInstance);
      int totalCapacity;
      if(magazine != null) {
         totalCapacity = magazine.getAmmo();
      } else {
         totalCapacity = weaponInstance.getWeapon().getAmmoCapacity();
      }

      String text;
      if(weaponInstance.getWeapon().getAmmoCapacity() == 0 && totalCapacity == 0) {
         text = CompatibilityProvider.compatibility.getLocalizedString("gui.noMagazine", new Object[0]);
      } else {
         text = CompatibilityProvider.compatibility.getLocalizedString("gui.ammoCounter", new Object[]{weaponInstance.getWeapon().getCurrentAmmo(CompatibilityProvider.compatibility.clientPlayer()) + "/" + totalCapacity});
      }

      return text;
   }

   private boolean isInModifyingState(PlayerWeaponInstance weaponInstance) {
      return weaponInstance.getState() == WeaponState.MODIFYING || weaponInstance.getState() == WeaponState.MODIFYING_REQUESTED || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT || weaponInstance.getState() == WeaponState.NEXT_ATTACHMENT_REQUESTED;
   }

   private static void drawTexturedQuadFit(double x, double y, double width, double height, double zLevel) {
      CompatibleTessellator tessellator = CompatibleTessellator.getInstance();
      tessellator.startDrawingQuads();
      tessellator.addVertexWithUV(x + 0.0D, y + height, zLevel, 0.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + height, zLevel, 1.0F, 1.0F);
      tessellator.addVertexWithUV(x + width, y + 0.0D, zLevel, 1.0F, 0.0F);
      tessellator.addVertexWithUV(x + 0.0D, y + 0.0D, zLevel, 0.0F, 0.0F);
      tessellator.draw();
   }
}
