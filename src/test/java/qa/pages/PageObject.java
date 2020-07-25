package qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import qa.SeleniumUtils.DriverUtils;
import qa.SeleniumUtils.SeleniumCommon;

public class PageObject {
    public WebDriver driver;
    public PageObject pageObj;

    @FindBy(css = ".site-search label input")
    public WebElement searchBox;

    @FindBy(css = "div[class=\"sort-bar\"] >h3")
    public WebElement resultInfo;

    public String getResultInfo() {
        return SeleniumCommon.waitUntilVisibleThenGetText(driver, resultInfo);
    }

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public PageObject(DriverUtils driverUtils) {
        this.driver = driverUtils.getDriver();
        PageFactory.initElements(this.driver, this);
    }

    public PageObject getPageObj() {
        if (pageObj == null) {
            pageObj = new PageObject(driver);
        }
        return pageObj;
    }
}
