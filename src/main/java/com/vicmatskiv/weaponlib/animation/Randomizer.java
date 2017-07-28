package com.vicmatskiv.weaponlib.animation;

import com.vicmatskiv.weaponlib.animation.MatrixHelper;
import java.nio.FloatBuffer;
import java.util.Random;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

final class Randomizer {
   private Random random = new Random();
   private Matrix4f beforeMatrix;
   private Matrix4f afterMatrix;
   private Matrix4f currentMatrix = this.getMatrixForPositioning(() -> {
   });
   private long startTime;
   private float rate = 0.25F;
   private float amplitude = 0.04F;
   private float xbias = 0.0F;
   private float ybias = 0.0F;
   private float zbias = 0.0F;

   public Randomizer() {
      this.next();
   }

   private boolean reconfigure(float rate, float amplitude) {
      if(rate == this.rate && amplitude == this.amplitude) {
         return false;
      } else {
         boolean reconfigured = false;
         if(rate != this.rate || amplitude != this.amplitude) {
            if(rate == 0.0F && amplitude == 0.0F) {
               this.afterMatrix = this.beforeMatrix = this.currentMatrix = this.getMatrixForPositioning(() -> {
               });
            } else {
               reconfigured = true;
            }
         }

         this.rate = rate;
         this.amplitude = amplitude;
         if(reconfigured) {
            this.next();
         }

         return reconfigured;
      }
   }

   private void next() {
      this.beforeMatrix = this.currentMatrix;
      this.afterMatrix = this.createRandomMatrix();
      this.startTime = System.currentTimeMillis();
   }

   private Matrix4f createRandomMatrix() {
      Runnable c = () -> {
         float maxAngle = 5.0F;
         float xRotation = maxAngle * this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.xbias);
         float yRotation = maxAngle * this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.ybias);
         float zRotation = maxAngle * this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.zbias) * 3.0F;
         GL11.glRotatef(xRotation, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(yRotation, 0.0F, 1.0F, 0.0F);
         GL11.glRotatef(zRotation, 0.0F, 0.0F, 1.0F);
         float xRandomOffset = this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.xbias);
         float yRandomOffset = this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.ybias);
         float zRandomOffset = this.amplitude * ((this.random.nextFloat() - 0.5F) * 2.0F + this.zbias) / 3.0F;
         GL11.glTranslatef(xRandomOffset, yRandomOffset, zRandomOffset);
      };
      return this.getMatrixForPositioning(c);
   }

   private Matrix4f getMatrixForPositioning(Runnable position) {
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      FloatBuffer buf = BufferUtils.createFloatBuffer(16);
      position.run();
      GL11.glGetFloat(2982, buf);
      buf.rewind();
      Matrix4f matrix = new Matrix4f();
      matrix.load(buf);
      GL11.glPopMatrix();
      return matrix;
   }

   public void update(float rate, float amplitude) {
      this.reconfigure(rate, amplitude);
      if(rate != 0.0F && amplitude != 0.0F) {
         long currentTime = System.currentTimeMillis();
         float progress = (float)(currentTime - this.startTime) * rate / 1000.0F;
         if(progress >= 1.0F) {
            this.next();
            progress = 0.0F;
         }

         Matrix4f currentTransformMatrix = MatrixHelper.captureMatrix();
         Matrix4f m1 = MatrixHelper.interpolateMatrix(this.beforeMatrix, 1.0F - progress);
         Matrix4f m2 = MatrixHelper.interpolateMatrix(this.afterMatrix, progress);
         this.currentMatrix = Matrix4f.add(m1, m2, (Matrix4f)null);
         Matrix4f composite = Matrix4f.mul(currentTransformMatrix, this.currentMatrix, (Matrix4f)null);
         MatrixHelper.loadMatrix(composite);
      }
   }
}
