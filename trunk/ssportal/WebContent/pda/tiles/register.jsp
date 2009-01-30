<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="register" focus="macAddress">
  <html:hidden property="userName" />
  <html:hidden property="password" />

  <table width="100%">
    <tr><td><h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.register"/></h4></td></tr>
  
    <tr><td>Please enter the MAC address (e.g. 03:3A:FE:98:3C:CB) and a 
      brief description of the device you want to register.</td></tr>

  
    <tr><td><h5>Mac address:</h5>
      <html:text property="macAddress" size="20"/></td></tr>
    <tr><td><h5>Description:</h5>
      <html:text property="description" size="20"/></td></tr>
    <tr><td>
      <html:submit property="ok" value="Register" />
      &nbsp;&nbsp;
      <html:submit property="cancel" value="Cancel" />
    </td></tr>
  </table>
</html:form>

<logic:notEmpty name="registration">
  <table width="100%">
    <logic:iterate id="element" name="registration">
      <tr><td><h5>Mac address:</h5></td>
        <td><bean:write name="element" property="key" /></td></tr>
      <tr><td><h5>Description:</h5></td>
        <td><bean:write name="element" property="value" /></td></tr>
    </logic:iterate>
  </table>
</logic:notEmpty>
