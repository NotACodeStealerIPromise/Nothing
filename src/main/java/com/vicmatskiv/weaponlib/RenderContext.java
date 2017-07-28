package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.PlayerItemInstance;
import com.vicmatskiv.weaponlib.PlayerWeaponInstance;
import com.vicmatskiv.weaponlib.animation.MatrixHelper;
import com.vicmatskiv.weaponlib.animation.PartPositionProvider;
import com.vicmatskiv.weaponlib.compatibility.CompatibleTransformType;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.util.vector.Matrix4f;

public class RenderContext implements PartPositionProvider {
   private EntityPlayer player;
   private ItemStack itemStack;
   private float limbSwing;
   private float flimbSwingAmount;
   private float ageInTicks;
   private float netHeadYaw;
   private float headPitch;
   private float scale;
   private float transitionProgress;
   private CompatibleTransformType compatibleTransformType;
   private Object fromState;
   private Object toState;
   private ModContext modContext;
   private PlayerItemInstance playerItemInstance;
   private Map attachablePartPositions;

   public RenderContext(ModContext modContext, EntityPlayer player, ItemStack itemStack) {
      this.modContext = modContext;
      this.player = player;
      this.itemStack = itemStack;
      this.attachablePartPositions = new HashMap();
   }

   public ModContext getModContext() {
      return this.modContext;
   }

   public float getLimbSwing() {
      return this.limbSwing;
   }

   public void setLimbSwing(float limbSwing) {
      this.limbSwing = limbSwing;
   }

   public float getFlimbSwingAmount() {
      return this.flimbSwingAmount;
   }

   public void setFlimbSwingAmount(float flimbSwingAmount) {
      this.flimbSwingAmount = flimbSwingAmount;
   }

   public float getAgeInTicks() {
      return this.ageInTicks;
   }

   public void setAgeInTicks(float ageInTicks) {
      this.ageInTicks = ageInTicks;
   }

   public float getNetHeadYaw() {
      return this.netHeadYaw;
   }

   public void setNetHeadYaw(float netHeadYaw) {
      this.netHeadYaw = netHeadYaw;
   }

   public float getHeadPitch() {
      return this.headPitch;
   }

   public void setHeadPitch(float headPitch) {
      this.headPitch = headPitch;
   }

   public float getScale() {
      return this.scale;
   }

   public void setScale(float scale) {
      this.scale = scale;
   }

   public void setPlayer(EntityPlayer player) {
      this.player = player;
   }

   public void setWeapon(ItemStack weapon) {
      this.itemStack = weapon;
   }

   public EntityPlayer getPlayer() {
      return this.player;
   }

   public ItemStack getWeapon() {
      return this.itemStack;
   }

   public CompatibleTransformType getCompatibleTransformType() {
      return this.compatibleTransformType;
   }

   public void setCompatibleTransformType(CompatibleTransformType compatibleTransformType) {
      this.compatibleTransformType = compatibleTransformType;
   }

   public Object getFromState() {
      return this.fromState;
   }

   public void setFromState(Object fromState) {
      this.fromState = fromState;
   }

   public Object getToState() {
      return this.toState;
   }

   public void setToState(Object toState) {
      this.toState = toState;
   }

   public float getTransitionProgress() {
      return this.transitionProgress;
   }

   public void setTransitionProgress(float transitionProgress) {
      this.transitionProgress = transitionProgress;
   }

   public PlayerItemInstance getPlayerItemInstance() {
      return this.playerItemInstance;
   }

   public void setPlayerItemInstance(PlayerItemInstance playerItemInstance) {
      this.playerItemInstance = playerItemInstance;
   }

   public PlayerWeaponInstance getWeaponInstance() {
      if(this.playerItemInstance instanceof PlayerWeaponInstance) {
         return (PlayerWeaponInstance)this.playerItemInstance;
      } else {
         PlayerWeaponInstance itemInstance = (PlayerWeaponInstance)this.modContext.getPlayerItemInstanceRegistry().getItemInstance(this.player, this.itemStack);
         return itemInstance instanceof PlayerWeaponInstance?itemInstance:null;
      }
   }

   public void capturePartPosition(Part part) {
      this.attachablePartPositions.put(part, MatrixHelper.captureMatrix());
   }

   public Matrix4f getPartPosition(Object part) {
      if(part == null) {
         part = Part.MAIN_ITEM;
      }

      return (Matrix4f)this.attachablePartPositions.get(part);
   }
}
