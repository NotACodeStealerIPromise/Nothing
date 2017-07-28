package com.vicmatskiv.weaponlib.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Explosions"
)
public class Explosions {
   @XmlAttribute(
      name = "damage"
   )
   protected Float damage;
   @XmlAttribute(
      name = "dropBlockChance"
   )
   protected Float dropBlockChance;

   public Float getDamage() {
      return this.damage;
   }

   public void setDamage(Float value) {
      this.damage = value;
   }

   public Float getDropBlockChance() {
      return this.dropBlockChance;
   }

   public void setDropBlockChance(Float value) {
      this.dropBlockChance = value;
   }
}
