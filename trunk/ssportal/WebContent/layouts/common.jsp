<%-- -*- html -*- --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<tiles:useAttribute id="color" name="color" scope="request" />
<tiles:useAttribute name="menuTag" scope="request" />
<tiles:useAttribute name="pageId" scope="request" />
<tiles:useAttribute name="mainLogo" scope="request" />
<tiles:useAttribute name="logo" scope="request" />

<html>
<head>
    <title><tiles:getAsString name="title" /></title>
    <link rel="stylesheet"
          href="style_sheets/<%=color%>.css"
          type="text/css">
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->
</script>
</head>

<body leftmargin=0 topmargin=0 marginwidth="0" marginheight="0"
  onLoad="MM_preloadImages('images/leftnav/<%=color%>/arrow-on.gif')">
  <tiles:insert attribute="header" />
  <table border=0 cellpadding=0 cellspacing=8 width="100%">
    <tr valign=top>
      <td width="25%" height="282">
        <tiles:insert attribute="menu" />
      </td>
      <td width="75%">
        <!-- Start Right Panel -->
        <!-- Start Selection Path Outline table -->
        <table border=0 cellpadding=1 cellspacing=0 class="tableoutline">
          <tr>
            <td>
              <!-- Start table holding Selection Path elements -->
              <table border=0 cellpadding=0 cellspacing=0>
                <tr>
                  <% String page_title = "page."+pageId; %>
                  <td class="selpathroot" height=20 nowrap><bean:message key="<%=page_title%>"/>&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  <td height=20 nowrap width=1><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td> 
                  <td class="selpathterm" height=20 nowrap width=11><img height=20 src="images/onepx/transp-pixel.gif" alt="" width=1></td>
                </tr>
              </table>
              <!-- End table holding Selection Path elements -->
            </td>
          </tr>
        </table>
        <!-- Start spacer table -->
        <table  border="0" cellpadding="0" cellspacing="0">
          <tr><td height="20" align="left"><img src="images/onepx/<%=color%>/colorA-pixel.gif" alt="" width="1" height="20" border="0"></td>
          </tr>
        </table>
        <!-- End spacer table -->
        <!-- End navigation selection path Outline table -->

        <!-- Start main right side table -->
        <tiles:insert attribute="body" />
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
          <tr><td></td></tr>
        </table>
        <!-- End main right side table -->
        <!-- Start spacer table -->
        <table  border="0" cellpadding="0" cellspacing="0">
          <tr><td height="20" align="left"><img src="images/onepx/<%=color%>/colorA-pixel.gif" alt="" width="1" height="20" border=0></td>
          </tr>
        </table>
        <!-- End spacer table -->
        <!-- Start ADs table -->
        <table width="100%" border="0" cellpadding="0" cellspacing="1" class="tableoutline">
          <tr><td>
          <table width="100%" border="0" cellpadding="0" cellspacing="5" bgcolor="#ffffff">
            <tr><td align="right"><img src="<bean:write name="logo"/>" alt=""></td>
            </tr>
          </table>
          </td></tr>
        </table>
        <!-- End ADs table -->
        <!-- End Right Panel -->
      </td>
    </tr>
  </table>
  <tiles:insert attribute="footer" />
</body>
</html>
