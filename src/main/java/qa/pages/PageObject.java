package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.SeleniumUtils.SeleniumCommon;

public class PageObject {
	
	public WebDriver driver;
	public PageObject pageObj;
	
	@FindBy(css=".btn.primary")
	public WebElement submitButton;
	
	@FindBy(css=".btn.secondary")
	public WebElement cancelButton;
	
	@FindBy(css="span[name=\"userName\"]")
	public WebElement userName;
	
	public String getUserName() {
		return SeleniumCommon.waitUntilVisibleThenGetText(driver, userName);	
	}
	
	public PageObject(WebDriver driver) {
		this.driver =driver;
		PageFactory.initElements(this.driver, this);
	}

	public PageObject getPageObj() {
		if(pageObj == null){
			pageObj = new PageObject(driver);
		}
		return pageObj;
	}
	
	
}
