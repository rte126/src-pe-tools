<%-- -*- html -*-
   Index Tile.

   Format body of wait page.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<h4><img src="images/blt-square.gif">&nbsp;<bean:message key="page.wait"/></h4>

<h4>Service Selection Portal wait page</h4>

<p>
The IP address of your machine is being changed.  this
may take a minute.  If your browser fails to reload this
page (<em>i.e.</em> after you have lost your current IP
address but before you get your new IP address), try the
browser's refresh button.  If you continue to see this
page for a long time, please reboot your machine.
<p>
Technical note: The SSP system is waiting for your DHCP
lease to expire.  After it expires, and after your
machine issues a new DHCP discover (not a DHCP renew!),
you will receive your new IP address.
