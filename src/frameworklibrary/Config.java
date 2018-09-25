
package frameworklibrary;

import frameworklibrary.ExecutionController;
import frameworklibrary.SharedDataBook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
	
	public static boolean passedStepsScreenshot;
	public static boolean failedStepsScreenshot;
	public static boolean allStepsScreenshot;
	public static int fireFoxOffset;
	public static int chromeOffset;
	public static int internetExplorerOffset;
	public static int waitTimeInSeconds;
	public static String closeBrowserAfterExecution;
	public static String testSuiteNames;
	public static String browser;
	public static boolean isPDT;
	public static String executionURL;
	public static String execution = null;
	public static String iePath,chromePath,ffPath;

	public static void readConfigFile() {
		Properties prop = new Properties();
		FileInputStream input = null;

		try {
			input = new FileInputStream("config.prop");
			prop.load(input);
			passedStepsScreenshot = prop.getProperty("PassedSteps").equalsIgnoreCase("True");
			failedStepsScreenshot = prop.getProperty("FailedSteps").equalsIgnoreCase("True");
			allStepsScreenshot = prop.getProperty("AllSteps").equalsIgnoreCase("True");
			fireFoxOffset = Integer.parseInt(prop.getProperty("Firefox_ScreenOffset"));
			chromeOffset = Integer.parseInt(prop.getProperty("Chrome_ScreenOffset"));
			internetExplorerOffset = Integer.parseInt(prop.getProperty("InternetExplorer_ScreenOffset"));
			waitTimeInSeconds = Integer.parseInt(prop.getProperty("WaitTimeInSeconds"));
			closeBrowserAfterExecution = prop.getProperty("CloseBrowserAfterExecution");
			iePath = prop.getProperty("IEPath");
			chromePath = prop.getProperty("ChromePath");
			ffPath = prop.getProperty("FFPath");

			if (execution.equalsIgnoreCase("ModelA")) {
				ExecutionController.exCtrl = "ExecutionControllerForModelA.xlsx";
				SharedDataBook.dataFolder = "dataSheetsForModelA";
			} else if (execution.equalsIgnoreCase("ModelB")) {
				ExecutionController.exCtrl = "ExecutionControllerForModelB.xlsx";
				SharedDataBook.dataFolder = "dataSheetsForModelB";
			} else if (execution.equalsIgnoreCase("Live")) {
				ExecutionController.exCtrl = "ExecutionController.xlsx";
				SharedDataBook.dataFolder = "dataSheetExecution";
			} else if (execution.equalsIgnoreCase("Sanity")){
				ExecutionController.exCtrl = "ExecutionControllerForSanity.xlsx";
				SharedDataBook.dataFolder = "dataSheetsForPDT";
			} else if (execution.equalsIgnoreCase("Ref")){
				ExecutionController.exCtrl = "ExecutionControllerForRef.xlsx";
				SharedDataBook.dataFolder = "dataSheetsForRef";
			} else {
				System.out.println("Please enter the Execution Parameter value in \'Config.prop\' file ");
			}

		} catch (IOException arg14) {
			arg14.printStackTrace();
			if (input != null) {
				try {
					input.close();
				} catch (IOException arg13) {
					arg13.printStackTrace();
				}
			}
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException arg12) {
					arg12.printStackTrace();
				}
			}

		}

	}
}