/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp;

import net.juniper.smgt.sae.sae.SAEFeature;
import java.util.Properties;
import java.util.List;
import javax.servlet.ServletException;


/**
 * Interface to a Service Activation Engine Feature Locator.<p>
 *
 * @version 1.0
 */
public interface SAEFeatureLocator {

    /**
     * Return reference to a named feature of a service activation
     * engine.<p>
     *
     * The locator uses the ipAddress of a user to locate first the
     * Service Activation Engine that handles the session of this user
     * and then the featureName to retrieve a reference to a remote
     * object implementing the named Service Activation Engine
     * feature.<p>
     *
     * @param ipAddress The IP address of the user requesting access.
     * @param featureName The name of the requested feature. The
     * feature must be installed in the located service activation
     * engine.
     * @param vrName A List to be filled by this method with the name
     * of the virtualRouter which this ipAddress maps to
     * @return reference to the located feature or <code>null</code>
     * if the feature could not be located.
     */
    public SAEFeature getSaeFeature(String ipAddress,
                                    String featureName,
                                    List vrName);

    /**
     * Configure the locator.<p>
     *
     * @param props a Properties object containing configuration
     * properties.
     */
    public void setProperties(Properties props) throws ServletException;

    /**
     * Invalidate a feature<p>
     *
     * This method destroys any cached object references for the given
     * ipAddress.
     *
     * @param ipAddress The IP address of the user requesting access.
     */
    public void invalidateSaeFeature(String ipAddress);


    /**
     * free all resources used by this locator.
     */
    public void destroy();
       
}
