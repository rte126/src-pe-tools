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

/**
 * The portal behavior specifies the functionality of the "model" used
 * to interface with a Service Activation Engine.<p>
 *
 * The implementation of this interface provides a thin wrapper of the
 * remote interface of the Service Activation Engine.
 *
 * Although the interface is specified in the context of a Web
 * Application, the model does not depend on any web application
 * specific functionality. The same model implemenation can be used to
 * create different applications.
 */
public interface PortalBehavior {

    /**
     * Activate a service session.<p>
     *
     * @param subscriptionName name of subscription to activate
     * @param sessionName name of service session to activate. If
     * <code>null</null> or "", create default session
     * @param actAttr map of activation attributes. The keys are
     * attribute names, the values are String arrays of values.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws UnknownSubscriptionException 
     * @throws ServiceAuthenticationException
     * @throws OverloadException
     * @throws SAEException
     */
    public void activateService(String subscriptionName,
                                String sessionName,
                                Map actAttrs)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               OverloadException,
               SAEException;

    /**
     * Activate default service session.<p>
     *
     * @param subscriptionName name of subscription to activate
     * @param actAttr map of activation attributes. The keys are
     * attribute names, the values are String arrays of values.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws UnknownSubscriptionException 
     * @throws ServiceAuthenticationException
     * @throws OverloadException
     * @throws SAEException
     */
    public void activateService(String subscriptionName,
                                Map actAttrs)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               OverloadException,
               SAEException;


    /**
     * Deactivate a service session.<p>
     *
     * @param subscriptionName name of the service subscription to
     * deactivate
     * @param sessionName name of the service session to deactivate,
     * if null or "", deactivate default session
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws UnknownSubscriptionException 
     * @throws ServiceAuthenticationException 
     * @throws SAEException
     */

    public void deactivateService(String subscriptionName,
                                  String sessionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException;

    /**
     * Deactivate default service session.<p>
     *
     * @param subscriptionName name of the service subscription to
     * deactivate
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws UnknownSubscriptionException 
     * @throws ServiceAuthenticationException 
     * @throws SAEException
     */

    public void deactivateService(String subscriptionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException;

    /**
     * Modify whether a service session is activated automatically.<p>
     *
     * @param subscriptionName name of the service subscription to
     * modify
     * @param sessionName name of the service session to modify,
     * if null or "", modify default session
     * @param active if true, the service session is activated
     * persistently, if false it is not activated persistently
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws SAEException
     */
    public void modifyPersistentSession(String subscriptionName,
                                        String sessionName,
                                        boolean active)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               SAEException;

    /**
     * Modify whether the default service session is activated
     * automatically.<p>
     *
     * @param subscriptionName name of the service subscription to
     * modify
     * @param active if true, the service session is activated
     * persistently, if false it is not activated persistently
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws SAEException
     */
    public void modifyPersistentSession(String subscriptionName,
                                        boolean active)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               SAEException;

    /**
     * Delete a persistent service session.<p>
     *
     * @param subscriptionName name of the service subscription to
     * delete from the persistent cache
     * @param sessionName name of the service session to delete,
     * if null or "", delete default session
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws SAEException
     */
    public void deletePersistentSession(String subscriptionName,
                                        String sessionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               SAEException;

    /**
     * Delete the default persistent service session.<p>
     *
     * @param subscriptionName name of the service subscription to
     * delete from the persistent cache
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws SAEException
     */
    public void deletePersistentSession(String subscriptionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               SAEException;

    
    public final String WAIT_FOR_TOKEN = "token";
    public final String WAIT_FOR_PUBLIC = "public";
    
    /**
     * Login user.<p>
     *
     * Create a user session for the specified user. Different portal
     * behaviors will implement different functionality while logging
     * in a user.
     *
     * @param loginName The name ther want to log in as.
     * @param password  The password used for authenticating the request.
     * @param persistent If set, the login is made persistent.
     * @return null if the login does not causes a switch in IP
     * address, WAIT_FOR_TOKEN if the login causes a switch to a TOKEN
     * address, WAIT_FOR_PUBLIC if the login causes a switch to a
     * PUBLIC address.
     *
     * @throws LoginException
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws ServiceAuthenticationException
     * @throws SAEException
     */
    public String login(String loginName, String password, boolean persistent)
        throws LoginException,
               UnknownUserException,
               NonUniqueUserException,
               ServiceAuthenticationException,
               SAEException;        

    /**
     * Logout current user session<p>
     *
     * The current user session is terminated and replaced with an
     * unauthenticated user session.
     *
     * @param unregister if true remove persistent login information
     * @return null if the logout does not causes a switch in IP
     * address, WAIT_FOR_TOKEN if the logout causes a switch to a
     * TOKEN address, WAIT_FOR_PUBLIC if the logout causes a switch to
     * a PUBLIC address.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException 
     * @throws SAEException
     */
    public String logout(boolean unregister)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException;

    /**
     * Register equipment.<p>
     *
     * When a Service Activation Engine receives a DHCP request from
     * registered equipment, it sends the registered loginName and
     * password to the router to authenticate a public IP address.<p>
     *
     * The identified equipment is registered without a check for
     * router and interface. This use may impose an unacceptable
     * security risk.<p>
     *
     * @param macAddress The MAC address identifying the equipment.
     * @param loginName Name used for authenticating IP address.
     * @param password Password used for authenticating IP address.
     * @param description Text describing the purpose of the
     * registration
     *
     * @throws SAEException
     */
    public void registerEquipment(String macAddress,
                                  String loginName,
                                  String password,
                                  String description)
        throws SAEException;
               
    /**
     * Register equipment.<p>
     *
     * When a Service Activation Engine receives a DHCP request from
     * registered equipment, it sends the registered loginName and
     * password to the router to authenticate a public IP address.<p>
     *
     * The registration is only recognized, if the Service Activation
     * Engine receives it from the registered virtual router and
     * interface. If the DHCP request is received from a different VR
     * or interface the equipment is treated as unregistered.<p>
     *
     * @param macAddress The MAC address identifying the equipment.
     * @param loginName Name used for authenticating IP address.
     * @param password Password used for authenticating IP address.
     * @param vrName Name of virtual router where the equipment is
     * registered. If the name is "*", virtual router check is
     * disabled.
     * @param intfName Name of the interface where the equipment is
     * registered. If the name is "*", the interface check is
     * disabled.
     * @param description Text describing the purpose of the
     * registration
     *
     * @throws SAEException
     */
    public void registerEquipment(String macAddress,
                                  String loginName,
                                  String password,
                                  String vrName,
                                  String intfName,
                                  String description)
        throws SAEException;

    /**
     * Remove equipment registration.<p>
     *
     * The virtual router and interface are not checked before
     * removing the registration.<p>
     *
     * @param macAddress The MAC address identifying the equipment.
     *
     * @throws SAEException
     */
    public void unregisterEquipment(String macAddress)
        throws SAEException;
               
    /**
     * Remove equipment registration.<p>
     *
     * @param macAddress The MAC address identifying the equipment.
     * @param loginName Name used for authenticating IP address. If
     * the loginName is <code>null</code> or "", the credentials are
     * not checked, otherwise they must match the credentials provided
     * when registering the equipment.
     * @param password Password used for authenticating IP address. If
     * the password is <code>null</code> or "", the credentials are
     * not checked, otherwise they must match the credentials provided
     * when registering the equipment.
     *
     * @throws SAEException
     */
    public void unregisterEquipment(String macAddress,
                                    String loginName,
                                    String password)
        throws SAEException;
    

    /**
     * Return list of equipment registered for the user identified by
     * "loginName".<p>
     *
     * @param loginName name which authenticated and identifies a user
     * who has equipment registered for IP address assignment.
     * @param password password used for authentication.
     *
     * @throws LoginException
     * @throws SAEException
     */
    public Map getRegisteredEquipment(String loginName,
                                       String password)
        throws LoginException,
               SAEException;
    
    /**
     * Register login.<p>
     *
     * The provided loginName and password are persistently
     * stored. The key to the store is the MAC address of the current
     * user session. If the MAC address is not known (e.g. if the
     * current session is not a DHCP session) an exception is thrown.<p>
     *
     * @param loginName login name to register
     * @param password password authenticating loginName
     *
     * @throws LoginException
     * @throws SAEException
     */
    public void registerLogin(String loginName, String password)
        throws LoginException, SAEException;

    /**
     * Remove login registration.<p>
     *
     * The login information stored based on the MAC address of the
     * current user session is removed.
     *
     * @throws LoginException
     * @throws SAEException
     */
    public void unregisterLogin()
        throws LoginException, SAEException;

    /**
     * Register next login.<p>
     *
     * The provided loginName and password are temporarily stored
     * while a DHCP IP address transition is in progress. If the
     * current session is not a DHCP session an exception is
     * thrown.<p>
     *
     * @param loginName login name to register
     * @param password password authenticating loginName
     *
     * @throws LoginException
     * @throws SAEException
     */
    public void registerNextLogin(String loginName, String password)
        throws LoginException, SAEException;

    /**
     * Return collection of currently subscribed services.<p>
     *
     * See  PortalBehavor.readSubscribedServcies(category, 
     *     addiontalServiceFilter) for details.
     *
     * @param category The service category to read
     * @return list of {@link net.juniper.smgt.ssp.model.Row Row} objects.
     */
    public Collection readSubscribedServices(String category)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException;

    /**
     * Return collection of currently subscribed services.<p>
     * 
     * The provided information can be used for the "services" page to
     * display information about currently subscribed services.<p>
     *
     * @param category Name of the service category to return. If the
     * category is "*", all services are returned.
     *
     * @param additionalServiceFilter to be used to further restrict
     *   the colleciton of services.
     *   For example, the filter
     *
     *     "(authenticationRequired=false)"
     *
     *   means: filter out services that require authentication.
     *   A value of "" is acceptable and is treated as no
     *     additional service filter to be used.
     *   A value of null is NOT acceptable and results in
     *     runtime exception (NullPointerException).
     *
     * @return collection of {@link net.juniper.smgt.ssp.model.Row Row}
     * objects.  The column names in the returned Rows are the name of
     * the Service and Subscription attributes prefixed with
     * "service." and "subscription.". <it>E.g.</it>
     * "service.serviceName", "subscription.active".
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public Collection readSubscribedServices(String category,
                                             String additionalServiceFilter)
        throws SAEException, UnknownUserException,
               NonUniqueUserException;

    /**
     * Return collection of available services.<p>
     * 
     * The provided information can be used for the "subscribe" page to
     * display information about service available for subscription.<p>
     *
     * @param category Name of the service category to return. If the
     * category is "*", all services are returned.
     *
     * @return collection of {@link net.juniper.smgt.ssp.model.Row Row}
     * objects.  The column names in the returned Rows are the name of
     * the Service attributes prefixed with "service." <it>E.g.</it>
     * "service.serviceName",
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public Collection readAvailableServices(String category)
        throws SAEException, UnknownUserException,
               NonUniqueUserException;


    /**
     * Return collection of current subscriptions.<p>
     * 
     * The provided information can be used for the "account" page to
     * display information about current subscriptons.<p>
     *
     * @param category Name of the service category to return. If the
     * category is "*", all services are returned.
     *
     * @return collection of {@link net.juniper.smgt.ssp.model.Row Row}
     * objects.  The column names in the returned Rows are the name of
     * the Service and Subscription attributes prefixed with
     * "service." and "subscription.". <it>E.g.</it>
     * "service.serviceName", "subscription.subscriptionName".
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public Collection readSubscriptions(String category)
        throws SAEException, UnknownUserException,
               NonUniqueUserException;
        

    /**
     * Return collection of current usage information.<p>
     * 
     * The provided information can be used for the "usage" page to
     * display information about current usage.<p>
     *
     * @param category Name of the service category to return. If the
     * category is "*", all services are returned.
     *
     * @return collection of {@link net.juniper.smgt.ssp.model.Row Row}
     * objects.  The column names in the returned Rows are the name of
     * the Service and Subscription attributes prefixed with
     * "service." and "subscription.". <it>E.g.</it>
     * "service.serviceName", "subscription.usage.bytesFromUser".
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public Collection readUsage(String category)
        throws SAEException, UnknownUserException,
               NonUniqueUserException;

    /**
     * Returns a collection of all the schedule entry Ids
     *   in the given range.
     *
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     * @return Collection of java.lang.String objects
     * 
     * @throws UnknownUserException
     * @throws SAEException
     */
    public Collection getScheduleEntryIds(long startDate, long endDate)
        throws SAEException, UnknownUserException;

    /**
     * Adds a Schedule Entry for the subscriber.
     *
     * @param fromScheduledTime The Scheduled Time from Time Spec
     *   provided as
     *   {@link net.juniper.smgt.ssp.model.Row Row}
     *   objects. A row includes various columns for the Time Specification.
     *   For example
     *   "idx", "year", "month", etc. 
     * @param toScheduledTime The Scheduled Time from Time Spec
     *   provided as
     *   {@link net.juniper.smgt.ssp.model.Row Row}
     *   objects. A row includes various columns for the Time Specification.
     *   For example
     *   "idx", "year", "month", etc.
     * @param actionRows list
     *   of {@link net.juniper.smgt.ssp.model.Row Row}
     *   objects. A row includes three columns. They are "idx", "operation",
     *   and "serviceName.
     * @return void
     */
    public void addScheduleEntry(
        String id,
        Row fromScheduledTime,
        Row toScheduledTime,
        Collection actionRows)
        throws SAEException, UnknownUserException;


    /**
     * Get a Schedule Entry given its Id.
     *
     * @param id String The id get the Schedule Entry.
     * @return ScheduleEntry 
     * 
     * @throws UnknownUserException
     * @throws SAEException
     */
    public ScheduleEntry getScheduleEntryById(String id)
        throws SAEException, UnknownUserException;


    /**
     * Delete the Time Schedule Entry for given id.
     *
     * @param id The id of the date.
     * 
     * @throws UnknownUserException
     * @throws SAEException
     */
    public void deleteScheduleEntry(String id)
        throws SAEException, UnknownUserException;


    /**
     * Add subscription to a service for the current subscriber.<p>
     *
     * @param serviceName Name of the service to subscribe.
     * @param userName optional name for authorizing the subscription
     * @param password optional password for authorizing the subscription
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws ServiceAuthenticationException
     * @throws SAEException
     */
    public void addSubscription(String serviceName,
                                String userName,
                                String password)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               ServiceAuthenticationException,
               SAEException;


    /**
     * Delete subscription to a service for the current subscriber.
     *
     * @param serviceName Name of the service to remove.
     * @param userName optional name for authorizing the subscription
     * @param password optional password for authorizing the subscription
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws ServiceAuthenticationException
     * @throws SAEException
     */
    public void deleteSubscription(String serviceName,
                                   String userName,
                                   String password)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException;


    /**
     * Set User Session Timeout.<p>
     *
     * The current user session is deactivated when the specified time
     * out expires.
     * The session timeout counts from the start of the user session.
     *
     * @param timeout number of seconds until the user session expires.     
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public void setSessionTimeout(int timeout)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException;

    /**
     * Modify the "Activation Trigger" of a subscription.<p>
     *
     * The activation trigger determines whether a subscription will
     * be activated automatically when the user logs in, or must be
     * activated manually through the portal.<p>
     *
     * @param subscriptionName Name of the subscription to modify.
     * @param activationTrigger either "MANUAL" or "ACTIVATE_ON_LOGIN"
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws UnknownServiceException
     * @throws UnknownSubscriptionException
     * @throws ServiceAuthenticationException
     * @throws SAEException
     */
    public void setActivationTrigger(String subscriptionName,
                                     String activationTrigger)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException;

    /**
     * Get information about the current user session.<p>
     *
     * @return {@link net.juniper.smgt.ssp.model.UserInfo UserInfo}
     * object initialized with data for the current user.
     */
    public UserInfo getUserInfo();

    /**
     * @return true if the current user session uses a DHCP address.
     */
    public boolean isDhcpUser(); 

    /**
     * Get list of currently available service categories.<p>
     *
     * Only the categories of services currently subscribed are returned.
     *
     * @param svcFilter a filter for the service
     * @return list of strings.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public List getCategories(String svcFilter)
        throws SAEException, UnknownUserException,
               NonUniqueUserException;

    /**
     * Get list of all service categories.
     *
     * @return list of strings.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public List getAllCategories()
        throws SAEException, UnknownUserException,
               NonUniqueUserException;


    /**
     * Check whether the portal behavior allows persistent logins or
     * not.
     *
     * @return true, if persistent login is supported.
     */
    public boolean allowPersistentLogin();

    /**
     * Check whether the portal behavior allows registration of
     * equipment or not.
     *
     * @return true, if registration is supported.
     */
    public boolean canRegister();

    /**
     * Check whether the portal behavior allows login/logout of users.
     *
     * @return true, if login/logout is supported.
     */
    public boolean canLogin();
    
    /**
     * Check whether the user has an ISP service active.
     * 
     * @return true if user has an active ISP service.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public boolean hasIspServiceActive()
        throws SAEException, UnknownUserException,
               NonUniqueUserException;
    
    /**
     * Check whether the named service is an "ISP" service.<p>
     *
     * @param serviceName name of a service
     * @return true if the service is an ISP service
     *
     * @throws UnknownServiceException
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    public boolean isIspService(String serviceName)
        throws UnknownServiceException, UnknownUserException,
               NonUniqueUserException, SAEException;
    
    /**
     * @return true when the current user session uses an
     * unauthenticated DHCP address.<p>
     */
    public boolean isTokenDhcpUser();
    
    /**
     * @return true when the current user session uses an
     * authenticated DHCP address.<p>
     */
    public boolean isPublicDhcpUser();
    
    /**
     * The current user session is deactivated when the specified time
     * out expires.
     * The session timeout counts from the current time,
     * not from the time of the start of the user session.
     */
    public void updateSessionTimeout(int timeout)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException;
    /**
     * Generates a session key for the user
     * session keep alive aplication.
     */
    public String generateSessionKey(); 

}
