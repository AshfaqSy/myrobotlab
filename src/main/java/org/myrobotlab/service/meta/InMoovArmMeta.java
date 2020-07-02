package org.myrobotlab.service.meta;

import org.myrobotlab.framework.Platform;
import org.myrobotlab.logging.LoggerFactory;
import org.myrobotlab.service.meta.abstracts.MetaData;
import org.slf4j.Logger;

public class InMoovArmMeta  extends MetaData {
  private static final long serialVersionUID = 1L;
public final static Logger log = LoggerFactory.getLogger(InMoovArmMeta.class);
  
  /**
   * This class is contains all the meta data details of a service.
   * It's peers, dependencies, and all other meta data related to the service.
   * 
   */
  public InMoovArmMeta() {

    
    Platform platform = Platform.getLocalInstance();
    
   addDescription("the InMoov Arm Service");
   addCategory("robot");

   addPeer("bicep", "Servo", "Bicep servo");
   addPeer("rotate", "Servo", "Rotate servo");
   addPeer("shoulder", "Servo", "Shoulder servo");
   addPeer("omoplate", "Servo", "Omoplate servo");
   addPeer("arduino", "Arduino", "Arduino controller for this arm");

    
  }

  
  
}

