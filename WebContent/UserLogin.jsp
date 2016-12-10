<%@page import="musicx.dao.*"%>
<%@page import="musicx.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MusicX - Music Recommendation Portal</title>
<link rel="shortcut icon" href="images/favicon.ico">
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="New Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900' rel='stylesheet' type='text/css'><!--//fonts-->
<!-- start menu -->
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>
<script src="js/simpleCart.min.js"> </script>

<script type="text/javascript">
function validateForm() {
	var uname = document.forms["frmUserLogin"]["username"].value;
	var password = document.forms["frmUserLogin"]["pwd"].value;
	if (uname == "" || uname == null){
		alert("Enter username");
		return false;		
	}
	if (password == "" || password == null){
		alert("Enter password");
		return false;		
	}
}

function registerUser(){
	window.location.href="UserRegister.jsp";	
}

</script>

</head>
<body>
<!--header-->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="search">
					<form action="SearchTracks" method="post" name="frmSearch">
						<input type="text" id="searchText" name="searchText" value="Search " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
						<input type="submit" value="Go">
					</form>
			</div>
			<div class="header-left">		
					<ul>
						<li ><a href="UserLogin.jsp"  >Login</a></li>
						<li><a  href="UserRegister.jsp"  >Register</a></li>
						<li><a  href="UserPlayListServlet"  >My Playlist</a></li>
						<li><a  href="UpdateUser.jsp"  >Account</a></li>

					</ul>
					<div class="clearfix"> </div>
			</div>
				<div class="clearfix"> </div>
		</div>
		</div>
		<div class="container">
			<div class="head-top">
				<div class="logo">
				<img alt="" src="images/MusicxLogos-18.png" width="120" height="60">
				</div>
		  <div class=" h_menu4">
				<ul class="memenu skyblue">
					 <li class="active grid"><a class="color8" href="UserHome.jsp">Home</a></li>	
				      
				    
			  </ul> 
			</div>
				
				<div class="clearfix"> </div>
		</div>
		</div>

	</div>
	<!--header-ends-->
	<!--content-->
<div class="container">
		<div class="account">
		<h1>Account</h1>
		<div class="account-pass">
		<div class="col-md-12 account-top">
<form name="frmUserLogin" action="UserLogin" method="post" onsubmit="return validateForm()">
  <div> 	
				<span>Username</span>
				<input type="text" id="username" name="username"></input>
			</div>
			<div> 
				<span >Password</span>
				<input type="password" id="pwd" name="pwd">
			</div>	

  	<div><p id="errMsg" style="color:red"><%
    if(null!=request.getAttribute("errorMessage"))
    {
        out.println(request.getAttribute("errorMessage"));
    }
%></p></div>

<input type="submit" value="Login">
  
</form>
</div>
		
	<div class="clearfix"> </div>
	</div>
	</div>

</div>

</body>
</html>