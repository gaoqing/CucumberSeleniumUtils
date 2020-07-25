package qa.SeleniumUtils;


import org.openqa.selenium.WebDriver;
import qa.pages.PageObject;

public class ObjectHelper {

    WebDriver driver;
    PageObject pageObj;

    public WebDriver getTheDriver() {
        if (driver == null) {
            driver = new DriverUtils().getDriver();
        }
        return driver;
    }

    public PageObject getPageObj(WebDriver driverParm) {
        if (pageObj == null) {
            pageObj = new PageObject(driverParm).getPageObj();
        }
        return pageObj;
    }
}
