<%-- -*- html -*-
  Equipment Registration Credentials Tile.
  
  Input Form asking for equipment registration authentication credentials

  FormBean:
    RegisterForm
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.register"/></h4>

<p>You may register your DHCP equipment so that it always obtains a public IP address. 
The first step is to supply the credentials that will authorize your equipment to 
receive a public IP address.</p>

<html:form action="register" focus="userName">
  <table width="100%">
    <tr><td><h5><img src="images/blt-2arrow.gif"> Equipment Credentials</h5></td></tr>
    <tr><td><h5>Please enter your Username and Password for Equipment Registration.</h5></td></tr>
    <tr><td>Username:<html:text property="userName"  size="20"/></td></tr>
    <tr><td>Password:<html:password property="password"  size="20"/></td></tr>
    <tr><td><html:submit property="cont" value="Continue" /></td></tr>
  </table>
</html:form>