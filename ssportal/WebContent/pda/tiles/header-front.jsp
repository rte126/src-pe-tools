<%-- -*- html -*-
   Main Header Tile.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<table width="100%">
<tr><td><img height=65 src="<bean:write name="logo"/>" width=115></td></tr>
<tr><td><img height=1 src="images/horzline.gif" width="100%"></td></tr>
<tr><td>
  <logic:present name="loginAction">
    <bean:define id="loginAction" name="loginAction" type="java.lang.String"/>
    <% String login_key = "menu."+loginAction; %>
    <img src="images/blt-<%=loginAction%>.gif">&nbsp;<html:link action="<%=loginAction%>"><bean:message key="<%=login_key%>"/></html:link>&nbsp;
  </logic:present>
<img src="images/blt-contact.gif">&nbsp;<a href="mailto:support@juniper.net">Contact us</a></td>
</tr>
</table>
