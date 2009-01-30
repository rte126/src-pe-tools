<%-- -*- html -*-
  Services Tile.

  Format body of Services page.

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

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<h4><img src="images/blt-square.gif">&nbsp;<html:link action="subscribe"><bean:message key="page.subscribe"/></html:link>&nbsp;&nbsp;
<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="category"/></h4>

<p>It may take a minute for your new subscriptions to take effect.</p>

<table width="100%">
  <html:form action="subscribe">
    <html:hidden property="category" />
    <logic:iterate id="row" name="services">
      <tr><td colspan="2"><img src="images/horzline.gif" width="100%" height="1"></td></tr>
      <tr><td colspan="2"><h4><img src="images/blt-1arrow.gif">&nbsp;
        <bean:write name="row" property="column(service.serviceName)" />
      </td></tr>
      <bean:define id="serviceName" name="row"
                   property="column(service.serviceName)"
                   type="java.lang.String" />
      <% String subscribe="subscribe("+serviceName+")"; %>
      <tr><td><h5>Subscribe:</h5></td>
        <td><html:radio property="<%=subscribe%>" value="true"/></td>
      </tr>
      
      <tr><td><h5>Unsubscribe:</h5></td>
        <td><html:radio property="<%=subscribe%>" value="false"/></td>
      </tr>
    </logic:iterate>
    <tr><td colspan="2">
      <html:submit property="ok" value="OK" />
      &nbsp;&nbsp;
      <html:submit property="cancel" value="Cancel" />
    </td></tr>
  </html:form>
</table>

