package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import musicx.dao.ReviewsDao;
import musicx.model.Reviews;

/**
 * Servlet implementation class CreateReviewServlet
 */
@WebServlet("/CreateReviewServlet")
public class CreateReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userName");
		String trackId = request.getParameter("trackId");
		float rating = Float.parseFloat(request.getParameter("rating"));
		String description = request.getParameter("description");
		int reviewId =0;
		System.out.println("hi");
		ReviewsDao rd = new ReviewsDao();
		Reviews review = new Reviews(username, trackId, description, rating);
		
		try{
			review = rd.create(review);
			System.out.println("r1 "+review.getReview_id());
			}catch (SQLException e) {
				e.printStackTrace();
			}
		reviewId = review.getReview_id();
		
		if(reviewId != 0){
			request.setAttribute("errorMessage", "Review submitted succesfully");
			RequestDispatcher r = request.getRequestDispatcher("CreateReview.jsp");
            r.forward(request, response); 
		} 
		
		else{
			request.setAttribute("errorMessage", "Review submission Failed");
			RequestDispatcher r = request.getRequestDispatcher("CreateReview.jsp");
            r.forward(request, response);
		}
		
	}

}
