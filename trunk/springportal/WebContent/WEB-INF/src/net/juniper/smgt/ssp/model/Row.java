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

/**
 * Wrap information read from a remote Service Activation Engine.<p>
 *
 * The wrapped information is presented in a way, which makes it easy
 * to format a table based on the information read from the engine.
 * Each row contains named columns with the actual values.
 *
 * @version 1.0
 */
public class Row implements Comparable {
    private Map data = new HashMap();

    private String keyColumn=null;

    /**
     * Create unordered Row.
     */
    public Row() {
    }

    /**
     * Create Row, which can be sorted by the information stored in
     * "keyColumn".
     *
     * @param keyColumn name of the column used for comparison.
     */
    public Row(String keyColumn) {
        this.keyColumn = keyColumn;
    }
    
    /**
     * Return data for a named column.<p>
     *
     * @param columnName name of the column to return
     * @return String content of the column or <code>null</code> if named
     * value does not exist.
     */
    public synchronized String getColumn(String columnName) {
        return (String)data.get(columnName);
    }
                                            
    /**
     * Add column data.
     *
     * @param columnName name of the column to hold data.
     * @param value
     */
    public synchronized void add(String columnName, String value) {
        data.put(columnName, value);
    }

    /**
     * Get list of column names.
     *
     * @return List of Strings.
     */
    public synchronized List getColumnNames() {
        List ret = new ArrayList();
        for (Iterator it=data.keySet().iterator(); it.hasNext();) {
            ret.add(it.next());
        }
        return ret;
    }

    /**
     * Compare this Row with other Row.<p>
     *
     * Rows are compared based on their "keyColumn". If the keyColumn
     * is not set, the order will be undefined.
     */
    public synchronized int compareTo(Object o) {
        Row other = (Row)o;
        if (keyColumn == null || data.get(keyColumn) == null)
            return -1;
        String key = (String)data.get(keyColumn);
        return key.compareTo(other.getColumn(keyColumn));
    }
    
	/**
	 * Representation of Row used for debugging.<p>
	 *
	 * Rows are compared based on their "keyColumn". If the keyColumn
	 * is not set, the order will be undefined.
	 */
	public synchronized String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Row {");
		for (Iterator it=data.entrySet().iterator(); it.hasNext();) {
			sb.append("\n  " + it.next());
		}
		sb.append("}");
		return sb.toString();
	}


}
