<%-- -*- html -*-
   Menu Tile.

   Format menu bar.

   Input attributes:
     menuItems - list MenuItem objects (pair of action/title)
     menuTag   - action of current page
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<table>
  <tr><td><h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.index"/></h4></td></tr>
  <logic:iterate id="item" name="menuItems" type="java.lang.String" indexId="column">
    <% String menu_item = "menu."+item; %>
    <% pageContext.setAttribute("newRow", (column.intValue() % 2 == 0)?"true":"false"); %>
    <logic:equal name="newRow" value="true"><tr></logic:equal>
      <td><img src="images/blt-arrow.gif">&nbsp;<html:link action="<%=item%>"><bean:message key="<%=menu_item%>"/></html:link></td>
    <logic:equal name="newRow" value="false"></tr></logic:equal>
  </logic:iterate>
</table>
