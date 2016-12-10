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
						<li><a  href="UpdateUser.jsp"  >Account</a></li>
						<li><a  href="LogoutServlet"  >Logout</a></li>
					</ul>
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
	<div class="banner">
		<div class="container">
			  <script src="js/responsiveslides.min.js"></script>
  <script>
    $(function () {
      $("#slider").responsiveSlides({
      	auto: true,
      	nav: true,
      	speed: 500,
        namespace: "callbacks",
        pager: true,
      });
    });
  </script>
			<div  id="top" class="callbacks_container">
			<ul class="rslides" id="slider">
			    <li>
					
						<div class="banner-text">
							<h3>MusicX - Music Recommendation Portal</h3>
						<p>We provide you a platform to cue into music based on your likings. Its easy to use and it's free. All you need to do is signup! and that doesn't take more than a minute!</p>
						<a href="#">Learn More</a>
						</div>
				
				</li>
				<li>
					
						<div class="banner-text">
							<h3>Tracks, Genres, Albums, Artists - ANYTHING! </h3>
						<p>Our platform will help you search your favourite music in a really easy way! You need to know everything about your favourite track, we'll make it simple for you!</p>
												<a href="#">Learn More</a>

						</div>
					
				</li>
				<li>
						<div class="banner-text">
							<h3>Our music philosophy</h3>
						<p>We believe in providing recommendations to people who are really enthusiastic about music. If you're just a normal chap who streams into rock or a deejay who's concerned with bpm. We
						will provide you all the info.</p>
								<a href="#">Learn More</a>

						</div>
					
				</li>
			</ul>
		</div>

	</div>
	</div>

<!--content-->
<div class="content">
	<div class="container">
	<!----->
	</br>
	<div class="content-top-bottom">
		<h2>Featured</h2>
		<div class="col-md-6 men">
			<a href="TopTracksServlet" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="images/t1.jpg" alt="">
				<div class="b-wrapper">
									<h3 class="b-animate b-from-top top-in   b-delay03 ">
										<span>Top Rated Tracks</span>	
									</h3>
								</div>
			</a>
			
			
		</div>
		<div class="col-md-6">
			<div class="col-md1 ">
				<a href="MostPlayedTracksServlet" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="images/t2.jpg" alt="">
					<div class="b-wrapper">
									<h3 class="b-animate b-from-top top-in1   b-delay03 ">
										<span>Most Played Tracks</span>	
									</h3>
								</div>
				</a>
				
			</div>
			<div class="col-md2">
				<div class="col-md-6 men1">
					<a href="TracksByPopularArtistsServlet" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="images/t3.jpg" alt="">
							<div class="b-wrapper">
									<h3 class="b-animate b-from-top top-in2   b-delay03 ">
										<span>Tracks By Popular Artists</span>	
									</h3>
								</div>
					</a>
					
				</div>
				<div class="col-md-6 men2">
					<a href="TracksByPopularAlbumsServlet" class="b-link-stripe b-animate-go  thickbox"><img class="img-responsive" src="images/t4.jpg" alt="">
							<div class="b-wrapper">
									<h3 class="b-animate b-from-top top-in2   b-delay03 ">
										<span>Tracks From Popular Albums</span>	
									</h3>
								</div>
					</a>
					
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="clearfix"> </div>
	</div>
	</div>
	</br>
	</br>
	<!---->
</div>
<div class="footer">
				<div class="container">
			<div class="footer-top-at">
			
				
				<div class="col-md-4 amet-sed">
					<h4>Newsletter</h4>
					<p>Sign Up to get updates which will harass you with advertisements for life</p>
					<form>
						<input type="text" value="" onfocus="this.value='';" onblur="if (this.value == '') {this.value ='';}">
						<input type="submit" value="Sign up">
					</form>
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
		<div class="footer-class">
		<p >MusicX - Music Recommendation Portal | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
		</div>
		</div>
</body>
</html>