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

// SDX libs
import net.juniper.smgt.ssp.model.PortalBehavior;
import net.juniper.smgt.ssp.model.Row;
import net.juniper.smgt.ssp.model.UserInfo;
import net.juniper.smgt.ssp.model.PortalUtil;
import net.juniper.smgt.sae.sae.SAEException;
import net.juniper.smgt.sae.sae.UnknownUserException;

import net.juniper.smgt.sae.sae.ScheduleEntry;
import net.juniper.smgt.ssp.form.ScheduleOperationForm;

/**
 * Handle requests to schedules page.<p>
 *
 * The schedules page displays all currently available schdules and
 * provides input forms to schedule new events.
 *
 * @author      : SDX Engineering Team
 *   support@juniper.net
 *   1-888-314-JTAC (within the United States) or 
 *   1-408-745-2121 (from outside the United States)
 *
 * @see net.juniper.smgt.ssp.action.ScheduleOperation
 * @version 1.0
 */

public class Schedules extends SspAction {

    public Schedules() {
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

        String rowSize = request.getParameter("rowSize");
        if (rowSize == null) {
            rowSize = "3";
        }

        String subAction = getSubAction(pb, request);
        String filter = getFilter(pb, request, false);
        if ("Add".equals(subAction)) {
            filter = "NewSchedule";
        }
        List actionRows = new ArrayList();

        request.setAttribute(
            "services",
            pb.readSubscribedServices("*", "(authenticationRequired=false)"));
            
        setAttributeForOperOptions(request);

        long startDate = 0L;
        long endDate = 0L;
        if ("ThisMonth".equals(filter)) {
            long now = System.currentTimeMillis();
            Calendar startCal = Calendar.getInstance(Locale.US);
            startCal.setTimeInMillis(now);
            startCal.set(Calendar.DAY_OF_MONTH, 1);
            startCal.set(Calendar.AM_PM, 0);
            startCal.set(Calendar.HOUR, 0);
            startCal.set(Calendar.MINUTE, 0);
            startCal.set(Calendar.SECOND, 0);
            Calendar endCal = (Calendar) startCal.clone();
            endCal.roll(Calendar.MONTH, 1);
            startDate = startCal.getTimeInMillis();
            endDate = endCal.getTimeInMillis() - 1000; //subtract one second
        }

        Collection scheduleIds = pb.getScheduleEntryIds(startDate, endDate);

        request.setAttribute("scheduleIds", scheduleIds);

        String elem = request.getParameter("elem");
        String selectedSEId = "";
        Collection scheduleActions = new ArrayList();
        ScheduleEntry scheduleEntry = null;
        
        if (elem != null && "View".equals(subAction)) {
            // access the elements of the form handling this type of request.
            ScheduleOperationForm aForm = (ScheduleOperationForm) form;
            aForm.setName(elem);
            selectedSEId = elem;
            scheduleEntry = pb.getScheduleEntryById(elem);

            Row fromScheduledTime =
                PortalUtil.convertCORBATimeSpecToTimeSpecRows(
                    scheduleEntry.schedTime.firstTimeSpec);
            Row toScheduledTime =
                PortalUtil.convertCORBATimeSpecToTimeSpecRows(
                    scheduleEntry.schedTime.secondTimeSpec);
            scheduleActions =
                PortalUtil.convertCORBAActionsToActionRows(
                    scheduleEntry.actions);

            aForm.setScheduleTimeRow("from", fromScheduledTime);
            aForm.setScheduleTimeRow("to", toScheduledTime);
            String[] operations =
                PortalUtil.getOperationsUsingCORBAActions(
                    scheduleEntry.actions);
            String[] serviceNames =
                PortalUtil.getServiceNamesUsingCORBAActions(
                    scheduleEntry.actions);
            aForm.setOperations(operations);
            aForm.setServiceNames(serviceNames);

            actionRows =
                (List) PortalUtil.convertCORBAActionsToActionRows(
                    scheduleEntry.actions);
        }

        request.setAttribute("rowSize", rowSize);
        request.setAttribute("selectedSEId", selectedSEId);
        request.setAttribute("elem", selectedSEId);
        request.setAttribute("scheduleActions", scheduleActions);
        request.setAttribute("actionRows", actionRows);

        return mapping.findForward("success");
    }


    /**
     * get and set subAction attribute from subations roptions
     */
    protected String getSubAction(
        PortalBehavior pb,
        HttpServletRequest request)
        throws SAEException, UnknownUserException {
        return getOption(
            pb,
            request,
            "subAction",
            "subActions",
            (List) Arrays.asList(new String[] { "Add", "View", "Delete"}), true);
    }

    /**
     * get and set Filter attribute from filter roptions
     */
    protected String getFilter(PortalBehavior pb, HttpServletRequest request,
        boolean useDefault)
        throws SAEException, UnknownUserException {            
        return getOption(
            pb,
            request,
            "filter",
            "filterOptions",
            (List) Arrays.asList(new String[] {"ThisMonth", "EventList", "NewSchedule"}),
            useDefault);            
    }

    /**
     * Extract an option from curent request from list of options.<p>
     *
     * This is a convenience method which gets the requested option
     * from the request and exports both the list of
     * options and the option itself through the request object.
     *
     * @param pb current Portal behavior
     * @param request current request
     * @param selectedOptionId The bean name for the selected option
     * @param optionsId The bean name for the options
     * @param options The list of options
     * @throws SAEException If the requested option is not part of
     *   the list ov available options
     */
    protected String getOption(
        PortalBehavior pb,
        HttpServletRequest request,
        String selectedOptionId,
        String optionsId,
        List options,
        boolean useDefault)
        throws SAEException, UnknownUserException {

        String selectedOption = request.getParameter(selectedOptionId);

        // default option to first in list of options, if request
        // did not provide option.
        if ((selectedOption == null || selectedOption.length() == 0)) {
            if (useDefault) {
                selectedOption = (String) options.get(0);
            } else {
                selectedOption = "NotSelected";
            }
        }

        // check if option is valid
        if (options.indexOf(selectedOption) == -1
            && !"NotSelected".equals(selectedOption)) {
            throw new SAEException(
                "Unknown "
                    + selectedOptionId
                    + " requested: "
                    + selectedOption);
        }

        request.setAttribute(optionsId, options);
        request.setAttribute(selectedOptionId, selectedOption);
        return selectedOption;

    }
    
    /**
     *  Set request Attributes for OperatoinOptions.
     *  It overrides 'operOptions" attribute and is provided
     *    in the request
     */
    protected void setAttributeForOperOptions(HttpServletRequest request) {

        TreeMap operOptions = new TreeMap();
        operOptions.put("-1", "Please Select");
        operOptions.put("0", "deactivate");
        operOptions.put("1", "activate");
        operOptions.put("2", "deny");
        operOptions.put("3", "deny&deactivate");
        request.setAttribute("operOptions", operOptions);
    }


} // Schedules
