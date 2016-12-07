package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import musicx.dao.UsersDao;

import musicx.model.Users;

/**
 * Servlet implementation class UserDeleteServlet
 */
@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("userName");
		UsersDao ud = UsersDao.getInstance();
		
		try{
			Users u = ud.getUserByUsername(username);
			
			if(ud.delete(u)==null){
				request.setAttribute("errorMessage", "User Deleted succesfully");
				RequestDispatcher r = request.getRequestDispatcher("UserDelete.jsp");
	            r.forward(request, response); 
			} 
			
			else{
				request.setAttribute("errorMessage", "User Delete Failed");
				RequestDispatcher r = request.getRequestDispatcher("UserDelete.jsp");
	            r.forward(request, response);
			}
		}catch (SQLException e) {
			e.printStackTrace();
			}
	}

}
