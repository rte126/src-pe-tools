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
import org.apache.struts.action.ActionErrors;
import net.juniper.smgt.sae.sae.SAET;
import net.juniper.smgt.sae.sae.ServiceAuthenticationException;
import net.juniper.smgt.ssp.form.ActivateForm;

/**
 * Handle service activation requests.<p>
 *
 * Service activation requests will try to activate a subscribed
 * service. If the service requires authentication, the user is
 * redirected to an input form asking for username and password.<p>
 *
 * @version 1.0
 */

public class Activate extends SspAction {
    public Activate () { }

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
        
        Map actAttrs = new HashMap();
        ActivateForm aForm = (ActivateForm)form;
        
        String userName = aForm.getUserName();
        String password = aForm.getPassword();
        String cancel   = aForm.getCancel();
        String subscriptionName = aForm.getSubscription();
        String persistent = aForm.getChangedPersistent(subscriptionName);

        try {
            if ("".equals(cancel)) {
                if (userName != null && userName.length()>0)
                    actAttrs.put("subscriptionUsername", new String[] {userName});
                if (password != null && password.length()>0)
                    actAttrs.put("subscriptionPassword", new String[] {password});
                if (persistent != null)
                    actAttrs.put("persistentActivation", new String[] {persistent});
                
                pb.activateService(subscriptionName, actAttrs);
            }
        }
        catch (ServiceAuthenticationException e) {
            if ((e.reason != SAET.SAET_SERVICE_AUTHORIZATION &&
                 e.reason != SAET.SAET_PUBLIC_IP_AUTHENTICATION) ||
                ! aForm.isAuthReq())
                throw e;
            
            if (! "".equals(userName) || ! "".equals(password)) {
                request.setAttribute("message", e.message);
            }
            else {
                request.setAttribute("message", "");
            }
            return mapping.getInputForward();
        }

        boolean isIspService = "ISP".equals(aForm.getServiceType());
        if (isIspService && "".equals(cancel)) {
            ActionForward forward = mapping.findForward("wait");
            return new ActionForward(forward.getPath() + "?waitCondition=public",
                                     forward.getRedirect());
        }
        
        String category = request.getParameter("category");
        ActionForward forward = mapping.findForward("success");
        
        if (category != null && category.length()>0)
            return new ActionForward(forward.getPath() + "?category=" + category,
                                     forward.getRedirect());

        return forward;
    }
   
}// Activate
