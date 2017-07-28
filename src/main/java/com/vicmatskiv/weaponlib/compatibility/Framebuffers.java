package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.client.renderer.OpenGlHelper;
import org.lwjgl.opengl.GL11;

public class Framebuffers {
   public static int getCurrentFramebuffer() {
      return GL11.glGetInteger('貦');
   }

   public static void unbindFramebuffer() {
      if(OpenGlHelper.isFramebufferEnabled()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, 0);
      }

   }

   public static void bindFramebuffer(int framebufferId, boolean depthEnabled, int width, int height) {
      if(OpenGlHelper.isFramebufferEnabled()) {
         OpenGlHelper.func_153171_g(OpenGlHelper.field_153198_e, framebufferId);
         if(depthEnabled) {
            GL11.glViewport(0, 0, width, height);
         }
      }

   }
}
