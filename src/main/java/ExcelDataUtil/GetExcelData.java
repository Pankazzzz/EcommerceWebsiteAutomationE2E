package ExcelDataUtil;

import java.io.IOException;
import java.util.HashMap;

public class GetExcelData {
	
	public static HashMap<String, String> getDataForTheTestCase(String testcaseName) throws IOException
	{
		ExcelUtilties excelUtilties = new ExcelUtilties();
		return excelUtilties.getDataForTestCase(testcaseName);
	}

}
