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
import net.juniper.smgt.sae.sae.UnknownUserException;
import net.juniper.smgt.sae.sae.SAEException;

/**
 * Handle wait requests during IP address transitions.<p>
 *
 * @version 1.0
 */

public class Wait extends SspAction {
    public Wait (){
        
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        String condition = (String) request.getParameter("waitCondition");

        try {
            PortalBehavior pb = getBehavior(request);
            
            if ("public".equals(condition) && pb.isPublicDhcpUser())
                return mapping.findForward("success");
            if ("token".equals(condition) && pb.isTokenDhcpUser())
                return mapping.findForward("success");
        }
        catch (UnknownUserException e) {
            // ignored
        }
        catch (SAEException e) {
            // ignored
        }
               
        ActionForward forward = mapping.findForward("wait");
        String path = forward.getPath() + "?waitCondition="+condition;
        response.setHeader("Refresh", "10; "+path);
        
        return forward;
    }
   
}// Wait
