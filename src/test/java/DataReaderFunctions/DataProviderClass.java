package DataReaderFunctions;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

	@DataProvider
	public Object[][] getData()
	{
		return new Object[][] {{"Demo@email.com","Demo"}};
	}
}
