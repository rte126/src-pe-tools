<%-- -*- html -*-
  Basic Tab Tile.
  
  Format the "Basic Tab" tile as a header of tabbed tables.

  used requestAttributes:
    <none>
  used tilesAttributes:
    action      - name of surrounding action
    tabParam    - name of tab param for changing selected tabl
    tabstab     - List of tab titles
    selectedTab - title of selected tab    
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<tiles:useAttribute name="action" classname="java.lang.String" />
<tiles:useAttribute name="tabParam" classname="java.lang.String" />
<tiles:useAttribute name="selectedTab" classname="java.lang.String" />
<tiles:useAttribute name="tabs" classname="java.util.List" />
  
<tiles:useAttribute name="action" classname="java.lang.String" />

<table>
  <logic:iterate id="tab" name="tabs" indexId="tabId">
    <% pageContext.setAttribute("newRow", 
      (tabId.intValue() % 2 == 0)?"true":"false"); %>
    <logic:equal name="newRow" value="true"><tr></logic:equal>
    <td><img src="images/blt-2arrow.gif">&nbsp;<html:link action="<%=action%>" paramId="<%=tabParam%>" paramName="tab">
      <bean:write name="tab"/>
    </html:link></td>
    <logic:equal name="newRow" value="false"></tr></logic:equal>
  </logic:iterate>
</table>

