package PageObjects;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import AbstractHelper.AppUtilities;
import AbstractHelper.HelperClass;
import CommonObjectClasses.Detail;
import CommonObjectClasses.PageObjectClass;
import net.bytebuddy.asm.MemberSubstitution.FieldValue;

public class ShopPageClass extends HelperClass{
	
	public WebDriver driver;
	public PageObjectClass pageObjectClassObject;
	public Detail detail;
	
	public ShopPageClass(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		pageObjectClassObject = new PageObjectClass(driver);
		detail = new Detail(driver);
	}
	
	@FindBy(xpath = "//button[contains(text(),'Add to basket')]")
	WebElement addToBasketElement;
	
	@FindBy(xpath = "//a[normalize-space()='View Basket']")
	WebElement viewBasketElement;
	
	@FindBy(xpath = "//div[@class='woocommerce-message']")
	WebElement messageElement;
	
	@FindBy(xpath = "//a[normalize-space()='Reviews (0)']")
	WebElement reviewElement;
	
	@FindBy(xpath = "//h3[text()='Be the first to review “Selenium Ruby” ']")
	WebElement reviewContainerElement;
	
	
	@FindBy(id="wp-comment-cookies-consent")
	WebElement consentElement;
	
	@FindBy(id="comment")
	WebElement commElement;
	
	@FindBy(xpath = "//input[@name='submit']")
	WebElement submitReviewElement;
	
	@FindBy(xpath = "//div[@class='comment-text']/div[@itemprop='description']/p")
	List<WebElement> reviewTextElement;
	
	@FindBy(xpath = "//h2[text()='Product Description']/following-sibling::p")
	WebElement productDesElement;
	
	public void addProduct()
	{
		waitUntilElementIsVisible(addToBasketElement);
		addToBasketElement.click();
		waitUntilElementIsClicable(viewBasketElement);
	}
	
	public String getMessage()
	{
		return messageElement.getText();
	}
	
	public CartPageClass clickOnViewBasket()
	{
		viewBasketElement.click();
		return new CartPageClass(driver);
	}
	
	public void clickOnReview()
	{
		reviewElement.click();
		waitUntilElementIsVisible(reviewContainerElement);
	}
	
	public void addRating(String rate,String comment)
	{
		driver.findElement(By.cssSelector("a[class='star-"+rate+"']")).click();
		commElement.sendKeys(comment);

	}
	
	public void clickonConsent()
	{
		consentElement.click();
	}
	
	public void submitReview()
	{
		submitReviewElement.click();
	}
	
	public String getProductDescription()
	{
		scrollBy(0, 500);
		return productDesElement.getText();
	}
	
	public String validateReview(String reviewString) throws Exception
	{
		WebElement reviewElement = driver.findElement(By.cssSelector("#comments"));
		jsScrollIntoView(reviewElement);
		AppUtilities.sleep(1000);
		waitUntilElementIsVisible(commElement);
		List<WebElement> descText = reviewTextElement.stream().filter(e -> e.getText().equals(reviewString)).collect(Collectors.toList());
		return descText.getFirst().getText();
	}
	
	

	


}
