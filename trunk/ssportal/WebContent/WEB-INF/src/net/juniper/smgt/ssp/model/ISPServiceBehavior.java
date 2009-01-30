/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */


package net.juniper.smgt.ssp.model;

import net.juniper.smgt.sae.sae.*;
import net.juniper.smgt.ssp.PortalBehaviorFactory;
import java.util.*;

/**
 * Implementation of the "ISP Service Behavior" (previously known as
 * "Normal Behavior".<p>
 *
 * The ISP Service Behavior allows users to activate and deactivate
 * their services. It does not provide any special functionality for
 * users connected through DHCP. <em>I.e.</em> the portal does not
 * provide any functionality to switch IP addresses obtained through
 * DHCP.
 *
 */
public class ISPServiceBehavior extends GenericPortalBehavior {

     /**
      * Create ISP Service Behavior object.<p>
      *
      * @param saeAccess reference to SAEAccess feature implemented in
      * the Service Activation Engine responsible for the current user.
      * @param ipAddress IP address of the current user.
      * @param factory reference to the factory instantiating this object.
      */
    public ISPServiceBehavior(SAEAccess saeAccess,
                              String ipAddress,
                              boolean ignoreUnknownUser,
                              String vrName)
        throws UnknownUserException, NonUniqueUserException, SAEException
    {
        super(saeAccess, ipAddress, ignoreUnknownUser, null, vrName);
    }
}
