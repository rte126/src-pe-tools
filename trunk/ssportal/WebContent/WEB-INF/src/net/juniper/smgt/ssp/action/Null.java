/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * Null Handle request.<p>
 *
 * @version 1.0
 */

public class Null extends SspAction {
    public Null (){       
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        try {
            PortalBehavior pb = getBehavior(request);
            UserInfo ui = pb.getUserInfo();

            setMenuItems(request, pb);
            request.setAttribute("userInfo", ui);
        }
        catch(Exception e) {
            // ignore
        }
        return mapping.findForward("success");
    }
    
}// Null
