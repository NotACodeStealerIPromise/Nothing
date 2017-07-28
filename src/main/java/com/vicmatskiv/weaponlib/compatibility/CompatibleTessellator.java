package com.vicmatskiv.weaponlib.compatibility;

import net.minecraft.client.renderer.Tessellator;

public class CompatibleTessellator {
   private static Tessellator tessellator;
   private static CompatibleTessellator compatibleTesselator;

   public static CompatibleTessellator getInstance() {
      return compatibleTesselator;
   }

   public void startDrawingQuads() {
      tessellator.startDrawingQuads();
   }

   public void startDrawingParticles() {
      tessellator.startDrawing(7);
   }

   public void startDrawingLines() {
      tessellator.startDrawing(1);
   }

   public void addVertexWithUV(double d, double e, double zLevel, float i, float j) {
      tessellator.addVertexWithUV(d, e, zLevel, (double)i, (double)j);
   }

   public void draw() {
      tessellator.draw();
   }

   public void setLightMap(int j, int k) {
      tessellator.setBrightness(200);
   }

   public void setColorRgba(float red, float green, float blue, float alpha) {
      tessellator.setColorRGBA_F(red, green, blue, alpha);
   }

   public void addVertex(float x, float y, float z) {
      tessellator.addVertex((double)x, (double)y, (double)z);
   }

   public void endVertex() {
   }

   static {
      tessellator = Tessellator.instance;
      compatibleTesselator = new CompatibleTessellator();
   }
}
