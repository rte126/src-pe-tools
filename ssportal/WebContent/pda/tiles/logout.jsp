<%-- -*- html -*-
   Logout Tile.

   Input Form asking for confirmation of logout.

   FormBean:
     logoutForm
   Attributes:

--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:form action="logout">
<table>
<tr><td><h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.logout"/></h4></td></tr>
<tr><td><h5>Logging out will deactivate all of your services.</h5></td></tr>
<tr><td><html:checkbox property="persistent" />Unregister my PDA.</td></tr>
<tr><td><html:submit property="logout" value="Logout" />
<html:submit property="cancel" value="Cancel" /></td></tr>
</table>
</html:form>