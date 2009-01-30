<%-- -*- html -*-
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<bean:parameter id="url" name="url" />
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
     <table width="100%" border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
       <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
         <td valign="top">
          <p class="welcome">Service Activation Required</p>
          <p class="rightpaneltext">
          You attempted to access a controlled network resource at
          <b><bean:write name="url"/></b>.

          <p class="rightpaneltext">
          To gain access, make sure you have logged in and have activated the
          service that provides access to this resource.  You can activate your
          subscribed services on the
          <html:link action="services">Services</html:link> page.
        </td>
        <td><div align="left"><img src="images/pane/error01.jpg" alt="" width="90" height="215"></div></td>      
      </tr>
    </table>
  </td></tr>
</table>
