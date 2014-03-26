/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3710
 * Professor: Dr. Dahai Guo
 * University: Florida Gulf Coast University
 ******************************************************************/
package hw4_1114;

import java.sql.*;

public class Sql {
	Connection conn;

	Sql() {
		connect();
		create();
	}

	public void connect() {
		// Connect to DataBase
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:" + // Protocol
					System.getProperty("user.dir") + "/movies", // db file path
					"sa", // user
					""); // password

			// conn.close(); // disconnects from database
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			conn.close(); // disconnects from database
			System.out.println("Closing Database Connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Creates movies H2 database
	public void create() {
		try {
			Statement statement = conn.createStatement();

			/* begin table creation */

			// Create Movie Table
			statement.execute("create table IF NOT EXISTS Movie("
					+ "id varchar(100) not null ,"
					+ "title varchar(100) not null,"
					+ "year smallint unsigned not null,"
					+ "mpaa_rating varchar(10) not null,"
					+ "audience_score smallint unsigned not null,"
					+ "critics_score smallint unsigned not null, "
					+ "constraint pk_Movie primary key (id)" + ");");

			// Create Actor Table
			statement.execute("create table IF NOT EXISTS Actor("
					+ "id varchar(100) ," 
					+ "name varchar(100) ,"
					+ "constraint pk_Actor primary key (id)" + ");");

			// Create Character Table
			statement
					.execute("create table IF NOT EXISTS Character("
							+ "actor_id varchar(100) not null,"
							+ "movie_id varchar(100) not null,"
							+ "character varchar(100) not null,"
							+ "constraint fk_actor_id foreign key(actor_id)references Actor(id),"
							+ "constraint fk_movie_id foreign key(movie_id)references Movie(id),"
							+ "constraint pk_Character primary key(actor_id,movie_id,character)"
							+ ");");
			System.out.println("Database Created");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Checks for ' in String and replaces with ''
	public String checkString(String s) {
		char[] temp = s.toCharArray();
		String newS = "";
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == '\'') {
				newS = newS + temp[i] + temp[i];
			} else {
				newS = newS + temp[i];
			}
		}
		return newS;
	}

	public void mergeMovie(Integer id, String title, Integer year,
			String mpaa_rating, Integer audience_score, Integer critics_score) {

		title = checkString(title);
		mpaa_rating = checkString(mpaa_rating);

		String insertMovie = "MERGE INTO MOVIE VALUES ('" + id + "','" + title
				+ "','" + year + "','" + mpaa_rating + "','" + audience_score
				+ "','" + critics_score + "');";

		try {
			Statement statement = conn.createStatement();
			// Insert Movie Table Entry
			statement.execute(insertMovie);

		} catch (SQLException e) {
			System.out.println("Problem With Entry:\n" + insertMovie + "\n");
			e.printStackTrace();
		}

	}

	public void mergetActor(Integer id, String name) {

		name = checkString(name);

		String insertActor = "MERGE INTO ACTOR VALUES('" + id + "','" + name
				+ "');";

		try {
			Statement statement = conn.createStatement();
			// Insert Actor Table Entry
			statement.execute(insertActor);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Problem With Entry:\n" + insertActor + "\n");
		}

	}

	public void mergeCharacter(Integer actor_id, Integer movie_id,
			String character) {

		character = checkString(character);

		String insertCharacter = "MERGE INTO Character VALUES('" + actor_id
				+ "','" + movie_id + "','" + character + "');";

		try {
			Statement statement = conn.createStatement();
			// Merge Character Table Entry
			statement.execute(insertCharacter);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Problem With Entry:\n" + insertCharacter + "\n");
		}

	}

}
