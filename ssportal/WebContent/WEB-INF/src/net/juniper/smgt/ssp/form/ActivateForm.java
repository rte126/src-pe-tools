/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


/**
 * Bean wrapping input information for service activation.<p>
 *
 * @see net.juniper.smgt.ssp.action.Services
 * @see net.juniper.smgt.ssp.action.Activate
 * @see net.juniper.smgt.ssp.action.Deactivate
 * 
 * @version 1.0
 */

public class ActivateForm extends ActionForm {
    private String userName;
    private String password;
    private String category;
    private String subscription;
    private String cancel;
    private Map persistent;
    private Map persistentInitial;
    private boolean activateOnly;
    private boolean authReq;
    private String serviceType;
    
    /**
     * default constructor;
     */
    public ActivateForm (){ }

    /**
     * Gets the value of cancel
     *
     * @return the value of cancel
     */
    public String getCancel() {
        return this.cancel;
    }

    /**
     * Sets the value of cancel
     *
     * @param argCancel Value to assign to this.cancel
     */
    public void setCancel(String argCancel){
        this.cancel = argCancel;
    }

    /**
     * Gets the value of userName
     *
     * @return the value of userName
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * Sets the value of userName
     *
     * @param argUserName Value to assign to this.userName
     */
    public void setUserName(String argUserName){
        this.userName = argUserName;
    }

    /**
     * Gets the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Sets the value of password
     *
     * @param argPassword Value to assign to this.password
     */
    public void setPassword(String argPassword){
        this.password = argPassword;
    }

    /**
     * Gets the value of category
     *
     * @return the value of category
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Sets the value of category
     *
     * @param argCategory Value to assign to this.category
     */
    public void setCategory(String argCategory){
        this.category = argCategory;
    }

    /**
     * Gets the value of subscription
     *
     * @return the value of subscription
     */
    public String getSubscription() {
        return this.subscription;
    }

    /**
     * Sets the value of subscription
     *
     * @param argSubscription Value to assign to this.subscription
     */
    public void setSubscription(String argSubscription){
        this.subscription = argSubscription;
    }



    /**
     * Gets the value of persistent
     *
     * @return the value of persistent
     */
    public String getPersistent(String key) {
        return (String)persistent.get(key);
    }

    /**
     * Sets the value of persistent
     *
     * @param argPersistent Value to assign to this.persistent
     */
    public void setPersistent(String key, String argPersistent){
        persistent.put(key, argPersistent);
    }

    /**
     * Gets the value of persistent
     *
     * @return the value of persistent
     */
    public String getPersistentInitial(String key) {
        return (String)persistentInitial.get(key);
    }

    public void setPersistentInitial(String key, String argPersistent) {
        persistentInitial.put(key, argPersistent);
    }

    public String getChangedPersistent(String key) {
        String initial = (String)persistentInitial.get(key);
        String current = (String)persistent.get(key);
        if (current==null)
            return null;
        if (initial == null || !initial.equals(current)) {
            return current;
        }
        return null;
    }
    
    /**
     * Gets the value of activateOnly
     *
     * @return the value of activateOnly
     */
    public boolean isActivateOnly() {
        return this.activateOnly;
    }

    /**
     * Sets the value of activateOnly
     *
     * @param argActivateOnly Value to assign to this.activateOnly
     */
    public void setActivateOnly(boolean argActivateOnly){
        this.activateOnly = argActivateOnly;
    }

    
    /**
     * Gets the value of authReq
     *
     * @return the value of authReq
     */
    public boolean isAuthReq() {
        return this.authReq;
    }

    /**
     * Sets the value of authReq
     *
     * @param argAuthReq Value to assign to this.authReq
     */
    public void setAuthReq(boolean argAuthReq){
        this.authReq = argAuthReq;
    }

    
    /**
     * Gets the value of serviceType
     *
     * @return the value of serviceType
     */
    public String getServiceType() {
        return this.serviceType;
    }

    /**
     * Sets the value of serviceType
     *
     * @param argServiceType Value to assign to this.serviceType
     */
    public void setServiceType(String argServiceType){
        this.serviceType = argServiceType;
    }

    
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {
        
        subscription = request.getParameter("subscription");
        category = request.getParameter("category");
        userName = "";
        password = "";
        cancel = "";
        persistent = new HashMap();
        persistentInitial = new HashMap();
        activateOnly = false;
        authReq  = false;
        serviceType = "";
    }
}// ActivateForm
