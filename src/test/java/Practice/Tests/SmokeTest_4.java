package Practice.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest_4 {

	@Test
	public void testCase1()
	{
	
		WebDriver driver = new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Random random=new Random();
		String emailAdd = "shubham"+random.nextInt(0,2999)+"@gmail.com";
		String password = "shubham157@"+random.nextInt(0,2999)+"#"+random.nextInt(0,2999);
	
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@id='main-nav']//a[text()='My Account']")).isDisplayed(), "The Option My Account is not visible in the navigation menu");
		driver.findElement(By.xpath("//ul[@id='main-nav']//a[text()='My Account']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Register')]")).isDisplayed(), "The Register Text is not Displayed");
		
		driver.findElement(By.xpath("//input[@name='email']")).sendKeys(emailAdd);
		
		driver.findElement(By.xpath("//input[@id='reg_password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sign out']")).isDisplayed(), "The Sign out option is not present");
		
		driver.findElement(By.xpath("//a[text()='Sign out']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Login')]")).isDisplayed(), "The Register Text is not Displayed");
		
		
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys(emailAdd);
		
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
		
		driver.findElement(By.xpath("//input[@name='login']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Sign out']")).isDisplayed(), "The Sign out option is not present");
		
		
		driver.quit();
	}
	
	
	@Test
	public void testCase2() {

		WebDriver driver = new ChromeDriver();
		driver.get("http://practice.automationtesting.in/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		Assert.assertTrue(driver.findElement(By.xpath("//ul[@id='main-nav']//a[text()='Shop']")).isDisplayed(),
				"The Option Shop is not visible in the navigation menu");
		driver.findElement(By.xpath("//ul[@id='main-nav']//a[text()='Shop']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//button[@type='submit' and text()='Filter']")).isDisplayed(),
				"The Filter Button is not Displayed");

		Actions move = new Actions(driver);
		move.clickAndHold(driver.findElement(By.xpath("(//span[contains(@class,'ui-state-default')])[1]")))
				.moveByOffset(100, 0).release().build().perform();
		driver.findElement(By.xpath("//button[@type='submit' and text()='Filter']")).click();

		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");

		List<WebElement> filteredElements = driver
				.findElements(By.xpath("//span[contains(@class,'woocommerce-Price-amount') and not(parent::del)]"));
		List<Integer> prices = new ArrayList<Integer>();
		for (WebElement price : filteredElements) {
			String rawPrice = price.getText().replaceAll("[^\\d.]", "");
			double pricewithoutDecimal = Double.parseDouble(rawPrice);
			int intPrice = (int) pricewithoutDecimal;
			prices.add(intPrice);

		}
		
		String fromValueRaw = driver.findElement(By.xpath("//span[@class='from']")).getText().replaceAll("[^\\d.]", "");

		String toValueRaw = driver.findElement(By.xpath("//span[@class='to']")).getText().replaceAll("[^\\d.]", "");

		double fromValueRawwithoutDecimal = Double.parseDouble(fromValueRaw);
		int fromValue = (int) fromValueRawwithoutDecimal;

		double toValueRawwithoutDecimal = Double.parseDouble(toValueRaw);
		int toValue = (int) toValueRawwithoutDecimal;

		for (int price : prices) {
			Assert.assertTrue((price >= fromValue) || (price <= toValue),
					"The Filtered product price " + price + " does not falls between " + fromValue + "-" + toValue);
		}
		
		WebElement dropdown = driver.findElement(By.name("orderby"));
		
		Select sel = new Select(dropdown);
		sel.selectByValue("price");
		
		
		List<WebElement> elementsAfterSort = driver
				.findElements(By.xpath("//span[contains(@class,'woocommerce-Price-amount') and not(parent::del)]"));
		List<Integer> sortedPrices = new ArrayList<Integer>();
		for (WebElement ele : elementsAfterSort) {
			String rawElements = ele.getText().replaceAll("[^\\d.]", "");
			double pricewithoutDecimal = Double.parseDouble(rawElements);
			int intPrice = (int) pricewithoutDecimal;
			sortedPrices.add(intPrice);

		}
		
		System.out.println("Sorted Elements are "+sortedPrices);
		  for (int i = 0; i < sortedPrices.size() - 1; i++) {
	            
	             Assert.assertFalse(sortedPrices.get(i) > sortedPrices.get(i + 1), "The Prices are not sorted in Ascending Order");
	            
	        }
		
		// driver.quit();
	}
	
	
	
}
