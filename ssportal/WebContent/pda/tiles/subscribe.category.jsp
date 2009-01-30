<%-- -*- html -*-
  Service Categories Tile.

  Input attributes:
    userInfo   - UserInfo bean
    categories - list of all available service categories
    services   - list of service data
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.subscribe"/></h4>
<logic:empty name="services">
<h4>Sorry... you are not subscribed to any currently available services.</h4>
</logic:empty>
<logic:notEmpty name="services">
  <logic:equal name="userInfo" property="info(anonymous)" value="true">
    <h4>You are not permitted to modify your shared user profile.</h4>
  </logic:equal>

  <logic:notEqual name="userInfo" property="info(anonymous)" value="true">

    <tiles:insert page="/pda/tiles/servicetabs.jsp">
      <tiles:put name="action" value="subscribe" />
    </tiles:insert>
  </logic:notEqual>
  
  <p>Please select one of the available service categories.
</logic:notEmpty>
