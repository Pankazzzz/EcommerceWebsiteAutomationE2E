package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractHelper.HelperClass;
import CommonObjectClasses.PageObjectClass;

public class LandPageClass extends HelperClass{
	
	public WebDriver driver;
	public PageObjectClass pageObjectClassObject;
	
	public LandPageClass(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		pageObjectClassObject=new PageObjectClass(driver);
	}
	
	@FindBy(className = "products")
	List<WebElement> products;
	
	public int returnProductSize()
	{
		return products.size();
	}
	
	public ShopPageClass clickFirstProduct()
	{
		waitUntilElementIsClicable(products.get(0));
		products.get(0).click();
		ShopPageClass shopPageClassObject = new ShopPageClass(driver);
		return shopPageClassObject;
	}
	

}
