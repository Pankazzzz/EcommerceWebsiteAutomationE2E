package Practice.Tests;

import java.awt.PrintJob;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class SmokeTest_5 {
	
	
	//@Test
	public void test1() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			WebElement widgetElement = driver.findElement(By.linkText("Widgets"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Accordion']")).click();
			driver.findElement(By.cssSelector("[href=\"#collapse4\"]")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			
//			widgetElement = driver.findElement(By.linkText("Widgets"));
//			actions.moveToElement(widgetElement).build().perform();
//			driver.findElement(By.xpath("//a[normalize-space()='AutoComplete']")).click();
//			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div[class*='autocomplete']"))));
//			Thread.sleep(2000);
//			driver.findElement(By.xpath("//div[contains(@class,'autocomplete')]/input")).sendKeys("ind");
//			wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//*[@class=\"ui-menu-item\"]"))));
//			List<WebElement> elements = driver.findElements(By.xpath("//*[@class=\"ui-menu-item\"]"));
//			elements.stream().filter(e -> e.getText().equals("India")).findFirst().orElse(null).click();
//	
	
//			widgetElement = driver.findElement(By.linkText("Widgets"));
//			actions.moveToElement(widgetElement).build().perform();
//			driver.findElement(By.xpath("//a[normalize-space()='Datepicker']")).click();
//			System.out.println(driver.findElement(By.id("datepicker1")).isEnabled());
//			driver.findElement(By.id("datepicker2")).sendKeys("09/24/2025"+ Keys.TAB);

			widgetElement = driver.findElement(By.linkText("Widgets"));
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Slider']")).click();
			WebElement sliderElement = driver.findElement(By.cssSelector("[class*='ui-slider-handle']"));
			actions.clickAndHold(sliderElement).moveByOffset(20, 0).release().perform();
			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			
			//jsExecutor.executeScript("arguments[0].setAttribute('style=','left: 60%;');", sliderElement);
			//js.executeScript("arguments[0].click();",driver.findElement(By.id("submitBtn")));
			//js.executeScript("arguments[0].value='Hello World';", driver.findElement(By.id("username")));
	}

	
	//@Test
	public void test2() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			WebElement widgetElement = driver.findElement(By.linkText("Interactions"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			actions.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='Drag and Drop']"))).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Static']")).click();
			
			WebElement drag1= driver.findElement(By.id("angular"));
			WebElement dropElement=driver.findElement(By.id("droparea"));
			actions.dragAndDrop(drag1, dropElement).build().perform();

			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			WebElement drag2= driver.findElement(By.id("mongo"));
			jsExecutor.executeScript("arguments[0].scrollIntoView('true');", drag2);
			actions.dragAndDrop(drag2, dropElement).build().perform();

			
	}

	//@Test
	public void test3() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			WebElement widgetElement = driver.findElement(By.linkText("Interactions"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			actions.moveToElement(driver.findElement(By.xpath("//a[normalize-space()='Drag and Drop']"))).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Dynamic']")).click();
			
			WebElement drag1= driver.findElement(By.id("angular"));
			WebElement dropElement=driver.findElement(By.id("droparea"));
			actions.dragAndDrop(drag1, dropElement).build().perform();

			JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
			WebElement drag2= driver.findElement(By.id("mongo"));
			jsExecutor.executeScript("arguments[0].scrollIntoView('true');", drag2);
			actions.dragAndDrop(drag2, dropElement).build().perform();

			
	}

	

	@Test
	public void test4() throws InterruptedException
	{
				WebDriver driver =  new ChromeDriver();
				driver.get("https://demo.automationtesting.in/Register.html");
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				
			WebElement widgetElement = driver.findElement(By.linkText("Interactions"));
			Actions actions = new Actions(driver);
			actions.moveToElement(widgetElement).build().perform();
			driver.findElement(By.xpath("//a[normalize-space()='Selectable']")).click();
			driver.findElement(By.cssSelector("[href='#Serialize']")).click();
			driver.findElement(By.xpath("//ul[@class=\"SerializeFunc\"]/li[@class=\"ui-widget-content\"]/b[text()='Sakinalium - Extent Reports']")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[id='feedback']"))));
			
			System.out.println(driver.findElement(By.cssSelector("[id='feedback']")).getText());
			
	}

}
