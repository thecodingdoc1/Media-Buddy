package entities;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import constants.BookGenre;

public class Book extends Bookmark implements partners.Shareable{
	private String title;
	private int publicationYear;
	private String publishers;
	private String[] authors;
	private BookGenre genre;
	private double amazonRating;
	private String imageUrl;

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getPublishers() {
		return publishers;
	}

	public void setPublishers(String publishers) {
		this.publishers = publishers;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	public BookGenre getGenre() {
		return genre;
	}

	public void setGenre(BookGenre genre) {
		this.genre = genre;
	}

	public double getAmazonRating() {
		return amazonRating;
	}

	public void setAmazonRating(double amazonRating) {
		this.amazonRating = amazonRating;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", publicationYear=" + publicationYear + ", publishers=" + publishers + ", authors="
				+ Arrays.toString(authors) + ", genre=" + genre + ", amazonRating=" + amazonRating + "]";
	}

	@Override
	public boolean isKidFriendlyEligible() {
		if (genre.equals(BookGenre.PHILOSOPHY) || genre.equals(BookGenre.SELF_HELP)) {
			return false;
		}
		return true;
	}

	@Override
	public String getItemData() {
		StringBuilder builder = new StringBuilder();
		builder.append("<item>");
			builder.append("<title>Book</title>");
			builder.append("<title>").append(title).append("</title>");
			builder.append("<authors>").append(StringUtils.join(authors, ",")).append("</authors>");
			builder.append("<publishers>").append(publishers).append("</publuishes>");
			builder.append("<publicationYear>").append(publicationYear).append("</publicationYear>");
			builder.append("<genre>").append(genre).append("</genre>");
			builder.append("<AmazonRating>").append(amazonRating).append("</AmazonRating>");
		builder.append("</item>");
		
		return builder.toString();
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
