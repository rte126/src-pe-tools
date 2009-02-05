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

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr>
  <td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>
          <!-- Begin note -->
          <p class="welcome">Your current schedule is shown below.</p>
          <p class="rightpaneltext">
          You can add new events to your schedule, or delete scheduled
          events. You can also view the detail information about each
          of your scheduled events.
          </p>
        </td>
				<td><img src="images/pane/schedule.jpg" alt="" width="274" height="137"></td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td colspan="2">
          <!-- End note -->    

          <!-- Begin form -->
          <%
            String action = "schedules";
            pageContext.setAttribute("action", action);
          %>
          <bean:define id="filter" name="filter" type="java.lang.String" />  

          <bean:define id="selectedTab" name="filter" type="java.lang.String" />  
          <tiles:insert page="/tiles/basictabs.jsp">
            <tiles:put name="action" value="schedules"/>
            <tiles:put name="tabParam" value="filter"/>
            <tiles:put name="selectedTab" beanName="filter"/>
            <tiles:putList name="tabs">
              <tiles:add value="ThisMonth"/>
              <tiles:add value="EventList"/>
            </tiles:putList>
          </tiles:insert>

          <!-- Start border only table -->
          <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
            <tr>
              <td>
                <!-- Start list schedules table -->
                <table border=0 cellpadding=4 cellspacing=1 width="100%">
                  <!-- table row showing column headings -->
                  <tr> 
                    <th nowrap align="center" class="tableheading">Schedule Name</span></th>
                    <th align="center" class="tableheading">Action</th>
                  </tr>
                  <logic:empty name="scheduleIds">
                    <tr valign="center" class="tablerow">
                        <!-- table cell showing the schedule date and time -->
                        <td colspan=2 align="left" class="tablecell">
                          You have no schedules events for the given period.
                        </td>
                    </tr>
                  </logic:empty>
                  <logic:notEmpty name="scheduleIds">
                  <html:form action="scheduleOperation">
                    <html:hidden property="filter" />
                    <logic:iterate id="elem" name="scheduleIds" type="java.lang.String">
                      <!-- table row showing a schedule entry -->
                      <tr valign="center" class="tablerow">
                        <!-- table cell showing the schedule date and time -->
                        <td align="left" class="tablecell">
													<a href="schedules.do?filter=<%=filter%>&subAction=View&elem=<%=elem%>"
														title="View"><bean:write name="elem"/></a>
                        </td>
                        <!-- table cell showing the operator actions possible on a schedule -->                  
                        <td align="center" valign="middle" class="tablecell">
                          <a href="scheduleOperation.do?filter=<%=filter%>&subAction=Delete&elem=<%=elem%>"
                          	title="Delete">Delete</a>
                        </td>
                      </tr>
                    </logic:iterate>
                    </html:form>
                  </logic:notEmpty>
                </table>
                <!-- End list schedules table -->
              </td>
            </tr>
          </table>
          <!-- End border only table -->
          <br>
          <tiles:insert page="/tiles/scheduledetail.jsp">
            <tiles:put name="action" value="services" />
          </tiles:insert>
        </td>
      </tr>
    </table>
  </td></tr>
</table>

