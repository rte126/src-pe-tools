/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp;

import java.util.*;
import net.juniper.smgt.gateway.gal.CorbaRefSaeId;
import net.juniper.smgt.gateway.gal.SaeLocator;
import net.juniper.smgt.lib.config.ConfigMgr;
import net.juniper.smgt.lib.config.ConfigMgrFactory;
import net.juniper.smgt.sae.sae.*;
import org.omg.CORBA.ORB;
import javax.servlet.ServletException;
import net.juniper.smgt.gateway.gal.NicFactory;
import net.juniper.smgt.gateway.gal.NicKey;

/**
 * Implementation of a distributed SAEFeatureLocator.<p>
 *
 * <em>TODO: describe NIC implementation, properties, etc.</em>
 * @version 1.0
 */
public class DistributedFeatureLocator implements SAEFeatureLocator {
    private static ORB orb = ORB.init((String[])null, null);

    private Properties props;

    private SaeLocator saeLocator;

    private ConfigMgr confMgr;

    // cache remote object references
    // key: SAEid, value: Map of SAEFeatures (key: featureName)
    private Map features; 
    
    public DistributedFeatureLocator () {
    }
    
    public void setProperties(Properties props) throws ServletException {
        this.props = props;

        String locName = props.getProperty("DistributedFeatureLocator.locName");

        try {
            confMgr = ConfigMgrFactory.getConfigMgr(props);
            NicFactory.setConfigManager(confMgr);

            saeLocator = NicFactory.getInstance().getSaeLocator(locName, confMgr);
        
        }
        catch (Exception e) {
            throw new ServletException("DistributedFeatureLocator failed: "+e);
        }
        features = new HashMap();

        //init Logger
        try {
            net.juniper.smgt.lib.logging.Log.init(confMgr, "/");
        }
        catch (net.juniper.smgt.lib.logging.LogInitException e) { //ignore it, webapp can run without logger
        }
    }

    public SAEFeature getSaeFeature(String ipAddress, String featureName, List vrNameList)
    {
        NicKey key = new NicKey(ipAddress);
        try {
            CorbaRefSaeId saeId = (CorbaRefSaeId)saeLocator.lookupSae(key);
            String ref=null;

            if (saeId != null) {
                ref = saeId.getReference();
                String vrName = saeId.getIntermediateValue("Vr");
                if (vrNameList!=null && vrName!=null) {
                    vrNameList.add(vrName);
                }
                
            }
            
            if (ref == null || ref.length()==0) {
                System.out.println("Cannot locate remote engine for IP Address: "+ipAddress);
                
                return null;
            }

            Map saeFeatures = (Map)features.get(ref);
            if (saeFeatures == null) {
                saeFeatures = new HashMap();
                features.put(ref, saeFeatures);
            }

            SAEFeature f = (SAEFeature)saeFeatures.get(featureName);

            if (f == null) {
                ServiceActivationEngine sae =
                    ServiceActivationEngineHelper.narrow(orb.string_to_object(ref));
                f = sae.getFeature(featureName);
                saeFeatures.put(featureName, f);
            }
            return f;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void invalidateSaeFeature(String ipAddress) {
        NicKey key = new NicKey(ipAddress);
        saeLocator.invalidateLookup(key, null);
    }

    public void destroy() {
        saeLocator.destroy();
        saeLocator = null;

        confMgr.destroy();
        confMgr = null;

        orb.destroy();
        orb = null;
    }
}
