package Practice.Tests;

import java.awt.TextArea;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest_2 {
	
		//@Test
		public void testCase1()
		{
			WebDriver driver = new ChromeDriver();
			driver.get("https://practice.automationtesting.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
			driver.findElement(By.linkText("My Account")).click();
			Random random=new Random();
			String emailString = "demo"+random.nextInt()+"@demo.com";
			driver.findElement(By.id("reg_email")).sendKeys(emailString);
			driver.findElement(By.id("reg_password")).sendKeys("demoA123@ad5!");
			driver.findElement(By.id("reg_password")).sendKeys(Keys.TAB);
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//input[@value='Register']"))));
			driver.findElement(By.xpath("//input[@value='Register']")).click();
			String registeredMessage=driver.findElement(By.xpath("//div[@class='woocommerce-MyAccount-content']/p[1]")).getText();
			System.out.println(registeredMessage);
			Assert.assertTrue(registeredMessage.contains(emailString.split("\\@")[0]),"Account not registred");
			driver.close();
		}
		
		//@Test
		public void testCase2()
		{
			WebDriver driver = new ChromeDriver();
			driver.get("https://practice.automationtesting.in/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
			driver.findElement(By.linkText("My Account")).click();
			Random random=new Random();
			String emailString = "demo"+random.nextInt()+"@demo.com";
			driver.findElement(By.id("username")).sendKeys(emailString);
			driver.findElement(By.id("password")).sendKeys("demoA123@ad5!");
			driver.findElement(By.id("password")).sendKeys(Keys.TAB);
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			
			String errorText = driver.findElement(By.cssSelector("[class='woocommerce-error'] li")).getText();
			Assert.assertTrue(errorText.contains("Error"),"Login Error");
			
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			errorText = driver.findElement(By.cssSelector("[class='woocommerce-error'] li")).getText();
			Assert.assertTrue(errorText.contains("Password is required."),"Login Error");
			
			driver.findElement(By.id("username")).clear();
			driver.findElement(By.id("password")).sendKeys("demoA123@ad5!");
			driver.findElement(By.xpath("//input[@value='Login']")).click();
			errorText = driver.findElement(By.cssSelector("[class='woocommerce-error'] li")).getText();
			Assert.assertTrue(errorText.contains("Username is required."),"Login Error");
			
			
			
			driver.close();
		}
		
		
		@Test
		public void testCase3()
		{
			//Ketan Branch
			WebDriver driver = new ChromeDriver();
			driver.get("http://practice.automationtesting.in/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			driver.findElement(By.xpath("//li[@id='menu-item-50']/a[contains(text(),'Account')]")).click();
			driver.findElement(By.id("username")).sendKeys("ketan@ketan.com");
			driver.findElement(By.name("password")).sendKeys("Ketan@9022!");
			driver.findElement(By.cssSelector("input[value='Login']")).click();
			boolean signOut = driver.findElement(By.xpath("//a[normalize-space()='Sign out']")).isDisplayed();
			Assert.assertTrue(signOut,"Sign out not visible");
			driver.findElement(By.xpath("//a[normalize-space()='Sign out']")).click();
			driver.navigate().back(); 
			
			driver.findElement(By.xpath("//h2[text()='Login']")).isDisplayed();
			driver.close();
		
		}


}
