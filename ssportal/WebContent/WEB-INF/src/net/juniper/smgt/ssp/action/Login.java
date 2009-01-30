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
import net.juniper.smgt.ssp.form.LoginForm;
import net.juniper.smgt.ssp.model.EquipmentRegistrationBehavior;

/**
 * Handle login requests.<p>
 *
 * The portal allows users to login in. A successful login will create
 * a new user session which may be subscribed to different services.<p>
 *
 * The handler checks that both username and password are supplied. If
 * username or password are missing the user is redirected to a login
 * input form.
 *
 * @version 1.0
 */

public class Login extends SspAction {
    public Login (){
    }

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws Exception
    {
        PortalBehavior pb = getBehaviorNoUser(request);
        LoginForm lForm = (LoginForm)form;

        String loginName = lForm.getUserName();
        String password = lForm.getPassword();

        if (loginName == null || loginName.length() == 0 ||
            password == null || password.length() == 0) {
            // no username and password supplied -> ask for input
            request.setAttribute("allowPersistent", ""+pb.allowPersistentLogin());

            setMenuItems(request, pb);
            request.setAttribute("userInfo", pb.getUserInfo());
            return mapping.getInputForward();
        }

        String waitCondition = null;

        waitCondition = pb.login(loginName, password, lForm.isPersistent());

        HttpSession session = request.getSession();
        if (session != null)
            session.removeAttribute("sessionKey");
        
        if (waitCondition != null) {
            ActionForward forward = mapping.findForward("wait");
            return new ActionForward(forward.getPath() +
                                     "?waitCondition="+waitCondition,
                                     forward.getRedirect());
        }
        
        setMenuItems(request, pb);
        request.setAttribute("userInfo", pb.getUserInfo());

        return mapping.findForward("success");
    }
   
}// Login
