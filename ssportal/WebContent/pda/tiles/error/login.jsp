<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.error"/></h4>

<h5>Login Failed</h5>

<p>
Your attempt to log in or to manipulate a persistent login failed:
"<bean:write name="message" />".  Please try again.
