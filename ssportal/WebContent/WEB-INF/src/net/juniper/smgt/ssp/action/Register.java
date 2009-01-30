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
import net.juniper.smgt.ssp.form.RegisterForm;
import net.juniper.smgt.sae.sae.Registration;

/**
 * Handle Equipment Registration requests.<p>
 *
 * @version 1.0
 */

public class Register extends SspAction {
    public Register (){
        
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
        
        RegisterForm aForm = (RegisterForm)form;
        String userName    = aForm.getUserName();
        String password    = aForm.getPassword();
        String macAddress  = aForm.getMacAddress();
        String description = aForm.getDescription();
        String ok          = aForm.getOk();
        String cont        = aForm.getCont();
        
        if (userName == null || userName.length()==0)
            return mapping.getInputForward();
        
        if (cont!=null && !"".equals(cont)){
            request.setAttribute("registration", pb.getRegisteredEquipment(userName, password));
            return mapping.findForward("getmac");
        }

        if (ok!=null && !"".equals(ok)) {
            pb.registerEquipment(macAddress, userName, password, description);
            request.setAttribute("registration",
                                 pb.getRegisteredEquipment(userName, password));
            return mapping.findForward("getmac");
        }

        return mapping.findForward("success");
   
    }   
}// Register
