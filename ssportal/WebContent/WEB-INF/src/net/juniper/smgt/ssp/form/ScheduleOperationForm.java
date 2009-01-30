package net.juniper.smgt.ssp.form;
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
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;

// struts and servlet libs
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;

// SDX
import net.juniper.smgt.ssp.model.Row;

/**
 * The <code>ScheduleOperationForm<code> class is
 *   a Bean wrapping input information for operations
 *   on schedules and schedule page.<p>
 *
 * @author      : SDX Engineering Team
 *   support@juniper.net
 *   1-888-314-JTAC (within the United States) or 
 *   1-408-745-2121 (from outside the United States)
 *
 * @see net.juniper.smgt.ssp.action.ScheduleOperation
 * @version 1.0
 */

public class ScheduleOperationForm extends ActionForm {
    private String lessActions;
    private String moreActions;
    private String ok;
    private String cancel;
    private String filter;
    private String rowSize;

    private String subAction;
    
    private String name;

    private String[] operations;
    private String[] serviceNames;    

	private Row toScheduledTime;  
	private Row fromScheduledTime;  


    // create instances of date and time formats for formating dates in this page.
    public static DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
    public static DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.MEDIUM, Locale.US);
    public static DateFormat dateTimeFormat = DateFormat.getDateTimeInstance( DateFormat.MEDIUM,
        DateFormat.MEDIUM, Locale.US);
    public static DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(Locale.US);
    public static Calendar calendar = Calendar.getInstance(Locale.US);

    public ScheduleOperationForm(){
    }
    
    public void reset (ActionMapping mapping,
                       HttpServletRequest request) {

        // now is current time + 30 min
        java.util.Date now = new java.util.Date(java.lang.System.currentTimeMillis()
            + 30*60*1000);
        calendar.setTime(now);
                
        Row aRow = new Row("idx");
        aRow.add("idx", "from");
        // e.g. "2003"
        aRow.add("year", String.valueOf(calendar.get(Calendar.YEAR)));
        // e.g. "8" for August
        aRow.add("month", String.valueOf(calendar.get(Calendar.MONTH) + 1));
        // e.g. "4" for 4th day of the month
        aRow.add("day", String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        aRow.add("dow", "*");
        // e.g. "15" for 3 PM
        // aRow.add("hour", String.valueOf(calendar.get(Calendar.HOUR_OF_DAY)));
        aRow.add("hour", "*");
        // e.g. "1" for 1 minute past the hour
        // aRow.add("minute", String.valueOf(calendar.get(Calendar.MINUTE)));
        aRow.add("minute", "0,30");
        aRow.add("tz", "*");
        fromScheduledTime = aRow;

        aRow = new Row("idx");
        aRow.add("idx",    "to");
        aRow.add("year",   "*");
        aRow.add("month",  "*");
        aRow.add("day",    "*");
        aRow.add("dow",    "*");
        aRow.add("hour",   "*");
        aRow.add("minute", "*");
        aRow.add("tz",     "*");
        toScheduledTime = aRow;

        operations = new String[1];
        operations[0]="-1";
        serviceNames = new String[1];
        serviceNames[0] = "";


        ok = "";
        cancel = "";
        filter="";
        rowSize="3";
        moreActions = "";
        lessActions = "";
    }
    
    /**
     * Gets the value of ok
     *
     * @return the value of ok
     */
    public String getOk() {
        return this.ok;
    }

    /**
     * Sets the value of ok
     *
     * @param argOk Value to assign to this.ok
     */
    public void setOk(String argOk){
        this.ok = argOk;
    }

    /**
     * Gets the value of cancel
     *
     * @return the value of cancel
     */
    public String getCancel() {
        return this.cancel;
    }

    /**
     * Sets the value of cancel
     *
     * @param argCancel Value to assign to this.cancel
     */
    public void setCancel(String argCancel){
        this.cancel = argCancel;
    }

    /**
     * Returns the name.
     * @return String
     */
    public String getName() {
        return name;
    }
    /**
     * Sets the name.
     * @param name The name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the subAction.
     * @return String
     */
    public String getSubAction() {
        return subAction;
    }

    /**
     * Sets the subAction.
     * @param subAction The subAction to set
     */
    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    /**
     * Returns the ScheduledTime.
     * @return Row
     */
    public Row getScheduleTimeRow(String columnName) {
        if (columnName.startsWith("to")) {
            return toScheduledTime;
        } else if (columnName.startsWith("from")) {
            return fromScheduledTime;
        } else {
            // not supported
            return new Row();
        }
    }

    /**
     * Returns the ScheduledTime.
     * @return void
     */
    public void setScheduleTimeRow(String columnName, Row aRow) {
        if (columnName.startsWith("to")) {
            toScheduledTime = aRow;
        } else if (columnName.startsWith("from")) {
            fromScheduledTime = aRow;
        } else {
            // not supported
        }
    }


    /**
     * Returns the ScheduledTime.
     * @return List
     */
    public String getScheduledTime(String columnName) {
        if (columnName.startsWith("to")) {
            return toScheduledTime.getColumn(columnName.substring(3));
        } else if (columnName.startsWith("from")) {
            return fromScheduledTime.getColumn(columnName.substring(5));
        } else {
            // not supported
            return "Unsupported";
        }
    }

    /**
     * Returns the ScheduledTime.
     * @return void
     */
    public void setScheduledTime(String columnName, String value) {
        if (columnName.startsWith("to")) {
            toScheduledTime.add(columnName.substring(3), value);
        } else if (columnName.startsWith("from")) {
            fromScheduledTime.add(columnName.substring(5), value);
        } else {
            // not supported
        }
    }

    /**
     * Returns the serviceNames.
     * @return String[]
     */
    public String[] getServiceNames() {
        return serviceNames;
    }

    /**
     * Sets the serviceNames.
     * @param serviceNames The serviceNames to set
     */
    public void setServiceNames(String[] serviceNames) {
        this.serviceNames = serviceNames;
    }

    /**
     * Returns the operations.
     * @return String[]
     */
    public String[] getOperations() {
        return operations;
    }

    /**
     * Sets the operations.
     * @param operations The operations to set
     */
    public void setOperations(String[] operations) {
        this.operations = operations;
    }

    /**
     * Returns the filter.
     * @return String
     */
    public String getFilter() {
        return filter;
    }

    /**
     * Sets the filter.
     * @param filter The filter to set
     */
    public void setFilter(String filter) {
        this.filter = filter;
    }

    /**
     * Returns the rowSize.
     * @return String
     */
    public String getRowSize() {
        return rowSize;
    }

    /**
     * Sets the rowSize.
     * @param rowSize The rowSize to set
     */
    public void setRowSize(String rowSize) {
        this.rowSize = rowSize;
    }

    /**
     * Returns the moreActions.
     * @return String
     */
    public String getMoreActions() {
        return moreActions;
    }

    /**
     * Sets the moreActions.
     * @param moreActions The moreActions to set
     */
    public void setMoreActions(String moreActions) {
        this.moreActions = moreActions;
    }

    /**
     * Returns the lessActions.
     * @return String
     */
    public String getLessActions() {
        return lessActions;
    }

    /**
     * Sets the lessActions.
     * @param lessActions The lessActions to set
     */
    public void setLessActions(String lessActions) {
        this.lessActions = lessActions;
    }

    /**
     * Returns the date that was selected by the user.
     * @return Date
     */
    public Date getDate() throws ParseException{
		// sample sDate 'May 3, 1976 3:33:20 PM'
        String sDate =
            getScheduledTime("from_month")
                + " "
                + getScheduledTime("from_day")
                + ", "
                + getScheduledTime("from_year")
                + " "
                + getScheduledTime("from_hour")
                + ":"
                + getScheduledTime("from_minute")
                + ":00 AM";
                    
        java.util.Date date = dateTimeFormat.parse(sDate);
    	return date;		
    }		

}// ScheduleOperationForm
