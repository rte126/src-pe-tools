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
 * Bean wrapping input information for the Account page.<p>
 *
 * @see net.juniper.smgt.ssp.action.Account
 * 
 * @version 1.0
 */

public class AccountForm extends ActionForm {
    private String category;
    private Map    trigger;
    private String ok;
    private String cancel;
        
    public AccountForm (){
    }

    
    /**
     *
     */
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {
        trigger = new HashMap();
        ok = "";
        cancel = "";
    }
    
    /**
     * Gets the value of trigger
     *
     * @return the value of trigger
     */
    public String getTrigger(String key) {
        return (String)trigger.get(key);
    }

    /**
     * Sets the value of trigger
     *
     * @param value Value to assign to this.trigger
     */
    public void setTrigger(String key, String value){
        trigger.put(key, value);
    }

    public Map getTriggerMap() {
        return trigger;
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
        this.ok = argCategory;
    }

}// AccountForm
