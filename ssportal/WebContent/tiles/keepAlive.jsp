<%-- -*- html -*-
   Index Tile.

   Format body of keepAlive page.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Begin front page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
     <table width="100%" border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
        <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>
          <p class="welcome">Service Selection Portal Keep Alive page</p>
          <p class="rightpaneltext">
          Please do not close this window.
          It will refresh itself for the user session
          keep alive aplication.
          <p class="rightpaneltext">
          To access the portal, please open a new window.
          <p class="rightpaneltext">
        </td>
      </tr>
    </table>
  </td></tr>
</table>
<!-- End front page -->