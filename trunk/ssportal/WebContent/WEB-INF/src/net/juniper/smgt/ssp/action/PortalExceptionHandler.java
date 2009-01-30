/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.action;

import java.io.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.juniper.smgt.sae.sae.*;
import net.juniper.smgt.ssp.PortalBehaviorFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import net.juniper.smgt.ssp.model.UserInfo;
import net.juniper.smgt.ssp.model.PortalBehavior;


/**
 * Handle Portal Exceptions.<p>
 *
 * Portal Exceptions are dispatched to different exception views.<p>
 *
 * The following portal specific exceptions are recognised:
 * <ul>
 * <li> UnknownUserException
 * <li> NonUniqueUserException
 * <li> UnknownServiceException
 * <li> UnknownSubscriptionException
 * <li> LoginException
 * <li> ServiceAuthenticationException
 * <li> SAEException
 * </ul>
 *
 * Unrecognised exceptions are handled by a generic error view.
 *
 * @version 1.0
 */

public class PortalExceptionHandler extends ExceptionHandler {
    public PortalExceptionHandler (){
        
    }

    public ActionForward execute(Exception ex,
                                 ExceptionConfig ae,
                                 ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
    {
        request.setAttribute("exception", ex);
        request.setAttribute("exceptionClass", ex.getClass().getName());
        String message = ex.getMessage();
        request.setAttribute("exceptionMessage", message==null?"":message);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintWriter out = new PrintWriter(baos);
        ex.printStackTrace(out);
        out.close();
        request.setAttribute("stackTrace", baos.toString());

        try {
            PortalBehavior pb = SspAction.getBehavior(request);
            UserInfo ui = pb.getUserInfo();

            SspAction.setMenuItems(request, pb);
            request.setAttribute("userInfo", ui);
        }
        catch (Exception e) {
            // ignore
        }
        
        if (ex instanceof org.omg.CORBA.SystemException ||
            ex instanceof UnknownUserException ||
            ex instanceof SAEException) {
            String remoteAddr = request.getHeader("X-Forwarded-for");
            if (remoteAddr == null) {
                remoteAddr = request.getRemoteAddr();
            }        

            PortalBehaviorFactory.getInstance().getLocator().
                invalidateSaeFeature(remoteAddr);
        }

        if (ex instanceof UnknownUserException) {
            UnknownUserException e = (UnknownUserException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("userId", e.userId);
            return mapping.findForward("unknownUser");
        }

        if (ex instanceof NonUniqueUserException) {
            NonUniqueUserException e = (NonUniqueUserException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("userId", e.userId);
            return mapping.findForward("nonUniqueUser");
        }

        if (ex instanceof UnknownServiceException) {
            UnknownServiceException e = (UnknownServiceException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("serviceName", e.serviceName);
            return mapping.findForward("unknownService");
        }

        if (ex instanceof UnknownSubscriptionException) {
            UnknownSubscriptionException e = (UnknownSubscriptionException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("subscriptionName", e.subscriptionName);
            return mapping.findForward("unknownSubscription");
        }

        if (ex instanceof LoginException) {
            LoginException e = (LoginException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("currentIp", e.currentIp);
            request.setAttribute("loginName", e.loginName);
            return mapping.findForward("loginError");
        }

        if (ex instanceof ServiceAuthenticationException) {
            ServiceAuthenticationException e = (ServiceAuthenticationException)ex;
            request.setAttribute("message", e.message);
            request.setAttribute("reason", ""+e.reason.value());
            request.setAttribute("userIp", e.userIp);
            request.setAttribute("serviceName", e.serviceName);
            request.setAttribute("sessionName", e.sessionName);
            return mapping.findForward("serviceAuthError");
        }

        if (ex instanceof OverloadException) {
            OverloadException e = (OverloadException)ex;
            request.setAttribute("message",
                                 "Unable to perform action due to router overload. Please try again later");
            return mapping.findForward("saeError");
        }
        if (ex instanceof SAEException) {
            SAEException e = (SAEException)ex;
            request.setAttribute("message", e.message);
            return mapping.findForward("saeError");
        }

        // generic error
        return mapping.findForward("error");
    }
    
}// PortalExceptionHandler
