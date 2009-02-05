<%-- -*- html -*-
  Schedule Detail Tile.
  
  Format the "Schedule Detail" tile.

  used requestAttributes:
    elem - the element to do operations on, either view or modify.
    subAction - the selected action/operation for this tile
    subActions - List of  subaActions supported by this tile
    categories - List of tab titles
    category   - title of selected tab
  used tilesAttributes:
    <none>
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<html:form action="scheduleOperation">
  <!-- Define scripting variable based on 
         the value(s) of the specified bean property. -->
	<bean:define id="selected" name="subAction" type="java.lang.String" />
	<bean:define id="actionRows" name="actionRows" type="java.util.List" />
  <bean:define id="filter" name="filter" type="java.lang.String" />
  <bean:define id="rowSize" name="rowSize" type="java.lang.String" />

	<!-- Use form hidden types set from the 
	       scripting variablesDefine scripting variables. -->
  <html:hidden property="filter" value="<%=filter%>" />
  <html:hidden property="rowSize" value="<%=rowSize%>" />

	<!-- Begin border only table -->
	<table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
	  <tr>
			<td>


<!-- Start Main table -->
<table border=0 cellpadding=4 cellspacing=1 width="100%">		  		  
	<tr> 
	  <th align="center" colspan="2" class="tableheading">Main</th>
	</tr>
	<tr valign="center" class="tablerow">
	  <td><p>Name:</td>
	  <td>
			<html:text property='name' size="10" maxlength="10"/>
		</td>  
	</tr>	
	<tr  class="tablerow">
	  <td colspan=4 align=center class="tablecellbutton">
			<html:submit property="ok" value="Schedule" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<html:submit property="canel" value="Cancel" />
				&nbsp;
	  </td>
	</tr>
</table>
<!-- End Main table -->


			</td>
	  </tr>
	</table>
	<!-- End border only table -->

<!-- Create some space between tables -->
<br>

<tiles:insert page="/tiles/timespec.jsp">
</tiles:insert>

<!-- Create some space between tables -->
<br>

<tiles:insert page="/tiles/scheduleactions.jsp">
	<tiles:put name="actionRows" beanName="actionRows"/>
	<tiles:put name="rowSize" beanName="rowSize"/>
	<tiles:put name="operOptions" beanName="operOptions"/>
</tiles:insert>

</html:form>
