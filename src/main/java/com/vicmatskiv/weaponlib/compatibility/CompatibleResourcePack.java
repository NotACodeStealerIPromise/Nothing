package com.vicmatskiv.weaponlib.compatibility;

import java.io.IOException;
import java.util.Set;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;

public abstract class CompatibleResourcePack implements IResourcePack {
   public final Set getResourceDomains() {
      return this.getCompatibleResourceDomains();
   }

   protected abstract Set getCompatibleResourceDomains();

   public IMetadataSection getPackMetadata(IMetadataSerializer p_135058_1_, String p_135058_2_) throws IOException {
      return null;
   }
}
