package org.myrobotlab.service.meta;

import org.myrobotlab.framework.Platform;
import org.myrobotlab.framework.ServiceType;
import org.myrobotlab.logging.LoggerFactory;
import org.slf4j.Logger;

public class Pcf8574Meta {
  public final static Logger log = LoggerFactory.getLogger(Pcf8574Meta.class);
  
  /**
   * This static method returns all the details of the class without it having
   * to be constructed. It has description, categories, dependencies, and peer
   * definitions.
   * 
   * @return ServiceType - returns all the data
   * 
   */
  static public ServiceType getMetaData() {

    ServiceType meta = new ServiceType("org.myrobotlab.service.Pcf8574");
    Platform platform = Platform.getLocalInstance();
    meta.addDescription("Pcf8574 i2c 8 pin I/O extender");
    meta.addCategory("shield", "sensors");
    meta.setSponsor("Mats");
    return meta;
  }

  
}
