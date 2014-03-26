/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3720
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


public class HW4_1114{
	public static void main(String args[]) throws 	
			JsonParseException, JsonMappingException, IOException{
		Sql h2 = new Sql();
		int count=0;
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		for(int i = 1;i<25;i++){
		
		
		JsonDeserializer page = mapper.readValue(new File((System.getProperty("user.dir")+"\\movies\\page"+i+".json")), JsonDeserializer.class);
		
		//System.out.println(page1.getTotal());
		ArrayList<movies> th= page.getMovies();
		
		for (movies s : th){
			h2.insertMovie(s.getId(),s.getTitle(),s.getYear(),s.getMpaa_rating (),s.getRatings().getAudience_score(),s.getRatings().getCritics_score());
			
			
			//System.out.println("Critics_Score: "+s.getRatings().getCritics_score()+ 
			//" & Audience Score: "+ s.getRatings().getAudience_score()+" MPAA Ranking: "+s.getMpaa_rating ()+"\n");
		
			
			count++;
		}
		
		}
		System.out.println("Movies "+count);
		h2.close();
		
	}
} 