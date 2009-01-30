<%-- -*- html -*-
   Main Header Tile.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<jsp:useBean id="userInfo" scope="request"
             class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%">
<tr>
<td width="65" valign=top><img src="<bean:write name="logo"/>" width="65"></td>
<td><img src="images/blt-home.gif">&nbsp;<html:link action="index"><bean:message key="menu.index"/></html:link><br />
<logic:present name="loginAction">
  <bean:define id="loginAction" name="loginAction" type="java.lang.String"/>
  <% String login_key = "menu."+loginAction; %>
    <img src="images/blt-<%=loginAction%>.gif">&nbsp;<html:link action="<%=loginAction%>">
      <bean:message key="<%=login_key%>"/>
    </html:link>&nbsp;
</logic:present>
<br />
<img src="images/blt-contact.gif">&nbsp;<a href="mailto:support@juniper.net">Contact us</a></td>
</tr>
<tr>
<td colspan="2"><img height=1 src="images/horzline.gif" width="100%"></td></tr>
<logic:notEmpty name="userInfo" property="name">
<tr><td colspan="2" align="right">Hello <bean:write name="userInfo" property="name"/></td></tr>
</logic:notEmpty>
</table>
