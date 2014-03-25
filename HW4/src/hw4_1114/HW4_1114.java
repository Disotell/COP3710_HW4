/******************************************************************
 * Author: 1114
 * Date: 3/23/2014
 * Assignment: HW4
 * Class:COP 3720
 * Professor: Dr. Dahai Guo
 * University: Florida Gulf Coast University
 ******************************************************************/

package hw4_1114;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HW4_1114{
	public static void main(String args[]) throws 	
			JsonParseException, JsonMappingException, IOException{
		Sql h2 = new Sql();
		//h2.close();
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		
		page1 page1 = mapper.readValue(new File("page1.json"), page1.class);
		
		System.out.println(page1.getTotal());
		
	
		Users user = mapper.readValue(new File("user.json"), Users.class);
		
		user.getName().setFirst("Jackson");
		
		mapper.writeValue(new File("new_user.json"), user);
		
		System.out.println(user.getName().getFirst());
		
	}
} 