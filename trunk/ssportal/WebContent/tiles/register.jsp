<%-- -*- html -*-
  Equipment Registration Tile.
  
  Input Form asking for equipment registration info.
  
  Input attributes:
    userInfo   - UserInfo bean
    registration - list of all already registered equipment

  FormBean:
    registerForm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<!-- Begin equipment registration page -->
<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
  
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td><br />				  

          <!-- Begin form -->
          <html:form action="register" focus="userName">
            <html:hidden property="userName" />
            <html:hidden property="password" />
  
            <!-- ------------------------------Start tab outline table------------------------------------ -->
            <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
              <tr><td>
                <!-- -------------------------Start tab table------------------------------ -->
                <table border=0 cellpadding=0 cellspacing=0>
                  <tr>
                    <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                    <td class="tabselected" height=20>New&nbsp;Equipment&nbsp;Registration</td>
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
                    <p class="dialogh1">Please enter the MAC address (e.g. 03:3A:FE:98:3C:CB) and a
                    brief description of the device you want to register.</p>
                  </td></tr>
                  <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="1" height="1"></td></tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td class="tablecell">MAC&nbsp;address:</td>
                    <td class="tablecell"><html:text property="macAddress" size="30"/></td>
                  </tr>
                  <tr class="logtablerow">
                    <td class="tablecell">Description:</td>
                    <td class="tablecell"><html:text property="description" size="30"/></td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td>&nbsp;</td>
                    <td>
                      <html:submit property="ok" value="Register" />
                      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                      <html:submit property="cancel" value="Cancel" />
                    </td>
                  </tr>

                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                </table>
                <!-- ---------------------------End Registration Table--------------------------- -->
              </td></tr>
            </table>
            <!-- End border only table -->
            <br>
            <!-- Begin list of already registerd equipment form table -->
            <logic:notEmpty name="registration">
            
              <!-- ------------------------------Start  registered tab outline table------------------------------------ -->
              <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
                <tr><td>
                  <!-- -------------------------Start registered tab table------------------------------ -->
                  <table border=0 cellpadding=0 cellspacing=0>
                    <tr>
                      <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                      <td class=tabselected height=20>Registered&nbsp;Equipment&nbsp;</td>
                      <td><img height=20 src="images/onepx/transp-pixel.gif" width=1></td>
                    </tr>
                  </table>
                  <!-- ---------------------------------End registered tab table---------------------------- --> 
                </td></tr>
              </table>
              <!-- ---------------------------------End registered Tab Outline table---------------------------- -->
              <!-- --------------------------------Start registered  Outline Table------------------------------------- -->		
              <table width="100%" border=0 cellpadding=1 cellspacing=0 class="tableoutline">
                <tr><td>
                  <!-- ---------------------------Start registered  Table--------------------------- -->
                  <table width="100%" border=0 cellpadding=0 cellspacing=1>
                    <tr class="tablerow">
                      <td width="50%" class="tableheading">MAC&nbsp;address
                      <td width="50%" class="tableheading">Description</td>
                    </tr>

                    <logic:iterate id="element" name="registration">
                      <tr class="tablerow">
                        <td class="tablecell"><bean:write name="element" property="key" /></td>
                        <td class="tablecell"><bean:write name="element" property="value" /></td>
                      </tr>
                    </logic:iterate>
                  </table>
                  <!-- ---------------------------End registered Table--------------------------- -->
                </td></tr>
              </table>
              <!-- --------------------------------End Registration Outline Table------------------------------------- -->
            </logic:notEmpty>
            <!-- End list of already registerd equipment form table -->
          </html:form>
          <!-- End form -->

        </td>
        <td><div align="right"><img src="images/pane/register1.jpg" alt="" width="242" height="246"></div></td>
      </tr>
    </table>
  </td></tr>
</table>
