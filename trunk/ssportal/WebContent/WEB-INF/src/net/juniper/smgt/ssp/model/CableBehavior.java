package net.juniper.smgt.ssp.model;

import net.juniper.smgt.sae.sae.*;
import net.juniper.smgt.ssp.PortalBehaviorFactory;
import java.util.*;

/**
 * Implementation of the "Cable Behavior" 
 *
 * The Cable Behavior allows users which do not have
 * physical interfaces to connect to the SAE
 *
 */
public class CableBehavior extends GenericPortalBehavior{
    
    /**
      * Create Cable Behavior object.<p>
      *
      * @param saeAccess reference to SAEAccess feature implemented in
      * the Service Activation Engine responsible for the current user.
      * @param ipAddress IP address of the current user.
      * @param factory reference to the factory instantiating this object.
      */
    public CableBehavior(SAEAccess saeAccess,
                         String ipAddress,
                         boolean ignoreUnknownUser,
                         String vrName)
        throws UnknownUserException, NonUniqueUserException, SAEException
    {
        super(saeAccess, ipAddress, ignoreUnknownUser, null, vrName);    
    }

     protected void initSubscriber()
        throws UnknownUserException, NonUniqueUserException, SAEException
    {

        if (vrName==null) {
            super.initSubscriber();
            return;
        }

        SubscriberId sid = new SubscriberId();

        String interfaceName = "ip"+ipAddress;

        sid.addr_if_name(new AddrInterfaceName(ipAddress,interfaceName, vrName));

        saeSubscriber = saeAccess.getSubscriber(sid);

        String[] subAttr = {"subscriptionName"};
        String[] svcAttr = {};
        Select subSelect = new Select(subAttr, "(&(suspended=false)(currentlyAvailable=true))");
        Select svcSelect = new Select(svcAttr, serviceFilter);
        String[] userAttr = {"*"};
        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttr);
        userInfo = new UserInfo(res);
    }
}
