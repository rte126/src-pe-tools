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
import net.juniper.smgt.sae.sae.Attr;
import net.juniper.smgt.sae.sae.ReadResult;


/**
 * Wrap information about the user session attached to the current
 * request.<p>
 * 
 * @version 1.0
 */

public class UserInfo {
    private String name=null;
    private boolean unauthenticated=true;
    private boolean anonymous=true;
    private boolean subscribed=false;

    private Map info = new HashMap();

    /**
     * Encapsulate the "values" of user attributes read from the
     * remote Service Activation Engine.<p>
     *
     * The values can be retrived using the "indexed bean utililies"
     * of the jakarta-Struts framework.
     */
    public static class Values {
        private String [] values;

        /**
         * Create a new instance of Values.<p>
         *
         * @param values string array of values.
         */
        public Values(String [] values) {
            this.values = values;
        }

        /**
         * Return value at given index.<p>
         *
         * @param index The index of the value to return. If index is
         * out of bounds, return <code>null</code>
         * @return The indexed value or <code>null</code> if no value
         * was indexed.
         */
        public String getValue(int index) {
            if (index >= values.length || index < 0)
                return null;
            return values[index];
        }

        public Iterator getIter() {
            return Arrays.asList(values).iterator();
        }

        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            for (int i=0; i<values.length; i++) {
                sb.append(values[i]);
                if (i < values.length-1)
                    sb.append(", ");
            }
            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * Empty default constructor.<p>
     */
    public UserInfo() {  }

    /**
     * Create UserInfo based on information from remote portal.<p>
     *
     * @param res A read result structure containing user and
     * subscription information.
     */
    UserInfo (ReadResult res) {
        
        for (int i=0; i<res.attributes.length; i++) {
            Values val = new Values(res.attributes[i].values);
            info.put(res.attributes[i].name, val);
        }
        
        Values values = getInfo("name");
        if (values != null)
            name = values.getValue(0);
        
        values = getInfo("unauthenticated");
        if (values != null)
            unauthenticated = ! "false".equalsIgnoreCase(values.getValue(0));
        else
            unauthenticated = true;

        values = getInfo("anonymous");
        if (values != null)
            anonymous = ! "false".equalsIgnoreCase(values.getValue(0));
        else
            anonymous = true;

        subscribed = res.result.length > 0;
    }

    /**
     * Get named user information.<p>
     *
     * @param key The attribute to retrieve. The <code>key</code> is
     * the name of an attribute of the <code>User</code> bean of the
     * SAE portal API.
     * @return found values or <code>null</code> if the attribute was
     * not found in the user information.
     */
    public Values getInfo(String key) {
        return (Values)info.get(key);
    }
    
    /**
     * Gets the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return this.name;
    }

   
    /**
     * Gets the value of unauthenticated
     *
     * @return the value of unauthenticated
     */
    public boolean isUnauthenticated() {
        return this.unauthenticated;
    }

    /**
     * Gets the value of anonymous
     *
     * @return the value of anonymous
     */
    public boolean isAnonymous() {
        return this.anonymous;
    }

    /**
     * Gets the value of subscribed
     *
     * @return the value of subscribed
     */
    public boolean isSubscribed() {
        return this.subscribed;
    }
}// UserInfo
