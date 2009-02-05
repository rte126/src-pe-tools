<%-- -*- html -*-
  Equipment Unregistration Tile.

  Format body of Unregister page.
  
  Input attributes:
    userInfo   - UserInfo bean
    registration   - list of all already registered equipment
  Form:
    registerForm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!-- Begin unregister page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
  
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td><br />				  

          <logic:empty name="registration">
            <pclass="rightpaneltext">
            Sorry... no equipment has been registered with the username <bean:write name="userInfo" property="name"/>.
          </logic:empty>

          <logic:notEmpty name="registration">

            <!-- Begin note -->
            <p class="rightpaneltext">   
            Please choose the equipment you want to unregister.
            Check the equipments you want to unregister, and then click the
            "Unregister" button.
            <!-- End note -->    

            <!-- Begin form -->
            <html:form action="unregister">
              <html:hidden property="userName" />
              <html:hidden property="password" />
              <!-- ------------------------------Start tab outline table------------------------------------ -->
              <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
                <tr><td>
                  <!-- -------------------------Start tab table------------------------------ -->
                  <table border=0 cellpadding=0 cellspacing=0>
                    <tr>
                      <td height=20><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                      <td class="tabselected" height=20>Unregister&nbsp;Equipment</td>
                      <td><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                    </tr>
                  </table>
                  <!-- ---------------------------------End Tab table---------------------------- --> 
                </td></tr>
              </table>
              <!-- ---------------------------------End Tab Outline table---------------------------- -->
              <!-- --------------------------------Start Unregister Outline Table------------------------------------- -->		
              <table width="100%" border=0 cellpadding=1 cellspacing=0 class="tableoutline">
                <tr><td>
                  <!-- ---------------------------Start Unregister Table--------------------------- -->
                  <table width="100%" border=0 cellpadding=0 cellspacing=1>
                    <tr class="tablerow">
                      <td width="40%" class="tableheading">MAC&nbsp;address
                      <td width="40%" class="tableheading">Description</td>
                      <td class="tableheading">Unregister?</td>
                    </tr>

                    <logic:iterate id="element" name="registration">
                      <!-- table row showing a registered equipment in the requested category -->
                      <tr class="tablerow">
                        <!-- table cell showing service description or name -->
                        <td class="tablecell"><bean:write name="element" property="key" /></td>
                        <td class="tablecell" nowrap><bean:write name="element" property="value" /></td>
                        <td align="center" class="tablecell">
                          <bean:define id="macAddress" name="element"
                                       property="key"
                                       type="java.lang.String" />
                          <% String register="register("+macAddress+")"; %>
                          <html:checkbox property="<%=register%>" />                        
                        </td>
                      </tr>
                    </logic:iterate>
                    <tr class="tablerow">
                      <td colspan="3" class="tablecellbutton">
                        <div align="center">
                          <html:submit property="ok" value="Unregister" />
                          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                          <html:submit property="cancel" value="Cancel" />
                        </div>
                      </td>
                    </tr>
                  </table>
                  <!-- End registration table -->
                </td></tr>
              </table>
              <!-- End border only table -->
            </html:form>
          </logic:notEmpty>
        </td>
        <td><div align="right"><img src="images/pane/unregister.jpg" alt="" width="175" height="155"></div></td>
      </tr>
    </table>
  </td></tr>
</table>
