<%@page import="musicx.dao.*"%>
<%@page import="musicx.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- start menu -->
<link href="css/memenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/memenu.js"></script>
<script>$(document).ready(function(){$(".memenu").memenu();});</script>
<script src="js/simpleCart.min.js"> </script>
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
						<li><a  href="playlist.html"  >My Playlist</a></li>
						<li><a  href="account.html"  >Account</a></li>

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
	<!--content-->
<!---->
		<div class="product">
			<div class="container">
				
				<div class="col-md-12 product1">
				
				<div class=" bottom-product">
				<c:forEach var="track" items="${tracksList}"> 
					<div class="col-md-2 bottom-cd simpleCart_shelfItem">
						<div class="product-at ">
							<a href="single.html"><img class="special-img" src="${track.getTrack_image_file()}" height="150" alt="">
							<div class="pro-grid">
							<div class="col-md-12">
										<a href="blog.html"><span class="buy-in"><i class="glyphicon glyphicon-play"></i></span></a>
										<a href="blog.html"><span class="buy-in"><i class="glyphicon glyphicon-heart-empty"></i></span></a>
										<a href="blog.html"><span class="buy-in"><i class="glyphicon glyphicon-star"></i></span></a>

							</div>
							</div>
						</a>	
						</div>
						<p class="tun">
						${fn:substring(track.getTrack_title(),0,15)}
					</p>
						<a href="PlaylistServlet?trackId=${track.getTrack_id()}" class="item_add"><p class="number item_price"><i class="glyphicon glyphicon-plus"></i>Add to Playlist</p></a>					
					</div>
					</c:forEach>
					</div>
					
					<div class="clearfix"> </div>
				</div>
				<div class="clearfix"> </div>
				
				<nav class="in">
				<c:set var="p" value="${currentPage}" /> 
		<c:set var="l" value="10" /> 
		<c:set var="r" value="${l / 2}" /> 
		<c:set var="t" value="${noOfPages}" />

		<c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}" />
		<c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}" />
		
		
				  <ul class="pagination">
				  <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">«</span></a></li>
					
				  <c:forEach begin="${begin}" end="${end}" var="page">
					
					 
					 <c:choose>
                    <c:when test="${currentPage eq page}">
                       
                        <li class="active"><a href="#">${page} <span class="sr-only">(current)</span></a></li>
                    </c:when>
                    <c:otherwise>
                       
                        <li><a href="tracks.do?page=${page}">${page} <span class="sr-only"></span></a></li>
                    </c:otherwise>
                    </c:choose>
					 </c:forEach>
					 <li> <a href="#" aria-label="Next"><span aria-hidden="true">»</span> </a> </li>
				  </ul>
				  
				  

		
				  
				</nav>
				</div>
		
		
		</div>
		
		<!--//content-->
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