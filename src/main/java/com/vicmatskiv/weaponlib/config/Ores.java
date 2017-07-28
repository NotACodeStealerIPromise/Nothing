package com.vicmatskiv.weaponlib.config;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Ores",
   propOrder = {"ore"}
)
public class Ores {
   protected List ore;

   public List getOre() {
      if(this.ore == null) {
         this.ore = new ArrayList();
      }

      return this.ore;
   }
}
