package thrillio;

import java.sql.SQLException;
import java.util.List;

import constants.KidFriendlyStatus;
import constants.UserType;
import controllers.BookmarkController;
import entities.Bookmark;
import entities.User;
import partner.Shareable;

public class View { // each item is an action that the user would perform
	public static void browse(User user, List<List<Bookmark>> bookmarks) {
		System.out.println("\n" + user.getFirstName() + ", " + user.getUserType() + " is browsing items ...");
		int bookmarkCount = 0;

		for (List<Bookmark> bookmarkList : bookmarks) {
			for (Bookmark bookmark : bookmarkList) {
				// Bookmarking!!
				//if (bookmarkCount < DataStore.USER_BOOKMARK_LIMIT) {
					boolean isBookmarked = getBookmarkDecision(bookmark); // can input the user response and request here
					if (isBookmarked) {
						bookmarkCount++;

						BookmarkController.getInstance().saveUserBookmark(user, bookmark);

						System.out.println("New Item Bookmarked --" + bookmark);
					}
				
				
				if (user.getUserType().equals(UserType.EDITOR)
						|| user.getUserType().equals(UserType.CHIEF_EDITOR)) {
					
					// mark as kid-friendly
					if (bookmark.isKidFriendlyEligible()
							&& bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.UNKNOWN)) {
						KidFriendlyStatus kidFriendlyStatus = getKidFriendlyStatusDecision(bookmark);
						if (!kidFriendlyStatus.equals(KidFriendlyStatus.UNKNOWN)) {
							bookmark.setKidFriendlyStatus(kidFriendlyStatus);
							System.out.println("Kid-friendly status: " + kidFriendlyStatus + ", " + bookmark);
							BookmarkController.getInstance().setKidFriendlyStatus(user, kidFriendlyStatus, bookmark);
						}
					}
					// Sharing!!
					if (bookmark.getKidFriendlyStatus().equals(KidFriendlyStatus.APPROVED) && bookmark instanceof Shareable) {
						boolean isShared = getShareDecision();
						if (isShared) {
							BookmarkController.getInstance().share(user, bookmark);
						}
					}
				}
			}
			}
		}
	
	private static boolean getShareDecision() {
		return Math.random() < 0.5 ? true : false;
		
	}

	private static KidFriendlyStatus getKidFriendlyStatusDecision(Bookmark bookmark) {
		double decision = Math.random();
		return decision < 0.4 ? KidFriendlyStatus.APPROVED
				: (decision >= 0.4 && decision < 0.8) ? KidFriendlyStatus.REJECTED
						: KidFriendlyStatus.UNKNOWN;
	}

	private static boolean getBookmarkDecision(Bookmark bookmark) {
		return Math.random() < 0.5 ? true : false;
	}

	/* 
	 * public static void bookmark(User user, Bookmark[][] bookmarks) { // random
	 * generation and assignment of 5 bookmarks System.out.println("\n" +
	 * user.getEmail() + " is bookmarking"); for (int i = 0; i <
	 * DataStore.USER_BOOKMARK_LIMIT; i++) { int typeOffset =(int)(Math.random() *
	 * DataStore.BOOKMARK_TYPES_COUNT); int bookmarkOffset = (int)(Math.random() *
	 * DataStore.BOOKMARK_COUNT_PER_TYPE);
	 * 
	 * Bookmark bookmark = bookmarks [typeOffset][bookmarkOffset];
	 * 
	 * BookmarkController.getInstance().saveUserBookmark(user,bookmark);
	 * 
	 * System.out.println(bookmark); }
	 */

}
