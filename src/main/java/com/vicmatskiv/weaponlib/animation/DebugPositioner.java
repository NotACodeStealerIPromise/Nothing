package com.vicmatskiv.weaponlib.animation;

import com.vicmatskiv.weaponlib.KeyBindings;
import com.vicmatskiv.weaponlib.Part;
import com.vicmatskiv.weaponlib.RenderContext;
import com.vicmatskiv.weaponlib.animation.MatrixHelper;
import com.vicmatskiv.weaponlib.compatibility.CompatibilityProvider;
import com.vicmatskiv.weaponlib.tracking.PlayerEntityTracker;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class DebugPositioner {
   private static final Logger logger = LogManager.getLogger(DebugPositioner.class);
   private static final String WEAPONLIB_DEBUG_PROPERTY = "weaponlib.debug";
   private static Boolean debugModeEnabled;
   private static Part currentPart;
   private static Entity watchableEntity;
   private static Map partPositions = new HashMap();
   private static Map transitionConfigurations = new HashMap();

   private static DebugPositioner.Position getCurrentPartPosition() {
      return (DebugPositioner.Position)partPositions.get(currentPart);
   }

   public static void incrementXRotation(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.xRotation = partPosition.xRotation + increment;
         logger.debug("Debug rotations: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.xRotation), Float.valueOf(partPosition.yRotation), Float.valueOf(partPosition.zRotation)});
      }
   }

   public static void incrementYRotation(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.yRotation = partPosition.yRotation + increment;
         logger.debug("Debug rotations: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.xRotation), Float.valueOf(partPosition.yRotation), Float.valueOf(partPosition.zRotation)});
      }
   }

   public static void incrementZRotation(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.zRotation = partPosition.zRotation + increment;
         logger.debug("Debug rotations: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.xRotation), Float.valueOf(partPosition.yRotation), Float.valueOf(partPosition.zRotation)});
      }
   }

   public static void incrementXPosition(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.x = partPosition.x + partPosition.step * increment;
         logger.debug("Debug position: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.x), Float.valueOf(partPosition.y), Float.valueOf(partPosition.z)});
      }
   }

   public static void incrementYPosition(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.y = partPosition.y + partPosition.step * increment;
         logger.debug("Debug position: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.x), Float.valueOf(partPosition.y), Float.valueOf(partPosition.z)});
      }
   }

   public static void incrementZPosition(float increment) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.z = partPosition.z + partPosition.step * increment;
         logger.debug("Debug position: ({}, {}, {}) ", new Object[]{Float.valueOf(partPosition.x), Float.valueOf(partPosition.y), Float.valueOf(partPosition.z)});
      }
   }

   public static void setScale(float scale) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.scale = scale;
         logger.debug("Scale set to {}", new Object[]{Float.valueOf(scale)});
      }
   }

   public static void setStep(float step) {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         partPosition.step = step;
         logger.debug("Step set to {}", new Object[]{Float.valueOf(step)});
      }
   }

   public static void setDebugMode(boolean enabled) {
      debugModeEnabled = Boolean.valueOf(enabled);
      if(debugModeEnabled.booleanValue()) {
         KeyBindings.bindDebugKeys();
      }

   }

   public static boolean isDebugModeEnabled() {
      if(debugModeEnabled == null) {
         debugModeEnabled = Boolean.valueOf(Boolean.getBoolean("weaponlib.debug"));
      }

      return debugModeEnabled.booleanValue();
   }

   public static void reset() {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition == null) {
         CompatibilityProvider.compatibility.addChatMessage(CompatibilityProvider.compatibility.clientPlayer(), "Debug part not selected");
      } else {
         transitionConfigurations.clear();
         partPosition.x = partPosition.y = partPosition.z = partPosition.xRotation = partPosition.yRotation = partPosition.zRotation = 0.0F;
         partPosition.scale = 1.0F;
         partPosition.step = 0.025F;
      }
   }

   public static void setDebugPart(Part part) {
      currentPart = part;
      partPositions.computeIfAbsent(part, (p) -> {
         return new DebugPositioner.Position();
      });
   }

   public static Part getDebugPart() {
      return currentPart;
   }

   public static void configureTransitionPause(int transitionNumber, long pause) {
      DebugPositioner.TransitionConfiguration transitionConfiguration = getTransitionConfiguration(transitionNumber, true);
      transitionConfiguration.pause = pause;
   }

   public static DebugPositioner.TransitionConfiguration getTransitionConfiguration(int transitionNumber, boolean init) {
      return (DebugPositioner.TransitionConfiguration)transitionConfigurations.computeIfAbsent(Integer.valueOf(transitionNumber), (k) -> {
         return init?new DebugPositioner.TransitionConfiguration():null;
      });
   }

   public static void position(Part part, RenderContext renderContext) {
      if(part == currentPart) {
         DebugPositioner.Position partPosition = getCurrentPartPosition();
         if(partPosition != null) {
            GL11.glScalef(partPosition.scale, partPosition.scale, partPosition.scale);
            GL11.glRotatef(partPosition.xRotation, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(partPosition.yRotation, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(partPosition.zRotation, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(partPosition.x, partPosition.y, partPosition.z);
         }
      }
   }

   public static void showCode() {
      DebugPositioner.Position partPosition = getCurrentPartPosition();
      if(partPosition != null) {
         StringBuilder result = new StringBuilder();
         result.append(String.format("GL11.glScalef(%ff, %ff, %ff);\n", new Object[]{Float.valueOf(partPosition.scale), Float.valueOf(partPosition.scale), Float.valueOf(partPosition.scale)}));
         result.append(String.format("GL11.glRotatef(%ff, 1f, 0f, 0f);\n", new Object[]{Float.valueOf(partPosition.xRotation)}));
         result.append(String.format("GL11.glRotatef(%ff, 0f, 1f, 0f);\n", new Object[]{Float.valueOf(partPosition.yRotation)}));
         result.append(String.format("GL11.glRotatef(%ff, 0f, 0f, 1f);\n", new Object[]{Float.valueOf(partPosition.zRotation)}));
         result.append(String.format("GL11.glTranslatef(%ff, %ff, %ff);", new Object[]{Float.valueOf(partPosition.x), Float.valueOf(partPosition.y), Float.valueOf(partPosition.z)}));
         logger.debug("Generated positioning code: \n" + result);
         System.out.println("\n" + result);
      }
   }

   public static void watch() {
      PlayerEntityTracker tracker = PlayerEntityTracker.getTracker(CompatibilityProvider.compatibility.clientPlayer());
      System.out.println("Trackable entities: " + tracker.getTrackableEntitites());
   }

   public static Entity getWatchableEntity() {
      return watchableEntity;
   }

   public static void showCurrentMatrix(String message) {
      showCurrentMatrix((Object)null, message);
   }

   public static void showCurrentMatrix(Object part, String message) {
      if(part == null || part == currentPart) {
         Matrix4f preparedPositionMatrix = MatrixHelper.captureMatrix();
         logger.trace("Current matrix: {} {}", new Object[]{message, formatMatrix(preparedPositionMatrix)});
      }
   }

   public static String formatMatrix(Matrix4f m) {
      StringBuilder buf = new StringBuilder();
      buf.append("\n");
      buf.append(String.format("%4.2f %4.2f %4.2f %4.2f\n", new Object[]{Float.valueOf(m.m00), Float.valueOf(m.m10), Float.valueOf(m.m20), Float.valueOf(m.m30)}));
      buf.append(String.format("%4.2f %4.2f %4.2f %4.2f\n", new Object[]{Float.valueOf(m.m01), Float.valueOf(m.m11), Float.valueOf(m.m21), Float.valueOf(m.m31)}));
      buf.append(String.format("%4.2f %4.2f %4.2f %4.2f\n", new Object[]{Float.valueOf(m.m02), Float.valueOf(m.m12), Float.valueOf(m.m22), Float.valueOf(m.m32)}));
      buf.append(String.format("%4.2f %4.2f %4.2f %4.2f\n", new Object[]{Float.valueOf(m.m03), Float.valueOf(m.m13), Float.valueOf(m.m23), Float.valueOf(m.m33)}));
      return buf.toString();
   }

   private static class Position {
      private float xRotation;
      private float yRotation;
      private float zRotation;
      private float x;
      private float y;
      private float z;
      private float scale;
      private float step;

      private Position() {
         this.scale = 1.0F;
         this.step = 0.025F;
      }

      // $FF: synthetic method
      Position(Object x0) {
         this();
      }
   }

   public static final class TransitionConfiguration {
      private long pause;

      public long getPause() {
         return this.pause;
      }

      public void setPause(long pause) {
         this.pause = pause;
      }
   }
}
