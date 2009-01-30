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

<h4><img src="images/blt-square.gif">&nbsp;<html:link action="usage"><bean:message key="page.usage"/></html:link>&nbsp;&nbsp;
<img src="images/blt-2arrow.gif">&nbsp;<bean:write name="category"/></h4>
<table width="100%">
  <logic:iterate id="row" name="usage">
    <tr><td colspan="2"><img src="images/horzline.gif" width="100%" height="1"></td></tr>
    <tr><td colspan="2"><h4><img src="images/blt-1arrow.gif">&nbsp;
      <bean:write name="row" property="column(service.descriptionOrServiceName)" />
      <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
        (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
      </logic:notEmpty>
    </h4></td></tr>

    <tr><td><h5>Status:</h5></td><td>
      <logic:equal name="row" property="column(subscription.active)" value="true">
        <img src="images/pane/activ_green-small.gif" border=0
             alt="active">
      </logic:equal>
      <logic:notEqual name="row" property="column(subscription.active)" value="true">
        <img src="images/pane/activ_red-small.gif" border=0
             alt="inactive">
      </logic:notEqual>
    </td></tr>

    <tr><td><h5>Active for:</h5></td>
      <td><bean:define id="durationActive" name="row"
                       property="column(subscription.durationActive)"
                       type="java.lang.String"/>
        <% int l = durationActive.length();
          String d_sec = "0";
          if (l > 3)
              d_sec = durationActive.substring(0, l-3); %>
        <%=d_sec%> sec
      </td></tr>
    <tr><td><h5>Time:</h5></td>
      <td>
        <logic:notEmpty name="row" property="column(subscription.usage.collectionTime)">
          <bean:write name="row" property="column(subscription.usage.collectionTime)" />
        </logic:notEmpty>
        <logic:empty name="row" property="column(subscription.usage.collectionTime)">
          Never
        </logic:empty>
      </td></tr>
    <tr><td><h5>Bytes out:</h5></td>
      <td>
        <bean:write name="row" property="column(subscription.usage.bytesFromUser)" />
      </td></tr>
    <tr><td><h5>Bytes in:</h5></td>
      <td>
        <bean:write name="row" property="column(subscription.usage.bytesToUser)" />     
      </td></tr>
    <tr><td><h5>Packets out:</h5></td>
      <td>
        <bean:write name="row" property="column(subscription.usage.packetsFromUser)" />
      </td></tr>
    <tr><td><h5>Packets in:</h5></td>
      <td>
        <bean:write name="row" property="column(subscription.usage.packetsToUser)" />
      </td></tr>
    </logic:iterate>
</table>