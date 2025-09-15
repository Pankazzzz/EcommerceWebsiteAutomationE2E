package FunctionalTestcasesPOM;

import java.awt.Checkbox;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.stream.Collectors;

import javax.swing.text.html.parser.DTD;
import javax.xml.stream.events.StartDocument;

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
import DataResources.GetTestCaseDetails;
import ExcelDataUtil.GetExcelData;
import PageObjects.CartPageClass;
import PageObjects.CheckoutPageClass;
import PageObjects.ConfirmationPage;
import PageObjects.ShopPageClass;

public class FrameWorkSmokeTest3 extends BaseTest{

	
	@Test
	public void e2EFlow08(Method m) throws InterruptedException, IOException
	{
		//---------------------------- Commmon StartDocument -----------------------------//
		logger.info("Reading Excel data started");
		
		Properties properties = GetTestCaseDetails.getTestCaseDetails();
		String testCaseNameString = properties.getProperty(m.getName());
		HashMap<String, String> testCaseDataHashMap;
		HashMap<String, String> dataHashMap =GetExcelData.getDataForTheTestCase(testCaseNameString);
		System.out.println(dataHashMap);
		System.out.println(Arrays.deepToString(GetExcelData.getAllData()));
		
		//---------------------------- Commmon EndDocument -----------------------------//
		
		landPageClass.pageObjectClassObject.clickTab("Shop");
		landPageClass.pageObjectClassObject.clickOnHomePage();
		landPageClass.scrollBy(0, 500);
		Assert.assertEquals(landPageClass.returnProductSize(), Integer.parseInt(dataHashMap.get("ProductQuantity1")), "Product Size Not Matching");
		
		ShopPageClass shopPageClass=landPageClass.clickFirstProduct();
		shopPageClass.addProduct();
		System.out.println(shopPageClass.getMessage());
		Assert.assertTrue(shopPageClass.getMessage().contains("has been added to your basket."),"Product Not Added");
		CartPageClass cartPageClassObject=shopPageClass.clickOnViewBasket();
		
		cartPageClassObject.setCoupon(dataHashMap.get("Coupon"));
		cartPageClassObject.applyCoupon();
		Check.assertEqual(driver.findElement(By.xpath("//div[@class='woocommerce-message']")).getText(), "Coupon code applied successfully.");
		cartPageClassObject.setQuantity(dataHashMap.get("ProductQuantity2"));
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
		 checkoutPageClassObject.detail.setField("First Name", dataHashMap.get("FirstName")+random.nextInt());
		 checkoutPageClassObject.detail.setField("Last Name", dataHashMap.get("LastName"));
		 checkoutPageClassObject.detail.setField("Email", "Demo"+random.nextInt()+"@demo.com");
		 checkoutPageClassObject.scrollBy(0, 200);
		 checkoutPageClassObject.detail.selectCountry(dataHashMap.get("Country"));
		 checkoutPageClassObject.detail.setField("Phone", dataHashMap.get("Phone"));
		 checkoutPageClassObject.detail.setField("Town", dataHashMap.get("Town"));
		 checkoutPageClassObject.detail.setField("Post", dataHashMap.get("Post"));
		 checkoutPageClassObject.detail.setField("Address", dataHashMap.get("Address"));
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
