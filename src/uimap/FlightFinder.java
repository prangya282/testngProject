package uimap;
import frameworklibrary.*;

/**
 * @author Subhodeep
 *
 */
public class FlightFinder {
	
	//*********************  HelpUs Pop up  ****************************

	/*
	//flight finder logo
		@FindBy(css="img[src*='/images/masts/mast_flightfinder.gif']")
		public static WebElement FlightfinderLogo;
		
		//type button click
		@FindBy(xpath="//input[@value='oneway']")
		public static WebElement journeyType;
		
		//passengers count 
		
		@FindBy(xpath="//select[@name='passCount']")
		public static WebElement PassengersCountfromdropdown;
		
		//departing from
		
		@FindBy(xpath="//select[@name='fromPort']")
		public static WebElement DepartingCity;
		
		
		//find month
		
		@FindBy(xpath="//select[@name='fromMonth']")
		public static WebElement Month;
		
		@FindBy(xpath="//select[@name='fromDay']")
		public static WebElement Date;
		
		
		@FindBy(xpath="//select[@name='toPort']")
		public static WebElement ArrivingCity;
		
		
		//return month
		@FindBy(xpath="//select[@name='toMonth']")
		public static WebElement ReturningMonth;
		
		//preferences-service class
		
		@FindBy(xpath="//input[@value='Business']")
		public static WebElement ServiceClass;
		
		// to find airline 
		@FindBy(xpath="//select[@name='airline']")
		public static WebElement AirlinePreference;
		
		//click on continue button
		
		@FindBy(xpath="//input[@name='findFlights']")
		public static WebElement ContinueBtn;
		
		*/
	//public static final String flightFinderLogo = "//iframe[@id='edr_l_403438956']";
	
	//*********************  Flight Finder Page  ****************************


	//public static final Locator flightFinderLogo=  new Locator("xpath","//img[@src='/images/masts/mast_flightfinder.gif']");
	public static final Locator flightFinderLogo=  new Locator("cssSelector","img[src*='/images/masts/mast_flightfinder.gif']");

	public static final Locator journeyType = new Locator("xpath","//input[@value='oneway']"); // as the normal one was not working

	public static final Locator PassengersCountfromdropdown = new Locator("xpath","//select[@name='passCount']");

	public static final Locator DepartingCity = new Locator("xpath","//select[@name='fromPort']");

	public static final Locator Month = new Locator("xpath","//select[@name='fromMonth']");
	
	public static final Locator Date = new Locator("xpath","//select[@name='fromDay']");
	
	public static final Locator ArrivingCity = new Locator("xpath","//select[@name='toPort']");

	public static final Locator ReturningMonth = new Locator("xpath","//select[@name='toMonth']");
	
	public static final Locator ReturningDay = new Locator("xpath","//select[@name='toDay']");


	public static final Locator ServiceClass = new Locator("xpath","//input[@value='First']");
 
	public static final Locator AirlinePreference = new Locator("xpath","//select[@name='airline']");
	
	public static final Locator ContinueBtn = new Locator("xpath","//input[@name='findFlights']");


	//****************************************  First time user Popup********************************************************************//
	public static final String FTPopup = "//div[contains(@class,'firstTimePopup')]";

	public static final Locator popupFirstTimeUser = new Locator("xpath",FTPopup);

	public static final Locator lnkCloseCross_FirstTimePopup = new Locator("xpath", FTPopup + "//a[@title='Close']");
	
	public static final Locator btnClose_FirstTimePopup = new Locator("xpath",FTPopup +"//a[@class='lbutton right new_proceed_Btn']");
	
	public static final Locator drpdwnHowDoI_FirstTimePopup = new Locator("xpath",FTPopup+"//select[@class='new-select-ui']");

	
} // EOC
