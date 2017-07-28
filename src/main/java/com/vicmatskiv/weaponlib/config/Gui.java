package com.vicmatskiv.weaponlib.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Gui"
)
public class Gui {
   @XmlAttribute(
      name = "statusBarPosition",
      required = true
   )
   protected String statusBarPosition;

   public String getStatusBarPosition() {
      return this.statusBarPosition;
   }

   public void setStatusBarPosition(String value) {
      this.statusBarPosition = value;
   }
}
