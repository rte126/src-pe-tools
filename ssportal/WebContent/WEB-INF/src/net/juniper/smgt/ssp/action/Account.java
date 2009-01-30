/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.action;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.juniper.smgt.ssp.form.AccountForm;
import net.juniper.smgt.ssp.model.Row;

/**
 * Handle requests to the "account" portal page.<p>
 *
 * The account page displays all currently subscribed services of a
 * User and allows changes to the "ActivationTrigger", i.e. setting
 * subscriptions to "MANUAL" or "ACTIVATE_ON_LOGIN".
 *
 * @version 1.0
 */
public class Account extends SspAction {
    public Account (){ }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        PortalBehavior pb = getBehavior(request);
        UserInfo ui = pb.getUserInfo();
        AccountForm aForm = (AccountForm)form;

        setMenuItems(request, pb);
        request.setAttribute("userInfo", ui);
        
        // initialize current service category
        String category = getCategory(pb, request, "(authenticationRequired=false)");
        if (category == null || !"".equals(aForm.getCancel())) {
            return mapping.findForward("index");
        }
        
        aForm.setCategory(category);
        // get current set of subscription data        
        Collection services = pb.readSubscribedServices(category, "(authenticationRequired=false)");
        Map currentTrigger = new HashMap();
        for (Iterator it=services.iterator(); it.hasNext();) {
            Row row = (Row)it.next();
            String trigger = row.getColumn("subscription.activationTrigger");
            String subscriptionName = row.getColumn("subscription.subscriptionName");
            currentTrigger.put(subscriptionName, trigger);
        }

        String ok     = aForm.getOk();
        if (! "".equals(ok)) {
            // "Activate" action has been selected

            for (Iterator it=aForm.getTriggerMap().entrySet().iterator();
                 it.hasNext(); ) {
                Map.Entry entry = (Map.Entry)it.next();
                String subscriptionName = (String)entry.getKey();
                String trigger = (String)entry.getValue();
                if (! trigger.equals((String)currentTrigger.get(subscriptionName))) {
                    // selection is different from currently selected trigger
                    pb.setActivationTrigger(subscriptionName,
                                            trigger);
                }
            }
        }

        

        // get current set of subscription data
        services = pb.readSubscribedServices(category, "(authenticationRequired=false)");
        currentTrigger = new HashMap();
        for (Iterator it=services.iterator(); it.hasNext();) {
            Row row = (Row)it.next();
            String trigger = row.getColumn("subscription.activationTrigger");
            String subscriptionName = row.getColumn("subscription.subscriptionName");
            currentTrigger.put(subscriptionName, trigger);
        }

        // initialize action form
        for (Iterator it=currentTrigger.entrySet().iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            aForm.setTrigger((String)entry.getKey(), (String)entry.getValue());
        }

        request.setAttribute("services", services);
        if (request.getParameter("category") == null)
            return mapping.findForward("nocategory");
        else 
            return mapping.findForward("success");
    }
   
}// Account
