/**
 * 
 */
package frameworklibrary;

import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import frameworklibrary.TimeStamp;

/**
 * @author 609684083--->Subhodeep Ganguly
 *
 */
public class ControlData {


	public static final String executionTimeStamp = TimeStamp.getTimeStamp("yyyy-MM-dd_HH-mm-ss");
	public static XSSFWorkbook currTestSetExcelWorkbook;
	public static String currTestSetExcelWorkbookFileName;
	public static String currTestCaseID;
	public static String currTestCaseName;
	public static int currentTestCase_StepLogCount;
	public static int currentTestCase_PassedStepsCount;
	public static int currentTestCase_FailedStepsCount;
	public static int currentTestCase_WarningStepsCount;
	public static int currentTestCaseCount;
	public static boolean currentTestCasePass;
	public static boolean currentTestCaseHasWarnings;
	public static String currentTestMethod;
	public static long currentTestCaseTime;
	public static long totalExecutionTime;
	public static long currentTestMethodTime;
	public static HashMap<String, Integer> methodCount;
	public static int noOfPassedCases;
	public static int noOfFailedCases;
	public static int noOfWarnedCases;

	static {
		totalExecutionTime = 0;
		noOfPassedCases = 0;
		noOfFailedCases = 0;
		noOfWarnedCases = 0;
	}


}
