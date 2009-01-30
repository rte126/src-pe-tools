<%-- -*- html -*-
  Schedules Tile.

  Format body of Schedules page.
  
  Input attributes:
    userInfo   - UserInfo bean
    category   - selected service category
    categories - list of all available service categories
    services   - list of service data
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<!-- Begin schedules page -->


<bean:define id="filter" name="filter" type="java.lang.String" />  
<bean:define id="subActionr" name="subAction" type="java.lang.String" />  

<logic:equal name="filter" value="NotSelected">
	<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.schedules"/></h4>

	<%
  	String action = "schedules";
	  pageContext.setAttribute("action", action);
	%>

	<bean:define id="filter" name="filter" type="java.lang.String" />  
	<bean:define id="selectedTab" name="filter" type="java.lang.String" />  
	<tiles:insert page="/pda/tiles/basictabs.jsp">
	  <tiles:put name="action" value="schedules"/>
  	<tiles:put name="tabParam" value="filter"/>
	  <tiles:put name="selectedTab" beanName="filter"/>
  	<tiles:putList name="tabs">
    	<tiles:add value="ThisMonth"/>
	    <tiles:add value="EventList"/>
  	  <tiles:add value="NewSchedule"/>
	  </tiles:putList>
	</tiles:insert>

	<p>Please select one of the available time periods to view, 
	  or create new schedule.
</logic:equal>

<logic:equal name="subAction" value="View">
	<h4><img src="images/blt-square.gif">&nbsp;<html:link action="schedules"><bean:message key="page.schedules"/></html:link>&nbsp;&nbsp;
	<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="subAction"/></h4>
	<tiles:insert page="/pda/tiles/scheduledetail.jsp">
  	<tiles:put name="action" value="services" />
	</tiles:insert>
</logic:equal>

<logic:notEqual name="subAction" value="View">
	<%
  	String periodFilter = String.valueOf("ThisMonth".equals(filter) || "EventList".equals(filter));
	  pageContext.setAttribute("periodFilter", periodFilter);
	%>
	<logic:equal name="periodFilter" value="true">
		<h4><img src="images/blt-square.gif">&nbsp;<html:link action="schedules"><bean:message key="page.schedules"/></html:link>&nbsp;&nbsp;
		<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="filter"/></h4>
  	<tiles:insert page="/pda/tiles/schedules.list.jsp">
	  </tiles:insert>  
	</logic:equal>
</logic:notEqual>

<logic:equal name="filter" value="NewSchedule">
  <h4><img src="images/blt-square.gif">&nbsp;<html:link action="schedules"><bean:message key="page.schedules"/></html:link>&nbsp;&nbsp;
  <img src="images/blt-2arrow.gif">&nbsp;New</h4>
    
  <tiles:insert page="/pda/tiles/scheduledetail.jsp">
    <tiles:put name="action" value="services" />
  </tiles:insert>
    
  <p>Please select a name for the new schedule.
</logic:equal>
