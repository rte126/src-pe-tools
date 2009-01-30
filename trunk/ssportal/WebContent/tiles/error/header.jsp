<%-- -*- html -*-
   Header Tile.

   Format beginning of page.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<bean:define id="color" name="color" type="java.lang.String"/>

<!-- ------------------------------------Start top table--------------------------------------------- -->

<table border=0 cellpadding=0 cellspacing=0 width="100%">
  <tr>
    <td valign="bottom">
      <img src="<bean:write name="mainLogo"/>" alt=""></td>
    <td align=right>&nbsp;</td>
  </tr>
</table>
<table border=0 cellpadding=0 cellspacing=3 width="100%">
  <tr class="greeting">
    <td align=right>SSP Error Report&nbsp;&nbsp;</td>
  </tr>
  <tr>
    <td background="images/onepx/<%=color%>/colorB-pixel.gif" width=100%><img src="images/onepx/transp-pixel.gif" alt="" width=1 height="1"></td>
  </tr>
  <tr>
    <td>
      <!-- Start top right navigation Outline Table -->
      <table border=0 align="right" cellpadding=1 cellspacing=0 class="tableoutline">
        <tr>
          <td>
            <!-- Start table holding top right navigation elements -->
            <table border=0 cellpadding=0 cellspacing=0>
              <tr>
                <td height=20 nowrap class="topnav">&nbsp;<html:link action="index"><bean:message key="menu.index"/></html:link>&nbsp;</td>
                <td height=20 nowrap><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                <td height=20 nowrap class="topnavspacer"><img border=0 src="images/onepx/transp-pixel.gif" alt="" width=11></td>
                <td height=20 nowrap><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                <logic:present name="loginAction">
                  <bean:define id="loginAction" name="loginAction" type="java.lang.String"/>
                  <% String login_key = "menu."+loginAction; %>
                  <td height=20 nowrap class="topnav">
                    &nbsp;<html:link action="<%=loginAction%>">
                      <bean:message key="<%=login_key%>"/>
                    </html:link>&nbsp;
                  </td>
                  <td height=20 nowrap><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                  <td height=20 nowrap class="topnavspacer"><img border=0 src="images/onepx/transp-pixel.gif" alt="" width=11></td>
                </logic:present>
                <td height=20 nowrap><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                <td height=20 nowrap class="topnav">&nbsp;<a href="mailto:support@juniper.net">Contact us</a>&nbsp;</td>
                <td height=20 nowrap width=1><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                <td height=20 nowrap class="topnavspacer" width=11><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
              </tr>
            </table>
            <!-- End table holding top right navigation elements -->
          </td>
        </tr>
      </table>
      <!-- ------------------------------End top right navigation table------------------------------------ -->
    </td>
  </tr>
</table>
<!-- -------------------------------------End top tables------------------------------------------- -->
