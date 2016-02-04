package qa.SeleniumUtils;

import qa.CucumberUtils.ReporterHooks;
import qa.config.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtils {
	WebDriver driver;
	public WebDriver getDriver() {
		
		if (Config.runInVenue.equalsIgnoreCase("local")) {
			switch (Config.runWithBrowser) {
			case "firefox":
				if(driver ==null) driver = getLocalFirefoxDriver();
				break;
			case "ie":
				if(driver ==null) driver = getLocalIEDriver();
				break; 
			case "chrome":
			default:
				if(driver ==null) driver = getLocalChromeDriver();
				break;
			}
		} else if (Config.runInVenue.equalsIgnoreCase("remote")) {
			switch (Config.runWithBrowser) {
			case "firefox":
				if(driver ==null) driver = getRemoteFirefoxDriver();
				break;
			case "ie":
				if(driver ==null) driver = getRemoteIEDriver();
				break; 
			case "chrome":
			default:
				if(driver ==null) driver = getRemoteChromeDriver();
				break;
			}
		} else {
			new Throwable("This driver have not been supported yet");
		}
		
		// Pass the same driver instance for screen capture and tear down.
		new ReporterHooks(driver); 
		
		return driver;
	}

	public WebDriver getLocalChromeDriver() {
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--test-type");
		option.setBinary(Config.localChromePath);

		DriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(Config.localChromeDriverPath))
				.usingAnyFreePort()
				.build();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, option);
		ChromeDriver driver = new ChromeDriver((ChromeDriverService) service, cap);
		return driver;

	}

	public WebDriver getLocalIEDriver() {
		DriverService service = new InternetExplorerDriverService.Builder()
				.usingDriverExecutable(new File(Config.localIEDriverPath))
				.usingAnyFreePort()
				.build();
		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		driver = new InternetExplorerDriver((InternetExplorerDriverService) service,cap);
		return driver;
	}
	
	public WebDriver getLocalFirefoxDriver() {
		FirefoxBinary firefoxBinary = new FirefoxBinary(new File(Config.localFirefoxPath));
		FirefoxProfile firefoxProfile = new FirefoxProfile(new File("path to local profile"));

		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		driver = new FirefoxDriver(firefoxBinary,firefoxProfile,cap);
		
		return driver;
	}
	
	public WebDriver getRemoteChromeDriver() {
		ChromeOptions option = new ChromeOptions();
		option.setBinary(Config.remoteChromePathInNodeHost);

		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(ChromeOptions.CAPABILITY, option);
		try {
			driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
	
	public WebDriver getRemoteIEDriver() {

		DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
		cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		try {
			driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
	
	public WebDriver getRemoteFirefoxDriver() {

		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
		try {
			driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
