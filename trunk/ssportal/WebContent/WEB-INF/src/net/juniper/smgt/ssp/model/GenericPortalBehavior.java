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
import java.security.*;
import net.juniper.smgt.sae.sae.*;
import net.juniper.smgt.ssp.PortalBehaviorFactory;

/**
 * Generic implemenation of the PortalBehavior.<p>
 *
 * The generic implementation contains the implementation of methods
 * common to the specific behaviors.
 *
 * @version 1.0
 */
public abstract class GenericPortalBehavior implements PortalBehavior  {

    //tic.9656 - changed these three attributes to protected
    //to allow the sub class CableBehavior to access them
    protected SAEAccess saeAccess;
    protected Subscriber saeSubscriber;
    protected String ipAddress;
    protected String vrName;

    protected UserInfo userInfo;
    
    //tic.9656 - changed this attribute to protected
    //to allow the sub class CableBehavior to access it
    protected String serviceFilter = "(*)";

    // for generating session key
    private Random random = new Random();
    private byte[] b = {0, 0, 0, 0,
                        0, 0, 0, 0,
                        0, 0, 0, 0,
                        0, 0, 0, 0,
                        0, 0, 0, 0};

    public GenericPortalBehavior(SAEAccess saeAccess,
                                 String ipAddress,
                                 boolean ignoreUnknownUser,
                                 String serviceFilter,
                                 String vrName)
        throws UnknownUserException, NonUniqueUserException, SAEException
    {
        this.saeAccess = saeAccess;
        this.ipAddress = ipAddress;
        this.vrName = vrName;
        if (serviceFilter != null) 
            this.serviceFilter = serviceFilter;
        try {
            initSubscriber();
        }
        catch (UnknownUserException uue) {
            PortalBehaviorFactory.getInstance().getLocator().
                invalidateSaeFeature(ipAddress);
            if (ignoreUnknownUser) {
                userInfo = null;
            }
            else {
                throw uue;
            }
        }
        catch (NonUniqueUserException nuue) {
            PortalBehaviorFactory.getInstance().getLocator().
                invalidateSaeFeature(ipAddress);
            if (ignoreUnknownUser) {
                userInfo = null;
            }
            else {
                throw nuue;
            }
        }
        catch (SAEException se) {
            throw se; 
        }
    }

    protected void initSubscriber()
        throws UnknownUserException, NonUniqueUserException, SAEException
    {
        SubscriberId sid = new SubscriberId();

        sid.address(new TimedAddress(ipAddress, System.currentTimeMillis()));

        saeSubscriber = saeAccess.getSubscriber(sid);

        String[] subAttr = {"subscriptionName"};
        String[] svcAttr = {};
        Select subSelect = new Select(subAttr, "(&(suspended=false)(currentlyAvailable=true))");
        Select svcSelect = new Select(svcAttr, serviceFilter);
        String[] userAttr = {"*"};
        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttr);
        userInfo = new UserInfo(res);
    }
                    


    // implementation of PortalBehavior interface

    public void activateService(String subscriptionName,
                                Map actAttrs)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               OverloadException,
               SAEException
    {
        activateService(subscriptionName, null, actAttrs);
    }
    
    /**
     *
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
               SAEException
    {
        Attr[] attrs = new Attr[actAttrs.size()];
        int i=0;

        for (Iterator it=actAttrs.entrySet().iterator();
             it.hasNext();
             i++) {
            Map.Entry entry = (Map.Entry)it.next();
            attrs[i] = new Attr((String)entry.getKey(),
                                (String[])entry.getValue());
        }
        if (sessionName == null)
            sessionName = DEFAULT_SESSION_NAME.value;
        saeSubscriber.activateService(subscriptionName, sessionName,
                                      attrs);
    }

    public void modifyPersistentSession(String subscriptionName,
                                        String sessionName,
                                        boolean active)
        throws UnknownUserException, NonUniqueUserException,
               UnknownServiceException, SAEException
    {
        if (sessionName == null)
            sessionName = DEFAULT_SESSION_NAME.value;
        saeSubscriber.modifyPersistentSession(subscriptionName, sessionName,
                                              active);
    }

    public void modifyPersistentSession(String subscriptionName,
                                        boolean active)
        throws UnknownUserException, NonUniqueUserException,
               UnknownServiceException, SAEException
    {
        modifyPersistentSession(subscriptionName, null, active);
    }

    public void deletePersistentSession(String subscriptionName,
                                        String sessionName)
        throws UnknownUserException, NonUniqueUserException,
               UnknownServiceException, SAEException
    {
        if (sessionName == null)
            sessionName = DEFAULT_SESSION_NAME.value;
        saeSubscriber.deletePersistentSession(subscriptionName, sessionName);
    }

    public void deletePersistentSession(String subscriptionName)
        throws UnknownUserException, NonUniqueUserException,
               UnknownServiceException, SAEException
    {
        deletePersistentSession(subscriptionName, null);
    }


    /**
     *
     */
    public void deactivateService(String subscriptionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException
    {
        deactivateService(subscriptionName, null);
    }

    /**
     *
     */
    public void deactivateService(String subscriptionName,
                                  String sessionName)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException
    {
        if (sessionName == null)
            sessionName = DEFAULT_SESSION_NAME.value;
        saeSubscriber.deactivateService(subscriptionName, sessionName);
    }

    /**
     * Login user.<p>
     *
     * Create a user session for the specified user. Different portal
     * behaviors will implement different functionality while logging
     * in a user.
     *
     * @param loginName The name ther want to log in as.
     * @param password  The password used for authenticating the request.
     * @return true if the login caused a switch of IP address
     *
     * @throws LoginException
     * @throws UnknownUserException
     * @throws NonUniqueUserException 
     * @throws SAEException
     */
    protected boolean login(String loginName, String password)
        throws LoginException,
               UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        boolean isImplicitIspUser;
        if (saeSubscriber != null) {
            isImplicitIspUser = saeSubscriber.login(loginName, password);
        }
        else {
            // no subscriber session -> try to login Static IP user
            isImplicitIspUser = saeAccess.loginUser(ipAddress, loginName, password);
        }
        return isImplicitIspUser;
    }

    public String login(String loginName, String password, boolean persistent)
        throws LoginException,
               UnknownUserException,
               NonUniqueUserException,
               ServiceAuthenticationException,
               SAEException
    {
        String waitCondition = null;
        boolean hasIspServiceActive = (getUserInfo() == null) ? false : hasIspServiceActive();
        boolean isImplicitIspUser = login(loginName, password);
        if (persistent && isDhcpUser())
            registerLogin(loginName, password);
        if (hasIspServiceActive)
            waitCondition = WAIT_FOR_TOKEN;
        else if (isImplicitIspUser)
            waitCondition = WAIT_FOR_PUBLIC;
        return waitCondition;
    }

    /**
     *
     */
    protected void logout()
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        saeSubscriber.logout();
    }

    public String logout(boolean unregister)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        boolean hasIspServiceActive = (getUserInfo() == null) ? false : hasIspServiceActive();
        String waitCondition = (hasIspServiceActive) ? "token" : null;

        logout();

        return waitCondition;
    }


    /**
     *
     */
    public void registerEquipment(String macAddress,
                                  String loginName,
                                  String password,
                                  String description)
        throws SAEException
    {
        registerEquipment(macAddress, loginName, password, null, null, description);
    }
    
    /**
     *
     */
    public void registerEquipment(String macAddress,
                                  String loginName,
                                  String password,
                                  String vrName,
                                  String intfName,
                                  String description)
        throws SAEException
    {
        UserInfo.Values values;

        if (vrName == null) {
            values = userInfo.getInfo("vrName");
            vrName = values==null? "*": values.getValue(0);
            if (vrName == null)
                vrName = "*";
        }

        if (intfName == null) {
            values = userInfo.getInfo("intfName");
            intfName = values==null? "*": values.getValue(0);
            if (intfName == null)
                intfName = "*";
        }
        
        saeAccess.registerEquipment(macAddress,
                                    description,
                                    loginName,
                                    password,
                                    vrName,
                                    intfName);
    }

    /**
     *
     */
    public void unregisterEquipment(String macAddress)
        throws SAEException
    {
        unregisterEquipment(macAddress, null, null);
    }
    
    /**
     *
     */
    public void unregisterEquipment(String macAddress,
                                    String loginName,
                                    String password)
        throws SAEException
    {
        UserInfo.Values values;
        
        saeAccess.unregisterEquipment(macAddress,
                                      loginName==null?"":loginName,
                                      password==null?"":password);
    }


    /**
     *
     */
    public Map getRegisteredEquipment(String loginName, String password)
        throws LoginException,
               SAEException
    {
        Registration[] r = saeAccess.getRegisteredEquipment(loginName, password);      
        Map m = new HashMap();
        if (r != null) {
            for (int i=0; i < r.length; i++) {
                m.put(r[i].macAddress, r[i].description);
            }
        }
        return m;
    }
    
    /**
     * Authenticate a DHCP IP address.<p>
     *
     * The user credentials are sent to the router to authenticate an
     * IP address for the current user session. If the grant was
     * successful, the current DHCP lease will not be extended and the
     * DHCP client will receive an authenticated IP address for the
     * next DHCP Discover.
     *
     * @param loginName user name authenticating the IP address
     * @param password password authenticating the IP address
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws ServiceAuthenticationException
     * @throws SAEException
     */
    protected void grantPublicIp(String loginName, String password)
        throws UnknownUserException,
               ServiceAuthenticationException,
               SAEException
    {
        saeSubscriber.grantPublicIp(loginName, password);
    }
    
    /**
     * Revoke an authenticated DHCP IP address.<p>
     *
     * The DHCP lease of the current usersion session is
     * terminated. The DHCP client will receive an unauthenticted IP
     * address for the next DHCP Discover.
     *
     * @throws UnknownUserException
     * @throws NonUniqueUserException
     * @throws SAEException
     */
    protected void revokePublicIp()
        throws UnknownUserException,
               SAEException
    {
        saeSubscriber.revokePublicIp();
    }



    /**
     *
     */
    public void registerLogin(String loginName, String password)
        throws LoginException, SAEException
    {
        UserInfo.Values values;

        values = userInfo.getInfo("macAddress");
        String macAddress = values==null? "": values.getValue(0);

        values = userInfo.getInfo("vrName");
        String vrName = values==null? "*": values.getValue(0);
        
        values = userInfo.getInfo("intfName");
        String intfName = values==null? "*": values.getValue(0);
        
        saeAccess.registerLogin(macAddress==null?"":macAddress,
                                "Automatic Registration",
                                loginName==null?"":loginName,
                                password==null?"":password,
                                vrName==null?"*":vrName,
                                intfName==null?"*":intfName);
    }

    /**
     *
     */
    public void unregisterLogin()
        throws SAEException, LoginException
    {
        UserInfo.Values values;

        values = userInfo.getInfo("macAddress");
        String macAddress = values==null? "": values.getValue(0);

        saeAccess.unregisterLogin(macAddress==null?"":macAddress, "", "");
    }

    /**
     *
     */
    public void registerNextLogin(String loginName, String password)
        throws LoginException, SAEException
    {
        UserInfo.Values values;

        values = userInfo.getInfo("macAddress");
        String macAddress = values==null? "": values.getValue(0);

        values = userInfo.getInfo("vrName");
        String vrName = values==null? "*": values.getValue(0);
        
        values = userInfo.getInfo("intfName");
        String intfName = values==null? "*": values.getValue(0);
        
        saeAccess.registerNextLogin(macAddress==null?"":macAddress,
                                    "Automatic Registration",
                                    loginName==null?"":loginName,
                                    password==null?"":password,
                                    vrName==null?"*":vrName,
                                    intfName==null?"":intfName);
    }

    /**
     * Return list of service categories.
     *
     * The list contains the categories of all services, the user is
     * currently subscribed to.
     *
     * @param svcFilter a filter for the service
     * @return list of String
     */
    public List getCategories(String svcFilter)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException

    {
        String[] subAttributes = {};
        Select subSelect = new Select(subAttributes, "(&(suspended=false)(currentlyAvailable=true))");
        String[] svcAttributes = {"category"};
        String filter = (svcFilter==null) ? serviceFilter : "(&"+serviceFilter+svcFilter+")";
        Select svcSelect = new Select(svcAttributes, filter);
        String[] userAttributes = {};

        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttributes);

        List categories = new ArrayList();

        for (int i=0; i<res.service.length; i++) {
            for (int j=0; j< res.service[i].length; j++) {
                if ("category".equals(res.service[i][j].name)) {
                    categories.add(res.service[i][j].values[0]);
                }
            }
        }
        return categories;
    }

    public List getAllCategories()
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException
    {
        String[] svcAttributes = {"category"};
        Select svcSelect = new Select(svcAttributes, serviceFilter);

        Attr[][] res = saeSubscriber.readService(svcSelect);

        List categories = new ArrayList();

        for (int i=0; i<res.length; i++) {
            for (int j=0; j< res[i].length; j++) {
                if ("category".equals(res[i][j].name)) {
                    categories.add(res[i][j].values[0]);
                }
            }
        }
        return categories;
    }

    
    /**
     * convert a ReadResult in list-of-Row objects.
     *
     * Each Row contains a map of the returned attributes, where the
     * keys are "service.<serviceAttribute>" and
     * "subscription.<subscriptionAttribute>" and the values are the
     * first returned value of the multi-valued attribute.
     *
     * @param res read result structure
     */

    private Collection readResultToCollection(ReadResult res) {
        SortedSet table = new TreeSet();

        for (int i=0; i<res.result.length; i++) {
            Row row = new Row("subscription.subscriptionName");
            for (int j=0; j< res.service[res.result[i].svcIdx].length; j++) {
                Attr svc = res.service[res.result[i].svcIdx][j];
                row.add("service."+ svc.name,
                        svc.values.length>0 ? svc.values[0] : "");
                //System.out.println("service."+ svc.name + "="+
                //                   (svc.values.length>0 ? svc.values[0] : ""));
            }
            for (int j=0; j< res.subscription[res.result[i].subIdx].length; j++) {
                Attr sub = res.subscription[res.result[i].subIdx][j];
                row.add("subscription."+sub.name,
                        sub.values.length>0 ? sub.values[0] : "");
                //System.out.println("subscription."+sub.name + "="+
                //                   (sub.values.length>0 ? sub.values[0] : ""));
            }
            table.add(row);
        }
        return table;
    }
    
    /**
     * Return table of currently subscribed services.
     *
     * See  PortalBehavor.readSubscribedServices(category,
     *   addiontalServiceFilter) for details.
     *
     * @param category The service category to read
     * @return list of {@link net.juniper.smgt.ssp.model.Row Row} objects.
     */
    public Collection readSubscribedServices(String category)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        return readSubscribedServices(category, "");
    }

    /**
     * Return table of currently subscribed services.
     *
     * @param category The service category to read
     *
     * @param additionalServiceFilter to be used to further restrict
     *   the colleciton of services.
     *   For example, the filter
     *
     *     "(authenticationRequired=false)"
     *
     *   means: filter out the services that require authentication.
     *   A value of "" is acceptable and is treated as no
     *     additional service filter to be used.
     *   A value of null is NOT acceptable and results in
     *     runtime exception (NullPointerException).
     *
     * @return list of {@link net.juniper.smgt.ssp.model.Row Row} objects.
     */
    public Collection readSubscribedServices(String category, String additionalServiceFilter)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        String[] subAttributes = {"subscriptionName",
                                  "multiSubscriptionName",
                                  "active",
                                  "activationTrigger",
                                  "persistentActivation" };
        Select subSelect = new Select(subAttributes, "(&(sessionName=default)(suspended=false)(currentlyAvailable=true))");
        String[] svcAttributes = {"descriptionOrServiceName",
                                  "url",
                                  "type",
                                  "activateOnly",
                                  "authenticationRequired",
                                  "attribute.*"};

        Select svcSelect = new Select(svcAttributes,
                                      "(&(category="+category+")"
                                      +serviceFilter+additionalServiceFilter+")");
        String[] userAttributes = {};
        
        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttributes);
        
        return readResultToCollection(res);
    }

    /**
     */
    public Collection readAvailableServices(String category)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        String[] subAttributes = {"serviceName"};
        Select subSelect = new Select(subAttributes, "*");

        String[] svcAttributes = {"*"};
        Select svcSelect = new Select(svcAttributes,
                                      "(&(category="+category+")"
                                      +serviceFilter+")");

        Attr[][] svcRes = saeSubscriber.readService(svcSelect);
        
        Attr[][] subRes = saeSubscriber.readSubscription(subSelect);
        Set subscribedServices = new HashSet();
        for (int i=0; i<subRes.length; i++) {
            for (int j=0; j<subRes[i].length; j++) {
                if ("serviceName".equals(subRes[i][j].name) &&
                    subRes[i][j].values.length>0) {
                    subscribedServices.add(subRes[i][j].values[0]);
                }
            }
        }
        
        SortedSet table = new TreeSet();
        
        for (int i=0; i<svcRes.length; i++) {
            Row row = new Row("service.serviceName");
            for (int j=0; j< svcRes[i].length; j++) {
                String value;
                if (svcRes[i][j].values.length>0)
                    value = svcRes[i][j].values[0];
                else
                    value = "";
                
                row.add("service."+svcRes[i][j].name, value);

            }

            table.add(row);
        }
        return table;
    }

    /**
     * Return table of current subscriptions.
     *
     * @param category The service category to read
     * @return list of {@link net.juniper.smgt.ssp.model.Row Row} objects.
     */
    public Collection readSubscriptions(String category)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        String[] subAttributes = {"subscriptionName"};
        Select subSelect = new Select(subAttributes, "(suspended=false)");
        String[] svcAttributes = {};
        Select svcSelect = new Select(svcAttributes,
                                      "(&(category="+category+")"
                                      +serviceFilter+")");
        String[] userAttributes = {};

        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttributes);

        return readResultToCollection(res);
    }

    /**
     *
     * @param category <description>
     * @return <description>
     */
    public Collection readUsage(String category)
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        String[] subAttributes = {"usage.*",
                                  "durationActive",
                                  "subscriptionName",
                                  "multiSubscriptionName",
                                  "active" };
        Select subSelect = new Select(subAttributes, "(&(sessionName=default)(suspended=false)(currentlyAvailable=true))");
        String[] svcAttributes = {"descriptionOrServiceName"};
        Select svcSelect = new Select(svcAttributes,
                                      "(&(category="+category+")"
                                      +serviceFilter+")");
        String[] userAttributes = {};

        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttributes);

        return readResultToCollection(res);
    }


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
        throws SAEException, UnknownUserException {
        String[] ids = saeSubscriber.getScheduleEntryIds(startDate, endDate);
        List res = Arrays.asList(ids);
        Collections.sort(res);
        return res;
    }


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
        throws SAEException, UnknownUserException {
            
        // Input Validation: TIC.7231
        // This portal stops the Subscriber from creating a ScheduleEntry
        //   with no actions
        if ( actionRows.size() == 0 ) {
            throw new SAEException("There should be at least one action " +
               "in the schedule for accepting the schedule.");
        }

        // convert the objects
        TimeSpec fromTS = PortalUtil.convertRowToTimeSpec(fromScheduledTime);
        TimeSpec toTS = PortalUtil.convertRowToTimeSpec(toScheduledTime);
        ScheduledTime aSchedTime = new ScheduledTime(fromTS, toTS);
        net.juniper.smgt.sae.sae.ActionExt[] actions =
            PortalUtil.convertActionRowsCORBAActions(actionRows);

        saeSubscriber.addScheduleEntry(id, aSchedTime, actions);
        return;
    }

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
        throws SAEException, UnknownUserException
    {
		ScheduleEntry result = saeSubscriber.getScheduleEntryById(id);
       return result;
    }

    /**
     * Delete the One Time Schedule Entry for given date range.
     *
     * @param startDate The start of the date range.
     * @param endDate The end of the date range.
     */
    public void deleteScheduleEntry(String id)
        throws SAEException, UnknownUserException
    {
        saeSubscriber.deleteScheduleEntry(id);
        return;
    }

    /**
     * Add subscription to a service for the current subscriber.
     *
     * @param userName optional name for authorizing the subscription
     * @param password optional password for authorizing the subscription
     */
    public void addSubscription(String serviceName,
                                String userName,
                                String password)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               ServiceAuthenticationException,
               SAEException
    {
        if (userName==null) userName = "";
        if (password==null) password = "";

        saeSubscriber.addSubscription(serviceName, userName, password);
    }
    
    /**
     * Delete subscription to a service for the current subscriber.
     *
     * @param userName optional name for authorizing the subscription
     * @param password optional password for authorizing the subscription
     */
    public void deleteSubscription(String serviceName,
                                   String userName,
                                   String password)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException
    {
        if (userName==null) userName = "";
        if (password==null) password = "";
        
        saeSubscriber.deleteSubscription(serviceName, userName, password);
    }

    /**
     *
     */
    public void setSessionTimeout(int timeout)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        saeSubscriber.setSessionTimeout(timeout);
    }

    /**
     *
     */
    public void setActivationTrigger(String subscriptionName,
                                     String activationTrigger)
        throws UnknownUserException,
               NonUniqueUserException,
               UnknownServiceException,
               UnknownSubscriptionException,
               ServiceAuthenticationException,
               SAEException
    {
        saeSubscriber.setActivationTrigger(subscriptionName, activationTrigger);
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public boolean isDhcpUser() {
        if (userInfo == null)
            return false;

        UserInfo.Values values;

        values = userInfo.getInfo("publicDhcpUser");
        String publicDhcpUser = values==null? "false": values.getValue(0);

        values = userInfo.getInfo("tokenDhcpUser");
        String tokenDhcpUser = values==null? "false": values.getValue(0);

        return "true".equalsIgnoreCase(publicDhcpUser) ||
            "true".equalsIgnoreCase(tokenDhcpUser);
    }

    /**
     * Check whether the portal behavior allows registration of
     * equipment or not.
     *
     * @return true, if registration is supported.
     */
    public boolean allowPersistentLogin() {
        if (userInfo == null)
            return false;

        return isDhcpUser();
    }

    /**
     * Check whether the portal behavior allows registration of
     * equipment or not.
     *
     * @return true, if registration is supported.
     */
    public boolean canRegister() {
        return false;
    }

    /**
     * Check whether the portal behavior allows login/logout of users.
     *
     * @return true, if registration is supported.
     */
    public boolean canLogin() {
        return true;
    }
    
    /**
     * Check whether the user has an ISP service active.
     * 
     * @return true if user has an active ISP service.
     */
    public boolean hasIspServiceActive()
        throws SAEException,
               UnknownUserException,
               NonUniqueUserException               
    {
        String[] subAttributes = {"active"};
        Select subSelect = new Select(subAttributes, "(active=true)");
        String[] svcAttributes = {};
        Select svcSelect = new Select(svcAttributes, "(attribute.ssptype=ISP)");
        String[] userAttributes = {};
        
        ReadResult res = saeSubscriber.read(subSelect, svcSelect, userAttributes);

        for (int i=0; i<res.subscription.length; i++) {
            for (int j=0; j< res.subscription[i].length; j++) {
                if ("active".equals(res.subscription[i][j].name) && "true".equals(res.subscription[i][j].values[0]))
                    return true;
            }
        }
        return false;
    }
    
    /**
     *
     */
    public boolean isIspService(String serviceName)
        throws UnknownServiceException,
               UnknownUserException,
               NonUniqueUserException,
               SAEException
    {
        String [] readAttrs = {"type"};
        Attr[][] attrs =
            saeSubscriber.readService(new Select(readAttrs,
                                                 "(serviceName="+serviceName+")"));
        if (attrs.length < 1) 
            throw new UnknownServiceException(serviceName, "");
        
        if (attrs[0].length == 1)
            return "ISP".equals(attrs[0][0].values[0]);
        return false;
    }
    
    /**
     *
     */
    public boolean isTokenDhcpUser() {
        if (userInfo == null)
            return false;

        UserInfo.Values values;

        values = userInfo.getInfo("tokenDhcpUser");
        String tokenDhcpUser = values==null? "false": values.getValue(0);

        return "true".equals(tokenDhcpUser);
    }
    
    /**
     *
     */
    public boolean isPublicDhcpUser() {
        if (userInfo == null)
            return false;

        UserInfo.Values values;

        values = userInfo.getInfo("publicDhcpUser");
        String publicDhcpUser = values==null? "false": values.getValue(0);
        return "true".equals(publicDhcpUser);
    }
    
    /**
     * The current user session is deactivated when the specified time
     * out expires.
     * The session timeout counts from the current time,
     * not from the time of the start of the user session.
     */
    public void updateSessionTimeout(int timeout)
        throws UnknownUserException,
               NonUniqueUserException,
               SAEException {
        saeSubscriber.updateSessionTimeout(timeout);
    }

    /**
     * Generates a session key for the user
     * session keep alive application.
     */
    public String generateSessionKey() {
        random.nextBytes(b);
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA");
            sha.update(b);
            byte[] digest = sha.digest();
            for (int len = 0; len<digest.length; len++)
                sb.append((new Byte(digest[len])).toString());
        } catch (NoSuchAlgorithmException e) {
            for (int len = 0; len<b.length; len++)
                sb.append((new Byte(b[len])).toString());
        }
        return sb.toString();
    }

}
