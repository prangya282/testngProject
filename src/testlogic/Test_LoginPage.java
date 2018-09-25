package testlogic;
import java.awt.AWTException;
import java.util.ArrayList;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import frameworklibrary.*;

import uimap.HomePage;
import uimap.MasterPage;

public class Test_LoginPage extends WebPage {

	public Test_LoginPage() {
		super();
	}

	//For BT

	public static String URL;
	String userName = DataBook.getData("testData", "UserName"),
			password = DataBook.getData("testData", "Password");

	
	private static void manageURL(){
		String live= "http://newtours.demoaut.com/", 
				modelA= "http://newtours.demoaut.com/",
				modelB= "http://newtours.demoaut.com/",
				sanity = "http://newtours.demoaut.com/",
				ref = "ref1.btwholesale.com";//Change Accordingly
		if(Config.executionURL.equalsIgnoreCase("live"))
			URL = live;
		else if(Config.executionURL.equalsIgnoreCase("modela"))
			URL = modelA;
		else if(Config.executionURL.equalsIgnoreCase("modelb"))
			URL = modelB;
		else if(Config.executionURL.equalsIgnoreCase("sanity"))
			URL = sanity;
		else if(Config.executionURL.equalsIgnoreCase("Ref"))
			URL = ref;
		else if(Config.executionURL.equals(null))
			Report.updateTestStepLog("Execution Environment Setup", "Please enter the appropriate ENV in Static data", "Warning");

	}


	public static boolean isAlertPresent() { 
		try { 
			driver.switchTo().alert(); 
			return true; 
		}   
		catch (NoAlertPresentException Ex) { 
			return false; 
		}   
	}  


	public void openSiteURLValidateTitle() throws InterruptedException{

		//manageURL();

		//String url = URL;
		String url1="http://newtours.demoaut.com/";
		driver.get(url1);
		waitForPageToLoad();
		String actualURL=driver.getTitle();

		DynamicDataManager.initializeAllDynamicData();
		if(actualURL.equalsIgnoreCase("Welcome: Mercury Tours")){
			Report.updateTestStepLog("Home Page", "Landed Home page successfully ("+url1+")", "Pass");
		    Thread.sleep(3000);
		    }

		else
			Report.updateTestStepLog("Home Page", "Not landed Home page ("+url1+")", "Fail");

		/*Locator continueLnkForIe = new Locator("xpath", "//a[contains(text(),'Continue')]");

		if(isDisplayed(continueLnkForIe)){
			clickOn(continueLnkForIe);
			waitForPageToLoad();

			/*if(!StaticDataManager.staticData.get("URL").equalsIgnoreCase("live")){

				if(isAlertPresent()){
					driver.switchTo().alert().accept();
					snooze(1000);
					driver.switchTo().alert().accept();
					waitForPageToLoad();
				}
			}
		}
//		closeHelpUsPopup();

		DynamicDataManager.miscellenousData.put("envURl", url);//Used it in some other methods

		if(isDisplayed(HomePage.btnLogin))
			Report.updateTestStepLog("Home Page", "Landed Home page successfully ("+url+")", "Pass");
		else
			Report.updateTestStepLog("Home Page", "Not landed Home page ("+url+")", "Fail");*/
	}

	public void signInPortal() throws InterruptedException{
		Thread.sleep(3000);
		//String username="subho.qtp";
		//String password="qaz1wsx2";
		
		if(isDisplayed(HomePage.userName) || isDisplayed(HomePage.passWord) || isDisplayed(HomePage.signIn) ){
			Report.updateTestStepLog("Login Page", "User Name, Password & SignIn Button Displayed in the Login Page", "Pass");
			waitForPageToLoad();
			enterLoginCredentials_Homepage(userName,password);
			clickOn(HomePage.signIn);
			waitForPageToLoad();

			Report.updateTestStepLog("Login Page", "Clicked on Sign In Button", "Pass");

		}
		
		else
			Report.updateTestStepLog("Login Page", "User Name, Password & SignIn Button NOT Displayed in the Login Page", "Fail");
	}
		public void verifyFlightFinderTitle(){
		waitForPageToLoad();
		String URLTitle=driver.getTitle();

		DynamicDataManager.initializeAllDynamicData();
		if(URLTitle.equalsIgnoreCase("Find a Flight: Mercury Tours:"))
			Report.updateTestStepLog("Flight Finder Page", "Landed Flight Finder page successfully ("+URLTitle+")", "Pass");
		else
			Report.updateTestStepLog("Flight Finder Page", "Not landed Home page ("+URLTitle+")", "Fail");
		
	}
		
		private void enterLoginCredentials(String userName, String password) {

			waitForVisibleElement(HomePage.txtUserName); //For firefox..will change once the new firefox version is stable with new Selenium. Works fine in Chrome
			if(isDisplayed(HomePage.txtUserName)){
				typeIn(HomePage.txtUserName, userName);
				typeIn(HomePage.txtpassword, password);
				snooze(1000);
			} else
				Report.updateTestStepLog("User Login", "Username text box is not displayed", "Fail");
		}
		
		private void enterLoginCredentials_Homepage(String userName, String password) {

			waitForVisibleElement(HomePage.userName); //For firefox..will change once the new firefox version is stable with new Selenium. Works fine in Chrome
			if(isDisplayed(HomePage.userName)){
				typeIn(HomePage.userName, userName);
				typeIn(HomePage.passWord, password);
				Report.updateTestStepLog("User Login", "Typed in Username :"+userName+ "& Password :"+password+" in text box", "Pass");

				snooze(1000);
			} else
				Report.updateTestStepLog("User Login", "Username text box is not displayed", "Fail");
		}
		
		public void quitAllSessions(){		
			snooze(1000);
			driver.quit();

		}

} // End of Class
