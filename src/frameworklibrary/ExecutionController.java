
package frameworklibrary;

import frameworklibrary.Config;
import frameworklibrary.ControlData;
import frameworklibrary.Report;
import frameworklibrary.TestCaseExecutor;
import frameworklibrary.Timer;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExecutionController {
	public static List<HashMap<String, String>> testCaseExecutionList = new ArrayList();
	public static String exCtrl = "";

	public static void readExecutionControllerExcel() {
		String[] arrstring = Config.testSuiteNames.split(",");
		int n = arrstring.length;

		for (int n2 = 0; n2 < n; ++n2) {
			String testSuiteName = arrstring[n2];

			try {
				FileInputStream e = new FileInputStream(new File(exCtrl));
				XSSFWorkbook workbook = new XSSFWorkbook(e);
				XSSFSheet sheet = workbook.getSheet(testSuiteName);
				Iterator rowIterator = sheet.iterator();
				Row row = (Row) rowIterator.next();
				int noOfexecutionParameters = row.getLastCellNum() - row.getFirstCellNum() + 1;
				String[] executionParameters = new String[noOfexecutionParameters];
				Iterator cellIterator = row.cellIterator();

				int currColNo;
				for (currColNo = 0; cellIterator.hasNext(); ++currColNo) {
					Cell executionParametersMap = (Cell) cellIterator.next();
					executionParameters[currColNo] = executionParametersMap.getStringCellValue();
				}

				while (rowIterator.hasNext()) {
					HashMap arg16 = new HashMap();
					row = (Row) rowIterator.next();
					cellIterator = row.cellIterator();

					for (currColNo = 0; cellIterator.hasNext(); ++currColNo) {
						Cell cell = (Cell) cellIterator.next();
						arg16.put(executionParameters[currColNo], cell.getStringCellValue());
					}

					if (((String) arg16.get("Run")).equalsIgnoreCase("Yes")) {
						testCaseExecutionList.add(arg16);
					}
				}

				workbook.close();
				e.close();
			} catch (Exception arg15) {
				arg15.printStackTrace();
			}
		}

		System.out.println("\nNo Of TestCases set to run:" + testCaseExecutionList.size());
	}

	public static void doTestExecution() {
		ControlData.currentTestCaseCount = 0;
		int totalTestCaseSetToRun = testCaseExecutionList.size();
		Timer executionTimer = new Timer();
		executionTimer.start();
		Report.updateSummaryReportHeader();
		Iterator arg2 = testCaseExecutionList.iterator();

		while (arg2.hasNext()) {
			HashMap executionParametersMap = (HashMap) arg2.next();
			executionParametersMap.put("TestCaseNo", String.valueOf(++ControlData.currentTestCaseCount));
			ControlData.currentTestCaseTime = 0L;
			Timer testCaseTimer = new Timer();
			testCaseTimer.start();
			ControlData.currTestCaseID = (String) executionParametersMap.get("TC_ID");
			ControlData.currTestCaseName = (String) executionParametersMap.get("TestCase_Name");
			Report.updateDetailedReportHeader();
			System.out.println("\n");
			System.out.println("*******************************Start********************************");
			System.out.print("Current TC: " + ControlData.currentTestCaseCount);
			System.out.print("   Remaining TC: " + (totalTestCaseSetToRun - ControlData.currentTestCaseCount));
			System.out.println("   Total TC: " + totalTestCaseSetToRun);
			System.out.print("\nTestSet: " + (String) executionParametersMap.get("TestSet"));
			System.out.print("   TC_ID: " + (String) executionParametersMap.get("TC_ID"));
			System.out.println("   Browser: "+ Config.browser);
			System.out.println("\nTestCase_Name: " + (String) executionParametersMap.get("TestCase_Name") + "\n");
			TestCaseExecutor.executeTestCase(executionParametersMap);
			System.out.println("********************************End*********************************");
			System.out.println("\n");
			testCaseTimer.end();
			ControlData.currentTestCaseTime = testCaseTimer.getTotalTime();
			Report.updateDetailedReportFooter();
			Report.updateTestCaseLog(executionParametersMap);
		}

		executionTimer.end();
		ControlData.totalExecutionTime = executionTimer.getTotalTime();
		Report.updateSummaryReportFooter();
	}
}