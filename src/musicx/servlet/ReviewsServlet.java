package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import musicx.dao.ReviewsDao;
import musicx.model.Reviews;

/**
 * Servlet implementation class ReviewsServlet
 */
@WebServlet("/ReviewsServlet")
public class ReviewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		ReviewsDao rd = new ReviewsDao();
		List<Reviews> reviews1 = new ArrayList<Reviews>();
		//System.out.println(request.getParameter("buttonReviewByUser"));
		
		if (request.getParameter("buttonReviewByUser") != null) {
			String username = request.getParameter("userName");
			try{
			reviews1 = rd.getReviewsByUserName(username);
			//System.out.println("r1 "+reviews1.size());
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (request.getParameter("buttonReviewByTrackId") != null) {
			 String trackId = request.getParameter("trackId");
			 try{
					reviews1 = rd.getReviewsByTrackId(trackId);
					
					}catch (SQLException e) {
						e.printStackTrace();
					}
		}
		//System.out.println("r1 "+reviews1.size());
		request.setAttribute("reviewsResult", reviews1);
		RequestDispatcher r = request.getRequestDispatcher("Reviews.jsp");
        r.forward(request, response);
	}

}
