<%@page import="musicx.dao.LocationDao"%>
<%@page import="musicx.model.Location"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Update Location</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<h1 align="center">Update User Location</h1>
<% LocationDao ld = LocationDao.getInstance();
List<Location> locations = ld.getAllLocation();%>

<form action="${pageContext.request.contextPath}/UpdateUserLocationServlet" name="frmUpdateUserLocation" method="post">
	<div class="form-group">
	<table class="table table-bordered" align="center">
	 	<tr>
	 		<th align="left">Username</th>
	 		<td><input class="form-control" type="text" id="userName" name="userName" size="35"></td>
	 	</tr>
	 	<tr>
	 		<th align="left">Location</th>
	 		<td><select class="form-control" name="location" id="location">
			<option>--Select--</option>
        		<% for(Location l : locations){  %>
            <option><%= l.getState()%></option>
            <% } %>     
        </select></td>
	 	</tr>
	 	<tr>
  		<td colspan="2" align="center"><p id="errMsg" style="color:red"><%
    	if(null!=request.getAttribute("errorMessage"))
    	{
        	out.println(request.getAttribute("errorMessage"));
    	}
		%></p></td>
  	</tr>
	 	<tr>
	 	<td colspan="2" align="center">
	 		<input class="btn btn-sm btn-default" type="submit" value="Update User">
	 	</td>
	 	</tr> 
	 	
	 	</table>
	 	</div>
	 	</form>
	 	</div>
</body>
</html>