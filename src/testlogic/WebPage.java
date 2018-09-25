package testlogic;
import frameworklibrary.*;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;




/**
 * @author 609684083
 *
 */
public class WebPage {
	public static WebDriver driver;

	public WebPage(){
		driver = WebDriverFactory.getWebDriverObject();
	}

	//************************* UserDefined Keywords **************************************

	public static void submit(Locator locator){
		locateElement(locator).submit();
	}

	public static void refreshPage(){
		driver.navigate().refresh();
		waitForPageToLoad();
	}

	public static void navigateToPreviousPage(){
		driver.navigate().back();
		waitForPageToLoad();
	}

	public static void snooze(long secs){	
		try {
			Thread.sleep(secs);
		} catch (InterruptedException e) {
			System.out.println("Sleep Interrupted (Exception Caused)");
			e.printStackTrace();
		}
	}


	public static void clickOnEleByTC(Locator locator){

		String loctype =locator.key,
				locvalue = locator.value;
		WebElement ele;

		if(loctype.equalsIgnoreCase("cssSelector")){
			try {
				ele= driver.findElement(By.cssSelector(locvalue));
				Thread.sleep(6000);
				ele.click();
			} catch(Exception e){
				Report.updateTestStepLog("Exception", e.getMessage(), "Fail");
			}

		} else if(loctype.equalsIgnoreCase("xpath")){
			try {
				ele= driver.findElement(By.xpath(locvalue));
				snooze(500);
				ele.click();
			} catch(Exception e){
				Report.updateTestStepLog("Exception", e.getMessage(), "Fail");
			}
		}



	}


	public static void putDataInCellFromLocator(Locator locator, String sheetName, String columnName){
		String data = locateElement(locator).getText().trim();
		DataBook.putData(sheetName, columnName, data);
		snooze(500);
	}

	public static void waitForPageToLoad() {

		if(driver instanceof ChromeDriver){
			int secs = 0;
			while(secs<=30){
				snooze(1000);
				secs++;
				if(((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete"))
					break;
				else
					continue;
			}
		} else {
			ExpectedCondition<Boolean> expectation = new
					ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
				}
			};

			WebDriverWait wait = new WebDriverWait(driver,30);
			try {
				wait.until(expectation);
			} catch(Throwable error) {
				System.out.println("Exception: Timeout waiting for Page Load Request to complete.");
				error.printStackTrace();
			}
		}
	} 

	public static void switchToBrowserTab(int browserTab){
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		if(tabs.size()>0){
			driver.switchTo().window(tabs.get(browserTab));	
		} else
			Report.updateTestStepLog("Switching to other tab", "There is no other window opened", "Warning");
	}

	// **********************************Action Class methods Start **********************************

	public static void moveToElement(Locator locator){
		Actions actions = new Actions(driver);
		actions.moveToElement(locateElement(locator)).perform();
		snooze(5000);
	}

	public static void moveToElement(Locator locator, String target, String replacement){
		Actions actions = new Actions(driver);
		actions.moveToElement(locateElement(locator,target,replacement)).perform();
		snooze(5000);
	}

	// **********************************Action Class methods End**********************************


	// *****************************  Third party website methods *************************************
	public String getPasswordFromMailinator(String Email){

		System.setProperty("webdriver.chrome.driver", "Resources\\Windows\\BrowserExes\\chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();

		commonMailFunction(Email,driver1);

		String[] passwordToReolve = null;

		try {
			snooze(2000);
			passwordToReolve = driver1.findElement(By.xpath("//strong[contains(text(),'password')]")).getText().trim().split("is:");
		} catch(Exception e){

		}

		String pass = passwordToReolve[1].trim();
		Report.updateTestStepLog("Password from Mailinator", "The password from the mailinator is <b>" + pass + "</b>", "Info");
		driver1.close();

		return pass;
	}

	public static String getPinFromMailinator(String Email){

		System.setProperty("webdriver.chrome.driver", "Resources\\Windows\\BrowserExes\\chromedriver.exe");
		WebDriver driver1 = new ChromeDriver();

		commonMailFunction(Email,driver1);

		String[] pinToresolve = null;
		try{
			pinToresolve = driver1.findElement(By.xpath("//strong[contains(text(),'YOUR PIN IS')]")).getText().trim().split("IS:");
		} catch(Exception e){

		}

		String pin = pinToresolve[1].trim();
		Report.updateTestStepLog("Password from Mailinator", "The Pin from the mailinator is <b>" + pin + "</b>", "Info");

		driver1.close();

		return pin;
	}

	/**
	 * @param Email
	 */

	/*public static final Locator lnkClickHereForMailinator = new Locator("xpath","//a[contains(text(),'here')]");
	public static final Locator txtEmail_Mailinator = new Locator("xpath","//input[@id='inboxfield' and @class='form-control']");
	public static final Locator btnGo_Mailinator = new Locator("cssSelector","button[class='btn btn-dark']");
	public static final Locator lnkMail_Mailinator = new Locator("xpath","html/body/div/div/div[1]/div/div/div/div[2]/div[3]/div/div[1]/div[2]/div[2]/div");
	public static final Locator frame_Mailinator = new Locator("xpath","//iframe[@id='publicshowmaildivcontent']");
	public static final Locator lnkContinueForIE = new Locator("cssSelector","a#overridelink");*/


	private static void commonMailFunction(String Email, WebDriver driver) {

		driver.get("https://www.mailinator.com/");
		waitForPageToLoad();
		driver.navigate().refresh();
		waitForPageToLoad();
		driver.manage().window().maximize();

		//FOR IE 
		handleIEException(driver);
		WebElement ele;

		try {
			ele = driver.findElement(By.xpath("//a[contains(text(),'here')]"));

			if(ele.isDisplayed()){
				ele.click();
				waitForPageToLoad();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		ele = driver.findElement(By.xpath("//input[@id='inboxfield' and @class='form-control']"));
		ele.sendKeys(Email);

		snooze(500);
		ele = driver.findElement(By.cssSelector("button[class='btn btn-dark']"));
		ele.click();

		snooze(3000);
		waitForPageToLoad();
		snooze(3000);

		try{
			ele = driver.findElement(By.xpath("(//div[@class='innermail ng-binding'])[1]"));
			ele.click();
			snooze(2000);
			waitForPageToLoad();
			snooze(2000);
		} catch (Exception e) {
			// TODO: handle exception
		}

		try{

			ele = driver.findElement(By.xpath("//iframe[@id='publicshowmaildivcontent']"));
			driver.switchTo().frame(ele);
			snooze(2500);

		} catch(Exception e){

		}	
	}

	/**
	 * 
	 */
	private static void handleIEException(WebDriver driver) {

		try {
			if(driver.findElement(By.cssSelector("a#overridelink")).isDisplayed()){
				driver.findElement(By.cssSelector("a#overridelink")).click();
				waitForPageToLoad();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}


	// *****************************  Third party website methods *************************************

	public static void compareData_caseIgnored(String dataName, String expected, String actual){
		if(expected.equalsIgnoreCase(actual))
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are same", "Info");
		else
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are NOT same", "Fail");
	}

	public static void compareData_casesNotIgnored(String dataName, String expected, String actual){
		if(expected.equals(actual))
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are same", "Info");
		else
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are NOT same", "Fail");
	}

	public static void compareValues(String dataName, int expected, int actual){
		if(expected ==(actual))
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are same", "Info");
		else
			Report.updateTestStepLog("Validate: "+ dataName, "<b>Expected: </b>"+ expected+" and <b>Actual: </b>" + actual + " are NOT same", "Fail");
	}


	//The below method uses contains with case ignored
	public static void compareIfContains_CaseIgnored(String dataName, String expected, String actual){
		if(actual.toLowerCase().contains(expected.toLowerCase()))
			Report.updateTestStepLog("Verify: "+ dataName, "<b>Actual: </b>"+ actual+" contains <b>expected: </b>" + expected, "Info");
		else
			Report.updateTestStepLog("Verify: "+ dataName, "<b>Actual: </b>"+ actual+" doesn't contain <b>expected: </b>" + expected, "Fail");
	}


	public static void verifyRegExCompliance(String dataName, String regEx, String text){
		if(text.matches(regEx))
			Report.updateTestStepLog("Validate: "+ dataName, "Expected: "+ regEx+" and Actual: " + text + " are same", "Pass");
		else
			Report.updateTestStepLog("Validate: "+ dataName, "Expected: "+ regEx+" and Actual: " + text + " are NOT same", "Fail");
	}


	public static boolean isImageDisplayed(Locator locator){
		return isImageDisplayed(locateElement(locator));
	}

	public static boolean isImageDisplayed(Locator locator, String target, String replacement){
		String key = locator.key;
		String value = locator.value.replace(target, replacement);
		return isImageDisplayed(new Locator(key, value));
	}

	public static boolean isImageDisplayed(WebElement imgElement){
		if(imgElement.isDisplayed()){
			if(driver instanceof InternetExplorerDriver)
				return  (Boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete;", imgElement);
			else
				return (Boolean) ((JavascriptExecutor)driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", 
						imgElement);
		} else {
			Report.updateTestStepLog("Image Tag", "Image tag is NOT in html", "Fail");
			return false;
		}
	}

	public static void selectDropDownValue(Locator locator, String visibleText){
		Select select = new Select(locateElement(locator));
		select.selectByVisibleText(visibleText);
	}

	public static void selectDropDownValue(Locator locator, int index){
		Select select = new Select(locateElement(locator));
		select.selectByIndex(index);
	}
	
	public static void selectDropDownValueByText(Locator locator, String text){
		Select select = new Select(locateElement(locator));
		select.selectByVisibleText(text);
	}

	public static void selectDropDownValueByVal(Locator locator, String value){
		Select select = new Select(locateElement(locator));
		select.selectByValue(value);
	}
	public static void verifyListOptions(WebElement listElement, String listName, String[] expectedListOptions){
		Select list = new Select(listElement);
		List<WebElement> actualListOptions = list.getOptions();
		if(expectedListOptions.length == actualListOptions.size()){
			int count = 0;
			for(WebElement actualListOption:actualListOptions){

				String dataName, expected, actual;
				dataName = listName+ " Option" + (count+1);
				expected = expectedListOptions[count];
				actual = actualListOption.getText();
				compareData_caseIgnored(dataName, expected, actual);
				count++;
			}
		} else {
			String dataName, expected, actual;
			expected = "";
			actual = "";
			dataName = listName+ " Options";
			for(String expectedListOption:expectedListOptions){
				expected = expected + "," + expectedListOption;
			}

			for(WebElement actualListOption:actualListOptions){
				actual = actual + "," + actualListOption.getText();
			}
			compareData_caseIgnored(dataName, expected, actual);
		}
	}


	public static void verifyListOptions(Locator locator, String listName, String[] expectedListOptions){
		verifyListOptions(locateElement(locator), listName, expectedListOptions);
	}


	public static ArrayList<String> getListOptions(Locator locator){

		ArrayList<String> optns = new ArrayList<>();

		Select drpdwn = new Select(locateElement(locator));
		List<WebElement> options = drpdwn.getOptions();

		for(WebElement option: options){
			optns.add(option.getText());
		}

		return optns;

	}

	public static void verifySelectedListOption(Locator locator, String listName, String expectedSelectedOption){
		Select list = new Select(locateElement(locator));
		String dataName, expected, actual;
		dataName = listName+ " Selected Option";
		expected = expectedSelectedOption;
		actual = list.getFirstSelectedOption().getText();
		compareData_caseIgnored(dataName, expected, actual);
	}


	public static ArrayList<WebElement> locateElements(Locator locator){

		List<WebElement> elements = null;

		if(locator.key.equalsIgnoreCase("xpath"))
			elements = driver.findElements(By.xpath(locator.value));
		else if(locator.key.equalsIgnoreCase("id"))
			elements = driver.findElements(By.id(locator.value));
		else if(locator.key.equalsIgnoreCase("name"))
			elements = driver.findElements(By.name(locator.value));
		else if(locator.key.equalsIgnoreCase("className"))
			elements = driver.findElements(By.className(locator.value));
		else if(locator.key.equalsIgnoreCase("linkText"))
			elements = driver.findElements(By.linkText(locator.value));
		else if(locator.key.equalsIgnoreCase("partialLinkText"))
			elements = driver.findElements(By.partialLinkText(locator.value));
		else if(locator.key.equalsIgnoreCase("tagName"))
			elements = driver.findElements(By.tagName(locator.value));
		else if(locator.key.equalsIgnoreCase("cssSelector"))
			elements = driver.findElements(By.cssSelector(locator.value));
		else  
			System.out.println("Check Locator Key and Value");

		return (ArrayList<WebElement>) elements;

	}

	public static int getElementsCount(Locator locator){
		return locateElements(locator).size();
	}


	public static boolean isElementPresent(Locator locator){

		boolean elementPresent = true;
		try {
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			if(locator.key.equalsIgnoreCase("xpath"))
				driver.findElement(By.xpath(locator.value));
			else if(locator.key.equalsIgnoreCase("id"))
				driver.findElement(By.id(locator.value));
			else if(locator.key.equalsIgnoreCase("name"))
				driver.findElement(By.name(locator.value));
			else if(locator.key.equalsIgnoreCase("className"))
				driver.findElement(By.className(locator.value));
			else if(locator.key.equalsIgnoreCase("linkText"))
				driver.findElement(By.linkText(locator.value));
			else if(locator.key.equalsIgnoreCase("partialLinkText"))
				driver.findElement(By.partialLinkText(locator.value));
			else if(locator.key.equalsIgnoreCase("tagName"))
				driver.findElement(By.tagName(locator.value));
			else if(locator.key.equalsIgnoreCase("cssSelector"))
				driver.findElement(By.cssSelector(locator.value));
			else  {
				System.out.println("Check Locator Key");
				elementPresent = false;
			}

		} catch (Exception e) {
			elementPresent = false;
		} finally {
			driver.manage().timeouts().implicitlyWait(Config.waitTimeInSeconds, TimeUnit.SECONDS);
		}
		return elementPresent;
	}


	public static WebElement locateElement(Locator locator){

		WebElement element = null;

		if(locator.key.equalsIgnoreCase("xpath"))
			element = driver.findElement(By.xpath(locator.value));
		else if(locator.key.equalsIgnoreCase("id"))
			element = driver.findElement(By.id(locator.value));
		else if(locator.key.equalsIgnoreCase("name"))
			element = driver.findElement(By.name(locator.value));
		else if(locator.key.equalsIgnoreCase("className"))
			element = driver.findElement(By.className(locator.value));
		else if(locator.key.equalsIgnoreCase("linkText"))
			element = driver.findElement(By.linkText(locator.value));
		else if(locator.key.equalsIgnoreCase("partialLinkText"))
			element = driver.findElement(By.partialLinkText(locator.value));
		else if(locator.key.equalsIgnoreCase("tagName"))
			element = driver.findElement(By.tagName(locator.value));
		else if(locator.key.equalsIgnoreCase("cssSelector"))
			element = driver.findElement(By.cssSelector(locator.value));
		else  
			System.out.println("Check Locator Key and Value");

		return element;
	}

	public static boolean isClickable(Locator locator){
       return isEnabled(locator);
  }

	
	public static boolean isDisplayed(Locator locator){
		if(isElementPresent(locator))
			return locateElement(locator).isDisplayed();
		else
			return false;
	}

	public static boolean isEnabled(Locator locator){
		return locateElement(locator).isEnabled();
	}

	public static boolean isSelected(Locator locator){
		return locateElement(locator).isSelected();
	}


	public static void clickOn(Locator locator){
		locateElement(locator).click();
	}


	public static void hoverOn(Locator locator){
		WebElement element = locateElement(locator);
		hoverOnElementJavaRobo(element);
	}

	public static void hoverOn(Locator locator, String target, String replacement){
		WebElement element = locateElement(locator, target, replacement);
		hoverOnElementJavaRobo(element);
	}


	public static void hoverOnElementJavaRobo(WebElement element){

		snooze(500);
		Dimension dimension = element.getSize();
		Point point = element.getLocation(); 
		int x, y;
		int browserHeightOffset = 0;
		int browserWidthOffset = 0;

		long windowOuterHeight = (long)((JavascriptExecutor) driver).executeScript("return window.outerHeight;");
		long windowInnerHeight = (long)((JavascriptExecutor) driver).executeScript("return window.innerHeight;");
		int windowInnerOuterHeightDifference = (int) (windowOuterHeight - windowInnerHeight); 

		if(driver instanceof ChromeDriver)
			browserHeightOffset = windowInnerOuterHeightDifference-Config.chromeOffset;
		else if(driver instanceof InternetExplorerDriver){
			browserHeightOffset = windowInnerOuterHeightDifference-Config.internetExplorerOffset;		
		}
		else if(driver instanceof FirefoxDriver){
			browserHeightOffset = windowInnerOuterHeightDifference-Config.fireFoxOffset;		
		}
		x = point.x + (dimension.width/2) + browserWidthOffset;
		y = point.y + (dimension.height/2) + browserHeightOffset;

		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}

		robot.mouseMove(x, y);

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		executorService.execute(new Runnable() {
			@Override
			public void run() {
				Robot robot2 = null;
				try {
					robot2 = new Robot();
				} catch (AWTException e) {
					e.printStackTrace();
				}

				int hoverTimeInMilliSeconds = 2000;
				int mouseMoveTimeInterval = 100;
				int count = hoverTimeInMilliSeconds/(mouseMoveTimeInterval*5);
				int offset = 1;

				while(count>0){
					snooze(mouseMoveTimeInterval);
					robot2.mouseMove(x-offset, y-offset);
					snooze(mouseMoveTimeInterval);
					robot2.mouseMove(x+offset, y-offset);
					snooze(mouseMoveTimeInterval);
					robot2.mouseMove(x+offset, y+offset);
					snooze(mouseMoveTimeInterval);
					robot2.mouseMove(x-offset, y+offset);
					snooze(mouseMoveTimeInterval);
					robot2.mouseMove(x, y);
					count--;
				}
				robot2.mouseMove(0,0);
			}
		});
		executorService.shutdown();
	}

	public static void hoverOnElementJavaScript(WebElement element){

		String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(mouseOverScript, element);
	}

	public static void hoverOnElementWebDriver(WebElement element){
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}


	public static void typeIn(Locator locator, String text){

		if(driver instanceof InternetExplorerDriver){
			WebPage.type(locator, text);
		} else {
			WebElement element = locateElement(locator);
			element.clear();
			element.sendKeys(text); 
		}
	}


	public static void type(Locator locator, String text){

		WebElement ele = locateElement(locator);
		ele.sendKeys(text);

	}
	
	public static void typeClearing(Locator locator, String text){
		WebElement element = locateElement(locator);
		element.clear();
		element.sendKeys(text); 
	}
	
	public static void clearElement(Locator locator){
		WebElement ele = locateElement(locator);
		ele.clear();
		snooze(1000);//Default time 
	}


	public static void selectCheckBox(Locator locator){

		WebElement element = locateElement(locator);
		if(!element.isSelected())
			element.click();
	}


	public static void deSelectCheckBox(Locator locator){
		WebElement element = locateElement(locator);
		if(element.isSelected())
			element.click();
	}

	public static String getTextOf(Locator locator){
		return locateElement(locator).getText();
	}


	public static String getTextOf(Locator locator, String target1, String replacement1, String target2, String replacement2, String target3, String replacement3){
		return locateElement(locator, target1, replacement1, target2, replacement2, target3, replacement3).getText();

	} 
	public static WebElement locateElement(Locator locator, String target1, String replacement1, String target2, String replacement2,  String target3, String replacement3){
		WebElement element = null;
		String key = locator.key;
		String value = locator.value.replace(target1, replacement1);
		value = value.replace(target2, replacement2);
		value = value.replace(target3, replacement3);
		element = locateElement(new Locator(key, value));
		return element;
	} 



	public static int getElementsCount(Locator locator, String target1, String replacement1, String target2, String replacement2){
		return locateElements(locator, target1, replacement1, target2, replacement2).size();
	} 
	public static ArrayList<WebElement> locateElements(Locator locator, String target1, String replacement1, String target2, String replacement2){

		List<WebElement> elements = null;
		String key = locator.key;
		String value = locator.value.replace(target1, replacement1);
		value = value.replace(target2, replacement2); 
		if(locator.key.equalsIgnoreCase("xpath"))
			elements = driver.findElements(By.xpath(value));
		else if(key.equalsIgnoreCase("id"))
			elements = driver.findElements(By.id(value));
		else if(key.equalsIgnoreCase("name"))
			elements = driver.findElements(By.name(value));
		else if(key.equalsIgnoreCase("className"))
			elements = driver.findElements(By.className(value));
		else if(key.equalsIgnoreCase("linkText"))
			elements = driver.findElements(By.linkText(value)); 
		else if(key.equalsIgnoreCase("partialLinkText"))
			elements = driver.findElements(By.partialLinkText(value));
		else if(key.equalsIgnoreCase("tagName"))
			elements = driver.findElements(By.tagName(value));
		else if(key.equalsIgnoreCase("cssSelector"))
			elements = driver.findElements(By.cssSelector(value));
		else  
			System.out.println("Check Locator Key and Value");

		return (ArrayList<WebElement>) elements;
	} 

	public static String getCSSof(Locator locator, String attributeName){
		return locateElement(locator).getCssValue(attributeName);
	}


	public static String getCSSof(Locator locator, String target, String replacement, String attributeName){
		return locateElement(locator, target, replacement).getCssValue(attributeName);
	}

	public static String getAttributeValueOf(Locator locator, String attributeName){
		return locateElement(locator).getAttribute(attributeName);
	}

	public static String changeLstOption(Locator locator, String listName){

		int offset = 1;
		Select lst = new Select(locateElement(locator));
		List<WebElement> options = lst.getOptions();
		int noOfOptions = options.size();
		String defaultOption = lst.getFirstSelectedOption().getText();

		int optionIndex = 0;
		while (optionIndex<noOfOptions){
			if(options.get(optionIndex).getText().equals(defaultOption)){
				break;
			}
			optionIndex++;
		}
		int changedOptionIndex = (optionIndex + offset) % noOfOptions;
		lst.selectByIndex(changedOptionIndex);
		String currentOption = lst.getFirstSelectedOption().getText();
		if(!defaultOption.equalsIgnoreCase(currentOption))
			Report.updateTestStepLog("Change "+ listName +" Option", listName +" Option changed successfully(Default: "+defaultOption+" changed: "+currentOption+")", "Pass");
		else
			Report.updateTestStepLog("Change "+ listName +" Option", listName +" Option NOT get changed(Default: "+defaultOption+" changed: "+currentOption+")", "Fail");

		return currentOption;
	}


	public static String getSelectedOption_List(Locator locator){

		Select lst = new Select(locateElement(locator));
		return lst.getFirstSelectedOption().getText();
	}


	public static String getSelectedOption_List(Locator locator, String target, String replacement){
		Select lst = new Select(locateElement(locator, target, replacement));
		return lst.getFirstSelectedOption().getText();
	}

	public static ArrayList<WebElement> locateElements(Locator locator, String target, String replacement){

		List<WebElement> elements = null;
		String key = locator.key;
		String value = locator.value.replace(target, replacement);

		if(locator.key.equalsIgnoreCase("xpath"))
			elements = driver.findElements(By.xpath(value));
		else if(key.equalsIgnoreCase("id"))
			elements = driver.findElements(By.id(value));
		else if(key.equalsIgnoreCase("name"))
			elements = driver.findElements(By.name(value));
		else if(key.equalsIgnoreCase("className"))
			elements = driver.findElements(By.className(value));
		else if(key.equalsIgnoreCase("linkText"))
			elements = driver.findElements(By.linkText(value));
		else if(key.equalsIgnoreCase("partialLinkText"))
			elements = driver.findElements(By.partialLinkText(value));
		else if(key.equalsIgnoreCase("tagName"))
			elements = driver.findElements(By.tagName(value));
		else if(key.equalsIgnoreCase("cssSelector"))
			elements = driver.findElements(By.cssSelector(value));
		else  
			System.out.println("Check Locator Key and Value");

		return (ArrayList<WebElement>) elements;
	}


	public static int getElementsCount(Locator locator, String target, String replacement){
		return locateElements(locator, target, replacement).size();
	}


	public static boolean isElementPresent(Locator locator, String target, String replacement){

		String key = locator.key;
		String value = locator.value.replace(target, replacement);
		return isElementPresent(new Locator(key, value));
	}

	public static boolean isElementPresent(Locator locator, String target1, String replacement1, String target2, String replacement2){

		String key = locator.key;
		String value = locator.value.replace(target1, replacement1);
		value = value.replace(target2, replacement2);
		return isElementPresent(new Locator(key, value));
	}


	public static WebElement locateElement(Locator locator, String target, String replacement){
		WebElement element = null;
		String key = locator.key;
		String value = locator.value.replace(target, replacement);
		element = locateElement(new Locator(key, value));
		return element;
	}


	public static WebElement locateElement(Locator locator, String target1, String replacement1, String target2, String replacement2){
		WebElement element = null;
		String key = locator.key;
		String value = locator.value.replace(target1, replacement1);
		value=value.replace(target2, replacement2);
		element = locateElement(new Locator(key, value));
		return element;
	}


	public static boolean isDisplayed(Locator locator, String target, String replacement){
		if(isElementPresent(locator, target, replacement))
			return locateElement(locator, target, replacement).isDisplayed();
		else
			return false;
	}


	public static boolean isDisplayed(Locator locator, String target1, String replacement1, String target2, String replacement2){
		if(isElementPresent(locator, target1, replacement1,  target2,  replacement2))
			return locateElement(locator, target1, replacement1, target2, replacement2).isDisplayed();
		else
			return false;
	}

	public static boolean isEnabled(Locator locator, String target, String replacement){
		return locateElement(locator, target, replacement).isEnabled();
	}

	public static boolean isSelected(Locator locator, String target, String replacement){
		return locateElement(locator, target, replacement).isSelected();
	}

	public static void clickOn(Locator locator, String target, String replacement){
		locateElement(locator, target, replacement).click();
	}

	public static void clickOn(Locator locator, String target1, String replacement1,String target2, String replacement2){
		locateElement(locator, target1, replacement1, target2, replacement2).click();
	}

	public static void typeIn(Locator locator, String target, String replacement, String text){
		WebElement element = locateElement(locator, target, replacement);

		if(driver instanceof InternetExplorerDriver){
			element.sendKeys(text);
		} else {
			element.clear();
			element.sendKeys(text);
		}
	}


	public static void selectCheckBox(Locator locator, String target, String replacement){
		WebElement element = locateElement(locator, target, replacement);
		if(!element.isSelected())
			element.click();
	}


	public static void deSelectCheckBox(Locator locator, String target, String replacement){
		WebElement element = locateElement(locator, target, replacement);
		if(element.isSelected())
			element.click();
	}

	public static String getTextOf(Locator locator, String target, String replacement){
		return locateElement(locator, target, replacement).getText();
	}

	public static String getHiddenTextOf(Locator locator, String target, String replacement){
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", 
				locateElement(locator, target, replacement));
	}

	public static String getHiddenTextOf(Locator locator){
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", 
				locateElement(locator));
	}


	public static String getHiddenTextOf(WebElement element){
		return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML;", element);
	}


	public static String getAttributeValueOf(Locator locator, String target, String replacement, String attributeName){
		return locateElement(locator, target, replacement).getAttribute(attributeName);
	}


	public static void  waitForVisibleElement(Locator locator){

		int timeOutInSeconds = 60;

		if(driver instanceof InternetExplorerDriver){
			int secs = 0;
			while(secs<=timeOutInSeconds){
				snooze(1000);
				secs++;
				if(isDisplayed(locator))
					break;
				else
					continue;
			}
		} else {
			Wait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(locateElement(locator)));
		}
	}


	public static void  waitForVisibleElement(Locator locator, int timeOutInSeconds){
		if(driver instanceof InternetExplorerDriver){
			int secs = 0;
			while(secs<=timeOutInSeconds){
				snooze(1000);
				secs++;
				if(isDisplayed(locator))
					break;
				else
					continue;
			}
		} else {
			Wait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(locateElement(locator)));
		}
	}

	public static void waitForVisibleElement(Locator locator, String target, String replacement){

		int timeOutInSeconds = 30;
		if(driver instanceof InternetExplorerDriver){
			int secs = 0;
			while(secs<=timeOutInSeconds){
				snooze(1000);
				secs++;
				if(isDisplayed(locator,target, replacement))
					break;
				else
					continue;
			}
		} else {
			Wait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(locateElement(locator, target, replacement)));
		}
	}


	public static void waitForVisibleElement(Locator locator, String target, String replacement, int timeOutInSeconds){

		if(driver instanceof InternetExplorerDriver){
			int secs = 0;
			while(secs<=timeOutInSeconds){
				snooze(1000);
				secs++;
				if(isDisplayed(locator,target, replacement))
					break;
				else
					continue;
			}
		} else {
			Wait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions.visibilityOf(locateElement(locator, target, replacement)));
		}
	}


	public static String getTextOf(Locator locator, String target1, String replacement1, String target2, String replacement2){
		return locateElement(locator, target1, replacement1, target2, replacement2).getText();

	}


	//********************************************For Regular Expressions***********************************************


	public static int getIndex(String string, String regex){

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		int i=0;

		while(m.find()){	
			i = m.start();
			break;  // because I wanted only first occurrence. if we want the last occurrence remove break  
		}
		return i ;
	}

	public static int getEndIndex(String string, String regex){

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(string);
		int i=0;

		while(m.find()){	
			i = m.end();
			break;  // because I wanted only first occurrence. if we want the last occurrence remove break  
		}
		return i ;
	}


	//***************************************************	Regular expressions **********************************
	//	Regular expression to match everything after a word = (?<=word).*
	//	Regular expression to match everything before a word = ^(.+?)word


	public static void switchToNewBrowserTab(){
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		int tabCount = tabs.size();
		--tabCount;
		if(tabCount>0){
			driver.switchTo().window(tabs.get(tabCount));	
			snooze(4000);
		} else
			Report.updateTestStepLog("Switching to other tab", "There is no other window opened", "Warning");
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

	public static void printMethodName(String message){
		Report.updateTestStepLog("<b><font color = \"blue\">Method Name</font></b>", "<b><font color = \"blue\">"+message+"</font></b>", "Information");
	}





}//EOC
