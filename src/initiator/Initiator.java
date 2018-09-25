package initiator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import frameworklibrary.Config;
import frameworklibrary.ControlData;
import frameworklibrary.DataBook;
import frameworklibrary.ExecutionController;
import frameworklibrary.Report;
import frameworklibrary.SharedDataBook;
import frameworklibrary.StaticDataManager;
import frameworklibrary.TimeStamp;
import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxProfile;


/**
 * @author - Subhodeep Ganguly
 *
 */
public class Initiator {

	public static String executionTimeStamp = TimeStamp.getTimeStamp("yyyy-MM-dd_HH-mm-ss");


	public static void main(String args[]){
		
		
		
		/*try {
			
			URL url = new URL("https://www.google.com");
			
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setConnectTimeout(3000);
			connection.connect();
			
			System.out.println(connection.getResponseCode());
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String executionTimeStampStart = TimeStamp.getTimeStamp("yyyy-MM-dd_HH-mm-ss");
		System.out.println("Initiator has been trigerred successfully - YYYY/MM/DD HH-MM-SS :"+executionTimeStampStart+ "\n");

		killBrowser("iexplore");
		//killBrowser("chrome");
		killBrowser("firefox");

		readMasterConfig();
		Config.readConfigFile(); 
		System.out.println("Execution Started for: \n\n"+"\nTestSuite\\s:\t"+Config.testSuiteNames+"\nEnvironment:\t"+Config.executionURL+" \nBrowser:\t"+Config.browser );

		ExecutionController.readExecutionControllerExcel();
		Report.createResultFolder();
		StaticDataManager.readConfigFile();
		ExecutionController.doTestExecution();
		Report.openTestSummaryReport();
  
	    String executionTimeStampEnd = TimeStamp.getTimeStamp("yyyy-MM-dd_HH-mm-ss");
		System.out.println("Execution has come to End successfully - YYYY/MM/DD HH-MM-SS :"+executionTimeStampEnd+ "\n");

	}

	public static  FirefoxProfile ffPrf(){

		FirefoxProfile ffProfile = new FirefoxProfile();
		File fireBug = new File("Resources\\Windows\\FireFox Add-Ons\\firebug-2.0.18-fx.xpi");
		ffProfile.setPreference("extensions.firebug.currentVersion", "2.0.18");
		File firepath = new File("Resources\\Windows\\FireFox Add-Ons\\firepath-0.9.7.1-fx.xpi");
		try {
			ffProfile.addExtension(fireBug);
			ffProfile.addExtension(firepath);
			JavaScriptError.addExtension(ffProfile);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		return ffProfile;
	}


	public static void killBrowser(String browser){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM "+browser+".exe");
		} catch (IOException arg0) {
			;
		}
	}

	
	public static void readMasterConfig(){

		String testSuites, environment,browser;

		environment = DataBook.getData("MasterConfig.xlsm","MasterConfig", "Config1", "Environment");
		testSuites = DataBook.getData("MasterConfig.xlsm", "MasterConfig", "Config1", "TestSuite");
		browser = DataBook.getData("MasterConfig.xlsm", "MasterConfig", "Config1", "Browser");

		Config.testSuiteNames = testSuites;
		Config.executionURL = environment;
		Config.execution = environment;
		Config.browser = browser;
		
	}















} //EOC


