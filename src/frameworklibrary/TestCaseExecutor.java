
package frameworklibrary;


import frameworklibrary.ControlData;
import frameworklibrary.Report;
import frameworklibrary.WebDriverFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;


import testlogic.WebPage;

/**
 * @author 609684083
 *
 */
public class TestCaseExecutor {
	static boolean stopCurrTestCaseExecution;

	public static void executeTestCase(HashMap<String, String> executionParametersMap) {
		try {
			WebDriverFactory.createWebDriverObject(Config.browser);
			ControlData.currentTestCase_StepLogCount = 0;
			ControlData.currentTestCase_PassedStepsCount = 0;
			ControlData.currentTestCase_WarningStepsCount = 0;
			ControlData.currentTestCase_FailedStepsCount = 0;
			ControlData.currentTestCasePass = true;
			ControlData.currentTestCaseHasWarnings = false;
			ControlData.methodCount = new HashMap();
			Report.createScreenshotFolder();
			stopCurrTestCaseExecution = false;
			ControlData.currTestSetExcelWorkbookFileName = SharedDataBook.dataFolder + File.separator
					+ (String) executionParametersMap.get("TestSet") + ".xlsx";
			FileInputStream testSetExcelFile = new FileInputStream(
					new File(ControlData.currTestSetExcelWorkbookFileName));
			ControlData.currTestSetExcelWorkbook = new XSSFWorkbook(testSetExcelFile);
			XSSFSheet sheet = ControlData.currTestSetExcelWorkbook.getSheet("TestFlow");
			Iterator rowIterator = sheet.iterator();
			Row testCaseRow = null;

			Cell e;
			while (rowIterator.hasNext()) {
				Row cellIterator = (Row) rowIterator.next();
				e = cellIterator.getCell(0);
				if(e!=null)
				{
				if (ControlData.currTestCaseID.equals(e.getStringCellValue())) {
					testCaseRow = e.getRow();
				}
				}
			}

			Iterator cellIterator1 = testCaseRow.cellIterator();
			e = (Cell) cellIterator1.next();

			String methodName;
			while (cellIterator1.hasNext() && !stopCurrTestCaseExecution
					&& (methodName = ((Cell) cellIterator1.next()).getStringCellValue()) != null
					&& !methodName.equalsIgnoreCase("")) {
				executeTestMethod(methodName);
			}

			testSetExcelFile.close();
			WebDriverFactory.destroyWebDriverObject();
		} catch (Exception arg7) {
			ControlData.currentTestCasePass = false;
			arg7.printStackTrace();
		}

	}

	public static void executeTestMethod(String methodName) {
		String exceptionMessage;
		try {
			ControlData.currentTestMethod = methodName;
			Report.updateTestMethodLog();
			if (ControlData.methodCount.containsKey(methodName)) {
				int e = ((Integer) ControlData.methodCount.get(methodName)).intValue();
				++e;
				ControlData.methodCount.put(methodName, Integer.valueOf(e));
			} else {
				ControlData.methodCount.put(methodName, Integer.valueOf(1));
			}

			List arg18 = getClasses(WebPage.class.getClassLoader(), "testlogic");
			Iterator arg2 = arg18.iterator();

			while (arg2.hasNext()) {
				Class arg19 = (Class) arg2.next();
				Object businessclassObj = arg19.newInstance();

				try {
					Method method = arg19.getDeclaredMethod(methodName, new Class[0]);
					method.invoke(businessclassObj, new Object[0]);
					break;
				} catch (NoSuchMethodException arg5) {
					;
				}
			}
		} catch (NoSuchElementException arg6) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg6.getMessage();
			Report.updateTestStepLog("NoSuchElementException", exceptionMessage, "Fail");
		} catch (StaleElementReferenceException arg7) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg7.getMessage();
			Report.updateTestStepLog("StaleElementReferenceException", exceptionMessage, "Fail");
		} catch (TimeoutException arg8) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg8.getMessage();
			Report.updateTestStepLog("TimeoutException", exceptionMessage, "Fail");
		} catch (ElementNotVisibleException arg9) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg9.getMessage();
			Report.updateTestStepLog("ElementNotVisibleException", exceptionMessage, "Fail");
		}  catch (IllegalAccessException arg11) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg11.getMessage();
			Report.updateTestStepLog("IllegalAccessException", exceptionMessage, "Fail");
		} catch (IllegalArgumentException arg12) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg12.getMessage();
			Report.updateTestStepLog("IllegalArgumentException", exceptionMessage, "Fail");
		} catch (InvocationTargetException arg13) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg13.getCause().getMessage();
			Report.updateTestStepLog("InvocationTargetException", exceptionMessage, "Fail");
			stopCurrTestCaseExecution = true;
		} catch (SecurityException arg14) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg14.getCause().getMessage();
			Report.updateTestStepLog("SecurityException", exceptionMessage, "Fail");
		} catch (InstantiationException arg15) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg15.getMessage();
			Report.updateTestStepLog("InstantiationException", exceptionMessage, "Fail");
		} catch (ClassNotFoundException arg16) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg16.getMessage();
			Report.updateTestStepLog("ClassNotFoundException", exceptionMessage, "fail");
		} catch (Exception arg17) {
			ControlData.currentTestCasePass = false;
			exceptionMessage = arg17.getMessage();
			Report.updateTestStepLog("Exception", exceptionMessage, "Fail");
		}

	}

	public static List<Class> getClasses(ClassLoader classLoader, String myPackage) throws Exception {
		ArrayList classes = new ArrayList();
		URL packageURL = classLoader.getResource(myPackage);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(packageURL.openStream()));
		String fileName = null;

		while ((fileName = bufferedReader.readLine()) != null) {
			if (fileName.endsWith(".class")) {
				classes.add(Class
						.forName(String.valueOf(myPackage) + "." + fileName.substring(0, fileName.lastIndexOf(46))));
			}
		}

		return classes;
	}
}