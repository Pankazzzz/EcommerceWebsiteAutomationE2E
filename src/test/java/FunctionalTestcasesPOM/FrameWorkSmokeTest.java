package FunctionalTestcasesPOM;

import java.awt.Checkbox;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.DTD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BaseBrowserHelper.BaseTest;
import BaseUtilties.RetryAnalyzer;
import CommonObjectClasses.Check;
import DataReaderFunctions.DataProviderClass;
import DataReaderFunctions.JsonDataReader;
import PageObjects.CartPageClass;
import PageObjects.CheckoutPageClass;
import PageObjects.ConfirmationPage;
import PageObjects.ShopPageClass;

public class FrameWorkSmokeTest extends BaseTest{

	@Test(groups = "Smoke")
	public void basicValidation01()
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
	}
	
	//@Test
	public void addProduct02()
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
	}
	
	//@Test
	public void validateProduct03()
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		
		String description = shopPageClass.getProductDescription();
		System.out.println(description);
	}
	
	//@Test(dataProvider = "getData", dataProviderClass = DataProviderClass.class, retryAnalyzer = RetryAnalyzer.class, groups = "Smoke")
	public void addReview04(String email,String name) throws Exception
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 4, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		shopPageClass.scrollBy(0, 500);
		shopPageClass.clickOnReview();
		Random random = new Random();
		String reviewString =  "Demo"+random.nextInt();
		shopPageClass.addRating("4", reviewString);
		shopPageClass.detail.setField("Email", email);
		shopPageClass.detail.setField("Name", name);
		shopPageClass.clickonConsent();
		shopPageClass.submitReview();
		String reString = shopPageClass.validateReview(reviewString);
		Assert.assertEquals(reString, reviewString, "Review not matching");
		
	}
	
	//@Test
	public void checkoutFlow05()  throws Exception
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		CartPageClass cartPageClassObject=shopPageClass.clickOnViewBasket();
		
		cartPageClassObject.setCoupon("krishnasakinala");
		cartPageClassObject.applyCoupon();
		Check.assertEqual(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText(), "Coupon code applied successfully.");
		cartPageClassObject.setQuantity("10");
		cartPageClassObject.updateCart();
		System.out.println(cartPageClassObject.getMessage());
		Check.assertFalse(driver.findElement(By.name("update_cart")).isEnabled());
		HashMap<String, Integer> orderAmount=cartPageClassObject.getOrderAmount();
		System.out.println(orderAmount.get("amount")+" "+orderAmount.get("tax")+" "+orderAmount.get("total"));
		Check.assertEquals((orderAmount.get("amount")+orderAmount.get("tax"))-50, orderAmount.get("total"), "Total not matching");
		cartPageClassObject.scrollBy(0, 500);
		CheckoutPageClass checkoutPageClassObject =cartPageClassObject.clickOnCheckout();
	
		 Random random = new Random();
		 Assert.assertTrue(checkoutPageClassObject.isBillingDetailDisplayed(),"Elements not displayed");
		 List<HashMap<String, String>>  dataHashMaps = JsonDataReader.dataRead(null);
		 checkoutPageClassObject.detail.setField("First Name", dataHashMaps.get(0).get("firstName")+random.nextInt());
		 checkoutPageClassObject.detail.setField("Last Name", dataHashMaps.get(0).get("lastName"));
		 checkoutPageClassObject.detail.setField("Email", "Demo"+random.nextInt()+"@demo.com");
		 checkoutPageClassObject.scrollBy(0, 200);
		 checkoutPageClassObject.detail.selectCountry("Afghanistan");
		 checkoutPageClassObject.detail.setField("Phone", dataHashMaps.get(0).get("phone"));
		 checkoutPageClassObject.detail.setField("Town", dataHashMaps.get(0).get("town"));
		 checkoutPageClassObject.detail.setField("Post", dataHashMaps.get(0).get("postcode"));
		 checkoutPageClassObject.detail.setField("Address", dataHashMaps.get(0).get("address"));
		 checkoutPageClassObject.clickonPaymentOption("cod");		 
		 ConfirmationPage confirmationPageObject=checkoutPageClassObject.clickOnPay();
	}
	
	
	//@Test
	public void removeProduct07() throws InterruptedException
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		CartPageClass cartPageClassObject=shopPageClass.clickOnViewBasket();
		String message=cartPageClassObject.removeProduct();
		Check.assertTrue(message.contains("Selenium Ruby removed."));
		 
	}
	
	@Test
	public void e2EFlow08() throws InterruptedException, IOException
	{
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), 3, "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		CartPageClass cartPageClassObject=shopPageClass.clickOnViewBasket();
		
		cartPageClassObject.setCoupon("krishnasakinala");
		cartPageClassObject.applyCoupon();
		Check.assertEqual(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText(), "Coupon code applied successfully.");
		cartPageClassObject.setQuantity("10");
		cartPageClassObject.updateCart();
		System.out.println(cartPageClassObject.getMessage());
		Check.assertFalse(driver.findElement(By.name("update_cart")).isEnabled());
		HashMap<String, Integer> orderAmount=cartPageClassObject.getOrderAmount();
		System.out.println(orderAmount.get("amount")+" "+orderAmount.get("tax")+" "+orderAmount.get("total"));
		Check.assertEquals((orderAmount.get("amount")+orderAmount.get("tax"))-50, orderAmount.get("total"), "Total not matching");
		cartPageClassObject.scrollBy(0, 500);
		CheckoutPageClass checkoutPageClassObject =cartPageClassObject.clickOnCheckout();
	
		 Random random = new Random();
		 Assert.assertTrue(checkoutPageClassObject.isBillingDetailDisplayed(),"Elements not displayed");
		 List<HashMap<String, String>>  dataHashMaps = JsonDataReader.dataRead(null);
		 checkoutPageClassObject.detail.setField("First Name", dataHashMaps.get(0).get("firstName")+random.nextInt());
		 checkoutPageClassObject.detail.setField("Last Name", dataHashMaps.get(0).get("lastName"));
		 checkoutPageClassObject.detail.setField("Email", "Demo"+random.nextInt()+"@demo.com");
		 checkoutPageClassObject.scrollBy(0, 200);
		 checkoutPageClassObject.detail.selectCountry("Afghanistan");
		 checkoutPageClassObject.detail.setField("Phone", dataHashMaps.get(0).get("phone"));
		 checkoutPageClassObject.detail.setField("Town", dataHashMaps.get(0).get("town"));
		 checkoutPageClassObject.detail.setField("Post", dataHashMaps.get(0).get("postcode"));
		 checkoutPageClassObject.detail.setField("Address", dataHashMaps.get(0).get("address"));
		 checkoutPageClassObject.clickonPaymentOption("cod");		 
		 ConfirmationPage confirmationPageObject=checkoutPageClassObject.clickOnPay();
		 
		 Assert.assertEquals(confirmationPageObject.orderSuccessMessage(), "Thank you. Your order has been received.");
		 HashMap<String, String> orderDetails=confirmationPageObject.getOrderDetails(); 
		 System.out.println("Order Number: " + orderDetails.get("Order Number"));
		 System.out.println("Order Date: " + orderDetails.get("Order Date"));
		 System.out.println("Total Amount: " + orderDetails.get("Total Amount"));
		 System.out.println("Payment Method: " + orderDetails.get("Payment Method"));

		 
	}
	
	
}
