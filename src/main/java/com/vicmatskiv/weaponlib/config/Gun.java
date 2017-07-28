package com.vicmatskiv.weaponlib.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(
   name = "Gun"
)
public class Gun {
   @XmlAttribute(
      name = "id"
   )
   protected String id;
   @XmlAttribute(
      name = "enabled"
   )
   protected Boolean enabled;

   public String getId() {
      return this.id;
   }

   public void setId(String value) {
      this.id = value;
   }

   public boolean isEnabled() {
      return this.enabled == null?true:this.enabled.booleanValue();
   }

   public void setEnabled(Boolean value) {
      this.enabled = value;
   }
}
