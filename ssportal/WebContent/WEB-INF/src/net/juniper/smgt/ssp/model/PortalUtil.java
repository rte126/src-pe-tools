/**
 * ===========================================================================
 * Licensed Materials - Property of Juniper Networks
 * Juniper Networks SDX(tm)
 *
 *  (C) Copyright Juniper Networks Inc. 2000-2003 All Rights Reserved.
 * ===========================================================================
 */

package net.juniper.smgt.ssp.model;

import java.util.*;
import net.juniper.smgt.sae.sae.*;

/**
 * Utilility for converting CORBA object and Portal
 *   objects. <p>
 * 
 * @author      : SDX Engineering Team
 *   support@juniper.net
 *   1-888-314-JTAC (within the United States) or 
 *   1-408-745-2121 (from outside the United States)
 *
 * @version 1.0
 */
public class PortalUtil {

    /**
     * singleton instance
     */
    private static PortalUtil singleton;

    /**
     * Convert a Row to a TimeSpec
     * @param aRow Row - the row object containing a Time Spec
     * @return TimeSpec - the ORB object containing a Time Spec 
     */
    public static TimeSpec convertRowToTimeSpec(Row aRow) {
        TimeSpec aTS =
            new TimeSpec(
                aRow.getColumn("year"),
                aRow.getColumn("month"),
                aRow.getColumn("day"),
                aRow.getColumn("dow"),
                aRow.getColumn("hour"),
                aRow.getColumn("minute"),
                aRow.getColumn("tz"));
        return aTS;
    }

    /**
     * Convert Actions Row --> ORB
     */
    public static ActionExt[] convertActionRowsCORBAActions(Collection actionRows) {

        net.juniper.smgt.sae.sae.ActionExt[] actions =
            new net.juniper.smgt.sae.sae.ActionExt[actionRows.size()];

        // Iterate through the rows assuming the rows are provided in the
        //   correct order (that's ignoring the idx column).
        Iterator i = actionRows.iterator();
        int idx = 0;
        Attr[] attrs = new Attr[0];
        while (i.hasNext()) {
            Row aRow = (Row) i.next();
            actions[idx] =
                new net.juniper.smgt.sae.sae.ActionExt(
                    Integer.parseInt(aRow.getColumn("operation")),
                    aRow.getColumn("serviceName"),
                    attrs);
            idx++;
        }
        return actions;
    }

    /**
     * Convert Actions ORB --> Row
     */
    public static Collection convertCORBAActionsToActionRows(ActionExt[] actions) {
        List scheduleActions = new ArrayList();
        for (int counter = 0; counter < actions.length; counter++) {
            Row aRow = new Row("idx");
            aRow.add("idx", String.valueOf(counter + 1));
            aRow.add("operation", String.valueOf(actions[counter].operation));
            aRow.add("serviceName", actions[counter].serviceName);
            scheduleActions.add(aRow);
        }
        return scheduleActions;
    }

    public static String[] getOperationsUsingCORBAActions(ActionExt[] actions) {
        List result = new ArrayList();
        for (int counter = 0; counter < actions.length; counter++) {
            result.add(String.valueOf(actions[counter].operation));
        }
        return (String[]) result.toArray(new String[0]);
    }

    public static String[] getServiceNamesUsingCORBAActions(ActionExt[] actions) {
        List result = new ArrayList();
        for (int counter = 0; counter < actions.length; counter++) {
            result.add(actions[counter].serviceName);
        }
        return (String[]) result.toArray(new String[0]);
    }

    public static List createActionRowsUsing(
        String[] operations,
        String[] serviceNames) {

        ArrayList rows = new ArrayList();
        for (int idx = 0; idx < operations.length; idx++) {
            if (!"-1".equals(operations[idx])) {
                Row aRow = new Row("idx");
                aRow.add("idx", String.valueOf(idx));
                aRow.add("operation", String.valueOf(operations[idx]));
                aRow.add("serviceName", serviceNames[idx]);
                rows.add(aRow);
            }
        }
        return rows;
    }

    public static Row convertCORBATimeSpecToTimeSpecRows(TimeSpec aTimeSpec) {
        if (singleton == null) {
            singleton = new PortalUtil();
        }
        return singleton.convertCORBATimeSpecToTimeTimeSpecRows0(aTimeSpec);
    }

    protected Row convertCORBATimeSpecToTimeTimeSpecRows0(TimeSpec aTimeSpec) {
        Row aRow = new Row();
        aRow.add("year", aTimeSpec.year);
        aRow.add("month", aTimeSpec.month);
        aRow.add("day", aTimeSpec.dayOfMonth);
        aRow.add("dow", aTimeSpec.dayOfWeek);
        aRow.add("hour", aTimeSpec.hour);
        aRow.add("minute", aTimeSpec.minute);
        aRow.add("tz", aTimeSpec.extension);
        return aRow;
    }

}
