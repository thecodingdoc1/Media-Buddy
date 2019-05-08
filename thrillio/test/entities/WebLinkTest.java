package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import managers.BookmarkManager;

public class WebLinkTest {

	@Test
	public void testIsKidFriendlyEligible() {
		// Test 1- porn in url -- false
		WebLink webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-porn-tiger--part-2.html",
				"http://www.javaworld.com");
		
		boolean isKidFriendlyEligible = webLink.isKidFriendlyEligible();

		assertFalse("For porn in url -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);
		
		// Test 2 porn in title -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming porn Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();

		assertFalse("For porn in title -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 3 adult in host -- false
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming  Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaadultworld.com");
		
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();

		assertFalse("For adult in title -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 4 adult in url, but not in host -- true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-adult-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();

		assertTrue("For adult in url and not host -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);

		// Test 5 adult in title only --true
		webLink = BookmarkManager.getInstance().createWebLink(2000, "Taming Adult Tiger, Part 2",
				"http://www.javaworld.com/article/2072759/core-java/taming-tiger--part-2.html",
				"http://www.javaworld.com");
		
		isKidFriendlyEligible = webLink.isKidFriendlyEligible();

		assertTrue("For adult in title only -- isKidFriendlyEligible() must return false", isKidFriendlyEligible);

	}

}
