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

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.services"/></h4>
<logic:empty name="services">
<h5>Sorry... you are not subscribed to any currently available services.</h5>
</logic:empty>
<logic:notEmpty name="services">
  <tiles:insert page="/pda/tiles/servicetabs.jsp">
    <tiles:put name="action" value="services" />
  </tiles:insert>
  
  <p>Please select one of the available service categories.
</logic:notEmpty>
