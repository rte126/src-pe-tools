<%-- -*- html -*- --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String userAgent = request.getHeader("User-Agent");
  if (userAgent.indexOf("Windows CE") >= 0 ||
      userAgent.indexOf("PalmOS") >= 0) {
    response.sendRedirect("pda/index.do");
    return;
  }
%>
<jsp:forward page="index.do"/>
