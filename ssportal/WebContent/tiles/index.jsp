<%-- -*- html -*-
   Index Tile.

   Format body of frontpage.
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- Begin front page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr><td>
        <table border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
          <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
            <td>

              <logic:present name="userInfo" property="info(authDeniedReasons)">
                <p class="welcome">
                Login Denied
                
                <p class="welcomemessage">
                Sorry, but your login was denied for the following reason(s):<br>
                <logic:iterate id="reason" name="userInfo" property="info(authDeniedReasons).iter">
                  <bean:write name="reason"/><br>
                </logic:iterate>
              </logic:present>

              <br>
              <p class="welcome">
              Welcome to Juniper Networks' Service Selection Portal!

              <p class="welcomemessage">
              This demonstration portal is just <em>one example</em> of
              how the functionality of the SDX-300 Service Deployment System
              can be presented to subscribers.
          
              <p class="welcomemessage">
              You can browse the Javadoc <a href="javadoc">documentation</a> of the
              demo portal.

            </td>
          </tr>
        </table>
      </td>
      <td><div align="left"><img src="images/pane/welcome.jpg" alt="" width="138" height="283"></div></td></tr>
    </table>
  </td></tr>
</table>

 <!-- End front page -->
