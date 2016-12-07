<%@page import="musicx.dao.ReviewsDao"%>
<%@page import="musicx.model.Reviews"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Reviews</title>
<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

<script type="text/javascript">
	function validateForm() {
		var button1 = document.getElementById("buttonReviewByUser");
		var button2 = document.getElementById("buttonReviewByTrackId");
		var name = document.getElementById("userName").value;
		var trackID = document.getElementById("trackId").value;
		if (name == "" && button1.onclick == true) {
			alert("Enter Username");
			return false;
		} else if (trackID == "" && button2.onclick == true) {
			alert("Enter TrackID");
			return false;
		}
	}
</script>
</head>
<body>

	<div class="page-header">
		<div class="col-md-5">
			<h1>Music Reviews</h1>
		</div>
	</div>
	<form action="ReviewsServlet" name="frmReviews" method="post">
		<div class="col-md-1">
			<table class="table table-striped">
				<tr>
					<td><input type="text" id="userName" name="userName">
					</td>
					<td><input class="btn btn-secondary btn-sm" type="submit"
						name="buttonReviewByUser" id="buttonReviewByUser"
						value="Search By User"></td>
				</tr>
				<tr>
					<td><input type="text" id="trackId" name="trackId"></td>
					<td><input class="btn btn-secondary btn-sm" type="submit"
						name="buttonReviewByTrackId" id="buttonReviewByTrackId"
						value="Search By TrackID"></td>
				</tr>
				<tr>
					<td colspan="2"><p id="errMsg" style="color: red">
							<%
								
							%>
						</p></td>
				</tr>
			</table>
		</div>
		<%
			ReviewsDao rd = new ReviewsDao();
			List<Reviews> reviews = new ArrayList<Reviews>();
			if (null != request.getAttribute("reviewsResult")) {
				reviews = (ArrayList<Reviews>) request.getAttribute("reviewsResult");
				//System.out.println(reviews.size());
			}
		%>

		<%
			if (reviews.size() != 0) {
		%>
		<div class="col-md-1">
			<table class="table table-striped">
				<tr>
					<td>Review ID</td>
					<td>Username</td>
					<td>Track Id</td>
					<td>Rating</td>
					<td>Description</td>
				</tr>
				<%
					for (Reviews r : reviews) {
				%>
				<tr>
					<td><%=r.getReview_id()%></td>
					<td><%=r.getUsername()%></td>
					<td><%=r.getTrack_id()%></td>
					<td><%=r.getRating()%></td>
					<td><%=r.getDescription()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</div>
		<%
			}
		%>

	</form>
</body>
</html>