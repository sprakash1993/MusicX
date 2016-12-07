package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicx.dao.TracksDao;
import musicx.model.Tracks;

/**
 * Servlet implementation class TracksSearchServlet
 */
@WebServlet("/TracksSearchServlet")
public class TracksSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String searchKey = request.getParameter("searchText");
		TracksDao tracksDao = TracksDao.getInstance();
		List<Tracks> tracks = new ArrayList<Tracks>();
		System.out.println(searchKey);
		try{
		tracks = tracksDao.getTracksByTrackTitleQuery(searchKey);
		System.out.println(tracks.size());
		if(tracks.size()>0){
				request.setAttribute("tracksResult", tracks);
				RequestDispatcher r = request.getRequestDispatcher("FindTracksMatchingWords.jsp");
				r.forward(request, response); 
		 }
		else {
			request.setAttribute("errorMessage", "No results found");
			RequestDispatcher r = request.getRequestDispatcher("FindTracksMatchingWords.jsp");
			r.forward(request, response); 
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
