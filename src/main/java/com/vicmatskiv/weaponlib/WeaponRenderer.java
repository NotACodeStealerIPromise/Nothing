package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.AsyncWeaponState;
import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.DefaultPart;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemSkin;
import com.vicmatskiv.weaponlib.ModelWithAttachments;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.RenderableState;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.Weapon;
import com.vicmatskiv.weaponlib.WeaponState;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.animation.MultipartRenderStateManager;
import com.vicmatskiv.weaponlib.animation.MultipartTransition;
import com.vicmatskiv.weaponlib.animation.MultipartTransitionProvider;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleWeaponRenderer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class WeaponRenderer extends CompatibleWeaponRenderer {
   private static final Logger logger = LogManager.getLogger(WeaponRenderer.class);
   private static final float DEFAULT_RANDOMIZING_RATE = 0.33F;
   private static final float DEFAULT_RANDOMIZING_FIRING_RATE = 20.0F;
   private static final float DEFAULT_RANDOMIZING_ZOOM_RATE = 0.25F;
   private static final float DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE = 0.06F;
   private static final float DEFAULT_ZOOM_RANDOMIZING_AMPLITUDE = 0.005F;
   private static final float DEFAULT_FIRING_RANDOMIZING_AMPLITUDE = 0.03F;
   private static final int DEFAULT_ANIMATION_DURATION = 250;
   private static final int DEFAULT_RECOIL_ANIMATION_DURATION = 100;
   private static final int DEFAULT_SHOOTING_ANIMATION_DURATION = 100;
   private WeaponRenderer.Builder builder;
   private Map firstPersonStateManagers;
   private MultipartTransitionProvider weaponTransitionProvider;
   protected ClientModContext clientModContext;

   private WeaponRenderer(WeaponRenderer.Builder builder) {
      super(builder);
      this.builder = builder;
      this.firstPersonStateManagers = new HashMap();
      this.weaponTransitionProvider = new WeaponRenderer.WeaponPositionProvider(null);
   }

   protected long getTotalReloadingDuration() {
      return this.builder.totalReloadingDuration;
   }

   protected long getTotalUnloadingDuration() {
      return this.builder.totalUnloadingDuration;
   }

   protected ClientModContext getClientModContext() {
      return this.clientModContext;
   }

   protected void setClientModContext(ClientModContext clientModContext) {
      this.clientModContext = clientModContext;
   }

   protected CompatibleWeaponRenderer.StateDescriptor getStateDescriptor(EntityPlayer player, ItemStack itemStack) {
      float amplitude = this.builder.normalRandomizingAmplitude;
      float rate = this.builder.normalRandomizingRate;
      RenderableState currentState = null;
      PlayerItemInstance playerItemInstance = this.clientModContext.getPlayerItemInstanceRegistry().getItemInstance(player, itemStack);
      PlayerWeaponInstance playerWeaponInstance = null;
      if(playerItemInstance != null && playerItemInstance instanceof PlayerWeaponInstance && playerItemInstance.getItem() == itemStack.getItem()) {
         playerWeaponInstance = (PlayerWeaponInstance)playerItemInstance;
      } else {
         logger.error("Invalid or mismatching item. Player item instance: {}. Item stack: {}", new Object[]{playerItemInstance, itemStack});
      }

      if(playerWeaponInstance != null) {
         AsyncWeaponState stateManager = this.getNextNonExpiredState(playerWeaponInstance);
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$WeaponState[stateManager.getState().ordinal()]) {
         case 1:
            if(playerWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
               if(playerWeaponInstance.isAimed()) {
                  currentState = RenderableState.ZOOMING;
                  rate = this.builder.firingRandomizingRate;
                  amplitude = this.builder.zoomRandomizingAmplitude;
               } else {
                  currentState = RenderableState.NORMAL;
                  rate = this.builder.firingRandomizingRate;
                  amplitude = this.builder.firingRandomizingAmplitude;
               }
            } else if(playerWeaponInstance.isAimed()) {
               currentState = RenderableState.ZOOMING_RECOILED;
               amplitude = this.builder.zoomRandomizingAmplitude;
            } else {
               currentState = RenderableState.RECOILED;
            }
            break;
         case 2:
            if(playerWeaponInstance.isAutomaticModeEnabled() && !this.hasRecoilPositioning()) {
               boolean isLongPaused = (float)(System.currentTimeMillis() - stateManager.getTimestamp()) > 50.0F / playerWeaponInstance.getFireRate() && stateManager.isInfinite();
               if(playerWeaponInstance.isAimed()) {
                  currentState = RenderableState.ZOOMING;
                  if(!isLongPaused) {
                     rate = this.builder.firingRandomizingRate;
                  }

                  amplitude = this.builder.zoomRandomizingAmplitude;
               } else {
                  currentState = RenderableState.NORMAL;
                  if(!isLongPaused) {
                     rate = this.builder.firingRandomizingRate;
                     amplitude = this.builder.firingRandomizingAmplitude;
                  }
               }
            } else if(playerWeaponInstance.isAimed()) {
               currentState = RenderableState.ZOOMING_SHOOTING;
               amplitude = this.builder.zoomRandomizingAmplitude;
            } else {
               currentState = RenderableState.SHOOTING;
            }
            break;
         case 3:
         case 4:
         case 5:
            currentState = RenderableState.UNLOADING;
            break;
         case 6:
            currentState = RenderableState.RELOADING;
            break;
         case 7:
            currentState = RenderableState.EJECT_SPENT_ROUND;
            break;
         case 8:
         case 9:
         case 10:
         case 11:
            currentState = RenderableState.MODIFYING;
            break;
         default:
            if(player.isSprinting() && this.builder.firstPersonPositioningRunning != null) {
               currentState = RenderableState.RUNNING;
            } else if(playerWeaponInstance.isAimed()) {
               currentState = RenderableState.ZOOMING;
               rate = this.builder.zoomRandomizingRate;
               amplitude = this.builder.zoomRandomizingAmplitude;
            }
         }

         logger.trace("Rendering state {} created from {}", new Object[]{currentState, stateManager.getState()});
      }

      if(currentState == null) {
         currentState = RenderableState.NORMAL;
      }

      MultipartRenderStateManager stateManager1 = (MultipartRenderStateManager)this.firstPersonStateManagers.get(player);
      if(stateManager1 == null) {
         stateManager1 = new MultipartRenderStateManager(currentState, this.weaponTransitionProvider, Part.MAIN_ITEM);
         this.firstPersonStateManagers.put(player, stateManager1);
      } else {
         stateManager1.setState(currentState, true, currentState == RenderableState.SHOOTING || currentState == RenderableState.ZOOMING_SHOOTING);
      }

      return new CompatibleWeaponRenderer.StateDescriptor(playerWeaponInstance, stateManager1, rate, amplitude);
   }

   private AsyncWeaponState getNextNonExpiredState(PlayerWeaponInstance playerWeaponState) {
      AsyncWeaponState asyncWeaponState = null;

      while((asyncWeaponState = playerWeaponState.nextHistoryState()) != null) {
         if(System.currentTimeMillis() < asyncWeaponState.getTimestamp() + asyncWeaponState.getDuration() && (asyncWeaponState.getState() != WeaponState.FIRING || !this.hasRecoilPositioning() && playerWeaponState.isAutomaticModeEnabled())) {
            break;
         }
      }

      return asyncWeaponState;
   }

   private Consumer createWeaponPartPositionFunction(Transition t) {
      if(t == null) {
         return (context) -> {
         };
      } else {
         Consumer weaponPositionFunction = t.getItemPositioning();
         return weaponPositionFunction != null?(context) -> {
            weaponPositionFunction.accept(context);
         }:(context) -> {
         };
      }
   }

   private Consumer createWeaponPartPositionFunction(Consumer weaponPositionFunction) {
      return weaponPositionFunction != null?(context) -> {
         weaponPositionFunction.accept(context);
      }:(context) -> {
      };
   }

   private List getComplexTransition(List wt, List lht, List rht, LinkedHashMap custom) {
      ArrayList result = new ArrayList();

      for(int i = 0; i < wt.size(); ++i) {
         Transition p = (Transition)wt.get(i);
         Transition l = (Transition)lht.get(i);
         Transition r = (Transition)rht.get(i);
         long pause = p.getPause();
         if(DebugPositioner.isDebugModeEnabled()) {
            DebugPositioner.TransitionConfiguration t = DebugPositioner.getTransitionConfiguration(i, false);
            if(t != null) {
               pause = t.getPause();
            }
         }

         MultipartTransition var17 = (new MultipartTransition(p.getDuration(), pause)).withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(p)).withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(l)).withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(r));

         Entry e;
         Transition partTransition;
         for(Iterator var13 = custom.entrySet().iterator(); var13.hasNext(); var17.withPartPositionFunction(e.getKey(), this.createWeaponPartPositionFunction(partTransition))) {
            e = (Entry)var13.next();
            List partTransitions = (List)e.getValue();
            partTransition = null;
            if(partTransitions != null && partTransitions.size() > i) {
               partTransition = (Transition)partTransitions.get(i);
            } else {
               logger.warn("Transition not defined for part {}", new Object[]{custom});
            }
         }

         result.add(var17);
      }

      return result;
   }

   private List getSimpleTransition(Consumer w, Consumer lh, Consumer rh, LinkedHashMap custom, int duration) {
      MultipartTransition mt = (new MultipartTransition((long)duration, 0L)).withPartPositionFunction(Part.MAIN_ITEM, this.createWeaponPartPositionFunction(w)).withPartPositionFunction(Part.LEFT_HAND, this.createWeaponPartPositionFunction(lh)).withPartPositionFunction(Part.RIGHT_HAND, this.createWeaponPartPositionFunction(rh));
      custom.forEach((part, position) -> {
         mt.withPartPositionFunction(part, this.createWeaponPartPositionFunction(position));
      });
      return Collections.singletonList(mt);
   }

   public void renderItem(ItemStack weaponItemStack, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      List attachments = null;
      if(this.builder.getModel() instanceof ModelWithAttachments) {
         attachments = ((Weapon)weaponItemStack.getItem()).getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
      }

      if(this.builder.getTextureName() != null) {
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + this.builder.getTextureName()));
      } else {
         String textureName = null;
         CompatibleAttachment compatibleSkin = (CompatibleAttachment)attachments.stream().filter((ca) -> {
            return ca.getAttachment() instanceof ItemSkin;
         }).findAny().orElse((Object)null);
         if(compatibleSkin != null) {
            PlayerItemInstance weapon = this.getClientModContext().getPlayerItemInstanceRegistry().getItemInstance(renderContext.getPlayer(), weaponItemStack);
            if(weapon instanceof PlayerWeaponInstance) {
               int textureIndex = ((PlayerWeaponInstance)weapon).getActiveTextureIndex();
               if(textureIndex >= 0) {
                  textureName = ((ItemSkin)compatibleSkin.getAttachment()).getTextureVariant(textureIndex) + ".png";
               }
            }
         }

         if(textureName == null) {
            Weapon weapon1 = (Weapon)weaponItemStack.getItem();
            textureName = weapon1.getTextureName();
         }

         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + textureName));
      }

      this.builder.getModel().render((Entity)null, renderContext.getLimbSwing(), renderContext.getFlimbSwingAmount(), renderContext.getAgeInTicks(), renderContext.getNetHeadYaw(), renderContext.getHeadPitch(), renderContext.getScale());
      if(attachments != null) {
         this.renderAttachments(positioner, renderContext, attachments);
      }

   }

   public void renderAttachments(MultipartPositioning.Positioner positioner, RenderContext renderContext, List attachments) {
      Iterator var4 = attachments.iterator();

      while(var4.hasNext()) {
         CompatibleAttachment compatibleAttachment = (CompatibleAttachment)var4.next();
         if(compatibleAttachment != null && !(compatibleAttachment.getAttachment() instanceof ItemSkin)) {
            this.renderCompatibleAttachment(compatibleAttachment, positioner, renderContext);
         }
      }

   }

   private void renderCompatibleAttachment(CompatibleAttachment compatibleAttachment, MultipartPositioning.Positioner positioner, RenderContext renderContext) {
      GL11.glPushMatrix();
      GL11.glPushAttrib(8193);
      if(compatibleAttachment.getPositioning() != null) {
         compatibleAttachment.getPositioning().accept(renderContext.getPlayer(), renderContext.getWeapon());
      }

      ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
      if(positioner != null) {
         if(itemAttachment instanceof Part) {
            positioner.position((Part)itemAttachment, renderContext);
         } else if(itemAttachment.getRenderablePart() != null) {
            positioner.position(itemAttachment.getRenderablePart(), renderContext);
         }
      }

      Iterator postRenderer = compatibleAttachment.getAttachment().getTexturedModels().iterator();

      while(postRenderer.hasNext()) {
         Tuple texturedModel = (Tuple)postRenderer.next();
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + (String)texturedModel.getV()));
         GL11.glPushMatrix();
         GL11.glPushAttrib(8193);
         if(compatibleAttachment.getModelPositioning() != null) {
            compatibleAttachment.getModelPositioning().accept(texturedModel.getU());
         }

         ((ModelBase)texturedModel.getU()).render(renderContext.getPlayer(), renderContext.getLimbSwing(), renderContext.getFlimbSwingAmount(), renderContext.getAgeInTicks(), renderContext.getNetHeadYaw(), renderContext.getHeadPitch(), renderContext.getScale());
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

      CustomRenderer postRenderer1 = compatibleAttachment.getAttachment().getPostRenderer();
      if(postRenderer1 != null) {
         GL11.glPushMatrix();
         GL11.glPushAttrib(8193);
         postRenderer1.render(renderContext);
         GL11.glPopAttrib();
         GL11.glPopMatrix();
      }

      Iterator texturedModel1 = itemAttachment.getAttachments().iterator();

      while(texturedModel1.hasNext()) {
         CompatibleAttachment childAttachment = (CompatibleAttachment)texturedModel1.next();
         this.renderCompatibleAttachment(childAttachment, positioner, renderContext);
      }

      GL11.glPopAttrib();
      GL11.glPopMatrix();
   }

   public boolean hasRecoilPositioning() {
      return this.builder.hasRecoilPositioningDefined;
   }

   // $FF: synthetic method
   WeaponRenderer(WeaponRenderer.Builder x0, Object x1) {
      this(x0);
   }

   private class WeaponPositionProvider implements MultipartTransitionProvider {
      private WeaponPositionProvider() {
      }

      public List getPositioning(RenderableState state) {
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$RenderableState[state.ordinal()]) {
         case 1:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningModifying, WeaponRenderer.this.builder.firstPersonLeftHandPositioningModifying, WeaponRenderer.this.builder.firstPersonRightHandPositioningModifying, WeaponRenderer.this.builder.firstPersonCustomPositioning, 250);
         case 2:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningRunning, WeaponRenderer.this.builder.firstPersonLeftHandPositioningRunning, WeaponRenderer.this.builder.firstPersonRightHandPositioningRunning, WeaponRenderer.this.builder.firstPersonCustomPositioning, 250);
         case 3:
            return WeaponRenderer.this.getComplexTransition(WeaponRenderer.this.builder.firstPersonPositioningUnloading, WeaponRenderer.this.builder.firstPersonLeftHandPositioningUnloading, WeaponRenderer.this.builder.firstPersonRightHandPositioningUnloading, WeaponRenderer.this.builder.firstPersonCustomPositioningUnloading);
         case 4:
            return WeaponRenderer.this.getComplexTransition(WeaponRenderer.this.builder.firstPersonPositioningReloading, WeaponRenderer.this.builder.firstPersonLeftHandPositioningReloading, WeaponRenderer.this.builder.firstPersonRightHandPositioningReloading, WeaponRenderer.this.builder.firstPersonCustomPositioningReloading);
         case 5:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningRecoiled, WeaponRenderer.this.builder.firstPersonLeftHandPositioningRecoiled, WeaponRenderer.this.builder.firstPersonRightHandPositioningRecoiled, WeaponRenderer.this.builder.firstPersonCustomPositioningRecoiled, WeaponRenderer.this.builder.recoilAnimationDuration);
         case 6:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningShooting, WeaponRenderer.this.builder.firstPersonLeftHandPositioningShooting, WeaponRenderer.this.builder.firstPersonRightHandPositioningShooting, WeaponRenderer.this.builder.firstPersonCustomPositioning, WeaponRenderer.this.builder.shootingAnimationDuration);
         case 7:
            return WeaponRenderer.this.getComplexTransition(WeaponRenderer.this.builder.firstPersonPositioningEjectSpentRound, WeaponRenderer.this.builder.firstPersonLeftHandPositioningEjectSpentRound, WeaponRenderer.this.builder.firstPersonRightHandPositioningEjectSpentRound, WeaponRenderer.this.builder.firstPersonCustomPositioningEjectSpentRound);
         case 8:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioning, WeaponRenderer.this.builder.firstPersonLeftHandPositioning, WeaponRenderer.this.builder.firstPersonRightHandPositioning, WeaponRenderer.this.builder.firstPersonCustomPositioning, 250);
         case 9:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningZooming, WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming, WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming, WeaponRenderer.this.builder.firstPersonCustomPositioning, 250);
         case 10:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningZoomingShooting, WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming, WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming, WeaponRenderer.this.builder.firstPersonCustomPositioningZoomingShooting, 60);
         case 11:
            return WeaponRenderer.this.getSimpleTransition(WeaponRenderer.this.builder.firstPersonPositioningZoomingRecoiled, WeaponRenderer.this.builder.firstPersonLeftHandPositioningZooming, WeaponRenderer.this.builder.firstPersonRightHandPositioningZooming, WeaponRenderer.this.builder.firstPersonCustomPositioningZoomingRecoiled, 60);
         default:
            return null;
         }
      }

      // $FF: synthetic method
      WeaponPositionProvider(Object x1) {
         this();
      }
   }

   public static class Builder {
      private ModelBase model;
      private String textureName;
      private float weaponProximity;
      private float yOffsetZoom;
      private float xOffsetZoom = 0.69F;
      private Consumer entityPositioning;
      private Consumer inventoryPositioning;
      private Consumer thirdPersonPositioning;
      private Consumer firstPersonPositioning;
      private Consumer firstPersonPositioningZooming;
      private Consumer firstPersonPositioningRunning;
      private Consumer firstPersonPositioningModifying;
      private Consumer firstPersonPositioningRecoiled;
      private Consumer firstPersonPositioningShooting;
      private Consumer firstPersonPositioningZoomingRecoiled;
      private Consumer firstPersonPositioningZoomingShooting;
      private Consumer firstPersonLeftHandPositioning;
      private Consumer firstPersonLeftHandPositioningZooming;
      private Consumer firstPersonLeftHandPositioningRunning;
      private Consumer firstPersonLeftHandPositioningModifying;
      private Consumer firstPersonLeftHandPositioningRecoiled;
      private Consumer firstPersonLeftHandPositioningShooting;
      private Consumer firstPersonRightHandPositioning;
      private Consumer firstPersonRightHandPositioningZooming;
      private Consumer firstPersonRightHandPositioningRunning;
      private Consumer firstPersonRightHandPositioningModifying;
      private Consumer firstPersonRightHandPositioningRecoiled;
      private Consumer firstPersonRightHandPositioningShooting;
      private List firstPersonPositioningReloading;
      private List firstPersonLeftHandPositioningReloading;
      private List firstPersonRightHandPositioningReloading;
      private List firstPersonPositioningUnloading;
      private List firstPersonLeftHandPositioningUnloading;
      private List firstPersonRightHandPositioningUnloading;
      private long totalReloadingDuration;
      private long totalUnloadingDuration;
      private String modId;
      private int recoilAnimationDuration = 100;
      private int shootingAnimationDuration = 100;
      private float normalRandomizingRate = 0.33F;
      private float firingRandomizingRate = 20.0F;
      private float zoomRandomizingRate = 0.25F;
      private float normalRandomizingAmplitude = 0.06F;
      private float zoomRandomizingAmplitude = 0.005F;
      private float firingRandomizingAmplitude = 0.03F;
      private LinkedHashMap firstPersonCustomPositioning = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningUnloading = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningReloading = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningRecoiled = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningZoomingRecoiled = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningZoomingShooting = new LinkedHashMap();
      private List firstPersonPositioningEjectSpentRound;
      private List firstPersonLeftHandPositioningEjectSpentRound;
      private List firstPersonRightHandPositioningEjectSpentRound;
      private LinkedHashMap firstPersonCustomPositioningEjectSpentRound = new LinkedHashMap();
      private boolean hasRecoilPositioningDefined;

      public WeaponRenderer.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public WeaponRenderer.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public WeaponRenderer.Builder withShootingAnimationDuration(int shootingAnimationDuration) {
         this.shootingAnimationDuration = shootingAnimationDuration;
         return this;
      }

      public WeaponRenderer.Builder withRecoilAnimationDuration(int recoilAnimationDuration) {
         this.recoilAnimationDuration = recoilAnimationDuration;
         return this;
      }

      public WeaponRenderer.Builder withNormalRandomizingRate(float normalRandomizingRate) {
         this.normalRandomizingRate = normalRandomizingRate;
         return this;
      }

      public WeaponRenderer.Builder withZoomRandomizingRate(float zoomRandomizingRate) {
         this.zoomRandomizingRate = zoomRandomizingRate;
         return this;
      }

      public WeaponRenderer.Builder withFiringRandomizingRate(float firingRandomizingRate) {
         this.firingRandomizingRate = firingRandomizingRate;
         return this;
      }

      public WeaponRenderer.Builder withFiringRandomizingAmplitude(float firingRandomizingAmplitude) {
         this.firingRandomizingAmplitude = firingRandomizingAmplitude;
         return this;
      }

      public WeaponRenderer.Builder withNormalRandomizingAmplitude(float firingRandomizingRate) {
         this.firingRandomizingRate = firingRandomizingRate;
         return this;
      }

      public WeaponRenderer.Builder withZoomRandomizingAmplitude(float zoomRandomizingAmplitude) {
         this.zoomRandomizingAmplitude = zoomRandomizingAmplitude;
         return this;
      }

      public WeaponRenderer.Builder withTextureName(String textureName) {
         this.textureName = textureName + ".png";
         return this;
      }

      public WeaponRenderer.Builder withWeaponProximity(float weaponProximity) {
         this.weaponProximity = weaponProximity;
         return this;
      }

      public WeaponRenderer.Builder withYOffsetZoom(float yOffsetZoom) {
         this.yOffsetZoom = yOffsetZoom;
         return this;
      }

      public WeaponRenderer.Builder withXOffsetZoom(float xOffsetZoom) {
         this.xOffsetZoom = xOffsetZoom;
         return this;
      }

      public WeaponRenderer.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public WeaponRenderer.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public WeaponRenderer.Builder withThirdPersonPositioning(Consumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioning(Consumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningRunning(Consumer firstPersonPositioningRunning) {
         this.firstPersonPositioningRunning = firstPersonPositioningRunning;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningZooming(Consumer firstPersonPositioningZooming) {
         this.firstPersonPositioningZooming = firstPersonPositioningZooming;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningRecoiled(Consumer firstPersonPositioningRecoiled) {
         this.hasRecoilPositioningDefined = true;
         this.firstPersonPositioningRecoiled = firstPersonPositioningRecoiled;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningShooting(Consumer firstPersonPositioningShooting) {
         this.firstPersonPositioningShooting = firstPersonPositioningShooting;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningZoomingRecoiled(Consumer firstPersonPositioningZoomingRecoiled) {
         this.firstPersonPositioningZoomingRecoiled = firstPersonPositioningZoomingRecoiled;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningZoomingShooting(Consumer firstPersonPositioningZoomingShooting) {
         this.firstPersonPositioningZoomingShooting = firstPersonPositioningZoomingShooting;
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonPositioningReloading(Transition... transitions) {
         this.firstPersonPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonPositioningUnloading(Transition... transitions) {
         this.firstPersonPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonPositioningModifying(Consumer firstPersonPositioningModifying) {
         this.firstPersonPositioningModifying = firstPersonPositioningModifying;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningRunning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningRunning = leftHand;
         this.firstPersonRightHandPositioningRunning = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningZooming(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningZooming = leftHand;
         this.firstPersonRightHandPositioningZooming = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningRecoiled(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningRecoiled = leftHand;
         this.firstPersonRightHandPositioningRecoiled = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningShooting(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningShooting = leftHand;
         this.firstPersonRightHandPositioningShooting = rightHand;
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningReloading(Transition... transitions) {
         this.firstPersonLeftHandPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonLeftHandPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonLeftHandPositioningUnloading(Transition... transitions) {
         this.firstPersonLeftHandPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonRightHandPositioningReloading(Transition... transitions) {
         this.firstPersonRightHandPositioningReloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonRightHandPositioningUnloading(Transition... transitions) {
         this.firstPersonRightHandPositioningUnloading = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonRightHandPositioningEjectSpentRound(Transition... transitions) {
         this.firstPersonRightHandPositioningEjectSpentRound = Arrays.asList(transitions);
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonHandPositioningModifying(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningModifying = leftHand;
         this.firstPersonRightHandPositioningModifying = rightHand;
         return this;
      }

      public WeaponRenderer.Builder withFirstPersonCustomPositioning(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioning.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonPositioningCustomRecoiled(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningRecoiled.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingShooting(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningZoomingShooting.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public WeaponRenderer.Builder withFirstPersonPositioningCustomZoomingRecoiled(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningZoomingRecoiled.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonCustomPositioningReloading(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningReloading.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonCustomPositioningUnloading(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningUnloading.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      @SafeVarargs
      public final WeaponRenderer.Builder withFirstPersonCustomPositioningEjectSpentRound(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningEjectSpentRound.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public WeaponRenderer build() {
         if(!CompatibilityProvider.compatibility.isClientSide()) {
            return null;
         } else if(this.modId == null) {
            throw new IllegalStateException("ModId is not set");
         } else {
            if(this.inventoryPositioning == null) {
               this.inventoryPositioning = (itemStack) -> {
                  GL11.glTranslatef(0.0F, 0.12F, 0.0F);
               };
            }

            if(this.entityPositioning == null) {
               this.entityPositioning = (itemStack) -> {
               };
            }

            WeaponRenderer renderer = new WeaponRenderer(this, null);
            if(this.firstPersonPositioning == null) {
               this.firstPersonPositioning = (renderContext) -> {
                  GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
                  if(renderer.getClientModContext() != null) {
                     PlayerWeaponInstance instance = renderer.getClientModContext().getMainHeldWeapon();
                     if(instance != null && instance.isAimed()) {
                        GL11.glTranslatef(this.xOffsetZoom, this.yOffsetZoom, this.weaponProximity);
                     } else {
                        GL11.glTranslatef(0.0F, -1.2F, 0.0F);
                     }
                  }

               };
            }

            if(this.firstPersonPositioningZooming == null) {
               this.firstPersonPositioningZooming = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningReloading == null) {
               this.firstPersonPositioningReloading = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            Iterator var2;
            Transition t;
            for(var2 = this.firstPersonPositioningReloading.iterator(); var2.hasNext(); this.totalReloadingDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalReloadingDuration += t.getDuration();
            }

            if(this.firstPersonPositioningUnloading == null) {
               this.firstPersonPositioningUnloading = Collections.singletonList(new Transition(this.firstPersonPositioning, 250L));
            }

            for(var2 = this.firstPersonPositioningUnloading.iterator(); var2.hasNext(); this.totalUnloadingDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalUnloadingDuration += t.getDuration();
            }

            if(this.firstPersonPositioningRecoiled == null) {
               this.firstPersonPositioningRecoiled = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningRunning == null) {
               this.firstPersonPositioningRunning = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningModifying == null) {
               this.firstPersonPositioningModifying = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningShooting == null) {
               this.firstPersonPositioningShooting = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningZoomingRecoiled == null) {
               this.firstPersonPositioningZoomingRecoiled = this.firstPersonPositioningZooming;
            }

            if(this.firstPersonPositioningZoomingShooting == null) {
               this.firstPersonPositioningZoomingShooting = this.firstPersonPositioningZooming;
            }

            if(this.thirdPersonPositioning == null) {
               this.thirdPersonPositioning = (context) -> {
                  GL11.glTranslatef(-0.4F, 0.2F, 0.4F);
                  GL11.glRotatef(-45.0F, 0.0F, 1.0F, 0.0F);
                  GL11.glRotatef(70.0F, 1.0F, 0.0F, 0.0F);
               };
            }

            if(this.firstPersonLeftHandPositioning == null) {
               this.firstPersonLeftHandPositioning = (context) -> {
               };
            }

            if(this.firstPersonLeftHandPositioningReloading == null) {
               this.firstPersonLeftHandPositioningReloading = (List)this.firstPersonPositioningReloading.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonLeftHandPositioningUnloading == null) {
               this.firstPersonLeftHandPositioningUnloading = (List)this.firstPersonPositioningUnloading.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonLeftHandPositioningRecoiled == null) {
               this.firstPersonLeftHandPositioningRecoiled = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonLeftHandPositioningShooting == null) {
               this.firstPersonLeftHandPositioningShooting = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonLeftHandPositioningZooming == null) {
               this.firstPersonLeftHandPositioningZooming = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonLeftHandPositioningRunning == null) {
               this.firstPersonLeftHandPositioningRunning = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonLeftHandPositioningModifying == null) {
               this.firstPersonLeftHandPositioningModifying = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonRightHandPositioning == null) {
               this.firstPersonRightHandPositioning = (context) -> {
               };
            }

            if(this.firstPersonRightHandPositioningReloading == null) {
               this.firstPersonRightHandPositioningReloading = (List)this.firstPersonPositioningReloading.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningUnloading == null) {
               this.firstPersonRightHandPositioningUnloading = (List)this.firstPersonPositioningUnloading.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningRecoiled == null) {
               this.firstPersonRightHandPositioningRecoiled = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningShooting == null) {
               this.firstPersonRightHandPositioningShooting = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningZooming == null) {
               this.firstPersonRightHandPositioningZooming = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningRunning == null) {
               this.firstPersonRightHandPositioningRunning = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningModifying == null) {
               this.firstPersonRightHandPositioningModifying = this.firstPersonRightHandPositioning;
            }

            if(!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningRecoiled.isEmpty()) {
               this.firstPersonCustomPositioning.forEach((part, pos) -> {
                  this.firstPersonCustomPositioningRecoiled.put(part, pos);
               });
            }

            if(!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningZoomingRecoiled.isEmpty()) {
               this.firstPersonCustomPositioning.forEach((part, pos) -> {
                  this.firstPersonCustomPositioningZoomingRecoiled.put(part, pos);
               });
            }

            if(!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningZoomingShooting.isEmpty()) {
               this.firstPersonCustomPositioning.forEach((part, pos) -> {
                  this.firstPersonCustomPositioningZoomingShooting.put(part, pos);
               });
            }

            this.firstPersonCustomPositioningReloading.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningReloading.size()) {
                  throw new IllegalStateException("Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningReloading.size() + ", actual: " + t.size());
               }
            });
            this.firstPersonCustomPositioningUnloading.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningUnloading.size()) {
                  throw new IllegalStateException("Custom unloading transition number mismatch. Expected " + this.firstPersonPositioningUnloading.size() + ", actual: " + t.size());
               }
            });
            return renderer;
         }
      }

      public Consumer getEntityPositioning() {
         return this.entityPositioning;
      }

      public Consumer getInventoryPositioning() {
         return this.inventoryPositioning;
      }

      public Consumer getThirdPersonPositioning() {
         return this.thirdPersonPositioning;
      }

      public String getTextureName() {
         return this.textureName;
      }

      public ModelBase getModel() {
         return this.model;
      }

      public String getModId() {
         return this.modId;
      }
   }
}
