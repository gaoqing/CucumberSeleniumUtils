package Test.CucumberUtils;



import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ReporterHooks {

	/*Have to use static here to keep the value being assigned 
	when calling constructor for driver or calling setUp to get scenario instance
	*/
	
	static private Scenario scenario;
	static private WebDriver driver;

	public ReporterHooks() {
	}

	public ReporterHooks(WebDriver driverParm) {
		driver = driverParm;
	}
	
	public void takeScreenShot() {
		byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screen, "image/png");
	}

	
	public void writeTextToReport(String text) {
		scenario.write(text);
	}

	@Before
	public void setUp(Scenario scenarioParm) {
		scenario = scenarioParm; 
		System.out.println("This setUp hook will be called by Cukes automatically to init scenario instance");
	}

	/*Can Apply tab to feature file */
	@After("@Tag1, @Tag2") 
	public void tearDown() {
		if (scenario.isFailed()) {
			byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screen, "image/png");
		}
	}

}
