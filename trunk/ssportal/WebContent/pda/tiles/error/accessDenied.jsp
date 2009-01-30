<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.error"/></h4>

<h5>Service Activation Required</h5>
<p>You attempted to access a controlled network resource at
<b><bean:write name="url"/></b>.

<p>
To gain access, make sure you have logged in and have activated the
service that provides access to this resource.  You can activate your
subscribed services on the
<html:link action="services">Services</html:link> page.
