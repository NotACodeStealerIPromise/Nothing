package com.vicmatskiv.weaponlib.compatibility;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

@SideOnly(Side.CLIENT)
public class CompatibleActiveRenderInfo {
   public static float objectX;
   public static float objectY;
   public static float objectZ;
   private static IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
   private static FloatBuffer modelview = GLAllocation.createDirectFloatBuffer(16);
   private static FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
   private static FloatBuffer objectCoords = GLAllocation.createDirectFloatBuffer(3);
   public static float rotationX;
   public static float rotationXZ;
   public static float rotationZ;
   public static float rotationYZ;
   public static float rotationXY;
   private static final String __OBFID = "CL_00000626";

   public static void updateRenderInfo(EntityLivingBase p_74583_0_, boolean p_74583_1_) {
      GL11.glGetFloat(2982, modelview);
      GL11.glGetFloat(2983, projection);
      GL11.glGetInteger(2978, viewport);
      float f = (float)((viewport.get(0) + viewport.get(2)) / 2);
      float f1 = (float)((viewport.get(1) + viewport.get(3)) / 2);
      GLU.gluUnProject(f, f1, 0.0F, modelview, projection, viewport, objectCoords);
      objectX = objectCoords.get(0);
      objectY = objectCoords.get(1);
      objectZ = objectCoords.get(2);
      int i = p_74583_1_?1:0;
      float f2 = p_74583_0_.rotationPitch;
      float f3 = p_74583_0_.rotationYaw;
      rotationX = MathHelper.cos(f3 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      rotationZ = MathHelper.sin(f3 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      rotationYZ = -rotationZ * MathHelper.sin(f2 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      rotationXY = rotationX * MathHelper.sin(f2 * 3.1415927F / 180.0F) * (float)(1 - i * 2);
      rotationXZ = MathHelper.cos(f2 * 3.1415927F / 180.0F);
   }

   public static Vec3 projectViewFromEntity(EntityLivingBase p_74585_0_, double p_74585_1_) {
      double d1 = p_74585_0_.prevPosX + (p_74585_0_.posX - p_74585_0_.prevPosX) * p_74585_1_;
      double d2 = p_74585_0_.prevPosY + (p_74585_0_.posY - p_74585_0_.prevPosY) * p_74585_1_ + (double)p_74585_0_.getEyeHeight();
      double d3 = p_74585_0_.prevPosZ + (p_74585_0_.posZ - p_74585_0_.prevPosZ) * p_74585_1_;
      double d4 = d1 + (double)(objectX * 1.0F);
      double d5 = d2 + (double)(objectY * 1.0F);
      double d6 = d3 + (double)(objectZ * 1.0F);
      return Vec3.createVectorHelper(d4, d5, d6);
   }

   public static Block getBlockAtEntityViewpoint(World p_151460_0_, EntityLivingBase p_151460_1_, float p_151460_2_) {
      Vec3 vec3 = projectViewFromEntity(p_151460_1_, (double)p_151460_2_);
      ChunkPosition chunkposition = new ChunkPosition(vec3);
      Block block = p_151460_0_.getBlock(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ);
      if(block.getMaterial().isLiquid()) {
         float f1 = BlockLiquid.getLiquidHeightPercent(p_151460_0_.getBlockMetadata(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ)) - 0.11111111F;
         float f2 = (float)(chunkposition.chunkPosY + 1) - f1;
         if(vec3.yCoord >= (double)f2) {
            block = p_151460_0_.getBlock(chunkposition.chunkPosX, chunkposition.chunkPosY + 1, chunkposition.chunkPosZ);
         }
      }

      return block;
   }
}
