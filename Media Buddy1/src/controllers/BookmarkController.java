package controllers;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.KidFriendlyStatus;
import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;
import managers.UserManager;

@WebServlet(urlPatterns = {"/bookmark", "/bookmark/savebook", "/bookmark/mybooks", "/bookmark/savemovie", "/bookmark/usmovie", "/bookmark/usbook"})
public class BookmarkController extends HttpServlet { // woul dbe servlet in real java EE application
	/*private static BookmarkController instance = new BookmarkController();
	private BookmarkController() {}
	public static BookmarkController getInstance() {
		return instance;
	}*/
	 public BookmarkController() {
	       
	    }
	
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 RequestDispatcher dispatcher = null;
		 System.out.println("Servlet path: " + request.getServletPath());
		 if(request.getSession().getAttribute("userId") != null) {
			 long userId = (long) request.getSession().getAttribute("userId"); //getAttribute will return an object, getParameter returns a string
			 if(request.getServletPath().contains("savebook")) {
				 //savebook
				 dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				 String bid = request.getParameter("bid");
				 User user = UserManager.getInstance().getUser(userId);
				 Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
				 BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
					
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books",  list);
				
				 Collection<Bookmark> listM = BookmarkManager.getInstance().getMovies(true, userId);
				 request.setAttribute("movies",  listM);
			 } else if(request.getServletPath().contains("mybooks")) {
				 //mybooks
				 dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				 Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				 request.setAttribute("books",  list);
				 
				 Collection<Bookmark> listM = BookmarkManager.getInstance().getMovies(true, userId);
	
				 request.setAttribute("movies",  listM);
			 } else if(request.getServletPath().contains("savemovie")) {
				
				 dispatcher =request.getRequestDispatcher("/mybooks.jsp");
				 String mid = request.getParameter("mid");
				 User user = UserManager.getInstance().getUser(userId);

				 Bookmark bookmark = BookmarkManager.getInstance().getMovie(Long.parseLong(mid));
				
				 BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
				
				 Collection<Bookmark> list = BookmarkManager.getInstance().getMovies(true, userId);
				 request.setAttribute("movies",  list);
				 
				 Collection<Bookmark> listB = BookmarkManager.getInstance().getBooks(true, userId);
					request.setAttribute("books",  listB);
			 } else if(request.getServletPath().contains("usmovie")){ 
				 
				
				 dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				 String mid = request.getParameter("mid");
				 User user = UserManager.getInstance().getUser(userId);
				 Bookmark bookmark = BookmarkManager.getInstance().getMovie(Long.parseLong(mid));
				
				 BookmarkManager.getInstance().unsaveUserBookmark(user, bookmark);
				 
				 Collection<Bookmark> list = BookmarkManager.getInstance().getMovies(true, userId);
				request.setAttribute("movies",  list);
					
				Collection<Bookmark> listB = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books",  listB);
			 } else if (request.getServletPath().contains("usbook")) {
				 
				 dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				 String bid = request.getParameter("bid");
				 User user = UserManager.getInstance().getUser(userId);
				 Bookmark bookmark = BookmarkManager.getInstance().getBook(Long.parseLong(bid));
				 BookmarkManager.getInstance().unsaveUserBookmark(user, bookmark);
					
				Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(true, userId);
				request.setAttribute("books",  list);
				
				Collection<Bookmark> listM = BookmarkManager.getInstance().getMovies(true, userId);
				request.setAttribute("movies",  listM);
			 }else {
				 //browse
				 dispatcher = request.getRequestDispatcher("/mybooks.jsp");
				 Collection<Bookmark> list = BookmarkManager.getInstance().getBooks(false, userId);
				 request.setAttribute("books",  list);
			 
				 Collection<Bookmark> listM = BookmarkManager.getInstance().getMovies(false, userId);
				 request.setAttribute("movies",  listM);
				 //System.out.println(listM);
				 request.getRequestDispatcher("/browse.jsp").forward(request,  response);//may delete
			 }
		 } else {
			 dispatcher = request.getRequestDispatcher("/login.jsp");
		 }
		 
		 dispatcher.forward(request,  response);
	 }
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//since method is post, goes to doPost method
			doGet(request, response);
		}
	public void saveUserBookmark(User user, Bookmark bookmark) {
		BookmarkManager.getInstance().saveUserBookmark(user, bookmark);
		
	}
	public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark)  {
		BookmarkManager.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
		
	}
	public void share(User user, Bookmark bookmark) { // simple a relay to the manager
		BookmarkManager.getInstance().share(user, bookmark);
		
	}
}
