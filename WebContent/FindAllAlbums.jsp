<%@page import="musicx.dao.AlbumsDao"%>
<%@page import="musicx.model.Albums"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Albums</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="page-header">
			<div class="col-md-5">
				<h1>Albums Listing</h1>
				<button type="button" name="nextTracks" id="nextTracks"
					class="btn btn-sm btn-default">Next</button>
			</div>
		</div>
		<%
			AlbumsDao albumsDao = AlbumsDao.getInstance();
			List<Albums> albums = new ArrayList<Albums>();
			albums = albumsDao.getAllAlbums();
		%>
		<div class="col-md-5">
			<table class="table table-bordered">
				<tr>
					<td>ID</td>
					<td>Title</td>
					<td>URL</td>
				</tr>
				<%
					System.out.println(albums.size());
					for (Albums album : albums) {
				%>
				<tr>
					<td><%=album.getAlbum_id()%></td>
					<td><%=album.getAlbum_title()%></td>
					<td><%=album.getAlbum_url()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
	</div>

</body>
</html>