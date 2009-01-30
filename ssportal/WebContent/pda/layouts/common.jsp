<%-- -*- html -*- --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<tiles:useAttribute name="logo" scope="request" />

<html>
<head><title><tiles:getAsString name="title" /></title></head>
<body>
<tiles:insert attribute="header" />
<tiles:insert attribute="body" />
<tiles:insert attribute="footer" />
</body>
</html>
