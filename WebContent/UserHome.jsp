<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p style="align:right"><% if (session != null) {
	if (session.getAttribute("UserName") != null) {
		String name = (String) session.getAttribute("UserName");
		out.print("Hello " + name + ",  Welcome to ur Profile");
	} else {
		out.println("No Session");
		request.setAttribute("errorMessage", "Session Expired, Login Again.");
		RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
        rd.forward(request, response); 
	}
} %> </p>
</body>
</html>