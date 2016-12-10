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
	
	var fName = document.forms["frmUserRegister"]["firstname"].value;
	var lName = document.forms["frmUserRegister"]["lastname"].value;

	
	if (fName == "" || fName == null){
		alert("Enter First Name");
		return false;		
	}
	
	if (lName == "" || lName == null){
		alert("Enter Last Name");
		return false;		
	}
	
			
	}
	



function deleteUser(){
	window.location.href="DeleteUserServlet";	
}
</script>

</head>
<body>
<p style="align:right"><% if (session != null) {
	if (session.getAttribute("UserName") != null) {
		String name = (String) session.getAttribute("UserName");
		out.print("Hello " + name + ",  Welcome to your Profile");
	} else {
		out.println("No Session");
		request.setAttribute("errorMessage", "Session Expired, Login Again.");
		RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
        rd.forward(request, response); 
	}
} %> </p>
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
						<li><a  href="UserPlayListServlet"  >My Playlist</a></li>
						<li><a  href="#"  >Account</a></li>
						<li><a href="DeleteUserServlet">Delete Account</a></li>
						<li><a  href="LogoutServlet"  >Logout</a></li>
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
				      <li><a class="color1" href="#">Genres</a>
				      	<div class="mepanel">
						<div class="row">
						<% GenresDao gd = GenresDao.getInstance();
							   List<Genres> genres = gd.getAllGenres();
							   int i,j;
							   for(i=0;i<3;i++){%>
							<div class="col1">
							
								<div class="h_nav">
									<ul>
									<%for(j=5*i;j<5*(i+1);j++){ %>
										<li><a class="truncate" href="searchTracksGenre.do?genreID=<%= genres.get(j).getGenre_id() %>"><%= genres.get(j).getGenre_title() %></a></li>
									    
										<%} %>
									</ul>	
								</div>							
							</div>
							<%} %>
							
						  </div>
						</div>
					</li>
				    <li class="grid"><a class="color1" href="#">Artists</a>
					  	<div class="mepanel">
					  	<h3 align="center">Popular Artists</h3>
				      	<hr/>
						<div class="row">
							<% ArtistsDao ard = ArtistsDao.getInstance();
							   List<Artists> artists = ard.getAllArtists();
							   for(i=0;i<3;i++){%>
							<div class="col1">
							
								<div class="h_nav">
									<ul>
									<%for(j=5*i;j<5*(i+1);j++){ %>
										<li><a class="truncate" href="searchTracksArtist.do?artistId=<%= artists.get(j).getArtistId()%>"><%= artists.get(j).getArtistName() %></a></li>
									    
										<%} %>
									</ul>	
								</div>							
							</div>
							<%} %>
						  </div>
						</div>
			    </li>
				<li class="grid"><a class="color1" href="#">Albums</a>
					  	<div class="mepanel">
					  	<h3 align="center">Popular Albums</h3>
				      	<hr/>
						<div class="row">
							<% AlbumsDao ald = AlbumsDao.getInstance();
							   List<Albums> albums = ald.getAllAlbums();
							   for(i=0;i<3;i++){%>
							<div class="col1">
								<div class="h_nav">
									<ul>
									<%for(j=5*i;j<5*(i+1);j++){ %>
										<li><a class="truncate" href="searchTracksAlbum.do?albumId=<%=albums.get(j).getAlbum_id()%>"><%= albums.get(j).getAlbum_title() %></a></li>
									    
										<%} %>
									</ul>	
								</div>							
							</div>
							<%} %>
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
	<h1>Update User</h1>
	<% UsersDao ud = UsersDao.getInstance();
	Users u = ud.getUserByUsername(session.getAttribute("UserName").toString());
	session.setAttribute("userObj", u);%>
	
<form name="frmUserUpdate" action="UpdateUserServlet" method="get" onsubmit="return validateForm()">
  
  <div class="col-md-6 register-top-grid">
  <div>
						 <span>Username</span>
						<input type="text" id="userName" name="userName" disabled="disabled" value="<%=session.getAttribute("UserName")%>"></input>
					 </div>
					
					 <div>
						<span>First Name</span>
						<input type="text" id="firstname" name="firstname" value="<%=u.getFirstname()%>"></input>
					 </div>
					 <div>
						<span>Last Name</span>
						<input type="text" id="lastname" name="lastname" value="<%=u.getLastname()%>"></input>
					 </div>
							
							 <div>
								<span>Phone</span>
								<input type="number" id="phone" name="phone" maxlength="6" value="<%= u.getPhone_number() %>">
							 </div>
							 
							  
							 <br/>
							 <br/>
							 
							  <input type="submit" class="btn btn-warning btn-lg" value="Update">
							 
					 
					 </div>
				     <div class="col-md-6 register-bottom-grid">
				     
					 
					 <div><p id="errMsg" style="color:red"><%
    					if(null!=request.getAttribute("errorMessage"))
    					{
        				out.println(request.getAttribute("errorMessage"));
    					}
					%></p></div>
						   
							
							
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