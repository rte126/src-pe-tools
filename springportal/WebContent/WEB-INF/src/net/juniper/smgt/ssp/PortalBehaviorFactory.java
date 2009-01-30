/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import net.juniper.smgt.sae.sae.SAEAccess;
import net.juniper.smgt.sae.sae.SAEAccessHelper;
import net.juniper.smgt.sae.sae.SAEException;
import net.juniper.smgt.sae.sae.UnknownUserException;
import net.juniper.smgt.ssp.model.PortalBehavior;


/**
 * The PortalBehaviorFactory acts as a global fatory to supply the
 * different actions with portal behavior objects.<p>
 *
 * Also, the PortalBehaviorFactory makes the configured
 * SAEFeatureLocator available for advanced portal actions.<br>
 */
public class PortalBehaviorFactory  {
    private static PortalBehaviorFactory theInstance;
    private SAEFeatureLocator theLocator;
    private Constructor theBehavior;

    private PortalBehaviorFactory() { }

    /**
     * Get behavior (model) object for interaction with a Portal User.
     *
     * @param ipAddress Dotted decimal IP address of the Portal
     * User. e.g. "127.0.0.1"
     * @return A portal behavior object allowing interaction with an
     * active Service Activation Engine responsible for the requested
     * user.
     * @throws SAEException No behavior could be instantiated (e.g. when
     * the corresponding engine is not active).
     */
    public PortalBehavior getBehavior(String ipAddress,
                                      boolean ignoreUnknownUser)
        throws UnknownUserException, SAEException
    {
        try {

            List vrNameList = new ArrayList();
            SAEAccess saeAccess = SAEAccessHelper.narrow(theLocator.getSaeFeature(ipAddress, "SAEAccess", vrNameList));

            if (saeAccess == null) {
                throw new SAEException("Could not locate ServiceActivationEngine for IP address "+ipAddress);
            }

            String vrName = null;
            if (vrNameList.size()>0) {
                vrName = (String)vrNameList.get(0);             
            }

            Object[] args = new Object[] {saeAccess, ipAddress,
                                          new Boolean(ignoreUnknownUser),
                                          vrName};
            
            return (PortalBehavior)theBehavior.newInstance(args);
            
        }
        catch (InvocationTargetException ite) {
            Throwable cause = ite.getTargetException(); // 1.3 compatible
            if (cause instanceof UnknownUserException)
                throw (UnknownUserException)cause;
            throw new SAEException(cause.toString());
        }
        catch (SAEException se) {
            throw se;
        }
        catch (Throwable t) {
            //t.printStackTrace();
            throw new SAEException(t.toString());
        }
    }

    /**
     * Get locator object for advanced interaction with a Portal User.
     *
     * The implementation of a model can use the locator to retrieve
     * references to SAEFeatures different to the default "SAEAccess"
     *
     * @return SAEFeatureLocator the configured feature locator object.
     */
    public SAEFeatureLocator getLocator() {
        return theLocator;
    }

    /**
     * Set locator reference
     *
     * @param locator
     */
    public void setLocator(SAEFeatureLocator locator) {
        theLocator = locator;
    }

    /**
     * Set behavior reference
     *
     * @param behavior
     */
    public void setBehavior(Constructor behavior) {
        theBehavior = behavior;
    }

    /**
     * @return reference to singleton instance
     */
    public static PortalBehaviorFactory getInstance() {
        if (theInstance == null)
            theInstance = new PortalBehaviorFactory();
        return theInstance;
    }

    public void destroy() {
        theLocator.destroy();
        theInstance = null;
    }
}
