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

import musicx.dao.LocationDao;
import musicx.dao.UsersDao;
import musicx.model.Location;
import musicx.model.Users;

/**
 * Servlet implementation class UpdateUserLocationServlet
 */
@WebServlet("/UpdateUserLocationServlet")
public class UpdateUserLocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String username = request.getParameter("userName");
		String location = request.getParameter("location");
		
		LocationDao ld = LocationDao.getInstance();
		try{
		List<Location> locations = ld.getAllLocation();
		List<String> locationStringList = new ArrayList<>();
		
		for (int i=0;i<locations.size();i++){
			locationStringList.add(locations.get(i).getState());
		}
		
		int location_id = locations.get(locationStringList.indexOf(location)).getLocationId();
		//System.out.println(location_id);
		
		UsersDao ud = UsersDao.getInstance();
		Users u = ud.getUserByUsername(username);
		u = ud.updateLocationId(u, location_id);
		if(u.getLocation_id()==location_id){
			request.setAttribute("errorMessage", "Location updated succesfully");
			RequestDispatcher r = request.getRequestDispatcher("UpdateUserLocation.jsp");
            r.forward(request, response); 
		} 
		
		else{
			request.setAttribute("errorMessage", "Location update Failed");
			RequestDispatcher r = request.getRequestDispatcher("UpdateUserLocation.jsp");
            r.forward(request, response);
		}
		
		}catch (SQLException e) {
		e.printStackTrace();
		}
		
		
	}

}
