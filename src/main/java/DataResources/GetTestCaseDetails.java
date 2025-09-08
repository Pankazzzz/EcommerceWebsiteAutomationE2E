package DataResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class GetTestCaseDetails {
	
	public static Properties getTestCaseDetails() throws IOException
	{
		FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/DataResources/TestCase.properties"));
		Properties properties = new Properties();
		properties.load(fileInputStream);
		return properties;
	}
}
