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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */



	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getSession().getAttribute("UserName").toString();
		
		System.out.println(username);
		
		UsersDao ud = UsersDao.getInstance();
		

		try {
			
			Users u = ud.delete(username);

			if (u == null) {
				request.setAttribute("errorMessage", "User Deleted succesfully");
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
				request.getSession().setAttribute("UserName", null);
				rd.forward(request, response);
			}

			else {
				request.setAttribute("errorMessage", "User Deletion Failed");
				RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
				rd.forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}

	}

}
