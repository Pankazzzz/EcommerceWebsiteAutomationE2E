package Practice.Tests;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SmoketTest_4 {

	//@Test
	public void test1()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://letcode.in/edit");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("fullName")).sendKeys("Pankaj Shukla");
		String valString = " Boy"+Keys.TAB;
		driver.findElement(By.xpath("//*[@placeholder=\"Enter \" and @id='join']")).sendKeys(valString);
		System.out.println(driver.findElement(By.id("getMe")).getAttribute("value"));
		driver.findElement(By.id("clearMe")).clear();
		System.out.println(driver.findElement(By.id("clearMe")).isEnabled());
		driver.close();
	}
	

	//@Test
	public void test2()
	{
		WebDriver driver = new ChromeDriver();
		driver.get("https://letcode.in/button");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("home")).click();
		driver.navigate().back();
		System.out.println(driver.findElement(By.id("position")).getLocation());
		System.out.println(driver.findElement(By.id("color")).getCssValue("background-color"));
		System.out.println(driver.findElement(By.id("property")).getSize());
		System.out.println(driver.findElement(By.id("property")).getRect().getDimension().height);
		
		Actions actions = new Actions(driver);
		boolean flag=false;
	
			do {
			actions.clickAndHold(driver.findElement(By.xpath("//div/h2"))).build().perform();
			try {
				WebElement element= driver.findElement(By.xpath("//h2[contains(text(),' Button has been long pressed')]"));
				flag = element.isDisplayed();
			}catch (Exception e) {
				// TODO: handle exception
			}
			}while(flag==false);
				
		driver.close();
	}
	
		//@Test
		public void test3()
		{
			WebDriver driver = new ChromeDriver();
			driver.get("https://letcode.in/dropdowns");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			WebElement select = driver.findElement(By.id("fruits"));
			Select scSelect = new Select(select);
			scSelect.selectByIndex(3);
			System.out.println(driver.findElement(By.xpath("//div[@class='select']/following-sibling::div/descendant::p")).getText());
			System.out.println(scSelect.getFirstSelectedOption().getText());
			
			WebElement select1 = driver.findElement(By.id("superheros"));
			Select scSelect1 = new Select(select1);
			scSelect1.selectByVisibleText("Batman");
			System.out.println(driver.findElement(By.xpath("//div[@class='select is-multiple']/following-sibling::div/descendant::p")).getText());
			
			driver.get("https://letcode.in/alert");
			driver.findElement(By.id("modern")).click();
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[class='title']")));
			System.out.println(driver.findElement(By.cssSelector("[class='title']")).getText());
			driver.findElement(By.cssSelector("[aria-label=\"close\"]")).click();
			driver.close();
		}
		
		//@Test
		public void test4()
		{
			WebDriver driver = new ChromeDriver();
			driver.get("https://letcode.in/frame");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			System.out.println(driver.findElements(By.tagName("iframe")).size());
			driver.switchTo().frame("firstFr");
			driver.findElement(By.cssSelector("[placeholder=\"Enter email\"]")).sendKeys("Pankaj");
			driver.switchTo().defaultContent();
			
			driver.get("https://letcode.in/window");
			driver.findElement(By.id("multi")).click();
			Set<String> windows = driver.getWindowHandles();
			Iterator<String> eahcWindow = windows.iterator();
			String parentString = eahcWindow.next();
			String childString = eahcWindow.next();
			driver.switchTo().window(childString);
			System.out.println(driver.getTitle());
			driver.switchTo().window(parentString);
			driver.switchTo().defaultContent();
			driver.close();
		}
		
		//@Test
		public void test5()
		{
			WebDriver driver = new ChromeDriver();
//			driver.get("https://letcode.in/droppable");			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//			
//			WebElement dragElement= driver.findElement(By.id("draggable"));
//			WebElement dropElement= driver.findElement(By.id("droppable"));
//			Actions actions = new Actions(driver);
//			actions.dragAndDrop(dragElement, dropElement).build().perform();
			
			driver.get("https://letcode.in/selectable");
			driver.findElement(By.xpath("//div[normalize-space()='Playwright']")).click();
			Actions actions = new Actions(driver);
			actions.moveToElement(driver.findElement(By.xpath("//div[normalize-space()='Cypress']"))).click().perform();

		}
		
		//@Test
		public void test6()
		{
			WebDriver driver = new ChromeDriver();
			driver.get("https://letcode.in/sortable");			
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			
			WebElement task1 = driver.findElement(By.xpath("//div[@id='sample-box1' and text()=' Get to work']"));
		    WebElement task2 = driver.findElement(By.xpath("//div[@id='sample-box1' and text()=' Pick up groceries']"));
		    WebElement task3 = driver.findElement(By.xpath("//div[@id='sample-box1' and text()=' Go home']"));
		    WebElement task4 = driver.findElement(By.xpath("//div[@id='sample-box1' and text()=' Fall asleep']"));
	
			Actions actions = new Actions(driver);

			 actions.clickAndHold(task2)
	           .moveByOffset(0, -50)   // move upward ~50px
	           .pause(Duration.ofMillis(500)) // tiny wait helps
	           .release()
	           .build()
	           .perform();


		    // Example: move "Go home" before "Pick up groceries"
		    actions.clickAndHold(task3)
		           .moveToElement(task2)
		           .release()
		           .perform();

		    // Example: move "Fall asleep" to the end
		    actions.clickAndHold(task4)
		           .moveByOffset(0, 150)   // drag downward
		           .release()
		           .perform();
		    
//			System.out.println(workElement.getLocation());
//			actions.clickAndHold(homElement).moveByOffset(88, 258).release().perform();
		}
		
		//@Test
		public void test7() throws InterruptedException
		{
			WebDriver driver =  new ChromeDriver();
			driver.get("https://letcode.in/slider");
			driver.manage().window().maximize();
			
			WebElement sliderElement = driver.findElement(By.id("generate"));
			Actions actions = new Actions(driver);
			actions.clickAndHold(sliderElement).moveByOffset(1, 0).release().perform();
			driver.findElement(By.xpath("//*[text()='Get Countries']")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("div p[class='has-text-primary-light']"))));
			String[] countryNames=driver.findElement(By.cssSelector("div p[class='has-text-primary-light']")).getText().split("-");
			System.out.println(Arrays.asList(countryNames));
			
			
			
		}
		
		//@Test
		public void test8() throws InterruptedException
		{
			WebDriver driver =  new ChromeDriver();
			driver.get("https://letcode.in/waits");
			driver.manage().window().maximize();
			
			driver.findElement(By.id("accept")).click();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			boolean alertPresent=true;
			while (alertPresent) {
			    try {
			        // Wait until alert is present and switch to it
			        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			        System.out.println("Alert text: " + alert.getText());
			        alert.accept();
			    } catch (Exception e) {
			        // No more alerts → break the loop
			        alertPresent = false;
			    }
			}
		}
		
		//@Test
		public void test9() throws InterruptedException
		{
			WebDriver driver =  new ChromeDriver();
			driver.get("https://letcode.in/advancedtable");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@aria-controls='advancedtable']"))));
			

			
			WebElement slElement = driver.findElement(By.xpath("//*[@aria-controls='advancedtable']"));
			Select scSelect = new Select(slElement);
			scSelect.selectByVisibleText("10");
			driver.findElement(By.cssSelector("[type='search']")).sendKeys("America");
			
			
			WebElement tablElement = driver.findElement(By.xpath("//table[@id='advancedtable']"));
			List<WebElement>  tableHeadersElements = tablElement.findElements(By.xpath(".//th/span[1]"));
			List<String> headers = tableHeadersElements.stream().map(e -> e.getText()).collect(Collectors.toList());
			System.out.println(headers);
					
			List<WebElement>  tableRows = tablElement.findElements(By.xpath(".//tr[@class='ng-star-inserted']"));
			List<HashMap<String, String>> tableVaHashMaps = new ArrayList<>();
			for(int i=0;i<tableRows.size();i++)
			{
				List<WebElement> tdValues = tableRows.get(i).findElements(By.xpath(".//td"));
				for(int j=0;j<headers.size();j++)
				{
					HashMap<String, String> rowValueHashMap = new HashMap<String, String>();
					rowValueHashMap.put(headers.get(j), tdValues.get(j).getText());
					tableVaHashMaps.add(rowValueHashMap);
				}
			}
			
			System.out.println(tableVaHashMaps);
			
			
			driver.close();
			
			
		}
		
		//@Test
		public void test10()
		{
			WebDriver driver =  new ChromeDriver();
			driver.get("https://letcode.in/forms");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

			//Table2
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].scrollIntoView('true');", driver.findElement(By.id("shopping")));
		
			
			
			driver.findElement(By.xpath("//td[text()='Raj']/following-sibling::td[2]/input")).click();
		
			//Table3
			//jsExecutor.executeScript("arguments[0].scrollIntoView('true');", driver.findElement(By.xpath("//*[@class='field']/label[@for='sort']/following-sibling::table")));
			
			jsExecutor.executeScript("window.scrollBy(0,1200)");


			driver.findElement(By.xpath("//*[@class='field']/label[@for='sort']/following-sibling::table//th[1]")).click();
			
			
			List<WebElement> elements = driver.findElements(By.xpath("//*[@class='field']/label[@for='sort']/following-sibling::table//td[1]"));
			List<String> val1 = elements.stream().map(e -> e.getText()).collect(Collectors.toList());
			List<String> val2 = val1.stream().sorted().collect(Collectors.toList());
			Assert.assertEquals(val1, val2);
			
			
		}
		
		//@Test
		public void test11()
		{
					WebDriver driver =  new ChromeDriver();
					driver.get("https://letcode.in/calendar");
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					
					//1
					//driver.findElement(By.id("birthday")).sendKeys("31/07/1999");
					
					
					//2
					JavascriptExecutor js = (JavascriptExecutor) driver;
					//js.executeScript("arguments[0].value='2025-10-15';", dateInput);
					
						
					//As date here is input tag, no need to travel
					
					
		}
		
		
		@Test
		public void test12()
		{
					WebDriver driver =  new ChromeDriver();
					driver.get("https://letcode.in/shadow");
					driver.manage().window().maximize();
					driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
					
					JavascriptExecutor js = (JavascriptExecutor) driver;

					//OpenShadow
					String string = "return document.querySelector('#open-shadow').shadowRoot.querySelector('#fname')";
					WebElement element= (WebElement) js.executeScript(string);
					element.sendKeys("Pankaj");
					
					//CloseShadow
					string = "return document.querySelector('my-web-component').myRoot.querySelector('#lname').value='Shukla'";
					js.executeScript(string);
		}
				
}
