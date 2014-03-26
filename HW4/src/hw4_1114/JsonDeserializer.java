/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3710
 * Professor: Dr. Dahai Guo
 * University: Florida Gulf Coast University
 ******************************************************************/

package hw4_1114;

import java.util.ArrayList;

public class JsonDeserializer {

	// Total
	private String total;

	public String getTotal() {
		return total;
	}

	public void setTotal(String s) {
		total = s;
	}

	// Movies
	private ArrayList<movies> movies;

	public ArrayList<movies> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<movies> o) {
		this.movies = o;
	}

	public static class movies {

		// ID
		private Integer id;

		public Integer getId() {
			return id;
		}

		public void setId(Integer i) {
			id = i;
		}

		// Title
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String s) {
			title = s;
		}

		// Year
		private Integer year;

		public Integer getYear() {
			return year;
		}

		public void setYear(Integer i) {
			year = i;
		}

		// MPAA Rating
		private String mpaa_rating;

		public String getMpaa_rating() {
			return mpaa_rating;
		}

		public void setMpaa_rating(String s) {
			mpaa_rating = s;
		}

		// Rating
		private Ratings ratings;

		public Ratings getRatings() {
			return ratings;
		}

		public void setRatings(Ratings o) {
			ratings = o;
		}

		public class Ratings {
			private Integer critics_score;

			public Integer getCritics_score() {
				return critics_score;
			}

			public void setCritics_score(Integer i) {
				critics_score = i;
			}

			private Integer audience_score;

			public Integer getAudience_score() {
				return audience_score;
			}

			public void setAudience_score(Integer i) {
				audience_score = i;
			}
		}

		// Abridged Cast
		private abridged_cast[] abridged_cast;

		public abridged_cast[] getAbridged_cast() {
			return abridged_cast;
		}

		public void setAbridged_cast(abridged_cast[] abridged_cast) {
			this.abridged_cast = abridged_cast;
		}
	}//End of Class movies
}//End of Class JsonDeserializer
