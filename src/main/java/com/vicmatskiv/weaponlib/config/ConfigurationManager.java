package com.vicmatskiv.weaponlib.config;

import com.vicmatskiv.weaponlib.config.Configuration;
import com.vicmatskiv.weaponlib.config.Explosions;
import com.vicmatskiv.weaponlib.config.Gui;
import com.vicmatskiv.weaponlib.config.Gun;
import com.vicmatskiv.weaponlib.config.ObjectFactory;
import com.vicmatskiv.weaponlib.config.Ore;
import com.vicmatskiv.weaponlib.config.Ores;
import com.vicmatskiv.weaponlib.config.Projectiles;
import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigurationManager {
   private static final Logger logger = LogManager.getLogger(ConfigurationManager.class);
   private static final float DROP_BLOCK_COEFFICIENT_MIN = 0.0F;
   private static final float DROP_BLOCK_COEFFICIENT_MAX = 5.0F;
   private static final float EXPLOSION_COEFFICIENT_MAX = 2.0F;
   private static final float EXPLOSION_COEFFICIENT_MIN = 0.2F;
   private static final float BLEEDING_ON_HIT_COEFFICIENT_MIN = 0.0F;
   private static final float BLEEDING_ON_HIT_COEFFICIENT_MAX = 1.0F;
   private static final int ORE_MIN_PER_CHUNK = 0;
   private static final int ORE_MAX_PER_CHUNK = 50;
   private static final Predicate DEFAULT_ORE_VALIDATOR = (ore) -> {
      return ore.spawnsPerChunk.intValue() >= 0 && ore.spawnsPerChunk.intValue() <= 50;
   };
   private static final Predicate DEFAULT_EXPLOSIONS_VALIDATOR = (explosions) -> {
      return explosions != null;
   };
   private Configuration config;
   private File userConfigFile;
   private ConfigurationManager.Builder builder;

   protected ConfigurationManager(Configuration config, File userConfigFile, ConfigurationManager.Builder builder) {
      this.config = config;
      this.userConfigFile = userConfigFile;
      this.builder = builder;
   }

   protected Configuration getConfiguration() {
      return this.config;
   }

   public void save() {
      if(this.userConfigFile.exists() && this.builder.userConfig == null) {
         String e = ".invalid." + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
         File marshaller = new File(this.userConfigFile.toString() + e);

         try {
            Files.move(this.userConfigFile.toPath(), marshaller.toPath(), new CopyOption[0]);
         } catch (IOException var5) {
            logger.error("Failed to rename {} to {}", new Object[]{this.userConfigFile, marshaller});
         }
      }

      try {
         JAXBContext e1 = JAXBContext.newInstance(new Class[]{ObjectFactory.class});
         Marshaller marshaller1 = e1.createMarshaller();
         marshaller1.setProperty("jaxb.formatted.output", Boolean.valueOf(true));
         if(this.userConfigFile != null) {
            marshaller1.marshal((new ObjectFactory()).createConfiguration(this.config), this.userConfigFile);
         } else {
            marshaller1.marshal((new ObjectFactory()).createConfiguration(this.config), System.out);
         }
      } catch (JAXBException var4) {
         logger.error("Failed to save configuration to {}", new Object[]{this.userConfigFile});
      }

   }

   public Explosions getExplosions() {
      return this.config.getExplosions();
   }

   public Ore getOre(String oreName) {
      Ores ores = this.config.getOres();
      return ores == null?null:(Ore)ores.getOre().stream().filter((o) -> {
         return oreName.equalsIgnoreCase(o.getName());
      }).findFirst().orElse((Object)null);
   }

   public Projectiles getProjectiles() {
      return this.config.getProjectiles();
   }

   public ConfigurationManager.StatusBarPosition getStatusBarPosition() {
      return this.builder.statusBarPosition;
   }

   public Gun getGun(String id) {
      return (Gun)this.builder.guns.get(id);
   }

   public static final class Builder {
      private Map guns = new LinkedHashMap();
      private Source defaultConfigSource;
      private Source userConfigSource;
      private File userConfigFile;
      private ConfigurationManager.StatusBarPosition statusBarPosition;
      private Predicate oreValidator;
      private Predicate explosionsValidator;
      private Configuration userConfig;

      public Builder() {
         this.statusBarPosition = ConfigurationManager.StatusBarPosition.TOP_RIGHT;
         this.oreValidator = ConfigurationManager.DEFAULT_ORE_VALIDATOR;
         this.explosionsValidator = ConfigurationManager.DEFAULT_EXPLOSIONS_VALIDATOR;
      }

      public ConfigurationManager.Builder withDefaultConfiguration(Source defaultConfigSource) {
         this.defaultConfigSource = defaultConfigSource;
         return this;
      }

      ConfigurationManager.Builder withUserConfiguration(Source userConfigSource) {
         this.userConfigSource = userConfigSource;
         return this;
      }

      public ConfigurationManager.Builder withUserConfiguration(File userConfigFile) {
         this.userConfigFile = userConfigFile;
         this.userConfigSource = new StreamSource(userConfigFile);
         return this;
      }

      public ConfigurationManager.Builder withOreValidator(Predicate oreValidator) {
         this.oreValidator = oreValidator;
         return this;
      }

      public ConfigurationManager.Builder withExplosionsValidator(Predicate explosionsValidator) {
         this.explosionsValidator = explosionsValidator;
         return this;
      }

      public ConfigurationManager build() {
         Configuration defaultUpdatableConfig = createConfiguration(this.defaultConfigSource);
         this.userConfig = createConfiguration(this.userConfigSource);
         return new ConfigurationManager(this.merge(this.userConfig, defaultUpdatableConfig), this.userConfigFile, this);
      }

      static Configuration createConfiguration(Source source) {
         try {
            JAXBContext e = JAXBContext.newInstance(new Class[]{ObjectFactory.class});
            Unmarshaller jaxbUnmarshaller = e.createUnmarshaller();
            JAXBElement configElement = jaxbUnmarshaller.unmarshal(source, Configuration.class);
            return (Configuration)configElement.getValue();
         } catch (JAXBException var4) {
            ConfigurationManager.logger.error("Failed to parse configuration: " + var4, var4);
            return null;
         }
      }

      private Configuration merge(Configuration userConfig, Configuration defaultUpdatableConfig) {
         if(userConfig == null) {
            return defaultUpdatableConfig;
         } else {
            this.mergeOres(userConfig, defaultUpdatableConfig);
            this.mergeExplosions(userConfig, defaultUpdatableConfig);
            this.mergeProjectiles(userConfig, defaultUpdatableConfig);
            this.mergeGui(userConfig, defaultUpdatableConfig);
            return defaultUpdatableConfig;
         }
      }

      private void mergeExplosions(Configuration userConfig, Configuration defaultUpdatableConfig) {
         if(this.explosionsValidator.test(userConfig.getExplosions())) {
            Float userDamageCoefficient = userConfig.getExplosions().getDamage();
            if(userDamageCoefficient == null) {
               userDamageCoefficient = defaultUpdatableConfig.getExplosions().getDamage();
            } else if(userDamageCoefficient.floatValue() < 0.2F) {
               userDamageCoefficient = Float.valueOf(0.2F);
            } else if(userDamageCoefficient.floatValue() > 2.0F) {
               userDamageCoefficient = Float.valueOf(2.0F);
            }

            defaultUpdatableConfig.getExplosions().setDamage(userDamageCoefficient);
            Float userDropBlockChance = userConfig.getExplosions().getDropBlockChance();
            if(userDropBlockChance == null) {
               userDropBlockChance = defaultUpdatableConfig.getExplosions().getDropBlockChance();
            } else if(userDropBlockChance.floatValue() < 0.0F) {
               userDropBlockChance = Float.valueOf(0.0F);
            } else if(userDropBlockChance.floatValue() > 5.0F) {
               userDropBlockChance = Float.valueOf(5.0F);
            }

            defaultUpdatableConfig.getExplosions().setDropBlockChance(userDropBlockChance);
         }

      }

      private void mergeProjectiles(Configuration userConfig, Configuration defaultUpdatableConfig) {
         if(defaultUpdatableConfig.getProjectiles() != null) {
            defaultUpdatableConfig.getProjectiles().getGun().stream().filter((gun) -> {
               return gun.getId() != null;
            }).forEach((gun) -> {
               this.guns.put(gun.getId(), gun);
            });
         }

         if(userConfig != null && userConfig.getProjectiles() != null) {
            Float bleedingOnHit = userConfig.getProjectiles().getBleedingOnHit();
            if(bleedingOnHit != null) {
               if(bleedingOnHit.floatValue() < 0.0F) {
                  bleedingOnHit = Float.valueOf(0.0F);
               } else if(bleedingOnHit.floatValue() > 1.0F) {
                  bleedingOnHit = Float.valueOf(1.0F);
               }

               defaultUpdatableConfig.getProjectiles().setBleedingOnHit(bleedingOnHit);
            }

            if(userConfig.getProjectiles().isDestroyGlassBlocks() != null) {
               defaultUpdatableConfig.getProjectiles().setDestroyGlassBlocks(userConfig.getProjectiles().isDestroyGlassBlocks());
            }

            if(userConfig.getProjectiles().isKnockbackOnHit() != null) {
               defaultUpdatableConfig.getProjectiles().setKnockbackOnHit(userConfig.getProjectiles().isKnockbackOnHit());
            }

            if(userConfig.getProjectiles().isMuzzleEffects() != null) {
               defaultUpdatableConfig.getProjectiles().setMuzzleEffects(userConfig.getProjectiles().isMuzzleEffects());
            }

            Iterator var4 = userConfig.getProjectiles().getGun().iterator();

            while(var4.hasNext()) {
               Gun gun = (Gun)var4.next();
               if(gun.getId() != null) {
                  this.guns.put(gun.getId(), gun);
               }
            }
         }

         if(defaultUpdatableConfig.getProjectiles() != null) {
            defaultUpdatableConfig.getProjectiles().getGun().clear();
            defaultUpdatableConfig.getProjectiles().getGun().addAll(this.guns.values());
         }

      }

      private void mergeOres(Configuration userConfiguration, Configuration updatableDefaults) {
         if(userConfiguration.getOres() != null) {
            updatableDefaults.getOres().getOre().forEach((updatableDefaultOre) -> {
               userConfiguration.getOres().getOre().stream().filter((o) -> {
                  return updatableDefaultOre.getName().equalsIgnoreCase(o.getName());
               }).findFirst().ifPresent((userOre) -> {
                  this.mergeOre(userOre, updatableDefaultOre);
               });
            });
         }

      }

      private void mergeOre(Ore userOre, Ore updatableDefaultOre) {
         if(this.oreValidator.test(userOre)) {
            updatableDefaultOre.spawnsPerChunk = userOre.spawnsPerChunk;
         }

      }

      private void mergeGui(Configuration userConfig, Configuration defaultUpdatableConfig) {
         if(userConfig.getGui() != null && userConfig.getGui().getStatusBarPosition() != null) {
            try {
               String positionValue = userConfig.getGui().getStatusBarPosition().toUpperCase().replaceAll("[\\.\\-\\s]+", "_");
               this.statusBarPosition = ConfigurationManager.StatusBarPosition.valueOf(positionValue);
            } catch (IllegalArgumentException var4) {
               ;
            }
         }

         if(this.statusBarPosition == null) {
            this.statusBarPosition = ConfigurationManager.StatusBarPosition.TOP_RIGHT;
         }

         if(defaultUpdatableConfig.getGui() == null) {
            defaultUpdatableConfig.setGui(new Gui());
         }

         defaultUpdatableConfig.getGui().setStatusBarPosition(this.statusBarPosition.toString());
      }
   }

   public static enum StatusBarPosition {
      TOP_RIGHT,
      BOTTOM_RIGHT,
      TOP_LEFT,
      BOTTOM_LEFT;
   }
}
