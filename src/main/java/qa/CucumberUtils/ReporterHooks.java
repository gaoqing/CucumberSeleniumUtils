package qa.CucumberUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import bsh.commands.dir;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import qa.SeleniumUtils.DriverUtils;
import qa.config.Config;

public class ReporterHooks {

	/*Have to use static here to keep the value being assigned to this field
	when calling constructor for driver and automatically calling setUp to get scenario instance
	*/
	
	static private Scenario scenario;
	static private WebDriver driver;
	private static boolean dunit = false;
	
    
	/*DI Picocontainor will be responsible for automatically creating instance for 
     * step definition related java class and class annotated by cucumber hooks, 
     * say current class for example*/

	/* The java class annotated by cucumber hook annotation can be automatically instaniated by 
	 * dependency DI Picocontainor, as long as it has no-arguments constructor or have arguments that 
	 * also can be instaniated by DI Picocontainor.
	 * In below constructor, the argument object DriverUtils can be instaniated by that DI as well*/
	public ReporterHooks(DriverUtils driverUtils) {
		driver = driverUtils.getDriver();
	}
	
	public void takeScreenShot() {
		byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		scenario.embed(screen, "image/png");
	}

	
	public void writeTextToReport(String text) {
		scenario.write(text);
	}

	@Before
	public void setUp(Scenario s) {
		scenario = s; 
		System.out.println("This setUp hook has been called by Cukes automatically to init scenario instance");
	}
	
	@Before
	public void allHooks(){
		if(!dunit){
			Runtime.getRuntime().addShutdownHook(new Thread(){
				public void run() {
					System.out.println("This is after all hook");
					if(driver!=null) driver.quit();
				}
			});
			System.out.println("This is before all");
			dunit = true;
		}
	}

	/*Can Apply tab to feature/scenario file, like After("@Tag1, @Tag2")  */
	@After
	public void tearDown() {
		if (scenario.isFailed()) {
			byte[] screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.embed(screen, "image/png");
		}
		if(!Config.shareBrowserSession){
			driver.quit();
		}
	}

}
