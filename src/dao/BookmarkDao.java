package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import constants.BookGenre;
import constants.MovieGenre;
import entities.Book;
import entities.Bookmark;
import entities.Movie;
import entities.UserBookmark;
import entities.WebLink;
import managers.BookmarkManager;


public class BookmarkDao {

	public void saveUserBookmark(UserBookmark userBookmark) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root") ;
				Statement stmt = conn.createStatement()) {
			if (userBookmark.getBookmark() instanceof Book) {
				saveUserBook(userBookmark, stmt);
			//} else if (userBookmark.getBookmark() instanceof WebLink) {
				//saveUserWebLink(userBookmark, stmt);
			} else {
				saveUserMovie(userBookmark, stmt);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void unsaveUserBookmark(UserBookmark userBookmark) {
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root") ;
				Statement stmt = conn.createStatement()) {
			//System.out.println("ran unsaveuserbookmark in Dao");
			if (userBookmark.getBookmark() instanceof Book) {
				//System.out.println("went into book if statement");
				unsaveUserBook(userBookmark, stmt);
			} else if (userBookmark.getBookmark() instanceof Movie) {
				//System.out.println("went into movie if 1");
				unsaveUserMovie(userBookmark, stmt);
			} else {
				//System.out.println("went into else of unsaveUserBookmark in dao");
				unsaveUserMovie(userBookmark, stmt);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void unsaveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		
		String query = "delete from User_Movie where user_id =" + userBookmark.getUser().getId() + " and movie_id= " + userBookmark.getBookmark().getId();
		stmt.executeUpdate(query);
		//System.out.println("executed delete query in unsaveusermovie");
	}

	private void unsaveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "delete from User_Book where user_id =" + userBookmark.getUser().getId() + " and book_id= " + userBookmark.getBookmark().getId();
		stmt.executeUpdate(query);
	}

	/*private void saveUserWebLink(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_WebLink (user_id, weblink_id) values (" + userBookmark.getUser().getId() + " , " + userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
		
	}*/

	private void saveUserMovie(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Movie (user_id, movie_id) values (" + userBookmark.getUser().getId() + " , " + userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
		
	}

	private void saveUserBook(UserBookmark userBookmark, Statement stmt) throws SQLException {
		String query = "insert into User_Book(user_id, book_id) values (" + userBookmark.getUser().getId() + " , " + userBookmark.getBookmark().getId() + ")";
		stmt.executeUpdate(query);
		
	}

	
	/*public void updateKidFriendlyStatus(Bookmark bookmark)  {
		int kidFriendlyStatus = bookmark.getKidFriendlyStatus().ordinal();
		long userId = bookmark.getKidFriendlyMarkedBy().getId();
		
		String tableToUpdate = "Book";
		if (bookmark instanceof Movie) {
			tableToUpdate = "Movie";
		} else if (bookmark instanceof WebLink) {
			tableToUpdate = "WebLink";
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root") ;
				Statement stmt = conn.createStatement()) {
			String query = "update " + tableToUpdate + " set kid_friendly_status = " + kidFriendlyStatus + ", kid_friendly_marked_by = " + userId + " where id = " + bookmark.getId();
			//System.out.println("query (updateKidFriendlyStatus): " + query);
			stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void sharedByInfo(Bookmark bookmark) {
		long userId = bookmark.getSharedBy().getId();
		
		String tableToUpdate = "Book";
		if (bookmark instanceof WebLink) {
			tableToUpdate = "WebLink";
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root") ;
				Statement stmt = conn.createStatement();) {
			String query = "update " + tableToUpdate + " set shared_by = " + userId + " where id = " + bookmark.getId();
			//System.out.println("query (updateKidFriendlyStatus): " + query);
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}*/

	public Collection<Bookmark> getBooks(boolean isBookmarked, long userId) {
			
			Collection<Bookmark> result = new ArrayList<>();
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				//new com.mysql.jdbc.Driver(); 
				            // OR
				//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
			
			                // OR java.sql.DriverManager
			    //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
					Statement stmt = conn.createStatement();) {			
				
				String query = "";
				if (!isBookmarked) {
					
					query = "SELECT b.id, title, image_url, publication_year, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, " +
							"amazon_rating FROM Book b, Author a, Book_Author ba WHERE b.id = ba.book_id AND ba.author_id = a.id AND " + 
							"b.id NOT IN (SELECT ub.book_id FROM User u, User_Book ub WHERE u.id = " + userId +
							" AND u.id = ub.user_id) GROUP BY b.id";				
				} else {
					query = "SELECT b.id, title, image_url, publication_year, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, " +
							"amazon_rating FROM Book b, Author a, Book_Author ba WHERE b.id = ba.book_id AND ba.author_id = a.id AND " + 
							"b.id IN (SELECT ub.book_id FROM User u, User_Book ub WHERE u.id = " + userId +
							" AND u.id = ub.user_id) GROUP BY b.id";
				}
				
				ResultSet rs = stmt.executeQuery(query);				
				
		    	while (rs.next()) {
		    		long id = rs.getLong("id");
					String title = rs.getString("title");
					String imageUrl = rs.getString("image_url");
					int publicationYear = rs.getInt("publication_year");
					//String publisher = rs.getString("name");		
					String[] authors = rs.getString("authors").split(",");			
					int genre_id = rs.getInt("book_genre_id");
					BookGenre genre = BookGenre.values()[genre_id];
					double amazonRating = rs.getDouble("amazon_rating");
					
					System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);
		    		
		    		Bookmark bookmark = BookmarkManager.getInstance().createBook(id, title, imageUrl, publicationYear, null, authors, genre, amazonRating/*, values[7]*/);
		    		result.add(bookmark); 
		    	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			return result;
		}
	
	public Bookmark getBook(long bookId) {
		Book book = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			String query = "SELECT b.id, title, image_url, publication_year, p.name, GROUP_CONCAT(a.name SEPARATOR ',') AS authors, book_genre_id, amazon_rating, created_date"
					+ " FROM Book b, Publisher p, Author a, Book_Author ba "
					+ "WHERE b.id = " + bookId + " AND b.publisher_id = p.id AND b.id = ba.book_id AND ba.author_id = a.id GROUP BY b.id";
	    	ResultSet rs = stmt.executeQuery(query);
			
	    	while (rs.next()) {
	    		long id = rs.getLong("id");
				String title = rs.getString("title");
				String imageUrl = rs.getString("image_url");
				int publicationYear = rs.getInt("publication_year");
				String publisher = rs.getString("name");		
				String[] authors = rs.getString("authors").split(",");			
				int genre_id = rs.getInt("book_genre_id");
				BookGenre genre = BookGenre.values()[genre_id];
				double amazonRating = rs.getDouble("amazon_rating");
				
				System.out.println("id: " + id + ", title: " + title + ", publication year: " + publicationYear + ", publisher: " + publisher + ", authors: " + String.join(", ", authors) + ", genre: " + genre + ", amazonRating: " + amazonRating);
	    		
	    		book = BookmarkManager.getInstance().createBook(id, title, imageUrl, publicationYear, publisher, authors, genre, amazonRating/*, values[7]*/);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}
 
	public Bookmark getMovie(long movieId) { // change
		Movie movie = null;
		System.out.println("in getMovie");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {
			String query = "SELECT m.id, title, image_url, release_year, d.name AS director, GROUP_CONCAT(a.name SEPARATOR ',') AS actor, movie_genre_id, imdb_rating, created_date"
					+ " FROM Movie m, Director d, Actor a, Movie_Actor ma "
					+ "WHERE m.id = " + movieId + " AND m.director_id = d.id AND m.id = ma.movie_id AND ma.actor_id = a.id GROUP BY m.id";
	    	ResultSet rs = stmt.executeQuery(query);
			
	    	while (rs.next()) {
	    		long id = rs.getLong("id");
				String title = rs.getString("title");
				String imageUrl = rs.getString("image_url");
				int releaseYear = rs.getInt("release_year");
				String[] director = rs.getString("director").split(",");;		
				String[] actor = rs.getString("actor").split(",");			
				int genre_id = rs.getInt("movie_genre_id");
				MovieGenre genre = MovieGenre.values()[genre_id];
				double imdbRating = rs.getDouble("imdb_rating");
				System.out.println("got the movie instance");
				System.out.println("id: " + id + ", title: " + title + ", release year: " + releaseYear + ", director:: " + director + ", actor:: " + String.join(", ", actor) + ", genre: " + genre + ", imdbRating: " + imdbRating);
	    		
	    		movie = BookmarkManager.getInstance().createMovie(id, title, imageUrl, releaseYear, actor, director, genre, imdbRating/*, values[7]*/);
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("before return in getmovie");
		return movie;
	}

	public Collection<Bookmark> getMovies(boolean isBookmarked, long userId) {
		
		//TODO only activate the server driver once (at login, then try to see if it works for lookup and such)
		
		
		Collection<Bookmark> result = new ArrayList<>();
		
		// set up to only activate below once at beginning, probably after login
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//new com.mysql.jdbc.Driver(); 
			            // OR
			//System.setProperty("jdbc.drivers", "com.mysql.jdbc.Driver");
		
		                // OR java.sql.DriverManager
		    //DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jid_thrillio?useSSL=false", "root", "root");
				Statement stmt = conn.createStatement();) {			
			
			String query = "";
			if (!isBookmarked) {
				query = "SELECT m.id, title, image_url, release_year, a.name AS actor, d.name AS director, movie_genre_id, " +
									"imdb_rating FROM movie m, actor a, director d, movie_actor ma where m.id = ma.movie_id and ma.actor_id = a.id and m.director_id = d.id and " + 
									"m.id NOT IN (select um.movie_id from User u, User_Movie um where u.id = " + userId +
									" and u.id = um.user_id) group by m.id";	
				System.out.println("in if statement of getMovie(2)");
			} else {
				query = "Select m.id, title, image_url, release_year, GROUP_CONCAT(a.name SEPARATOR ',') AS actor, GROUP_CONCAT(d.name SEPARATOR ',') AS director, movie_genre_id," +
						"imdb_rating from Movie m, Actor a, Director d, Movie_Actor ma where m.id = ma.movie_id and ma.actor_id = a.id and m.director_id = d.id and  " + 
						"m.id IN (select um.movie_id from User u, User_Movie um where u.id = " + userId+
						" and u.id = um.user_id) group by m.id";
				System.out.println("in else statement of getMovie(2)");
			}
			
			ResultSet rs = stmt.executeQuery(query);				
			
	    	while (rs.next()) {
	    		long id = rs.getLong("id");
				String title = rs.getString("title");
				String imageUrl = rs.getString("image_url");
				int releaseYear = rs.getInt("release_year");
				//String publisher = rs.getString("name");		
				String[] actor = rs.getString("actor").split(",");	
				String[] director = rs.getString("director").split(",");
				int genre_id = rs.getInt("movie_genre_id");
				MovieGenre genre = MovieGenre.values()[genre_id];
				double imdbRating = rs.getDouble("imdb_rating");
				
				System.out.println("id: " + id + ", title: " + title + ", release year: " + releaseYear + ", director:: " + director + ", actor:: " + String.join(", ", actor) + ", genre: " + genre + ", imdbRating: " + imdbRating);
	    	
	    	
	    		Bookmark bookmark = BookmarkManager.getInstance().createMovie(id, title, imageUrl, releaseYear, actor, director, genre, imdbRating/*, values[7]*/);
	    		result.add(bookmark); 
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	
}
