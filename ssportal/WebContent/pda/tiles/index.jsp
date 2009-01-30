<%-- -*- html -*-
   Index Tile.

   Format body of frontpage.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.index"/></h4>
<logic:present name="userInfo" property="info(authDeniedReasons)">
  <h3>Login Denied</h3>
  <p>Sorry, but your login was denied for the following reason(s):<br>
  <logic:iterate id="reason" name="userInfo" property="info(authDeniedReasons).iter">
    <bean:write name="reason"/><br>
  </logic:iterate>
</logic:present>
<h4>Welcome to Juniper Networks' Service Selection Portal!</h4>

<p>This demonstration portal is just one example of how the 
functionality of the SDX-300 Service Deployment System can be 
presented to subscribers.