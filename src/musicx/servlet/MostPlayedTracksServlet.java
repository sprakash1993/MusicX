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
 * Servlet implementation class TrackSearch
 */
@WebServlet("/MostPlayedTracksServlet")
public class MostPlayedTracksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MostPlayedTracksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		int page = 1;
        int recordsPerPage = 18;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));
        
		TracksDao tracksDao = TracksDao.getInstance();
		List<Tracks> tracks = new ArrayList<Tracks>();
		
		
		try{
			tracks = tracksDao.getMostPlayedTracks((page-1)*recordsPerPage,
                    recordsPerPage);
			System.out.println(tracks.size());
			int noOfRecords = tracksDao.getNoOfSpecialRecords();
	        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
			
	        request.setAttribute("tracksList", tracks);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", page);
	        
	        RequestDispatcher view = request.getRequestDispatcher("TracksByFilteredResults.jsp");
	        view.forward(request, response);
	        
			}catch (SQLException e) {
				e.printStackTrace();
			}
	}

	
	

}
