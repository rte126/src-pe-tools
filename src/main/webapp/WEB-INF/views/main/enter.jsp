<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Start view in main flow :)</h1>
<h2>via POST:</h2>
<form method="post"><input type="submit" value="Proceed" /> <input
type="hidden" name="_eventId" value="proceed" /></form>
<h2>or via GET:</h2>
<a href="${flowExecutionUrl}&_eventId=proceed">Proceed</a>


</body>
</html>