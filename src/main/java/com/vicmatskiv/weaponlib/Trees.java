package com.vicmatskiv.weaponlib;

import io.netty.buffer.ByteBuf;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class Trees {
   public static void writeBuf(ByteBuf buf, Object root, BiConsumer writeContent, Function getChildren) {
      LinkedList queue = new LinkedList();
      Trees.Entry SENTINEL = new Trees.Entry();
      SENTINEL.name = "Sentinel";
      Trees.Entry rootEntry = new Trees.Entry();
      rootEntry.content = root;
      rootEntry.parentIndex = 0;
      queue.add(rootEntry);
      queue.add(SENTINEL);
      int counter = 0;

      while(true) {
         while(!queue.isEmpty()) {
            Trees.Entry e = (Trees.Entry)queue.pollFirst();
            if(e == SENTINEL) {
               counter = 0;
               buf.writeByte(-1);
            } else {
               buf.writeInt(e.parentIndex);
               writeContent.accept(buf, e.content);
               List children = (List)getChildren.apply(e.content);
               if(!children.isEmpty()) {
                  Iterator var10 = children.iterator();

                  while(var10.hasNext()) {
                     Object n = var10.next();
                     Trees.Entry c = new Trees.Entry();
                     c.content = n;
                     c.index = counter++;
                     c.parentIndex = e.index;
                     queue.addLast(c);
                  }

                  queue.addLast(SENTINEL);
               }
            }
         }

         buf.writeByte(-1);
         return;
      }
   }

   public static Object readBuf(ByteBuf buf, Function reader, BiConsumer attacher) {
      ArrayList currentRow = new ArrayList();
      ArrayList previousRow = null;
      Object root = null;

      while(true) {
         while(true) {
            buf.markReaderIndex();
            if(buf.readByte() == -1) {
               if(currentRow.isEmpty()) {
                  return root;
               }

               previousRow = currentRow;
               currentRow = new ArrayList();
            } else {
               buf.resetReaderIndex();
               int parentIndex = buf.readInt();
               Object thisNode = reader.apply(buf);
               if(previousRow != null) {
                  Object parentNode = previousRow.get(parentIndex);
                  attacher.accept(parentNode, thisNode);
               } else {
                  root = thisNode;
               }

               currentRow.add(thisNode);
            }
         }
      }
   }

   private static class Entry {
      Object content;
      int index;
      int parentIndex;
      private String name;

      private Entry() {
      }

      public String toString() {
         return this.name != null?this.name:this.content.toString();
      }

      // $FF: synthetic method
      Entry(Object x0) {
         this();
      }
   }
}
