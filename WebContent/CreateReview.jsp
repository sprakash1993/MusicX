<%@page import="musicx.dao.*"%>
<%@page import="musicx.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Review</title>
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
	<!--content-->
<!---->


<div class="container">
<br/>
<h1 align="center">Create Review</h1>
<hr/>
<div class="page-header">
		</div>
<form action="${pageContext.request.contextPath}/CreateReviewServlet" name="frmCreateReview" method="post">
	<div class="form-group">
	<table class="table table-bordered" align="center">
	 	<tr>
	 		<th align="left">Username</th>
	 		<td><input class="form-control" type="text" id="userName" name="userName" value="<%= session.getAttribute("UserName") %>"></td>
	 	</tr>
	 	<tr>
	 		<th align="left">Track Id</th>
	 		<td><input class="form-control" type="text" id="trackId" name="trackId" value="<%= request.getParameter("trackIdReview")%>"></td>
	 	</tr>
	 	<tr>
	 		<th align="left">Rating</th>
	 		<td><input class="form-control" type="text" id="rating" name="rating" maxlength="3"></td>
	 	</tr>
	 	<tr>
	 		<th align="left">Description</th>
	 		<td>
	 			<textarea class="form-control" id="description" name="description" rows="4" cols="26"></textarea>
	 		</td>
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
	 		
	 	</tr>
	 	<tr>
	 	<td colspan="2" align="center">
	 		<input class="btn btn-sm btn-default" type="submit" value="Create Review">
	 	</td>
	 	</tr> 
	 	
	 </table>
	 </div>
	 </form>
	 </div>
</body>
</html>