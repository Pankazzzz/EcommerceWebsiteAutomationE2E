package PageObjects;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractHelper.HelperClass;
import CommonObjectClasses.PageObjectClass;
import io.cucumber.java.lu.a;

public class CartPageClass extends HelperClass{
	
	public WebDriver driver;
	public PageObjectClass pageObjectClassObject;
	
	public CartPageClass(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//*[@name='coupon_code' and @id='coupon_code']")
	WebElement couponFieldElement;
	
	@FindBy(name = "apply_coupon")
	WebElement applyCouponElement;
	
	@FindBy(xpath = "//div[@class='woocommerce-message']")
	WebElement messageElement;
	
	@FindBy(css = "[title='Qty']")
	WebElement quantityElement;
	
	@FindBy(name="update_cart")
	WebElement updateCartElement;
	
	@FindBy(xpath = "//td[@data-title='Subtotal']//span[@class='woocommerce-Price-amount amount']")
	WebElement amountElement;
	
	@FindBy(xpath = "//td[@data-title='Tax']//span[@class='woocommerce-Price-amount amount']")
	WebElement taxElement;
	
	@FindBy(xpath = "(//td[@data-title='Total']//span[@class='woocommerce-Price-amount amount'])[2]")
	WebElement totalElement;
	
	@FindBy(xpath = "//a[contains(normalize-space(),'Checkout')]")
	WebElement checkoutElement;
	
	@FindBy(css=".remove")
	WebElement removeProductElement;
	
	@FindBy(xpath = "//a[text()='Undo?']")
	WebElement undoElement;
	
	public void setCoupon(String couString)
	{
		couponFieldElement.sendKeys(couString);
	}
	
	public void applyCoupon()
	{
		applyCouponElement.click();
	}
	
	public String getMessage()
	{
		return messageElement.getText();
	}
	
	public void setQuantity(String quantity)
	{
		quantityElement.clear();
		quantityElement.sendKeys(quantity);
		quantityElement.sendKeys(Keys.TAB);
	}
	
	public void updateCart() throws InterruptedException
	{
		updateCartElement.click();
		Thread.sleep(2000);
		
	}
	
	public boolean updateCartButtonStatus()
	{
		return updateCartElement.isEnabled() ? true : false;
	}

	public HashMap<String, Integer> getOrderAmount()
	{
		String amountString = amountElement.getText();
		String taxString = taxElement.getText();
		String totalString = totalElement.getText();
	
		int amount = Integer.parseInt(amountString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		int tax = Integer.parseInt(taxString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		int total = Integer.parseInt(totalString.split("\\.")[0].replaceAll("[$,₹.]", ""));
		
		HashMap<String, Integer> amountMap = new HashMap<String, Integer>();
		amountMap.put("amount", amount);
		amountMap.put("tax", tax);
		amountMap.put("total", total);
		return amountMap;
	}

	public CheckoutPageClass clickOnCheckout()
	{
		moveToElemet(checkoutElement);
		checkoutElement.click();
		CheckoutPageClass checkoutPageClass = new CheckoutPageClass(driver);
		return checkoutPageClass;
	}
	
	public String removeProduct()
	{
		removeProductElement.click();
		String string = messageElement.getText();
		undoElement.click();
		return string;
	}
}
