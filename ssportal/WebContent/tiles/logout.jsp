<%-- -*- html -*-
   Logout Tile.

   Input Form asking for confirmation of logout.

   FormBean:
     logoutForm
   Attributes:

--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<%--
      Check "Unregister my PC" if you want to unregister
      your PC.  If you do so, your PC will no longer be given a 
      public IP address, and will be be unable to connect to the internet.
--%>

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td><br>		 
          <!-- Begin form -->
          <html:form action="logout">
          <!-- --------------------------------Start Logout Outline Table------------------------------------- -->
            <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
              <tr><td>
                <!-- ---------------------------Start Logout Table--------------------------- -->

                <table width="100%" border=0 cellpadding=0 cellspacing=0>
                  <tr><td colspan="2" bgcolor="#FFFFFF">
                    <p class="dialogh1">Logging out will deactivate all of your services.</p>
                  </td></tr>
                  <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="1" height="1"></td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>

                  <logic:equal name="canRegister" value="true">
                    <tr class="logtablerow">
                      <td colspan="2" class="tablecell">
                        <div align="center">
                          <html:checkbox property="persistent" />
                          Unregister my PC.
                        </div>
                      </td>
                    </tr>
                  </logic:equal>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td>
                      <div align="center">
                        <html:submit property="logout" value="Logout" />
                      </div>
                    </td>
                    <td>
                      <div align="center">
                        <html:submit property="cancel" value="Cancel" />
                      </div>
                    </td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                </table>
                <!-- ---------------------------End LogoutTable--------------------------- -->
              </td></tr>
            </table>
            <!-- --------------------------------End Logout Outline Table------------------------------------- -->				
          </html:form>
          <!-- End form -->
        </td>
        <td><div align="left"><img src="images/pane/logout.jpg" alt="" width="87" height="216"></div></td>
      </tr>
    </table>
  </td></tr>
</table>
