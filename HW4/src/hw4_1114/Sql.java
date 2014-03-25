/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3720
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

	public void close() {
		try {
			conn.close(); // disconnects from database
			System.out.println("Closing Database Connection");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void connect() {
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

	//Creates movies H2 database 
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
					+ "id varchar(100) not null,"
					+ "name varchar(20) not null,"
					+ "constraint pk_Actor primary key (id)" + ");");

			// Create Character Table
			statement.execute("create table IF NOT EXISTS Character("
							+ "actor_id varchar(100) not null,"
							+ "movie_id varchar(100) not null,"
							+ "character varchar(100) not null,"
							+ "constraint fk_actor_id foreign key (actor_id)references Actor(id),"
							+ "constraint fk_movie_id foreign key(movie_id)references Movie(id),"
							+ "constraint pk_Character primary key (actor_id,movie_id,character)"
							+ ");");

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
