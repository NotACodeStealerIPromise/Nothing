package com.vicmatskiv.weaponlib.melee;

import com.vicmatskiv.weaponlib.ClientModContext;
import com.vicmatskiv.weaponlib.CompatibleAttachment;
import com.vicmatskiv.weaponlib.CustomRenderer;
import com.vicmatskiv.weaponlib.DefaultPart;
import com.vicmatskiv.weaponlib.ItemAttachment;
import com.vicmatskiv.weaponlib.ItemSkin;
import com.vicmatskiv.weaponlib.ModelWithAttachments;
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
import com.vicmatskiv.weaponlib.compatibility.CompatibleMeleeRenderer;
import com.vicmatskiv.weaponlib.melee.AsyncMeleeState;
import com.vicmatskiv.weaponlib.melee.ItemMelee;
import com.vicmatskiv.weaponlib.melee.MeleeSkin;
import com.vicmatskiv.weaponlib.melee.PlayerMeleeInstance;
import com.vicmatskiv.weaponlib.melee.RenderableState;
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

public class MeleeRenderer extends CompatibleMeleeRenderer {
   private static final Logger logger = LogManager.getLogger(MeleeRenderer.class);
   private static final float DEFAULT_RANDOMIZING_RATE = 0.33F;
   private static final float DEFAULT_NORMAL_RANDOMIZING_AMPLITUDE = 0.06F;
   private static final int DEFAULT_ANIMATION_DURATION = 70;
   private MeleeRenderer.Builder builder;
   private Map firstPersonStateManagers;
   private MultipartTransitionProvider weaponTransitionProvider;
   protected ClientModContext clientModContext;

   private MeleeRenderer(MeleeRenderer.Builder builder) {
      super(builder);
      this.builder = builder;
      this.firstPersonStateManagers = new HashMap();
      this.weaponTransitionProvider = new MeleeRenderer.WeaponPositionProvider(null);
   }

   protected long getTotalAttackDuration() {
      return this.builder.totalAttackingDuration;
   }

   protected long getTotalHeavyAttackDuration() {
      return this.builder.totalHeavyAttackingDuration;
   }

   protected ClientModContext getClientModContext() {
      return this.clientModContext;
   }

   public void setClientModContext(ClientModContext clientModContext) {
      this.clientModContext = clientModContext;
   }

   protected CompatibleMeleeRenderer.StateDescriptor getStateDescriptor(EntityPlayer player, ItemStack itemStack) {
      float amplitude = this.builder.normalRandomizingAmplitude;
      float rate = this.builder.normalRandomizingRate;
      RenderableState currentState = null;
      PlayerItemInstance playerItemInstance = this.clientModContext.getPlayerItemInstanceRegistry().getItemInstance(player, itemStack);
      PlayerMeleeInstance playerMeleeInstance = null;
      if(playerItemInstance != null && playerItemInstance instanceof PlayerMeleeInstance && playerItemInstance.getItem() == itemStack.getItem()) {
         playerMeleeInstance = (PlayerMeleeInstance)playerItemInstance;
      } else {
         logger.error("Invalid or mismatching item. Player item instance: {}. Item stack: {}", new Object[]{playerItemInstance, itemStack});
      }

      if(playerMeleeInstance != null) {
         AsyncMeleeState stateManager = this.getNextNonExpiredState(playerMeleeInstance);
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$melee$MeleeState[stateManager.getState().ordinal()]) {
         case 1:
         case 2:
            currentState = RenderableState.ATTACKING;
            break;
         case 3:
         case 4:
            currentState = RenderableState.HEAVY_ATTACKING;
            break;
         case 5:
         case 6:
         case 7:
         case 8:
            currentState = RenderableState.MODIFYING;
            break;
         default:
            if(player.isSprinting() && this.builder.firstPersonPositioningRunning != null) {
               currentState = RenderableState.RUNNING;
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
         stateManager1.setState(currentState, true, currentState == RenderableState.ATTACKING);
      }

      return new CompatibleMeleeRenderer.StateDescriptor(playerMeleeInstance, stateManager1, rate, amplitude);
   }

   private AsyncMeleeState getNextNonExpiredState(PlayerMeleeInstance playerWeaponState) {
      AsyncMeleeState asyncWeaponState = null;

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
         attachments = ((ItemMelee)weaponItemStack.getItem()).getActiveAttachments(renderContext.getPlayer(), weaponItemStack);
      }

      if(this.builder.getTextureName() != null) {
         Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.builder.getModId() + ":textures/models/" + this.builder.getTextureName()));
      } else {
         String textureName = null;
         CompatibleAttachment compatibleSkin = (CompatibleAttachment)attachments.stream().filter((ca) -> {
            return ca.getAttachment() instanceof MeleeSkin;
         }).findAny().orElse((Object)null);
         if(compatibleSkin != null) {
            PlayerItemInstance weapon = this.getClientModContext().getPlayerItemInstanceRegistry().getItemInstance(renderContext.getPlayer(), weaponItemStack);
            if(weapon instanceof PlayerMeleeInstance) {
               int textureIndex = ((PlayerMeleeInstance)weapon).getActiveTextureIndex();
               if(textureIndex >= 0) {
                  textureName = ((MeleeSkin)compatibleSkin.getAttachment()).getTextureVariant(textureIndex) + ".png";
               }
            }
         }

         if(textureName == null) {
            ItemMelee weapon1 = (ItemMelee)weaponItemStack.getItem();
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

   protected BiConsumer getPartDebugPositioning() {
      return this.builder.partDebugPositioning;
   }

   // $FF: synthetic method
   MeleeRenderer(MeleeRenderer.Builder x0, Object x1) {
      this(x0);
   }

   private class WeaponPositionProvider implements MultipartTransitionProvider {
      private WeaponPositionProvider() {
      }

      public List getPositioning(RenderableState state) {
         switch(null.$SwitchMap$com$vicmatskiv$weaponlib$melee$RenderableState[state.ordinal()]) {
         case 1:
            return MeleeRenderer.this.getSimpleTransition(MeleeRenderer.this.builder.firstPersonPositioningModifying, MeleeRenderer.this.builder.firstPersonLeftHandPositioningModifying, MeleeRenderer.this.builder.firstPersonRightHandPositioningModifying, MeleeRenderer.this.builder.firstPersonCustomPositioning, MeleeRenderer.this.builder.animationDuration);
         case 2:
            return MeleeRenderer.this.getSimpleTransition(MeleeRenderer.this.builder.firstPersonPositioningRunning, MeleeRenderer.this.builder.firstPersonLeftHandPositioningRunning, MeleeRenderer.this.builder.firstPersonRightHandPositioningRunning, MeleeRenderer.this.builder.firstPersonCustomPositioning, MeleeRenderer.this.builder.animationDuration);
         case 3:
            return MeleeRenderer.this.getComplexTransition(MeleeRenderer.this.builder.firstPersonPositioningAttacking, MeleeRenderer.this.builder.firstPersonLeftHandPositioningAttacking, MeleeRenderer.this.builder.firstPersonRightHandPositioningAttacking, MeleeRenderer.this.builder.firstPersonCustomPositioningAttacking);
         case 4:
            return MeleeRenderer.this.getComplexTransition(MeleeRenderer.this.builder.firstPersonPositioningHeavyAttacking, MeleeRenderer.this.builder.firstPersonLeftHandPositioningHeavyAttacking, MeleeRenderer.this.builder.firstPersonRightHandPositioningHeavyAttacking, MeleeRenderer.this.builder.firstPersonCustomPositioningHeavyAttacking);
         case 5:
            return MeleeRenderer.this.getSimpleTransition(MeleeRenderer.this.builder.firstPersonPositioning, MeleeRenderer.this.builder.firstPersonLeftHandPositioning, MeleeRenderer.this.builder.firstPersonRightHandPositioning, MeleeRenderer.this.builder.firstPersonCustomPositioning, MeleeRenderer.this.builder.animationDuration);
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
      private BiConsumer partDebugPositioning;
      private Consumer entityPositioning;
      private Consumer inventoryPositioning;
      private Consumer thirdPersonPositioning;
      private Consumer firstPersonPositioning;
      private Consumer firstPersonPositioningRunning;
      private Consumer firstPersonPositioningModifying;
      private Consumer firstPersonLeftHandPositioning;
      private Consumer firstPersonLeftHandPositioningRunning;
      private Consumer firstPersonLeftHandPositioningModifying;
      private Consumer firstPersonRightHandPositioning;
      private Consumer firstPersonRightHandPositioningRunning;
      private Consumer firstPersonRightHandPositioningModifying;
      private List firstPersonPositioningAttacking;
      private List firstPersonLeftHandPositioningAttacking;
      private List firstPersonRightHandPositioningAttacking;
      private List firstPersonPositioningHeavyAttacking;
      private List firstPersonLeftHandPositioningHeavyAttacking;
      private List firstPersonRightHandPositioningHeavyAttacking;
      private long totalAttackingDuration;
      private long totalHeavyAttackingDuration;
      private String modId;
      private float normalRandomizingRate = 0.33F;
      private float normalRandomizingAmplitude = 0.06F;
      private LinkedHashMap firstPersonCustomPositioning = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningAttacking = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningHeavyAttacking = new LinkedHashMap();
      private LinkedHashMap firstPersonCustomPositioningEjectSpentRound = new LinkedHashMap();
      private boolean hasRecoilPositioningDefined;
      public int animationDuration = 70;

      public MeleeRenderer.Builder withModId(String modId) {
         this.modId = modId;
         return this;
      }

      public MeleeRenderer.Builder withModel(ModelBase model) {
         this.model = model;
         return this;
      }

      public MeleeRenderer.Builder withAnimationDuration(int animationDuration) {
         this.animationDuration = animationDuration;
         return this;
      }

      public MeleeRenderer.Builder withNormalRandomizingRate(float normalRandomizingRate) {
         this.normalRandomizingRate = normalRandomizingRate;
         return this;
      }

      public MeleeRenderer.Builder withTextureName(String textureName) {
         this.textureName = textureName + ".png";
         return this;
      }

      public MeleeRenderer.Builder withEntityPositioning(Consumer entityPositioning) {
         this.entityPositioning = entityPositioning;
         return this;
      }

      public MeleeRenderer.Builder withInventoryPositioning(Consumer inventoryPositioning) {
         this.inventoryPositioning = inventoryPositioning;
         return this;
      }

      public MeleeRenderer.Builder withPartDebugPositioning(BiConsumer partDebugPositioning) {
         this.partDebugPositioning = partDebugPositioning;
         return this;
      }

      public MeleeRenderer.Builder withThirdPersonPositioning(Consumer thirdPersonPositioning) {
         this.thirdPersonPositioning = thirdPersonPositioning;
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonPositioning(Consumer firstPersonPositioning) {
         this.firstPersonPositioning = firstPersonPositioning;
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonPositioningRunning(Consumer firstPersonPositioningRunning) {
         this.firstPersonPositioningRunning = firstPersonPositioningRunning;
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonPositioningAttacking(Transition... transitions) {
         this.firstPersonPositioningAttacking = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonPositioningHeavyAttacking(Transition... transitions) {
         this.firstPersonPositioningHeavyAttacking = Arrays.asList(transitions);
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonPositioningModifying(Consumer firstPersonPositioningModifying) {
         this.firstPersonPositioningModifying = firstPersonPositioningModifying;
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonHandPositioning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioning = leftHand;
         this.firstPersonRightHandPositioning = rightHand;
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonHandPositioningRunning(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningRunning = leftHand;
         this.firstPersonRightHandPositioningRunning = rightHand;
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonLeftHandPositioningAttacking(Transition... transitions) {
         this.firstPersonLeftHandPositioningAttacking = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonLeftHandPositioningHeavyAttacking(Transition... transitions) {
         this.firstPersonLeftHandPositioningHeavyAttacking = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonRightHandPositioningHeavyAttacking(Transition... transitions) {
         this.firstPersonRightHandPositioningHeavyAttacking = Arrays.asList(transitions);
         return this;
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonRightHandPositioningAttacking(Transition... transitions) {
         this.firstPersonRightHandPositioningAttacking = Arrays.asList(transitions);
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonHandPositioningModifying(Consumer leftHand, Consumer rightHand) {
         this.firstPersonLeftHandPositioningModifying = leftHand;
         this.firstPersonRightHandPositioningModifying = rightHand;
         return this;
      }

      public MeleeRenderer.Builder withFirstPersonCustomPositioning(Part part, Consumer positioning) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else if(this.firstPersonCustomPositioning.put(part, positioning) != null) {
            throw new IllegalArgumentException("Part " + part + " already added");
         } else {
            return this;
         }
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonCustomPositioningAttacking(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningAttacking.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonCustomPositioningHeavyAttacking(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningHeavyAttacking.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      @SafeVarargs
      public final MeleeRenderer.Builder withFirstPersonCustomPositioningEjectSpentRound(Part part, Transition... transitions) {
         if(part instanceof DefaultPart) {
            throw new IllegalArgumentException("Part " + part + " is not custom");
         } else {
            this.firstPersonCustomPositioningEjectSpentRound.put(part, Arrays.asList(transitions));
            return this;
         }
      }

      public MeleeRenderer build() {
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

            MeleeRenderer renderer = new MeleeRenderer(this, null);
            if(this.firstPersonPositioning == null) {
               this.firstPersonPositioning = (renderContext) -> {
               };
            }

            if(this.firstPersonPositioningAttacking == null) {
               this.firstPersonPositioningAttacking = Collections.singletonList(new Transition(this.firstPersonPositioning, (long)this.animationDuration));
            }

            if(this.firstPersonPositioningHeavyAttacking == null) {
               this.firstPersonPositioningHeavyAttacking = Collections.singletonList(new Transition(this.firstPersonPositioning, (long)this.animationDuration));
            }

            Iterator var2;
            Transition t;
            for(var2 = this.firstPersonPositioningAttacking.iterator(); var2.hasNext(); this.totalAttackingDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalAttackingDuration += t.getDuration();
            }

            for(var2 = this.firstPersonPositioningHeavyAttacking.iterator(); var2.hasNext(); this.totalHeavyAttackingDuration += t.getPause()) {
               t = (Transition)var2.next();
               this.totalHeavyAttackingDuration += t.getDuration();
            }

            if(this.firstPersonPositioningRunning == null) {
               this.firstPersonPositioningRunning = this.firstPersonPositioning;
            }

            if(this.firstPersonPositioningModifying == null) {
               this.firstPersonPositioningModifying = this.firstPersonPositioning;
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

            if(this.firstPersonLeftHandPositioningAttacking == null) {
               this.firstPersonLeftHandPositioningAttacking = (List)this.firstPersonPositioningAttacking.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonLeftHandPositioningHeavyAttacking == null) {
               this.firstPersonLeftHandPositioningHeavyAttacking = (List)this.firstPersonPositioningHeavyAttacking.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningHeavyAttacking == null) {
               this.firstPersonRightHandPositioningHeavyAttacking = (List)this.firstPersonPositioningHeavyAttacking.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
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

            if(this.firstPersonRightHandPositioningAttacking == null) {
               this.firstPersonRightHandPositioningAttacking = (List)this.firstPersonPositioningAttacking.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningHeavyAttacking == null) {
               this.firstPersonRightHandPositioningHeavyAttacking = (List)this.firstPersonPositioningHeavyAttacking.stream().map((t) -> {
                  return new Transition((c) -> {
                  }, 0L);
               }).collect(Collectors.toList());
            }

            if(this.firstPersonRightHandPositioningRunning == null) {
               this.firstPersonRightHandPositioningRunning = this.firstPersonRightHandPositioning;
            }

            if(this.firstPersonRightHandPositioningModifying == null) {
               this.firstPersonRightHandPositioningModifying = this.firstPersonRightHandPositioning;
            }

            this.firstPersonCustomPositioningAttacking.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningAttacking.size()) {
                  throw new IllegalStateException("Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningAttacking.size() + ", actual: " + t.size());
               }
            });
            this.firstPersonCustomPositioningHeavyAttacking.forEach((p, t) -> {
               if(t.size() != this.firstPersonPositioningHeavyAttacking.size()) {
                  throw new IllegalStateException("Custom reloading transition number mismatch. Expected " + this.firstPersonPositioningAttacking.size() + ", actual: " + t.size());
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
