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
import net.juniper.smgt.ssp.form.ActivateForm;
import net.juniper.smgt.ssp.model.Row;

/**
 * Handle requests to services page.<p>
 *
 * The services page displays all currently available services and
 * provides input forms to activate or deactivate services.
 *
 * @version 1.0
 */

public class Services extends SspAction {
    public Services (){
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
        
        String category = getCategory(pb, request, null);
        if (category == null) {
            return mapping.findForward("index");
        }
        
        Collection services = pb.readSubscribedServices(category);
        request.setAttribute("services", services);

        ActivateForm aForm = (ActivateForm)form;
        for (Iterator it=services.iterator(); it.hasNext(); ) {
            Row row = (Row)it.next();
            String sn = row.getColumn("subscription.subscriptionName");
            String pa = row.getColumn("subscription.persistentActivation");
            aForm.setPersistent(sn, pa);
            aForm.setPersistentInitial(sn, pa);
        }

        if (request.getParameter("category") == null)
            return mapping.findForward("nocategory");
        else 
            return mapping.findForward("success");
    }
    
}// Services
