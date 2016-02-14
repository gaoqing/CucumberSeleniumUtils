package qa.CucumberUtils;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		glue={"classpath:qa.stepdef"},
		plugin={"pretty", "html:target/cucumber"},
		dryRun=false,
		features={
				"/CucumberSeleniumUtils/resources/features/test.feature",
//				"classpath:feature2"		
		})


public class CucumberRunner extends AbstractTestNGCucumberTests{

}
