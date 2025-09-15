package ExcelDataUtil;

import java.io.IOException;
import java.util.HashMap;

public class GetExcelData {
	
	public static HashMap<String, String> getDataForTheTestCase(String testcaseName) throws IOException
	{
		ExcelUtilties excelUtilties = new ExcelUtilties();
		return excelUtilties.getDataForTestCase(testcaseName);
	}
	
	public static String[][] getAllData() throws IOException
	{
		ExcelUtilties excelUtilties = new ExcelUtilties();
		int rowCount=excelUtilties.getRowCount();
		int cellCount = excelUtilties.getCellCount();
		
		String[][] dataStrings = new String[rowCount][cellCount];
		
		for(int i=1;i<rowCount;i++)
		{
			for(int j=0;j<cellCount;j++)
			{
				dataStrings[i-1][j]=excelUtilties.getCellData(i, j);
			}
		}
		
		return dataStrings;
		
	}

}
