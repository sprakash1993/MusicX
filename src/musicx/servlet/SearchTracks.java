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
 * Servlet implementation class SearchTracks
 */
@WebServlet("/SearchTracks")
public class SearchTracks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchTracks() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
        String text = request.getParameter("searchText");
        
        System.out.println("Hi");
        
        request.getSession().setAttribute("searchText", text);
        
        response.sendRedirect("searchTracks.do");
        
        
 
	}


}
