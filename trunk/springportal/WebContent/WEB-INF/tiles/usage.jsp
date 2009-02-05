<%--
  Usage Tile.

  Format body of Services page.
  
  Input attributes:
    userInfo   - UserInfo bean
    category   - selected service category
    categories - list of all available service categories
    usage      - list of usage data
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>


<!-- Begin usage page -->

<jsp:useBean id="userInfo" scope="request" class="net.juniper.smgt.ssp.model.UserInfo" />

<logic:empty name="usage">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
    <tr><td>
       <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
         <tr>
           <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
           <td width="100%">
  
             <p>Sorry... no service usage information is available because you
             are not subscribed to any services.
           </td>
         </tr>
       </table>
     </td></tr>
   </table>
</logic:empty>

<logic:notEmpty name="usage">
  <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
    <tr><td>
      <table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
        <tr>
          <td><img src="images/onepx/transp-pixel.gif" alt="" width="10" border="0"></td>
          <td width="100%">
            <!-- Begin note -->
            <p class="welcome">
            Accounting data for each of your subscribed services is listed
            below.
            
            <p class="rightpaneltext">
            This information describes your <em>most recent</em> use of each
            service during your <em>current</em> login session.  The status
            column shows a green circle for an active service or a red circle
            for a non active service. The time column shows the time at which
            the data was collected from the network.

            <!-- End note -->
          </td>
          <td width="155"><img src="images/pane/usage.jpg" alt="" width="155" height="128"></td>
        </tr>
        <tr>

          <!-- Begin form -->
          <td>&nbsp;</td>
          <td colspan="2">
            <tiles:insert page="/tiles/servicetabs.jsp">
              <tiles:put name="action" value="usage" />
            </tiles:insert>

            <!-- --------------------------------Start Selection Options Outline Table------------------------------------- -->
            <table border="0" cellpadding="1" cellspacing="0" class="tableoutline" width="95%">
              <tr><td>
                <table border="0" cellpadding="4" cellspacing="1" width="100%">
                <tr> 
                                    <th class="tableheading">Service&nbsp;description</th>
                                    <th class="tableheading">Status</th>
                                    <th class="tableheading">Been&nbsp;active&nbsp;for</th>
                                    <th class="tableheading">Time</th>
                                    <th class="tableheading">Bytes&nbsp;out</th>
                                    <th class="tableheading">Bytes&nbsp;in</th>
                                    <th class="tableheading">Packets&nbsp;out</th>
                                    <th class="tableheading">Packets&nbsp;in</th>
                </tr>
                <logic:iterate id="row" name="usage">
                  <!-- table row showing a subscribed service in the requested category -->
                  <tr valign="center" class="tablerow">
                    <!-- table cell showing service description or name -->
                    <td class="tablecell">
                      <span class="rightpaneltext">
                        <bean:write name="row" property="column(service.descriptionOrServiceName)" />
                        <logic:notEmpty name="row" property="column(subscription.multiSubscriptionName)">
                          (<bean:write name="row" property="column(subscription.multiSubscriptionName)"/>)
                        </logic:notEmpty>
                      </span>
                    </td>

                    <td align="center" class="tablecell">
                      <logic:equal name="row" property="column(subscription.active)" value="true">
                        <img src="images/pane/activ_green.gif" border=0
                        alt="active">
                      </logic:equal>
                      <logic:notEqual name="row" property="column(subscription.active)" value="true">
                        <img src="images/pane/activ_red.gif" border=0
                        alt="inactive">
                      </logic:notEqual>
                    </td>

                    <td align="center" class="tablecell">
                      <bean:define id="durationActive" name="row" property="column(subscription.durationActive)" type="java.lang.String"/>
                      <% int l = durationActive.length();
                         String d_sec = "0";
                         if (l > 3)
                             d_sec = durationActive.substring(0, l-3); %>
                       <%=d_sec%> sec
                    </td>

                    <td align="center" class="tablecell">
                      <logic:notEmpty name="row" property="column(subscription.usage.collectionTime)">
                        <bean:write name="row" property="column(subscription.usage.collectionTime)" />
                      </logic:notEmpty>
                      <logic:empty name="row" property="column(subscription.usage.collectionTime)">
                        Never
                      </logic:empty>
                    </td>

                    <td align="center" class="tablecell">
                      <bean:write name="row" property="column(subscription.usage.bytesFromUser)" />
                    </td>

                    <td align="center" class="tablecell">
                      <bean:write name="row" property="column(subscription.usage.bytesToUser)" />
                    </td>

                    <td align="center" class="tablecell">
                      <bean:write name="row" property="column(subscription.usage.packetsFromUser)" />
                    </td>

                    <td align="center" class="tablecell">
                      <bean:write name="row" property="column(subscription.usage.packetsToUser)" />
                    </td>

                  </tr>  
                </logic:iterate>
              </table>
            <!-- End usage table -->
            </tr></td>
            </table>
            <br>
          </td>
        </tr>
      </table>
    </td></tr>
   </table>
</logic:notEmpty>

<%-- Local Variables: --%>
<%-- mode: html --%>
<%-- End: --%>
