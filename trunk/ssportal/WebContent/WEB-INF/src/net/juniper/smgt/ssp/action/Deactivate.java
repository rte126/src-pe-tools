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
import net.juniper.smgt.ssp.form.ActivateForm;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Handle service deactivation requests.<p>
 *
 * @version 1.0
 */

public class Deactivate extends SspAction {
    public Deactivate (){
        
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
        
        ActivateForm aForm = (ActivateForm)form;

        if (! aForm.isActivateOnly()) {
            String subscriptionName = aForm.getSubscription();
            String persistent = aForm.getPersistentInitial(subscriptionName);
            if ("true".equals(persistent)) {
                pb.deletePersistentSession(subscriptionName);
            }

            pb.deactivateService(subscriptionName);
        
            boolean isIspService =  "ISP".equals(aForm.getServiceType());
            if (isIspService) {
                ActionForward forward = mapping.findForward("wait");
                return new ActionForward(forward.getPath() + "?waitCondition=token",
                                         forward.getRedirect());
            }
        }
        
        String category = request.getParameter("category");
        ActionForward forward = mapping.findForward("success");
        
        if (category != null && category.length()>0)
            return new ActionForward(forward.getPath() + "?category=" + category,
                                     forward.getRedirect());

        return forward;
    }
   
}// Deactivate
