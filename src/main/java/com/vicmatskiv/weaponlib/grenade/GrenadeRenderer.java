package com.vicmatskiv.weaponlib.grenade;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.DefaultPart;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemSkin;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.Tuple;
import com.vicmatskiv.weaponlib.animation.DebugPositioner;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.animation.MultipartRenderStateManager;
import com.vicmatskiv.weaponlib.animation.MultipartTransition;
import com.vicmatskiv.weaponlib.animation.MultipartTransitionProvider;
import com.vicmatskiv.weaponlib.animation.Transition;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleGrenadeRenderer;
import com.vicmatskiv.weaponlib.grenade.AsyncGrenadeState;
import com.vicmatskiv.weaponlib.grenade.ItemGrenade;
import com.vicmatskiv.weaponlib.grenade.PlayerGrenadeInstance;
import com.vicmatskiv.weaponlib.grenade.RenderableState;
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
import java.util.function.Supplier;
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

public class GrenadeRenderer extends CompatibleGrenadeRenderer {
   private static final Logger logger = LogManager.getLogger(GrenadeRenderer.class);
   private static final float DEFAULT_RANDOMIZING_RATE = 0.33F;
   private static final float DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE = 0.06F;
   private static final int DEFAULT_ANIMATION_DURATION = 70;
   private GrenadeRenderer.Builder builder;
   private Map firstPersonStateManagers;
   private MultipartTransitionProvider weaponTransitionProvider;
   protected ClientModContext clientModContext;

   private GrenadeRenderer(GrenadeRenderer.Builder builder) {
      super(builder);
      this.builder = builder;
      this.firstPersonStateManagers = new HashMap();
      this.weaponTransitionProvider = new GrenadeRenderer.WeaponPositionProvider(null);
   }

   protected long getTotalTakingSafetyPinOffDuration() {
      return this.builder.totalTakingPinOffDuration;
   }

   protected long getTotalThrowingDuration() {
      return this.builder.totalThrowingDuration;
   }

   protected ClientModContext getClientModContext() {
      return this.clientModContext;
   }

   public void setClientModContext(ClientModContext clientModContext) {
      this.clientModContext = clientModContext;
   }

   protected CompatibleGrenadeRenderer.StateDescriptor getStateDescriptor(EntityPlayer player, ItemStack itemStack) {
      float amplitude = this.builder.normalRandomizingAmplitude;
      float rate = this.builder.normalRandomizingRate;
      RenderableState currentState = null;
      PlayerItemInstance playerItemInstance = this.clientModContext.getPlayerItemInstanceRegistry().getItemInstance(player, itemStack);
      PlayerGrenadeInstance playerGrenadeInstance = null;
      if(playerItemInstance != null && playerItemInstance instanceof PlayerGrenadeInstance && playerItemInstance.getItem() == itemStack.getItem()) {
         playerGrenadeInstance = (PlayerGrenadeInstance)playerItemInstance;
      } else {
         logger.error("Invalid or mismatching item. Player item instance: {}. Item stack: {}", new Object[]{playerItemInstance, itemStack});
      }

      if(playerGrenadeInstance != null) {
         AsyncGrenadeState key = this.getNextNonExpiredState(playerGrenadeInstance);
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$grenade$GrenadeState[key.getState().ordinal()]) {
         case 1:
            currentState = RenderableState.SAFETY_PIN_OFF;
            break;
         case 2:
            currentState = RenderableState.STRIKER_LEVEL_OFF;
            break;
         case 3:
            currentState = RenderableState.THROWING;
            break;
         case 4:
            currentState = RenderableState.THROWN;
            break;
         default:
            if(player.isSprinting() && this.builder.firstPersonPositioningRunning != null) {
               currentState = RenderableState.RUNNING;
            }
         }

         logger.trace("Rendering state {} created from {}", new Object[]{currentState, key.getState()});
      }

      if(currentState == null) {
         currentState = RenderableState.NORMAL;
      }

      GrenadeRenderer.StateManagerKey key1 = new GrenadeRenderer.StateManagerKey(player, playerGrenadeInstance != null?playerGrenadeInstance.getItemInventoryIndex():-1);
      MultipartRenderStateManager stateManager = (MultipartRenderStateManager)this.firstPersonStateManagers.get(key1);
      if(stateManager == null) {
         stateManager = new MultipartRenderStateManager(currentState, this.weaponTransitionProvider, Part.MAIN_ITEM);
         this.firstPersonStateManagers.put(key1, stateManager);
      } else {
         stateManager.setState(currentState, true, currentState == RenderableState.THROWING);
      }

      return new CompatibleGrenadeRenderer.StateDescriptor(playerGrenadeInstance, stateManager, rate, amplitude);
   }

   private AsyncGrenadeState getNextNonExpiredState(PlayerGrenadeInstance playerWeaponState) {
      AsyncGrenadeState asyncWeaponState = null;

      while((asyncWeaponState = playerWeaponState.nextHistoryState()) != null && System.currentTimeMillis() > asyncWeaponState.getTimestamp() + asyncWeaponState.getDuration()) {
         ;
      }

      return asyncWeaponState;
   }

   private Consumer createWeaponPartPositionFunction(Transition t) {
      if(t == null) {
         return (context) -> {
         };
      } else {
         Consumer weaponPositionFunction = t.getItemPositioning();
         return weaponPositionFunction == Transition.anchoredPosition()?MultipartTransition.anchoredPosition():(weaponPositionFunction != null?(context) -> {
            weaponPositionFunction.accept(context);
         }:(context) -> {
         });
      }
   }

   private Consumer createWeaponPartPositionFunction(Consumer weaponPositionFunction) {
      return weaponPositionFunction == Transition.anchoredPosition()?MultipartTransition.anchoredPosition():(weaponPositionFunction != null?(context) -> {
         weaponPositionFunction.accept(context);
      }:(context) -> {
      });
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

         MultipartTransition var17 = (new MultipartTransition(p.getDuration(), pause)).withPartPositionFunction(Part.MAIN_ITEM, p.getAttachedTo(), this.createWeaponPartPositionFunction(p)).withPartPositionFunction(Part.LEFT_HAND, l.getAttachedTo(), this.createWeaponPartPositionFunction(l)).withPartPositionFunction(Part.RIGHT_HAND, r.getAttachedTo(), this.createWeaponPartPositionFunction(r));

         Entry e;
         Transition partTransition;
         for(Iterator var13 = custom.entrySet().iterator(); var13.hasNext(); var17.withPartPositionFunction(e.getKey(), partTransition.getAttachedTo(), this.createWeaponPartPositionFunction(partTransition))) {
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
      long pause = 0L;
      if(DebugPositioner.isDebugModeEnabled()) {
         DebugPositioner.TransitionConfiguration mt = DebugPositioner.getTransitionConfiguration(0, false);
         if(mt != null) {
            pause = mt.getPause();
         }
      }

      MultipartTransition mt1 = (new MultipartTransition((long)duration, pause)).withPartPositionFunction(Part.MAIN_ITEM, (Object)null, this.createWeaponPartPositionFunction(w)).withPartPositionFunction(Part.LEFT_HAND, (Object)null, this.createWeaponPartPositionFunction(lh)).withPartPositionFunction(Part.RIGHT_HAND, (Object)null, this.createWeaponPartPositionFunction(rh));
      custom.forEach((part, position) -> {
         mt1.withPartPositionFunction(part, position.attachedTo, this.createWeaponPartPositionFunction(position.positioning));
      });
      return Collections.singletonList(mt1);
   }

   public void renderItem(ItemStack weaponItemStack, RenderContext renderContext, MultipartPositioning.Positioner positioner) {
      if(this.builder.getTextureName() != null) {
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + this.builder.getTextureName()));
      } else {
         String itemInstance = null;
         if(itemInstance == null) {
            ItemGrenade grenadeInstance = (ItemGrenade)weaponItemStack.getItem();
            itemInstance = grenadeInstance.getTextureName();
         }

         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + itemInstance));
      }

      this.builder.getModel().render((Entity)null, renderContext.getLimbSwing(), renderContext.getFlimbSwingAmount(), renderContext.getAgeInTicks(), renderContext.getNetHeadYaw(), renderContext.getHeadPitch(), renderContext.getScale());
      PlayerItemInstance itemInstance1 = renderContext.getPlayerItemInstance();
      if(itemInstance1 instanceof PlayerGrenadeInstance) {
         PlayerGrenadeInstance grenadeInstance1 = (PlayerGrenadeInstance)itemInstance1;
         List attachments = grenadeInstance1.getActiveAttachments(renderContext, true);
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
         ;
      }

      ItemAttachment itemAttachment = compatibleAttachment.getAttachment();
      if(positioner != null) {
         if(itemAttachment instanceof Part) {
            positioner.position((Part)itemAttachment, renderContext);
            if(DebugPositioner.isDebugModeEnabled()) {
               DebugPositioner.position((Part)itemAttachment, renderContext);
            }
         } else if(itemAttachment.getRenderablePart() != null) {
            positioner.position(itemAttachment.getRenderablePart(), renderContext);
            if(DebugPositioner.isDebugModeEnabled()) {
               DebugPositioner.position(itemAttachment.getRenderablePart(), renderContext);
            }
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

   public String getTextureName() {
      return this.builder.getTextureName();
   }

   public ModelBase getModel() {
      return this.builder.getModel();
   }

   public Supplier getXRotationCenterOffset() {
      return this.builder.xCenterOffset;
   }

   public Supplier getYRotationCenterOffset() {
      return this.builder.yCenterOffset;
   }

   public Supplier getZRotationCenterOffset() {
      return this.builder.zCenterOffset;
   }

   public Runnable getThrownEntityPositioning() {
      return this.builder.thrownEntityPositioning;
   }

   // $FF: synthetic method
   GrenadeRenderer(GrenadeRenderer.Builder x0, Object x1) {
      this(x0);
   }

   private class WeaponPositionProvider implements MultipartTransitionProvider {
      private WeaponPositionProvider() {
      }

      public List getPositioning(RenderableState state) {
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$grenade$RenderableState[state.ordinal()]) {
         case 1:
            return GrenadeRenderer.this.getComplexTransition(GrenadeRenderer.this.builder.firstPersonPositioningSafetyPinOff, GrenadeRenderer.this.builder.firstPersonLeftHandPositioningSafetyPinOff, GrenadeRenderer.this.builder.firstPersonRightHandPositioningSafetyPinOff, GrenadeRenderer.this.builder.firstPersonCustomPositioningSafetyPinOff);
         case 2:
            return GrenadeRenderer.this.getSimpleTransition(GrenadeRenderer.this.builder.firstPersonPositioningStrikerLeverOff, GrenadeRenderer.this.builder.firstPersonLeftHandPositioningStrikerLeverOff, GrenadeRenderer.this.builder.firstPersonRightHandPositioningStrikerLeverOff, GrenadeRenderer.this.builder.firstPersonCustomPositioningStrikerLeverOff, GrenadeRenderer.this.builder.animationDuration);
         case 3:
            return GrenadeRenderer.this.getComplexTransition(GrenadeRenderer.this.builder.firstPersonPositioningThrowing, GrenadeRenderer.this.builder.firstPersonLeftHandPositioningThrowing, GrenadeRenderer.this.builder.firstPersonRightHandPositioningThrowing, GrenadeRenderer.this.builder.firstPersonCustomPositioningThrowing);
         case 4:
            return GrenadeRenderer.this.getSimpleTransition(GrenadeRenderer.this.builder.firstPersonPositioningThrown, GrenadeRenderer.this.builder.firstPersonLeftHandPositioningThrown, GrenadeRenderer.this.builder.firstPersonRightHandPositioningThrown, GrenadeRenderer.this.builder.firstPersonCustomPositioningThrown, GrenadeRenderer.this.builder.animationDuration);
         case 5:
         case 6:
            return GrenadeRenderer.this.getSimpleTransition(GrenadeRenderer.this.builder.firstPersonPositioning, GrenadeRenderer.this.builder.firstPersonLeftHandPositioning, GrenadeRenderer.this.builder.firstPersonRightHandPositioning, GrenadeRenderer.this.builder.firstPersonCustomPositioning, GrenadeRenderer.this.builder.animationDuration);
         default:
            return null;
         }
      }

      // $FF: synthetic method
      WeaponPositionProvider(Object x1) {
         this();
      }
   }

   private static class StateManagerKey {
      EntityPlayer player;
      int slot = -1;

      public StateManagerKey(EntityPlayer player, int slot) {
         this.player = player;
         this.slot = slot;
      }

      public int hashCode() {
         boolean prime = true;
         byte result = 1;
         int result1 = 31 * result + (this.player == null?0:this.player.hashCode());
         result1 = 31 * result1 + this.slot;
         return result1;
      }

      public boolean equals(Object obj) {
         if(this == obj) {
            return true;
         } else if(obj == null) {
            return false;
         } else if(this.getClass() != obj.getClass()) {
            return false;
         } else {
            GrenadeRenderer.StateManagerKey other = (GrenadeRenderer.StateManagerKey)obj;
            if(this.player == null) {
               if(other.player != null) {
                  return false;
               }
            } else if(!this.player.equals(other.player)) {
               return false;
            }

            return this.slot == other.slot;
         }
      }
   }

   public static class Builder {
      private ModelBase model;
      private String textureName;
      private Consumer entityPositioning;
      private Runnable thrownEntityPositioning = () -> {
      };
      private Consumer inventoryPositioning;
      private Consumer thirdPersonPositioning;
      private Consumer firstPersonPositioning;
      private Consumer firstPersonLeftHandPositioning;
      private Consumer firstPersonRightHandPositioning;
      private LinkedHashMap firstPersonCustomPositioning = new LinkedHashMap();
      private Consumer firstPersonPositioningRunning;
      private Consumer firstPersonLeftHandPositioningRunning;
      private Consumer firstPersonRightHandPositioningRunning;
      private LinkedHashMap firstPersonCustomPositioningRunning = new LinkedHashMap();
      private List firstPersonPositioningSafetyPinOff;
      private List firstPersonLeftHandPositioningSafetyPinOff;
      private List firstPersonRightHandPositioningSafetyPinOff;
      private LinkedHashMap firstPersonCustomPositioningSafetyPinOff = new LinkedHashMap();
      private Consumer firstPersonPositioningStrikerLeverOff;
      private Consumer firstPersonLeftHandPositioningStrikerLeverOff;
      private Consumer firstPersonRightHandPositioningStrikerLeverOff;
      private LinkedHashMap firstPersonCustomPositioningStrikerLeverOff = new LinkedHashMap();
      private List firstPersonPositioningThrowing;
      private List firstPersonLeftHandPositioningThrowing;
      private List firstPersonRightHandPositioningThrowing;
      private LinkedHashMap firstPersonCustomPositioningThrowing = new LinkedHashMap();
      private Consumer firstPersonPositioningThrown;
      private Consumer firstPersonLeftHandPositioningThrown;
      private Consumer firstPersonRightHandPositioningThrown;
      private LinkedHashMap firstPersonCustomPositioningThrown = new LinkedHashMap();
      private long totalTakingPinOffDuration;
      private long totalThrowingDuration;
      private String modId;
      private float normalRandomizingRate = 0.33F;
      private float normalRandomizingAmplitude = 0.06F;
      public int animationDuration = 70;
      private Supplier xCenterOffset = () -> {
         return Float.valueOf(0.0F);
      };
      private Supplier yCenterOffset = () -> {
         return Float.valueOf(0.0F);
      };
      private Supplier zCenterOffset = () -> {
         return Float.valueOf(0.0F);
      };

      public GrenadeRenderer.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public GrenadeRenderer.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public GrenadeRenderer.Builder withAnimationDuration(int animationDuration) {
         this.animationDuration = animationDuration;
         return this;
      }

      public GrenadeRenderer.Builder withNormalRandomizingRate(float normalRandomizingRate) {
         this.normalRandomizingRate = normalRandomizingRate;
         return this;
      }

      public GrenadeRenderer.Builder withTextureName(String textureName) {
         this.textureName = textureName + ".png";
         return this;
      }

      public GrenadeRenderer.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public GrenadeRenderer.Builder withThrownEntityPositioning(Runnable throwEntityPositioning) {
         this.thrownEntityPositioning = throwEntityPositioning;
         return this;
      }

      public GrenadeRenderer.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public GrenadeRenderer.Builder withThirdPersonPositioning(Consumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonPositioning(Consumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonCustomPositioning(Part part, Part attachedTo, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioning.put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public GrenadeRenderer.Builder withFirstPersonPositioningRunning(Consumer firstPersonPositioningRunning) {
         this.firstPersonPositioningRunning = firstPersonPositioningRunning;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonHandPositioningRunning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningRunning = leftHand;
         this.firstPersonRightHandPositioningRunning = rightHand;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonCustomPositioningRunning(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningRunning.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      public GrenadeRenderer.Builder withFirstPersonPositioningThrown(Consumer firstPersonPositioningThrown) {
         this.firstPersonPositioningThrown = firstPersonPositioningThrown;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonHandPositioningThrown(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningThrown = leftHand;
         this.firstPersonRightHandPositioningThrown = rightHand;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonCustomPositioningThrown(Part part, Part attachedTo, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningThrown.put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonPositioningSafetyPinOff(Transition... transitions) {
         this.firstPersonPositioningSafetyPinOff = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonPositioningThrowing(Transition... transitions) {
         this.firstPersonPositioningThrowing = Arrays.asList(transitions);
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonPositioningStrikerLeverOff(Consumer firstPersonPositioningStrikerLeverOff) {
         this.firstPersonPositioningStrikerLeverOff = firstPersonPositioningStrikerLeverOff;
         return this;
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonLeftHandPositioningSafetyPinOff(Transition... transitions) {
         this.firstPersonLeftHandPositioningSafetyPinOff = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonLeftHandPositioningThrowing(Transition... transitions) {
         this.firstPersonLeftHandPositioningThrowing = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonRightHandPositioningThrowing(Transition... transitions) {
         this.firstPersonRightHandPositioningThrowing = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonRightHandPositioningSafetyPinOff(Transition... transitions) {
         this.firstPersonRightHandPositioningSafetyPinOff = Arrays.asList(transitions);
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonHandPositioningStrikerLevelOff(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningStrikerLeverOff = leftHand;
         this.firstPersonRightHandPositioningStrikerLeverOff = rightHand;
         return this;
      }

      public GrenadeRenderer.Builder withFirstPersonCustomPositioningStrikerLeverOff(Part part, Part attachedTo, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioningStrikerLeverOff.put(part, new GrenadeRenderer.SimplePositioning(attachedTo, positioning)) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonCustomPositioningSafetyPinOff(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningSafetyPinOff.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      @SafeVarargs
      public final GrenadeRenderer.Builder withFirstPersonCustomPositioningThrowing(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningThrowing.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public GrenadeRenderer.Builder withEntityRotationCenterOffsets(Supplier xCenterOffset, Supplier yCenterOffset, Supplier zCenterOffset) {
         this.xCenterOffset = xCenterOffset;
         this.yCenterOffset = yCenterOffset;
         this.zCenterOffset = zCenterOffset;
         return this;
      }

      public GrenadeRenderer build() {
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

            GrenadeRenderer renderer = new GrenadeRenderer(this, null);
            if(this.firstPersonPositioning == null) {
               this.firstPersonPositioning = (renderContext) -> {
               };
            }

            if(this.firstPersonPositioningSafetyPinOff == null) {
               this.firstPersonPositioningSafetyPinOff = Collections.singletonList(new Transition(this.firstPersonPositioning, (long)this.animationDuration));
            }

            if(this.firstPersonPositioningThrowing == null) {
               this.firstPersonPositioningThrowing = Collections.singletonList(new Transition(this.firstPersonPositioning, (long)this.animationDuration));
            }

            Iterator var2;
            Transition t;
            for(var2 = this.firstPersonPositioningSafetyPinOff.iterator(); var2.hasNext(); this.totalTakingPinOffDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalTakingPinOffDuration += t.getDuration();
            }

            for(var2 = this.firstPersonPositioningThrowing.iterator(); var2.hasNext(); this.totalThrowingDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalThrowingDuration += t.getDuration();
            }

            if(this.firstPersonPositioningRunning == null) {
               this.firstPersonPositioningRunning = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningStrikerLeverOff == null) {
               if(this.firstPersonPositioningSafetyPinOff != null && !this.firstPersonPositioningSafetyPinOff.isEmpty()) {
                  this.firstPersonPositioningStrikerLeverOff = ((Transition)this.firstPersonPositioningSafetyPinOff.get(this.firstPersonPositioningSafetyPinOff.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonPositioningStrikerLeverOff == null) {
                  this.firstPersonPositioningStrikerLeverOff = this.firstPersonPositioning;
               }
            }

            if(this.firstPersonPositioningThrown == null) {
               if(this.firstPersonPositioningThrowing != null && !this.firstPersonPositioningThrowing.isEmpty()) {
                  this.firstPersonPositioningThrown = ((Transition)this.firstPersonPositioningThrowing.get(this.firstPersonPositioningThrowing.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonPositioningThrown == null) {
                  this.firstPersonPositioningThrown = this.firstPersonPositioning;
               }
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

            if(this.firstPersonLeftHandPositioningSafetyPinOff == null) {
               this.firstPersonLeftHandPositioningSafetyPinOff = (List)this.firstPersonPositioningSafetyPinOff.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonLeftHandPositioningThrowing == null) {
               this.firstPersonLeftHandPositioningThrowing = (List)this.firstPersonPositioningThrowing.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningThrowing == null) {
               this.firstPersonRightHandPositioningThrowing = (List)this.firstPersonPositioningThrowing.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonLeftHandPositioningRunning == null) {
               this.firstPersonLeftHandPositioningRunning = this.firstPersonLeftHandPositioning;
            }

            if(this.firstPersonLeftHandPositioningStrikerLeverOff == null) {
               if(this.firstPersonLeftHandPositioningSafetyPinOff != null && !this.firstPersonLeftHandPositioningSafetyPinOff.isEmpty()) {
                  this.firstPersonLeftHandPositioningStrikerLeverOff = ((Transition)this.firstPersonLeftHandPositioningSafetyPinOff.get(this.firstPersonLeftHandPositioningSafetyPinOff.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonLeftHandPositioningStrikerLeverOff == null) {
                  this.firstPersonLeftHandPositioningStrikerLeverOff = this.firstPersonLeftHandPositioning;
               }
            }

            if(this.firstPersonLeftHandPositioningThrown == null) {
               if(this.firstPersonLeftHandPositioningThrowing != null && !this.firstPersonLeftHandPositioningThrowing.isEmpty()) {
                  this.firstPersonLeftHandPositioningThrown = ((Transition)this.firstPersonLeftHandPositioningThrowing.get(this.firstPersonLeftHandPositioningThrowing.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonLeftHandPositioningThrown == null) {
                  this.firstPersonLeftHandPositioningThrown = this.firstPersonLeftHandPositioning;
               }
            }

            if(this.firstPersonRightHandPositioning == null) {
               this.firstPersonRightHandPositioning = (context) -> {
               };
            }

            if(this.firstPersonRightHandPositioningSafetyPinOff == null) {
               this.firstPersonRightHandPositioningSafetyPinOff = (List)this.firstPersonPositioningSafetyPinOff.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningThrowing == null) {
               this.firstPersonRightHandPositioningThrowing = (List)this.firstPersonPositioningThrowing.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningRunning == null) {
               this.firstPersonRightHandPositioningRunning = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningStrikerLeverOff == null) {
               if(this.firstPersonRightHandPositioningSafetyPinOff != null && !this.firstPersonRightHandPositioningSafetyPinOff.isEmpty()) {
                  this.firstPersonRightHandPositioningStrikerLeverOff = ((Transition)this.firstPersonRightHandPositioningSafetyPinOff.get(this.firstPersonRightHandPositioningSafetyPinOff.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonRightHandPositioningStrikerLeverOff == null) {
                  this.firstPersonRightHandPositioningStrikerLeverOff = this.firstPersonRightHandPositioning;
               }
            }

            if(this.firstPersonRightHandPositioningThrown == null) {
               if(this.firstPersonRightHandPositioningThrowing != null && !this.firstPersonRightHandPositioningThrowing.isEmpty()) {
                  this.firstPersonRightHandPositioningThrown = ((Transition)this.firstPersonRightHandPositioningThrowing.get(this.firstPersonRightHandPositioningThrowing.size() - 1)).getItemPositioning();
               }

               if(this.firstPersonRightHandPositioningThrown == null) {
                  this.firstPersonRightHandPositioningThrown = this.firstPersonRightHandPositioning;
               }
            }

            this.firstPersonCustomPositioningSafetyPinOff.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningSafetyPinOff.size()) {
                  throw new IllegalStateException("Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningSafetyPinOff.size() + ", actual: " + t.size());
               }
            });
            this.firstPersonCustomPositioningThrowing.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningThrowing.size()) {
                  throw new IllegalStateException("Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningThrowing.size() + ", actual: " + t.size());
               }
            });
            if(!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningStrikerLeverOff.isEmpty()) {
               this.firstPersonCustomPositioning.forEach((part, pos) -> {
                  this.firstPersonCustomPositioningStrikerLeverOff.put(part, new GrenadeRenderer.SimplePositioning((Part)null, pos.positioning));
               });
            }

            if(!this.firstPersonCustomPositioning.isEmpty() && this.firstPersonCustomPositioningThrown.isEmpty()) {
               this.firstPersonCustomPositioning.forEach((part, pos) -> {
                  this.firstPersonCustomPositioningThrown.put(part, new GrenadeRenderer.SimplePositioning((Part)null, pos.positioning));
               });
            }

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

   private static class SimplePositioning {
      Part attachedTo;
      Consumer positioning;

      SimplePositioning(Part attachedTo, Consumer positioning) {
         this.attachedTo = attachedTo;
         this.positioning = positioning;
      }
   }
}
