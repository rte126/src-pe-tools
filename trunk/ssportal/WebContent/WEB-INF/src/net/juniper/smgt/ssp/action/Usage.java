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

/**
 * Handle requests to usage page.<p>
 *
 * The usage page displays accounting information for currently
 * subscribed services.<p>
 *
 * @version 1.0
 */

public class Usage extends SspAction {
    public Usage (){
        
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

        request.setAttribute("usage", pb.readUsage(category));

        if (request.getParameter("category") == null)
            return mapping.findForward("nocategory");
        else 
            return mapping.findForward("success");
    }
   
}// Usage
