<%-- -*- html -*-
   Index Tile.

   Format body of wait page.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Begin front page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
     <table border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
        <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>
          <p class="welcome">Service Selection Portal wait page</p>
          <p class="rightpaneltext">
          The IP address of your machine is being changed.  this
          may take a minute.  If your browser fails to reload this
          page (<em>i.e.</em> after you have lost your current IP
          address but before you get your new IP address), try the
          browser's refresh button.  If you continue to see this
          page for a long time, please reboot your machine.
          <p class="rightpaneltext">
          Technical note: The SSP system is waiting for your DHCP
          lease to expire.  After it expires, and after your
          machine issues a new DHCP discover (not a DHCP renew!),
          you will receive your new IP address.
        </td>
      </tr>
    </table>
  </td></tr>
</table>
<!-- End front page -->
