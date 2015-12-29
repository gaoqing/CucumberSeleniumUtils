package qa.SeleniumUtils;

import java.lang.reflect.Method;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Function;


public class SeleniumCommon {
	public static void sleepInHalfSec(int halfSec) {
		try {
			Thread.sleep(halfSec * 500);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void waitUntilClickableThenClick(WebDriver driver, WebElement element) {
		WebElement webElement=null;
		for (int i = 1; i <= 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
				webElement.getText(); //Use getText method to test if such element is stale or not
				webElement.click();
				return;
			} catch (Throwable t) {
				if(webElement==null) break;
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Element state is unknown or cannot find such element");
	}

	public static String waitUntilVisibleThenGetText(WebDriver driver, WebElement element) {
		WebElement webElement=null;
		for (int i = 1; i <= 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
				String text = webElement.getText();
				return text;
			} catch (Throwable t) {
				if(webElement==null) break;
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Element state is unknown or cannot find such element");
	}

	public static void waitUntilClickableThenSentKeys(WebDriver driver, WebElement element, String textToSend) {
		WebElement webElement=null;
		for (int i = 1; i <= 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
				webElement.clear();
				webElement.sendKeys(textToSend);
				return;
			} catch (Throwable t) {
				if(webElement==null) break;
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Element state is unknown or cannot find such element");
	}

	public static WebElement waitUtilVisible(WebDriver driver, WebElement element) {
		WebElement webElement=null;
		for (int i = 1; i <= 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(element));
				webElement.getText(); // Use getText method to test if such element is stale or not
				return webElement;
			} catch (Throwable t) {
				if(webElement==null) break;
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Element state is unknown or cannot find such element");
	}

	public static WebElement waitUtilClickable(WebDriver driver, WebElement element) {
		WebElement webElement=null;
		for (int i = 1; i <= 6; i++) {
			try {
				webElement = new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(element));
				webElement.getText(); // Use getText method to test if such element is stale or not
				return webElement;
			} catch (Throwable t) {
				if(webElement==null) break;
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Element state is unknown or cannot find such element");
	}

	public static String waitUntilOneInListVisibleThenGetText(WebDriver driver, List<WebElement> elements, int... targetIndex) {
		WebElement targetElement = null;
		int size = elements.size();
		if (targetIndex.length == 0) {
			for (int j = 0; j < size; j++) {
				if (elements.get(j).isDisplayed()) {
					targetElement = elements.get(j);
					break;
				}
				if (j == size - 1) {
					System.out.println("No element is the list is visible");
					return null;
				}
			}
		} else {
			targetElement = elements.get(targetIndex[0]-1);  //Parameter index counting from 1
		}

		for (int i = 0; i < 6; i++) {
			try {
				targetElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(targetElement));
				String text = targetElement.getText();
				return text;
			} catch (Throwable t) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");

	}

	public static WebElement waitUntilOneInListVisibleThenSendKeys(WebDriver driver, List<WebElement> elements,
			String keysToSend, int... targetIndex) {
		WebElement targetElement = null;
		int size = elements.size();
		if (targetIndex.length == 0) {
			for (int j = 0; j < size; j++) {
				if (elements.get(j).isDisplayed()) {
					targetElement = elements.get(j);
					break;
				}
				if (j == size - 1) {
					System.out.println("No element is the list is visible");
					return null;
				}
			}
		} else {
			targetElement = elements.get(targetIndex[0]-1);
		}

		for (int i = 0; i < 6; i++) {
			try {
				targetElement = new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOf(targetElement));
				targetElement.clear();
				targetElement.sendKeys(keysToSend);
				return targetElement;
			} catch (Throwable tt) {
				System.out.println("Failed in attemption No. " + i);
				sleepInHalfSec(i * 2);
				continue;
			}
		}
		throw new RuntimeException("Have tried 5 times, but failed as element state is unknown");
	}

	
	public boolean waitAfterPageLoad(WebDriver driver) {
		new WebDriverWait(driver, 30).until(isPageLoaded());
		return true;
		
	}
	
	
	public static Function<WebDriver, Boolean> isPageLoaded(){
		return new Function<WebDriver, Boolean>() {

			public Boolean apply(WebDriver input) {
				return ((JavascriptExecutor)input)
						.executeScript("return document.readyState").equals("complete");
			}
		};
	}
	
	public static Object tryCatchWraper4NonPrimitive(Object classInstance, String method, Object... paraValueList) {
		Class<?> clazz = classInstance.getClass();
		Method methodToInvoke = null;
		int length = paraValueList.length;
		Class<?>[] parameterType = new Class<?>[length];
		
		try {
			if (length > 0) {
				for (int i = 0; i < length; i++) {
					parameterType[i] = paraValueList[i].getClass();
				}
				methodToInvoke = clazz.getDeclaredMethod(method, parameterType);
			} else {
				methodToInvoke = clazz.getDeclaredMethod(method);
			}
			return methodToInvoke.invoke(classInstance, paraValueList);
		} catch (Throwable t) {
			return "";
		}
	}

	public static Object tryCatchWrapper(Object classInstance, String method, Class<?>[] paraType, Object[] paraValue) {
		// if(paraType.length!=paraType.length){
		// System.out.println("Each values provided have to provide its Class
		// type as well");
		// }
		Class<?> clazz = classInstance.getClass();
		Method methodToInvoke = null;
		try {
			methodToInvoke = clazz.getDeclaredMethod(method, paraType);
			return methodToInvoke.invoke(classInstance, paraValue);
		} catch (Throwable t) {
			return "";
		}

	}

}
