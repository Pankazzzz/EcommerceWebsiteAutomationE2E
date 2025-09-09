package CommonObjectClasses;

import org.openqa.selenium.By;
import org.testng.Assert;

public class Check {
	
	

	public static void assertEqual(String actual,String Expected,String message)
	{
		Assert.assertEquals(actual, Expected, message);
	}

	public static void assertEqual(String actual,String Expected)
	{
		Assert.assertEquals(actual, Expected);
	}
	
	public static void assertFalse(Boolean condition,String message)
	{
		Assert.assertFalse(condition, message);
	}

	public static void assertFalse(Boolean condition)
	{
		Assert.assertFalse(condition);
	}
	
	public static void assertEquals(Integer actual,Integer Expected,String message)
	{
		Assert.assertEquals(actual, Expected, message);
	}
	
	public static void assertTrue(boolean condition) {
	    Assert.assertTrue(condition, null);
	  }


}
