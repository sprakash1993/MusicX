<%@page import="musicx.dao.LocationDao"%>
<%@page import="musicx.model.Location"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>MusicX - Music Recommendation Portal</title>
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
<!--header-->
<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="search">
					<form>
						<input type="text" value="Search " onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Search';}">
						<input type="submit" value="Go">
					</form>
			</div>
			<div class="header-left">		
					<ul>
						<li ><a href="login.html"  >Login</a></li>
						<li><a  href="register.html"  >Register</a></li>

					</ul>
					<div class="clearfix"> </div>
			</div>
				<div class="clearfix"> </div>
		</div>
		</div>
		<div class="container">
			<div class="head-top">
				<div class="logo">
				<h1>MusicX</h1>
				</div>
		  <div class=" h_menu4">
				<ul class="memenu skyblue">
					  <li class="active grid"><a class="color8" href="index.html">Home</a></li>	
				      <li><a class="color1" href="#">Genres</a>
				      	<div class="mepanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Popular Genres</h4>
									<ul>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
										<li><a href="products.html">Genre1</a></li>
									</ul>	
								</div>												
							</div>
						  </div>
						</div>
					</li>
				    <li class="grid"><a class="color1" href="#">Artists</a>
					  	<div class="mepanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Trending Artists</h4>
									<ul>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
										<li><a href="products.html">Artist1</a></li>
									</ul>	
								</div>												
							</div>
						  </div>
						</div>
			    </li>
				<li class="grid"><a class="color1" href="#">Albums</a>
					  	<div class="mepanel">
						<div class="row">
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<ul>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
									</ul>	
								</div>							
							</div>
							<div class="col1">
								<div class="h_nav">
									<h4>Trending Albums</h4>
									<ul>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
										<li><a href="products.html">Album1</a></li>
									</ul>	
								</div>												
							</div>
						  </div>
						</div>
			    </li>
			  </ul> 
			</div>
				
				<div class="clearfix"> </div>
		</div>
		</div>

	</div>
	<!--header-ends-->
<% LocationDao ld = LocationDao.getInstance();
List<Location> locations = ld.getAllLocation();%>
<div class=" container">
<div class=" register">
	<h1>Register</h1>
<form name="frmUserRegister" action="UserRegister" method="post" onsubmit="return validateForm()">
  <h1>Register</h1>
  
  <div class="col-md-6 register-top-grid">
					<div>
						 <span>Username</span>
						<input type="text" id="userName" name="userName"></input>
					 </div>
					 <div>
						 <span>Password</span>
						 <input type="password" id="pwd" name="pwd">
					 </div>
					 <div>
						 <span>Reenter Password</span>
						<input type="password" id="repwd" name="repwd" onchange="checkPass()" >
					 </div>
					 <div>
						<span>First Name</span>
						<input type="text" id="firstname" name="firstname"></input>
					 </div>
					 <div>
						<span>Last Name</span>
						<input type="text" id="lastname" name="lastname"></input>
					 </div>
					 
					 </div>
				     <div class="col-md-6 register-bottom-grid">
						   
							 <div>
								<span>Date Of Birth</span>
								<input type="date" id="birthDate" name="birthDate">
							 </div>
							 <div>
								<span>Location</span>
								<select  name="location" id="location">
			<option>--Select--</option>
        		<% for(Location l : locations){  %>
            <option><%= l.getState()%></option>
            <% } %>     
        </select>
							 </div>
							<div>
								<span>Gender</span>
								<input type="radio" name="gender" value="male" checked> Male
  <input type="radio" name="gender" id="gender" value="female"> Female
							 </div>
							 <div>
								<span>Phone</span>
								<input type="number" id="phone" name="phone" maxlength="6">
							 </div>
							 </br>
							 </br>
							 <input type="submit" value="Submit">
							 <input type="button" value="Cancel" onclick="cancelRegister()" >
					 </div>
					 <div class="clearfix"> </div>
</form>
</div>
</div>
<!--//content-->
<div class="footer">
				<div class="container">
			<div class="footer-top-at">
			
				<div class="col-md-4 amet-sed">
				<h4>MORE INFO</h4>
				<ul class="nav-bottom">
						<li><a href="#">How to order</a></li>
						<li><a href="#">FAQ</a></li>
						<li><a href="contact.html">Location</a></li>
						<li><a href="#">Shipping</a></li>
						<li><a href="#">Membership</a></li>	
					</ul>	
				</div>
				<div class="col-md-4 amet-sed ">
				<h4>CONTACT US</h4>
				
					<p>
Contrary to popular belief</p>
					<p>The standard chunk</p>
					<p>office:  +12 34 995 0792</p>
					<ul class="social">
						<li><a href="#"><i> </i></a></li>						
						<li><a href="#"><i class="twitter"> </i></a></li>
						<li><a href="#"><i class="rss"> </i></a></li>
						<li><a href="#"><i class="gmail"> </i></a></li>
						
					</ul>
				</div>
				<div class="col-md-4 amet-sed">
					<h4>Newsletter</h4>
					<p>Sign Up to get all news update
and promo</p>
					<form>
						<input type="text" value="" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}">
						<input type="submit" value="Sign up">
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="footer-class">
		<p >© 2015 New store All Rights Reserved | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
		</div>
		</div>
</body>
</html>