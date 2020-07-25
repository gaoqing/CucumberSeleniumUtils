package qa.SeleniumUtils;

import qa.config.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.service.DriverService;
import org.webbitserver.handler.PathMatchHandler;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverUtils {
    private WebDriver driver;
    private static WebDriver staticDriver;

    public WebDriver getDriver() {
        if (!Config.shareBrowserSession) {
            return getInstanceDriver();
        } else {
            return getStaticDriver();
        }
    }

    public WebDriver getInstanceDriver() {
        if (Config.runInVenue.equalsIgnoreCase("local")) {
            switch (Config.runWithBrowser) {
                case "firefox":
                    if (driver == null)
                        driver = getLocalFirefoxDriver();
                    break;
                case "ie":
                    if (driver == null)
                        driver = getLocalIEDriver();
                    break;
                case "chrome":
                default:
                    if (driver == null)
                        driver = getLocalChromeDriver();
                    break;
            }
        } else if (Config.runInVenue.equalsIgnoreCase("remote")) {
            switch (Config.runWithBrowser) {
                case "firefox":
                    if (driver == null)
                        driver = getRemoteFirefoxDriver();
                    break;
                case "ie":
                    if (driver == null)
                        driver = getRemoteIEDriver();
                    break;
                case "chrome":
                default:
                    if (driver == null)
                        driver = getRemoteChromeDriver();
                    break;
            }
        } else {
            throw new RuntimeException("This driver have not been supported yet");
        }

        return driver;
    }

    public WebDriver getStaticDriver() {
        if (Config.runInVenue.equalsIgnoreCase("local")) {
            switch (Config.runWithBrowser) {
                case "firefox":
                    if (staticDriver == null)
                        staticDriver = getLocalFirefoxDriver();
                    break;
                case "ie":
                    if (staticDriver == null)
                        staticDriver = getLocalIEDriver();
                    break;
                case "chrome":
                default:
                    if (staticDriver == null)
                        staticDriver = getLocalChromeDriver();
                    break;
            }
        } else if (Config.runInVenue.equalsIgnoreCase("remote")) {
            switch (Config.runWithBrowser) {
                case "firefox":
                    if (staticDriver == null)
                        staticDriver = getRemoteFirefoxDriver();
                    break;
                case "ie":
                    if (staticDriver == null)
                        staticDriver = getRemoteIEDriver();
                    break;
                case "chrome":
                default:
                    if (staticDriver == null)
                        staticDriver = getRemoteChromeDriver();
                    break;
            }
        } else {
            throw new RuntimeException("This driver have not been supported yet");
        }

        return staticDriver;
    }

    public WebDriver getLocalChromeDriver() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, Config.localChromeDriverPath);
        WebDriver driver;
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--test-type --auth-server-whitelist=*example.com,*foobar.com,*baz");
        option.setBinary(Config.localChromePath);

//		DriverService service = new ChromeDriverService.Builder()
//				.usingDriverExecutable(new File(Config.localChromeDriverPath))
//				.usingAnyFreePort()
//				.build();
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, option);
        driver = new ChromeDriver(cap);
//		driver = new ChromeDriver((ChromeDriverService) service, cap);

        return driver;
    }

    public WebDriver getLocalIEDriver() {
        System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, Config.localIEDriverPath);
        WebDriver driver;
//		DriverService service = new InternetExplorerDriverService.Builder()
//				.usingDriverExecutable(new File(Config.localIEDriverPath))
//				.usingAnyFreePort()
//				.build();
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
        cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
//		cap.setCapability(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, Config.localIEDriverPath);

//		driver = new InternetExplorerDriver((InternetExplorerDriverService) service, cap);
        driver = new InternetExplorerDriver(cap);

        return driver;
    }

    public WebDriver getLocalFirefoxDriver() {
        WebDriver driver;
        FirefoxBinary firefoxBinary = new FirefoxBinary(new File(Config.localFirefoxPath));
        FirefoxProfile firefoxProfile = new FirefoxProfile(new File("path to local profile"));
        firefoxProfile.setEnableNativeEvents(true);
        firefoxProfile.setAcceptUntrustedCertificates(true);
        firefoxProfile.setAssumeUntrustedCertificateIssuer(true);

        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");

        driver = new FirefoxDriver(firefoxBinary, firefoxProfile, cap);

        return driver;
    }

    public WebDriver getLocalPhantomjs() {
        WebDriver driver;
        System.setProperty(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "path to phantomjs");
        DesiredCapabilities cap = DesiredCapabilities.phantomjs();
        cap.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, new String[]{"--ignore-ssl-errors=true", "--ssl-protocal=any"});
//		cap.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, "path to phantomjs");
        driver = new PhantomJSDriver(cap);
        driver.manage().window().setSize(new Dimension(1024, 768));

        return driver;
    }

    public WebDriver getRemoteChromeDriver() {
        System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "path to chrome driver in remote host");
        WebDriver driver = null;
        ChromeOptions option = new ChromeOptions();
        option.setBinary(Config.remoteChromePathInNodeHost);

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, option);
        try {
            driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub), cap);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return driver;
    }

    public WebDriver getRemoteIEDriver() {
        System.setProperty(InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY, "path to id in remote host");
        WebDriver driver = null;
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        try {
            driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub), cap);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return driver;
    }

    public WebDriver getRemoteFirefoxDriver() {
        WebDriver driver = null;
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        cap.setCapability(CapabilityType.BROWSER_NAME, "firefox");
        cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        try {
            driver = new RemoteWebDriver(new URL(Config.remoteSeleniumServerHub), cap);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return driver;
    }
}
