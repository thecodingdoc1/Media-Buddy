package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import constants.BookGenre;
import managers.BookmarkManager;

public class BookTest {

	@Test
	public void test() {
		
		// Test 1 genre is philosophy
		Book book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.PHILOSOPHY, 4.3);
		
		boolean isKidFriendlyEligible = book.isKidFriendlyEligible();

		assertFalse("Genre is philosophy -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2 genre is self help
		book = BookmarkManager.getInstance().createBook(4000, "Walden", 1854, "Wilder Publications",
				new String[] { "Henry David Thoreau" }, BookGenre.SELF_HELP, 4.3);
		
		isKidFriendlyEligible = book.isKidFriendlyEligible();

		assertFalse("Genre is Self Help -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		
		
	}

}
