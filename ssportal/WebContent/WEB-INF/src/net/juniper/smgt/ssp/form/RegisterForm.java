package net.juniper.smgt.ssp.form;

import java.util.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;



/**
 * RegisterForm.java
 *
 *
 * Created: Tue Jan 14 11:26:10 2003
 *
 * @version 1.0
 */

public class RegisterForm extends ActionForm {
    private String userName;
    private String password;
    private Map    register;
    private String ok;
    private String cont;
    private String cancel;
    private String description;
    private String macAddress;
        
    public RegisterForm (){
        
    }

    
    /**
     *
     */
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {
        userName = "";
        password = "";
        register = new HashMap();
        ok = "";
        cancel = "";
        description = "";
        macAddress = "";
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
     * Gets the value of register
     *
     * @return the value of register
     */
    public Boolean getRegister(String key) {
        return (Boolean)register.get(key);
    }

    /**
     * Sets the value of register
     *
     * @param argRegister Value to assign to this.register
     */
    public void setRegister(String key, Boolean value){
        register.put(key, value);
    }

    public Map getRegisterMap() {
        return register;
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
     * Sets the value of persistent
     *
     * @param argOk Value to assign to this.persistent
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
     * Gets the value of description
     *
     * @return the value of description
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Sets the value of description
     *
     * @param argDescription Value to assign to this.description
     */
    public void setDescription(String argDescription){
        this.description = argDescription;
    }
    
    /**
     * Gets the value of macAddress
     *
     * @return the value of macAddress
     */
    public String getMacAddress() {
        return this.macAddress;
    }
    
    /**
     * Sets the value of macAddress
     *
     * @param argMacAddress Value to assign to this.macAddress
     */
    public void setMacAddress(String argMacAddress){
        this.macAddress = argMacAddress;
    }

    
    /**
     * Gets the value of cont
     *
     * @return the value of cont
     */
    public String getCont() {
        return this.cont;
    }

    /**
     * Sets the value of cont
     *
     * @param argCont Value to assign to this.cont
     */
    public void setCont(String argCont){
        this.cont = argCont;
    }

}// RegisterForm
