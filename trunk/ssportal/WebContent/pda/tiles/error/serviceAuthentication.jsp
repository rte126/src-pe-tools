<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.error"/></h4>

<h5>Service Authentication Error</h5>
<p>
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

