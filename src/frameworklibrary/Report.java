/**
 * 
 */
package frameworklibrary;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import frameworklibrary.Config;
import frameworklibrary.ControlData;
import frameworklibrary.Report;
import frameworklibrary.StaticDataManager;
import frameworklibrary.TimeStamp;
import frameworklibrary.Timer;
import frameworklibrary.WebDriverFactory;

/**
 * @author 609684083---> Subhodeep Ganguly
 *
 */
public class Report {

	public static void updateTestStepLog(String stepName, String stepDescription, String status) {
		++ControlData.currentTestCase_StepLogCount;
		File detailedReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html");
		String fileName = detailedReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html" : "assets/DetailedReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		if (status.equalsIgnoreCase("Pass")) {
			++ControlData.currentTestCase_PassedStepsCount;
			status = Config.allStepsScreenshot || Config.passedStepsScreenshot ? "<a href=\"" + Report.captureScreenshot() + "\"> <font color=\"Green\">" + status + "</font></a>" : "<font color=\"Green\">" + status + "</font>";
		} else if (status.equalsIgnoreCase("Warning")) {
			++ControlData.currentTestCase_WarningStepsCount;
			status = Config.allStepsScreenshot || Config.failedStepsScreenshot ? "<a href=\"" + Report.captureScreenshot() + "\"> <font color=\"Blue\">" + status + "</font></a>" : "<font color=\"Blue\">" + status + "</font>";
			ControlData.currentTestCaseHasWarnings = true;
		} else if (status.equalsIgnoreCase("Fail")) {
			++ControlData.currentTestCase_FailedStepsCount;
			status = Config.allStepsScreenshot || Config.failedStepsScreenshot ? "<a href=\"" + Report.captureScreenshot() + "\"> <font color=\"Red\">" + status + "</font></a>" : "<font color=\"Red\">" + status + "</font>";
			ControlData.currentTestCasePass = false;
		} else {
			status = "<font color=\"Black\">" + status + "</font>";
		}
		String stepLogContents = "<tr><td align=\"center\">" + ControlData.currentTestCase_StepLogCount + "</td><td>" + stepName + "</td><td>" + stepDescription + "</td><td>" + status + "</td><td align=\"center\">" + TimeStamp.getTimeStamp("HH:mm:ss") + "</td></tr>";
		htmlContent = htmlContent.replace("<!--Placeholder-->", String.valueOf(stepLogContents) + "<!--Placeholder-->");
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(detailedReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateTestMethodLog() {
		File detailedReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html");
		String fileName = detailedReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html" : "assets/DetailedReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		String methodLogContents = "<tr><td></td><td align=\"center\" colspan=\"3\" scope=\"col\"><font color=\"Purple\"><B>" + ControlData.currentTestMethod + "</B></font>" + "</td><td align=\"center\">" + TimeStamp.getTimeStamp("HH:mm:ss") + "</td>" + "</tr>";
		htmlContent = htmlContent.replace("<!--Placeholder-->", String.valueOf(methodLogContents) + "<!--Placeholder-->");
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(detailedReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateDetailedReportHeader() {
		File detailedReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html");
		String fileName = detailedReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html" : "assets/DetailedReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		htmlContent = htmlContent.replace("Test Case - Results", ControlData.currTestCaseName);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(detailedReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateDetailedReportFooter() {
		File detailedReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html");
		String fileName = detailedReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID + ".html" : "assets/DetailedReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		String testStepStatusContents = "<font color=\"Green\"> Passed Steps: " + ControlData.currentTestCase_PassedStepsCount + " </font>" + "<font color=\"Blue\"> &emsp;&emsp;&emsp;Warning Steps: " + ControlData.currentTestCase_WarningStepsCount + " </font>" + "<font color=\"Red\"> &emsp;&emsp;&emsp;Failed Steps: " + ControlData.currentTestCase_FailedStepsCount + " </font>";
		htmlContent = htmlContent.replace("<!--TestSteps_Status_Placeholder-->", testStepStatusContents);
		String timeTakenByTestCase = "Test Case Execution Time: " + Timer.getDurationBreakdown(ControlData.currentTestCaseTime);
		htmlContent = htmlContent.replace("<!--TimeTaken_Placeholder-->", timeTakenByTestCase);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(detailedReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateTestCaseLog(HashMap<String, String> executionParametersMap) {
		File summaryReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/Summary.html");
		String fileName = summaryReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/Summary.html" : "assets/SummaryReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		String status = "Status";
		if (!ControlData.currentTestCasePass) {
			status = "<a href=\"" + executionParametersMap.get("TC_ID") + ".html" + "\"> <font color=\"Red\"> Fail </font></a>";
			++ControlData.noOfFailedCases;
		} else if (ControlData.currentTestCaseHasWarnings) {
			status = "<a href=\"" + executionParametersMap.get("TC_ID") + ".html" + "\"> <font color=\"Blue\"> Pass </font></a>";
			++ControlData.noOfWarnedCases;
		} else {
			status = "<a href=\"" + executionParametersMap.get("TC_ID") + ".html" + "\"> <font color=\"Green\"> Pass </font></a>";
			++ControlData.noOfPassedCases;
		}
		String stepLogContents = "<tr><td align=\"center\">" + executionParametersMap.get("TestCaseNo") + "</td><td>" + executionParametersMap.get("TestSet") + "</td><td>" + executionParametersMap.get("TC_ID") + "</td><td>" + executionParametersMap.get("TestCase_Name") + "</td><td>" + executionParametersMap.get("Browser") + "</td><td>"+ StaticDataManager.staticData.get("URL")+"</td><td>" + status + "</td><td>" + Timer.getDurationBreakdown(ControlData.currentTestCaseTime) + "</td></tr>";
		htmlContent = htmlContent.replace("<!--Placeholder-->", String.valueOf(stepLogContents) + "<!--Placeholder-->");
		if (ControlData.totalExecutionTime != 0) {
			htmlContent = htmlContent.replace("Total Execution Time: ", "Total Execution Time: " + ControlData.totalExecutionTime);
		}
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(summaryReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateSummaryReportHeader() {
		File summaryReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/Summary.html");
		String fileName = summaryReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/Summary.html" : "assets/SummaryReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		String replacementString = Config.testSuiteNames;
		if (replacementString.contains(",")) {
			replacementString = replacementString.replaceAll(",", ", ");
		}
		replacementString = "Test Suite: " + replacementString;
		htmlContent = htmlContent.replace("Test Suite - Results", replacementString);
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(summaryReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void updateSummaryReportFooter() {
		File summaryReportFile = new File("Results/Run_" + ControlData.executionTimeStamp + "/Summary.html");
		String fileName = summaryReportFile.exists() ? "Results/Run_" + ControlData.executionTimeStamp + "/Summary.html" : "assets/SummaryReport_Template.html";
		StringBuilder contentBuilder = new StringBuilder();
		try {
			String str;
			BufferedReader in = new BufferedReader(new FileReader(fileName));
			while ((str = in.readLine()) != null) {
				contentBuilder.append(str);
			}
			in.close();
		}
		catch (IOException in) {
			// empty catch block
		}
		String htmlContent = contentBuilder.toString();
		String testSuiteStatusContents = "<font color=\"Green\"> Passed Cases: " + ControlData.noOfPassedCases + " </font>" + "<font color=\"Blue\"> &emsp;&emsp;&emsp;Warning Cases: " + ControlData.noOfWarnedCases + " </font>" + "<font color=\"Red\"> &emsp;&emsp;&emsp;Failed Cases: " + ControlData.noOfFailedCases + " </font>";
		htmlContent = htmlContent.replace("<!--TestSuite_Status_Placeholder-->", testSuiteStatusContents);
		if (ControlData.totalExecutionTime != 0) {
			htmlContent = htmlContent.replace("Total Execution Time: ", "Total Execution Time: " + Timer.getDurationBreakdown(ControlData.totalExecutionTime));
		}
		try {
			BufferedWriter output = new BufferedWriter(new FileWriter(summaryReportFile));
			output.write(htmlContent);
			output.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void openTestSummaryReport() {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.open(new File("Results/Run_" + ControlData.executionTimeStamp + "/Summary.html"));
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String captureScreenshot() {
		String screenshotFolderPath = "Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID;
		String screenshotFileName = String.valueOf(ControlData.currentTestCase_StepLogCount) + ".jpg";
		File scrFile = ((TakesScreenshot)WebDriverFactory.getWebDriverObject()).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile, new File(String.valueOf(screenshotFolderPath) + "/" + screenshotFileName));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return String.valueOf(ControlData.currTestCaseID) + "/" + screenshotFileName;
	}

	public static void createResultFolder() {
		File resultsDirectory = new File("Results/Run_" + ControlData.executionTimeStamp);
		if (!resultsDirectory.exists()) {
			resultsDirectory.mkdirs();
		}
	}

	public static void createScreenshotFolder() {
		File resultsDirectory = new File("Results/Run_" + ControlData.executionTimeStamp + "/" + ControlData.currTestCaseID);
		if (!resultsDirectory.exists()) {
			resultsDirectory.mkdirs();
		}
	}
	
	
}
