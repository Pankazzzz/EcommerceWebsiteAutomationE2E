package Practice.Tests;

import static org.testng.Assert.fail;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SmokeTest_6 {
	
	//@Test
	public void test1() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

				
			WebElement widgetElement = driver.findElement(By.linkText("More"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Dynamic Data']")).click();
			driver.findElement(By.xpath("//button[@id='save' and text()='Get Dynamic Data']")).click();
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("[src*='gif']"))));
			//setTimeout(()=>{debugger;},5000)
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView('true');", driver.findElement(By.xpath("//div[@id='loading']")));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@id='loading']"))));
			System.out.println(driver.findElement(By.xpath("//div[@id='loading']")).getText());
	}
	
	//@Test
	public void test2() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				
			WebElement widgetElement = driver.findElement(By.linkText("More"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='JQuery ProgressBar']")).click();
			driver.findElement(By.id("downloadButton")).click();
			
			boolean flag=false;

			while(flag==false) {
			try {
			  flag = driver.findElement(By.cssSelector("[class=\"progress-label\"]")).getText().contains("Complete!") ? true : false;
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			}
			driver.findElement(By.xpath("//button[text()='Close']")).click();
	}

	
	//@Test
	public void test3() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				
			WebElement widgetElement = driver.findElement(By.linkText("More"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Loader']")).click();
			driver.findElement(By.id("loader")).click();
			
			wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//h1[text()='Please wait...']"))));
			System.out.println(driver.findElement(By.cssSelector("[class='modal-body'] p")).getText());
			driver.findElement(By.xpath("//*[text()='Close']")).click();
		
	}

	
	@Test
	public void test4() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

				
			WebElement widgetElement = driver.findElement(By.linkText("More"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='ProgressBar']")).click();
			
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView('true');", driver.findElement(By.xpath("(//div[@class=\"container\"])[3]")));
		
			
			driver.findElement(By.id("cricle-btn")).click();
			
			
	
			boolean flag=false;

			while(flag==false) {
			try {
			  flag = driver.findElement(By.cssSelector("[class='progressbar-text']")).getText().contains("100") ? true : false;
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			}
			driver.close();
	}
	
	



}
