package musicx.servlet;

import java.io.IOException;
import java.math.BigInteger;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import musicx.dao.LocationDao;
import musicx.dao.UsersDao;
import musicx.model.Location;
import musicx.model.Users;
import musicx.model.Users.Gender;




/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String firstName, lastName, userName, password, gender,location;
		//Date dob;
		int phone;
		
		firstName=request.getParameter("firstname");
		lastName=request.getParameter("lastname");
		userName=request.getParameter("userName");
		password=request.getParameter("pwd");
		gender = request.getParameter("gender");
		
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		//dob= format.parse();

	    
		if(gender.equals("male"))
			gender="M";
		else gender="F";
		
		location = request.getParameter("location");
		
		
		phone =Integer.parseInt(request.getParameter("phone"));
		
		try{
			Date dob = (Date) format.parse(request.getParameter("birthDate"));
			Timestamp dob1 = new Timestamp(dob.getTime());
			System.out.println(dob1.toString());
			LocationDao ld = LocationDao.getInstance();
			
			List<Location> locations = ld.getAllLocation();
			List<String> locationStringList = new ArrayList<>();
			
			for (int i=0;i<locations.size();i++){
				locationStringList.add(locations.get(i).getState());
			}
			
			int location_id = locations.get(locationStringList.indexOf(location)).getLocationId();
			
			UsersDao ud = UsersDao.getInstance();
			
			Users u = new Users(userName, password, location_id, firstName, lastName,Users.Gender.valueOf(gender), dob1, phone);
			
			u = ud.create(u);
			if(u!=null){
				request.setAttribute("errorMessage", "User Registered succesfully");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
	            rd.forward(request, response); 
			} 
			
			else{
				request.setAttribute("errorMessage", "User Registration Failed");
				RequestDispatcher rd = request.getRequestDispatcher("UserRegister.jsp");
	            rd.forward(request, response);
			}
		}catch (SQLException | ParseException e) {
			e.printStackTrace();
			
		}
			
	}
}
