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
import org.apache.struts.action.DynaActionForm;
import net.juniper.smgt.sae.sae.ServiceAuthenticationException;
import net.juniper.smgt.ssp.form.SubscribeForm;
import net.juniper.smgt.ssp.model.Row;
import net.juniper.smgt.sae.sae.SAET;

/**
 * Handle subscription requests.<p>
 *
 * Users can subscribe to available services. If the
 * ServiceActivationEngine is configured to require authentication for
 * subscriptions, the user is redirected to an input form.
 * 
 * @version 1.0
 */

public class Subscribe extends SspAction {
    public Subscribe (){
        
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        PortalBehavior pb = getBehavior(request);
        UserInfo ui = pb.getUserInfo();

        setMenuItems(request, pb);
        request.setAttribute("userInfo", ui);
        
        SubscribeForm sForm = (SubscribeForm)form;
        
        // initialize current service category
        List categories = pb.getAllCategories();
        request.setAttribute("categories", categories);

        String category = request.getParameter("category");
        if ((category == null || category.length()==0)
            && ! categories.isEmpty()) {
            category = (String)categories.get(0);
        }

        request.setAttribute("category", category);
        sForm.setCategory(category);

        // get current set of subscription data
        Set userSubscriptions = new HashSet();
        UserInfo.Values vals = ui.getInfo("subscriptionNames");
        for (int i=0; ; i++) {
            String subname = vals.getValue(i);
            if (subname == null)
                break;
            userSubscriptions.add(subname);
        }
        
        Collection services = pb.readAvailableServices(category);
        Map currentSubscription = new HashMap();
        for (Iterator it=services.iterator(); it.hasNext();) {
            Row row = (Row)it.next();

            String serviceName = row.getColumn("service.serviceName");

            currentSubscription.put(serviceName,
                                    new Boolean(userSubscriptions.contains(serviceName)));
        }
       
        String ok       = sForm.getOk();
        String userName = sForm.getUserName();
        String password = sForm.getPassword();

        if (! "".equals(ok)) {
            // key: name of subscribed service, value: true=subscribe
            Map subscribe = sForm.getSubscribeMap();
            if (subscribe.isEmpty())
                subscribe = (Map) request.getSession().getAttribute("subscribe");
            try {
                for (Iterator it=subscribe.entrySet().iterator();
                     it.hasNext();) {
                    Map.Entry entry = (Map.Entry)it.next();

                    String service = (String)entry.getKey();
                    Boolean action = (Boolean)entry.getValue();

                    if (! action.equals(currentSubscription.get(service))) {
                        if (action.booleanValue()) {
                            pb.addSubscription(service,
                                               userName, password);
                        }
                        else {
                            pb.deleteSubscription(service,
                                                  userName, password);
                        }
                        currentSubscription.put(service, action);
                    }
                }
            }
            catch (ServiceAuthenticationException e) {
                if (! e.reason.equals(SAET.SAET_SUBSCRIPTION_AUTHORIZATION))
                    throw e;
                
                if (! "".equals(userName) || ! "".equals(password)) {
                    request.setAttribute("message", e.message);
                }
                else {
                    request.setAttribute("message", "");
                }
                request.getSession().setAttribute("subscribe",subscribe);
                return mapping.getInputForward();
            }
        }

        for (Iterator it=currentSubscription.entrySet().iterator();
             it.hasNext();) {
            Map.Entry entry = (Map.Entry)it.next();
            sForm.setSubscribe((String)entry.getKey(),
                               (Boolean)entry.getValue());
        }

        request.setAttribute("services", services);
       
        if (request.getParameter("category") == null)
            return mapping.findForward("nocategory");
        else 
            return mapping.findForward("success");
    }
   
}// Subscribe
