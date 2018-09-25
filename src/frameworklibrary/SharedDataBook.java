
package frameworklibrary;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author 609684083
 *
 */
public class SharedDataBook {
	public static XSSFWorkbook sharedWorkBook;
	public static String dataFolder;

	public static Cell findTestDataCell(XSSFWorkbook workbook, String sheetName, String rowName, String colName) {
		XSSFSheet sheet = sharedWorkBook.getSheet(sheetName);
		Iterator rowIterator = sheet.iterator();
		Row row = null;
		Cell testDataCell = null;

		Cell cell;
		while (rowIterator.hasNext()) {
			row = (Row) rowIterator.next();
			cell = row.getCell(0);
			if (rowName.equalsIgnoreCase(cell.getStringCellValue())) {
				break;
			}
		}

		Iterator cellIterator = sheet.getRow(0).cellIterator();

		while (cellIterator.hasNext()) {
			cell = (Cell) cellIterator.next();
			if (colName.equalsIgnoreCase(cell.getStringCellValue())) {
				testDataCell = row.getCell(cell.getColumnIndex());
				break;
			}
		}

		return testDataCell;
	}

	public static String getData(String rowName, String colName) {
		String data = null;
		String sheetName = "SharedData";
		Cell testDataCell = null;

		try {
			FileInputStream e = new FileInputStream(new File(dataFolder + File.separator + "SharedDataBook.xlsx"));
			sharedWorkBook = new XSSFWorkbook(e);
			testDataCell = findTestDataCell(sharedWorkBook, sheetName, rowName, colName);
			data = testDataCell.getStringCellValue();
			sharedWorkBook.close();
			e.close();
		} catch (FileNotFoundException arg5) {
			arg5.printStackTrace();
		} catch (IOException arg6) {
			arg6.printStackTrace();
		}

		return data;
	}

	public static void putData(String rowName, String colName, String data) {
		String sheetName = "SharedData";
		Cell testDataCell = null;

		try {
			String e = dataFolder + File.separator + "SharedDataBook.xlsx";
			FileInputStream sharedWorkBookFile = new FileInputStream(new File(e));
			sharedWorkBook = new XSSFWorkbook(sharedWorkBookFile);
			testDataCell = findTestDataCell(sharedWorkBook, sheetName, rowName, colName);
			testDataCell.setCellValue(data);
			sharedWorkBookFile.close();
			FileOutputStream outFile = new FileOutputStream(new File(e));
			sharedWorkBook.write(outFile);
			outFile.close();
			sharedWorkBook.close();
		} catch (FileNotFoundException arg7) {
			arg7.printStackTrace();
		} catch (IOException arg8) {
			arg8.printStackTrace();
		}

	}
}