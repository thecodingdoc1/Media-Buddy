package thrillio;

import java.sql.SQLException;
import java.util.List;

import bgjobs.WebpageDownloaderTask;
import entities.Bookmark;
import entities.User;
import managers.BookmarkManager;
import managers.UserManager;

public class Launch {
	private static List<User> users;
	private static List<List<Bookmark>> bookmarks;
	
	private static void loadData() {
		System.out.println("1. Loading data ...");
		DataStore.loadData();
		
		users = UserManager.getInstance().getUsers();
		bookmarks = BookmarkManager.getInstance().getBookmarks();
		
		System.out.println("Printing data ...");
		printUserData();
		printBookmarkData();
	}

		private static void printBookmarkData() {
			for (List<Bookmark> bookmarkList : bookmarks) {
				for ( Bookmark bookmark : bookmarkList) {
					System.out.println(bookmark);
				}
			}
		} 

		private static void printUserData() {
		for (User users : users) {
			System.out.println(users);
		}

	}
		private static void start()  {
			// System.out.println("\n2. Bookmarking ...");
			for (User user: users) {
				View.browse(user, bookmarks);
			}
		}


		public static void main(String[] args) {
			loadData();
			start();
			
			//Background Jobs
			//runDownloaderJob();
		}
		
		private static void runDownloaderJob() {
			WebpageDownloaderTask task = new WebpageDownloaderTask(true);
			(new Thread(task)).start();
		}

	
}
