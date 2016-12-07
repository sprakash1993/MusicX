<%@page import="musicx.dao.LocationDao"%>
<%@page import="musicx.model.Location"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>

<script type="text/javascript">
function validateForm() {
	var uID = document.forms["frmUserRegister"]["userID"].value;
	var fName = document.forms["frmUserRegister"]["firstname"].value;
	var lName = document.forms["frmUserRegister"]["lastname"].value;
	var password = document.forms["frmUserRegister"]["pwd"];
	
	if (fName == "" || fName == null){
		alert("Enter First Name");
		return false;		
	}
	
	if (lName == "" || lName == null){
		alert("Enter Last Name");
		return false;		
	}
	if (uID == "" || uID == null){
		alert("Enter UserID");
		return false;		
	}
	if (password.value == "" || password.value == null){
		alert("Enter password");
		return false;		
	}
	var pass2 = document.forms["frmUserRegister"]["repwd"];
	//alert(pass2.value);
	
	if(password.value!=pass2.value){
		alert("Passwords do not match");
		password.value="";
		pass2.value="";
		return false;
	}
	
}

function checkPass(){
	var password = document.forms["frmUserRegister"]["pwd"];
	var pass2 = document.forms["frmUserRegister"]["repwd"];
	//alert(pass2.value);
	
	if(password.value!=pass2.value){
		alert("Passwords do not match");
		password.value="";
		pass2.value="";
		return false;
	}
}

function cancelRegister(){
	window.location.href="UserLogin.jsp";	
}
</script>

</head>
<body>
<% LocationDao ld = LocationDao.getInstance();
List<Location> locations = ld.getAllLocation();%>
<form name="frmUserRegister" action="UserRegister" method="post" onsubmit="return validateForm()">
  <h1>User Register</h1><br/>
  <table>
  	<tr>
  	<th>FirstName</th>
  	<td> <input type="text" id="firstname" name="firstname"></input></td>
  	</tr>
  	<tr>
  	<tr>
  	<th>LastName</th>
  	<td> <input type="text" id="lastname" name="lastname"></input></td>
  	</tr>
  	<tr>
  	<th>UserID</th>
  	<td> <input type="text" id="userName" name="userName"></input></td>
  	</tr>
  	<tr>
  	<th>Password</th>
  	<td><input type="password" id="pwd" name="pwd"></td>
  	</tr>
  	<tr>
  	<th>Re-type Password</th>
  	<td><input type="password" id="repwd" name="repwd" onchange="checkPass()" ></td>
  	</tr>
  	<tr>
  	<th>Date of Birth</th>
  	<td><input type="date" id="birthDate" name="birthDate"></td>
  	</tr>
  	<tr>
  	<th>Gender</th>
  	<td><input type="radio" name="gender" value="male" checked> Male
  <input type="radio" name="gender" id="gender" value="female"> Female</td>
  	</tr>
  	<tr>
  	<th>Location</th>
  	<td><select  name="location" id="location">
			<option>--Select--</option>
        		<% for(Location l : locations){  %>
            <option><%= l.getState()%></option>
            <% } %>     
        </select></td>
  	</tr>
  	<tr>
  	<th>Phone</th>
  	<td><input type="number" id="phone" name="phone" maxlength="6"></td>
  	</tr>
  	<tr>
  	<td colspan="2" ><p id="errMsg" style="color:red"><%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%></p></td>
  	</tr>
  	<tr>
  	<td>
<input type="submit" value="Register" >
</td>
<td>
<input type="button" value="Cancel" onclick="cancelRegister()" >
</td>
</tr>
  </table>  
</form>
</body>
</html>