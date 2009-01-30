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
 * Bean wrapping input information for subscription and
 * unsubscription.<p>
 *
 * @see net.juniper.smgt.ssp.action.Subscribe
 * 
 * @version 1.0
 */

public class SubscribeForm extends ActionForm {
    private String userName;
    private String password;
    private Map subscribe;
    private String ok;
    private String cancel;
    private String category;
        
    public SubscribeForm (){
        
    }

    
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {
        userName = "";
        password = "";
        subscribe = new HashMap();
        ok = "";
        cancel = "";
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
     * Gets the value of subscribe
     *
     * @return the value of subscribe
     */
    public Boolean getSubscribe(String key) {
        return (Boolean)subscribe.get(key);
    }

    /**
     * Sets the value of subscribe
     *
     * @param argSubscribe Value to assign to this.subscribe
     */
    public void setSubscribe(String key, Boolean value){
        subscribe.put(key, value);
    }

    public Map getSubscribeMap() {
        return subscribe;
    }

    /**
     * Gets the value of ok
     *
     * @return the value of ok
     */
    public String getOk() {
        return this.ok;
    }

    /**
     * Sets the value of ok
     *
     * @param argOk Value to assign to this.ok
     */
    public void setOk(String argOk){
        this.ok = argOk;
    }

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

}// SubscribeForm
