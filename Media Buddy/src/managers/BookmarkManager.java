package managers;

import java.util.Collection;
import java.util.List;

import constants.BookGenre;
import constants.KidFriendlyStatus;
import constants.MovieGenre;
import dao.BookmarkDao;
import entities.Book;
import entities.Bookmark;
import entities.Movie;
import entities.User;
import entities.UserBookmark;
import entities.WebLink;

public class BookmarkManager {
	private static BookmarkManager instance = new BookmarkManager();

	private static BookmarkDao dao = new BookmarkDao();

	private BookmarkManager() {
	}

	public static BookmarkManager getInstance() {
		return instance;
	}

	public Movie createMovie(long id, String title, String imageUrl, int releaseYear, String[] actor,
			String[] director, MovieGenre genre, double imdbRating) {
		Movie movie = new Movie();
		movie.setId(id);
		movie.setTitle(title);
		movie.setImageUrl(imageUrl);
		movie.setReleaseYear(releaseYear);
		movie.setActor(actor);
		movie.setDirector(director);
		movie.setGenre(genre);
		movie.setImdbRating(imdbRating);

		return movie;
	}

	public Book createBook(long id, String title, String imageUrl, int publicationYear, String publishers,
			String[] authors, BookGenre genre, double amazonRating) {
		Book book = new Book();
		book.setId(id);
		book.setTitle(title);
		book.setImageUrl(imageUrl);
		book.setPublicationYear(publicationYear);
		book.setPublishers(publishers);
		book.setAuthors(authors);
		book.setGenre(genre);
		book.setAmazonRating(amazonRating);

		return book;
	}

	public WebLink createWebLink(long id, String title, String url, String hosts) {
		WebLink webLink = new WebLink();
		webLink.setId(id);
		webLink.setTitle(title);
		webLink.setUrl(url);
		webLink.setHost(hosts);

		return webLink;
	}


	public void saveUserBookmark(User user, Bookmark bookmark) {
		UserBookmark userBookmark = new UserBookmark();
		userBookmark.setUser(user);
		userBookmark.setBookmark(bookmark);

		dao.saveUserBookmark(userBookmark);
		}

	/*public void setKidFriendlyStatus(User user, KidFriendlyStatus kidFriendlyStatus, Bookmark bookmark) { // saves and prints out which user did the marking of the KidFriendlyStatus
		bookmark.setKidFriendlyStatus(kidFriendlyStatus);
		bookmark.setKidFriendlyMarkedBy(user);

		dao.updateKidFriendlyStatus(bookmark);
		
		System.out.println("Kid-friendlystatus: " + kidFriendlyStatus + ", Marked By: " + user.getEmail() + ", " + bookmark);

	}

	public void share(User user, Bookmark bookmark) {
		bookmark.setSharedBy(user);
		
		System.out.println("Data to be shared: ");
		if (bookmark instanceof Book) {
			System.out.println(((Book)bookmark).getItemData());
		} else if(bookmark instanceof WebLink) {
			System.out.println(((WebLink)bookmark).getItemData()); 
		}
		dao.sharedByInfo(bookmark);
	}*/

	public Collection<Bookmark> getBooks(boolean isBookmarked, long userId) {
		return dao.getBooks(isBookmarked, userId);
		
	}

	public Bookmark getBook(long bid) {
		
		return dao.getBook(bid);
	}

	public Bookmark getMovie(long mid) {
		return dao.getMovie(mid);
	}

	public Collection<Bookmark> getMovies(boolean isBookmarked, long userId) {
		return dao.getMovies(isBookmarked, userId);
	}

	public void unsaveUserBookmark(User user, Bookmark bookmark) {
			UserBookmark userBookmark = new UserBookmark();
			userBookmark.setUser(user);
			userBookmark.setBookmark(bookmark);
			dao.unsaveUserBookmark(userBookmark);
			}
}
