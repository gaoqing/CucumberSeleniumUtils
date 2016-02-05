package qa.config;


public class Config {
	public static String appUrl="www.google.com";
	public static String runWithBrowser="chrome";
	
	public static String localChromePath ="C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe";
	public static String localFirefoxPath ="C:\\Program Files\\firefox\\20.0\\firefox.exe";
    
	public static String localChromeDriverPath="D:\\dev\\tool\\chromedriver.exe";
    public static String localIEDriverPath="D:\\dev\\tool\\IEDriverServer.exe";
 
    
    public static String runInVenue = "local";
//    public static String runInVenue = "remote";
    
    public static boolean shareBrowserSession = false;
    
    public static String remoteSeleniumServerHub = "http://10.28.10.1/wd/hub";
    public static String remoteChromePathInNodeHost ="D:\\Google\\Chrome\\Application\\chrome.exe";
   
	
    
}
