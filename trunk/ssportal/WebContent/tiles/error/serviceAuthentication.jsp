<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
     <table width="100%" border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
      <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td valign="top">
          <p class="welcome">Service Authentication Error</p>
          <p class="rightpaneltext">

          <bean:define id="reason"      name="reason"      type="java.lang.String"/>
          <bean:define id="message"     name="message"     type="java.lang.String"/>
          <bean:define id="userIp"      name="userIp"      type="java.lang.String"/>
          <bean:define id="serviceName" name="serviceName" type="java.lang.String"/>
          <bean:define id="sessionName" name="sessionName" type="java.lang.String"/>

          <% String key = "error.saet."+reason; %>

          <bean:message key="<%=key%>"
                        arg0="<%=message%>"
                        arg1="<%=userIp%>"
                        arg2="<%=serviceName%>"
                        arg3="<%=sessionName%>" />
        </td>
        <td><div align="left"><img src="images/pane/error01.jpg" alt="" width="90" height="215"></div></td>
      </tr>
      <tr><td colspan="3">
        <jsp:include page="stacktrace.jsp" />
      </td></tr>
    </table>
  </td></tr>
</table>
