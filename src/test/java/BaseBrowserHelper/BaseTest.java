package BaseBrowserHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.FileHandler;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import CommonObjectClasses.PageObjectClass;
import PageObjects.LandPageClass;

public class BaseTest {

	public WebDriver driver;
	public String siteNameString;
	public LandPageClass landPageClass;
	public PageObjectClass pageObjectClass;
	
	public  WebDriver setup() throws Exception
	{
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File(System.getProperty("user.dir")+"/src/main/java/DataResources/Property.properties"));
		properties.load(fileInputStream);
		String browserName= System.getProperty("browser")!=null ? System.getProperty("browser") : properties.getProperty("browser") ;
		String modeName= System.getProperty("mode")!=null ? System.getProperty("mode") : properties.getProperty("mode") ;
		siteNameString = properties.getProperty("site");
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions chromeOptions = new ChromeOptions();
			if(modeName.equalsIgnoreCase("headless")) {
			    chromeOptions.addArguments("--headless");
			} else if(modeName.equalsIgnoreCase("start-maximized")) {
			    chromeOptions.addArguments("--start-maximized");
			}
			
			Map<String, String> prefsMap  = new HashMap<String, String>();
			String downloadPath = System.getProperty("user.dir") + "/Downloads";
			prefsMap.put("download.default_directory", downloadPath);
			chromeOptions.setExperimentalOption("prefs", prefsMap);
			
			chromeOptions.setAcceptInsecureCerts(true);
			
			driver = new ChromeDriver(chromeOptions);
			implicitWait(driver);
			//driver.manage().window().setSize(new Dimension(1440, 900));
			
		} else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
			implicitWait(driver);
		}
		
		driver.manage().window().maximize();
		
		return driver;
	}
	
	private void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public String getScreenShot(String testcaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot tScreenshot = (TakesScreenshot) driver;
		File source = tScreenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "/Reports/"+testcaseName+".jpg");
		org.openqa.selenium.io.FileHandler.copy(source, dest);
		return dest.getAbsolutePath();
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() throws Exception
	{
		driver=setup();
		driver.get(siteNameString);
		landPageClass = new LandPageClass(driver);
	}
	
	@AfterMethod(alwaysRun = true)
	public void lastFunction()
	{
			driver.quit();
	}
	
}
