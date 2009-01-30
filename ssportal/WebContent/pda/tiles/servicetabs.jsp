<%-- -*- html -*-
  Service Category Tile.
  
  Format the "Service Category Tab" tile as a header of services tables.

  used requestAttributes:
    categories - List of tab titles
    category   - title of selected tab
  used tilesAttributes:
    action     - name of surrounding action
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="action" classname="java.lang.String" />

<table>
  <logic:iterate id="cat" name="categories" indexId="categoryId">
    <% pageContext.setAttribute("newRow", (categoryId.intValue() % 2 == 0)?"true":"false"); %>
    <logic:equal name="newRow" value="true"><tr></logic:equal>
    <td><img src="images/blt-2arrow.gif">&nbsp;<html:link action="<%=action%>" paramId="category" paramName="cat">
      <bean:write name="cat"/>
    </html:link></td>
    <logic:equal name="newRow" value="false"></tr></logic:equal>
  </logic:iterate>
</table>
