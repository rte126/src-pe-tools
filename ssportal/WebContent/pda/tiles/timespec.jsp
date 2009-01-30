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
  <th align="center" colspan="3" class="tableheading">Schedule</th>
</tr>

<%
  java.util.List orderST = new java.util.ArrayList();
	orderST.add("from");
	orderST.add("to");
  pageContext.setAttribute("orderST", orderST);
%>


<logic:iterate id="ordST" name="orderST" indexId="orderId">  
	<tr>
		<td colspan="2">
			<img src="images/horzline.gif" width="100%" height="1">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<h4><img src="images/blt-1arrow.gif">&nbsp;
			<bean:write name="ordST"/></h4>
		</td>
	</tr>
	<tr>
		<td><h5>Year:</h5></td>	  	  	
		<td>
			<html:text property='<%= "scheduledTime(" + ordST + "_year)" %>'
				size="6" maxlength="60"/>
		</td>
  </tr>
	<tr>			
		<td><h5>Month:</h5></td>
		<td>
			<html:text property='<%= "scheduledTime(" + ordST + "_month)" %>'
				size="6" maxlength="60"/>
		</td>
	</tr>
	<tr>			
		<td><h5>Day:</h5></td>
		<td>
			<html:text property='<%= "scheduledTime(" + ordST + "_day)" %>'
				size="6" maxlength="60"/>
		</td>
	</tr>
	<tr>			
		<td><h5>DOW:</h5></td>
		<td>
			<html:text property='<%= "scheduledTime(" + ordST + "_dow)" %>'
				size="6" maxlength="60"/>
		</td>
	</tr>
	<tr>			
		<td><h5>Hour:</h5></td>
		<td>
			<html:text property='<%= "scheduledTime(" + ordST + "_hour)" %>'
				size="6" maxlength="60"/>
		</td>
	</tr>
	<tr>			
			<td><h5>Minute:</h5></td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_minute)" %>'
					size="6" maxlength="60"/>
			</td>
	</tr>
	<tr>			
			<td><h5>TZ:</h5></td>
			<td>
				<html:text property='<%= "scheduledTime(" + ordST + "_tz)" %>'
					size="6" maxlength="60"/>
			</td>
	</tr>

</logic:iterate>



	  	</table>
	  	<!-- End Schedule table -->
		</td>
  </tr>
</table>
<!-- End border only table -->
