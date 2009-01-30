/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.model;

import java.util.*;
import net.juniper.smgt.sae.sae.*;
import net.juniper.smgt.ssp.PortalBehaviorFactory;

/**
 * Implementation of the "Equipment Registration Behavior".<p>
 *
 * This behavior has the following properties for DHCP users:
 * <ul>
 * <li> <em>ISP Services</em> are not available for activation or
 * deactivation.</li>
 * <li> <em>Token DHCP</em> users must login and register with the
 * portal. The Service Activation Engine registers the username and
 * password for the MAC address of the user and switches to an
 * authenticated (<em>Public</em>) IP address.
 * <li> <em>Public DHCP</em> users can login to the portal to switch
 * their user profile. The login can be persistent.
 * </ul>
 */
public class EquipmentRegistrationBehavior extends GenericPortalBehavior
{
    /**
     * Create Equipment Registration Behavior object.<p>
     *
     * @param saeAccess reference to SAEAccess feature implemented in
     * the Service Activation Engine responsible for the current user.
     * @param ipAddress IP address of the current user.
     * @param factory reference to the factory instantiating this object.
     */
    public EquipmentRegistrationBehavior(SAEAccess saeAccess,
                                         String ipAddress,
                                         boolean ignoreUnknownUser,
                                         String vrName)
        throws UnknownUserException, NonUniqueUserException, SAEException
    {
        super(saeAccess, ipAddress, ignoreUnknownUser,
              "(!(attribute.ssptype=ISP))", vrName);
    }
    
    /**
     * Check whether the portal behavior allows registration of
     * equipment or not.
     *
     * @return true, if registration is supported.
     */
    public boolean canRegister() {
        return true;
    }

    public String login(String loginName, String password, boolean persistent)
        throws LoginException,
               UnknownUserException,
               NonUniqueUserException,
               ServiceAuthenticationException,
               SAEException
    {
        String waitCondition = null;
         
        if (isTokenDhcpUser()) {
            if (persistent)
                registerLogin(loginName, password);
            else
                registerNextLogin(loginName, password);
            try {
                grantPublicIp(loginName, password);
                waitCondition = WAIT_FOR_PUBLIC;
            } catch (ServiceAuthenticationException e) {
                unregisterLogin();
                throw e;
            } catch (SAEException e) {
                unregisterLogin();
                throw e;
            }
            try {
                UserInfo.Values values = getUserInfo().getInfo("macAddress");
                String macAddress = values==null? "": values.getValue(0);
                registerEquipment(macAddress, loginName, password,
                                  "Automatic Registration");
            } catch (Exception e) {
                // if we fail to register equipment, then
                // we make this login non-persistent as well
                if (persistent) {
                    unregisterLogin();
                    registerNextLogin(loginName, password);
                }
            }
        } else {
            login(loginName, password);
            if (persistent)
                registerLogin(loginName, password);
        }
        return waitCondition;
    }

    public String logout(boolean unregister)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        logout();
        String waitCondition = null;
        if (unregister && isPublicDhcpUser()) {
            UserInfo.Values values = getUserInfo().getInfo("macAddress");
            String macAddress = values==null? "": values.getValue(0);
            unregisterEquipment(macAddress, null, null);
            revokePublicIp();
            waitCondition = "token";
        }
        return waitCondition;
    }
}
