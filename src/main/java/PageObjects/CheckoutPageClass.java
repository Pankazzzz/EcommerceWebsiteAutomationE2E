package PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractHelper.HelperClass;
import CommonObjectClasses.Detail;
import CommonObjectClasses.PageObjectClass;

public class CheckoutPageClass extends HelperClass{
	
	public WebDriver driver;
	public PageObjectClass pageObjectClassObject;
	public Detail detail;
	
	public CheckoutPageClass(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		detail = new Detail(driver);
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//h3[text()='Billing Details']" )
	WebElement billingDElement;
	
	public boolean isBillingDetailDisplayed()
	{
		return billingDElement.isDisplayed()? true : false;
	}
	
	@FindBy(name="billing_first_name")
	WebElement fnamElement;
	
	@FindBy(id="billing_last_name")
	WebElement lnamElement;
	
	@FindBy(name="billing_email")
	WebElement emailElement;
	
	@FindBy(xpath ="//label[text()='Country *']/following-sibling::input[@role='combobox']")
	WebElement countryElement;
	
	@FindBy(id="billing_phone")
	WebElement phonElement;
	
	@FindBy(id="billing_address_1")
	WebElement addressElement;

	@FindBy(id="billing_city")
	WebElement cityElement;
	
	@FindBy(id="billing_postcode")
	WebElement postCodeElement;
	
	@FindBy(id="payment_method_cod")
	WebElement codContainerElement;
	
	@FindBy(xpath = "//label[normalize-space()='Cash on Delivery']/preceding-sibling::input")
	WebElement codElement;
	
	@FindBy(id="place_order")
	WebElement placeOrderElement;
	
	
	public void clickonPaymentOption(String value) throws InterruptedException
	{
		if(value.equalsIgnoreCase("cod"))
			jsClick(codContainerElement);
		
		Thread.sleep(2000);
	}
	
	public ConfirmationPage clickOnPay()
	{
		jsClick(placeOrderElement);
		return new ConfirmationPage(driver);
	}
	


}
