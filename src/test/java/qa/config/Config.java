package qa.config;

/**
 * need to change the paths below to fit for your environment in order to start up a demo test for look and feel purpose
 * of course all of those settings can be moved into a property file and alike, here using plain static java class for quick demo only.
 */

public class Config {
    public static String testAppUrl = "https://github.com/";
    public static String runWithBrowser = "chrome";

    public static boolean shareBrowserSession = false;

    // paths to the testing browsers
    public static String localChromePath = "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
    public static String localFirefoxPath = "C:\\Program Files\\firefox\\20.0\\firefox.exe";

    // paths to the selenium drivers
    public static String localChromeDriverPath = "D:\\dev\\tool\\chromedriver.exe";
    public static String localIEDriverPath = "D:\\dev\\tool\\IEDriverServer.exe";

    // use when running tests in a remote selenium grid testing environment
    public static String remoteSeleniumServerHub = "http://10.28.10.1/wd/hub";
    public static String remoteChromePathInNodeHost = "D:\\Google\\Chrome\\Application\\chrome.exe";

    // specify whether to run tests in your local Dev PC or a remote selenium grid environment
    public static String runInVenue = "local";
    // public static String runInVenue = "remote";
}
