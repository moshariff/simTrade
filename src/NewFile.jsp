<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<b>userName:</b><%= request.getParameter("userName")%><br/>
<b>country:</b><%= request.getParameter("country")%><br/>
<b>expiryDate:</b><%= request.getParameter("expiryDate")%><br/>
<b>simType:</b><%= request.getParameter("simType")%><br/>
<b>phoneNumber:</b><%= request.getParameter("phoneNumber")%><br/>
<b>plan:</b><%= request.getParameter("plan")%><br/>
<b>currentStatus:</b><%= request.getParameter("currentStatus")%><br/>
<b>ownerId:</b><%= request.getParameter("ownerId")%>

</body>
</html>