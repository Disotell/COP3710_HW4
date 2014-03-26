/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3710
 * Professor: Dr. Dahai Guo
 * University: Florida Gulf Coast University
 ******************************************************************/

package hw4_1114;

import hw4_1114.JsonDeserializer.movies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HW4_1114 {
	public static void main(String args[]) throws JsonParseException,
			JsonMappingException, IOException {
		
		Sql h2 = new Sql();
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		
		//Page1 - Page25
		for (int i = 1; i < 26; i++) {
			JsonDeserializer page = mapper.readValue(
					new File((System.getProperty("user.dir") + "\\movies\\page"
							+ i + ".json")), JsonDeserializer.class);
			
			//ArrayList of Movie Objects
			ArrayList<movies> movies = page.getMovies();
			
			for (movies m : movies) {
				//Merge Movie Values To H2 Movies database Table Movie
				 h2.mergeMovie(m.getId(), m.getTitle(), m.getYear(),
				  m.getMpaa_rating(), m.getRatings().getAudience_score(),
				  m.getRatings().getCritics_score());
				
				//Array of abridged_cast Objects 
				abridged_cast[] cast = m.getAbridged_cast();
				
				for (abridged_cast c : cast) {
					//Merge Actor Values To H2 Movies database Table Actor
					h2.mergetActor(c.getId(),c.getName());
					
					//
					String[] character = c.getCharacters();
					
					if(character != null){
					for (String  a : character) {
						h2.mergeCharacter(c.getId(),m.getId(),a);
					}
					
					}
					else{
						//String error = "No Discription" ;
						//h2.insertCharacter(c.getId(),m.getId(), error);	
					}
				}					
			}
		}
		System.out.println("All Values Entered into DataBase");
		h2.close();
	}
}