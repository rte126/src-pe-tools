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
import javax.servlet.http.HttpSession;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import net.juniper.smgt.sae.sae.UnknownUserException;
import net.juniper.smgt.sae.sae.SAEException;

/**
 * Handle keep alive for user session.<p>
 *
 * @version 1.0
 */

public class KeepAlive extends SspAction {
    public KeepAlive (){
        
    }

    public ActionForward execute(ActionMapping mapping,
                        ActionForm form,
                        HttpServletRequest request,
                        HttpServletResponse response)
        throws Exception
    {        
        PortalBehavior pb = getBehavior(request);
        UserInfo ui = pb.getUserInfo();
                
        ActionForward forward = mapping.findForward("keepAlive");
        
        HttpSession session = request.getSession();

        if (session==null || ui.isUnauthenticated())
            return forward;

        String sessionKey = (String) session.getAttribute("sessionKey");

        if (sessionKey == null || (sessionKey != null &&
              sessionKey.equals((String) request.getParameter("sessionKey")))) {
            String newSessionKey = pb.generateSessionKey();
            session.setAttribute("sessionKey", newSessionKey);
            pb.updateSessionTimeout(30);
            ActionForward refresh = mapping.findForward("refresh");
            String path = "URL="+refresh.getPath()+"?sessionKey="+newSessionKey;
            response.setHeader("Refresh", "15; "+path);
        }

        return forward; 
    }
   
}
