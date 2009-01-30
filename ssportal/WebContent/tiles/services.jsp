<%-- -*- html -*-
  Services Tile.

  Format body of Services page.

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
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!-- Begin services page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>
          <logic:empty name="services">
            <p class="rightpaneltext">
            Sorry... you are not subscribed to any currently available
            services.
          </logic:empty>


          <logic:notEmpty name="services">
            <!-- Begin note -->
            <p class="rightpaneltext">
            You can start or stop a service by clicking on the circle in
            the "Status" column. A green circle (&nbsp;<img
            src="images/pane/activ_green-small.gif" alt="o" width="15" height="15"
            border="0">&nbsp;) means the service is currently on. A red
            circle (&nbsp;<img src="images/pane/activ_red-small.gif"
            alt="o" width="12" height="13" border="0">&nbsp;) means the service is
            currently off.</p>

            <p class="rightpaneltext">
            You can persistently activate a service by clicking on the
            check box in the "Persistent" column. Persistently activated
            services are automatically activated when you login to the
            portal.</p>
          </td>
          <td><div align="right"><img src="images/pane/service.jpg" alt="" width="232" height="188"></div></td>
        </tr>
        <!-- End note -->

        <!-- Begin form -->
        <tr>
          <td>&nbsp;</td>
          <td colspan="2">
            <tiles:insert page="/tiles/servicetabs.jsp">
              <tiles:put name="action" value="services" />
            </tiles:insert>

            <!-- --------------------------------Start Selection Options Outline Table------------------------------------- -->
            <table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
              <tr>
                <td>
                  <!-- ---------------------------Start Selection Options Table--------------------------- -->
                  <!-- Start subscribed services table -->
                  <table cellpadding=4 cellspacing=1 border=0 width="100%">
                    <!-- table row showing column headings -->
                    <tr>
                      <th class="tableheading">Service&nbsp;Description</th>
                      <th class="tableheading">Status</th>
                      <th class="tableheading">Password&nbsp;required</th>
                      <th class="tableheading">Persistent</th>
                      <th class="tableheading">Price</th>
                    </tr>
                    <logic:iterate id="row" name="services">
                      <bean:define id="subscriptionName"
                                   name="row"
                                   property="column(subscription.subscriptionName)"
                                   type="java.lang.String"/>
                      <bean:define id="activateOnly"
                                   name="row"
                                   property="column(service.activateOnly)"
                                   type="java.lang.String"/>
                      <bean:define id="authReq"
                                   name="row"
                                   property="column(service.authenticationRequired)"
                                   type="java.lang.String"/>
                      <bean:define id="serviceType"
                                   name="row"
                                   property="column(service.type)"
                                   type="java.lang.String"/>
                      <bean:define id="isActive"
                                   name="row"
                                   property="column(subscription.active)"/>
                      <%
                        String action = "services";
                        if ("true".equals(isActive)) {
                           action = "deactivate";
                         }
                         else {
                           action = "activate";
                        }
                        pageContext.setAttribute("action", action);
                      %>
                      <html:form action="<%=action%>">
                        <html:hidden property="category" />
                        <html:hidden property="subscription" value="<%=subscriptionName%>"/>
                        <html:hidden property="activateOnly" value="<%=activateOnly%>"/>
                        <html:hidden property="authReq" value="<%=authReq%>"/>
                        <html:hidden property="serviceType" value="<%=serviceType%>"/>
                        <!-- table row showing a subscribed service in the requested category -->
                        <tr class="tablerow">
                          <!-- table cell showing service description or name -->
                          <td width="100%" class="tablecell">
                            <span class="rightpaneltext">
                              <logic:equal name="row" property="column(subscription.active)" value="true">
                                <logic:notEmpty name="row" property="column(service.url)">
                                  <a href="<bean:write name="row" property="column(service.url)"/>">
                                    <bean:write name="row" property="column(service.descriptionOrServiceName)" />
                                    <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                                      (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
                                    </logic:notEmpty>
                                  </a>
                                </logic:notEmpty>
                                <logic:empty name="row" property="column(service.url)">
                                  <bean:write name="row" property="column(service.descriptionOrServiceName)" />
                                  <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                                    (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
                                  </logic:notEmpty>
                                </logic:empty>
                              </logic:equal>
                              <logic:notEqual name="row" property="column(subscription.active)" value="true">
                                <bean:write name="row" property="column(service.descriptionOrServiceName)" />
                                <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                                  (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
                                </logic:notEmpty>
                              </logic:notEqual>
                            </span>
                          </td>

                          <!-- table cell showing activate or deactivate button -->
                          <td align="center" valign="middle" class="tablecell">
                            <logic:equal name="action" value="activate">
                              <html:image src="images/pane/activ_red.gif" border="0" alt="inactive"/>
                            </logic:equal>
                            <logic:equal name="action" value="deactivate">
                              <html:image src="images/pane/activ_green.gif" border="0" alt="active"/>
                            </logic:equal>
                          </td>

                          <!-- table cell showing whether service requires authentication -->
                          <td align="center" valign="middle" class="tablecell">
                            <logic:equal name="row" property="column(service.authenticationRequired)" value="true">
                              <img src="images/pane/key_blue.gif" width="50" height="30" alt="Yes">
                            </logic:equal>
                            &nbsp;
                          </td>

                          <!-- table cell showing the peristent state -->
                          <td align="center" valign="middle" class="tablecell">
                            <% String persistent="persistent("+subscriptionName+")"; %>
                            <logic:equal name="row" property="column(service.authenticationRequired)" value="false">
                              <html:checkbox property="<%=persistent%>" value="true"/>
                            </logic:equal>
                            <% String persistentInitial="persistentInitial("+subscriptionName+")"; %>
                            <html:hidden property="<%=persistentInitial%>" />
                          </td>

                          <!-- table cell showing the service price -->
                          <td align="center"valign="middle" class="tablecell">
                            <span class="rightpaneltext">N/A</span>
                          </td>

                        </tr>
                      </html:form>
                    </logic:iterate>
                  </table>
                  <!-- End subscribed services table -->
                </td>
              </tr>
            </table>
            <br>
          </logic:notEmpty>
        </td>
      </tr>
    </table>
  </td></tr>
</table>
