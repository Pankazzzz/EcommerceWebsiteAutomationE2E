package ExcelDataUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
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
		HashMap<String, String> dataHashMap = new HashMap<>();
		try (FileInputStream fileInputStream = new FileInputStream(new File(pathString));
			 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			workbook = wb;
			int sheetCount = workbook.getNumberOfSheets();

			for (int i = 0; i < sheetCount; i++) {
				if (workbook.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetNameString)) {
					sheet = workbook.getSheetAt(i);
					Iterator<Row> rows = sheet.iterator();
					Row row = rows.next(); 
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
										dataHashMap.put(headers.get(j++), "");
								}
							}
						}
					}
				}
			}
		}
		return dataHashMap;
	}

	public ArrayList<String> getTableHeaders() throws IOException {
		ArrayList<String> headerStrings = new ArrayList<>();
		try (FileInputStream fileInputStream = new FileInputStream(new File(pathString));
			 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			workbook = wb;
			int sheetCount = workbook.getNumberOfSheets();

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
		}
		return headerStrings;
	}
	
	public int getRowCount() throws IOException {
		int rowsCount;
		try (FileInputStream fileInputStream = new FileInputStream(new File(pathString));
			 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			workbook = wb;
			XSSFSheet sheet = workbook.getSheet(sheetNameString);
			rowsCount = sheet.getPhysicalNumberOfRows();
		}
		return rowsCount;
	}
	
	public int getCellCount() throws IOException {
		int cellCount;
		try (FileInputStream fileInputStream = new FileInputStream(new File(pathString));
			 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			workbook = wb;
			XSSFSheet sheet = workbook.getSheet(sheetNameString);
			Row row = sheet.getRow(0);
			cellCount = row.getLastCellNum();
		}
		return cellCount;
	}
	
	public String getCellData(int r,int column) throws IOException {
		String dataString;
		try (FileInputStream fileInputStream = new FileInputStream(new File(pathString));
			 XSSFWorkbook wb = new XSSFWorkbook(fileInputStream)) {
			workbook = wb;
			XSSFSheet sheet = workbook.getSheet(sheetNameString);
			Row row = sheet.getRow(r);
			Cell c = row.getCell(column);
			DataFormatter dataFormatter = new DataFormatter();
			dataString = dataFormatter.formatCellValue(c);
		}
		return dataString;
	}
}
