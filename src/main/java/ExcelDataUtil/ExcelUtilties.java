package ExcelDataUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilties {
	
	public String pathString = "/Users/pankajshukla/eclipse-workspace/PracticeAutomation/src/main/java/DataResources/ExcelDataForTestPractice.xlsx"; 
	public String sheetNameString = "DataFile";
	public XSSFWorkbook workbook; 
	public XSSFSheet sheet;
	
	public HashMap<String, String> getDataForTestCase(String testCaseName) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(pathString));
		workbook = new XSSFWorkbook(fileInputStream);
		int sheetCount = workbook.getNumberOfSheets();
		HashMap<String, String> dataHashMap = new HashMap<>();

		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetNameString)) {
				sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row row = rows.next(); // header row
				Iterator<Cell> cellsIterator = row.cellIterator();
				int k = 0;
				int column = 0;
				while (cellsIterator.hasNext()) {
					Cell c = cellsIterator.next();
					if (c.getStringCellValue().equalsIgnoreCase("TestCaseName")) {
						column = k;
					}
					k++;
				}

				ArrayList<String> headers = getTableHeaders();

				while (rows.hasNext()) {
					row = rows.next();
					if (row.getCell(column) != null 
							&& row.getCell(column).getCellType() == CellType.STRING 
							&& row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						
						Iterator<Cell> cells = row.cellIterator();
						int j = 0; 
						while (cells.hasNext() && j < headers.size()) {
							Cell cell = cells.next();
							switch (cell.getCellType()) {
								case STRING:
									dataHashMap.put(headers.get(j++), cell.getStringCellValue());
									break;
								case NUMERIC:
									dataHashMap.put(headers.get(j++), NumberToTextConverter.toText(cell.getNumericCellValue()));
									break;
								case BOOLEAN:
									dataHashMap.put(headers.get(j++), String.valueOf(cell.getBooleanCellValue()));
									break;
								case BLANK:
									dataHashMap.put(headers.get(j++), "");
									break;
								default:
									dataHashMap.put(headers.get(j++), ""); // safe fallback
							}
						}
					}
				}
			}
		}
		workbook.close(); 
		return dataHashMap;
	}

	public ArrayList<String> getTableHeaders() throws IOException {
		FileInputStream fileInputStream = new FileInputStream(new File(pathString));
		workbook = new XSSFWorkbook(fileInputStream);
		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<String> headerStrings = new ArrayList<>();

		for (int i = 0; i < sheetCount; i++) {
			if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetNameString)) {
				sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row row = rows.next();
				Iterator<Cell> cellsIterator = row.cellIterator();
				while (cellsIterator.hasNext()) {
					Cell cell = cellsIterator.next();
					headerStrings.add(cell.getStringCellValue());
				}
			}
		}
		workbook.close(); 
		return headerStrings;
	}
}
