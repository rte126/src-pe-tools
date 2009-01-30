<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
     <table width="100%" border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
      <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td valign="top">
          <p class="welcome">Unrecognized Service</p>

          <p class="rightpaneltext">You requested an unrecognized service: "<bean:write name="message"/>".
        </td>
        <td><div align="left"><img src="images/pane/error01.jpg" alt="" width="90" height="215"></div></td>
      </tr>
      <tr><td colspan="3">
        <jsp:include page="stacktrace.jsp" /></td>
      </tr>
    </table>
  </td></tr>
</table>
