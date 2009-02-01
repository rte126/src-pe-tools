<%-- -*- html -*-
  Account Tile.

  Format body of Account page.
  
  Input attributes:
    userInfo   - UserInfo bean
    category   - selected service category
    categories - list of all available service categories
    services   - list of service data
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>


<!-- Begin services page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<logic:empty name="services">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
    <tr><td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
          <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
          <td>
            <p>Sorry... you are not subscribed to any currently available
            services.
          </td>
        </tr>
      </table>
    </td></tr>
  </table>
</logic:empty>

<logic:notEmpty name="services">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
    <tr><td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
          <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
          <td>
            <!-- Begin note -->
            <p class="welcome">      
            Your subscribed services are listed below.
            <p class="rightpaneltext">
            To have a service automatically activated every time you log in,
            select "automatic" for that service and press the "Update" button.
            <!-- End note -->
            </td>
            <td><div align="left"><img src="images/pane/account.jpg" alt="" width="120" height="216"></div></td>
            </tr>
            <tr>
              <td>&nbsp;</td>
              <td colspan="2">
  

            <!-- Begin form -->
            <html:form action="account">
              <html:hidden property="category" />

              <tiles:insert page="/tiles/servicetabs.jsp">
                <tiles:put name="action" value="account" />
              </tiles:insert>

              <!-- Start border only table -->
              <table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
                <tr>
                  <td>
                    <!-- Start subscribed services table -->
                    <table border=0 cellpadding=4 cellspacing=1 width="100%">
                      <!-- table row showing column headings -->
                      <tr>
                        <th class="tableheading">Service&nbsp;description</th>
                        <th class="tableheading">Manual</th>
                        <th class="tableheading">Automatic</th>
                      </tr>
                      <logic:iterate id="row" name="services">
                        <!-- table row showing a subscribed service in the requested category -->
                        <tr class="tablerow">
                          <!-- table cell showing service description or name -->
                          <td class="tablecell">
                            <span class="rightpaneltext">
                              <bean:write name="row" property="column(service.descriptionOrServiceName)" />
                              <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                                (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
                              </logic:notEmpty>
                            </span>
                          </td>
                          <bean:define id="subscriptionName" name="row"
                                       property="column(subscription.subscriptionName)"
                                       type="java.lang.String" />
                          <!-- table cell showing activation trigger equals MANUAL checkbox -->
                          <td align="center" class="tablecell">
                            <% String trigger="trigger("+subscriptionName+")"; %>
                            <html:radio property="<%=trigger%>"
                                        value="MANUAL" />
                          </td>

                          <!-- table cell showing activation trigger equals ACTIVATE_ON_LOGIN checkbox -->
                          <td align="center" class="tablecell">
                            <html:radio property="<%=trigger%>"
                                        value="ACTIVATE_ON_LOGIN" />
                          </td>
                        </tr>  
                      </logic:iterate>
                      <!-- Begin button tr -->
                      <tr class="tablerow">
                        <td colspan=3 align=center class="tablecellbutton">
                          <div align="center">
                            <html:submit property="ok" value="Update" />
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <html:submit property="cancel" value="Cancel" />
                          <div>
                        </td>
                      </tr>
                      <!-- End button tr -->
                    </table>
                    <!-- End subscribed services table -->
                  </td>
                </tr>
              </table>
              <!-- End border only table -->
            </html:form>
            <br>
          </td>
        </tr>
      </table>
    </td></tr>
  </table>
</logic:notEmpty>
