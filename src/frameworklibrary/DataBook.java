
package frameworklibrary;

import frameworklibrary.ControlData;
import frameworklibrary.SharedDataBook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.poifs.macros.VBAMacroExtractor;
import org.apache.poi.poifs.macros.VBAMacroReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 609684083
 *
 */
public class DataBook {

	public static Cell findTestDataCell(XSSFWorkbook workbook, String sheetName, String colName, boolean isIteratedData) {

		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator rowIterator = sheet.iterator();
		Row row = null;
		Row testCaseStartRow = null;
		Object testCaseEndRow = null;
		Row testCaseCurrRow = null;
		Cell testDataCell = null;

		Cell cell;
		label62: while (rowIterator.hasNext()) {
			row = (Row) rowIterator.next();
			cell = row.getCell(0);
			if (ControlData.currTestCaseID.equalsIgnoreCase(cell.getStringCellValue())) {
				testCaseStartRow = cell.getRow();
				if (rowIterator.hasNext()) {
					String cellIterator = "";

					while (true) {
						if (!rowIterator.hasNext()) {
							break label62;
						}

						row = (Row) rowIterator.next();
						cell = row.getCell(0);
						cellIterator = cell != null ? cell.getStringCellValue() : "";
						if (!ControlData.currTestCaseID.equalsIgnoreCase(cellIterator)) {
							testCaseEndRow = sheet.getRow(cell.getRowIndex() - 1);
							break label62;
						}

						testCaseEndRow = sheet.getRow(row.getRowNum());
					}
				} else {
					testCaseEndRow = testCaseStartRow;
					break;
				}
			}
		}

		if (isIteratedData) {
			for (int arg13 = testCaseStartRow.getRowNum(); arg13 <= ((Row) testCaseEndRow).getRowNum(); ++arg13) {
				XSSFCell arg12 = sheet.getRow(arg13).getCell(1);
				if (((Integer) ControlData.methodCount.get(ControlData.currentTestMethod)).intValue() == Integer
						.parseInt(arg12.getStringCellValue())) {
					testCaseCurrRow = arg12.getRow();
					break;
				}
			}
		} else {
			testCaseCurrRow = testCaseStartRow;
		}

		Iterator arg14 = sheet.getRow(0).cellIterator();

		while (arg14.hasNext()) {
			cell = (Cell) arg14.next();
			if (colName.equalsIgnoreCase(cell.getStringCellValue())) {
				testDataCell = testCaseCurrRow.getCell(cell.getColumnIndex());
			}
		}

		return testDataCell;
	}


	public static String getData(String sheetName, String colName, boolean isIteratedData) {
		String data = null;
		Cell testDataCell = null;
		FileInputStream testSetExcelFile = null;

		try {
			testSetExcelFile = new FileInputStream(new File(ControlData.currTestSetExcelWorkbookFileName));
			XSSFWorkbook e = new XSSFWorkbook(testSetExcelFile);
			testDataCell = findTestDataCell(e, sheetName, colName, isIteratedData);
			data = testDataCell.getStringCellValue();
			if (data.length() > 0 && data.charAt(0) == 64) {
				data = SharedDataBook.getData(data.substring(1), colName);
			}

			e.close();
			testSetExcelFile.close();
		} catch (FileNotFoundException arg6) {
			arg6.printStackTrace();
		} catch (IOException arg7) {
			arg7.printStackTrace();
		}

		return data;
	}

	public static String getData(String sheetName, String colName) {
		return getData(sheetName, colName, true);
	}

	public static void putData(String sheetName, String colName, String data, boolean isIteratedData) {
		Cell testDataCell = null;
		FileInputStream testSetExcelFile = null;

		try {
			testSetExcelFile = new FileInputStream(new File(ControlData.currTestSetExcelWorkbookFileName));
			XSSFWorkbook e = new XSSFWorkbook(testSetExcelFile);
			testDataCell = findTestDataCell(e, sheetName, colName, isIteratedData);
			String cellData = testDataCell.getStringCellValue();
			if (cellData.length() > 0) {
				if (cellData.charAt(0) == 64) {
					SharedDataBook.putData(cellData.substring(1), colName, data);
				} else {
					testDataCell.setCellValue(data);
				}
			} else {
				testDataCell.setCellValue(data);
			}

			testSetExcelFile.close();
			FileOutputStream outFile = new FileOutputStream(new File(ControlData.currTestSetExcelWorkbookFileName));
			e.write(outFile);
			outFile.close();
			e.close();
		} catch (FileNotFoundException arg8) {
			arg8.printStackTrace();
		} catch (IOException arg9) {
			arg9.printStackTrace();
		}

	}

	public static void putData(String sheetName, String colName, String data) {
		putData(sheetName, colName, data, true);
	}


	public static String getData(String wbName, String sheetName, String rowName, String colName) {
		String data = null;
		Cell testDataCell = null;
		FileInputStream testSetExcelFile = null;

		try {
			testSetExcelFile = new FileInputStream(new File(wbName));
			XSSFWorkbook e = new XSSFWorkbook(testSetExcelFile);
			testDataCell = findTestDataCell(e, sheetName, rowName, colName);
			data = testDataCell.getStringCellValue();
			if (data.length() > 0 && data.charAt(0) == 64) {
				data = SharedDataBook.getData(data.substring(1), colName);
			}

			e.close();
			testSetExcelFile.close();
		} catch (FileNotFoundException arg6) {
			arg6.printStackTrace();
		} catch (IOException arg7) {
			arg7.printStackTrace();
		}
		return data;

		//test Kireeti
	}


	public static Cell findTestDataCell(XSSFWorkbook workbook,String sheetName, String rowName, String colName) {

		XSSFSheet sheet = workbook.getSheet(sheetName);
		Iterator rowIterator = sheet.iterator();
		Row row = null;
		Row testCaseStartRow = null;
		Object testCaseEndRow = null;
		Row testCaseCurrRow = null;
		Cell testDataCell = null;

		Cell cell;
		label62: while (rowIterator.hasNext()) {
			row = (Row) rowIterator.next();
			cell = row.getCell(0);
			if (rowName.equalsIgnoreCase(cell.getStringCellValue())) {
				testCaseStartRow = cell.getRow();
				if (rowIterator.hasNext()) {
					String cellIterator = "";

					while (true) {
						if (!rowIterator.hasNext()) {
							break label62;
						}

						row = (Row) rowIterator.next();
						cell = row.getCell(0);
						cellIterator = cell != null ? cell.getStringCellValue() : "";
						if (!rowName.equalsIgnoreCase(cellIterator)) {
							testCaseEndRow = sheet.getRow(cell.getRowIndex() - 1);
							break label62;
						}

						testCaseEndRow = sheet.getRow(row.getRowNum());
					}
				} else {
					testCaseEndRow = testCaseStartRow;
					break;
				}
			}
		}


		testCaseCurrRow = testCaseStartRow;


		Iterator arg14 = sheet.getRow(0).cellIterator();
		while (arg14.hasNext()) {
			cell = (Cell) arg14.next();
			if (colName.equalsIgnoreCase(cell.getStringCellValue())) {
				testDataCell = testCaseCurrRow.getCell(cell.getColumnIndex());
			}
		}
		return testDataCell;
	}


















}//EOC