<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.error"/></h4>

<h5>Unrecognized Subscription</h5>
<p>
You requested an unrecognized subscription: "<bean:write name="message"/>".
