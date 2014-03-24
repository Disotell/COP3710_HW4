package hw4_1114;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class HW4_1114{
	public static void main(String args[]) throws 	
			JsonParseException, JsonMappingException, IOException{
		Sql h2 = new Sql();
		h2.close();
		
		ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
		User user = mapper.readValue(new File("user.json"), User.class);
		
		user.getName().setFirst("Jackson");
		
		mapper.writeValue(new File("new_user.json"), user);
		System.out.println(user.getName().getFirst());
	}
}