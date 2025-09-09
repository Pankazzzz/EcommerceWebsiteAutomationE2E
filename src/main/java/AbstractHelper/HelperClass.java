package AbstractHelper;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperClass {
	
public WebDriver driver;

	public JavascriptExecutor jsExecutor;
	public WebDriverWait wait;
	public Actions actions;

	
	public HelperClass(WebDriver driver)
	{
		this.driver=driver;
		jsExecutor= (JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		actions = new Actions(driver);
	}
	
	public void scrollBy(int cordinate1,int cordinate2)
	{
		jsExecutor.executeScript("window.scrollBy('"+cordinate1+"','"+cordinate2+"')");
	}
	
	public void waitUntilElementIsClicable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitUntilElementIsVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void moveToElemet(WebElement element)
	{
		 actions.moveToElement( element).build().perform();

	}
	
	public void jsClick(WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}
	
	public void jsScrollIntoView(WebElement element)
	{
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);

	}
}
