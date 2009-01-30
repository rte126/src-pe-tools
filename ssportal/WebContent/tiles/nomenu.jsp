<%-- -*- html -*-
   Empty Menu Tile.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<bean:define id="color" name="color" type="java.lang.String"/>
<bean:define id="menuTag" name="menuTag" type="java.lang.String"/>

<!-- ---------------------------------------Start outside left navigation  table-------------------------------- -->
<table border="0" cellpadding="0" cellspacing="0">
  <tr><td>
    <!-- Start left navigation  title Outline table -->
    <table width="100%" border=0 cellpadding=1 cellspacing=0 class="tableoutline">
      <tr><td>
        <!-- Start table holding left navigation  title elements -->
        <table width="100%" border=0 cellpadding=0 cellspacing=0>
          <tr>
            <td width="100%" height=20 nowrap class="leftnavtitlebox">&nbsp;Service Selection Portal&nbsp;</td>
            <td height=20 nowrap><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
            <td height=20 align="right" nowrap bgcolor="#FFFFFF"><img border=0 height=15 src="images/pane/selpathsep.gif" alt="" width=11></td>
          </tr>
        </table>
        <!-- End table holding left navigation  title elements -->
      </td></tr>
    </table>
    <!-- ------------------------------End left navigation  title Outline table------------------------------------ -->
  </td></tr>
  <tr><td height="20" align="left"><img src="images/onepx/<%=color%>/colorA-pixel.gif" alt="" width="1" height="20" border=0></td></tr>
  <tr><td>
    <!-- ------------------------------Start left navigation  Outline table------------------------------------ -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
      <tr><td>
        <table width="100%" border="0" cellpadding="8" cellspacing="0" bgcolor="#FFFFFF">
          <tr><td>
            <!-- --------------------------------------------Start left navigation  table-------------------------------------------------------- -->
            <table width="164" border="0" cellspacing="0" cellpadding="0">
              <tr><td>
                <img src="images/leftnav/woman.jpg" alt="">
              </td></tr>
            </table>
          </tr></td>
        </table>
      </tr></td>
    </table>

    <!-- -------------------- Start Spacer table----------------------------- -->
    <table border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="20" align="left"><img src="images/onepx/<%=color%>/colorA-pixel.gif" alt="" width="1" height="20" border=0></td>
      </tr>
    </table>
    <!-- -------------------- End Spacertable----------------------------- -->

    <!-- --------------------Start code for Search table----------------------------- -->
    <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
      <tr><td>
        <table width="100%" border="0" cellpadding="5" cellspacing="0" class="leftpanelbox">
          <tr>
            <td colspan="2" class="searchboxtext">Search</td>
          </tr>
          <tr>
            <td><input name="search" type="text" size="20" maxlength="20"></td>
            <td><img border=0 height=15 src="images/pane/selpathsep.gif" alt="" width=11></td>
          </tr>
        </table>
      </td></tr>
    </table>
    <!-- ------------------End code for Search table----------------------------------- -->
  </td></tr>
</table>
<!-- --------------------------------End outside left navigation  table-------------------------- -->
