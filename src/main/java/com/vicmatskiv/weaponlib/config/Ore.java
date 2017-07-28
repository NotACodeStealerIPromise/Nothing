package com.vicmatskiv.weaponlib.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Ore"
)
public class Ore {
   @XmlAttribute(
      name = "name"
   )
   protected String name;
   @XmlAttribute(
      name = "spawnsPerChunk"
   )
   protected Integer spawnsPerChunk;

   public String getName() {
      return this.name;
   }

   public void setName(String value) {
      this.name = value;
   }

   public Integer getSpawnsPerChunk() {
      return this.spawnsPerChunk;
   }

   public void setSpawnsPerChunk(Integer value) {
      this.spawnsPerChunk = value;
   }
}
