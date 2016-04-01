package qa.stepdef;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitWebElement;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import qa.SeleniumUtils.ObjectHelper;
import qa.config.Config;
import qa.pages.PageObject;
import qa.config.*;
import qa.CucumberUtils.Reporter;
import qa.SeleniumUtils.*;

public class Test_StepDef {

	PageObject pageObj;
	DriverUtils driveUtils;
	
	public Test_StepDef(DriverUtils driveUtils, PageObject pageObj) {
		this.pageObj = pageObj;
		this.driveUtils = driveUtils;
	}
	
	@Given("^User can open github site and do a search$")
	public void user_can_log_into_the_application_as_expected(){
		WebDriver driver = driveUtils.getDriver();
		driver.get(Config.appUrl);
		Reporter.writeText("Getting to site: "+ Config.appUrl);
	}

	@Then("^search result can be returned$")
	public void user_can_see_his_name_in_the_top_right_corner(){
		WebDriver driver = driveUtils.getDriver();
		WebElement e=SeleniumCommon.waitUntilClickableThenClick(driver, pageObj.searchBox);
		Method[] ms= e.getClass().getMethods();
		for(Method m: ms){
			if(m.getParameterCount()==0) {
				System.out.println(m.toString() +"--->");
				try {
					m.invoke(e, null);
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalArgumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvocationTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
		e.sendKeys("cucumber");
		e.sendKeys(Keys.ENTER);
		SeleniumCommon.sleepInHalfSec(4);
		Assert.assertTrue(pageObj.getResultInfo().startsWith("We’ve found"));
		Reporter.takeScreenShot();
	}
}

//public class Test_StepDef {
//	ObjectHelper helper;
//
//	public Test_StepDef(ObjectHelper helper) {
//		this.helper = helper;
//	}
//
//	@Given("^User can open github site and do a search$")
//	public void user_can_log_into_the_application_as_expected() {
//		WebDriver driver = helper.getTheDriver();
//		driver.get(Config.appUrl);
//
//		Reporter.writeTextToReport("Getting to site: " + Config.appUrl);
//
//	}
//
//	@Then("^search result can be returned$")
//	public void user_can_see_his_name_in_the_top_right_corner() {
//		WebDriver driver = helper.getTheDriver();
//		PageObject pageObj = helper.getPageObj(driver);
//		WebElement e = SeleniumCommon.waitUntilClickableThenClick(driver, pageObj.searchBox);
//		e.sendKeys("cucumber");
//		e.sendKeys(Keys.ENTER);
//		SeleniumCommon.sleepInHalfSec(4);
//		Assert.assertTrue(pageObj.getResultInfo().startsWith("We’ve found"));
//		Reporter.takeScreenShot();
//	}
//}
