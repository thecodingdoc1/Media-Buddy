package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import constants.MovieGenre;
import managers.BookmarkManager;

public class MovieTest {

	@Test
	public void test() {
		// Test 1 genre is horror
		Movie movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.HORROR,
				8.5);
		boolean isKidFriendlyEligible = movie.isKidFriendlyEligible();

		assertFalse("Genre is horror -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2 genre is thrillers
		movie = BookmarkManager.getInstance().createMovie(3000, "Citizen Kane", 1941,
				new String[] { "Orson Welles", "Joseph Cotten" }, new String[] { "Orson Welles" }, MovieGenre.THRILLERS,
				8.5);
		isKidFriendlyEligible = movie.isKidFriendlyEligible();

		assertFalse("Genre is thrillers -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
	}

}
