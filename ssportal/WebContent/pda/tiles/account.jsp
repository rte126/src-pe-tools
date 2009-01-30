<%-- -*- html -*-
  Account Tile.

  Format body of Account page.
  
  Input attributes:
    userInfo   - UserInfo bean
    category   - selected service category
    categories - list of all available service categories
    services   - list of service data
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<h4><img src="images/blt-square.gif">&nbsp;<html:link action="account"><bean:message key="page.account"/></html:link>&nbsp;&nbsp;
<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="category"/></h4>

<p>To have a service automatically activated every time you log in, select "automatic" for 
that service and press the "Update" button.

<table width="100%">
  <html:form action="account">
    <html:hidden property="category" />
    <logic:iterate id="row" name="services">
      <tr><td colspan="2"><img src="images/horzline.gif" width="100%" height="1"></td></tr>
      <tr><td colspan="2"><h4><img src="images/blt-1arrow.gif">&nbsp;
        <bean:write name="row" property="column(service.descriptionOrServiceName)" />
        <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
          (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
        </logic:notEmpty>:</h4>
      </td></tr>
      
      <bean:define id="subscriptionName" name="row"
                   property="column(subscription.subscriptionName)"
                   type="java.lang.String" />
      <% String trigger="trigger("+subscriptionName+")"; %>

      <tr><td>Manual:</td>
        <td><html:radio property="<%=trigger%>" value="MANUAL" />
      </td></tr>
      <tr><td>Automatic:</td>
        <td><html:radio property="<%=trigger%>" value="ACTIVATE_ON_LOGIN" />
      </td></tr>
    </logic:iterate>
    <tr><td colspan="2">
      <html:submit property="ok" value="Update" />
      &nbsp;&nbsp;
      <html:submit property="cancel" value="Cancel" />
    </td></tr>
  </html:form>
</table>
