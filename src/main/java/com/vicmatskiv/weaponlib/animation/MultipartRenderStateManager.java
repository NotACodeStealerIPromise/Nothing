package com.vicmatskiv.weaponlib.animation;

import com.vicmatskiv.weaponlib.animation.MatrixHelper;
import com.vicmatskiv.weaponlib.animation.MultipartPositioning;
import com.vicmatskiv.weaponlib.animation.MultipartTransition;
import com.vicmatskiv.weaponlib.animation.MultipartTransitionProvider;
import com.vicmatskiv.weaponlib.animation.PartPositionProvider;
import com.vicmatskiv.weaponlib.animation.Randomizer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.function.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

public class MultipartRenderStateManager {
   private static final Logger logger = LogManager.getLogger(MultipartRenderStateManager.class);
   private Randomizer randomizer;
   private WeakHashMap lastApplied = new WeakHashMap();
   private Object currentState;
   private MultipartTransitionProvider transitionProvider;
   private Deque positioningQueue;

   public MultipartRenderStateManager(Object initialState, MultipartTransitionProvider transitionProvider, Object mainPart) {
      this.transitionProvider = transitionProvider;
      this.positioningQueue = new LinkedList();
      this.randomizer = new Randomizer();
      this.setState(initialState, false, true);
   }

   public void setState(Object newState, boolean animated, boolean immediate) {
      if(newState == null) {
         throw new IllegalArgumentException("State cannot be null");
      } else if(!newState.equals(this.currentState)) {
         if(immediate) {
            this.positioningQueue.clear();
         }

         if(animated) {
            this.positioningQueue.add(new MultipartRenderStateManager.TransitionedPositioning(this.currentState, newState));
         }

         this.positioningQueue.add(new MultipartRenderStateManager.StaticPositioning(newState));
         this.currentState = newState;
      }
   }

   public MultipartPositioning nextPositioning() {
      MultipartPositioning result = null;

      while(!this.positioningQueue.isEmpty()) {
         MultipartPositioning p = (MultipartPositioning)this.positioningQueue.poll();
         if(!p.isExpired(this.positioningQueue)) {
            this.positioningQueue.addFirst(p);
            result = p;
            break;
         }
      }

      if(result == null) {
         throw new IllegalStateException("Position cannot be null");
      } else {
         return result;
      }
   }

   private class TransitionedPositioning implements MultipartPositioning {
      private Map partDataMap = new HashMap();
      private Long startTime;
      private long totalDuration;
      private int currentIndex;
      private long currentStartTime;
      private boolean expired;
      private int segmentCount;
      private List fromPositioning;
      private List toPositioning;
      private Object fromState;
      private Object toState;

      TransitionedPositioning(Object fromState, Object toState) {
         this.fromState = fromState;
         this.toState = toState;
         this.fromPositioning = MultipartRenderStateManager.this.transitionProvider.getPositioning(fromState);
         this.toPositioning = MultipartRenderStateManager.this.transitionProvider.getPositioning(toState);
         this.segmentCount = this.toPositioning.size();

         MultipartTransition t;
         for(Iterator var4 = this.toPositioning.iterator(); var4.hasNext(); this.totalDuration += t.getDuration() + t.getPause()) {
            t = (MultipartTransition)var4.next();
         }

      }

      public float getProgress() {
         return this.startTime != null?(float)(System.currentTimeMillis() - this.startTime.longValue()) / (float)this.totalDuration:0.0F;
      }

      public boolean isExpired(Queue positioningQueue) {
         return this.expired;
      }

      public Object getFromState(Class stateClass) {
         return stateClass.cast(this.fromState);
      }

      public Object getToState(Class stateClass) {
         return stateClass.cast(this.toState);
      }

      private Matrix4f adjustToAttached(Matrix4f matrix, Object fromAttached, Object toAttached, PartPositionProvider context) {
         if(fromAttached == toAttached) {
            return matrix;
         } else {
            Matrix4f fromMatrix = context.getPartPosition(fromAttached);
            if(fromMatrix == null) {
               return matrix;
            } else {
               Matrix4f toMatrix = context.getPartPosition(toAttached);
               if(toMatrix == null) {
                  return matrix;
               } else {
                  Matrix4f invertedToMatrix = Matrix4f.invert(toMatrix, (Matrix4f)null);
                  if(invertedToMatrix == null) {
                     return matrix;
                  } else {
                     Matrix4f correctionMatrix = Matrix4f.mul(invertedToMatrix, fromMatrix, (Matrix4f)null);
                     return Matrix4f.mul(correctionMatrix, matrix, (Matrix4f)null);
                  }
               }
            }
         }
      }

      private MultipartRenderStateManager.TransitionedPositioning.PartData getPartData(Object part, PartPositionProvider context) {
         try {
            return (MultipartRenderStateManager.TransitionedPositioning.PartData)this.partDataMap.computeIfAbsent(part, (p) -> {
               MultipartRenderStateManager.TransitionedPositioning.PartData pd = new MultipartRenderStateManager.TransitionedPositioning.PartData();
               MultipartTransition fromMultipart = (MultipartTransition)this.fromPositioning.get(this.fromPositioning.size() - 1);
               Matrix4f fromMatrix;
               if(fromMultipart.getPositioning(part) == MultipartTransition.anchoredPosition()) {
                  fromMatrix = (Matrix4f)MultipartRenderStateManager.this.lastApplied.get(p);
                  if(fromMatrix == null) {
                     fromMatrix = new Matrix4f();
                     fromMatrix.setIdentity();
                  }
               } else {
                  fromMatrix = this.getMatrixForPositioning(fromMultipart, p, context);
               }

               fromMatrix = this.adjustToAttached(fromMatrix, fromMultipart.getAttachedTo(p), ((MultipartTransition)this.toPositioning.get(0)).getAttachedTo(p), context);
               pd.matrices.add(fromMatrix);
               pd.attachedTo = ((MultipartTransition)this.toPositioning.get(0)).getAttachedTo(p);
               Matrix4f previous = fromMatrix;

               Matrix4f current;
               for(Iterator var8 = this.toPositioning.iterator(); var8.hasNext(); previous = current) {
                  MultipartTransition t = (MultipartTransition)var8.next();
                  if(t.getPositioning(part) == MultipartTransition.anchoredPosition()) {
                     current = previous;
                  } else {
                     current = this.getMatrixForPositioning(t, p, context);
                  }

                  pd.matrices.add(current);
               }

               return pd;
            });
         } catch (Exception var4) {
            System.err.println("Failed to get data for part " + part + " for transition from [" + this.fromState + "] to [" + this.toState + "]");
            throw var4;
         }
      }

      public MultipartPositioning.Positioner getPositioner() {
         long currentTime = System.currentTimeMillis();
         MultipartTransition targetState = (MultipartTransition)this.toPositioning.get(this.currentIndex);
         long currentDuration = targetState.getDuration();
         long currentPause = targetState.getPause();
         if(this.currentIndex == 0 && this.startTime == null) {
            MultipartRenderStateManager.logger.debug("Starting transition {}, duration {}ms, pause {}ms", new Object[]{Integer.valueOf(this.currentIndex), Long.valueOf(currentDuration), Long.valueOf(currentPause)});
            this.startTime = Long.valueOf(currentTime);
         }

         if(this.currentStartTime == 0L) {
            this.currentStartTime = currentTime;
         } else if(currentTime > this.currentStartTime + currentDuration + currentPause) {
            MultipartRenderStateManager.logger.debug("Completed transition {}, duration {}ms, pause {}ms", new Object[]{Integer.valueOf(this.currentIndex), Long.valueOf(currentDuration), Long.valueOf(currentPause)});
            ++this.currentIndex;
            if(MultipartRenderStateManager.logger.isDebugEnabled() && this.currentIndex < this.toPositioning.size()) {
               MultipartTransition currentOffset = (MultipartTransition)this.toPositioning.get(this.currentIndex);
               MultipartRenderStateManager.logger.debug("Starting transition {}, duration {}ms, pause {}ms", new Object[]{Integer.valueOf(this.currentIndex), Long.valueOf(currentOffset.getDuration()), Long.valueOf(currentOffset.getPause())});
            }

            this.currentStartTime = currentTime;
         }

         long var12 = currentTime - this.currentStartTime;
         final float currentProgress = (float)var12 / (float)currentDuration;
         if(currentProgress > 1.0F) {
            currentProgress = 1.0F;
         }

         if(this.currentIndex >= this.segmentCount) {
            this.expired = true;
            return new MultipartPositioning.Positioner() {
               public void position(Object part, PartPositionProvider context) {
                  MultipartRenderStateManager.TransitionedPositioning.PartData partData = TransitionedPositioning.this.getPartData(part, context);
                  TransitionedPositioning.this.applyOnce(part, context, (Matrix4f)partData.matrices.get(TransitionedPositioning.this.currentIndex - 1), (Matrix4f)partData.matrices.get(TransitionedPositioning.this.currentIndex), partData.attachedTo, 1.0F);
               }

               public void randomize(float rate, float amplitude) {
                  MultipartRenderStateManager.this.randomizer.update(0.0F, 0.0F);
               }
            };
         } else {
            return new MultipartPositioning.Positioner() {
               public void position(Object part, PartPositionProvider context) {
                  MultipartRenderStateManager.TransitionedPositioning.PartData partData = TransitionedPositioning.this.getPartData(part, context);
                  TransitionedPositioning.this.applyOnce(part, context, (Matrix4f)partData.matrices.get(TransitionedPositioning.this.currentIndex), (Matrix4f)partData.matrices.get(TransitionedPositioning.this.currentIndex + 1), partData.attachedTo, currentProgress);
               }

               public void randomize(float rate, float amplitude) {
                  MultipartRenderStateManager.this.randomizer.update(0.0F, 0.0F);
               }
            };
         }
      }

      private void applyOnce(Object part, PartPositionProvider context, Matrix4f beforeMatrix, Matrix4f afterMatrix, Object attachedTo, float progress) {
         MultipartRenderStateManager.logger.trace("Applying position for part {}", new Object[]{part});
         Matrix4f currentMatrix = null;
         if(attachedTo != null) {
            currentMatrix = context.getPartPosition(attachedTo);
         }

         if(currentMatrix == null) {
            currentMatrix = MatrixHelper.captureMatrix();
         }

         Matrix4f m1 = MatrixHelper.interpolateMatrix(beforeMatrix, 1.0F - progress);
         Matrix4f m2 = MatrixHelper.interpolateMatrix(afterMatrix, progress);
         Matrix4f deltaMatrix = Matrix4f.add(m1, m2, (Matrix4f)null);
         MultipartRenderStateManager.this.lastApplied.put(part, deltaMatrix);
         Matrix4f composite = Matrix4f.mul(currentMatrix, deltaMatrix, (Matrix4f)null);
         MatrixHelper.loadMatrix(composite);
      }

      private Matrix4f getMatrixForPositioning(MultipartTransition transition, Object part, PartPositionProvider context) {
         GL11.glPushMatrix();
         GL11.glMatrixMode(5888);
         GL11.glLoadIdentity();
         FloatBuffer buf = BufferUtils.createFloatBuffer(16);
         transition.position(part, context);
         GL11.glGetFloat(2982, buf);
         buf.rewind();
         Matrix4f matrix = new Matrix4f();
         matrix.load(buf);
         GL11.glPopMatrix();
         return matrix;
      }

      private class PartData {
         List matrices;
         Object attachedTo;

         private PartData() {
            this.matrices = new ArrayList();
         }

         // $FF: synthetic method
         PartData(Object x1) {
            this();
         }
      }
   }

   private class StaticPositioning implements MultipartPositioning {
      private Object state;

      public StaticPositioning(Object state) {
         this.state = state;
      }

      public float getProgress() {
         return 1.0F;
      }

      public boolean isExpired(Queue positioningQueue) {
         return !positioningQueue.isEmpty();
      }

      public MultipartPositioning.Positioner getPositioner() {
         final List transitions = MultipartRenderStateManager.this.transitionProvider.getPositioning(this.state);
         return new MultipartPositioning.Positioner() {
            public void position(Object part, PartPositionProvider context) {
               try {
                  MultipartTransition e = (MultipartTransition)transitions.get(transitions.size() - 1);
                  Object attachedTo = e.getAttachedTo(part);
                  if(attachedTo != null) {
                     MatrixHelper.loadMatrix(context.getPartPosition(attachedTo));
                  }

                  if(e.getPositioning(part) == MultipartTransition.anchoredPosition()) {
                     Matrix4f m = (Matrix4f)MultipartRenderStateManager.this.lastApplied.get(part);
                     MatrixHelper.applyMatrix(m);
                  } else {
                     e.position(part, context);
                  }

               } catch (Exception var6) {
                  System.err.println("Failed to find static position for " + part + " in " + StaticPositioning.this.state);
                  throw var6;
               }
            }

            public void randomize(float rate, float amplitude) {
               MultipartRenderStateManager.this.randomizer.update(rate, amplitude);
            }
         };
      }

      public Object getFromState(Class stateClass) {
         return stateClass.cast(this.state);
      }

      public Object getToState(Class stateClass) {
         return stateClass.cast(this.state);
      }
   }
}
