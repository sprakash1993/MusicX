<%@page import="musicx.dao.TracksDao"%>
<%@page import="musicx.model.Tracks"%>

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tracks</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="page-header">
			<div class="col-md-5">
				<h1>Tracks Listing</h1>
				<button type="button" name="nextTracks" id="nextTracks"
					class="btn btn-sm btn-default">Next</button>
			</div>
		</div>
		<div class="col-md-1">
			<table class="table table-bordered">
				<thead>
					<tr>
						<td>Title</td>
						<td>Album</td>
						<td>Artist</td>
						<td>Genre</td>
						<td>Duration</td>
						<td>Composer</td>
						<td>Bit Rate</td>
					</tr>
				</thead>
				<tbody>
					<%
						TracksDao tracksDao = TracksDao.getInstance();
						List<Tracks> tracks = new ArrayList<Tracks>();
						int currentIndex = 0;
						tracks = tracksDao.getAllTracks();
					%>
					<%
						System.out.println(tracks.size());
						for (Tracks track : tracks) {
					%>
					<tr>
						<td><%=track.getTrack_title()%></td>
						<td><%=track.getAlbum_id()%></td>
						<td><%=track.getArtist_id()%></td>
						<td><%=track.getGenre_id()%></td>
						<td><%=track.getTrack_duration()%></td>
						<td><%=track.getTrack_composer()%></td>
						<td><%=track.getTrack_bit_rate()%></td>
					</tr>
					<%
						}
					%>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>