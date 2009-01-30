<%-- -*- html -*-
   Login Tile.

   Input Form asking for user credentials.

   FormBean:
     LoginForm
   Attributes:
     allowPersistent
--%>
    
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td><br>		 

          <!-- Begin form -->
          <html:form action="login" focus="userName">
          <!-- --------------------------------Start Login Outline Table------------------------------------- -->
            <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
              <tr><td>
                <!-- ---------------------------Start Login Table--------------------------- -->
                <table width="100%" border=0 cellpadding=0 cellspacing=0>
                  <tr><td colspan="2" bgcolor="#FFFFFF">
                    <p class="dialogh1">Please enter your SSP Username and Password.</p>
                  </td></tr>
                  <tr><td><img src="images/onepx/transp-pixel.gif" alt="" width="1" height="1"></td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <tr class="logtablerow">
                    <td class="tablecell">Username:</td>
                    <td class="tablecell"><html:text property="userName"  size="30"/></td>
                  </tr>

                  <tr class="logtablerow">
                    <td class="tablecell">Password:</td>
                    <td class="tablecell"><html:password property="password" size="30"/></td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td></tr>
                  <logic:equal name="allowPersistent" value="true">
                    <tr class="logtablerow">
                      <td colspan="2" class="dialogh1">
                        <div align="center">
                          <html:checkbox property="persistent"/>
                          Don't ask me again.
                        </div>
                      </td>
                    </tr>
                  </logic:equal>
                  <tr class="logtablerow">
                    <td colspan="2">
                      <div align="center">
                        <html:submit value="Login"/>
                      </div>
                    </td>
                  </tr>
                  <tr class="logtablerow"><td colspan="2">&nbsp;</td>
                  </tr>
                </table>
                <!-- ---------------------------End Login Table--------------------------- -->
              </td></tr>
            </table>
          </html:form>

        <!-- --------------------------------End Login Outline Table------------------------------------- -->

        </td>
        <!--Sign Up link  -->
        <td valign="bottom">
          <p class="rightpaneltext">Not registered yet?<br />
          <a href="">Sign up now!</a></p>
          <p class="rightpaneltext">Forgot your password?<br /><a href="">Click here</a> for help.</p>
          <div align="right"><img src="images/pane/login.gif" alt="" width="127" height="114" border="0"></div>
        </td>
      </tr>
    </table>
  </td></tr>
</table>
 