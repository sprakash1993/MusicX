package musicx.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import musicx.dao.UsersDao;
import musicx.model.Users;


/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uname, pass;
		
		response.setContentType("text/html");  
        //PrintWriter out=response.getWriter(); 
		
		uname = request.getParameter("username");
		pass =  request.getParameter("pwd");
		
		System.out.println(uname+ " "+ pass);
		HttpSession session = request.getSession(true);
		UsersDao ud = UsersDao.getInstance();
		
		try{
		Users u = ud.getUserByUsername(uname);
		System.out.println(u.getFirstname());
		//boolean result = true;
		if(u.getPassword().equals(pass) && u!=null){
			session.setAttribute("UserName", uname);
			response.sendRedirect("UserHome.jsp");
			return;
		}
		else{
			session.invalidate();
			request.setAttribute("errorMessage", "Invalid user or password");
			RequestDispatcher rd = request.getRequestDispatcher("UserLogin.jsp");
            rd.forward(request, response);
		}
		
		}catch (SQLException e) {
			e.printStackTrace();
			
		}
	}

}
