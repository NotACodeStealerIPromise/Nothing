package com.vicmatskiv.weaponlib.compatibility;

import com.vicmatskiv.weaponlib.compatibility.CompatibleBlockState;
import com.vicmatskiv.weaponlib.compatibility.CompatibleRayTraceResult;
import com.vicmatskiv.weaponlib.compatibility.CompatibleVec3;
import java.util.function.BiPredicate;
import net.minecraft.block.Block;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CompatibleRayTracing {
   public static CompatibleRayTraceResult rayTraceBlocks(World world, CompatibleVec3 cvec1, CompatibleVec3 cvec2, BiPredicate isCollidable) {
      Vec3 vec1 = cvec1.getVec();
      Vec3 vec2 = cvec2.getVec();
      boolean ignoreBlockWithoutBoundingBox = false;
      boolean returnLastUncollidableBlock = false;
      if(!Double.isNaN(vec1.xCoord) && !Double.isNaN(vec1.yCoord) && !Double.isNaN(vec1.zCoord)) {
         if(!Double.isNaN(vec2.xCoord) && !Double.isNaN(vec2.yCoord) && !Double.isNaN(vec2.zCoord)) {
            int i = MathHelper.floor_double(vec2.xCoord);
            int j = MathHelper.floor_double(vec2.yCoord);
            int k = MathHelper.floor_double(vec2.zCoord);
            int l = MathHelper.floor_double(vec1.xCoord);
            int i1 = MathHelper.floor_double(vec1.yCoord);
            int j1 = MathHelper.floor_double(vec1.zCoord);
            Block block = world.getBlock(l, i1, j1);
            int blockMetadata = world.getBlockMetadata(l, i1, j1);
            MovingObjectPosition movingobjectposition2;
            if((!ignoreBlockWithoutBoundingBox || block.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null) && isCollidable.test(block, new CompatibleBlockState(block, blockMetadata))) {
               movingobjectposition2 = block.collisionRayTrace(world, l, i1, j1, vec1, vec2);
               if(movingobjectposition2 != null) {
                  return CompatibleRayTraceResult.fromMovingObjectPosition(movingobjectposition2);
               }
            }

            movingobjectposition2 = null;
            blockMetadata = 200;

            while(blockMetadata-- >= 0) {
               if(Double.isNaN(vec1.xCoord) || Double.isNaN(vec1.yCoord) || Double.isNaN(vec1.zCoord)) {
                  return null;
               }

               if(l == i && i1 == j && j1 == k) {
                  return returnLastUncollidableBlock?CompatibleRayTraceResult.fromMovingObjectPosition(movingobjectposition2):null;
               }

               boolean flag6 = true;
               boolean flag3 = true;
               boolean flag4 = true;
               double d0 = 999.0D;
               double d1 = 999.0D;
               double d2 = 999.0D;
               if(i > l) {
                  d0 = (double)l + 1.0D;
               } else if(i < l) {
                  d0 = (double)l + 0.0D;
               } else {
                  flag6 = false;
               }

               if(j > i1) {
                  d1 = (double)i1 + 1.0D;
               } else if(j < i1) {
                  d1 = (double)i1 + 0.0D;
               } else {
                  flag3 = false;
               }

               if(k > j1) {
                  d2 = (double)j1 + 1.0D;
               } else if(k < j1) {
                  d2 = (double)j1 + 0.0D;
               } else {
                  flag4 = false;
               }

               double d3 = 999.0D;
               double d4 = 999.0D;
               double d5 = 999.0D;
               double d6 = vec2.xCoord - vec1.xCoord;
               double d7 = vec2.yCoord - vec1.yCoord;
               double d8 = vec2.zCoord - vec1.zCoord;
               if(flag6) {
                  d3 = (d0 - vec1.xCoord) / d6;
               }

               if(flag3) {
                  d4 = (d1 - vec1.yCoord) / d7;
               }

               if(flag4) {
                  d5 = (d2 - vec1.zCoord) / d8;
               }

               byte b0;
               if(d3 < d4 && d3 < d5) {
                  if(i > l) {
                     b0 = 4;
                  } else {
                     b0 = 5;
                  }

                  vec1.xCoord = d0;
                  vec1.yCoord += d7 * d3;
                  vec1.zCoord += d8 * d3;
               } else if(d4 < d5) {
                  if(j > i1) {
                     b0 = 0;
                  } else {
                     b0 = 1;
                  }

                  vec1.xCoord += d6 * d4;
                  vec1.yCoord = d1;
                  vec1.zCoord += d8 * d4;
               } else {
                  if(k > j1) {
                     b0 = 2;
                  } else {
                     b0 = 3;
                  }

                  vec1.xCoord += d6 * d5;
                  vec1.yCoord += d7 * d5;
                  vec1.zCoord = d2;
               }

               Vec3 vec32 = Vec3.createVectorHelper(vec1.xCoord, vec1.yCoord, vec1.zCoord);
               l = (int)(vec32.xCoord = (double)MathHelper.floor_double(vec1.xCoord));
               if(b0 == 5) {
                  --l;
                  ++vec32.xCoord;
               }

               i1 = (int)(vec32.yCoord = (double)MathHelper.floor_double(vec1.yCoord));
               if(b0 == 1) {
                  --i1;
                  ++vec32.yCoord;
               }

               j1 = (int)(vec32.zCoord = (double)MathHelper.floor_double(vec1.zCoord));
               if(b0 == 3) {
                  --j1;
                  ++vec32.zCoord;
               }

               Block block1 = world.getBlock(l, i1, j1);
               int block1Metadata = world.getBlockMetadata(l, i1, j1);
               if(!ignoreBlockWithoutBoundingBox || block1.getCollisionBoundingBoxFromPool(world, l, i1, j1) != null) {
                  if(isCollidable.test(block1, new CompatibleBlockState(block1, block1Metadata))) {
                     MovingObjectPosition movingobjectposition1 = block1.collisionRayTrace(world, l, i1, j1, vec1, vec2);
                     if(movingobjectposition1 != null) {
                        return CompatibleRayTraceResult.fromMovingObjectPosition(movingobjectposition1);
                     }
                  } else {
                     movingobjectposition2 = new MovingObjectPosition(l, i1, j1, b0, vec1, false);
                  }
               }
            }

            return returnLastUncollidableBlock?CompatibleRayTraceResult.fromMovingObjectPosition(movingobjectposition2):null;
         } else {
            return null;
         }
      } else {
         return null;
      }
   }
}
