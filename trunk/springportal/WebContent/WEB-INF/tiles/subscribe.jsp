<%-- -*- html -*-
  Subscribe Tile.

  Format body of Subscribe page.
  
  Input attributes:
    userInfo   - UserInfo bean
    services   - list of subscribable services data
    category   - selected service category
    categories - list of all available service categories
  Form:
    subscribeForm
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!-- Begin subscribe page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
  <tr><td>
    <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
      <tr>
        <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
        <td>

          <logic:empty name="services">
            <p class="rightpaneltext">
            Sorry... no services are available for you to subscribe to.
          </logic:empty>

          <logic:notEmpty name="services">
            <logic:equal name="userInfo" property="info(anonymous)" value="true">
              <p class="rightpaneltext">
              You are not permitted to modify your shared user profile.
            </logic:equal>

            <logic:notEqual name="userInfo" property="info(anonymous)" value="true">
              <!-- Begin note -->
              <p class="welcome">   
              All available services are listed below.
              <p class="rightpaneltext">
              It may take a minute for your new subscriptions to take
              effect.
            </td>
            <!-- End note -->
            <td><img src="images/pane/subscribe.jpg" alt="" width="185" height="171"></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
            <td colspan="2">

              <!-- Begin form -->
              <html:form action="subscribe">
                <html:hidden property="category" />
                
                <tiles:insert page="/tiles/servicetabs.jsp">
                  <tiles:put name="action" value="subscribe" />
                </tiles:insert>
                
                <!-- Start border only table -->
                <table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
                  <tr>
                    <td>
                      <!-- Start services table -->
                      <table border=0 cellpadding=4 cellspacing=1 width="100%">
                        <!-- table row showing column headings -->
                        <tr class="lightcolour"> 
                          <th class="tableheading">Service&nbsp;Name</th>
                          <th class="tableheading">Service&nbsp;description</th>
                          <th class="tableheading">Subscribed</th>
                          <th class="tableheading">Unsubscribed</th>
                        </tr>
                        <logic:iterate id="row" name="services">
                          <!-- table row showing a subscribed service in the requested category -->
                          <tr class="tablerow">
                            <!-- table cell showing service description or name -->
                            <td nowrap class="tablecell">
                              <bean:write name="row"
                                          property="column(service.serviceName)" />
                              </td>
                            <td class="tablerow">
                              <span class="rightpaneltext">
                                <bean:write name="row"
                                            property="column(service.attribute.description)" />
                              </span>
                            </td>
                            <bean:define id="serviceName" name="row"
                                         property="column(service.serviceName)"
                                         type="java.lang.String" />
                            <% String subscribe="subscribe("+serviceName+")"; %>
                            <td align="center" class="tablecell">
                              <html:radio property="<%=subscribe%>" value="true"/>
                            </td>
                            <td align="center" class="tablecell">
                              <html:radio property="<%=subscribe%>" value="false"/>
                            </td>
                          </tr>  
                        </logic:iterate>
                        
                        <!-- Begin button tr -->
                        <tr class="tablerow">
                          <td colspan=4 class="tablecellbutton">
                            <div align="center">
                              <html:submit property="ok" value="OK" />
                              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                              <html:submit property="cancel" value="Cancel" />
                            </div>
                          </td>
                        </tr>
                        <!-- End button tr -->
                      </table>
                      <!-- End subscribed services table -->
                    </td>
                  </tr>
                </table>
                <!-- End border only table -->
                <br>
              </html:form>
            </logic:notEqual>
          </logic:notEmpty>
        </td>
      </tr>
    </table>
  </td></tr>
</table>