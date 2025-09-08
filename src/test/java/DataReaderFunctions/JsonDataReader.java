package DataReaderFunctions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonDataReader {
	
	public  static List<HashMap<String, String>> dataRead(String dire) throws IOException
	{
		Path path = Paths.get(System.getProperty("user.dir")+"/src/main/java/DataResources/data.json");
		String fille;
		
		if(dire!=null)
		 fille=Files.readString(Paths.get(dire));
		else {
			fille=Files.readString(path);
		}
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		List<HashMap<String, String>> dataHashMaps =objectMapper.readValue(fille, new TypeReference<List<HashMap<String, String>> >() {
		
		});
		
		return dataHashMaps;
	}

}
