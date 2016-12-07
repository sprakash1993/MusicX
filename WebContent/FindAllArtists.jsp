<%@page import="musicx.dao.ArtistsDao"%>
<%@page import="musicx.model.Artists"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Artists</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="page-header">
			<div class="col-md-5">
				<h1>Artists Listing</h1>
				<button type="button" name="nextTracks" id="nextTracks"
					class="btn btn-sm btn-default">Next</button>
			</div>
		</div>
		<%
			ArtistsDao artistsDao = ArtistsDao.getInstance();
			List<Artists> artists = new ArrayList<Artists>();
			artists = artistsDao.getAllArtists();
		%>
		<div class="col-md-5">
			<table class="table table-bordered">
				<tr>
					<td>ID</td>
					<td>Name</td>
					<td>URL</td>
					<td>Website</td>
				</tr>
				<%
					System.out.println(artists.size());
					for (Artists artist : artists) {
				%>
				<tr>
					<td><%=artist.getArtistId()%></td>
					<td><%=artist.getArtistName()%></td>
					<td><%=artist.getArtistUrl()%></td>
					<td><%=artist.getArtistWebsite()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</body>
</html>