package PageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractHelper.HelperClass;
import CommonObjectClasses.PageObjectClass;

public class ConfirmationPage extends HelperClass{
	
	public WebDriver driver;
	public PageObjectClass pageObjectClassObject;
	
	public ConfirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@class='woocommerce-thankyou-order-received']")
	WebElement successMessageElement;
	
	@FindBy(css="li[class*='order'] strong")
	WebElement orderNumberElement;
	
	@FindBy(css="li[class*='date'] strong")
	WebElement dateElement;
	
	@FindBy(css="li[class*='total'] strong")
	WebElement totalElement;
	
	@FindBy(css="li[class*='method'] strong")
	WebElement methodElement;
	
	public String orderSuccessMessage()
	{
		return successMessageElement.getText();
	}
	
	public HashMap<String, String> getOrderDetails()
	{
		HashMap<String, String> valueHashMap = new HashMap<String, String>();
		valueHashMap.put("Order Number", orderNumberElement.getText());
		valueHashMap.put("Order Date", dateElement.getText());
		valueHashMap.put("Total Amount", totalElement.getText());
		valueHashMap.put("Payment Method", methodElement.getText());

		return valueHashMap;
	}

	
}
