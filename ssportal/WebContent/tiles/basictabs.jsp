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
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<tiles:useAttribute name="action" classname="java.lang.String" />
<tiles:useAttribute name="tabParam" classname="java.lang.String" />
<tiles:useAttribute name="selectedTab" classname="java.lang.String" />
<tiles:useAttribute name="tabs" classname="java.util.List" />

<table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
  <tr>
    <td>
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>
          <logic:iterate id="tab" name="tabs" indexId="tabId">
            <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
            <logic:equal name="tab" value="<%=selectedTab%>">
              <td class="tabselected" height=20>&nbsp;<bean:write name="tab"/></td>
            </logic:equal>
            <logic:notEqual name="tab" value="<%=selectedTab%>">
              <td class="tabunselected" height=20>&nbsp;
                <html:link action="<%=action%>" paramId="<%=tabParam%>" paramName="tab">
                  <bean:write name="tab"/>
                </html:link>
              </td>
            </logic:notEqual>
          </logic:iterate>
        </tr>
      </table>
    </td>
  </tr>
  <tr>
    <td width="100%" height="1" tab_style="tabselected"><img src="images/onepx/transp-pixel.gif" alt="" height="1" width="100%" border=0></td>
  </tr>
</table>
  