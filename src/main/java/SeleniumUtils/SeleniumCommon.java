package SeleniumUtils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumCommon {
	public static void sleepInHalfSec(int halfSec) {
		try {
			Thread.sleep(halfSec * 500);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void waitUntilClickableThenClick(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
				webElement.click();
				return;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static String waitUntilVisibleThenGetText(WebDriver driver, WebElement element) {
		String textString = null;
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
				textString = webElement.getText();
				return textString;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static void waitUntilClickableThenSentKeys(WebDriver driver, WebElement element, String textToSend) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
				webElement.clear();
				webElement.sendKeys(textToSend);
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static WebElement waitUtilVisible(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(element));
				webElement.getText(); // Using getText method to test if element
										// is stale or not
				return webElement;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	public static WebElement waitUtilClickable(WebDriver driver, WebElement element) {
		WebElement webElement = null;
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
		for (int i = 1; i < 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
				webElement.getText(); // Using getText method to test if element
										// is stale or not
				return webElement;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	// For example try to getText from element, if element not exist then still
	// fine to take empty string value
	public static String tryCatchWraper4Method(WebElement elementObj, String method) {
		String str = null;
		try {
			str = (String)elementObj.getClass().getMethod(method).invoke(elementObj);
			return str;
		} catch (Throwable t) {
			System.out.println("Element not exist or is stale");
			return "";
		}
	}

}
