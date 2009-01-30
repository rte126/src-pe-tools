<%-- -*- html -*-
  Scheduled Time containing two Time Specs.
  
  Format the Scheduled Time which contains two row 
    of Time Specs
  
  used html properties
    scheduledTime - 
  used requestAttributes:
    <none>
  used tilesAttributes:
    <none>

--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!-- Begin border only table -->
<table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
  <tr>
		<td>
	  	<!-- Start Schedule table -->
	  	<table border=0 cellpadding=4 cellspacing=1 width="100%">
				


<!-- table row showing column headings -->
<tr> 
  <th align="center" colspan="9" class="tableheading">Schedule</th>
</tr>

<%
  java.util.List orderST = new java.util.ArrayList();
	orderST.add("from");
	orderST.add("to");
  pageContext.setAttribute("orderST", orderST);
%>

<logic:iterate id="ordST" name="orderST" indexId="orderId">  
	<tr valign="center" class="tablerow">
		<tr valign="center" class="tablerow">
			<td ROWSPAN="2" ><bean:write name="ordST"/></td>
			<td><p>Year:</td>	  	  	
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_year)" %>'
					size="6" maxlength="60"/>
			</td>
			<td><p>Month:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_month)" %>'
					size="6" maxlength="60"/>
			</td>
			<td><p>Day:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_day)" %>'
					size="6" maxlength="60"/>
			</td>
			<td><p>DOW:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_dow)" %>'
					size="6" maxlength="60"/>
			</td>
		</tr>    
		<tr valign="center" class="tablerow">
			<td> <p>Hour:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_hour)" %>'
					size="6" maxlength="60"/>
			</td>
			<td> <p>Minute:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_minute)" %>'
					size="6" maxlength="60"/>
			</td>
			<td COLSPAN="2">&nbsp;</td>
			<td> <p>TZ:</td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_tz)" %>'
					size="6" maxlength="60"/>
			</td>
		</tr>    
	</tr>
</logic:iterate>




	  	</table>
	  	<!-- End Schedule table -->
		</td>
  </tr>
</table>
<!-- End border only table -->
