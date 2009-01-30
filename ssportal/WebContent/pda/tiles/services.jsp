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

<h4><img src="images/blt-square.gif">&nbsp;<html:link action="services"><bean:message key="page.services"/></html:link>&nbsp;&nbsp;
<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="category"/></h4>

You can activate/deactivate services by pressing the red
(<img src="images/pane/activ_red-small.gif" border="0">)
or green
(<img src="images/pane/activ_green-small.gif" border="0">)
circle.

<table width="100%">
  <logic:iterate id="row" name="services">
    <bean:define id="subscriptionName"
                 name="row"
                 property="column(subscription.subscriptionName)"
                 type="java.lang.String"/>
    <bean:define id="activateOnly"
                 name="row"
                 property="column(service.activateOnly)"
                 type="java.lang.String"/>
    <bean:define id="authReq"
                 name="row"
                 property="column(service.authenticationRequired)"
                 type="java.lang.String"/>
    <bean:define id="serviceType"
                 name="row"
                 property="column(service.type)"
                 type="java.lang.String"/>
    <bean:define id="isActive"
                 name="row"
                 property="column(subscription.active)"/>
    <bean:define id="ispServiceActive"
                 name="userInfo"
                 property="info(ispServiceActive).value[0]"/>
    <%
      String action = "services";
      if (!("ISP".equals(serviceType) &&
            "true".equals(ispServiceActive) &&
            !"true".equals(isActive))) {
          if ("true".equals(isActive)) {
            action = "deactivate";
          }
          else {
            action = "activate";
          }
      }
      pageContext.setAttribute("action", action);
    %>
    <html:form action="<%=action%>">
      <html:hidden property="category" />
      <html:hidden property="subscription" value="<%=subscriptionName%>"/>
      <html:hidden property="activateOnly" value="<%=activateOnly%>"/>
      <html:hidden property="authReq" value="<%=authReq%>"/>
      <html:hidden property="serviceType" value="<%=serviceType%>"/>

      <tr><td colspan="2"><img src="images/horzline.gif" width="100%" height="1"></td></tr>
      <tr><td colspan="2"><h4><img src="images/blt-1arrow.gif">&nbsp;
        <logic:equal name="row" property="column(subscription.active)" value="true">
          <logic:notEmpty name="row" property="column(service.url)">
            <a href="<bean:write name="row" property="column(service.url)"/>">
              <bean:write name="row" property="column(service.descriptionOrServiceName)" />
              <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
              </logic:notEmpty>
            </a>
          </logic:notEmpty>
          <logic:empty name="row" property="column(service.url)">
            <bean:write name="row" property="column(service.descriptionOrServiceName)" />
            <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
              (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
            </logic:notEmpty>
          </logic:empty>
        </logic:equal>
        <logic:notEqual name="row" property="column(subscription.active)" value="true">
          <bean:write name="row" property="column(service.descriptionOrServiceName)" />
          <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
            (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
          </logic:notEmpty>
        </logic:notEqual>
      </h4></td></tr>

      <tr><td><h5>Status:</h5></td>
        <td>
          <logic:equal name="action" value="activate">
            <html:image src="images/pane/activ_red-small.gif" border="0" alt="inactive"/>
          </logic:equal>
          <logic:equal name="action" value="deactivate">
            <html:image src="images/pane/activ_green-small.gif" border="0" alt="active"/>
          </logic:equal>
        </td>
      </tr>

      <tr><td nowrap><h5>Password required:</h5></td>
        <td>
          <logic:equal name="row" property="column(service.authenticationRequired)" value="true">
            Yes
          </logic:equal>
          <logic:notEqual name="row" property="column(service.authenticationRequired)" value="true">
            No
          </logic:notEqual>
        </td>
      </tr>

      <tr><td nowrap><h5>Persistent:</h5></td>
        <td>
          <% String persistent="persistent("+subscriptionName+")"; %>
          <logic:equal name="row" property="column(service.authenticationRequired)" value="false">
            <html:checkbox property="<%=persistent%>" value="true"/>
          </logic:equal>
          <% String persistentInitial="persistentInitial("+subscriptionName+")"; %>
          <html:hidden property="<%=persistentInitial%>" />          
        </td>
      </tr>
      
    </html:form>
  </logic:iterate>
</table>
