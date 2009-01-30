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
import net.juniper.smgt.ssp.model.EquipmentRegistrationBehavior;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

/**
 * Handle logout requests.<p>
 *
 * When a user logs out from the portal the associated user session is
 * terminated and replaced with an "unauthenticated" user session.<p>
 *
 * @version 1.0
 */

public class Logout extends SspAction {
    public Logout (){
        
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
        
        DynaActionForm dForm = (DynaActionForm)form;
        boolean unregister =
            ((Boolean)dForm.get("persistent")).booleanValue();
        String logout = (String)dForm.get("logout");
        String cancel = (String)dForm.get("cancel");

        if (! "".equals(cancel))
            return mapping.findForward("success");

        if ("".equals(logout)) {
            setMenuItems(request, pb);
            request.setAttribute("userInfo", ui);
            request.setAttribute("canRegister", ""+pb.canRegister());
        
            return mapping.getInputForward();
        }

        if (pb.isDhcpUser())
            pb.unregisterLogin();
            
        String waitCondition = pb.logout(unregister);
        
        if (waitCondition != null) {
            ActionForward forward = mapping.findForward("wait");
            return new ActionForward(forward.getPath() +
                                     "?waitCondition="+waitCondition,
                                     forward.getRedirect());
        }
        
        return mapping.findForward("success");
    }
   
}// Logout
