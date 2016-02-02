package qa.SeleniumUtils;

import qa.config.*;

import java.io.File;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Driver {
	
	public WebDriver getDriver() {
		switch(Config.runWithBrowser){
		case "chrome":
		default:
			return getChromeDriver();
		case "ie":
			return getIEDriver();
		}
	}

	public WebDriver getChromeDriver(){
		ChromeOptions option= new ChromeOptions();
		option.addArguments("--test-type");
		option.setBinary(Config.pathToChrome);
		
		
		DriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File(Config.pathToChromeDriver))
				.usingAnyFreePort()
				.build();
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(ChromeOptions.CAPABILITY, option);
		ChromeDriver driver= new ChromeDriver((ChromeDriverService)service, cap);
		return driver;
		
	}
	
	public WebDriver getIEDriver(){
		DriverService service = new InternetExplorerDriverService.Builder()
				.usingAnyFreePort()
				.usingDriverExecutable(new File(Config.pathToIEDriver))
				.build();
		
		InternetExplorerDriver driver = new InternetExplorerDriver((InternetExplorerDriverService)service);
		return driver;
	}
}
