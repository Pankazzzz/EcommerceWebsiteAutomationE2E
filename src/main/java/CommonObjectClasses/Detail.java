package CommonObjectClasses;

import java.util.concurrent.ConcurrentHashMap.KeySetView;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractHelper.HelperClass;

public class Detail extends HelperClass {
	
	public WebDriver driver;
	
	public Detail(WebDriver driver)
	{	
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".select2-chosen" )
	WebElement countryContainerElement;
	
	@FindBy(xpath = "//label[text()='Country *']/following-sibling::input[@role='combobox']")
	WebElement setCountryElement;
	
	public void setField(String field,String value)
	{
		if(field.equalsIgnoreCase("Address"))
		{
			 driver.findElement(By.id("billing_address_1")).sendKeys(value);
		}else {
		driver.findElement(By.xpath("//label[contains(text(),'"+field+"')]/following-sibling::input")).sendKeys(value);
		}
	}
	
	
	public void selectCountry(String country)
	{
		moveToElemet(countryContainerElement);
		waitUntilElementIsClicable(countryContainerElement);
		countryContainerElement.click();
		setCountryElement.sendKeys(country);
		setCountryElement.sendKeys(Keys.ENTER);
	}

}
