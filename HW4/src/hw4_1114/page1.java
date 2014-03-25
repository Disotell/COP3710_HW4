package hw4_1114;

import java.util.ArrayList;

public class page1 {

	private String total;

	public String getTotal() {
		return total;
	}

	private ArrayList<Movies> Movie;

	public static class Movies {
		private String id;

		public String getid() {
			return id;
		}

		public void setid(String s) {
			id = s;
		}

	}

	public ArrayList<Movies> getMovies() {
		return Movie;
	}

	public void setMovies(ArrayList<Movies> m) {
		this.Movie = m;
	};

}
