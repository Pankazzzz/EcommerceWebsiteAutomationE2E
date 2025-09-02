package Practice.Tests;

import java.lang.annotation.Documented;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.DTD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest_1 {

	//@Test
	public void testCase1()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		driver.close();
	}
	
	//@Test
	public void testCase2()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		driver.close();
	}
	
	//@Test
	public void testCase3()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='View Basket']"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		jsExecutor.executeScript("window.scrollBy(0,500)");
		String description = driver.findElement(By.xpath("//h2[text()='Product Description']/following-sibling::p")).getText();
		System.out.println(description);
		driver.close();
	}
	
	//@Test
	public void testCase4()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='View Basket']"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		jsExecutor.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//a[normalize-space()='Reviews (0)']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h3[text()='Be the first to review “Selenium Ruby” ']"))));
		
		driver.findElement(By.cssSelector("a[class='star-5']")).click();
		Random random = new Random();
		String reviewString =  "Demo"+random.nextInt();
		driver.findElement(By.id("comment")).sendKeys(reviewString);
		driver.findElement(By.name("author")).sendKeys("demo");
		driver.findElement(By.name("email")).sendKeys("Demo@email.com");
		driver.findElement(By.id("wp-comment-cookies-consent")).click();
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		
		WebElement reviewElement = driver.findElement(By.cssSelector("#comments"));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", reviewElement);

		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='comments']"))));
		List<WebElement>  descriptionTextElements = driver.findElements(By.xpath("//div[@class='comment-text']/div[@itemprop='description']/p"));
		List<WebElement> descText = descriptionTextElements.stream().filter(e -> e.getText().equals(reviewString)).collect(Collectors.toList());
		Assert.assertEquals(descText.getFirst().getText(), reviewString, "Review not matching");
		
		driver.close();
	}
	
	//@Test
	public void testCase5() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='View Basket']"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		driver.findElement(By.xpath("//a[normalize-space()='View Basket']")).click();

		driver.findElement(By.xpath("//*[@name='coupon_code' and @id='coupon_code']")).sendKeys("krishnasakinala");
		driver.findElement(By.name("apply_coupon")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText(), "Coupon code applied successfully.");

		driver.findElement(By.cssSelector("[title='Qty']")).clear();
		driver.findElement(By.cssSelector("[title='Qty']")).sendKeys("10");
		driver.findElement(By.cssSelector("[title='Qty']")).sendKeys(Keys.TAB);
		driver.findElement(By.name("update_cart")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		Thread.sleep(2000);
		Assert.assertFalse(driver.findElement(By.name("update_cart")).isEnabled());
		
		String amountString = driver.findElement(By.xpath("//td[@data-title='Subtotal']//span[@class='woocommerce-Price-amount amount']")).getText();
		String taxString = driver.findElement(By.xpath("//td[@data-title='Tax']//span[@class='woocommerce-Price-amount amount']")).getText();
		String totalString = driver.findElement(By.xpath("(//td[@data-title='Total']//span[@class='woocommerce-Price-amount amount'])[2]")).getText();


		 
		 int amount = Integer.parseInt(amountString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 int tax = Integer.parseInt(taxString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 int total = Integer.parseInt(totalString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 
		 System.out.println(total+" "+amount+" "+tax);
		 
		 Assert.assertEquals((amount+tax)-50, total, "Total not matching");
		 Actions actions = new Actions(driver);
		 actions.moveToElement( driver.findElement(By.xpath("//a[contains(normalize-space(),'Checkout')]"))).build().perform();
		 driver.findElement(By.xpath("//a[contains(normalize-space(),'Checkout')]")).click();
		
		 
		driver.close();
	}
	
	
	//@Test
	public void testCase6() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='View Basket']"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		driver.findElement(By.xpath("//a[normalize-space()='View Basket']")).click();

		driver.findElement(By.cssSelector(".remove")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText().contains("Selenium Ruby removed."));
		driver.findElement(By.xpath("//a[text()='Undo?']")).click();
		 
		driver.close();
	}
	
	@Test
	public void testCase7() throws InterruptedException
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://practice.automationtesting.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
		driver.findElement(By.xpath("//a[contains(text(),'Shop')]")).click();
		driver.findElement(By.cssSelector("[alt='Automation Practice Site']")).click();
		JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
		jsExecutor.executeScript("window.scrollBy(0,500)");
		List<WebElement> products =driver.findElements(By.className("products"));
		Assert.assertEquals(products.size(), 3, "Product Size Not Matching");
		

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(products.get(0)));
		products.get(0).click();
		WebElement element = driver.findElement(By.xpath("//button[contains(text(),'Add to basket')]"));
		wait.until(ExpectedConditions.visibilityOf(element)).click();
		wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[normalize-space()='View Basket']"))));
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		driver.findElement(By.xpath("//a[normalize-space()='View Basket']")).click();

		driver.findElement(By.xpath("//*[@name='coupon_code' and @id='coupon_code']")).sendKeys("krishnasakinala");
		driver.findElement(By.name("apply_coupon")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText(), "Coupon code applied successfully.");

		driver.findElement(By.cssSelector("[title='Qty']")).clear();
		driver.findElement(By.cssSelector("[title='Qty']")).sendKeys("10");
		driver.findElement(By.cssSelector("[title='Qty']")).sendKeys(Keys.TAB);
		driver.findElement(By.name("update_cart")).click();
		System.out.println(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText());
		Thread.sleep(2000);
		Assert.assertFalse(driver.findElement(By.name("update_cart")).isEnabled());
		
		String amountString = driver.findElement(By.xpath("//td[@data-title='Subtotal']//span[@class='woocommerce-Price-amount amount']")).getText();
		String taxString = driver.findElement(By.xpath("//td[@data-title='Tax']//span[@class='woocommerce-Price-amount amount']")).getText();
		String totalString = driver.findElement(By.xpath("(//td[@data-title='Total']//span[@class='woocommerce-Price-amount amount'])[2]")).getText();


		 
		 int amount = Integer.parseInt(amountString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 int tax = Integer.parseInt(taxString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 int total = Integer.parseInt(totalString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		 
		 System.out.println(total+" "+amount+" "+tax);
		 
		 Assert.assertEquals((amount+tax)-50, total, "Total not matching");
		 Actions actions = new Actions(driver);
		 actions.moveToElement( driver.findElement(By.xpath("//a[contains(normalize-space(),'Checkout')]"))).build().perform();
			jsExecutor.executeScript("window.scrollBy(0,500)");
		 driver.findElement(By.xpath("//a[contains(normalize-space(),'Checkout')]")).click();
		 
		 Random random = new Random();
	
		 Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Billing Details']")).isDisplayed(),"Elements not displayed");
		 driver.findElement(By.name("billing_first_name")).sendKeys("DemoFirstName"+random.nextInt());
		 driver.findElement(By.id("billing_last_name")).sendKeys("DemoLastName");
		 driver.findElement(By.id("billing_email")).sendKeys("Demo"+random.nextInt()+"@demo.com");
			jsExecutor.executeScript("window.scrollBy(0,200)");

		 
		 actions.moveToElement(driver.findElement(By.cssSelector(".select2-chosen"))).build().perform();
		 wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".select2-chosen"))));
		 driver.findElement(By.cssSelector(".select2-chosen")).click();
		 driver.findElement(By.xpath("//label[text()='Country *']/following-sibling::input[@role='combobox']")).sendKeys("Afghanistan");
		 driver.findElement(By.xpath("//label[text()='Country *']/following-sibling::input[@role='combobox']")).sendKeys(Keys.ENTER);
		 
		 
		 driver.findElement(By.id("billing_phone")).sendKeys("9022002299");
		 driver.findElement(By.id("billing_address_1")).sendKeys("demoGanj");
		 driver.findElement(By.id("billing_city")).sendKeys("Thane");
		 driver.findElement(By.id("billing_postcode")).sendKeys("401105");

		 WebElement cod = driver.findElement(By.id("payment_method_cod"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cod);

		 //driver.findElement(By.xpath("//label[normalize-space()='Cash on Delivery']/preceding-sibling::input")).click();
//		 jsExecutor.executeScript("window.scrollBy(0,300)");
//		 driver.findElement(By.id("place_order")).click();
		 
		 Thread.sleep(2000);
		 
		 WebElement cod1 = driver.findElement(By.id("place_order"));
		 ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cod1);

		 
		 String successMessageString = driver.findElement(By.xpath("//*[@class='woocommerce-thankyou-order-received']")).getText();
		 Assert.assertEquals(successMessageString, "Thank you. Your order has been received.");
		 
		 String orderNumberString = driver.findElement(By.cssSelector("li[class*='order'] strong")).getText();
		 String dateString = driver.findElement(By.cssSelector("li[class*='date'] strong")).getText();
		 String totalString2 = driver.findElement(By.cssSelector("li[class*='total'] strong")).getText();
		 String methodString = driver.findElement(By.cssSelector("li[class*='method'] strong")).getText();

		 System.out.println("Order Number: " + orderNumberString);
		 System.out.println("Order Date: " + dateString);
		 System.out.println("Total Amount: " + totalString2);
		 System.out.println("Payment Method: " + methodString);
		 
		 //WebElement cod = driver.findElement(By.id("payment_method_cod"));
		 //((JavascriptExecutor) driver).executeScript("arguments[0].click();", cod);

		 driver.close();
		 
		 
	}
}
