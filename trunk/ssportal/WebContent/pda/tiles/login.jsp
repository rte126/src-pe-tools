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

<html:form action="login" focus="userName">
<table width="100%">
<tr><td><h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.login"/></h4></td></tr>
<tr><td><h4>Please enter your SSP Username and Password.</h4></td></tr>
<tr><td>Username:<html:text property="userName"  size="20"/></td></tr>
<tr><td>Password:<html:password property="password" size="20"/></td></tr>
<tr><td>
<logic:equal name="allowPersistent" value="true">
<html:checkbox property="persistent"/>Don't ask me again.
</logic:equal>
<html:submit value="Login"/></td></tr>
<tr><td><h5>Not registered yet? <a href="">Sign up now!</a></h5></td></tr>
</table>
</html:form>