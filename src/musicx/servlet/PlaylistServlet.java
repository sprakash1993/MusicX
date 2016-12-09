package musicx.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicx.dao.PlaylistDao;
import musicx.dao.PlaylistTracksDao;
import musicx.model.*;

/**
 * Servlet implementation class PlaylistServlet
 */
@WebServlet("/PlaylistServlet")
public class PlaylistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaylistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getSession().getAttribute("UserName").toString();
		
		String trackId = request.getParameter("trackId");
		List<Tracks> tracksList = (List<Tracks>)request.getSession().getAttribute("tracksList");
		String noOfPages = request.getParameter("noOfPages");
		String currentPage = request.getParameter("currentPage");
		
		PlaylistDao playlistDao = PlaylistDao.getInstance();
		
		Playlist p = new Playlist(userName);
		try{
		List<Playlist> playlists = playlistDao.getPlaylistByUserName(userName);
		
		if(playlists.size()==0){
			p = playlistDao.create(p);
		}
		else{
			p = playlists.get(0);
		}
		
		PlaylistTracksDao pd = PlaylistTracksDao.getInstance();
		
		PlaylistTracks pt = new PlaylistTracks(p.getPlaylistId(),trackId);
		
		List<PlaylistTracks> plt = pd.getPlaylistTrackByPlaylistId(p.getPlaylistId());
		Boolean alreadyAdded = false;
		
		for(PlaylistTracks pl : plt){
			System.out.println(p.getPlaylistId() + " " + trackId);
			if(pl.getTrackId().equals(trackId)) {
				
				alreadyAdded = true;
				break;
			}
		}
		System.out.println(alreadyAdded);
		
		if(alreadyAdded==false)
			pt = pd.create(pt); 
		
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if(pt!=null){
			out.println("<script type=\"text/javascript\">");
		       out.println("alert('Track Added to playlist');");
		       out.println("</script>");
			request.setAttribute("errorMessage", "Track Added to playlist");
			RequestDispatcher rd = request.getRequestDispatcher("TracksByFilteredResults.jsp");
			
			request.setAttribute("tracksList", tracksList);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", currentPage);
			
            rd.forward(request, response); 
		} 
		
		else{
			request.setAttribute("errorMessage", "Unable to add to playlist");
			RequestDispatcher rd = request.getRequestDispatcher("TracksByFilteredResults.jsp");

			request.setAttribute("tracksList", tracksList);
	        request.setAttribute("noOfPages", noOfPages);
	        request.setAttribute("currentPage", currentPage);
			
            rd.forward(request, response);
		}
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	}

}
