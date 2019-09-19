package entities;

import java.util.Arrays;

import constants.MovieGenre;

public class Movie extends Bookmark {
	private String title;
	private int releaseYear;
	private String[] actor;
	private String[] director;
	private MovieGenre genre;
	private double imdbRating;
	private String imageUrl;

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String[] getActor() {
		return actor;
	}

	public void setActor(String[] actor) {
		this.actor = actor;
	}

	public String[] getDirector() {
		return director;
	}

	public void setDirector(String[] director) {
		this.director = director;
	}

	public MovieGenre getGenre() {
		return genre;
	}

	public void setGenre(MovieGenre genre) {
		this.genre = genre;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	@Override
	public String toString() {
		return "Movie [title=" + title + ", releaseYear=" + releaseYear  + ", cast=" + Arrays.toString(actor) + ", directors="
				+ Arrays.toString(director) + ", genre=" + genre + ", imdbRating=" + imdbRating + "]";
	}

	/*@Override
	public boolean isKidFriendlyEligible() {
		if (genre.equals(MovieGenre.HORROR) || genre.equals(MovieGenre.THRILLERS)) {
			return false;
		}
		return true;
	}*/

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	
}
