package net.juniper.smgt.ssp.action;
/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

// java libs
import java.util.*;

// servlet libs
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// struts libs
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;

// SDX libs
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.UserInfo;
import net.juniper.smgt.ssp.model.Row;
import net.juniper.smgt.ssp.model.PortalUtil;
import net.juniper.smgt.ssp.form.ScheduleOperationForm;
import net.juniper.smgt.sae.sae.ServiceAuthenticationException;

import net.juniper.smgt.sae.sae.ScheduleEntry;


/**
 * Handle schedule operation requests.<p>
 *
 * Schedule Operation request will try to add, delete or view an
 *   individual schedule entry for the given subscriber.<p>
 *
 * @author      : SDX Engineering Team
 *   support@juniper.net
 *   1-888-314-JTAC (within the United States) or 
 *   1-408-745-2121 (from outside the United States)
 *
 * @see net.juniper.smgt.ssp.form.ScheduleOperationForm
 * @version 1.0
 */

public class ScheduleOperation extends Schedules {

    public ScheduleOperation() {
    }

    public ActionForward execute(
        ActionMapping mapping,
        ActionForm form,
        HttpServletRequest request,
        HttpServletResponse response)
        throws Exception {

        //
        PortalBehavior pb = getBehavior(request);
        UserInfo ui = pb.getUserInfo();

        setMenuItems(request, pb);
        request.setAttribute("userInfo", ui);
 
        setAttributeForOperOptions(request);

        // access the elements of the form handling this type of request.
        ScheduleOperationForm aForm = (ScheduleOperationForm) form;

        String[] operations = aForm.getOperations();
        String[] serviceNames = aForm.getServiceNames();
        String rowSize = aForm.getRowSize();

        String ok = aForm.getOk();
        String cancel = aForm.getCancel();
        String formFilter = aForm.getFilter();
        String moreActions = aForm.getMoreActions();
        String lessActions = aForm.getLessActions();

        // access request parameters for this request
        //   including setting the services that are available in the serviceName
        //   that allow scheduling actions (this is used in the serviceNames
        //   html:select
        String subAction = getSubAction(pb, request);
        request.setAttribute("services", pb.readSubscribedServices("*",
            "(authenticationRequired=false)"));

        String filter = getFilter(pb, request, false);
        if ("Add".equals(subAction)) {
            filter = "NewSchedule";
        }


        // elem is the element for the delete and view operation 
        String elem = request.getParameter("elem");
        if (elem == null) {
            String name = aForm.getName();
            if (name != null) {
                elem = name;
            }
        }

        // the scheduled actions for the given element is initialized
        Collection scheduleActions = new ArrayList();

        try {
            if (!"".equals(moreActions)) {
                rowSize = String.valueOf(Integer.parseInt(rowSize) + 3);
                aForm.setRowSize(rowSize);
                if (!"NewSchedule".equals(filter)) {
                    subAction = "View";
                }
            } else if (!"".equals(lessActions)) {
                int newSize = Integer.parseInt(rowSize) - 3;
                if (newSize >= 3) {
                    rowSize = String.valueOf(newSize);
                    aForm.setRowSize(rowSize);
                }
                if (!"NewSchedule".equals(filter)) {
                    subAction = "View";
                }
            } else if ("".equals(cancel) && "Schedule".equals(ok)) {
                String name = aForm.getName();
                if (name.equals("")) {
                    name = String.valueOf(System.currentTimeMillis());
                }
                Row fromScheduledTime = aForm.getScheduleTimeRow("from");
                Row toScheduledTime = aForm.getScheduleTimeRow("to");
                List actionRows =
                    PortalUtil.createActionRowsUsing(operations, serviceNames);

                pb.addScheduleEntry(
                    name,
                    fromScheduledTime,
                    toScheduledTime,
                    actionRows);
            } else if (elem != null && "Delete".equals(subAction)) {
                pb.deleteScheduleEntry(elem);
                // After deletion, the deleted element should not be selected
                elem = null;
            }

        } catch (Exception e) {
            // uncomment and program for especial handling
            //
            //   request.setAttribute("message", e.getMessage());
            //   ActionForward forward = mapping.findForward("saeError");
            //   return forward;
            throw e;
        }

        request.setAttribute("scheduleActions", scheduleActions);

        ActionForward forward = mapping.findForward("success");

        if (filter != null && filter.length() > 0) {
            String newPath =
                forward.getPath()
                    + "?filter="
                    + filter
                    + "&rowSize="
                    + rowSize
                    + (elem != null ? "&elem=" + elem : "")
                    + "&subAction=" + subAction;
            return new ActionForward(newPath, forward.getRedirect());
        }

        return forward;

    }

} // ScheduleOperation
