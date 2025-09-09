package Practice.Tests;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.checkerframework.checker.units.qual.radians;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmokeTest_3 {
	
	
	//@Test
	public void test1() throws InterruptedException
	{
	   WebDriver driver = new ChromeDriver();
	   driver.get("http://practice.automationtesting.in/");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   driver.manage().window().maximize();
	   driver.findElement(By.linkText("Shop")).click();
	   
	  WebElement leftSlider = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle ui-corner-all ui-state-default')])[1]"));
	  WebElement rightSlider = driver.findElement(By.xpath("(//span[contains(@class,'ui-slider-handle ui-corner-all ui-state-default')])[2]"));
	  
	  Actions actions = new Actions(driver);
	  
	  
	  while(!driver.findElement(By.cssSelector("[class='from']")).getText().contains("200")) {
	  actions.clickAndHold(leftSlider).moveByOffset(1, 0).release().perform();
	  }
	  
	  while(!driver.findElement(By.cssSelector("[class='to']")).getText().contains("450")) {
	  actions.clickAndHold(rightSlider).moveByOffset(-1, 0).release().perform();
	  }
	  
	  //JavascriptExecutor js = (JavascriptExecutor) driver;
	  //js.executeScript("arguments[0].setAttribute('value', 'cash_on_delivery')", element);
	  
	  Assert.assertEquals(driver.findElement(By.cssSelector("[class='from']")).getText().split("₹")[1], "200");
	  Assert.assertEquals(driver.findElement(By.cssSelector("[class='to']")).getText().split("₹")[1], "450");
	  driver.findElement(By.xpath("//button[text()='Filter']")).click();	  
	  
	 List<WebElement> price1 =  driver.findElements(By.xpath("//span[@class='price']/span[@class='woocommerce-Price-amount amount']"));
	 List<WebElement> price2 =  driver.findElements(By.xpath("//ins/span[@class='woocommerce-Price-amount amount']"));
	   
	 System.out.println(price1.getFirst().getText());
	 System.out.println(price1.getFirst().getText().split("\\.")[0].split("₹")[1]);
	 boolean value1 = price1.stream().allMatch(ele -> Integer.parseInt(ele.getText().split("\\.")[0].split("₹")[1]) <= 450 && Integer.parseInt(ele.getText().split("\\.")[0].split("₹")[1]) > 200);
	 boolean value2 = price2.stream().allMatch(ele -> Integer.parseInt(ele.getText().split("\\.")[0].split("₹")[1]) <= 450 && Integer.parseInt(ele.getText().split("\\.")[0].split("₹")[1]) > 200);
	 
	 /*
	   boolean value1 = price1.stream().allMatch(ele -> {
	  
	    int price = priceParser.apply(ele);
	    return price <= 450 && price > 200;
	});
	*/
	 driver.close();
	 
	}
	
	
	//@Test
	public void test2() throws InterruptedException
	{
	   WebDriver driver = new ChromeDriver();
	   driver.get("http://practice.automationtesting.in/");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   driver.manage().window().maximize();
	   driver.findElement(By.linkText("Shop")).click();
	   
	  List<WebElement>  productsElements =  driver.findElements(By.cssSelector("a h3"));
	  WebElement productElement = productsElements.stream().filter(ele ->  ele.getText().equalsIgnoreCase("Mastering JavaScript")).findFirst().orElse(null);
	  String productName =productElement.getText();
	  JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
	  jsExecutor.executeScript("arguments[0].scrollIntoView(true);", productElement);
	  productElement.click();
	  
	  Assert.assertTrue(driver.findElement(By.cssSelector("[class='product_title entry-title']")).getText().equalsIgnoreCase(productName));
	  driver.close();
	}
	
	//@Test
	public void test3() throws InterruptedException
	{
	   WebDriver driver = new ChromeDriver();
	   driver.get("http://practice.automationtesting.in/");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   driver.manage().window().maximize();
	   driver.findElement(By.linkText("Shop")).click();
	   
	   WebElement element = driver.findElement(By.xpath("//select[@name='orderby']"));
	   Select scSelect = new Select(element);
	   scSelect.selectByValue("popularity");
	
	}
	
	
	@Test
	public void test4() throws InterruptedException
	{
	   WebDriver driver = new ChromeDriver();
	   driver.get("http://practice.automationtesting.in/");
	   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	   driver.manage().window().maximize();
	   driver.findElement(By.linkText("Shop")).click();
	  
	   driver.findElement(By.xpath("//li/a[text()='HTML']")).click();
	   List<WebElement> productsElements = driver.findElements(By.xpath("//li[contains(@class,'product')]"));
	   
	   Optional<WebElement> productOnSale = productsElements.stream()
			    .filter(e -> !e.findElements(By.xpath(".//span[@class='onsale']")).isEmpty())
			    .findFirst();

			if (productOnSale.isPresent()) {
			    WebElement addToBasket = productOnSale.get()
			        .findElement(By.xpath(".//following-sibling::a[text()='Add to basket']"));
			    addToBasket.click();
			}
			
			driver.close();
	}
}
