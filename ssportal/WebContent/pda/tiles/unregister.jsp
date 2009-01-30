<%-- -*- html -*-
  Equipment Unregistration Tile.

  Format body of Unregister page.
  
  Input attributes:
    userInfo   - UserInfo bean
    registration   - list of all already registered equipment
  Form:
    registerForm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.unregister"/></h4>
<logic:empty name="registration">
  <h4>Sorry... no equipment has been registered with the username <bean:write name="userInfo" property="name"/>.</h4
</logic:empty>

<logic:notEmpty name="registration">
  <html:form action="unregister">
    <html:hidden property="userName" />
    <html:hidden property="password" />
    <table width="100%">
      <logic:iterate id="element" name="registration">
        <tr><td colspan="2"><img src="images/horzline.gif" width="100%" height="1"></td></tr>

        <tr><td height="16"><h5>Mac address:</h5></td></tr>
        <tr><td><bean:write name="element" property="key" /></td></tr>
        
        <tr><td><h5>Description:</h5></td></tr>
        <tr><td><bean:write name="element" property="value" /></td></tr>

        <bean:define id="macAddress" name="element"
                     property="key"
                     type="java.lang.String" />
        <% String register="register("+macAddress+")"; %>
        <tr><td><html:checkbox property="<%=register%>">&nbsp;Unregister</html:checkbox></td></tr>
      </logic:iterate>
      <tr><td>
        <html:submit property="ok" value="Unregister" />
        &nbsp;&nbsp;
        <html:submit property="cancel" value="Cancel" />
      </td></tr>
    </table>
  </html:form>
</logic:notEmpty>
