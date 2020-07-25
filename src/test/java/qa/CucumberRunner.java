package qa;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

// `mvn test` will trigger tests scanning under the package('qa'), which is specified in testng/testng.xml(refer to pom.xml line#164)
// after scanning, this class is found as a test runner, then it will execute with the cucumber tests configuration from annotation.

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"classpath:qa"},
        plugin = {"pretty", "html:target/cucumber"},
        dryRun = false,
        features = {
                "classpath:features/test.feature",
        })
public class CucumberRunner extends AbstractTestNGCucumberTests {

}

