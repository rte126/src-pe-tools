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


/**
 * Bean wrapping input information for user login.<p>
 *
 * @see net.juniper.smgt.ssp.action.Login
 * 
 * @version 1.0
 */

public class LoginForm extends ActionForm {
    private String userName;
    private String password;
    private boolean persistent;
        
    public LoginForm (){
        
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
     * Gets the value of persistent
     *
     * @return the value of persistent
     */
    public boolean isPersistent() {
        return this.persistent;
    }

    /**
     * Sets the value of persistent
     *
     * @param argPersistent Value to assign to this.persistent
     */
    public void setPersistent(boolean argPersistent){
        this.persistent = argPersistent;
    }

    /**
     *
     */
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {
        userName = "";
        password = "";
        persistent = false;
    }
}// LoginForm
