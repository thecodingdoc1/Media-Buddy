package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import managers.UserManager;

/**
 * Servlet implementation class AuthController
 */
@WebServlet(urlPatterns = {"/auth", "/auth/logout"})
public class AuthController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthController() {
        super();
        
    }
  
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getServletPath().contains("logout")) {
			//login
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			long userId = UserManager.getInstance().authenticate(email, password);
			if (userId != -1) {
				HttpSession session = request.getSession(); //creates new session ans associates it with session id, in this case userId
				session.setAttribute("userId",  userId); //utilizes cookies so needs to be enabled for it to work, valid by default for 30 minutes without actions
				//can invoke set max time interval on session, -1 is forever, or other time limits
				
				request.getRequestDispatcher("bookmark/mybooks").forward(request,  response); //forwarding to servlet, does not need /
			} else {
				request.getRequestDispatcher("/login.jsp").forward(request,  response); //forwarding to jsp, needs /
			}
		} else {
			request.getSession().invalidate();
			request.getRequestDispatcher("/login.jsp").forward(request,  response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//since method is post, goes to doPost method
		doGet(request, response);
	}

}
