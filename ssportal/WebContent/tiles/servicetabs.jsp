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

<bean:define id="selected" name="category" type="java.lang.String" />
<tiles:useAttribute name="action" classname="java.lang.String" />

<!-- ------------------------------Start Tabbed Selection Navigation--------------------------------- -->
<!-- ------------------------------Start Tabbed Selection Outline table------------------------------------ -->
<table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
  <tr>
    <td>
      <!-- -------------------------Start Tabbed Selection table------------------------------ -->
      <table border=0 cellpadding=0 cellspacing=0>
        <tr>
          <logic:iterate id="cat" name="categories" indexId="categoryId">
            <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
            <logic:equal name="cat" value="<%=selected%>">
              <td class="tabselected" height=20>&nbsp;<bean:write name="cat"/>&nbsp;</td>
            </logic:equal>
            <logic:notEqual name="cat" value="<%=selected%>">
              <td class="tabunselected" height=20>&nbsp;
                <html:link action="<%=action%>" paramId="category" paramName="cat">
                  <bean:write name="cat"/>
                </html:link>
                &nbsp;
              </td>
            </logic:notEqual>
          </logic:iterate>
        </tr>
      </table>
      <!-- ---------------------------------End Tabbed Selection table---------------------------- --> 
    </td>
  </tr>
  <tr>
    <td width="100%" height="1" tab_style="tabselected"><img src="images/onepx/transp-pixel.gif" alt="" height="1" width="100%" border=0></td>
  </tr>
</table>
<!-- ---------------------------------End Tabbed Selection Outline table---------------------------- --> 
