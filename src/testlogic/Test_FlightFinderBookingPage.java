package testlogic;
import java.awt.AWTException;
import java.util.ArrayList;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import frameworklibrary.*;

import uimap.HomePage;
import uimap.MasterPage;
import uimap.FlightFinder;

public class Test_FlightFinderBookingPage extends WebPage {

	public Test_FlightFinderBookingPage() {
		super();
	}

	//For BT

	public static String URL;

	public static void verifyFlightfinderLogo(){
		try{
		if(isDisplayed(FlightFinder.flightFinderLogo)){
			
				Report.updateTestStepLog("Flight Finder Page", "Flight Finder Logo is displayed", "Pass");
				waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page", "Flight Finder Logo is NOT displayed", "Fail");
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
		
	
	public static void selectTripType(){
		if(isDisplayed(FlightFinder.journeyType)){
			clickOn(FlightFinder.journeyType);
			waitForPageToLoad();
				Report.updateTestStepLog("Flight Finder Page- Trip Type", "Selected Necessary Journey Type", "Pass");
				waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page -Trip Type", "Not Selected Necessary Journey Type", "Fail");
		}
	
	public static void selectPassengerCount(){
		if(isDisplayed(FlightFinder.PassengersCountfromdropdown )){
			selectDropDownValue(FlightFinder.PassengersCountfromdropdown, 1);
            snooze(2000);
			Report.updateTestStepLog("Flight Finder Page - Passenger", "Number of passengers Selected", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Passenger", "Number of passengers NOT Selected", "Fail");
		}
	
	public static void selectDepartingCity(){
		if(isDisplayed(FlightFinder.DepartingCity)){
			selectDropDownValueByVal(FlightFinder.DepartingCity, "Frankfurt");
			//selectDropDownValueByText(FlightFinder.PassengersCountfromdropdown, "Frankfurt");
            snooze(2000);
				Report.updateTestStepLog("Flight Finder Page - Departing City", "Selected Departing City", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Departing City", "Number of passengers NOT Selected", "Fail");
		}
	
	public static void selectDeptDateMonth(){
		try{
		if(isDisplayed(FlightFinder.Month) && isDisplayed(FlightFinder.Date) ){
			snooze(2000);
			//selectDropDownValueByVal(FlightFinder.Month, "September");
			selectDropDownValue(FlightFinder.Month,8);
			snooze(2000);
			//selectDropDownValueByVal(FlightFinder.Date, "25");
			selectDropDownValue(FlightFinder.Date, 20);
            snooze(2000);
				Report.updateTestStepLog("Flight Finder Page - Departure Date", "Selected Departure Date & Month", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Departure Date", "Not Selected Departure Date & Month", "Fail");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		
	
	public static void selectArrivalCity(){
		if(isDisplayed(FlightFinder.ArrivingCity)){
			selectDropDownValueByText(FlightFinder.ArrivingCity, "Seattle");
            snooze(2000);
				Report.updateTestStepLog("Flight Finder Page - Arrival City", "Selected Arrival City", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Arrival City", "Number of passengers NOT Selected", "Fail");
		}
	
	public static void selectArrivalDateMonth(){
		if(isDisplayed(FlightFinder.ReturningMonth) && isDisplayed(FlightFinder.ReturningDay) ){
			selectDropDownValue(FlightFinder.ReturningMonth,10);
            snooze(2000);
			selectDropDownValue(FlightFinder.ReturningDay,25);
				Report.updateTestStepLog("Flight Finder Page - Arrival Date", "Selected Arrival Date & Month", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Arrival Date", "Not Selected Departure Date & Month", "Fail");
		}
	
	public static void selectPreferenceClass(){
		if(isDisplayed(FlightFinder.ServiceClass) && isDisplayed(FlightFinder.AirlinePreference) ){
			clickOn(FlightFinder.ServiceClass);
			snooze(2000);
			selectDropDownValue(FlightFinder.AirlinePreference, 2);
				Report.updateTestStepLog("Flight Finder Page - Service Class & Preference", "Selected Airline Preference", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Arrival Date", "Not Selected Departure Date & Month", "Fail");
		}
	
	public static void clickContinue(){
		if(isDisplayed(FlightFinder.ContinueBtn)){
			clickOn(FlightFinder.ContinueBtn);
			snooze(2000);
				Report.updateTestStepLog("Flight Finder Page - Click on Continue Button", "Continue Button Clicked", "Pass");
				//waitForPageToLoad();
			} else
				Report.updateTestStepLog("Flight Finder Page - Arrival Date", "Not Selected Departure Date & Month", "Fail");
		}
	
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

	/*public void signInPortal() throws InterruptedException{
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

		}*/
		
	
		
		
	public void closeURL()
	{
		manageURL();
		String url = URL;

			Report.updateTestStepLog("CLosing Page..", "Closed Page page successfully - ("+url+")", "Pass");
	
		driver.close();
	}
	public void openPopup(){

		manageURL();

		String url = URL;
		driver.get(url);
		waitForPageToLoad();

		DynamicDataManager.initializeAllDynamicData();

		Locator continueLnkForIe = new Locator("xpath", "//a[contains(text(),'Continue')]");

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
			}*/
		}
//		closeHelpUsPopup();

		DynamicDataManager.miscellenousData.put("envURl", url);//Used it in some other methods

		if(isDisplayed(HomePage.btnLogin))
			Report.updateTestStepLog("Home Page", "Landed Home page successfully ("+url+")", "Pass");
		else
			Report.updateTestStepLog("Home Page", "Not landed Home page ("+url+")", "Fail");
	}


}	

		