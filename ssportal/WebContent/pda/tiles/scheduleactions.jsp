<%-- -*- html -*-
  Schedule Detail Tile.
  
  Format the "Schedule Detail" tile.

  used requestAttributes:
    elem - the element to do operations on, either view or modify.
    subAction - the selected action/operation for this tile
    subActions - List of  subaActions supported by this tile
    categories - List of tab titles
    category   - title of selected tab
  used tilesAttributes:
    actionRows 
    rowSize
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.TreeMap" %>

<tiles:useAttribute name="actionRows" classname="java.util.List" />
<tiles:useAttribute name="rowSize" classname="java.lang.String" />
<tiles:useAttribute name="operOptions" classname="java.util.Map" />

<!-- Begin Actions table -->
<table border=0 cellpadding=1 cellspacing=0 class=tableoutline width="95%">
  <tr>
    <td>
    
      <!-- Start Actions table -->
      <table border=0 cellpadding=4 cellspacing=1 width="100%">
				<tr> 
				  <th align="center" colspan="2" class="tableheading">Actions</th>
				</tr>        
        
        <!-- table row for each action in the schedule actions -->

				<!-- Beg of add section -->
				<logic:iterate id="row" name="actionRows" indexId="orderId">
				
						<bean:define id="serviceName" name="row"
							property="column(serviceName)"
							type="java.lang.String"/>
						<bean:define id="operation" name="row"
							property="column(operation)"
							type="java.lang.String"/>
							
            <tr>
              <td colspan="2">
                <img src="images/horzline.gif" width="100%" height="1">
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <h4><img src="images/blt-1arrow.gif">&nbsp;
                Order: <bean:write name="orderId"/>
              </td>
            </tr>				
            <tr>
            	<td valign="center"><h5>Operation:</h5></td>
            	<td class="tablecell">
							
<%
  TreeMap operOptionsSelected = new TreeMap(operOptions);
	pageContext.setAttribute("operOptionsSelected", operOptionsSelected);
  operOptionsSelected.put(operation, 
		String.valueOf(operOptions.get(operation))+" *");
%>
    							<html:select property="operations" value='<%=operation%>'>
    								<html:options collection="operOptionsSelected" property="key"
    								  labelProperty="value" />
    							</html:select>
    													
						  </td>      
					  </tr>
					  <tr>
							<td valign="center"><h5>Service:</h5></td>
					  	<td class="tablecell">  
    							<html:select property="serviceNames" value='<%=serviceName%>' >
    							  <html:option value="">Please Select</html:option>
    								<html:option value='<%=serviceName%>'>
    									<%=serviceName%> *</html:option>	  
    								<logic:iterate id="irow" name="services">
    								  <bean:define id="subscriptionName" name="irow"
    									  property="column(subscription.subscriptionName)"
    									  type="java.lang.String"/>
    									<logic:notEqual name="irow" 
    										property="column(subscription.subscriptionName)"
    										value='<%=serviceName%>'>
    										<html:option value="<%=subscriptionName%>">             
    											  <bean:write name="irow" 
    											  	property="column(subscription.subscriptionName)" />
    										</html:option>
    									</logic:notEqual>			  
    								</logic:iterate>
    							</html:select>					  					    
					  	</td>    
					  </tr>
			  </logic:iterate>


				<%
				  int idxBeg = actionRows.size();
				  int idxEnd = Integer.parseInt(rowSize)-1;
				  java.util.List order = new java.util.ArrayList();
				  for(int i = idxBeg; i<=idxEnd; i++)
						order.add(String.valueOf(i));
				  pageContext.setAttribute("order", order);
				%>


				<!-- Beg of add section -->
			  <logic:iterate id="ord" name="order" indexId="orderId">  				
            <tr>
              <td colspan="2">
                <img src="images/horzline.gif" width="100%" height="1">
              </td>
            </tr>
            <tr>
              <td colspan="2">
                <h4><img src="images/blt-1arrow.gif">&nbsp;
                Order: <bean:write name="ord"/>
              </td>
            </tr>				
    				<tr>
    				  <td valign="center"><h5>Operation:</h5></td>
    				  <td class="tablecell">
    						<html:select property="operations" value="">
    							<html:options collection="operOptions" property="key"
    							  labelProperty="value" />
    						</html:select>  
    				  </td>
            </tr>
            <tr>
    					<td valign="center"><h5>Service:</h5></td>
    				  <td class="tablecell">    
    						<html:select property="serviceNames" value="">
    						  <html:option value="">Please Select</html:option>
    						  <logic:empty name="services">
    							  <!--option value="">No available services</option-->
    						  </logic:empty>
    						  <logic:notEmpty name="services">
    							<logic:iterate id="row" name="services">
    							  <bean:define id="subscriptionName" name="row"
    								  property="column(subscription.subscriptionName)"
    								  type="java.lang.String"/>
    							  <html:option value="<%=subscriptionName%>">             
    								<	bean:write name="row" property="column(subscription.subscriptionName)" />
    							  </html:option>
    							</logic:iterate>
    						  </logic:notEmpty>
    						</html:select>
    				  </td>    
    				</tr>
			  </logic:iterate>

        <!-- horzline -->
        <tr>
          <td colspan="2">
            <img src="images/horzline.gif" width="100%" height="1">
          </td>
        </tr>

        <!-- Begin button tr -->
        <tr  class="tablerow">
          <td colspan=4 align=center class="tablecellbutton">
            <html:submit property="moreActions" value="More Actions" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <html:submit property="lessActions" value="Less Actions" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          </td>
        </tr>
        <!-- End of add section -->        
      </table>
      <!-- End Actions table -->
      
    </td>
  </tr>
</table>
<!-- End border only table -->
