/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package ckim.srcpe.springportal;

import java.util.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.Controller;

import net.juniper.smgt.sae.sae.SAEException;
import net.juniper.smgt.ssp.PortalBehaviorFactory;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import net.juniper.smgt.sae.sae.UnknownUserException;
import net.juniper.smgt.sae.sae.NonUniqueUserException;

/**
 * Generic Portal Action.<p>
 *
 * The SspAction class implements common functionality for all Portal
 * specific action implementations.<p>
 *
 * @version 1.0
 */

public abstract class SspAction implements Controller {
    public SspAction (){ }

    /**
     * Get reference to a model object for interacting with a Service
     * Activation Engine.<p>
     *
     * @param request The HTTP request currently handled. The user is
     * identified through the remoteAddr of the request.
     * @return The specific PortalBehavior configured for the portal.
     */
    static PortalBehavior getBehavior(HttpServletRequest request)
        throws UnknownUserException, SAEException
    {

        PortalBehaviorFactory behaviorFactory = PortalBehaviorFactory.getInstance();

        String remoteAddr = request.getHeader("X-Forwarded-for");
        if (remoteAddr == null) {
            remoteAddr = request.getRemoteAddr();
        }        

        PortalBehavior pb = behaviorFactory.getBehavior(remoteAddr, false);

        return pb;
    }

    /**
     * Get reference to a model object for interacting with a Service
     * Activation Engine.<p>
     *
     * UnknownUserExceptions are ignored.<p>
     *
     * @param request The HTTP request currently handled. The user is
     * identified through the remoteAddr of the request.
     * @return The specific PortalBehavior configured for the portal.
     */
    static PortalBehavior getBehaviorNoUser(HttpServletRequest request)
        throws UnknownUserException, SAEException
    {

        PortalBehaviorFactory behaviorFactory = PortalBehaviorFactory.getInstance();

        String remoteAddr = request.getHeader("X-Forwarded-for");
        if (remoteAddr == null) {
            remoteAddr = request.getRemoteAddr();
        }        

        PortalBehavior pb = behaviorFactory.getBehavior(remoteAddr, true);

        return pb;
    }

    /**
     * Get list of menu actions.<p>
     *
     * @param pb the active portal behavior.
     * @param request current HTTPRequest
     * @return false if the list of menu items is empty
     */
    static boolean setMenuItems(HttpServletRequest request, PortalBehavior pb) {
        List items = new ArrayList();
        UserInfo userInfo = null;

        if (pb != null)
            userInfo = pb.getUserInfo();

        if (userInfo == null) {
            request.setAttribute("loginAction", "login");
        }
        else {
            if (userInfo.isSubscribed()) {
                items.add("services");
                items.add("usage");
            }
            
            if (! userInfo.isAnonymous()) {
                if (userInfo.isSubscribed()) {
                    items.add("account");
                    items.add("schedules");
            }
                items.add("subscribe");
            }

            if (! userInfo.isUnauthenticated() && pb.canRegister()) {
                items.add("register");
                items.add("unregister");
            }
            request.setAttribute("menuItems", items);
            

            if (userInfo.isUnauthenticated()) {
                request.setAttribute("loginAction", "login");
            }
            else {
                request.setAttribute("loginAction", "logout");
            }
        }
        return ! items.isEmpty();
    }

    /**
     * Extract service category from curent request.<p>
     *
     * This is a convenience method which gets the requested service
     * category from the request and exports both the list of
     * categories and the category itself through the request object.
     *
     * @param pb current Portal behavior
     * @param request current request
     * @return requested category or <code>null</code> if no
     * categories are available
     * @throws SAEException if the requested category is not part of
     * the list ov available categories
     */
    protected String getCategory(PortalBehavior pb,
                                 HttpServletRequest request,
                                 String serviceFilter)
        throws SAEException, UnknownUserException, NonUniqueUserException
    {
        List categories = pb.getCategories(serviceFilter);
        String category = request.getParameter("category");

        if (categories.isEmpty())
            return null;

        // default category to first in list of categories, if request
        // did not provide category.
        if ((category == null || category.length()==0)
            && ! categories.isEmpty()) {
            category = (String)categories.get(0);
        }

        // check if category is valid
        boolean ok = false;
        for (Iterator it=categories.iterator(); it.hasNext();) {
            String c = (String)it.next();
            if (c.equals(category)) {
                ok = true;
                break;
            }
        }
        if (! ok)
            throw new SAEException("Unknown category requested: " + category);

        request.setAttribute("categories", categories);
        request.setAttribute("category", category);
        return category;
        
    }

}// SspAction
