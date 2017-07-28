package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

public class FlatSurfaceModelBox extends ModelBox {
   private PositionTextureVertex[] vertexPositions = new PositionTextureVertex[8];
   private FlatSurfaceModelBox.TexturedQuad quad;

   public FlatSurfaceModelBox(ModelRenderer renderer, int par2, int par3, float posX1, float posY1, float posZ1, int sizeX, int sizeY, int sizeZ, float p_i1171_10_) {
      super(renderer, par2, par3, posX1, posY1, posZ1, sizeX, sizeY, sizeZ, p_i1171_10_);
      float adjX2 = posX1 + (float)sizeX;
      float adjY2 = posY1 + (float)sizeY;
      float adjZ2 = posZ1 + (float)sizeZ;
      posX1 -= p_i1171_10_;
      posY1 -= p_i1171_10_;
      float var10000 = posZ1 - p_i1171_10_;
      adjX2 += p_i1171_10_;
      adjY2 += p_i1171_10_;
      adjZ2 += p_i1171_10_;
      if(renderer.mirror) {
         float backLowerLeft = adjX2;
         adjX2 = posX1;
         posX1 = backLowerLeft;
      }

      PositionTextureVertex backLowerLeft1 = new PositionTextureVertex(posX1, posY1, adjZ2, 0.0F, 0.0F);
      PositionTextureVertex backLowerRight = new PositionTextureVertex(adjX2, posY1, adjZ2, 0.0F, 8.0F);
      PositionTextureVertex backUpperRight = new PositionTextureVertex(adjX2, adjY2, adjZ2, 8.0F, 8.0F);
      PositionTextureVertex backUpperLeft = new PositionTextureVertex(posX1, adjY2, adjZ2, 8.0F, 0.0F);
      this.vertexPositions[4] = backLowerLeft1;
      this.vertexPositions[5] = backLowerRight;
      this.vertexPositions[6] = backUpperRight;
      this.vertexPositions[7] = backUpperLeft;
      this.quad = new FlatSurfaceModelBox.TexturedQuad(new PositionTextureVertex[]{backUpperRight, backUpperLeft, backLowerLeft1, backLowerRight}, par2 + sizeZ + sizeX + sizeZ, par3 + sizeZ, par2 + sizeZ + sizeX + sizeZ + sizeX, par3 + sizeZ + sizeY, renderer.textureWidth, renderer.textureHeight);
      if(renderer.mirror) {
         this.quad.flipFace();
      }

   }

   @SideOnly(Side.CLIENT)
   public void render(Tessellator tessellator, float scale) {
      this.quad.draw(tessellator, scale);
   }

   public ModelBox func_78244_a(String p_78244_1_) {
      this.field_78247_g = p_78244_1_;
      return this;
   }

   private static class TexturedQuad {
      private PositionTextureVertex[] vertexPositions;
      private boolean invertNormal;

      public TexturedQuad(PositionTextureVertex[] vertices) {
         this.vertexPositions = vertices;
      }

      public TexturedQuad(PositionTextureVertex[] vertices, int texcoordU1, int texcoordV1, int texcoordU2, int texcoordV2, float textureWidth, float textureHeight) {
         this(vertices);
         vertices[0] = vertices[0].setTexturePosition(0.0F, 0.0F);
         vertices[1] = vertices[1].setTexturePosition(1.0F, 0.0F);
         vertices[2] = vertices[2].setTexturePosition(1.0F, 1.0F);
         vertices[3] = vertices[3].setTexturePosition(0.0F, 1.0F);
      }

      public void flipFace() {
         PositionTextureVertex[] apositiontexturevertex = new PositionTextureVertex[this.vertexPositions.length];

         for(int i = 0; i < this.vertexPositions.length; ++i) {
            apositiontexturevertex[i] = this.vertexPositions[this.vertexPositions.length - i - 1];
         }

         this.vertexPositions = apositiontexturevertex;
      }

      public void draw(Tessellator tessellator, float scale) {
         Vec3 vec3 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[0].vector3D);
         Vec3 vec31 = this.vertexPositions[1].vector3D.subtract(this.vertexPositions[2].vector3D);
         Vec3 vec32 = vec31.crossProduct(vec3).normalize();
         tessellator.startDrawingQuads();
         if(this.invertNormal) {
            tessellator.setNormal(-((float)vec32.xCoord), -((float)vec32.yCoord), -((float)vec32.zCoord));
         } else {
            tessellator.setNormal((float)vec32.xCoord, (float)vec32.yCoord, (float)vec32.zCoord);
         }

         for(int i = 0; i < 4; ++i) {
            PositionTextureVertex positiontexturevertex = this.vertexPositions[i];
            tessellator.addVertexWithUV((double)((float)positiontexturevertex.vector3D.xCoord * scale), (double)((float)positiontexturevertex.vector3D.yCoord * scale), (double)((float)positiontexturevertex.vector3D.zCoord * scale), (double)positiontexturevertex.texturePositionX, (double)positiontexturevertex.texturePositionY);
         }

         tessellator.draw();
      }
   }
}
