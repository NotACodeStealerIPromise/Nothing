package com.vicmatskiv.weaponlib.network;

import com.vicmatskiv.weaponlib.network.UniversallySerializable;
import io.netty.buffer.ByteBuf;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class TypeRegistry {
   private static final String SHA1PRNG_ALG = "SHA1PRNG";
   private ConcurrentMap typeRegistry = new ConcurrentHashMap();
   private static final TypeRegistry instance = new TypeRegistry();

   public static TypeRegistry getInstance() {
      return instance;
   }

   public void register(Class cls) {
      this.typeRegistry.put(this.createUuid(cls), cls);
   }

   protected UUID createUuid(Class cls) {
      try {
         SecureRandom e = SecureRandom.getInstance("SHA1PRNG");
         e.setSeed(cls.getName().getBytes());
         return new UUID(e.nextLong(), e.nextLong());
      } catch (NoSuchAlgorithmException var3) {
         var3.printStackTrace();
         return UUID.fromString(this.getClass().getName());
      }
   }

   public void toBytes(UniversallySerializable object, ByteBuf buf) {
      UUID typeUuid = this.createUuid(object.getClass());
      if(!this.typeRegistry.containsKey(typeUuid)) {
         throw new RuntimeException("Failed to serialize object " + object + " because its class is not registered: " + object.getClass());
      } else {
         buf.writeLong(typeUuid.getMostSignificantBits());
         buf.writeLong(typeUuid.getLeastSignificantBits());
         if(object.getClass().isEnum()) {
            buf.writeInt(((Enum)object).ordinal());
         } else {
            object.serialize(buf);
         }

      }
   }

   public UniversallySerializable fromBytes(ByteBuf buf) {
      long mostSigBits = buf.readLong();
      long leastSigBits = buf.readLong();
      UUID typeUuid = new UUID(mostSigBits, leastSigBits);
      Class targetClass = (Class)this.typeRegistry.get(typeUuid);
      if(targetClass == null) {
         throw new RuntimeException("Failed to deserailize object. Did you forget to register type?");
      } else {
         UniversallySerializable instance;
         if(targetClass.isEnum()) {
            UniversallySerializable[] e = (UniversallySerializable[])targetClass.getEnumConstants();
            instance = e[buf.readInt()];
         } else {
            try {
               instance = (UniversallySerializable)targetClass.newInstance();
            } catch (IllegalAccessException | InstantiationException var10) {
               var10.printStackTrace();
               throw new RuntimeException("Cannot create instance of  " + targetClass);
            }

            instance.init(buf);
         }

         return (UniversallySerializable)targetClass.cast(instance);
      }
   }
}
