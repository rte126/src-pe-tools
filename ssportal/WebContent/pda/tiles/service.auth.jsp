<%-- -*- html -*-
   Login Tile.

   Input Form asking for user credentials.

   FormBean:
     LoginForm
   Attributes:
     allowPersistent
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.services"/></h4>

<logic:notEmpty name="message">
  <tr><td colspan="2">
  <h4>Service Activation Failed</h4>
  <p><bean:write name="message" />
  </td></tr>
</logic:notEmpty>

<logic:empty name="message">
  <p>The service you want to activate requires a service-specific username
and password.
</logic:empty>

<html:form action="activate" focus="userName">
  <html:hidden property="category" />
  <html:hidden property="subscription" />
  <html:hidden property="serviceType" />
  <table>
    <tr><td nowrap><h4><img src="images/blt-2arrow.gif">Service Authentication</h4></td></tr>
    <tr><td><h5>Please enter your Username and Password for service "<%= request.getParameter("subscription") %>".</h5></td></tr>
    <tr><td>Username:<html:text property="userName" size="20"/></td></tr>
    <tr><td>Password:<html:password property="password" size="20"/></td></tr>
    <tr><td><html:submit value="Activate" />&nbsp;&nbsp; 
            <html:submit property="cancel" value="Cancel" /></td></tr>
  </table>
</html:form>
