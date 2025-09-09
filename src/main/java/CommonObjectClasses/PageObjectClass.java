package CommonObjectClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageObjectClass {

public WebDriver driver;
	
	public PageObjectClass(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[alt='Automation Practice Site']")
	WebElement homePageElement;
	
	public void clickTab(String tabName)
	{
		driver.findElement(By.xpath("//a[contains(text(),'"+tabName+"')]")).click();
	}
	
	public void clickOnHomePage()
	{
		homePageElement.click();
	}
	
	
}
