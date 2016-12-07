<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Create Review</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="page-header">
			<div class="col-md-5">
				<h1 align="center">Create Review</h1>
			</div>
		</div>
<form action="${pageContext.request.contextPath}/CreateReviewServlet" name="frmCreateReview" method="post">
	<div class="form-group">
	<table class="table table-bordered" align="center">
	 	<tr>
	 		<th align="left">Username</th>
	 		<td><input class="form-control" type="text" id="userName" name="userName"></td>
	 	</tr>
	 	<tr>
	 		<th align="left">Track Id</th>
	 		<td><input class="form-control" type="text" id="trackId" name="trackId"></td>
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