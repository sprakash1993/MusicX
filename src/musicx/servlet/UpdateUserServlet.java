package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
Users u = (Users) request.getSession().getAttribute("userObj");
		
		String fname, lastName, location;
		int phone;
		
		fname=request.getParameter("firstname");
		lastName=request.getParameter("lastname");
		
		phone =Integer.parseInt(request.getParameter("phone"));
		
		try{
			
			UsersDao ud = UsersDao.getInstance();
			
			u.setFirstname(fname);
			u.setLastname(lastName);
			u.setPhone_number(phone);
			
			u = ud.updateUser(u);
			
			if(u!=null){
				request.setAttribute("errorMessage", "User Updated succesfully");
				RequestDispatcher rd = request.getRequestDispatcher("UserHome.jsp");
	            rd.forward(request, response); 
			} 
			
			else{
				request.setAttribute("errorMessage", "User Update Failed");
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
	            rd.forward(request, response);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}

}
