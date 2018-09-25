
package frameworklibrary;
import frameworklibrary.Config;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;


/**
 * @author 609684083
 *
 */
public class WebDriverFactory {
	private static WebDriver driver = null;
	public static FirefoxProfile prof = initiator.Initiator.ffPrf();


	public static void createWebDriverObject(String browserName) throws MalformedURLException {

		System.setProperty("webdriver.gecko.driver", Config.ffPath);

		if (browserName.equalsIgnoreCase("FireFox") || browserName.equalsIgnoreCase("FF")) {
			driver = new FirefoxDriver(prof);

		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", Config.iePath);

			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setBrowserName("internet explorer");
			ieCapabilities.setVersion("11");
			ieCapabilities.setCapability("nativeEvents", false);    
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			driver = new InternetExplorerDriver(ieCapabilities);

		} else if (browserName.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", Config.chromePath);
			driver = new ChromeDriver();
		} else {
			System.out.println("Invalid Browser option has been Entered");
		}

		driver.manage().window().maximize();
		if (driver != null) {
			driver.manage().timeouts().implicitlyWait(Config.waitTimeInSeconds, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(Config.waitTimeInSeconds, TimeUnit.SECONDS);
			driver.manage().timeouts().setScriptTimeout(Config.waitTimeInSeconds, TimeUnit.SECONDS);
		}
	}

	public static WebDriver getWebDriverObject() {
		return driver;
	}

	public static void destroyWebDriverObject() {

		if (driver instanceof FirefoxDriver) {
			try {
				Runtime.getRuntime().exec("taskkill /F /IM plugin-container.exe");
			}
			catch (IOException var0) {
				var0.printStackTrace();
			}
		} else if(driver instanceof ChromeDriver){
			try {
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			}
			catch (IOException var0) {
				var0.printStackTrace();
			}
		}
		
		
		
		if (Config.closeBrowserAfterExecution.equalsIgnoreCase("Yes")) {
			System.out.println("Driver Close Quit - Start");
 
			driver.quit();
			driver = null;
			System.out.println("Driver Close Quit - End");
		}

	}
}