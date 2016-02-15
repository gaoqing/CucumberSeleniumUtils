package qa.CucumberUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;

public class Reporter {
	public static Scenario scenario;
	public static WebDriver driver;
	
	
	public static void takeScreenShot() {
		scenario = Hooks.getScenario();
		driver = Hooks.getDriver();
		byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screen, "image/png");
	}
	
	public static void writeText(String text) {
		scenario = Hooks.getScenario();
		scenario.write(text);
	}

}
