<%-- -*- html -*-
  Equipment Unregistration Credentials Tile.
  
  Input Form asking for equipment unregistration authentication credentials

  FormBean:
    RegisterForm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<!-- Begin unregistration credentials page -->
<!-- Begin registration credentials page -->
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>
  
          <p class="rightpaneltext">
          You may unregister your DHCP equipment so that it does not
          automatically obtain a public IP address.  The first step is
          to supply the same credentials that you entered when you
          registered your equipment.
          <br>

          <!-- Begin form -->
          <html:form action="unregister" focus="userName">
            <!-- ------------------------------Start tab outline table------------------------------------ -->
            <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
              <tr><td>
                <!-- -------------------------Start tab table------------------------------ -->
                <table border=0 cellpadding=0 cellspacing=0>
                  <tr>
                    <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                    <td class="tabselected" height=20>Equipment&nbsp;Credentials</td>
                    <td><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                  </tr>
                </table>
                <!-- ---------------------------------End Tab table---------------------------- --> 
              </td></tr>
            </table>
            <!-- ---------------------------------End Tab Outline table---------------------------- -->
            
            <!-- --------------------------------Start Registration Outline Table------------------------------------- -->		
            <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
              <tr><td>
                <!-- ---------------------------Start Registration Table--------------------------- -->
                <table width="100%" border=0 cellpadding=0 cellspacing=0>
                  <tr><td colspan="2" bgcolor="#FFFFFF">
                    <p class="dialogh1">Please enter your username and password <br>for the Equipment Registration:</p>
                  </td></tr>
                  <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="1" height="1"></td>
                  </tr>
                  <tr class="logtablerow">
                    <td colspan="2" class="tablecell">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td class="tablecell">Username:</td>
                    <td class="tablecell"><html:text property="userName" size="30"/></td>
                  </tr>
                  <tr class="logtablerow">
                    <td class="tablecell">Password:</td>
                    <td class="tablecell"><html:password property="password"size="30"/></td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td colspan="2">
                      <div align="center">
                        <html:submit property="cont" value="Continue" />
                      </div>
                    </td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                </table>
                <!-- ---------------------------End Registration Table--------------------------- -->
              </td></tr>
            </table>
            <!-- --------------------------------End Registration Outline Table------------------------------------- -->
            </html:form>
            <!-- End form -->

          <br>
        </td>
	<td><div align="right"><img src="images/pane/unregister.jpg" alt="" width="175" height="155"></div></td>
      </tr>
    </table>
  </td></tr>
</table>
<!-- End unregistration credentials page -->

