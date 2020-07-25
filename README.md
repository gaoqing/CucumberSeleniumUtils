<h1 align="center">Selenium/Cucumber Test Utils </h1>

## Selenium/Cucumber Test Utils

This `CucumberSeleniumUtils` is pretty much a very simple toolkit with basic utils classes for starting up a `selenium` & `cucumber` test environment
for demonstration and reference purpose. As a starting point for beginner to start up and then continue evolving. 

This includes an end 2 end web GUI test demo by using [selenium](https://www.selenium.dev/) webdriver to drive a web browser to simulate user behaviours(like clicking/inputting/navigating etc).

The test case itself is managed and arranged in [cucumber](https://cucumber.io/) feature files, which is quite a BDD test framework(Given/When/Then phrases)


## Usage

Easy enough for beginner to start with, it incorporate the testing into maven `test` lifecycle, there is a `maven-surefire-plugin` setup in maven build. 

In surefile plugin, it config a test suite xml file to lookup test cases, inside which it specify a package to scan with. After scanning, a class(qa.CucumberRunner.java, which extends AbstractTestNGCucumberTests.java) is found as a test runner, then will execute with all the information available from its annotation.

To experience how this goes, only two easy steps to follow:

Step 1: check and change `qa.config.Config.java` to specify the correct location for your browser executable file and webdriver path etc

Step 2: run command: `mvn test` to go

After running, a pretty report will be auto generated under directory(target/surefire-reports/html/index.html)


## License
[MIT](https://github.com/gaoqing/CucumberSeleniumUtils/blob/master/LICENSE)