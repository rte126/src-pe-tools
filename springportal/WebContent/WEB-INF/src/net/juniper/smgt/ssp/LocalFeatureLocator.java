/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp;

import net.juniper.smgt.sae.sae.*;
import org.omg.CORBA.ORB;
import java.util.*;


/**
 * Simple Implementation of the SAEFeatureLocator interface.<p>
 *
 * The LocalFeatureLocator uses static configuration to return
 * references to a single Service Activation Engine.<p>
 *
 * This class can be used for a simple demonstration, where only a
 * single ServiceActivationEngine and one or many Portal Servers are
 * involved.<p>
 *
 * The locator uses the property <code>LocalFeatureLocator.objectRef</code> from the
 * configuration file to configure the reference to the Service
 * Activation Engine.<p>
 *
 * @version 1.0
 */
public class LocalFeatureLocator implements SAEFeatureLocator {

    private static ORB orb = ORB.init((String[])null, null);
    private Properties props;
    private ServiceActivationEngine sae;

    // cache features for the remote SAE
    // key: featureName, value: SAEFeature
    private Map<String,SAEFeature> features;

    private String vrName;
    
    public LocalFeatureLocator() {
    }

    public void setProperties(Properties props) {
        this.props = props;

        features = new HashMap<String,SAEFeature>();
    }
    
    public SAEFeature getSaeFeature(String ipAddress, String featureName, List<String> vrNameList)
    {
        if (sae == null) {
            String ior = props.getProperty("LocalFeatureLocator.objectRef",
                                           "file:///opt/UMC/ssp/var/run/sae.ior");

            org.omg.CORBA.Object obj = orb.string_to_object(ior);
            sae = ServiceActivationEngineHelper.narrow(obj);

            vrName = props.getProperty("LocalFeatureLocator.vrName",null);
            
            if (sae == null)
                // Remote Reference not available
                return null;
        }

        if (vrNameList!=null && vrName!=null) {
            vrNameList.add(vrName);                
        }
        
        if (features.containsKey(featureName))
            return (SAEFeature)features.get(featureName);


        try {
            SAEFeature f = sae.getFeature(featureName);
            features.put(featureName, f);
            return f;
        }
        catch (FeatureNotInstalled e) {
            return null;
        }
    }

    public void invalidateSaeFeature(String ipAddress) {
        sae = null;                     // reference to engine is invalid.
    }

    public void destroy() {
        orb.destroy();
        orb = null;
    }
}
