package org.myrobotlab.service.meta;

import org.myrobotlab.framework.Platform;
import org.myrobotlab.framework.ServiceType;
import org.myrobotlab.logging.LoggerFactory;
import org.slf4j.Logger;

public class GoogleCloudMeta {
  public final static Logger log = LoggerFactory.getLogger(GoogleCloudMeta.class);
  
  /**
   * This static method returns all the details of the class without it having
   * to be constructed. It has description, categories, dependencies, and peer
   * definitions.
   * 
   * @return ServiceType - returns all the data
   * 
   */
  static public ServiceType getMetaData() {

    ServiceType meta = new ServiceType("org.myrobotlab.service.GoogleCloud");
    Platform platform = Platform.getLocalInstance();
    meta.addDescription("google api client service");
    meta.setAvailable(true);
    // add dependency if necessary
    // meta.addDependency("com.google.api-client", "google-api-client",
    // "1.23.0");
    meta.addDependency("com.google.cloud", "google-cloud-vision", "1.14.0");
    meta.addCategory("cloud", "vision");
    meta.setCloudService(true);
    meta.setRequiresKeys(true);

    return meta;
  }

  
}
