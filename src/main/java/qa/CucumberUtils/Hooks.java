package qa.CucumberUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import qa.SeleniumUtils.DriverUtils;
import qa.config.Config;

public class Hooks {

	/*Have to use static here to keep the value being assigned to this field
	when calling constructor for driver and automatically calling setUp to get scenario instance
	*/
	
	private static Scenario scenario;
	private static WebDriver driver;
	private static boolean whenFirstScenario = true;
	
	/*DI Picocontainor will be responsible for automatically creating instance for 
     * step definition related java class and class annotated by cucumber hooks, 
     * say current class for example*/

	/* The java class annotated by cucumber hook annotation can be automatically instantiated by 
	 * dependency DI Picocontainor, as long as it has no-arguments constructor or have arguments that 
	 * also can be instantiated by DI Picocontainor.
	 * In below constructor, the argument object DriverUtils can be instantiated by that DI as well
	 * */
	public Hooks(DriverUtils driverUtils) {
		driver = driverUtils.getDriver();
	}


	@Before
	public void setUp(Scenario s) {
		scenario = s; 
		System.out.println("This setUp hook is called by Cukes automatically"
				+ " to init scenario instance");
		
	}
	
	/* @Before hook will be called by every scenario, addShutdownHook() method only call
	 * Before the first scenario and then flag whenFirstScenario set false.
	 * The new Thread will be called automatically by Runtime before current runtime shutdown.
	 * */
	@Before
	public void allHooks(){
		if(whenFirstScenario){
			Runtime.getRuntime().addShutdownHook(new Thread(){
				public void run() {
					System.out.println("This after-all hook executed right before JVM shutdown");
					if(driver!=null) driver.quit();
				}
			});
			System.out.println("Here can write code for before-all hook"
					+ "(actually before the first scenario)");
			whenFirstScenario = false;
		}
	}

	/* Can Apply tab to feature/scenario file, like After("@Tag1, @Tag2")  */
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
	
	public static Scenario getScenario() {
		return scenario;
	}
	
	public static WebDriver getDriver() {
		return driver;
		
	}

}
