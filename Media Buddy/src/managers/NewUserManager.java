package managers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet (urlPatterns = {"/newUser", "/newUser/submit"})
public class NewUserManager extends HttpServlet {

	private static UserDao dao = new UserDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!request.getServletPath().contains("/submit")) {
			request.getRequestDispatcher("/newUser.jsp").forward(request,  response);
		} else {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			
			UserManager.createUser(firstName, lastName, email, password);
			request.getRequestDispatcher("/login.jsp").forward(request,  response);
			
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//since method is post, goes to doPost method
		doGet(request, response);
	}
}
