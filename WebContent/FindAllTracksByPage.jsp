<%@page import="musicx.dao.TracksDao"%>
<%@page import="musicx.model.Tracks"%>

<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Tracks</title>

<!-- Bootstrap core CSS -->
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	
				<h1>Tracks Listing</h1>
				<button type="button" name="nextTracks" id="nextTracks"
					class="btn btn-sm btn-default">Next</button>
	
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
				
					<c:forEach var="track" items="${tracksList}"> 
						<tr>
						<td>${track.getTrack_title()}</td>
						<td>${track.getAlbum_id()}</td>
						<td>${track.getArtist_id()}</td>
						<td>${track.getGenre_id()}</td>
						<td>${track.getTrack_duration()}</td>
						<td>${track.getTrack_composer()}</td>
						<td>${track.getTrack_bit_rate()}</td>
						</tr>
					</c:forEach>
			</table>
			
		
   		 
   		 <table border="0" cellpadding="5" cellspacing="5" align="center">
        <tr>      
        <c:if test="${currentPage != 1}">
       		 <td width="20"><a href="tracks.do?page=${currentPage - 1}">Previous</a></td>
       		 <td width="10"></td>
   		 </c:if>     
            
        <c:set var="p" value="${currentPage}" /> 
		<c:set var="l" value="10" /> 
		<c:set var="r" value="${l / 2}" /> 
		<c:set var="t" value="${noOfPages}" />

		<c:set var="begin" value="${((p - r) > 0 ? ((p - r) < (t - l + 1) ? (p - r) : (t - l)) : 0) + 1}" />
		<c:set var="end" value="${(p + r) < t ? ((p + r) > l ? (p + r) : l) : t}" />

		<c:forEach begin="${begin}" end="${end}" var="page">
		<c:choose>
                    <c:when test="${currentPage eq page}">
                        <td width="20">${page}</td>
                    </c:when>
                    <c:otherwise>
                        <td width="20"><a href="tracks.do?page=${page}">  ${page} </a></td>
                    </c:otherwise>
                </c:choose>
		
    		
		</c:forEach>
           <c:if test="${currentPage lt noOfPages}">
    
        <td width="20"><a href="tracks.do?page=${currentPage + 1}">Next</a></td>
    </c:if>
		  
            
        </tr>
    </table>
   		 <%--For displaying Next link --%>
   
</body>
</html>