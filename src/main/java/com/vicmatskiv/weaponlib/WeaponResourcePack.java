package com.vicmatskiv.weaponlib;

import com.vicmatskiv.weaponlib.compatibility.CompatibleResourcePack;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class WeaponResourcePack extends CompatibleResourcePack {
   private static final String WEAPONLIB_RESOURCE_DOMAIN = "weaponlib";
   private static final Set RESOURCE_DOMAINS = Collections.unmodifiableSet(new HashSet(Collections.singleton("weaponlib")));

   public InputStream getInputStream(ResourceLocation resourceLocation) throws IOException {
      String resourcePath = this.modifyResourcePath(resourceLocation);
      return this.getClass().getResourceAsStream(resourcePath);
   }

   private String modifyResourcePath(ResourceLocation resourceLocation) {
      String resourcePath = resourceLocation.getResourcePath();
      if(resourcePath.startsWith("textures")) {
         int lastIndexOfSlash = resourcePath.lastIndexOf(47);
         if(lastIndexOfSlash >= 0) {
            String fileName = resourcePath.substring(lastIndexOfSlash + 1);
            resourcePath = '/' + this.getClass().getPackage().getName().replace('.', '/') + "/resources/" + fileName;
         }
      }

      return resourcePath;
   }

   public boolean resourceExists(ResourceLocation resourceLocation) {
      String resourcePath = this.modifyResourcePath(resourceLocation);
      boolean value = "weaponlib".equals(resourceLocation.getResourceDomain()) && this.getClass().getResource(resourcePath) != null;
      return value;
   }

   public Set getCompatibleResourceDomains() {
      return RESOURCE_DOMAINS;
   }

   public BufferedImage getPackImage() throws IOException {
      return null;
   }

   public String getPackName() {
      return this.getClass().getSimpleName();
   }
}
