package controllers;

import java.sql.SQLException;

import constants.KidFriendlyStatus;
import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;

public class BookmarkController { // woul dbe servlet in real java EE application
	private static BookmarkController instance = new BookmarkController();
	private BookmarkController() {}
	public static BookmarkController getInstance() {
		return instance;
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
